package au.edu.sydney.soft3202.majorproject.view;

import au.edu.sydney.soft3202.majorproject.controller.APIFacade;
import au.edu.sydney.soft3202.majorproject.model.api.*;
import au.edu.sydney.soft3202.majorproject.model.api.modifylist.*;
import au.edu.sydney.soft3202.majorproject.model.database.Database;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.BreadCrumbBar;
import java.util.*;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressIndicator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OxfordApp extends Application {
  private Database database = new Database();
  private Entry entry;
  private ModifyListStrategy pronunciationListStrategy = new PronunciationListStrategy();
  private ModifyListStrategy synonymListStrategy = new EntriesListStrategy();
  private ModifyListStrategy newWordsListStrategy = new EntriesListStrategy();
  private ModifyListStrategy entriesListStrategy = new EntriesListStrategy();
  private APIFacade apiFacade = new APIFacade(input, output, database);

  private Stage entryWindow = new Stage();
  private Stage window;

  private Menu menu;
  private MusicPlayer musicPlayer = new MusicPlayer();
  private BreadCrumbBar<String> newWordBreadCrumbBar;
  private BreadCrumbBar<String> synonymBreadCrumbBar;

  private GridPane grid = new GridPane();
  private ProgressIndicator progressIndicator = new ProgressIndicator();

  private Task<Integer> task;
  private Task<Integer> task2;

  private final ExecutorService pool =
      Executors.newFixedThreadPool(
          4,
          runnable -> {
            Thread thread = new Thread(runnable);
            thread.setDaemon(true);
            return thread;
          });

  private static String inputParameter;
  private static Input input;
  private static Output output;

  private final Scene scene;

  public static void main(String[] args) {
    if (args.length != 2) {
      throw new RuntimeException("Please provide two arguments");
    }
    if (args[0].equals("online")) {
      input = new OnlineInput();
    } else if (args[0].equals("offline")) {
      input = new OfflineInput();
    } else {
      throw new RuntimeException("Input must be either online or offline");
    }

    if (args[1].equals("online")) {
      output = new OnlineOutput();
    } else if (args[1].equals("offline")) {
      output = new OfflineOutput();
    } else {
      throw new RuntimeException("Output must be either online or offline");
    }

    inputParameter = args[0];

    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    window = primaryStage;
    window.setScene(getScene());
    window.setTitle("Oxford Dictionary App");
    window.show();
  }

  public OxfordApp() {
    BorderPane pane = new BorderPane();
    this.scene = new Scene(pane, 500, 400);
    this.entry = new Entry();

    initializeDatabase();
    buildCenter();
    pane.setTop(buildTop());
    pane.setCenter(grid);
    musicPlayer.playThemeMusic();
  }

  /** creates and sets up database */
  public void initializeDatabase() {
    apiFacade.createDB(inputParameter);
    apiFacade.setupDB(inputParameter);
  }

  /** Builds Menu Bar, for the top of the main Application window */
  public MenuBar buildTop() {
    grid.setPadding(new Insets(10, 10, 10, 10));
    grid.setVgap(8);
    menu = new Menu("Additional Features");

    MenuItem aboutFeatureItem = new MenuItem("About");
    aboutFeatureItem.setOnAction(
        (event) -> {
          aboutFeature();
        });
    menu.getItems().add(aboutFeatureItem);
    addPronunciationListItemToMenuBar();

    MenuBar menuBar = new MenuBar();
    menuBar.getMenus().addAll(menu);

    return menuBar;
  }

  /** Adds Pronunciation List Item to menuBar */
  public void addPronunciationListItemToMenuBar() {
    MenuItem pronunciationListItem = new MenuItem("Pronunciation List");
    pronunciationListItem.setOnAction((event) -> pronunciationListFeature());
    menu.getItems().add(pronunciationListItem);
  }

  /** Builds The center of the Main Application */
  public void buildCenter() {
    grid.setPadding(new Insets(10, 10, 10, 10));
    grid.setVgap(8);

    addBreadCrumbBar();
    enterWord();
    sendReport();
    clearCache();
    toggleMusic();
  }

  /** Displays information about the Application, its developer and references used */
  private void aboutFeature() {
    entryWindow.setTitle("About");

    TextArea text = new TextArea();
    text.setPrefRowCount(50);
    text.setText(
        "Application Name: Oxford Dictionary App\n"
            + "Developer Name: Anusha Amer\n"
            + "References used:  \n"
            + "     https://stackoverflow.com/questions/40244676/how-to-loop-an-mp3-file-in-javafx \n"
            + "     https://www.twilio.com/docs/sms/api/message-resource \n"
            + "     https://developer.oxforddictionaries.com/documentation#!/Entries \n");

    VBox layout = new VBox();
    layout.getChildren().addAll(text);

    Scene scene = new Scene(layout, 480, 150);
    entryWindow.setScene(scene);
    entryWindow.show();
  }

  /** Displays window for list of Pronunciations chosen by user */
  private void pronunciationListFeature() {
    entryWindow.setTitle("Pronunciation List");

    ListView<String> listOfPronunciationsListView = new ListView<>();
    List pronunciations = pronunciationListStrategy.getList();
    for (int i = 0; i < pronunciations.size(); i++) {
      listOfPronunciationsListView.getItems().add((String) pronunciations.get(i));
      System.out.println(pronunciations.get(i));
    }

    listOfPronunciationsListView.setOnMouseClicked(
        (e) -> {
          String pronunciationChosen =
              listOfPronunciationsListView.getSelectionModel().getSelectedItem();
          musicPlayer.playPronunciation(pronunciationChosen);
        });

    Button removePronunciationButton = new Button("Remove Pronunciation");
    removePronunciationButton.setOnAction(
        (e) -> {
          String pronunciationChosen =
              listOfPronunciationsListView.getSelectionModel().getSelectedItem();
          pronunciationListStrategy.removeItemFromList(pronunciationChosen);
          ObservableList items = FXCollections.observableArrayList(pronunciations);
          listOfPronunciationsListView.setItems(items);
        });

    VBox layout = new VBox();
    layout.getChildren().addAll(listOfPronunciationsListView, removePronunciationButton);

    Scene scene = new Scene(layout, 480, 150);
    entryWindow.setScene(scene);
    entryWindow.show();
  }

  /** Displays main view for user to enter word and submit word into GUI */
  public void enterWord() {
    Label userWordLabel = new Label("Word:");
    GridPane.setConstraints(userWordLabel, 5, 7);

    TextField wordInput = new TextField();
    wordInput.setPromptText("Enter Word...");
    GridPane.setConstraints(wordInput, 6, 8);

    Button submitButton = new Button("Submit");
    GridPane.setConstraints(submitButton, 6, 9);
    submitButton.setOnAction(
        (event) -> {
          submitButtonAction(wordInput.getText());
        });

    grid.getChildren().addAll(userWordLabel, wordInput, submitButton);
  }

  private void runTaskEnterWord(String word) {
    task =
        new Task<>() {
          final Random rand = new Random();

          @Override
          protected Integer call() {
            updateMessage("Task One just started");
            int value = 0;
            for (int i = 0; i < 1; i++) {
              if (isCancelled()) {
                break;
              }
              enterWordAction(word);

              updateMessage("Value is currently: " + value);
              updateProgress((100), 100);

              try {
                Thread.sleep(250);
              } catch (InterruptedException ignored) {
                if (isCancelled()) {
                  updateMessage("Task was cancelled");
                  break;
                }
              }

              value = rand.nextInt(1000);
            }

            return value;
          }
        };
    ProgressIndicator progressIndicator = new ProgressIndicator();
    progressIndicator.progressProperty().bind(task.progressProperty());
    grid.getChildren().add(progressIndicator);
    pool.execute(task);
  }

  /**
   * Submit word button action
   *
   * @param word The word provided by the user
   */
  public void submitButtonAction(String word) {
    word.toLowerCase();
    updateNewWordBreadCrumbTrail(word);
    entry.setWord(word);
    if (apiFacade.getWord(inputParameter).contains(word.toLowerCase())) {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Cache Hit Warning");
      alert.setHeaderText("Word and its associated data found in database.");
      alert.setContentText("Would you like to use cached data?");

      Optional<ButtonType> result = alert.showAndWait();
      if (result.isPresent() && result.get() == ButtonType.OK) {
        String data = apiFacade.getDataFromTable(word.toLowerCase(), inputParameter);
        String synonyms = apiFacade.getSynonymsFromTable(word.toLowerCase(), inputParameter);
        String pronunciations =
            apiFacade.getPronunciationsFromTable(word.toLowerCase(), inputParameter);

        System.out.println("synonyms: " + synonyms);
        System.out.println("pronunciations: " + pronunciations);
        cachedData(data, synonyms, pronunciations);
      } else {
        runTaskEnterWord(word);
      }
    } else {
      runTaskEnterWord(word);
    }
  }

  /** Displays new word and Synonym bread Crumb bar */
  public void addBreadCrumbBar() {
    Label breadCrumbLabel = new Label("New Word Navigation Bar");
    GridPane.setConstraints(breadCrumbLabel, 1, 3);

    newWordBreadCrumbBar = new BreadCrumbBar<>();
    GridPane.setConstraints(newWordBreadCrumbBar, 1, 4);
    newWordBreadCrumbBar.setAutoNavigationEnabled(false);

    newWordBreadCrumbBar.setOnCrumbAction(
        (event) -> {
          enterWordAction(String.valueOf(event.getSelectedCrumb().getValue()));
        });

    Label synonymBreadCrumbLabel = new Label("Synonym Navigation Bar");
    GridPane.setConstraints(synonymBreadCrumbLabel, 1, 5);

    synonymBreadCrumbBar = new BreadCrumbBar<>();
    GridPane.setConstraints(synonymBreadCrumbBar, 1, 6);

    synonymBreadCrumbBar.setOnCrumbAction(
        (event) -> {
          enterWordAction(String.valueOf(event.getSelectedCrumb().getValue()));
        });
    synonymBreadCrumbBar.setAutoNavigationEnabled(false);

    grid.getChildren()
        .addAll(
            breadCrumbLabel, synonymBreadCrumbLabel, synonymBreadCrumbBar, newWordBreadCrumbBar);
  }

  /** Displays button to get report of user History */
  public void sendReport() {
    Label sendReportLabel = new Label("Send Report:");
    GridPane.setConstraints(sendReportLabel, 6, 10);
    Button sendReportButton = new Button("Send");
    GridPane.setConstraints(sendReportButton, 6, 11);
    sendReportButton.setOnAction(
        (event) -> {
          runTaskSendReport();
        });
    grid.getChildren().addAll(sendReportLabel, sendReportButton);
  }

  /** Displays a 'short' form report output as a text representation of the user's entry history */
  public void sendReportButtonAction() {
    List userHistory = entriesListStrategy.getList();
    String report = apiFacade.getReport(userHistory);
    TextArea text = new TextArea();
    text.setPrefRowCount(100);

    text.setText("Short Report: " + report);
    VBox layout2 = new VBox();
    layout2.getChildren().addAll(text);

    Scene scene = new Scene(layout2, 300, 100);
    entryWindow.setScene(scene);
    entryWindow.show();
  }

  public void runTaskSendReport() {
    task2 =
        new Task<>() {
          final Random rand = new Random();

          @Override
          protected Integer call() {
            progressIndicator.setVisible(true);
            updateMessage("Task two just started");
            int value = 0;
            Platform.runLater(
                () -> {
                  sendReportButtonAction();
                });

            updateProgress((1), 100);
            try {
              Thread.sleep(250);
            } catch (InterruptedException ignored) {
              if (isCancelled()) {
                updateMessage("Task was cancelled");
              }
            }
            value = rand.nextInt(1000);

            progressIndicator.setVisible(false);
            return value;
          }
        };
    pool.execute(task2);
  }

  /** displays user accessible function to Clear Cached entry/lemma data */
  public void clearCache() {
    Button clearCacheButton = new Button("Clear Cache");
    GridPane.setConstraints(clearCacheButton, 1, 12);
    clearCacheButton.setOnAction(
        (event) -> {
          apiFacade.deleteData(inputParameter);
        });
    grid.getChildren().addAll(clearCacheButton);
  }

  /** Display user accessible function to toggle theme music on or off (play or pause) */
  public void toggleMusic() {
    // code adopted from:
    // https://stackoverflow.com/questions/42585186/javafx-music-on-off-toggle-button-not-working
    ToggleButton playMusicButton = new ToggleButton("Play/Pause");
    GridPane.setConstraints(playMusicButton, 10, 12);
    musicPlayer.playMusicButtonAction(playMusicButton);
    grid.getChildren().add(playMusicButton);
  }

  /**
   * update bread crumb bar when new word searched by user
   *
   * @param word the word provided by the user
   */
  public void updateNewWordBreadCrumbTrail(String word) {
    entriesListStrategy.addItemToList(word);
    newWordsListStrategy.addItemToList(word);
    TreeItem<String> model =
        BreadCrumbBar.buildTreeModel(
            newWordsListStrategy
                .getList()
                .toArray(new String[newWordsListStrategy.getList().size()]));
    if (Platform.isFxApplicationThread()) {
      newWordBreadCrumbBar.setSelectedCrumb(model);
      return;
    }
  }

  /**
   * update bread crumb bar when new synonym searched from entry by user
   *
   * @param word The word provided by the user
   */
  public void updateSynonymBreadCrumbTrail(String word) {
    synonymListStrategy.addItemToList(word);
    entriesListStrategy.addItemToList(word);
    TreeItem<String> model =
        BreadCrumbBar.buildTreeModel(
            synonymListStrategy
                .getList()
                .toArray(new String[synonymListStrategy.getList().size()]));
    synonymBreadCrumbBar.setSelectedCrumb(model);
  }

  /**
   * Display entry results of word provided by the user
   *
   * @param word The word provided by the user
   */
  private void entryWordAction(String word) {
    entryWindow.setTitle("Entry Details");

    word.toLowerCase();
    String message = apiFacade.getEntry(word);
    String pronunciationsFound = apiFacade.getPronunciationEntry(word);
    String synonymsFound = apiFacade.getSynonymEntry(word);
    apiFacade.addResultsToTable(word, pronunciationsFound, synonymsFound, message, inputParameter);

    TextArea text = new TextArea();
    text.setPromptText("Output shown here...");
    text.setPrefRowCount(100);
    text.setText(message);

    Button pronunciationButton = new Button("pronounce");
    pronunciationButton.setOnAction(
        (event) -> {
          String pronunciations = apiFacade.getPronunciationEntry(word);
          List<String> pronunciationsList = Arrays.asList(pronunciations.split(", "));
          if (pronunciationsList.contains("none") && pronunciationsList.size() == 1) {
            text.setText("no pronunciations found");
          }
          pronunciationButtonAction(pronunciationsList);
        });

    Button synonymButton = new Button("synonyms");
    synonymButton.setOnAction(
        (e) -> {
          String synonyms = apiFacade.getSynonymEntry(word);
          List<String> myList = Arrays.asList(synonyms.split(", "));
          System.out.println(myList);
          if (myList.contains("none") && myList.size() == 1) {
            text.setText("no Synonyms found");
          }
          Stage stage2 = new Stage();
          stage2.setTitle("Get Synonyms");
          ListView<String> listOfSynonymsListView = new ListView<>();
          for (int i = 0; i < myList.size(); i++) {
            listOfSynonymsListView.getItems().add(myList.get(i));
          }
          Button button = new Button("Get Entry");
          button.setOnAction(
              (event) -> {
                String entryChosen = listOfSynonymsListView.getSelectionModel().getSelectedItem();
                if (!entryChosen.contains(" ")) {
                  enterWordAction(entryChosen);
                  updateSynonymBreadCrumbTrail(entryChosen);
                  stage2.close();
                } else {
                  text.setText("Not a word, please choose another synonym");
                }
              });

          VBox layout2 = new VBox(10);
          layout2.setPadding(new Insets(20, 20, 20, 20));
          layout2.getChildren().addAll(listOfSynonymsListView, button);

          Scene scene = new Scene(layout2, 400, 250);
          stage2.setScene(scene);
          stage2.show();
        });

    VBox layout2 = new VBox();
    layout2.getChildren().addAll(text, pronunciationButton, synonymButton);

    Scene scene = new Scene(layout2, 650, 350);
    entryWindow.setScene(scene);
    entryWindow.show();
  }

  /**
   * Action hen user clicks 'pronounce' on an entry/or lemma
   *
   * @param pronunciations list of pronunciations of given word
   */
  public void pronunciationButtonAction(List<String> pronunciations) {
    Stage stage2 = new Stage();
    stage2.setTitle("Play Pronunciation");
    ListView<String> listOfPronunciationsListView = new ListView<>();
    for (int i = 0; i < pronunciations.size(); i++) {
      listOfPronunciationsListView.getItems().add(pronunciations.get(i));
      System.out.println(pronunciations.get(i));
    }

    Button playButton = new Button("Play");
    playButton.setOnAction(
        (e) -> {
          String pronunciationChosen =
              listOfPronunciationsListView.getSelectionModel().getSelectedItem();
          musicPlayer.playPronunciation(pronunciationChosen);
          stage2.close();
        });

    Button addPronunciationButton = new Button("Add Pronunciation to List");
    addPronunciationButton.setOnAction(
        (e) -> {
          String pronunciationChosen =
              listOfPronunciationsListView.getSelectionModel().getSelectedItem();
          addPronunciationButtonAction(pronunciationChosen);
        });

    VBox layout2 = new VBox(10);
    layout2.setPadding(new Insets(20, 20, 20, 20));
    layout2.getChildren().addAll(listOfPronunciationsListView, playButton, addPronunciationButton);

    Scene scene = new Scene(layout2, 420, 250);
    stage2.setScene(scene);
    stage2.show();
  }

  /**
   * Adds Pronunciation Chosen by user to list of Chosen Pronunciations
   *
   * @param pronunciation pronunciation chosen by user
   */
  private void addPronunciationButtonAction(String pronunciation) {
    pronunciationListStrategy.addItemToList(pronunciation);
  }

  /**
   * Display list of entries for user to choose from if more than one entry found from lemma
   *
   * @param listOfEntries list of entries
   */
  private void multipleEntryWordsFromLemmaAction(List listOfEntries) {
    entryWindow.setTitle("Choose Entry");

    ListView<String> listOfEntriesListView = new ListView<>();
    for (int i = 0; i < listOfEntries.size(); i++) {
      listOfEntriesListView.getItems().add((String) listOfEntries.get(i));
    }
    Button button = new Button("Submit");
    button.setOnAction(
        (e) -> {
          String entryChosen = listOfEntriesListView.getSelectionModel().getSelectedItem();
          entryWordAction(entryChosen);
          updateNewWordBreadCrumbTrail(entryChosen);
        });

    VBox layout = new VBox(10);
    layout.setPadding(new Insets(20, 20, 20, 20));
    layout.getChildren().addAll(listOfEntriesListView, button);

    Scene scene = new Scene(layout, 300, 250);
    entryWindow.setScene(scene);
    entryWindow.show();
  }

  /** Display when no word found in Oxford Dictionary */
  private void noWordsAction() {
    entryWindow.setTitle("Entry Details");

    TextArea text = new TextArea();
    text.setPrefRowCount(100);

    text.setText("Sorry, No matching Words found");

    VBox layout = new VBox();
    layout.getChildren().addAll(text);

    Scene scene = new Scene(layout, 250, 100);
    entryWindow.setScene(scene);
    entryWindow.show();
  }

  /**
   * Display cached data of an entry present in the database
   *
   * @param data The data associated with word
   * @param pronunciations The pronunciations associated with word
   * @param synonyms The synonyms associated with word
   */
  private void cachedData(String data, String synonyms, String pronunciations) {
    System.out.println(data);
    entryWindow.setTitle("Cached Entry Details");

    TextArea text = new TextArea();
    text.setPrefRowCount(100);
    text.setText(data);

    Button pronunciationButton = new Button("pronounce");
    pronunciationButton.setOnAction(
        (event) -> {
          List<String> pronunciationsList = Arrays.asList(pronunciations.split(", "));
          if (pronunciationsList.contains("none") && pronunciationsList.size() == 1) {
            text.setText("no pronunciations found");
          }
          pronunciationButtonAction(pronunciationsList);
        });

    Button synonymButton = new Button("synonyms");
    synonymButton.setOnAction(
        (e) -> {
          List<String> myList = Arrays.asList(synonyms.split(", "));
          System.out.println(myList);
          if (myList.contains("none") && myList.size() == 1) {
            text.setText("no Synonyms found");
          }
          Stage stage2 = new Stage();
          stage2.setTitle("Get Synonyms");
          ListView<String> listOfSynonymsListView = new ListView<>();
          for (int i = 0; i < myList.size(); i++) {
            listOfSynonymsListView.getItems().add(myList.get(i));
          }
          Button button = new Button("Get Entry");
          button.setOnAction(
              (event) -> {
                String entryChosen = listOfSynonymsListView.getSelectionModel().getSelectedItem();
                if (!entryChosen.contains(" ")) {
                  enterWordAction(entryChosen);
                  updateSynonymBreadCrumbTrail(entryChosen);
                  stage2.close();
                } else {
                  text.setText("Not a word, please choose another synonym");
                }
              });

          VBox layout2 = new VBox(10);
          layout2.setPadding(new Insets(20, 20, 20, 20));
          layout2.getChildren().addAll(listOfSynonymsListView, button);

          Scene scene = new Scene(layout2, 400, 250);
          stage2.setScene(scene);
          stage2.show();
        });

    VBox layout = new VBox();
    layout.getChildren().addAll(text, pronunciationButton, synonymButton);

    Scene scene = new Scene(layout, 650, 350);
    entryWindow.setScene(scene);
    entryWindow.show();
  }

  /**
   * Display depending on type of word chosen by user. ie entry/lemma/invalid
   *
   * @param word The word provided by the user
   */
  private void enterWordAction(String word) {
    String message;
    word.toLowerCase();
    if (!word.contains(" ")) {

      message = apiFacade.getEntry(word.toLowerCase());
      if (message.contains("Error Message:")) {

        String lemma = apiFacade.getLemma(word.toLowerCase());
        String listOfLemmas = apiFacade.getLexicalEntries(word.toLowerCase());
        List<String> myList =
            Arrays.asList(listOfLemmas.substring(1, listOfLemmas.length() - 1).split(", "));

        if (lemma.contains("Error Message")) {
          Platform.runLater(
              () -> {
                noWordsAction();
              });
        } else if (myList.size() == 1) {
          String newWord = myList.get(0);
          updateNewWordBreadCrumbTrail(newWord);
          Platform.runLater(
              () -> {
                entryWordAction(newWord);
              });
        } else if (myList.size() > 1) {
          Platform.runLater(
              () -> {
                multipleEntryWordsFromLemmaAction(myList);
              });
        }
      } else {
        Platform.runLater(
            () -> {
              entryWordAction(word.toLowerCase());
            });
      }
    } else {
      Platform.runLater(
          () -> {
            noWordsAction();
          });
    }
  }

  /** Gets scene for Oxford GUI */
  public Scene getScene() {
    return this.scene;
  }
}

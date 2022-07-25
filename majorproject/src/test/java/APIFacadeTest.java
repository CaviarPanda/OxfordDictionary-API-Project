import au.edu.sydney.soft3202.majorproject.controller.APIFacade;
import au.edu.sydney.soft3202.majorproject.model.api.*;
import au.edu.sydney.soft3202.majorproject.model.api.modifylist.EntriesListStrategy;
import au.edu.sydney.soft3202.majorproject.model.api.modifylist.ModifyListStrategy;
import au.edu.sydney.soft3202.majorproject.model.database.Database;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class APIFacadeTest {
  private String entry() {
    String id = "39";
    Map metadata = new HashMap();

    metadata.put("operation", "retrieve");
    metadata.put("provider", "Oxford University Press");
    metadata.put("schema", "retrieveEntry");

    List results = new ArrayList();

    Map derivativesMap = new HashMap();
    derivativesMap.put("id", "342");
    derivativesMap.put("text", "sleepy");

    List derivativesList = new ArrayList();
    derivativesList.add(derivativesMap);

    List etymologiesList = new ArrayList();
    etymologiesList.add(
        "Middle English (denoting the ‘one’ on dice): via Old French from Latin as ‘unity, a unit’");

    List dialectsList = new ArrayList();
    dialectsList.add("British English");

    Map pronunciationsMap = new HashMap();
    pronunciationsMap.put("audioFile", "https://audio.oxforddictionaries.com/en/mp3/ace_gb_3.mp3");
    pronunciationsMap.put("dialects", dialectsList);
    pronunciationsMap.put("phoneticNotation", "IPA");
    pronunciationsMap.put("phoneticSpelling", "eis");

    List pronunciationsList = new ArrayList();
    pronunciationsList.add(pronunciationsMap);

    List definitionsList = new ArrayList();
    definitionsList.add(
        "a playing card with a single spot on it, ranked as the highest card in its suit in most card games");

    Map domainClassMap = new HashMap();
    domainClassMap.put("id", "737");
    domainClassMap.put("text", "sleepy");

    List domainClassList = new ArrayList();
    domainClassList.add(domainClassMap);

    Map examplesMap = new HashMap();
    examplesMap.put("text", "sleepy");

    List examplesList = new ArrayList();
    examplesList.add(examplesMap);

    Map thesaurusLinkMap = new HashMap();
    thesaurusLinkMap.put("entry_id", "sleepy");
    thesaurusLinkMap.put("sense_id", "t_en_gb0000173.002");

    List thesaurusLinkMapList = new ArrayList();
    thesaurusLinkMapList.add(thesaurusLinkMap);

    Map sensesMap = new HashMap();
    sensesMap.put("definitions", definitionsList);
    sensesMap.put("domainClasses", domainClassList);
    sensesMap.put("examples", examplesList);
    sensesMap.put("id", "58");
    sensesMap.put("semanticClasses", domainClassList);
    sensesMap.put("shortDefinitions", etymologiesList);
    sensesMap.put("thesaurusLinks", thesaurusLinkMapList);

    List sensesList = new ArrayList();
    sensesList.add(sensesMap);

    Map entriesMap = new HashMap();
    entriesMap.put("etymologies", etymologiesList);
    entriesMap.put("pronunciations", pronunciationsList);
    entriesMap.put("senses", sensesList);

    List entriesList = new ArrayList();
    entriesList.add(entriesMap);

    Map lexicalEntriesMap = new HashMap();
    lexicalEntriesMap.put("derivatives", derivativesList);
    lexicalEntriesMap.put("entries", entriesList);
    lexicalEntriesMap.put("language", "en-gb");
    lexicalEntriesMap.put("lexicalCategory", derivativesMap);
    lexicalEntriesMap.put("text", "sleep");

    List lexicalEntriesList = new ArrayList();
    lexicalEntriesList.add(lexicalEntriesMap);

    Map resultsMap = new HashMap();
    resultsMap.put("id", "434");
    resultsMap.put("language", "en-gb");
    resultsMap.put("lexicalEntries", lexicalEntriesList);
    resultsMap.put("type", "noun");
    resultsMap.put("word", "434");

    results.add(resultsMap);

    Map entry = new HashMap();
    entry.put("id", id);
    entry.put("metadata", metadata);
    entry.put("results", results);

    return entry.toString();
  }

  private String lemma() {
    Map<Object, Object> metadata = new HashMap<>();
    metadata.put("provider", " Oxford University Press");
    List results = new ArrayList<>();

    Map grammaticalFeaturesMap = new HashMap<>();
    grammaticalFeaturesMap.put("id", "345");
    grammaticalFeaturesMap.put("text", "happy");
    grammaticalFeaturesMap.put("type", "adjective");

    List grammaticalFeaturesList = new ArrayList();
    grammaticalFeaturesList.add(grammaticalFeaturesMap);

    Map lexicalCategoryMap = new HashMap<>();
    lexicalCategoryMap.put("id", "345");
    lexicalCategoryMap.put("text", "happy");

    List lexicalCategoryList = new ArrayList();
    lexicalCategoryList.add(lexicalCategoryMap);

    Map lexicalEntriesMap = new HashMap<>();
    lexicalEntriesMap.put("grammaticalFeatures", grammaticalFeaturesList);
    lexicalEntriesMap.put("inflectionOf", lexicalCategoryList);
    lexicalEntriesMap.put("language", "En");
    lexicalEntriesMap.put("lexicalCategory", lexicalCategoryMap);
    lexicalEntriesMap.put("text", "happy");

    List lexicalEntriesList = new ArrayList();
    lexicalEntriesList.add(lexicalEntriesMap);

    Map resultLemma = new HashMap<>();
    resultLemma.put("id", "345");
    resultLemma.put("language", "En");
    resultLemma.put("lexicalEntries", lexicalEntriesList);
    resultLemma.put("word", "happy");

    results.add(resultLemma);

    Map lemma = new HashMap<Map, Map>();
    lemma.put("metadata", metadata);
    lemma.put("results", results);

    return lemma.toString();
  }

  private String lexicalEntries() {

    Map grammaticalFeaturesMap = new HashMap<>();
    grammaticalFeaturesMap.put("id", "345");
    grammaticalFeaturesMap.put("text", "happy");
    grammaticalFeaturesMap.put("type", "adjective");

    List grammaticalFeaturesList = new ArrayList();
    grammaticalFeaturesList.add(grammaticalFeaturesMap);

    Map lexicalCategoryMap = new HashMap<>();
    lexicalCategoryMap.put("id", "345");
    lexicalCategoryMap.put("text", "happy");

    Map lexicalCategoryMap2 = new HashMap<>();
    lexicalCategoryMap2.put("id", "348");
    lexicalCategoryMap2.put("text", "joyful");

    List lexicalCategoryList = new ArrayList();
    lexicalCategoryList.add(lexicalCategoryMap);
    lexicalCategoryList.add(lexicalCategoryMap2);

    Map lexicalEntriesMap = new HashMap<>();
    lexicalEntriesMap.put("grammaticalFeatures", grammaticalFeaturesList);
    lexicalEntriesMap.put("inflectionOf", lexicalCategoryList);
    lexicalEntriesMap.put("language", "En");
    lexicalEntriesMap.put("lexicalCategory", lexicalCategoryMap);
    lexicalEntriesMap.put("text", "happy");

    List lexicalEntriesList = new ArrayList();
    lexicalEntriesList.add(lexicalEntriesMap);

    Map lexicalEntries = new HashMap<Map, Map>();
    lexicalEntries.put("lexicalEntries", lexicalEntriesList);

    return lexicalEntries.toString();
  }

  private String synonymEntry() {
    Map synonymsMap = new HashMap();
    synonymsMap.put("text", "tired");

    List synonymsList = new ArrayList();
    synonymsList.add(synonymsMap);

    Map sensesMap = new HashMap();
    sensesMap.put("synonyms", synonymsList);

    System.out.println(sensesMap.toString());
    return sensesMap.toString();
  }

  private String pronunciationEntry() {
    Map pronunciationsMap = new HashMap();
    pronunciationsMap.put("audioFile", "https://audio.oxforddictionaries.com/en/mp3/ace_gb_3.mp3");

    List pronunciationsList = new ArrayList();
    pronunciationsList.add(pronunciationsMap);

    Map pronunciations = new HashMap();
    pronunciations.put("pronunciations", pronunciationsList);

    return pronunciations.toString();
  }

  @Test
  public void getEntryOnlineTest() {
    Input onlineInputMock = mock(OnlineInput.class);

    APIFacade apiFacade = new APIFacade(onlineInputMock, null, null);
    when(onlineInputMock.getEntry(anyString())).thenReturn(entry());
    String entry = apiFacade.getEntry("word");

    assertNotNull(entry);
    assertEquals(entry(), entry);
    verify(onlineInputMock, times(1)).getEntry(anyString());
  }

  @Test
  public void getEntryOfflineTest() {
    Input offlineInputMock = mock(OfflineInput.class);

    APIFacade apiFacade = new APIFacade(offlineInputMock, null, null);
    when(offlineInputMock.getEntry("word")).thenReturn(entry());
    String entry = apiFacade.getEntry("word");

    assertNotNull(entry);
    assertEquals(entry(), entry);
    verify(offlineInputMock, times(1)).getEntry("word");
  }

  @Test
  public void getLemmaOnlineTest() {
    Input onlineInputMock = mock(OnlineInput.class);

    APIFacade apiFacade = new APIFacade(onlineInputMock, null, null);
    when(onlineInputMock.getLemma(anyString())).thenReturn(lemma());
    String lemma = apiFacade.getLemma("word");

    assertNotNull(lemma);
    assertEquals(lemma(), lemma);
    verify(onlineInputMock, times(1)).getLemma("word");
  }

  @Test
  public void getLemmaOfflineTest() {
    Input offlineInputMock = mock(OfflineInput.class);

    APIFacade apiFacade = new APIFacade(offlineInputMock, null, null);
    when(offlineInputMock.getLemma("word")).thenReturn(lemma());
    String lemma = apiFacade.getLemma("word");

    assertNotNull(lemma);
    assertEquals(lemma(), lemma);
    verify(offlineInputMock, times(1)).getLemma("word");
  }

  @Test
  public void getLexicalEntriesOnlineTest() {
    Input onlineInputMock = mock(OnlineInput.class);

    APIFacade apiFacade = new APIFacade(onlineInputMock, null, null);
    when(onlineInputMock.getLexicalEntries("word")).thenReturn(lexicalEntries());
    String lexicalEntries = apiFacade.getLexicalEntries("word");

    assertNotNull(lexicalEntries);
    assertEquals(lexicalEntries(), lexicalEntries);
    verify(onlineInputMock, times(1)).getLexicalEntries("word");
  }

  @Test
  public void getLexicalEntriesOfflineTest() {
    Input offlineInput = mock(OfflineInput.class);

    APIFacade apiFacade = new APIFacade(offlineInput, null, null);
    when(offlineInput.getLexicalEntries("word")).thenReturn(lexicalEntries());
    String lexicalEntries = apiFacade.getLexicalEntries("word");

    assertNotNull(lexicalEntries);
    assertEquals(lexicalEntries(), lexicalEntries);
    verify(offlineInput, times(1)).getLexicalEntries("word");
  }

  @Test
  public void getSynonymEntryOnlineTest() {
    Input onlineInputMock = mock(OnlineInput.class);

    APIFacade apiFacade = new APIFacade(onlineInputMock, null, null);
    when(onlineInputMock.getSynonymEntry("word")).thenReturn(synonymEntry());
    String synonymEntry = apiFacade.getSynonymEntry("word");

    assertNotNull(synonymEntry);
    assertEquals(synonymEntry(), synonymEntry);
    verify(onlineInputMock, times(1)).getSynonymEntry("word");
  }

  @Test
  public void getSynonymEntryOfflineTest() {
    Input offlineInputMock = mock(OfflineInput.class);

    APIFacade apiFacade = new APIFacade(offlineInputMock, null, null);
    when(offlineInputMock.getSynonymEntry("word")).thenReturn(synonymEntry());
    String synonymEntry = apiFacade.getSynonymEntry("word");

    assertNotNull(synonymEntry);
    assertEquals(synonymEntry(), synonymEntry);
    verify(offlineInputMock, times(1)).getSynonymEntry("word");
  }

  @Test
  public void getPronunciationEntryOnlineTest() {
    Input onlineInputMock = mock(OnlineInput.class);

    APIFacade apiFacade = new APIFacade(onlineInputMock, null, null);
    when(onlineInputMock.getPronunciationEntry("word")).thenReturn(pronunciationEntry());
    String pronunciationEntry = apiFacade.getPronunciationEntry("word");

    assertNotNull(pronunciationEntry);
    assertEquals(pronunciationEntry(), pronunciationEntry);
    verify(onlineInputMock, times(1)).getPronunciationEntry("word");
  }

  @Test
  public void getPronunciationEntryOfflineTest() {
    Input offlineInputMock = mock(OfflineInput.class);

    APIFacade apiFacade = new APIFacade(offlineInputMock, null, null);
    when(offlineInputMock.getPronunciationEntry("word")).thenReturn(pronunciationEntry());
    String pronunciationEntry = apiFacade.getPronunciationEntry("word");

    assertNotNull(pronunciationEntry);
    assertEquals(pronunciationEntry(), pronunciationEntry);
    verify(offlineInputMock, times(1)).getPronunciationEntry("word");
  }

  @Test
  public void errorSchemaGetEntryTest() {
    Input onlineInputMock = mock(OnlineInput.class);

    APIFacade apiFacade = new APIFacade(onlineInputMock, null, null);
    when(onlineInputMock.getEntry(null)).thenReturn("error Message: no entries found");
    String entry = apiFacade.getEntry(null);

    assertThat(entry, containsString("error Message"));
    verify(onlineInputMock, never()).getEntry("word");
  }

  @Test
  public void errorSchemaGetLemmaTest() {
    Input onlineInputMock = mock(OnlineInput.class);

    APIFacade apiFacade = new APIFacade(onlineInputMock, null, null);
    when(onlineInputMock.getLemma(null)).thenReturn("error Message: no lemmas found");
    String lemma = apiFacade.getLemma(null);

    assertThat(lemma, containsString("error Message"));
    verify(onlineInputMock, never()).getLemma("word");
  }

  @Test
  public void errorSchemaGetLexicalEntriesTest() {
    Input onlineInputMock = mock(OnlineInput.class);

    APIFacade apiFacade = new APIFacade(onlineInputMock, null, null);
    when(onlineInputMock.getLexicalEntries(null))
        .thenReturn("error Message: no lexical Entries found");
    String lexicalEntry = apiFacade.getLexicalEntries(null);

    assertThat(lexicalEntry, containsString("error Message"));
    verify(onlineInputMock, never()).getLexicalEntries("word");
  }

  @Test
  public void errorSchemaGetSynonymEntriesTest() {
    Input onlineInputMock = mock(OnlineInput.class);

    APIFacade apiFacade = new APIFacade(onlineInputMock, null, null);
    when(onlineInputMock.getSynonymEntry(null))
        .thenReturn("error Message: no synonym Entries found");
    String synonym = apiFacade.getSynonymEntry(null);

    assertThat(synonym, containsString("error Message"));
    verify(onlineInputMock, never()).getSynonymEntry("word");
  }

  @Test
  public void errorSchemaGetPronunciationTest() {
    Input onlineInputMock = mock(OnlineInput.class);

    APIFacade apiFacade = new APIFacade(onlineInputMock, null, null);
    when(onlineInputMock.getPronunciationEntry("alumni"))
        .thenReturn("error Message: no pronounciations found");
    String synonym = apiFacade.getPronunciationEntry("alumni");

    assertThat(synonym, containsString("error Message"));
    verify(onlineInputMock, never()).getPronunciationEntry("word");
  }

  @Test
  public void testSendReport() {
    Output onlineOutput = mock(OnlineOutput.class);
    ModifyListStrategy entry = mock(EntriesListStrategy.class);
    APIFacade apiFacade = new APIFacade(null, onlineOutput, null);

    List entryHistory = new ArrayList();
    entryHistory.add("hello");
    entryHistory.add("world");
    when(entry.getList()).thenReturn(entryHistory);
    List report = entryHistory;
    when(onlineOutput.sendReport(report)).thenReturn(String.valueOf(report));
    String reportDetails = apiFacade.getReport(report);

    assertNotNull(reportDetails);
    verify(onlineOutput, times(1)).sendReport(report);
  }

  @Test
  public void testSendReportOffline() {
    Output offlineOutput = mock(OfflineOutput.class);
    ModifyListStrategy entry = mock(EntriesListStrategy.class);

    APIFacade apiFacade = new APIFacade(null, offlineOutput, null);
    List entryHistory = new ArrayList();
    entryHistory.add("hello");
    entryHistory.add("world");
    when(entry.getList()).thenReturn(entryHistory);
    List report = entryHistory;
    when(offlineOutput.sendReport(entryHistory)).thenReturn(String.valueOf(report));
    String reportDetails = apiFacade.getReport(report);

    assertNotNull(reportDetails);
    verify(offlineOutput, times(1)).sendReport(any());
  }

  @Test
  public void testSendNullReport() {
    Output onlineOutput = mock(OnlineOutput.class);

    APIFacade apiFacade = new APIFacade(null, onlineOutput, null);
    String actual = apiFacade.getReport(null);

    assertNotNull(actual);
    assertEquals("No report data found", actual);
    verify(onlineOutput, never()).sendReport(anyList());
  }

  @Test
  public void testSendEmptyReport() {
    Output onlineOutput = mock(OnlineOutput.class);

    APIFacade apiFacade = new APIFacade(null, onlineOutput, null);
    List emptyList = new ArrayList<>();
    String actual = apiFacade.getReport(emptyList);

    assertNotNull(actual);
    assertEquals("No report data found", actual);
    verify(onlineOutput, never()).sendReport(anyList());
  }

  @Test
  public void testCreateDB() {

    Input onlineInput = mock(OnlineInput.class);
    Output onlineOutput = mock(OnlineOutput.class);
    Database database = mock(Database.class);

    APIFacade apiFacade = new APIFacade(onlineInput, onlineOutput, database);
    apiFacade.createDB("online");

    verify(database, times(1)).createDB();
  }

  @Test
  public void testCreateDBOffline() {
    Input offlineInput = mock(OfflineInput.class);
    Output onlineOutput = mock(OnlineOutput.class);
    Database database = mock(Database.class);

    APIFacade apiFacade = new APIFacade(offlineInput, onlineOutput, database);
    apiFacade.createDB("offline");

    verify(database, never()).createDB();
  }

  @Test
  public void testSetUpDB() {
    Input onlineInput = mock(OnlineInput.class);
    Output onlineOutput = mock(OnlineOutput.class);
    Database database = mock(Database.class);

    APIFacade apiFacade = new APIFacade(onlineInput, onlineOutput, database);
    apiFacade.setupDB("online");

    verify(database, times(1)).setupDB();
  }

  @Test
  public void testSetUpDBOffline() {
    Input offlineInput = mock(OfflineInput.class);
    Output onlineOutput = mock(OnlineOutput.class);
    Database database = mock(Database.class);
    APIFacade apiFacade = new APIFacade(offlineInput, onlineOutput, database);
    apiFacade.setupDB("offline");

    verify(database, never()).setupDB();
  }

  @Test
  public void testAddResultsToTable() {
    Input onlineInput = mock(OnlineInput.class);
    Output onlineOutput = mock(OnlineOutput.class);
    Database database = mock(Database.class);

    APIFacade apiFacade = new APIFacade(onlineInput, onlineOutput, database);
    when(onlineInput.getEntry("hello")).thenReturn("hello means to greet someone");
    when(database.getDataFromTable("hello")).thenReturn("hello means to greet someone");
    String synonyms = "greeting, welcome, salutation, saluting, hailing, address, hello, hallo";
    String pronunciations =
        "https://audio.oxforddictionaries.com/en/mp3/hello_gb_1.mp3, https://audio.oxforddictionaries.com/en/mp3/hello_gb_2.mp3";
    apiFacade.addResultsToTable(
        "hello", pronunciations, synonyms, "hello means to greet someone", "online");

    assertEquals("hello means to greet someone", apiFacade.getDataFromTable("hello", "online"));
    verify(database, times(1)).addResultsToTable(any(), any(), any(), any());
  }

  @Test
  public void testAddResultsToTableOffline() {
    Input onlineInput = mock(OnlineInput.class);
    Output onlineOutput = mock(OnlineOutput.class);
    Database database = mock(Database.class);

    APIFacade apiFacade = new APIFacade(onlineInput, onlineOutput, database);
    when(onlineInput.getEntry("hello")).thenReturn("hello means to greet someone");
    when(database.getDataFromTable("hello")).thenReturn("hello means to greet someone");
    String synonyms = "greeting, welcome, salutation, saluting, hailing, address, hello, hallo";
    String pronunciations =
        "https://audio.oxforddictionaries.com/en/mp3/hello_gb_1.mp3, https://audio.oxforddictionaries.com/en/mp3/hello_gb_2.mp3";
    apiFacade.addResultsToTable(
        "hello", pronunciations, synonyms, "hello means to greet someone", "offline");
    apiFacade.getDataFromTable("hello", "offline");

    verify(database, never()).getDataFromTable(any());
  }

  @Test
  public void testGetDataFromTable() {
    Input onlineInput = mock(OnlineInput.class);
    Output onlineOutput = mock(OnlineOutput.class);
    Database database = mock(Database.class);

    APIFacade apiFacade = new APIFacade(onlineInput, onlineOutput, database);
    when(database.getDataFromTable("hello")).thenReturn("hello means to greet someone");
    String data = apiFacade.getDataFromTable("hello", "online");

    assertEquals("hello means to greet someone", data);
    verify(database, times(1)).getDataFromTable(any());
  }

  @Test
  public void testGetDataFromTableOffline() {
    Output onlineOutput = mock(OnlineOutput.class);
    Input offlineInput = mock(OfflineInput.class);
    Database database = mock(Database.class);

    APIFacade apiFacade = new APIFacade(offlineInput, onlineOutput, database);
    when(database.getDataFromTable("hello")).thenReturn("hello means to greet someone");
    String data = apiFacade.getDataFromTable("hello", "offline");

    assertEquals("cannot access cached data offline", data);
    verify(database, never()).getDataFromTable(any());
  }

  @Test
  public void testGetWord() {
    Input onlineInput = mock(OnlineInput.class);
    Output onlineOutput = mock(OnlineOutput.class);
    Database database = mock(Database.class);

    APIFacade apiFacade = new APIFacade(onlineInput, onlineOutput, database);
    List words = new ArrayList();
    words.add("hello");
    words.add("goodbye");
    when(database.getWord()).thenReturn(words);
    List<String> data = apiFacade.getWord("online");

    assertEquals(words, data);
    verify(database, times(1)).getWord();
  }

  @Test
  public void testGetWordOffline() {
    Input offlineInput = mock(OfflineInput.class);
    Output offlineOutput = mock(OfflineOutput.class);
    Database database = mock(Database.class);

    APIFacade apiFacade = new APIFacade(offlineInput, offlineOutput, database);
    List noWords = new ArrayList();
    when(database.getWord()).thenReturn(noWords);
    List<String> data = apiFacade.getWord("offline");

    assertEquals(noWords, data);
    verify(database, never()).getWord();
  }

  @Test
  public void testDeleteData() {
    Input onlineInput = mock(OnlineInput.class);
    Output onlineOutput = mock(OnlineOutput.class);
    Database database = mock(Database.class);

    APIFacade apiFacade = new APIFacade(onlineInput, onlineOutput, database);
    List noWords = new ArrayList();
    when(database.getDataFromTable("hello")).thenReturn("hello means to greet someone");
    String synonyms = "greeting, welcome, salutation, saluting, hailing, address, hello, hallo";
    String pronunciations =
        "https://audio.oxforddictionaries.com/en/mp3/hello_gb_1.mp3, https://audio.oxforddictionaries.com/en/mp3/hello_gb_2.mp3";
    apiFacade.addResultsToTable(
        "hello", pronunciations, synonyms, "hello means to greet someone", "offline");
    apiFacade.getDataFromTable("hello", "offline");
    apiFacade.deleteData("online");

    assertEquals(noWords, apiFacade.getWord("online"));
    verify(database, times(1)).deleteData();
  }

  @Test
  public void testDeleteDataOffline() {
    Input offlineInput = mock(OfflineInput.class);
    Output offlineOutput = mock(OfflineOutput.class);
    Database database = mock(Database.class);

    APIFacade apiFacade = new APIFacade(offlineInput, offlineOutput, database);
    apiFacade.deleteData("offline");

    verify(database, never()).deleteData();
  }
}

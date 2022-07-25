package au.edu.sydney.soft3202.majorproject.controller;

import au.edu.sydney.soft3202.majorproject.model.api.*;
import au.edu.sydney.soft3202.majorproject.model.database.Database;
import java.util.ArrayList;
import java.util.List;

public class APIFacade {
  private final Input input;
  private final Output output;
  private final Database database;

  public APIFacade(Input input, Output output, Database database) {
    this.input = input;
    this.output = output;
    this.database = database;
  }

  /**
   * Creates Database given correct input API status
   *
   * @param parameterStatus The input API parameter provided by user
   */
  public void createDB(String parameterStatus) {
    if (parameterStatus.equals("online")) {
      database.createDB();
    }
  }

  /**
   * Sets Up Database given correct input API status
   *
   * @param parameterStatus The input API parameter provided by user
   */
  public void setupDB(String parameterStatus) {
    if (parameterStatus.equals("online")) {
      database.setupDB();
    }
  }

  /**
   * Adds the corresponding entry data into database
   *
   * @param word The name of the word provided by the user
   * @param pronunciations The pronunciations found of the given word
   * @param synonyms The synonyms found of the given word
   * @param data The entry results of the given word
   * @param parameterStatus The input API parameter provided by user
   */
  public void addResultsToTable(
      String word, String pronunciations, String synonyms, String data, String parameterStatus) {
    if (parameterStatus.equals("online")) {
      database.addResultsToTable(word, pronunciations, synonyms, data);
    }
  }

  /**
   * Returns the Entry results given correct input API status
   *
   * @param word The name of the word provided by the user
   * @param parameterStatus The input API parameter provided by user
   * @return The data from the entry or a message stating cached data cannot be accessed
   */
  public String getDataFromTable(String word, String parameterStatus) {
    if (parameterStatus.equals("online")) {
      return database.getDataFromTable(word);
    } else {
      return "cannot access cached data offline";
    }
  }

  /**
   * Returns the pronunciations given correct input API status
   *
   * @param word The name of the word provided by the user
   * @param parameterStatus The input API parameter provided by user
   * @return The pronunciations from the entry or a message stating cached pronunciations cannot be
   *     accessed
   */
  public String getPronunciationsFromTable(String word, String parameterStatus) {
    if (parameterStatus.equals("online")) {
      return database.getPronunciationsFromTable(word);
    } else {
      return "cannot access cached pronunciations offline";
    }
  }

  /**
   * Returns the synonyms given correct input API status
   *
   * @param word The name of the word provided by the user
   * @param parameterStatus The input API parameter provided by user
   * @return The synonyms from the entry or a message stating cached synonyms cannot be accessed
   */
  public String getSynonymsFromTable(String word, String parameterStatus) {
    if (parameterStatus.equals("online")) {
      return database.getSynonymsFromTable(word);
    } else {
      return "cannot access cached synonyms offline";
    }
  }

  /**
   * Returns a list of all words found in database, given correct Input API status
   *
   * @param parameterStatus The input API parameter provided by user
   * @return The list of words found in the database. Otherwise, an empty list is returned
   */
  public List<String> getWord(String parameterStatus) {
    if (parameterStatus.equals("online")) {
      return database.getWord();
    } else {
      List noWords = new ArrayList<>();
      return noWords;
    }
  }

  /**
   * Deletes all rows from database, given correct input API status
   *
   * @param parameterStatus The input API parameter provided by user
   */
  public void deleteData(String parameterStatus) {
    if (parameterStatus.equals("online")) {
      database.deleteData();
    }
  }

  /**
   * Returns a short report of user's word and synonym history
   *
   * @param reportData List of entries/lemmas searched and synonyms retrieved by user
   * @return a short report of the user's search and synonym history. Otherwise, message stating no
   *     report data found
   */
  public String getReport(List reportData) {
    if (reportData == null || reportData.isEmpty()) {
      return "No report data found";
    }
    return output.sendReport(reportData);
  }

  /**
   * Returns the lemma of a word provided by the user
   *
   * @param word The word provided by the user
   * @return a lemma result with all data associated with lemma of given word
   */
  public String getLemma(String word) {
    return input.getLemma(word);
  }

  /**
   * Returns the lexical entries of a word provided by the user
   *
   * @param word The word provided by the user
   * @return all lexical entries of word provided by the user
   */
  public String getLexicalEntries(String word) {
    return input.getLexicalEntries(word);
  }

  /**
   * Returns the entry of a word provided by the user
   *
   * @param word The word provided by the user
   * @return all entry details of given word
   */
  public String getEntry(String word) {
    return input.getEntry(word);
  }

  /**
   * Returns all pronunciations of a word provided by the user
   *
   * @param word The word provided by the user
   * @return all pronunciations associated with entry
   */
  public String getPronunciationEntry(String word) {
    return input.getPronunciationEntry(word);
  }

  /**
   * Returns all synonyms of a word provided by the user
   *
   * @param word The word provided by the user
   * @return all pronunciations associated with entry
   */
  public String getSynonymEntry(String word) {
    return input.getSynonymEntry(word);
  }
}

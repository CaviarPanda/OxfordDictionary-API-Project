package au.edu.sydney.soft3202.majorproject.model.database;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String dbName = "test.db";
    private static final String dbURL = "jdbc:sqlite:" + dbName;

    /**
     * Creates new Database if not created, with name test.db
     */
    public void createDB() {
        File dbFile = new File(dbName);
        if (dbFile.exists()) {
            System.out.println("Database already created");
            return;
        }
        try (Connection ignored = DriverManager.getConnection(dbURL)) {
            // If we get here that means no exception raised from getConnection - meaning it worked
            System.out.println("A new database has been created.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    /**
     * creates database table if it does not exist
     */
    public void setupDB() {
        String createNameTableSQL =
                """
                CREATE TABLE IF NOT EXISTS entryResults (
                    word text,
                    pronunciations text,
                    synonyms text,
                    data text NOT NULL
                );
                """;

        try (Connection conn = DriverManager.getConnection(dbURL);
             Statement statement = conn.createStatement()) {
            statement.execute(createNameTableSQL);
            System.out.println("Created table");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    /**
     * Adds the Entry/Lemma and its details into the database.
     *
     * @param word the word provided by the user
     * @param pronunciations the pronunciations of given word
     * @param  synonyms the synonyms of given word
     * @param data the data information of given word
     */

    public void addResultsToTable(String word, String pronunciations, String synonyms,  String data) {
        String addSingleStudentWithParametersSQL =
                """
                INSERT INTO entryResults(word, pronunciations, synonyms, data) VALUES
                    (?, ?, ?, ?)
                """;

        try (Connection conn = DriverManager.getConnection(dbURL);
             PreparedStatement preparedStatement = conn.prepareStatement(addSingleStudentWithParametersSQL)) {
            preparedStatement.setString(1, word);
            preparedStatement.setString(2, pronunciations);
            preparedStatement.setString(3, synonyms);
            preparedStatement.setString(4, data);


            preparedStatement.executeUpdate();

            System.out.println("Added Entry and/or Lemma data");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }

    }

    /**
     * Gets data from the database of a specifc word
     *
     * @param word the word provided by the user
     * @return data information of given word from table
     */
    public String getDataFromTable(String word) {
        String data = null;
        String queryGameWithName =
                String.format("""
                              SELECT data
                              FROM entryResults
                              WHERE word = ?
                        """);
        try (Connection conn = DriverManager.getConnection(dbURL);
             PreparedStatement preparedStatement = conn.prepareStatement(queryGameWithName)) {
            preparedStatement.setString(1, word);
            ResultSet results = preparedStatement.executeQuery();
            data = results.getString("data");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        return data;
    }

    /**
     * Gets Synonyms from the database of a specific word
     *
     * @param word the word provided by the user
     * @return synonym information of given word from table
     */
    public String getSynonymsFromTable(String word) {
        String data = null;
        String queryGameWithName =
                String.format("""
                              SELECT synonyms
                              FROM entryResults
                              WHERE word = ?
                        """);
        try (Connection conn = DriverManager.getConnection(dbURL);
             PreparedStatement preparedStatement = conn.prepareStatement(queryGameWithName)) {
            preparedStatement.setString(1, word);
            ResultSet results = preparedStatement.executeQuery();
            data = results.getString("synonyms");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        return data;
    }

    /**
     * Gets pronunciations from the database of a specific word
     *
     * @param word the word provided by the user
     * @return pronunciation information of given word from table
     */
    public String getPronunciationsFromTable(String word) {
        String data = null;
        String queryGameWithName =
                String.format("""
                              SELECT pronunciations
                              FROM entryResults
                              WHERE word = ?
                        """);
        try (Connection conn = DriverManager.getConnection(dbURL);
             PreparedStatement preparedStatement = conn.prepareStatement(queryGameWithName)) {
            preparedStatement.setString(1, word);
            ResultSet results = preparedStatement.executeQuery();
            data = results.getString("pronunciations");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        return data;
    }

    /**
     * Gets a list of all words present in the database
     *
     * @return list of all words found in table of database
     */
    public List<String> getWord(){
        List<String> wordsCached = new ArrayList<>();
        String queryGameWithName =
                String.format("""
                              SELECT word
                              FROM entryResults
                        """);
        try (Connection conn = DriverManager.getConnection(dbURL);
             PreparedStatement preparedStatement = conn.prepareStatement(queryGameWithName)) {
            ResultSet results = preparedStatement.executeQuery();
            while (results.next()) {
                wordsCached.add(results.getString("word"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        return wordsCached;
    }

    /**
     * Deletes all rows from the table
     */

    public void deleteData() {
        String queryGameWithName =
                String.format("""
                              DELETE FROM entryResults;
                        """);
        try (Connection conn = DriverManager.getConnection(dbURL);
             PreparedStatement preparedStatement = conn.prepareStatement(queryGameWithName)) {
            preparedStatement.executeUpdate();
            System.out.println("cache is cleared");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

}

package au.edu.sydney.soft3202.majorproject.model.api;

public interface Input {
  /**
   * GET Lemma from Oxford Dictionary API
   *
   * @param wordId The word provided by the user
   * @return The lemma details of word
   */
  String getLemma(String wordId);

  /**
   * GET Lexical Entries from lemma from Oxford Dictionary API
   *
   * @param wordId The word provided by the user
   * @return The lexical entry details of word
   */
  String getLexicalEntries(String wordId);

  /**
   * GET entry from Oxford Dictionary API
   *
   * @param wordId The word provided by the user
   * @return The entry details of word
   */
  String getEntry(String wordId);

  /**
   * GET Pronunciation from Entry from Oxford Dictionary API
   *
   * @param wordId The word provided by the user
   * @return The pronunciations from entry of word
   */
  String getPronunciationEntry(String wordId);

  /**
   * GET synonym from Entry from Oxford Dictionary API
   *
   * @param wordId The word provided by the user
   * @return The synonym entry details of word
   */
  String getSynonymEntry(String wordId);
}

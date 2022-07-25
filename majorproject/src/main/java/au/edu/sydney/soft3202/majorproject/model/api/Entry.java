package au.edu.sydney.soft3202.majorproject.model.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Entry {
  private String id;
  private Map metadata;
  private List results;

  private String word;
  private ArrayList<String> listEntries = new ArrayList<String>();
  private ArrayList<String> pronunciationsChosenList = new ArrayList<String>();

  private ArrayList<String> newWordListEntries = new ArrayList<String>();
  private ArrayList<String> synonymListEntries = new ArrayList<String>();

  public Entry(String id, Map metadata, List results) {
    this.id = id;
    this.metadata = metadata;
    this.results = results;
  }

  public Entry() {}

  /**
   * sets word to the new word provided in the parameter, given it is not null
   *
   * @param word The new word to replace the current word
   */
  public void setWord(String word) {
    if (word != null) {
      this.word = word;
    }
  }

  /**
   * Returns all metadata from a given map
   *
   * @return The list of new entries
   */
  public String metadataMapToString(Map item) {
    return "     Operation: "
        + item.get("operation")
        + "\n"
        + "     Provider: "
        + item.get("provider")
        + "\n"
        + "     Schema: "
        + item.get("schema");
  }

  /**
   * Returns all results from a given map
   *
   * @return The entry results
   */
  public String resultMapToString(Map item) {
    return "     ID: "
        + item.get("id")
        + "\n"
        + "     Language: "
        + item.get("language")
        + "\n"
        + "     Lexical Entries:\n"
        + lexicalEntriesListToString((List) item.get("lexicalEntries"))
        + "\n"
        + "     Type: "
        + item.get("type")
        + "\n"
        + "     Word: "
        + item.get("word")
        + "\n";
  }

  /**
   * Returns all synonym results from a given map
   *
   * @return The synonym results
   */
  public String synonymResultMapToString(Map item) {
    return synonymLexicalEntriesListToString((List) item.get("lexicalEntries"));
  }

  /**
   * Returns all entry results from a given map
   *
   * @return The entry results
   */
  public String entriesResultMapToString(Map item) {
    return pronunciationLexicalEntriesListToString((List) item.get("lexicalEntries"));
  }

  /**
   * Returns all derivatives from a given map
   *
   * @return The derivatives
   */
  public String derivativesMapToString(Map item) {
    return "       ID: " + item.get("id") + "\n" + "       Text: " + item.get("text");
  }

  /**
   * Returns all examples of word from a given map
   *
   * @return The examples of word
   */
  public String examplesMapToString(Map item) {
    return "             Text: " + item.get("text");
  }

  /**
   * Returns domain class from a given map
   *
   * @return The domain class
   */
  public String domainClassMapToString(Map item) {
    return "            ID: "
        + item.get("id")
        + "\n"
        + "            Text: "
        + item.get("text")
        + "\n";
  }

  /**
   * Returns all thesaurus links from a given map
   *
   * @return The thesaurus links
   */
  public String thesaurusLinkMapToString(Map item) {
    return "            Entry ID: "
        + item.get("entry_id")
        + "\n"
        + "            Sense ID: "
        + item.get("sense_id")
        + "\n";
  }

  /**
   * Returns all synonyms from a given map
   *
   * @return The synonyms
   */
  public String synonymsMapToString(Map item) {
    return "            Language: "
        + item.get("language")
        + "\n"
        + "            Text: "
        + item.get("text");
  }

  /**
   * Returns the text of a synonym from a given map
   *
   * @return The synonym text
   */
  public String textSynonymsMapToString(Map item) {
    return (String) item.get("text");
  }

  /**
   * Returns all entries from a given map
   *
   * @return The entries
   */
  public String entriesMapToString(Map item) {
    return "       Etymologies: "
        + etymologiesListToString((List) item.get("etymologies"))
        + "\n"
        + "       Pronunciations:\n"
        + pronunciationsListToString((List) item.get("pronunciations"))
        + "\n"
        + "       Senses:\n"
        + sensesListToString((List) item.get("senses"));
  }

  /**
   * Returns all synonym entries from a given map
   *
   * @return The synonym entries
   */
  public String synonymEntriesMapToString(Map item) {
    return synonymSensesListToString((List) item.get("senses"));
  }

  /**
   * Returns all pronunciations entries from a given map
   *
   * @return The pronunciations entries
   */
  public String pronunciationEntriesMapToString(Map item) {
    return audioPronunciationsListToString((List) item.get("pronunciations"));
  }

  /**
   * Returns all pronunciation results from a given map
   *
   * @return The pronunciation results
   */
  public String pronunciationsMapToString(Map item) {
    return "          Audio File: "
        + item.get("audioFile")
        + "\n"
        + "          Dialects: "
        + dialectsListToString((List) item.get("dialects"))
        + "\n"
        + "          Phonetic Notation: "
        + item.get("phoneticNotation")
        + "\n"
        + "          Phonetic Spelling: "
        + item.get("phoneticSpelling")
        + "\n";
  }

  /**
   * Returns all audio pronunciations from a given map
   *
   * @return The audio Pronunciations
   */
  public String audioPronunciationsMapToString(Map item) {
    return (String) item.get("audioFile");
  }

  /**
   * Returns all senses from a given map
   *
   * @return The senses of a word
   */
  public String sensesMapToString(Map item) {
    return "          Definitions: "
        + definitionsListToString((List) item.get("definitions"))
        + "\n"
        + "          Domain Classes:\n"
        + domainClassListToString((List) item.get("domainClasses"))
        + "\n"
        + "          Examples:\n"
        + examplesListToString((List) item.get("examples"))
        + "\n"
        + "          ID: "
        + item.get("id")
        + "\n"
        + "          Semantic Classes: \n"
        + domainClassListToString((List) item.get("semanticClasses"))
        + "\n"
        + "          Short Definitions: "
        + etymologiesListToString((List) item.get("shortDefinitions"))
        + "\n"
        + "          Synonyms: \n"
        + synonymsListToString((List) item.get("synonyms"))
        + "\n"
        + "          Thesaurus Links:\n"
        + thesaurusLinksListToString((List) item.get("thesaurusLinks"));
  }

  /**
   * Returns all synonym senses from a given map
   *
   * @return The synonym senses
   */
  public String synonymSensesMapToString(Map item) {
    return textSynonymsListToString((List) item.get("synonyms"));
  }

  /**
   * Returns all derivatives results from a given list
   *
   * @return The derivatives
   */
  public String derivativesListToString(List derivatives) {
    if (derivatives == null) {
      return "            None";
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < derivatives.size(); i++) {
      sb.append(derivativesMapToString((Map) derivatives.get(i)) + "\n");
    }
    return sb.toString();
  }

  /**
   * Returns domain class results from a given list
   *
   * @return The domain class, returns "None" if domain class list is null
   */
  public String domainClassListToString(List domainClass) {
    if (domainClass == null) {
      return "            None";
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < domainClass.size(); i++) {
      sb.append(domainClassMapToString((Map) domainClass.get(i)));
    }
    return sb.toString();
  }

  /**
   * Returns all synonym results from a list
   *
   * @return The synonyms
   */
  public String synonymsListToString(List synonyms) {
    if (synonyms == null) {
      return "            None";
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < synonyms.size(); i++) {
      sb.append("         " + i + ")\n" + synonymsMapToString((Map) synonyms.get(i)) + "\n");
    }
    return sb.toString();
  }

  /**
   * Returns all text from synonym from a given list
   *
   * @return The synonym texts
   */
  public String textSynonymsListToString(List synonyms) {
    if (synonyms == null) {
      return "None, ";
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < synonyms.size(); i++) {
      sb.append(textSynonymsMapToString((Map) synonyms.get(i)) + ", ");
    }
    return sb.toString();
  }

  /**
   * Returns all thesaurus links from a given list
   *
   * @return The thesaurus links
   */
  public String thesaurusLinksListToString(List thesaurusLinks) {
    if (thesaurusLinks == null) {
      return "            None";
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < thesaurusLinks.size(); i++) {
      sb.append(thesaurusLinkMapToString((Map) thesaurusLinks.get(i)));
    }
    return sb.toString();
  }

  /**
   * Returns all examples from a given list
   *
   * @return The examples. Otherwise return "None" if examples list is null
   */
  public String examplesListToString(List examples) {
    if (examples == null) {
      return "            None";
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < examples.size(); i++) {
      sb.append(examplesMapToString((Map) examples.get(i)) + "\n");
    }
    return sb.toString();
  }

  /**
   * Returns all etymologies from a given list
   *
   * @return The etymologies. Otherwise, return "None" if null
   */
  public String etymologiesListToString(List etymologies) {
    if (etymologies == null) {
      return "None";
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < etymologies.size(); i++) {
      sb.append(etymologies.get(i));
    }
    return sb.toString();
  }

  /**
   * Returns all definitions from a given list
   *
   * @return The definitons. Otherwise, return "No definitions" if null
   */
  public String definitionsListToString(List definitions) {
    if (definitions == null) {
      return "No definitions";
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < definitions.size(); i++) {
      sb.append(definitions.get(i));
    }
    return sb.toString();
  }

  /**
   * Returns all dialects from a given list
   *
   * @return The dialects. Otherwise, return "No dialects" if null
   */
  public String dialectsListToString(List dialects) {
    if (dialects == null) {
      return "No dialects";
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < dialects.size(); i++) {
      sb.append(dialects.get(i));
    }
    return sb.toString();
  }

  /**
   * Returns all pronunciations from a given list
   *
   * @return The pronunciations. Otherwise, return "None" if null
   */
  public String pronunciationsListToString(List pronunciations) {
    if (pronunciations == null) {
      return "None";
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < pronunciations.size(); i++) {
      sb.append(pronunciationsMapToString((Map) pronunciations.get(i)));
    }
    return sb.toString();
  }

  /**
   * Returns all audio pronunciations from a given list
   *
   * @return The audio pronunciations. Otherwise, return "None" if null
   */
  public String audioPronunciationsListToString(List pronunciations) {
    if (pronunciations == null) {
      return "None, ";
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < pronunciations.size(); i++) {
      sb.append(audioPronunciationsMapToString((Map) pronunciations.get(i)) + ", ");
    }
    return sb.toString();
  }

  /**
   * Returns all senses from a given list
   *
   * @return The senses.
   */
  public String sensesListToString(List senses) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < senses.size(); i++) {
      sb.append(sensesMapToString((Map) senses.get(i)) + "\n");
    }
    return sb.toString();
  }

  /**
   * Returns all synonym senses from a given list
   *
   * @return The synonym senses.
   */
  public String synonymSensesListToString(List senses) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < senses.size(); i++) {
      sb.append(synonymSensesMapToString((Map) senses.get(i)));
    }
    return sb.toString();
  }

  public String entriesListToString(List entries) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < entries.size(); i++) {
      sb.append(entriesMapToString((Map) entries.get(i)) + "\n");
    }
    return sb.toString();
  }

  public String synonymEntriesListToString(List entries) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < entries.size(); i++) {
      sb.append(synonymEntriesMapToString((Map) entries.get(i)));
    }
    return sb.toString();
  }

  public String pronunciationEntriesListToString(List entries) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < entries.size(); i++) {
      sb.append(pronunciationEntriesMapToString((Map) entries.get(i)) + ", ");
    }
    return sb.toString();
  }

  public String lexicalEntriesMapToString(Map item) {
    return "     Derivatives:\n"
        + derivativesListToString((List) item.get("derivatives"))
        + "\n"
        + "     Entries: \n"
        + entriesListToString((List) item.get("entries"))
        + "\n"
        + "     Language: "
        + item.get("language")
        + "\n"
        + "     Lexical Category:\n"
        + derivativesMapToString((Map) item.get("lexicalCategory"))
        + "\n"
        + "     Text: "
        + item.get("text");
  }

  public String synonymLexicalEntriesMapToString(Map item) {
    return synonymEntriesListToString((List) item.get("entries"));
  }

  public String entriesOnlyMapToString(Map item) {
    return pronunciationEntriesListToString((List) item.get("entries"));
  }

  public String lexicalEntriesListToString(List entries) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < entries.size(); i++) {
      sb.append(lexicalEntriesMapToString((Map) entries.get(i)) + "\n");
    }
    return sb.toString();
  }

  public String synonymLexicalEntriesListToString(List entries) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < entries.size(); i++) {
      sb.append(synonymLexicalEntriesMapToString((Map) entries.get(i)));
    }
    return sb.toString();
  }

  public String pronunciationLexicalEntriesListToString(List entries) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < entries.size(); i++) {
      sb.append(entriesOnlyMapToString((Map) entries.get(i)) + ", ");
    }
    return sb.toString();
  }

  public String resultListToString(List result) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < result.size(); i++) {
      sb.append(resultMapToString((Map) result.get(i)));
    }
    return sb.toString();
  }

  public String synonymResultListToString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < results.size(); i++) {
      sb.append(synonymResultMapToString((Map) results.get(i)));
    }
    return sb.toString();
  }

  public String audioPronunciationListToString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < results.size(); i++) {
      sb.append(entriesResultMapToString((Map) results.get(i)) + ", ");
    }
    String audio = sb.toString();
    List<String> myList = new ArrayList<String>(Arrays.asList(audio.split(", ")));
    List<String> newList = new ArrayList<>();
    for (int i = 0; i < myList.size(); i++) {
      if (!myList.get(i).equals(" ") && !newList.contains(myList.get(i))) {
        newList.add(myList.get(i));
      }
    }
    String pronunciations = newList.toString();
    pronunciations = pronunciations.replaceAll("\\[", "");
    pronunciations = pronunciations.replaceAll("\\]", "");
    return pronunciations;
  }

  @Override
  public String toString() {
    return "Entry: \n"
        + "  ID: "
        + id
        + "\n"
        + "  Metadata:\n"
        + metadataMapToString(metadata)
        + "\n"
        + "  Results:\n"
        + resultListToString(results);
  }
}

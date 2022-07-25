package au.edu.sydney.soft3202.majorproject.model.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Lemma {
  private Map metadata;
  private List results;

  public Lemma(Map metadata, List results) {
    this.metadata = metadata;
    this.results = results;
  }

  public String metaDataMapToString(Map item) {
    return "Provider: " + item.get("provider");
  }

  public String inflectionsOfMapToString(Map item) {
    return "      ID: " + item.get("id") + "\n" + "      Text: " + item.get("text");
  }

  public String inflectionsOfTextMapToString(Map item) {
    return (String) item.get("text");
  }

  public String lexicalCategoryMapToString(Map item) {
    return "      ID: " + item.get("id") + "\n" + "      Text: " + item.get("text");
  }

  public String grammaticalFeaturesMapToString(Map item) {
    return "      ID: "
        + item.get("id")
        + "\n"
        + "      Text: "
        + item.get("text")
        + "\n"
        + "      Type: "
        + item.get("type");
  }

  public String lexicalEntriestMapToString(Map item) {
    return grammaticalFeaturesListToString((List) item.get("grammaticalFeatures"))
        + "\n"
        + "   Inflection Of: \n"
        + inflectionsOfListToString((List) item.get("inflectionOf"))
        + "\n"
        + "   Language: "
        + item.get("language")
        + "\n"
        + "   Lexical Category \n"
        + lexicalCategoryMapToString((Map) item.get("lexicalCategory"))
        + "\n"
        + "   Text: "
        + item.get("text");
  }

  public String lexicalEntriesTextMapToString(Map item) {
    return inflectionsOfTextListToString((List) item.get("inflectionOf"));
  }

  public String resultMapToString(Map item) {
    return " ID: "
        + item.get("id")
        + "\n"
        + " Language: "
        + item.get("language")
        + "\n"
        + " Lexical Entries: \n"
        + lexicalEntriesListToString((List) item.get("lexicalEntries"))
        + "\n"
        + " Word: "
        + item.get("word");
  }

  public String inflectionsOfListToString(List inflections) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < inflections.size(); i++) {
      sb.append(inflectionsOfMapToString((Map) inflections.get(i)));
    }
    return sb.toString();
  }

  public String inflectionsOfTextListToString(List inflections) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < inflections.size(); i++) {
      sb.append(inflectionsOfTextMapToString((Map) inflections.get(i)));
    }
    return sb.toString();
  }

  public String grammaticalFeaturesListToString(List features) {
    if (features == null) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    sb.append("   Grammatical Features: \n");
    for (int i = 0; i < features.size(); i++) {
      sb.append(grammaticalFeaturesMapToString((Map) features.get(i)));
    }
    return sb.toString();
  }

  public String lexicalEntriesListToString(List entries) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < entries.size(); i++) {
      sb.append(lexicalEntriestMapToString((Map) entries.get(i)) + "\n");
    }
    return sb.toString();
  }

  public String lexicalEntriesTextListToString(List entries) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < entries.size(); i++) {
      sb.append(lexicalEntriesTextMapToString((Map) entries.get(i)) + ", ");
    }
    return sb.toString();
  }

  public String resultsToString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < results.size(); i++) {
      sb.append(resultMapToString((Map) results.get(i)) + "\n");
    }
    return sb.toString();
  }

  public List<String> lexicalEntries() {
    List<String> entries = new ArrayList<>();
    for (int i = 0; i < results.size(); i++) {
      Map item = (Map) results.get(i);
      String entry = lexicalEntriesTextListToString((List) item.get("lexicalEntries"));
      List<String> myList = Arrays.asList(entry.split(", "));
      for (int j = 0; j < myList.size(); j++) {
        if (!entries.contains(myList.get(i))) {
          entries.add(myList.get(i));
        }
      }
    }
    return entries;
  }

  @Override
  public String toString() {
    return "Metadata: " + metaDataMapToString(metadata) + "\n" + "Results: \n" + resultsToString();
  }
}

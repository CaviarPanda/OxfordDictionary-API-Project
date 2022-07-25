package au.edu.sydney.soft3202.majorproject.model.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OfflineInput implements Input {

  @Override
  public String getLemma(String wordId) {
    Map<Object, Object> metadata = new HashMap<>();
    metadata.put("provider", " Oxford University Press");
    List results = new ArrayList<>();

    Map grammaticalFeaturesMap = new HashMap<>();
    grammaticalFeaturesMap.put("id", "345");
    grammaticalFeaturesMap.put("text", wordId);
    grammaticalFeaturesMap.put("type", "adjective");

    List grammaticalFeaturesList = new ArrayList();
    grammaticalFeaturesList.add(grammaticalFeaturesMap);

    Map lexicalCategoryMap = new HashMap<>();
    lexicalCategoryMap.put("id", "345");
    lexicalCategoryMap.put("text", wordId);

    List lexicalCategoryList = new ArrayList();
    lexicalCategoryList.add(lexicalCategoryMap);

    Map lexicalEntriesMap = new HashMap<>();
    lexicalEntriesMap.put("grammaticalFeatures", grammaticalFeaturesList);
    lexicalEntriesMap.put("inflectionOf", lexicalCategoryList);
    lexicalEntriesMap.put("language", "En");
    lexicalEntriesMap.put("lexicalCategory", lexicalCategoryMap);
    lexicalEntriesMap.put("text", wordId);

    List lexicalEntriesList = new ArrayList();
    lexicalEntriesList.add(lexicalEntriesMap);

    Map resultLemma = new HashMap<>();
    resultLemma.put("id", "345");
    resultLemma.put("language", "En");
    resultLemma.put("lexicalEntries", lexicalEntriesList);
    resultLemma.put("word", wordId);

    results.add(resultLemma);

    Lemma lemma = new Lemma(metadata, results);
    System.out.println(lemma);
    return lemma.toString();
  }

  @Override
  public String getLexicalEntries(String wordId) {
    Map<Object, Object> metadata = new HashMap<>();
    metadata.put("provider", " Oxford University Press");
    List results = new ArrayList<>();

    Map grammaticalFeaturesMap = new HashMap<>();
    grammaticalFeaturesMap.put("id", "345");
    grammaticalFeaturesMap.put("text", wordId);
    grammaticalFeaturesMap.put("type", "adjective");

    List grammaticalFeaturesList = new ArrayList();
    grammaticalFeaturesList.add(grammaticalFeaturesMap);

    Map lexicalCategoryMap = new HashMap<>();
    lexicalCategoryMap.put("id", "345");
    lexicalCategoryMap.put("text", wordId);

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
    lexicalEntriesMap.put("text", wordId);

    List lexicalEntriesList = new ArrayList();
    lexicalEntriesList.add(lexicalEntriesMap);

    Map resultLemma = new HashMap<>();
    resultLemma.put("id", "345");
    resultLemma.put("language", "En");
    resultLemma.put("lexicalEntries", lexicalEntriesList);
    resultLemma.put("word", wordId);

    results.add(resultLemma);

    Lemma lemma = new Lemma(metadata, results);
    System.out.println(lemma.lexicalEntries());
    return lemma.lexicalEntries().toString();
  }

  @Override
  public String getEntry(String wordId) {

    String id = "39";
    Map metadata = new HashMap();

    metadata.put("operation", "retrieve");
    metadata.put("provider", "Oxford University Press");
    metadata.put("schema", "retrieveEntry");

    List results = new ArrayList();

    Map derivativesMap = new HashMap();
    derivativesMap.put("id", "342");
    derivativesMap.put("text", wordId);

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
    domainClassMap.put("text", wordId);

    List domainClassList = new ArrayList();
    domainClassList.add(domainClassMap);

    Map examplesMap = new HashMap();
    examplesMap.put("text", wordId);

    List examplesList = new ArrayList();
    examplesList.add(examplesMap);

    Map thesaurusLinkMap = new HashMap();
    thesaurusLinkMap.put("entry_id", wordId);
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
    lexicalEntriesMap.put("text", wordId);

    List lexicalEntriesList = new ArrayList();
    lexicalEntriesList.add(lexicalEntriesMap);

    Map resultsMap = new HashMap();
    resultsMap.put("id", "434");
    resultsMap.put("language", "en-gb");
    resultsMap.put("lexicalEntries", lexicalEntriesList);
    resultsMap.put("type", "noun");
    resultsMap.put("word", "434");

    results.add(resultsMap);

    Entry entry = new Entry(id, metadata, results);
    System.out.println(entry);
    return entry.toString();
  }

  @Override
  public String getPronunciationEntry(String wordId) {
    String id = "39";
    Map metadata = new HashMap();

    metadata.put("operation", "retrieve");
    metadata.put("provider", "Oxford University Press");
    metadata.put("schema", "retrieveEntry");

    List results = new ArrayList();

    Map derivativesMap = new HashMap();
    derivativesMap.put("id", "342");
    derivativesMap.put("text", wordId);

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
    domainClassMap.put("text", wordId);

    List domainClassList = new ArrayList();
    domainClassList.add(domainClassMap);

    Map examplesMap = new HashMap();
    examplesMap.put("text", wordId);

    List examplesList = new ArrayList();
    examplesList.add(examplesMap);

    Map thesaurusLinkMap = new HashMap();
    thesaurusLinkMap.put("entry_id", wordId);
    thesaurusLinkMap.put("sense_id", "t_en_gb0000173.002");

    List thesaurusLinkMapList = new ArrayList();
    thesaurusLinkMapList.add(thesaurusLinkMap);

    Map synonymsMap = new HashMap();
    synonymsMap.put("language", "english");
    synonymsMap.put("text", "tired");

    List synonymsList = new ArrayList();
    synonymsList.add(synonymsMap);

    Map sensesMap = new HashMap();
    sensesMap.put("definitions", definitionsList);
    sensesMap.put("domainClasses", domainClassList);
    sensesMap.put("examples", examplesList);
    sensesMap.put("id", "58");
    sensesMap.put("semanticClasses", domainClassList);
    sensesMap.put("shortDefinitions", etymologiesList);
    sensesMap.put("synonyms", synonymsList);

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
    lexicalEntriesMap.put("text", wordId);

    List lexicalEntriesList = new ArrayList();
    lexicalEntriesList.add(lexicalEntriesMap);

    Map resultsMap = new HashMap();
    resultsMap.put("id", "434");
    resultsMap.put("language", "en-gb");
    resultsMap.put("lexicalEntries", lexicalEntriesList);
    resultsMap.put("type", "noun");
    resultsMap.put("word", wordId);

    results.add(resultsMap);

    Entry entry = new Entry(id, metadata, results);
    System.out.println(entry.audioPronunciationListToString());
    return entry.audioPronunciationListToString();
  }

  @Override
  public String getSynonymEntry(String wordId) {
    String id = "39";
    Map metadata = new HashMap();

    metadata.put("operation", "retrieve");
    metadata.put("provider", "Oxford University Press");
    metadata.put("schema", "retrieveEntry");

    List results = new ArrayList();

    Map derivativesMap = new HashMap();
    derivativesMap.put("id", "342");
    derivativesMap.put("text", wordId);

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
    domainClassMap.put("text", wordId);

    List domainClassList = new ArrayList();
    domainClassList.add(domainClassMap);

    Map examplesMap = new HashMap();
    examplesMap.put("text", wordId);

    List examplesList = new ArrayList();
    examplesList.add(examplesMap);

    Map thesaurusLinkMap = new HashMap();
    thesaurusLinkMap.put("entry_id", "sleepy");
    thesaurusLinkMap.put("sense_id", "t_en_gb0000173.002");

    List thesaurusLinkMapList = new ArrayList();
    thesaurusLinkMapList.add(thesaurusLinkMap);

    Map synonymsMap = new HashMap();
    synonymsMap.put("language", "english");
    synonymsMap.put("text", "tired");

    List synonymsList = new ArrayList();
    synonymsList.add(synonymsMap);

    Map sensesMap = new HashMap();
    sensesMap.put("definitions", definitionsList);
    sensesMap.put("domainClasses", domainClassList);
    sensesMap.put("examples", examplesList);
    sensesMap.put("id", "58");
    sensesMap.put("semanticClasses", domainClassList);
    sensesMap.put("shortDefinitions", etymologiesList);
    sensesMap.put("synonyms", synonymsList);

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
    lexicalEntriesMap.put("text", wordId);

    List lexicalEntriesList = new ArrayList();
    lexicalEntriesList.add(lexicalEntriesMap);

    Map resultsMap = new HashMap();
    resultsMap.put("id", "434");
    resultsMap.put("language", "en-gb");
    resultsMap.put("lexicalEntries", lexicalEntriesList);
    resultsMap.put("type", "noun");
    resultsMap.put("word", "434");

    results.add(resultsMap);

    Entry entry = new Entry(id, metadata, results);
    System.out.println(entry.synonymResultListToString());
    return entry.synonymResultListToString();
  }
}

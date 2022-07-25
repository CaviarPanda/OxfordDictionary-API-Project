import au.edu.sydney.soft3202.majorproject.model.api.Entry;
import au.edu.sydney.soft3202.majorproject.model.api.modifylist.EntriesListStrategy;
import au.edu.sydney.soft3202.majorproject.model.api.modifylist.ModifyListStrategy;
import au.edu.sydney.soft3202.majorproject.model.api.modifylist.PronunciationListStrategy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class PronunciationListStrategyTest {
  @Test
  public void testAddPronunciation() {
    ModifyListStrategy entry = new PronunciationListStrategy();
    String pronunciation = "https://audio.oxforddictionaries.com/en/mp3/hello_gb_1.mp3";
    entry.addItemToList(pronunciation);

    List<String> expectedPronunciations = new ArrayList<>();
    expectedPronunciations.add(pronunciation);

    assertEquals(expectedPronunciations, entry.getList());
  }

  @Test
  public void testRemovePronunciation() {
    ModifyListStrategy entry = new PronunciationListStrategy();

    String pronunciationOne = "https://audio.oxforddictionaries.com/en/mp3/hello_gb_1.mp3";
    String pronunciationTwo = "https://audio.oxforddictionaries.com/en/mp3/hello_gb_2.mp3";
    entry.addItemToList(pronunciationOne);
    entry.addItemToList(pronunciationTwo);

    entry.removeItemFromList(pronunciationOne);

    assertThat(entry.getList(), not(hasItem(pronunciationOne)));
  }

  @Test
  public void testAddExistingPronunciation() {
    ModifyListStrategy entry = new PronunciationListStrategy();
    String pronunciation = "https://audio.oxforddictionaries.com/en/mp3/hello_gb_1.mp3";

    entry.addItemToList(pronunciation);
    entry.addItemToList(pronunciation);

    List<String> expectedPronunciations = new ArrayList<>();
    expectedPronunciations.add(pronunciation);

    assertEquals(expectedPronunciations, entry.getList());
  }

  @Test
  public void testAddNullPronunciation() {
    ModifyListStrategy entry = new PronunciationListStrategy();
    String pronunciation = "https://audio.oxforddictionaries.com/en/mp3/hello_gb_1.mp3";

    entry.addItemToList(null);
    entry.addItemToList(pronunciation);

    List<String> expectedPronunciations = new ArrayList<>();
    expectedPronunciations.add(pronunciation);

    assertEquals(expectedPronunciations, entry.getList());
  }

  @Test
  public void testRemoveNonExistingPronunciation() {
    ModifyListStrategy entry = new PronunciationListStrategy();

    String pronunciation = "https://audio.oxforddictionaries.com/en/mp3/hello_gb_1.mp3";

    entry.removeItemFromList(pronunciation);
    List<String> expectedPronunciations = new ArrayList<>();

    assertEquals(expectedPronunciations, entry.getList());
  }
}

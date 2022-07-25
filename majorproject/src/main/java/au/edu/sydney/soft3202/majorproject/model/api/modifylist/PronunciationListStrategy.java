package au.edu.sydney.soft3202.majorproject.model.api.modifylist;

import java.util.ArrayList;
import java.util.List;

public class PronunciationListStrategy implements ModifyListStrategy {
  private ArrayList<String> pronunciationsChosenList = new ArrayList<String>();

  @Override
  public void addItemToList(String pronunciation) {
    if (!pronunciationsChosenList.contains(pronunciation) && pronunciation != null) {
      pronunciationsChosenList.add(pronunciation);
    }
    System.out.println(pronunciationsChosenList);
  }

  public void removeItemFromList(String pronunciation) {
    if (pronunciationsChosenList.contains(pronunciation)) {
      pronunciationsChosenList.remove(pronunciation);
    }
    System.out.println(pronunciationsChosenList);
  }

  @Override
  public List<String> getList() {
    return pronunciationsChosenList;
  }
}

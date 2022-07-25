package au.edu.sydney.soft3202.majorproject.model.api.modifylist;

import java.util.ArrayList;
import java.util.List;

public class EntriesListStrategy implements ModifyListStrategy {
  private ArrayList<String> listEntries = new ArrayList<>();

  @Override
  public void addItemToList(String entry) {
    if (!listEntries.contains(entry.toLowerCase())) {
      listEntries.add(entry.toLowerCase());
    }
    System.out.println(listEntries);
  }

  @Override
  public void removeItemFromList(String item) {}

  @Override
  public List<String> getList() {
    return listEntries;
  }
}

package au.edu.sydney.soft3202.majorproject.model.api.modifylist;

import java.util.List;

public interface ModifyListStrategy {
    /**
     * Adds an item to a list of items given that the item does not exist in
     * the list beforehand
     *
     * @param item the selected item
     */
    public void addItemToList(String item);

    /**
     * Removes an item from list of pronunciations given that the item exists in the list
     *
     * @param item the selected item
     */

    public void removeItemFromList(String item);
    /**
     * gets the list of items
     *
     * @return The list of items
     */
    public List<String> getList();

}


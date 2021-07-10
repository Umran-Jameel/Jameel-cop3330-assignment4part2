/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Umran Jameel
 */
package ucf.assignments;

import java.util.ArrayList;

public class ListEditor {
    public void addToList(ToDoList list, String name) {
        Item item = new Item(); // new item being instantiated
        item.itemName = name; // setting the itemName
        list.list.add(item); // add the item
    }

    public void removeFromList(ToDoList list, String toBeRemoved) {
        // loop to find the item to be removed in the list
        for (int i = 0; i < list.list.size(); i++) {
            // if the item is found
            if (toBeRemoved.equals(list.list.get(i).itemName)) {
                list.list.remove(i); // remove the item
                break;
            }
        }
    }

    public void editDescription(ToDoList list, String selected, String edited) {
        // loop to find the item to be edited in the list
        for (int i = 0; i < list.list.size(); i++) {
            // if the item is found
            if (selected.equals(list.list.get(i).itemName)) {
                list.list.get(i).description = edited; // edit the item
                break;
            }
        }
    }

    public void editDueDate(ToDoList list) {
        // prompt the user for the item name
        // loop though and find the item in the list's ArrayList for names
        // prompt the user for the day, month, and year
        // replace the date information with the new info at that index
    }

    public void markAsComplete(ToDoList list) {
        // prompt the user for the item name
        // loop though and find the item in the list's ArrayList for names

        // mark the status at that index as true
    }

    public void clearList(ToDoList list) {

    }
}

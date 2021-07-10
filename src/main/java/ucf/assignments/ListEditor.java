/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Umran Jameel
 */
package ucf.assignments;

public class ListEditor {
    public void addToList(ToDoList list, String name) {
        Item item = new Item(); // making a new item
        item.itemName = name; // item name is what the user entered
    }

    public void removeFromList(ToDoList list) {
        // prompt the user for the name of the item to be removed
        // loop the though the Array List of item names to find the item to be removed
        // remove the name of the item, and remove the description, status, and due date from that index
    }

    public void editDescription(ToDoList list) {
        // prompt the user for the item name
        // prompt the user for the new description

        // loop through the list and find the item
        // replace the description at that index with the new one
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

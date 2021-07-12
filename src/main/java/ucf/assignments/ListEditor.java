/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Umran Jameel
 */
package ucf.assignments;

import java.time.LocalDate;
import java.util.ArrayList;

public class ListEditor {
    public void addToList(ToDoList toDoList, String name) {
        Item item = new Item(); // new item being instantiated
        item.itemName = name; // setting the itemName
        toDoList.list.add(item); // add the item
    }

    public void removeFromList(ToDoList toDoList, String toBeRemoved) {
        // loop to find the item to be removed in the list
        for (int i = 0; i < toDoList.list.size(); i++) {
            // if the item is found
            if (toBeRemoved.equals(toDoList.list.get(i).itemName)) {
                toDoList.list.remove(i); // remove the item
                break;
            }
        }
    }

    public void editDescription(ToDoList toDoList, String selected, String edited) {
        // loop to find the item to be edited in the list
        for (int i = 0; i < toDoList.list.size(); i++) {
            // if the item is found
            if (selected.equals(toDoList.list.get(i).itemName)) {
                toDoList.list.get(i).description = edited; // edit the item
                break;
            }
        }
    }

    public void editDueDate(ToDoList toDoList, LocalDate dueDate, String itemName) {
        int index = findIndex(toDoList, itemName); // finding the index of the item

        toDoList.list.get(index).dueDate = dueDate; // set the due date to be the one the user entered
    }

    public void markAsComplete(ToDoList toDoList, int index) {
        // index was already found in controller class function
        // use index and set the item to completed
        toDoList.list.get(index).status = true;

    }

    public void markAsInComplete(ToDoList toDoList, int index) {
        // index was already found in controller class function
        // use index and set the item to incomplete
        toDoList.list.get(index).status = false;
    }

    public void clearList(ToDoList toDoList) {
        toDoList.list.clear(); // clear the list
    }

    public int findIndex(ToDoList toDoList, String toFind) {
        int ret;
        // loop through to find the item's index
        for (ret = 0; ret < toDoList.list.size(); ret++) {
            // if the item is found (strings equal each other)
            if (toFind.equals(toDoList.list.get(ret).itemName)) {
                return ret;
            }
        }
        return 0;
    }
}

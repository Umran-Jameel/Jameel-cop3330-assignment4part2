/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Umran Jameel
 */
package ucf.assignments;

import java.util.ArrayList;

public class Display {
    public ArrayList<String> displayCompleted(ToDoList list) {
        ArrayList<String> ret = new ArrayList<>();

        // loop through the items
        for (int i = 0; i < list.list.size(); i++) {
            // if the item is completed
            if (list.list.get(i).status) {
                ret.add(list.list.get(i).itemName); // add the item to the array list to be returned
            }
        }

        return ret;
    }

    public ArrayList<String> displayIncomplete(ToDoList list) {
        ArrayList<String> ret = new ArrayList<>();

        // loop through the items
        for (int i = 0; i < list.list.size(); i++) {
            // if the item is completed
            if (!list.list.get(i).status) {
                ret.add(list.list.get(i).itemName); // add the item to the array list to be returned
            }
        }

        return ret;
    }
}

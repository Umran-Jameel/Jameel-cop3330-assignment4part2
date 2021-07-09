/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Umran Jameel
 */
package ucf.assignments;

import java.util.ArrayList;

public class ToDoList {
    // Name of item, completed or not, item description
    ArrayList<String> itemName = new ArrayList<String>();
    ArrayList<Boolean> status = new ArrayList<Boolean>();
    ArrayList<String> description = new ArrayList<String>();

    // due date
    ArrayList<Integer> year = new ArrayList<Integer>();
    ArrayList<Integer> month = new ArrayList<Integer>();
    ArrayList<Integer> day = new ArrayList<Integer>();

    ListEditor le = new ListEditor();
}

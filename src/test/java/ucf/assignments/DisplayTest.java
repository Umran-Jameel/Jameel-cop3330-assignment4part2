/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Umran Jameel
 */
package ucf.assignments;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DisplayTest {
    ToDoList list = new ToDoList();
    Display display = new Display();

    @Test
    void displayCompleted() {
        // make 4 different items to be tested
        Item item1 = new Item();
        item1.itemName = "test1";
        item1.status = true; // this one will have the status complete
        list.list.add(item1);

        Item item2 = new Item();
        item2.itemName = "test2";
        item2.status = false; // this one will have the status incomplete
        list.list.add(item2);

        Item item3 = new Item();
        item3.itemName = "test3";
        item3.status = false; // this one will have the status incomplete
        list.list.add(item3);

        Item item4 = new Item();
        item4.itemName = "test4";
        item4.status = true; // this one will have the status complete
        list.list.add(item4);

        ArrayList<String> expected = new ArrayList<>();

        // these items should be in the returned array list
        expected.add("test1");
        expected.add("test4");

        assertEquals(expected, display.displayCompleted(list));
    }

    @Test
    void displayIncomplete() {
        // make 4 different items to be tested
        Item item1 = new Item();
        item1.itemName = "test1";
        item1.status = true; // this one will have the status complete
        list.list.add(item1);

        Item item2 = new Item();
        item2.itemName = "test2";
        item2.status = false; // this one will have the status incomplete
        list.list.add(item2);

        Item item3 = new Item();
        item3.itemName = "test3";
        item3.status = false; // this one will have the status incomplete
        list.list.add(item3);

        Item item4 = new Item();
        item4.itemName = "test4";
        item4.status = true; // this one will have the status complete
        list.list.add(item4);

        ArrayList<String> expected = new ArrayList<>();

        // these items should be in the returned array list
        expected.add("test2");
        expected.add("test3");

        assertEquals(expected, display.displayIncomplete(list));
    }
}
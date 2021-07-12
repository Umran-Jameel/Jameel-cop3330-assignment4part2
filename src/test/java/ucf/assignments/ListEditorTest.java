/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Umran Jameel
 */
package ucf.assignments;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class ListEditorTest {
    ListEditor listEditor = new ListEditor();
    ToDoList toDoList = new ToDoList();

    @Test
    void addToList() {
        Item test = new Item(); // instantiating a new item for test
        listEditor.addToList(toDoList, "test"); // add to list a test item
        assertEquals("test", toDoList.list.get(0).itemName); // check to see if it has been added properly
    }

    @Test
    void removeFromList() {
        ArrayList<Item> test = new ArrayList<Item>(); // empty array list for comparison

        Item toAdd = new Item();
        toAdd.itemName = "toAdd";

        toDoList.list.add(toAdd); // adding an item to remove it

        listEditor.removeFromList(toDoList, "toAdd"); // remove the item

        assertEquals(test, toDoList.list);
    }

    @Test
    void editDescription() {
        Item test = new Item(); // new item for test
        test.description = "test";
        toDoList.list.add(test); // add the item to a list
        listEditor.editDescription(toDoList, "test", "test"); // set the item's description as "test"

        assertEquals("test", test.description); // check if item's description is as expected after method is called

    }

    @Test
    void editDueDate() {
        // new item for test with name "item"
        Item item = new Item();
        item.itemName = "item";

        LocalDate date = LocalDate.of(2021, 7, 11); // setting the date for comparison
        LocalDate expected =  LocalDate.of(2021, 7, 11);

        toDoList.list.add(item); // add the item to the list

        listEditor.editDueDate(toDoList, date, "item"); // change the due date of the item

        assertEquals(expected, item.dueDate); // check if the due date of the item was set

    }

    @Test
    void markAsComplete() {
        // making a new item with the status incomplete, index 0
        Item item = new Item();
        item.status = false;

        toDoList.list.add(item); // add the item to the list
        listEditor.markAsComplete(toDoList, 0); // mark the item as complete using the function

        assertEquals(true, toDoList.list.get(0).status); // check if the new status is true as expected
    }

    @Test
    void markAsIncomplete() {
        // making a new item with the status completed, index 0
        Item item = new Item();
        item.status = true;

        toDoList.list.add(item); // add the item to the list
        listEditor.markAsInComplete(toDoList, 0); // mark the item as complete using the function

        assertEquals(false, toDoList.list.get(0).status); // check if the new status is true as expected
    }

    @Test
    void clearList() {
        // adding an item to be cleared
        Item test1 = new Item();
        toDoList.list.add(test1);

        // adding a second item to be cleared
        Item test2 = new Item();
        toDoList.list.add(test2);

        listEditor.clearList(toDoList); // clear the list

        assertEquals(0, toDoList.list.size()); // size of the list should be zero
    }

    @Test
    void findIndex() {
        // new item with name no name (not the one to find)
        Item item = new Item();
        item.itemName = "no name";

        // new item with name findIndex
        Item item2 = new Item();
        item2.itemName = "findIndex";

        toDoList.list.add(item); // add the item, will be index 0
        toDoList.list.add(item2); // add the item2, will be index 1 (to be found)

        int actual = listEditor.findIndex(toDoList, "findIndex"); // find the index of the item

        assertEquals(01, actual);
    }
}
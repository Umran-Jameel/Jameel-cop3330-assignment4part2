package ucf.assignments;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ListEditorTest {
    ListEditor le = new ListEditor();
    ToDoList list = new ToDoList();

    @Test
    void addToList() {
        Item test = new Item(); // instantiating a new item for test
        test.itemName = "test"; // this item's name will be test
        list.list.add(test); // we add it to the test list
        assertEquals("test", list.list.get(0).itemName); // check to see if it has been added properly
    }

    @Test
    void removeFromList() {
        ArrayList<Item> test = new ArrayList<Item>(); // empty array list for comparison

        Item toAdd = new Item();
        toAdd.itemName = "toAdd";

        list.list.add(toAdd); // adding an item to remove it

        le.removeFromList(list, "toAdd"); // remove the item

        assertEquals(test, list.list);
    }

    @Test
    void editDescription() {
        Item test = new Item(); // new item for test
        test.description = "test";
        list.list.add(test); // add the item to a list
        le.editDescription(list, "test", "test"); // set the item's description as "test"

        assertEquals("test", test.description); // check if item's description is as expected after method is called

    }

    @Test
    void editDueDate() {
    }

    @Test
    void markAsComplete() {
    }

    @Test
    void clearList() {
        // adding an item to be cleared
        Item test1 = new Item();
        list.list.add(test1);

        // adding a second item to be cleared
        Item test2 = new Item();
        list.list.add(test2);

        le.clearList(list); // clear the list

        assertEquals(0, list.list.size()); // size of the list should be zero
    }
}
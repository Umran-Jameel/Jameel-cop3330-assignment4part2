package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListEditorTest {
    ListEditor le = new ListEditor();
    ToDoList list = new ToDoList();

    @Test
    void addToList() {
        Item item = new Item(); // instantiating a new item for test
        item.itemName = "test"; // this item's name will be test
        list.list.add(item); // we add it to the test list
        assertEquals("test", list.list.get(0).itemName); // check to see if it has been added properly
    }

    @Test
    void removeFromList() {
    }

    @Test
    void editDescription() {
    }

    @Test
    void editDueDate() {
    }

    @Test
    void markAsComplete() {
    }

    @Test
    void clearList() {
    }
}
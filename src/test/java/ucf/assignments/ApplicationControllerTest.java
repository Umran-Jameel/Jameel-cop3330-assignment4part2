/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Umran Jameel
 */
package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationControllerTest {

    @Test
    void addNewListClicked() {
        // create a list of to do lists
        // add a new list to that using the GUI
        // assertEquals(expected list of lists, actual list of lists)
    }

    @Test
    void removeExistingListClicked() {
        // create a list of to do lists
        // remove a list using the GUI
        // assertEquals(expected list of lists, actual list of lists)
    }

    @Test
    void loadListClicked() {
        // load a list using the gui
        // read it and store its contents in a string
        // assertEquals(expected string, actual string)
    }

    @Test
    void loadAllListsClicked() {
        // load the lists and store the contents in an array list of strings
        // assertEquals(expected array list, actual)
    }

    @Test
    void editTitleClicked() {
        // create a new list
        // edit the title of the list using the gui
        // assertEquals(expected new title, actual)
    }

    @Test
    void editDescriptionClicked() {
        // create a new list, create an item with a description
        // edit the description of the item using the gui
        // assertEquals(expected new description, actual)
    }

    @Test
    void addItemClicked() {
        // create a new list and add an item to it
        // assertEquals(expected list after addition, actual)
    }

    @Test
    void removeItemClicked() {
        // create a new list with itams and subtract an item to it
        // assertEquals(expected list after subtraction, actual)
    }

    @Test
    void editDueDateClicked() {
        // create a new list, create an item with a due date
        // edit the due date of the item using the gui
        // assertEquals(expected new date, actual)
    }

    @Test
    void markItemCompleteClicked() {
        // create a new list, create an item marked as incomplete
        // edit the status of the item using the gui
        // assertEquals(expected completed, actual)
    }
}
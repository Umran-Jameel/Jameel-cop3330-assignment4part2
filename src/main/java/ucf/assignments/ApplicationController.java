/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Umran Jameel
 */
package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ApplicationController {
    ToDoList toDoList = new ToDoList();
    ListEditor listEditor = new ListEditor();
    Display display = new Display();
    SaveAndLoader saveAndLoader = new SaveAndLoader();

    public void saveListToStorageClicked(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage(); // stage for the file chooser

        // file chooser to choose the directory
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save list");

        // only text files will be saved
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".txt", "*.txt"));

        File textList = fileChooser.showSaveDialog(stage); // the dialog is opened, user chooses where to save file and file name
        saveAndLoader.saveListToStorage(toDoList, textList); // we write and save the file

    }

    public void loadListClicked(ActionEvent actionEvent) throws FileNotFoundException {
        Stage stage = new Stage(); // stage for the file chooser

        // only text files may be opened
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".txt", "*.txt"));

        File file = fileChooser.showOpenDialog(stage); // opening the file that the user has chosen
        saveAndLoader.loadList(toDoList, file); // loading the list and putting the items in the list

        Stage confirmation = new Stage();
        confirmation.setTitle("List loaded");

        Text confirmationText = new Text("Successfully loaded list!");

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(20,20,20,20));
        vBox.getChildren().add(confirmationText);

        Scene scene = new Scene(vBox, 200, 50);
        confirmation.setScene(scene);
        confirmation.show();

    }

    public void editDescriptionClicked(ActionEvent actionEvent) {
        // ListView to show the user all of the items in the list
        ListView items = new ListView();
        for (int i = 0; i < toDoList.list.size(); i++) {
            items.getItems().add(toDoList.list.get(i).itemName); // add items to list view
        }

        Text current = new Text(); // Text to show user the current description
        TextField edited = new TextField(); // Textfield for user to enter new description
        Button done = new Button("Done"); // button to confirm the new description

        // Tell the user what to do when they see the list view
        Text instructions = new Text();
        instructions.setText("Select an item to edit its description.");


        // Set the stage with title: edit description
        Stage stage = new Stage();
        stage.setTitle("Edit Description of an Item");

        // Vbox with spacing 10, padding 20 all around
        VBox vb1 = new VBox(10);
        vb1.setPadding(new Insets(20, 20, 20, 20));
        vb1.getChildren().addAll(items, instructions); // Add the list view and instructions to the vbox
        vb1.setAlignment(Pos.TOP_CENTER);

        // Set the listview scene
        Scene scene = new Scene(vb1, 500, 500);
        stage.setScene(scene);
        stage.show();

        // Handle mouse click of the item the user chooses
        items.setOnMouseClicked(e -> {
            // Get the item that the user selected as a string
            String selected = items.getSelectionModel().getSelectedItem().toString();
            stage.close(); // close the listview scene, not needed anymore

            // make a new stage for the user to enter the new description
            Stage descriptionStage = new Stage();
            descriptionStage.setTitle("Edit Description of Item " + selected); // title of the new stage

            int i;
            // loop through to find the item in the Arraylist
            for (i = 0; i < toDoList.list.size(); i++) {
                if (selected.equals(toDoList.list.get(i).itemName)) {
                    break;
                }
            }
            // Get the current description of the item ot show to the user
            current.setText("Current: " + toDoList.list.get(i).description);

            // new Vbox for the description scene, spacing 10, padding 20 all around
            VBox vb2 = new VBox(10);
            vb2.setPadding(new Insets(20, 20, 20, 20));
            vb2.getChildren().addAll(current, edited, done); // add the current description text component, edited textfield, and done button to the vbox
            vb2.setAlignment(Pos.TOP_CENTER);

            // make the new scene with the description vbox
            Scene scene2 = new Scene(vb2, 500, 130);
            descriptionStage.setScene(scene2);
            descriptionStage.show();

            int finalI = i; // make the index a final variable
            // handle the button click of the done button
            done.setOnAction(event -> {
                // if the entered description is not between 1 and 256 characters in length
                if ((edited.getText().length() == 0) || edited.getText().length() > 256) {
                    descriptionStage.setTitle("Description should be between 1 and 256 characters in length."); // let the user know of their mistake
                    edited.clear(); // clear the text field for the user to enter again
                }
                else {
                    listEditor.editDescription(toDoList, selected, edited.getText()); // add the new description
                    descriptionStage.close();
                }
            });

        });

    }

    public void addItemClicked(ActionEvent actionEvent) {
        // Instantiating a textfield for input, an add button and close button
        TextField textField = new TextField();
        Button button = new Button("Add Item");
        Button closeButton = new Button("Close");

        // make the stage
        Stage stage = new Stage();
        stage.setTitle("Add Item");

        // using a vbox for the dialog
        VBox vb = new VBox(10);
        vb.setPadding(new Insets(20, 20, 20, 20));
        vb.getChildren().addAll(textField, button, closeButton);
        vb.setAlignment(Pos.TOP_CENTER);

        // layout of the scene
        Scene scene = new Scene(vb, 500, 130);
        stage.setScene(scene);
        stage.show();

        // Once the add button is clicked, add the item to the list, and tell user to close
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                stage.setTitle("Added item to list! Press Close to close");
                listEditor.addToList(toDoList, textField.getText());
            }
        };
        // setting the onAction for the buttons
        button.setOnAction(event);
        closeButton.setOnAction(e -> stage.close());
    }

    public void removeItemClicked(ActionEvent actionEvent) {
        // ListView to show the user all of the items in the list
        ListView items = new ListView();
        for (int i = 0; i < toDoList.list.size(); i++) {
            items.getItems().add(toDoList.list.get(i).itemName); // add items to list view
        }

        // Tell the user what to do when they see the list view
        Text instructions = new Text();
        instructions.setText("Select an item to remove.");


        // Set the stage with title: Remove Item
        Stage stage = new Stage();
        stage.setTitle("Remove Item");

        // Vbox with spacing 10, padding 20 all around
        VBox vb1 = new VBox(10);
        vb1.setPadding(new Insets(20, 20, 20, 20));
        vb1.getChildren().addAll(items, instructions); // Add the list view and instructions to the vbox
        vb1.setAlignment(Pos.TOP_CENTER);

        // Set the listview scene
        Scene scene = new Scene(vb1, 500, 500);
        stage.setScene(scene);
        stage.show();

        items.setOnMouseClicked(e -> {
            // Get the item that the user selected as a string
            String selected = items.getSelectionModel().getSelectedItem().toString();

            listEditor.removeFromList(toDoList, selected); // remove the selected list

            stage.close(); // close the listview stage

            // new stage for confirmation that the item was removed
            Stage confirmation = new Stage();
            confirmation.setTitle("Successfully removed item " + selected);

            // close button to close the confirmation window
            Button closeButton = new Button("Close");

            // new Vbox for confirmation dialog, spacing 10, padding 20 all around
            VBox vb2 = new VBox(10);
            vb2.setPadding(new Insets(20, 20, 20, 20));
            vb2.getChildren().add(closeButton); // Add the close button to the vbox
            vb2.setAlignment(Pos.CENTER);

            // Set the listview scene
            Scene scene2 = new Scene(vb2, 400, 100);
            confirmation.setScene(scene2);
            confirmation.show();

            closeButton.setOnAction(event -> confirmation.close());
        });
    }

    public void editDueDateClicked(ActionEvent actionEvent) {
        // ListView to show the user all of the items in the list
        ListView items = new ListView();
        for (int i = 0; i < toDoList.list.size(); i++) {
            items.getItems().add(toDoList.list.get(i).itemName); // add items to list view
        }

        Stage stage = new Stage();
        stage.setTitle("Change the due date of an item");

        Text instructions = new Text("Select an item to change its due date.");


        // Vbox with spacing 10, padding 20 all around
        VBox vb1 = new VBox(10);
        vb1.setPadding(new Insets(20, 20, 20, 20));
        vb1.getChildren().addAll(items, instructions); // Add the list view and instructions to the vbox
        vb1.setAlignment(Pos.TOP_CENTER);

        // Set the listview scene
        Scene scene = new Scene(vb1, 500, 500);
        stage.setScene(scene);
        stage.show();

        // handle the user click of the item
        items.setOnMouseClicked(event -> {
            // store the item name of the selected item
            String selected = items.getSelectionModel().getSelectedItem().toString();

            // date picker for the user to pick a date
            DatePicker datePicker = new DatePicker();
            Button changeDueDate = new Button("Change Due Date");

            int index = listEditor.findIndex(toDoList, selected);


            Text current = new Text();
            current.setText("Current due date: " + toDoList.list.get(index).dueDate);

            // new stage for selecting a date
            Stage stage2 = new Stage();
            stage2.setTitle("Change due date of selected item");

            // Vbox with spacing 10, padding 20 all around
            VBox vb2 = new VBox(10);
            vb2.setPadding(new Insets(20, 20, 20, 20));
            vb2.getChildren().addAll(datePicker, changeDueDate, current); // Add the date picker, change button and current date text to the vbox
            vb2.setAlignment(Pos.TOP_CENTER);

            // Set the listview scene
            Scene scene2 = new Scene(vb2, 500, 500);
            stage2.setScene(scene2);
            stage2.show();

            // user clicks button, date is stored and item's due date is changed
            changeDueDate.setOnAction(e -> {
                LocalDate dueDate = datePicker.getValue();
                listEditor.editDueDate(toDoList, dueDate, selected); // changing the due date and getting the index for the current due date
                stage2.close();
            });
        });



    }

    public void markItemCompleteClicked(ActionEvent actionEvent) {
        // ListView to show the user all of the items in the list
        ListView items = new ListView();
        for (int i = 0; i < toDoList.list.size(); i++) {
            items.getItems().add(toDoList.list.get(i).itemName); // add items to list view
        }


        // Tell the user what to do when they see the list view
        Text instructions = new Text();
        instructions.setText("Select an item to mark it as complete.");


        // Set the stage with title: mark an item as complete
        Stage stage = new Stage();
        stage.setTitle("Mark an item as complete");

        // Vbox with spacing 10, padding 20 all around
        VBox vb1 = new VBox(10);
        vb1.setPadding(new Insets(20, 20, 20, 20));
        vb1.getChildren().addAll(items, instructions); // Add the list view and instructions to the vbox
        vb1.setAlignment(Pos.TOP_CENTER);

        // Set the listview scene
        Scene scene = new Scene(vb1, 500, 500);
        stage.setScene(scene);
        stage.show();

        items.setOnMouseClicked(e -> {
            String selected = items.getSelectionModel().getSelectedItem().toString(); // get the selected item in string form

            CheckBox status = new CheckBox(); // checkbox to make an item complete

            // text to show user item they selected
            Text itemName = new Text();
            itemName.setText(selected);

            Text confirmation = new Text(); // text to let use know the status of the item


            // new stage, the one where the user will mark the item as complete
            Stage stage2 = new Stage();

            // Vbox with spacing 10, padding 20 all around
            VBox vb2 = new VBox(10);
            vb2.setPadding(new Insets(20, 20, 20, 20));
            vb2.getChildren().addAll(itemName, status, confirmation); // item name text, checkbox, and confirmation to the user add to box
            vb2.setAlignment(Pos.CENTER);

            // set the scene, 100 x 100
            Scene scene2 = new Scene(vb2, 100, 100);
            stage2.setScene(scene2);
            stage2.show();


            int index = listEditor.findIndex(toDoList, selected); // finding the index of the selected item in the list

            // if the item is already complete
            if (toDoList.list.get(index).status) {
                status.setSelected(true); // the checkbox will be checked
                confirmation.setText("Complete!"); // the user will know that the item is complete
            }
            else {
                status.setSelected(false); // the checkbox will not be checked
                confirmation.setText("Incomplete");
            }

            // handling the checkbox being checked or unchecked
            status.setOnAction(event -> {
                // if the checkbox is checked by the user
                if (status.isSelected()) {
                    listEditor.markAsComplete(toDoList, index); // mark the item as complete (boolean status -> true)
                    confirmation.setText("Complete!"); // confirmation for the user
                }
                else {
                    listEditor.markAsInComplete(toDoList, index); // the item is marked as Incomplete
                    confirmation.setText("Incomplete"); // confirmation for the user
                }
            });
        });
    }

    public void displayIncompleteClicked(ActionEvent actionEvent) {
        ArrayList<String> incomplete; // new arraylist to get all the completed items
        incomplete = display.displayIncomplete(toDoList); // taking the list and getting the completed items

        // ListView to show the user all of the incomplete items in the list
        ListView items = new ListView();
        for (int i = 0; i < incomplete.size(); i++) {
            items.getItems().add(incomplete.get(i)); // add the items to the listview
        }

        // stage to show the listview
        Stage stage = new Stage();
        stage.setTitle("Incomplete Items");

        // using vbox for the scene, spacing 10, padding 20 each
        VBox vb1 = new VBox(10);
        vb1.setPadding(new Insets(20, 20, 20, 20));
        vb1.getChildren().addAll(items); // Add the list view and instructions to the vbox

        // Set the listview scene
        Scene scene = new Scene(vb1, 500, 500);
        stage.setScene(scene);
        stage.show();
    }

    public void displayAllClicked(ActionEvent actionEvent) {
        // ListView to show the user all of the items in the list
        ListView items = new ListView();
        for (int i = 0; i < toDoList.list.size(); i++) {
            items.getItems().add(toDoList.list.get(i).itemName); // add all items to list view
        }

        // stage for listview
        Stage stage = new Stage();
        stage.setTitle("All Items");

        // using vbox for the scene, spacing 10, padding 20 each
        VBox vb1 = new VBox(10);
        vb1.setPadding(new Insets(20, 20, 20, 20));
        vb1.getChildren().addAll(items); // Add the list view and instructions to the vbox

        // Set the listview scene
        Scene scene = new Scene(vb1, 500, 500);
        stage.setScene(scene);
        stage.show();
    }

    public void displayCompleteClicked(ActionEvent actionEvent) {
        ArrayList<String> completed;  // new arraylist to get all the completed items
        completed = display.displayCompleted(toDoList); // taking the list and getting the completed items

        // ListView to show the user all of the completed items in the list
        ListView items = new ListView();
        for (int i = 0; i < completed.size(); i++) {
            items.getItems().add(completed.get(i)); // add the items to the listview
        }


        // stage for listview
        Stage stage = new Stage();
        stage.setTitle("Completed Items");

        // using vbox for the scene, spacing 10, padding 20 each
        VBox vb1 = new VBox(10);
        vb1.setPadding(new Insets(20, 20, 20, 20));
        vb1.getChildren().addAll(items); // Add the list view and instructions to the vbox

        // Set the listview scene
        Scene scene = new Scene(vb1, 500, 500);
        stage.setScene(scene);
        stage.show();
    }

    public void clearListClicked(ActionEvent actionEvent) {
        listEditor.clearList(toDoList); // clear the list

        // stage to tell user the list has been cleared
        Stage stage = new Stage();
        stage.setTitle("List Cleared");

        Text clear = new Text(); // text to confirm the list clearance
        clear.setText("The list has been cleared.");

        // using vbox for the scene, spacing 10, padding 20 each
        VBox vb1 = new VBox(10);
        vb1.setPadding(new Insets(20, 20, 20, 20));
        vb1.getChildren().addAll(clear); // Add the clear button to the vbox
        vb1.setAlignment(Pos.CENTER);

        // Set the listview scene
        Scene scene = new Scene(vb1, 200, 100);
        stage.setScene(scene);
        stage.show();

    }
}

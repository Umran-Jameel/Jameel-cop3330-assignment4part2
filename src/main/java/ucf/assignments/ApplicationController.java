/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Umran Jameel
 */
package ucf.assignments;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ApplicationController {
    ToDoList list = new ToDoList();
    ListEditor le = new ListEditor();

    public void saveListToStorageClicked(ActionEvent actionEvent) {
        // saves a desired list to storage as a text file
    }

    public void loadListClicked(ActionEvent actionEvent) {
        // loads a desired list
    }

    public void editDescriptionClicked(ActionEvent actionEvent) {
        // ListView to show the user all of the items in the list
        ListView items = new ListView();
        for (int i = 0; i < list.list.size(); i++) {
            items.getItems().add(list.list.get(i).itemName); // add items to list view
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
            descriptionStage.setTitle("Edit Description of Item" + selected); // title of the new stage

            int i;
            // loop through to find the item in the Arraylist
            for (i = 0; i < list.list.size(); i++) {
                if (selected.equals(list.list.get(i).itemName)) {
                    break;
                }
            }
            // Get the current description of the item ot show to the user
            current.setText("Current: " + list.list.get(i).description);

            // new Vbox for the description scene, spacing 10, padding 20 all around
            VBox vb2 = new VBox(10);
            vb2.setPadding(new Insets(20, 20, 20, 20));
            vb2.getChildren().addAll(current, edited, done); // add the current description text component, edited textfield, and done button to the vbox

            // make the new scene with the description vbox
            Scene scene2 = new Scene(vb2, 500, 500);
            descriptionStage.setScene(scene2);
            descriptionStage.show();

            int finalI = i; // make the index a final variable
            // handle the button click of the done button
            done.setOnAction(event -> {
                le.editDescription(list.list.get(finalI), edited.getText()); // add the new description to the item's index in the to do list
                descriptionStage.close();
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

        // layout of the scene
        Scene scene = new Scene(vb, 500, 500);
        stage.setScene(scene);
        stage.show();

        // Once the add button is clicked, add the item to the list, and tell user to close
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                stage.setTitle("Added List! Press Close to close");
                Item item = new Item();
                item.itemName = textField.getText();
                list.list.add(item);
            }
        };
        // setting the onAction for the buttons
        button.setOnAction(event);
        closeButton.setOnAction(e -> stage.close());
    }

    public void removeItemClicked(ActionEvent actionEvent) {
    }

    public void editDueDateClicked(ActionEvent actionEvent) {
        // edits the due date of a selected item in a desired list
    }

    public void markItemCompleteClicked(ActionEvent actionEvent) {
        // marks a selected item as complete in a desired list
    }

    public void displayIncompleteClicked(ActionEvent actionEvent) {
    }

    public void displayAllClicked(ActionEvent actionEvent) {
    }

    public void displayCompleteClicked(ActionEvent actionEvent) {
    }

    public void clearListClicked(ActionEvent actionEvent) {

    }
}

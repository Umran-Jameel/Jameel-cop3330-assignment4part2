/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Umran Jameel
 */
package ucf.assignments;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        // edits the description of a selected item in a selected list
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
        Scene scene = new Scene(vb, 350, 200);
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

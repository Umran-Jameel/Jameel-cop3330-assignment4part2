/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Umran Jameel
 */
package ucf.assignments;

import java.io.*;
import java.util.Scanner;

public class SaveAndLoader {
    public void saveListToStorage(ToDoList toDoList, File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file); // new file writer to write all the items

        // loop through each item
        for (int i = 0; i < toDoList.list.size(); i++) {
            fileWriter.write(toDoList.list.get(i).itemName + "\n"); // write the name of the item to the file
        }

        fileWriter.close();
    }

    public void loadList(ToDoList toDoList, File file) throws FileNotFoundException {
        // scanner to read the text file
        Scanner readFile = new Scanner(file);

        // loop through the file
        while (readFile.hasNextLine()) {
            // making a new item for each item in the text file
            Item item = new Item();
            item.itemName = readFile.nextLine();

            toDoList.list.add(item); // add the item to the list
        }
    }
}

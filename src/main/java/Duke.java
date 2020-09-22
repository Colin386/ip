package main.java;

import java.util.Scanner;

public class Duke {
    private UserInterface ui;
    private MyList items;
    private FileSaver fileManager;
    private Parser parser;


    /**
     * Constructor for the Duke item
     */
    public Duke() {
        ui = new UserInterface();
        items = new MyList();
        fileManager = new FileSaver();
        parser = new Parser(items);

    }

    /**
     * Main run function, prints welcome message, loops until user enters "bye", then prints bye message
     */
    public void run() {
        ui.welcomeMessage();

        Scanner in  = new Scanner(System.in);
        String command;

        boolean isComplete = false;
        fileManager.loadData(items);

        while (isComplete == false) { //program will return complete when bye condition is met

            command = in.nextLine();

            isComplete = parser.processUserInput(command, items);
            fileManager.saveData(items);
        }

        fileManager.saveData(items);
        System.out.println("File saved successfully!");

        ui.goodbyeMessage();
    }
    /**
     * Main function, creates a Duke class item and starts to run
     * @param args
     */
    public static void main(String[] args) {

        new Duke().run();


    }
}

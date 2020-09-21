package main.java;

import java.util.Scanner;

public class Duke {
    private UserInterface ui;
    private MyList items;
    private FileSaver fileManager;


    public Duke() {
        ui = new UserInterface();
        items = new MyList();
        fileManager = new FileSaver();

    }

    public void run() {
        ui.welcomeMessage();
        ui.echo(this.items, this.fileManager);
        ui.goodbyeMessage();
    }
    /**
     * Main Functions, welcomes users, process user commands
     * Prints goodbye message and terminates.
     * @param args
     */
    public static void main(String[] args) {

        new Duke().run();


    }
}

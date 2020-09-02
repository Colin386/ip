package main.java;

import java.util.Scanner;

public class Duke {

    /**
     * Prints a welcome message on the screen
     *
     * @return void
     */
    public static void welcomeMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints a goodbye message
     *
     */
    public static void goodbyeMessage() {

        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Function processes user commands
     * Terminates if bye is entered by the user
     * prints out the list of task if "list" is entered
     * Marks item X as complete when "done X" is entered
     *
     */
    public static void echo() {

        Scanner in  = new Scanner(System.in);
        String command;
        MyList items = new MyList();

        while (true) {

            command = in.nextLine();

            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                items.printList();

            } else if (command.contains("done")) {
                String[] info = command.split(" ");
                int index;

                index = Integer.parseInt(info[1]);
                items.completeTask(index);

            //add the thing into the list
            } else {

                items.addItem(command);
            }

        }

    }

    /**
     * Main Functions, welcomes users, process user commands
     * Prints goodbye message and terminates.
     * @param args
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        welcomeMessage();
        echo();
        goodbyeMessage();
    }
}

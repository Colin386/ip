package main.java;

import java.util.Scanner;

public class UserInterface {

    /**
     * Prints a welcome message on the screen
     *
     */
    public static void welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
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
    public static void echo(MyList items, FileSaver fileManager) {

        Scanner in  = new Scanner(System.in);
        String command;

        boolean isComplete = false;
        fileManager.loadData(items);

        while (isComplete == false) {

            command = in.nextLine();

            isComplete = processUserInput(command, items);
            fileManager.saveData(items);
        }

        fileManager.saveData(items);
        System.out.println("File saved successfully!");



    }

    private static boolean processUserInput(String command, MyList items) {
        if (command.equals("bye")) {
            return true;
        } else if (command.equals("list")) {
            items.printList();

        } else if (command.contains("done")) {
            String[] info = command.split(" ");
            int index;
            try {
                index = Integer.parseInt(info[1]);
                items.completeTask(index);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid index number entered");
            } catch (NumberFormatException e) {
                System.out.println("Number missing or invalid");
            }


            //add the thing into the list
        } else if (command.contains("delete")) {
            try {
                String[] commandArgs = command.split(" ");
                String number = commandArgs[1];
                number.trim(); //removes trailing spaces to convert into proper integer later
                int index = Integer.parseInt(number);
                items.deleteItem(index-1);
            } catch (IndexOutOfBoundsException e) { //error is thrown if the user gave a number outside the array size.
                System.out.println("Invalid index number entered");
            } catch (NumberFormatException e) { //error is thrown if the user did not provide a number
                System.out.println("Number missing or invalid");
            }

        }
        else {

            items.addItem(command);
        }
        return false;
    }
}

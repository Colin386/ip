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


}

package main.java;

import main.java.activity.Deadline;
import main.java.activity.Event;
import main.java.activity.Task;
import main.java.activity.ToDo;
import main.java.dukeExceptions.*;

import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class Parser {

    private MyList items;

    public Parser(MyList itemStorage)
    {
        this.items = itemStorage;
    }



    public boolean processUserInput(String command, MyList items) {
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



        } else if (command.contains("delete")) {
            ProcessDelete(command);

        }
        else {
            processAdd(command);
        }
        return false;
    }

    private void processAdd(String command) {
        try {
            Task newTask = produceTask(command);
            items.addItem(newTask);
        } catch (UnrecognisedCommandException e) {
            System.out.printf("\nI'm sorry, %s is not a recognised command\n", e.getWrongCommand());
        } catch (NotEnoughInfoException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.printf("\nNothing was entered, please enter a command\n");
        } catch (DateTimeParseException e) {
            System.out.println("\nIncorrect Date entered, enter in yyyy-mm-dd hh:mm format");
        }

    }

    private void ProcessDelete(String command) {
        try {
            String[] commandArgs = command.split(" ");
            String number = commandArgs[1];
            number = number.trim(); //removes trailing spaces to convert into proper integer later
            int index = Integer.parseInt(number);
            items.deleteItem(index-1);
        } catch (IndexOutOfBoundsException e) { //error is thrown if the user gave a number outside the array size.
            System.out.println("Invalid index number entered");
        } catch (NumberFormatException e) { //error is thrown if the user did not provide a number
            System.out.println("Number missing or invalid");
        }
    }

    /**
     *
     * @param itemInfo String containing command word, user event and dates
     * @return Task object according to parameters in itemInfo
     */
    public Task produceTask(String itemInfo) throws UnrecognisedCommandException, NotEnoughInfoException, IndexOutOfBoundsException, DateTimeParseException {

        String[] wordsEntered = itemInfo.split(" ");
        String itemType = wordsEntered[0];
        String[] commandInformationWords = Arrays.copyOfRange(wordsEntered,1, wordsEntered.length);
        String commandInformation = String.join( " ", commandInformationWords);

        switch (itemType) {

        case ("todo"):

            //throw exception if there are too little words
            if (commandInformationWords.length < 1) {
                throw new NotEnoughInfoException("todo");
            }
            return new ToDo(commandInformation);

        case ("deadline"):

            try {
                String deadlineInfo = this.extractDate(commandInformation, "/by", "deadline");
                String deadlineActivityInfo = this.extractActivity(commandInformation, "/by", "deadline");
                return new Deadline(deadlineActivityInfo, deadlineInfo);

            } catch (NotEnoughInfoException | DateTimeParseException e) {
                throw e;
            }


        case ("event"):
            try {
                String dateInfo = this.extractDate(commandInformation, "/at", "event");
                String eventInfo = this.extractActivity(commandInformation, "/at", "event");
                return new Event(eventInfo, dateInfo);
            } catch (NotEnoughInfoException | DateTimeParseException e) {
                throw e;
            }


        default:

            throw new UnrecognisedCommandException(itemType);

        }


    }
    /**
     * Extracts out the date information that is located after a certain keyword
     *
     * @param commandInfo String that contains the user input
     * @param keyword String containing the keyword that comes immediately before the date. E.g. /by
     * @param caller String of the name of the activity type that called it
     * @return String containing information on the Date found after the keyword
     */
    private String extractDate(String commandInfo, String keyword, String caller) throws MissingKeywordException, MissingDateException {
        int slashIndex = commandInfo.indexOf(keyword);
        String dateInfo;
        if (slashIndex < 0) {
            throw new MissingKeywordException(caller, keyword); //missing keyword exception
        }

        dateInfo = commandInfo.substring(slashIndex+1);


        if (dateInfo.length() <= keyword.length() - 1) {
            throw new MissingDateException(caller); //missing date exception
        }

        return dateInfo;
    }

    /**
     * Extracts out the Activity information that is located before a certain keyword
     *
     * @param commandInfo String that contains the user input
     * @param keyword String containing a keyword that comes immediately before the date e.g. /by
     * @param caller String of the name of the activity type that called it
     * @return String containing the activity information
     */
    private String extractActivity(String commandInfo, String keyword, String caller) throws MissingKeywordException, MissingActivityException {
        int slashIndex = commandInfo.indexOf(keyword);
        String activityName;
        if (slashIndex < 0) {
            throw new MissingKeywordException(caller, keyword);
        }

        activityName = commandInfo.substring(0, slashIndex);

        if (activityName.length() <= 0) {
            throw new MissingActivityException(caller);
        }
        return activityName.trim();


    }


}

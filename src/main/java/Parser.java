package main.java;

import main.java.activity.Deadline;
import main.java.activity.Event;
import main.java.activity.Task;
import main.java.activity.ToDo;
import main.java.dukeExceptions.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class Parser {

    private MyList items;

    /**
     * Constructor for the Parser object that interpret all user commands
     *
     * @param itemStorage MyList object to be used for containing all user recorded Activities
     */
    public Parser(MyList itemStorage) {
        this.items = itemStorage;
    }

    /**
     * Function accepts in the user input and determine which function to call and operate
     *
     * @param command string containing user input
     * @param items   MyList item where activity entry storage, deletion or listing is performed
     * @return boolean, true if bye is entered to indicate program is complete, false otherwise
     */

    public boolean processUserInput(String command, MyList items) {
        if (command.equals("bye")) {
            return true;
        } else if (command.contains("list")) {
            this.processList(command, items);

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

        } else if (command.contains("find")) {
            processFind(command, items);
        } else {
            processAdd(command);
        }
        return false;
    }


    /**
     * Internal command used to process any find commands entered by the user
     *
     * @param command String containing the user input
     * @param items MyList data type to be searched through to find the required task of the user
     */
    private void processFind(String command, MyList items) {
        String[] commandArgs = command.split(" ");
        if (commandArgs.length == 1) {
            System.out.println("\nNothing to find! Please enter \" Find <words>\"");
            return;
        }

        String[] findQueryWords = Arrays.copyOfRange(commandArgs, 1, commandArgs.length);
        String findQuery = String.join(" ", findQueryWords);

        int listSize = items.getSize();
        System.out.printf("\nHere are the list of activities that contain the word \"%s\":\n\n", findQuery);
        for (int i = 0; i < listSize; i++) { //goes through each item on the list and print out the ones that match search

            Task currentActivity = items.retrieveTask(i);
            if (currentActivity.getName().contains(findQuery)) {
                System.out.printf("%d.", i + 1);
                System.out.printf("%s", currentActivity.toString());
                System.out.printf("\n");
            }
        }
    }


    /**
     * Internal command used for processing any user listing commands
     *
     * @param command String containing the user input
     * @param items MyList data type to be looked through for printing in the user specified format
     */
    private void processList(String command, MyList items) {
        String[] commandArgs = command.split(" ");
        try {
            if (commandArgs.length == 1) {
                items.printList(); //no deadline given, assume standard print list
            } else {
                String dateString = commandArgs[1];
                LocalDate dateRequest = LocalDate.parse(dateString);
                items.printList(dateRequest);
            }
        } catch (DateTimeParseException e) {
            System.out.println("I'm sorry, an invalid date has been entered, please enter a valid date yyyy-mm-dd");
        }


    }

    /**
     * Internal function for processing the add Task command by the user, used to call the appropriate function
     *
     * @param command contains the user command
     */
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

    /**
     * Internal command used to process delete commands entered by the user
     *
     * @param command String containing user input indicating which object to be deleted from the MyList
     */
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
     * Command used to produce the required Task object with the information provided by the user
     *
     * @param itemInfo String with the user entered information about the event
     * @return
     *
     * @throws UnrecognisedCommandException thrown when user has given a Task type that does not exist in the program
     * @throws NotEnoughInfoException thrown if user did not provide the program with enough information to produce the Task required
     * @throws IndexOutOfBoundsException thrown if user did not give any information for the program to work produce the required Task
     * @throws DateTimeParseException thrown if invalid date information not in yyyy-mm--dd form is entered by user
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
     *
     * @throws MissingKeywordException thrown if the / keywords is not present in the user input
     * @throws MissingDateException thrown if there is no date information provided after the / keyword
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
     * @param caller String of the name of the activity type that called it e.g. deadline
     * @return String containing the activity information removed from the user input
     *
     * @throws MissingKeywordException thrown if there is no / keyword given by the user
     * @throws MissingActivityException thrown if there is nothing located before the keyword
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

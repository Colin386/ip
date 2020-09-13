package main.java;


import main.java.activity.Deadline;
import main.java.activity.Event;
import main.java.activity.Task;
import main.java.activity.ToDo;
import main.java.dukeExceptions.*;

import java.util.ArrayList;
import java.util.Arrays;

public class MyList {

    /** Array containing all tasks recorded*/
    //private Task[] things;
    /** Number of elements recorded in the array*/
    //private int size;

    /**new array list implementation*/
    private ArrayList<Task> things;

    /**
     *
     * @param itemInfo String containing command word, user event and dates
     * @return Task object according to parameters in itemInfo
     */
    private Task produceTask(String itemInfo) throws UnrecognisedCommandException, NotEnoughInfoException {

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
            } catch (NotEnoughInfoException e) {
                throw e;
            }


        case ("event"):

            String dateInfo = this.extractDate(commandInformation, "/at", "event");
            String eventInfo = this.extractActivity(commandInformation, "/at", "event");
            return new Event(eventInfo, dateInfo);

        default:

            throw new UnrecognisedCommandException(itemType);

        }


    }

    /**
     * Constructor method for generating a new list of items
     */
    public MyList(){

        this.things = new ArrayList<Task>();
    }

    /**
     * Generates a Task item to be added to the item area in MyList
     *
     * @param itemInfo String containing information to produce the required task
     */
    public void addItem(String itemInfo)  {

        try {
            Task item = this.produceTask(itemInfo);
            things.add(item);

            System.out.printf("\nGot it. I've added this task:");
            System.out.printf("\n  %s", item.toString());
            System.out.printf("\nNow you have %d tasks in the list.\n", things.size());

        } catch (UnrecognisedCommandException e) {
            System.out.printf("\nI'm sorry, %s is not a recognised command\n", e.getWrongCommand());
        } catch (NotEnoughInfoException e) {
            System.out.println(e.getMessage());
        }


    }

    /**
     * Function deletes the item at a particular index and shift everything one space to the left
     *
     * @param index integer containing index number of item to be deleted
     */
    public void deleteItem(int index) throws IndexOutOfBoundsException {
        int currentSize = this.things.size();

        if (index < 0 || index >= currentSize) {
            throw new IndexOutOfBoundsException();
        }

        Task deletedTask = this.things.get(index);
        this.things.remove(index);

        System.out.printf("\nNoted, I've removed this task:\n");
        System.out.printf("  %s\n", deletedTask.toString());


        System.out.printf("Now you have %d tasks in the list\n", this.things.size());

    }



    /**
     * Gets the number of items recorded in the list
     *
     * @return number of items in the list
     */
    public int getSize(){
        return this.things.size();
    }

    /**
     * Prints out the list of items recorded in a neat format
     */
    public void printList(){
        System.out.printf("\nHere are the tasks in your list:");
        for (int i = 0; i < this.getSize(); i++){
            Task item = this.things.get(i);
            System.out.printf("\n%d.", i+1);

            System.out.printf("%s", item.toString());


        }
        System.out.println("");
    }

    /**
     * Retrieves the task at a particular index of the list
     *
     * @param index
     * @return
     */
    public Task retrieveTask(int index) {

        return things[index];
    }

    /**
     * Mark the task number as completed
     *
     * @param index The task number shown on the list to be marked as done
     */
    public void completeTask(int index) throws IndexOutOfBoundsException{

        if (index-1 < 0 || index-1 >= this.getSize()){
            throw new IndexOutOfBoundsException();
        }

        if (this.things.get(index-1).getStatus()) {
            System.out.printf("\nThe task %s has already been marked as complete\n", this.things.get(index-1).getName());
        } else {
            this.things.get(index-1).setStatus(true);
            System.out.printf("\nNice! I've marked this task as done:");
            System.out.printf("\n[✓] %s\n", this.things.get(index-1).getName());
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

    /**
     * Function used if we need to directly write the item into the list
     * @param item contains the task to be added to our list
     */

    public void addItem(Task item) {
        this.things[this.size] = item;
        this.size++;
    }
}

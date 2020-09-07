package main.java;


import java.util.Arrays;

public class MyList {

    /** Array containing all tasks recorded*/
    private Task[] things;
    /** Number of elements recorded in the array*/
    private int size;

    /**
     *
     * @param itemInfo String containing command word, user event and dates
     * @return Task object according to parameters in itemInfo
     */
    private Task produceTask(String itemInfo) throws UnrecognisedCommandException {

        String[] wordsEntered = itemInfo.split(" ");
        String itemType = wordsEntered[0];
        String[] commandInformationWords = Arrays.copyOfRange(wordsEntered,1, wordsEntered.length);
        String commandInformation = String.join( " ", commandInformationWords);


        int commandLength;
        int slashIndex;

        switch (itemType) {

        case ("todo"):

            //throw exception if there are too little words
            if (commandInformationWords.length < 1) {
                ;
            }
            return new ToDo(commandInformation);

        case ("deadline"):

            String deadlineInfo = this.extractDate(commandInformation, "/by");
            String deadlineActivityInfo = this.extractActivity(commandInformation, "/by");
            return new Deadline(deadlineActivityInfo, deadlineInfo);

        case ("event"):

            String dateInfo = this.extractDate(commandInformation, "/at");
            String eventInfo = this.extractActivity(commandInformation, "/at");
            return new Event(eventInfo, dateInfo);

        default:

            throw new UnrecognisedCommandException(itemType);

        }


    }

    /**
     * Constructor method for generating a new list of items
     */
    public MyList(){
        this.size = 0;
        this.things = new Task[100];
    }

    /**
     * Generates a Task item to be added to the item area in MyList
     *
     * @param itemInfo String containing information to produce the required task
     */
    public void addItem(String itemInfo)  {

        try {
            Task item = this.produceTask(itemInfo);
            this.things[this.size] = item;
            this.size++;
            System.out.printf("\nGot it. I've added this task:");
            System.out.printf("\n  %s", item.toString());
            System.out.printf("\nNow you have %d tasks in the list.\n", size);

        } catch (UnrecognisedCommandException e) {
            System.out.printf("\nI'm sorry, %s is not a recognised command", e.getWrongCommand());
        }


    }

    /**
     * Gets the number of items recorded in the list
     *
     * @return number of items in the list
     */
    public int getSize(){
        return this.size;
    }

    /**
     * Prints out the list of items recorded in a neat format
     */
    public void printList(){
        System.out.printf("\nHere are the tasks in your list:");
        for (int i = 0; i < this.getSize(); i++){
            Task item = this.things[i];
            System.out.printf("\n%d.", i+1);

            System.out.printf("%s", item.toString());


        }
        System.out.println("");
    }

    /**
     * Mark the task number as completed
     *
     * @param index The task number shown on the list to be marked as done
     */
    public void completeTask(int index){

        if (index-1 < 0 || index-1 > this.getSize()){
            System.out.println("Error! No such task exists!");
            return;
        }
        this.things[index-1].setStatus(true);
        System.out.printf("\nNice! I've marked this task as done:");
        System.out.printf("\n[✓] %s\n", this.things[index-1].getName());
    }

    /**
     * Extracts out the date information that is located after a certain keyword
     *
     * @param commandInfo String that contains the user input
     * @param keyword String containing the keyword that comes immediately before the date. E.g. /by
     * @return String containing information on the Date found after the keyword
     */
    private String extractDate(String commandInfo, String keyword) {
        int slashIndex = commandInfo.indexOf(keyword);
        String dateInfo;
        if (slashIndex < 0) {
            ; //missing keyword exception
        }

        dateInfo = commandInfo.substring(slashIndex+1);


        if (dateInfo.length() <= keyword.length() - 1) {
            ; //missing date exception
        }

        return dateInfo;
    }

    /**
     * Extracts out the Activity information that is located before a certain keyworkd
     *
     * @param commandInfo String that contains the user input
     * @param keyword String containing a keyword that comes immediately before the date e.g. /by
     * @return String containing the activity information
     */
    private String extractActivity(String commandInfo, String keyword) {
        int slashIndex = commandInfo.indexOf(keyword);
        String activityName;
        if (slashIndex < 0) {
            ;//missing keyword exception
        }

        activityName = commandInfo.substring(0, slashIndex);

        if (activityName.length() <= 0) {
            ;//missing activity exception
        }
        return activityName.trim();


    }

}

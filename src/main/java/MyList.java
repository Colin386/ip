package main.java;


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
        int commandLength;
        int slashIndex;

        switch (itemType) {

        case ("todo"):
            commandLength = 4;
            return new ToDo(itemInfo.substring(commandLength+1));

        case ("deadline"):
            commandLength = 8;
            slashIndex = itemInfo.indexOf("/");
            return new Deadline(itemInfo.substring(commandLength+1, slashIndex-1), itemInfo.substring(slashIndex+1));

        case ("event"):
            commandLength = 5;
            slashIndex = itemInfo.indexOf("/");
            return new Event(itemInfo.substring(commandLength+1, slashIndex-1), itemInfo.substring(slashIndex+1));

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
    
}

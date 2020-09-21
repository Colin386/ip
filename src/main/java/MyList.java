package main.java;


import main.java.activity.Task;
import main.java.dukeExceptions.*;

import java.util.ArrayList;

public class MyList {



    /**new array list implementation*/
    private ArrayList<Task> things;


    /**
     * Constructor method for generating a new list of items
     */
    public MyList(){

        this.things = new ArrayList<Task>();

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

        return things.get(index);
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
     * Function used if we need to directly write the item into the list
     * @param item contains the task to be added to our list
     */

    public void addItem(Task item) {
        this.things.add(item);
    }
}

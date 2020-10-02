package main.java;


import main.java.activity.Deadline;
import main.java.activity.Event;
import main.java.activity.Task;
import main.java.activity.ToDo;
import main.java.dukeExceptions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
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
     * Function deletes the item at a particular index
     *
     * @param index integer containing index number of the item to be deleted
     * @throws IndexOutOfBoundsException thrown if index number provided is not an index in the current array list. Too small or too large.
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
     * Prints out the activity list with all Task which lies on the user given date
     *
     * @param eventDate Date object which contains the date to be compared with all Tasks.
     * @throws DateTimeParseException thrown if user did not key in date in the form yyyy-mm-dd
     */
     public void printList(LocalDate eventDate) throws DateTimeParseException {


        String dateString = eventDate.toString();
        System.out.printf("\nHere are all the activities that are conducted on this date: %s\n\n", dateString);

        for (int i = 0; i < this.getSize(); i++) {
            Task item = this.things.get(i);
            LocalDateTime itemDateTime;
            if (item instanceof ToDo) {

                continue; //ignores ToDo as they do not have any deadlines

            } else if ((item instanceof Deadline)) {

                itemDateTime = ((Deadline) item).retrieveDate();

            } else {

                itemDateTime = ((Event) item).retrieveDate();

            }

            if (eventDate.equals(itemDateTime.toLocalDate())){
                System.out.printf("%d.", i+1);
                System.out.printf("%s", item.toString());//print only when date matches
                System.out.printf("\n");
            }



        }


     }

    /**
     * Retrieves the task at a particular index of the list
     *
     * @param index integer of the index number of the item to be provided from the array list
     * @return Task item which contains the task that was stored at the index number provided.
     */
    public Task retrieveTask(int index) {

        return things.get(index);
    }



    /**
     * Mark the task as completed
     *
     * @param index The task number shown on the list to be marked as done
     * @throws IndexOutOfBoundsException thrown when the index number is out of range, either too small or too large
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

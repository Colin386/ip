package main.java;

public class Task {
    /** Name of the task*/
    private String name;
    /**Boolean indicating if the job is done or not*/
    private boolean isDone;

    /**
     * Task constructor, set the task to not done
     * @param thing Name of the task
     */
    public Task(String thing){
        this.name = thing;
        this.isDone = false;
    }

    /**
     * Getter for the task name
     *
     * @return a String containing the name of the task
     */
    public String getName(){

        return this.name;
    }

    /**
     * Getter for the status
     *
     * @return boolean value, True if the item was done, false if not
     */
    public boolean getStatus(){

        return this.isDone;
    }

    /**
     * Function change the done status of the item
     *
     * @param state boolean that changes the state of the Done status of the task
     */
    public void setStatus(boolean state){

        this.isDone = state;
    }

    /**
     * Overriding the toString method to format how the program display task information
     *
     * @return String that contains the proper display format for the item status and name
     */
    @Override
    public String toString() {

        String bracketSymbol;

        if (this.isDone) {
            bracketSymbol = "[✓] ";
        } else {
            bracketSymbol = "[✗] ";
        }
        return bracketSymbol + this.getName();
    }
}

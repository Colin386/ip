package main.java.activity;

public class ToDo extends Task{

    /**
     * Creates a Todo item from a name given
     *
     * @param thing contains the name of the item
     */
    public ToDo(String thing){
        super(thing);
    }

    /**
     * Overrides the toString message for the todo item
     *
     * @return String containing the formatted name information, done status and the T tag
     */
    @Override
    public String toString(){

        return "[T]" + super.toString();
    }


}

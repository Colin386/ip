package main.java;

public class ToDo extends Task{

    public ToDo(String thing){
        super(thing);
    }

    @Override
    public String toString(){

        return "[T]" + super.toString();
    }


}

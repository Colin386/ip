package main.java.dukeExceptions;

public class FileCorruptionException extends Exception{

    private String msg;
    public FileCorruptionException() {
        this.msg = "File loading error! Data is corrupted, information not loaded";
    }

    @Override
    public String getMessage (){
        return this.msg;
    }

}

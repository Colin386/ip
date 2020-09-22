package main.java;
import main.java.activity.Deadline;
import main.java.activity.Event;
import main.java.activity.Task;
import main.java.activity.ToDo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;


public class FileSaver {

    /** root directory of wherever the java file is run*/
    private final String HOME = System.getProperty("user.dir");
    private Path dukeInfoLocation;
    private Path dukeInfoFolder;
    private List<String> prevInfo;


    /**
     * FileSaver constructor, creates a reference to the home directory/data/duke.txt
     */
    public FileSaver() {
        dukeInfoLocation = Paths.get(HOME, "data","duke.txt");
        dukeInfoFolder = Paths.get(HOME, "data");

    }

    /**
     * Saves the information stored for the session into a txt file
     * Function will attempt to create a file /data/duke.txt on the computer if file not present in the computer
     * Command will not execute a save if it was unsuccessful in creating the file
     *
     * Existing data located in /data/duke.txt is overwritten with new information each time this command is called
     *
     * @param destination Mylist data type containing the records of events done earlier to be saved to computer
     */
    public void saveData(MyList destination) {

        try {

            if (!Files.exists(dukeInfoFolder)) {
                    Files.createDirectory(dukeInfoFolder);
            }


        } catch (IOException e) {
            System.out.println("File IO Error! file cannot be saved");
            return;
        }

        try {
            int entryNumber = destination.getSize();
            ArrayList<String> toWrite = new ArrayList<>();

            for (int i = 0; i < destination.getSize(); i++) {
                Task activity = destination.retrieveTask(i);
                String activityString = this.formatTask(activity);
                toWrite.add(activityString);

            }

            Files.write(dukeInfoLocation, toWrite, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Error! unable to record information to file");
        }
        System.out.println("Session saved!");
    }

    /**
     * Loads the data into the current list for the user to continue modifying the list
     * Program will look for the existence of a file called /data/duke.txt
     * If the file does not exist, the program will not load any data to the program.
     *
     * @param destination MyList data type to contain all recorded events in the computer's txt file
     */
    public void loadData(MyList destination) {
        try {
            if (Files.notExists(dukeInfoLocation)) {
                System.out.println("Nothing to load data from");
                return;
            }

            List<String> fileInfo = new ArrayList<>();
            fileInfo = Files.readAllLines(dukeInfoLocation, StandardCharsets.UTF_8);




            for (int i = 0; i < fileInfo.size(); i++) {

                Task activity = formatCommand(fileInfo.get(i));
                if (activity == null) { //occurs when date information not stored correctly on txt file
                    continue;
                }
                destination.addItem(activity);


            }
            System.out.println("Files Loaded\n");

        } catch (IOException e) {
            System.out.println("Error loading file to program");
        }



    }




    /**
     * Converts the Task activity into a formatted string to be written to text file
     *
     * @param activity Task to be formatted into a writeable form
     * @return String containing the activity information separated by pipes
     */
    private String formatTask(Task activity) {

        String type;
        String activityName;
        String activityStatus;
        String activityDate;

        if (activity.getClass() == ToDo.class) {
            type = "T";
            activityName = activity.getName();
            activityStatus = Boolean.toString(activity.getStatus());
            return type + " | " + activityStatus + " | " + activityName;
        } else if (activity.getClass() == Deadline.class) {
            type = "D";
            activityName = activity.getName();
            activityStatus = Boolean.toString(activity.getStatus());
            activityDate = ((Deadline) activity).getFullDateString();
        } else {
            type = "E";
            activityName = activity.getName();
            activityStatus = Boolean.toString(activity.getStatus());
            activityDate = ((Event) activity).getFullDateString();
        }
        return type + " | " + activityStatus + " | " + activityName + " | " + activityDate;
    }


    /**
     * Function takes in a string and creates an object to be added to the activity list
     *
     * @param info String from txt file that stores all previous session activity information to be processed into a Task
     * @return Task that match the information stored in info String
     */
    private Task formatCommand(String info) {


        String type;
        String activityName;
        boolean activityStatus;
        String activityDate;

        String[] infoBreakdown = info.split(" \\| ");


        for (int i = 0; i < infoBreakdown.length; i++)
        {

            infoBreakdown[i] = infoBreakdown[i].trim();

        }

        type = infoBreakdown[0];
        activityStatus = Boolean.parseBoolean(infoBreakdown[1]);
        activityName = infoBreakdown[2];
        activityDate = "";
        if (!type.equals("T")) {
            activityDate = infoBreakdown[3];
        }

        String creationCommand;
        Task activity;

        try {
            switch (type) {
            case ("T"):

                activity = new ToDo(activityName);
                activity.setStatus(activityStatus);
                break;
            case ("D"):

                activity = new Deadline(activityName, "/by" + activityDate);
                activity.setStatus(activityStatus);
                break;


            default:


                activity = new Event(activityName, "/at" + activityDate);
                activity.setStatus(activityStatus);
                break;

            }

            return activity;
        } catch (DateTimeParseException e) {
            System.out.println("Error, some entries in you txt files have invalid dates or times, these entries are not recorded");
            return null;
        }


    }
}

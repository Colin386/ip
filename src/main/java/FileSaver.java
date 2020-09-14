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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class FileSaver {

    /** root directory of wherever the java file is run*/
    private final String HOME = System.getProperty("user.dir");
    private Path dukeInfoLocation;
    private Path dukeInfoFolder;
    private List<String> prevInfo;





    public FileSaver() {
        dukeInfoLocation = Paths.get(HOME, "data","duke.txt");
        dukeInfoFolder = Paths.get(HOME, "data");

    }

    /**
     * Saves the information stored for the session into a txt file
     *
     * @param destination Mylist data type containing the records of events done earlier
     */
    public void saveData(MyList destination) {

        try {

            if (!Files.exists(dukeInfoFolder)) {
                    Files.createDirectory(dukeInfoFolder);
            }
            //Files.deleteIfExists(dukeInfoLocation);
            //dukeInfoLocation = Files.createFile(dukeInfoLocation);

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
                destination.addItem(activity);


            }
            System.out.println("Files Loaded\n");

        } catch (IOException e) {
            System.out.println("Error loading file to program");
        }



    }




    /**
     * returns the activity into a formatted string to be written to text file
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
            activityDate = ((Deadline) activity).getByDate();
        } else {
            type = "E";
            activityName = activity.getName();
            activityStatus = Boolean.toString(activity.getStatus());
            activityDate = ((Event) activity).getAtDate();
        }
        return type + " | " + activityStatus + " | " + activityName + " | " + activityDate;
    }


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

    }
}

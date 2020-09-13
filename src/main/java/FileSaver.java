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

    public void saveData(MyList destination) {

        try {

            if (!Files.exists(dukeInfoFolder)) {
                Files.createDirectory(dukeInfoFolder);
            }
            Files.deleteIfExists(dukeInfoLocation);
            dukeInfoLocation = Files.createFile(dukeInfoLocation);

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
}

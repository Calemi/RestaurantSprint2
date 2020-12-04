package profile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Registers default profiles.
 */
public class ProfileRegistry {

    private static final ProfileRegistry instance = new ProfileRegistry();

    /**
     * Used to get the single instance of the profile registry.
     */
    public static ProfileRegistry getInstance() {
        return instance;
    }

    /**
     * All of the currently registered profiles.
     */
    private final List<Profile> registeredProfiles = new ArrayList<>();

    private ProfileRegistry() {
        setupDefaultProfiles();
    }

    /**
     * Used to read and write from the profiles.txt file. The file is located in the user's appdata/roaming directory.
     */
    private void setupDefaultProfiles() {

        String path = System.getenv("APPDATA");
        String directoryName = path.concat("\\Profiles");
        String fileName = "profiles.txt";

        File directory = new File(directoryName);

        //Checks if the directory doesn't exists.
        if (!directory.exists()){
            //Create the directory.
            directory.mkdir();
        }

        File file = new File(directoryName + "\\" + fileName);

        try {

            //Checks if the file doesn't exists.
            if (!file.exists()) {

                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);

                //Writes all the default profiles to the file.
                bw.write("waitstaff1, password, 0, 1, 2, 3, 4, 5");
                bw.newLine();
                bw.write("waitstaff2, password, 10, 11, 12, 13, 14, 15");
                bw.newLine();
                bw.write("waitstaff3, password, 20, 21, 22, 23, 24, 25");

                bw.close();
            }

            FileReader fr = new FileReader(file.getAbsoluteFile());
            BufferedReader reader = new BufferedReader(fr);

            //Iterates until told otherwise.
            while(true) {

                //Gets the current line the file.
                String string = reader.readLine();

                //If the line is empty of null, stop reading.
                if (string == null || string.isEmpty()) {
                    break;
                }

                //Gets a list of strings separated by commas.
                String[] data = string.split(", ");

                int[] assignedTables = new int[data.length - 2];

                //Iterates through all of the numbers in the file and adds them to the assignedTables list.
                for (int i = 0; i < data.length; i++) {

                    if (i > 1) {
                        assignedTables[i - 2] = Integer.parseInt(data[i]);
                    }
                }

                //Adds the profile to registry.
                addProfile(new WaitStaffProfile(data[0], data[1], assignedTables));
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Used to register a profile
     */
    public void addProfile(Profile profile) {

        if (!registeredProfiles.contains(profile)) {
            registeredProfiles.add(profile);
        }
    }

    /**
     * Used to unregister a profile
     */
    public void removeProfile(Profile profile) {
        registeredProfiles.remove(profile);
    }

    /**
     * Used to find a profile by it's username and password.
     */
    public Profile findProfile(String username, String password) {

        for (Profile profile : registeredProfiles) {

            if (profile.getUsername().equals(username) && profile.getPassword().equals(password)) {
                return profile;
            }
        }

        return null;
    }
}

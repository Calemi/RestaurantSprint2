package profile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProfileRegistry {

    private static ProfileRegistry instance = new ProfileRegistry();

    public static ProfileRegistry getInstance() {
        return instance;
    }

    private List<Profile> registeredProfiles = new ArrayList<>();

    private ProfileRegistry() {

        String path = System.getenv("APPDATA");
        String directoryName = path.concat("\\Profiles");
        String fileName = "profiles.txt";

        File directory = new File(directoryName);

        if (!directory.exists()){
            directory.mkdir();
        }

        File file = new File(directoryName + "\\" + fileName);

        try {

            if (!file.exists()) {

                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);

                bw.write("waitstaff1, password, 0, 1, 2, 3, 4, 5");
                bw.newLine();
                bw.write("waitstaff2, password, 10, 11, 12, 13, 14, 15");
                bw.newLine();
                bw.write("waitstaff3, password, 20, 21, 22, 23, 24, 25");

                bw.close();
            }

            FileReader fr = new FileReader(file.getAbsoluteFile());
            BufferedReader reader = new BufferedReader(fr);

            while(true) {

                String string = reader.readLine();

                if (string == null || string.isEmpty()) {
                    break;
                }

                String[] data = string.split(", ");

                int[] assignedTables = new int[data.length - 2];

                for (int i = 0; i < data.length; i++) {

                    if (i > 1) {
                        assignedTables[i - 2] = Integer.parseInt(data[i]);
                    }
                }

                addProfile(new WaitStaffProfile(data[0], data[1], assignedTables));
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addProfile(Profile profile) {

        if (!registeredProfiles.contains(profile)) {
            registeredProfiles.add(profile);
        }
    }

    public void removeProfile(Profile profile) {
        registeredProfiles.remove(profile);
    }

    public Profile findProfile(String username, String password) {

        for (Profile profile : registeredProfiles) {

            if (profile.getUsername().equals(username) && profile.getPassword().equals(password)) {
                return profile;
            }
        }

        return null;
    }
}

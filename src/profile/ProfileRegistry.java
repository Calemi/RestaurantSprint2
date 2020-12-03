package profile;

import java.util.ArrayList;
import java.util.List;

public class ProfileRegistry {

    private static ProfileRegistry instance = new ProfileRegistry();

    public static ProfileRegistry getInstance() {
        return instance;
    }

    private List<Profile> registeredProfiles = new ArrayList<>();

    private ProfileRegistry() {

        if (registeredProfiles.isEmpty()) {
            addProfile(new WaitStaffProfile("waitstaff1", "password", 0, 1, 2, 3));
            addProfile(new WaitStaffProfile("waitstaff2", "password", 10, 11, 12, 13));
            addProfile(new WaitStaffProfile("waitstaff3", "password", 21, 21, 23, 24));
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

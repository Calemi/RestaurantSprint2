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
        if (registeredProfiles.isEmpty()) addProfile(new ManagerProfile("admin", "password"));
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

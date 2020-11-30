package profile;

public class ActiveProfile {

    private static Profile activeProfile;

    public static Profile getActiveProfile() {
        return activeProfile;
    }

    public static void setActiveProfile(Profile profile) {
        activeProfile = profile;
    }
}

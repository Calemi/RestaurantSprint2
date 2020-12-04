package profile;

public class ActiveProfile {

    private static Profile activeProfile;

    /**
     * Gets the active profile that's logged in.
     */
    public static Profile getActiveProfile() {
        return activeProfile;
    }

    /**
     * Sets the active profile that's logged in.
     */
    public static void setActiveProfile(Profile profile) {
        activeProfile = profile;
    }
}

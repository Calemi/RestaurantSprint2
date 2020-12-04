package profile;

/**
 * Used to check user's credentials when logging in.
 */
public class AuthenticationManager {

    /**
     * Used to check if a user is logged in.
     */
    public static boolean isLoggedIn() {
        return ActiveProfile.getActiveProfile() != null;
    }

    /**
     * Used to check the user's credentials, then log them in if they're correct.
     */
    public static boolean logIn (String username, String password) {

        //Checks if the user is already logged in & the username and password fields are not empty.
        if (!isLoggedIn() && !username.isEmpty() && !password.isEmpty()) {

            //Finds a profile with the given credentials.
            Profile profile = ProfileRegistry.getInstance().findProfile(username, password);

            //If a profile exists, set the active profile.
            if (profile != null) {
                ActiveProfile.setActiveProfile(profile);
                return true;
            }
        }

        return false;
    }

    /**
     * Used to log the active profile out.
     */
    public static void logOut () {
        ActiveProfile.setActiveProfile(null);
    }
}

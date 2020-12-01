package profile;

public class AuthenticationManager {

    public static boolean isLoggedIn() {
        return ActiveProfile.getActiveProfile() != null;
    }

    public static boolean logIn (String username, String password) {

        if (!isLoggedIn() && !username.isEmpty() && !password.isEmpty()) {

            Profile profile = ProfileRegistry.getInstance().findProfile(username, password);

            if (profile != null) {
                ActiveProfile.setActiveProfile(profile);

                return true;
            }
        }

        return false;
    }

    public static void logOut () {
        ActiveProfile.setActiveProfile(null);
    }
}

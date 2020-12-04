package profile;

import java.util.Objects;

/**
 * The basic profile.
 */
public class Profile {

    private final String username;
    private final String password;

    public Profile(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Overrides the default equals method to only check the username and password.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(username, profile.username) && Objects.equals(password, profile.password);
    }
}

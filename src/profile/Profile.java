package profile;

import java.util.Objects;

public class Profile {

    private String username;
    private String password;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(username, profile.username) && Objects.equals(password, profile.password);
    }
}

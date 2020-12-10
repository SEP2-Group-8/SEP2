package dk.via.sep.shared.transfer;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class User implements Serializable {

    private int user_id;
    private boolean adminCon;
    private final String username;
    private final String email;
    private final String password;

    public User(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
        adminCon = false;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setAdminCon(boolean adminCon) {
        this.adminCon = adminCon;
    }

    public boolean getAdminCon() {
        return adminCon;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public String toString() {
        return "User:" + username + "\n email:" + email + "\n is admin: " + adminCon;
    }
}

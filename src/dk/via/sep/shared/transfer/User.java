package dk.via.sep.shared.transfer;

import java.util.Objects;

public class User {
    private int user_id;

    private String username;
    private String email;
    private String password;
    public User(String email, String password, String username)
    {
        this.email=email;
        this.password=password;
        this.username=username;
    }
    public void setUser_id(int user_id)
    {
        this.user_id=user_id;
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
    @Override
    public boolean equals(Object obj)
    {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(password,user.password);
    }

    @Override
    public String toString()
    {
        return "User:" + username + "\n email:" + email;
    }
}

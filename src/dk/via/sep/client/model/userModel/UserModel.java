package dk.via.sep.client.model.userModel;

import dk.via.sep.shared.transfer.User;
import dk.via.sep.shared.utils.Subject;

import java.util.ArrayList;

public interface UserModel extends Subject {
    void login(String value, String value1);

    void register(String un, String pw, String e_mail);

    void logOut();

    ArrayList<User> getUserList();

    void deleteAccount();

    void editUserDetails(User user);
}

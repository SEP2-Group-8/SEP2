package dk.via.sep.client.model.userModel;

import dk.via.sep.shared.utils.Subject;

public interface UserModel extends Subject {
    void login(String value, String value1);

    void register(String un, String pw, String e_mail);

    void logOut();
}

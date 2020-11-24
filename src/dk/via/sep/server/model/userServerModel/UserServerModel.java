package dk.via.sep.server.model.userServerModel;

import dk.via.sep.shared.transfer.User;
import dk.via.sep.shared.utils.Subject;

public interface UserServerModel extends Subject {
    User login(String username, String password);

    void addUser(User user);
}

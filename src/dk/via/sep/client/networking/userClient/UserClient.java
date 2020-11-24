package dk.via.sep.client.networking.userClient;

import dk.via.sep.shared.transfer.User;
import dk.via.sep.shared.utils.Subject;

public interface UserClient extends Subject {
    void startClient();

    User loginUser(String username, String password);

    void registerAccount(User user);
}

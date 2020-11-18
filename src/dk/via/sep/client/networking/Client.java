package dk.via.sep.client.networking;

import dk.via.sep.shared.transfer.User;
import dk.via.sep.shared.utils.Subject;

public interface Client extends Subject {
    void startClient();
    void login(String username, String password);
    void register(User user);
}

package dk.via.sep.server.model.userList;

import dk.via.sep.shared.networking.userServerRemote.UserClientCallback;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LoggedUsers {
    private static LoggedUsers loggedUsers;
    private Map<UUID, UserClientCallback> activeUsers;

    private LoggedUsers() {
        activeUsers = new HashMap<>();
    }

    public static LoggedUsers getInstance() {
        //TODO add a lock
        if (loggedUsers == null) {
            loggedUsers = new LoggedUsers();
        }
        return loggedUsers;
    }

    public void addClient(UUID uuid, UserClientCallback client) {
        activeUsers.put(uuid, client);
    }

    public void removeClient(UUID uuid, UserClientCallback client) {
        activeUsers.remove(uuid, client);
    }

    public UserClientCallback getClient(UUID uuid) {
        return activeUsers.get(uuid);
    }

    public Map<UUID, UserClientCallback> getActiveUsers() {
        return activeUsers;
    }

    public void removeClient(UUID uuid) {
        System.out.println("Removing client "+uuid);
        activeUsers.remove(uuid);
    }
}

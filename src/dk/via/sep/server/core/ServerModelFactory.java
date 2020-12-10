package dk.via.sep.server.core;

import dk.via.sep.server.model.chatServerModel.ChatServerModel;
import dk.via.sep.server.model.chatServerModel.ChatServerModelManager;
import dk.via.sep.server.model.eventServerModel.EventServerModel;
import dk.via.sep.server.model.eventServerModel.EventServerModelManager;
import dk.via.sep.server.model.userServerModel.UserServerModel;
import dk.via.sep.server.model.userServerModel.UserServerModelManager;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ServerModelFactory {
    private final ServerDAOFactory DAOFactory;
    private UserServerModel userServerModel;
    private EventServerModel eventServerModel;
    private ChatServerModel chatServerModel;
    private final Lock lock;

    public ServerModelFactory(ServerDAOFactory DAOFactory) {
        this.DAOFactory = DAOFactory;
        lock = new ReentrantLock();
    }

    public UserServerModel getUserServerModel() {
        if (userServerModel == null) {
            synchronized (lock) {
                if (userServerModel == null)
                    userServerModel = new UserServerModelManager(DAOFactory.getUserDAO());
            }
        }
        return userServerModel;
    }

    public EventServerModel getEventServerModel() {
        if (eventServerModel == null) {
            synchronized (lock) {
                if (eventServerModel == null)
                    eventServerModel = new EventServerModelManager(DAOFactory.getEventDAO());
            }
        }
        return eventServerModel;
    }

    public ChatServerModel getChatServerModel(){
        if(chatServerModel == null){
            synchronized (lock){
                if(chatServerModel == null)
                    chatServerModel = new ChatServerModelManager();
            }
        }
        return chatServerModel;
    }

}

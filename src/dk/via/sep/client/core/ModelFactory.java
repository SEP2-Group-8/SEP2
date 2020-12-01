package dk.via.sep.client.core;

import dk.via.sep.client.model.eventModel.EventModel;
import dk.via.sep.client.model.eventModel.EventModelManager;
import dk.via.sep.client.model.userModel.UserModel;
import dk.via.sep.client.model.userModel.UserModelManager;

public class ModelFactory {

    private static ModelFactory modelFactory;
    private final UserModel userModel;
    private final EventModel eventModel;

    private ModelFactory() {
        userModel = new UserModelManager();
        eventModel = new EventModelManager();
    }

    public static ModelFactory getInstance() {
        if (modelFactory == null)
            modelFactory = new ModelFactory();
        return modelFactory;
    }

    public UserModel getUserModelManager() {
        return userModel;
    }

    public EventModel getEventModel() {
        return eventModel;
    }
}

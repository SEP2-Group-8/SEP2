package dk.via.sep.client.core;

import dk.via.sep.client.model.eventModel.EventModel;
import dk.via.sep.client.model.eventModel.EventModelManager;
import dk.via.sep.client.model.userModel.UserModel;
import dk.via.sep.client.model.userModel.UserModelManager;

/**
 * A factory for initializing and getting the model interfaces
 *
 * @version 1.0
 */
public class ModelFactory {

    private static ModelFactory modelFactory;
    private final UserModel userModel;
    private final EventModel eventModel;

    /**
     * Private constructor as this class is made with the singleton design pattern
     * Initializes the model interfaces with their respective implementations
     */
    private ModelFactory() {
        userModel = new UserModelManager();
        eventModel = new EventModelManager();
    }

    /**
     * A static getInstance method, needed to access the private fields contained in this method
     *
     * @return The model factory instance
     */
    public static ModelFactory getInstance() {
        if (modelFactory == null)
            modelFactory = new ModelFactory();
        return modelFactory;
    }

    /**
     * A getter for the user model interface
     *
     * @return The user model interface
     */
    public UserModel getUserModelManager() {
        return userModel;
    }


    /**
     * A getter for the event model interface
     *
     * @return The event model interface
     */
    public EventModel getEventModel() {
        return eventModel;
    }
}

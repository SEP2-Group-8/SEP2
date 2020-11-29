package dk.via.sep.client.core;

import dk.via.sep.client.model.eventModel.EventModel;
import dk.via.sep.client.model.eventModel.EventModelManager;
import dk.via.sep.client.model.userModel.UserModel;
import dk.via.sep.client.model.userModel.UserModelManager;

public class ModelFactory {

    private final ClientFactory cf;
    private UserModel userModel;
    private EventModel eventModel;

    public ModelFactory(ClientFactory cf) {
        this.cf = cf;
    }

    public UserModel getModelManager() {
        if (userModel == null)
            userModel = new UserModelManager(cf.getUserClient());
        return userModel;
    }

    public EventModel getEventModel(){
        if(eventModel == null)
            eventModel = new EventModelManager(cf.getEventClient());
        return eventModel;
    }
}

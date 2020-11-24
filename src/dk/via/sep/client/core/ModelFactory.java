package dk.via.sep.client.core;

import dk.via.sep.client.model.userModel.UserModel;
import dk.via.sep.client.model.userModel.UserModelManager;

public class ModelFactory {

    private final ClientFactory cf;
    private UserModel userModel;

    public ModelFactory(ClientFactory cf) {
        this.cf = cf;
    }

    public UserModel getModelManager() {
        if (userModel == null)
            userModel = new UserModelManager(cf.getUserClient());
        return userModel;
    }
}

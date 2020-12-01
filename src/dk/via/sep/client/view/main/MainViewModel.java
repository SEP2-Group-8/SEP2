package dk.via.sep.client.view.main;

import dk.via.sep.client.core.ModelFactory;
import dk.via.sep.client.model.userModel.UserModel;

public class MainViewModel {
    private final UserModel userModel;

    public MainViewModel() {
        this.userModel = ModelFactory.getInstance().getUserModelManager();
    }

    public void logOut() {
        userModel.logOut();
    }
}

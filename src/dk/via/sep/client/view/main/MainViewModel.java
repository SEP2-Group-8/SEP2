package dk.via.sep.client.view.main;

import dk.via.sep.client.model.userModel.UserModel;

public class MainViewModel {
    private final UserModel userModel;

    public MainViewModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public void logOut() {
        userModel.logOut();
    }
}

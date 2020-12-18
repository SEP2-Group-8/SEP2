package dk.via.sep.client.view.adminMain;

import dk.via.sep.client.core.ModelFactory;
import dk.via.sep.client.model.userModel.UserModel;

public class AdminMainViewModel {

    private final UserModel modelManager;

    public AdminMainViewModel() {
        this.modelManager = ModelFactory.getInstance().getUserModelManager();
    }

}

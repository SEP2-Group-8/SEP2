package dk.via.sep.client.view.main;

import dk.via.sep.client.core.ViewHandler;
import dk.via.sep.client.model.user.LoggedUser;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class MainViewController {

    private MainViewModel mainViewModel;
    private ViewHandler vh;

    @FXML
    private TextArea user;

    public void init(MainViewModel mainViewModel, ViewHandler vh) {
        this.mainViewModel = mainViewModel;
        this.vh = vh;

        //proof that it works
        user.setText(LoggedUser.getInstance().getUser().toString());
    }


}

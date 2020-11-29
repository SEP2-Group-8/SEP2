package dk.via.sep.client.view.main;

import dk.via.sep.client.core.ViewHandler;
import dk.via.sep.client.model.user.LoggedUser;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class MainViewController {

    private MainViewModel mainViewModel;
    private ViewHandler vh;
    @FXML
    private Button backButton;
    @FXML
    private TextArea user;

    public void init(MainViewModel mainViewModel, ViewHandler vh) {
        this.mainViewModel = mainViewModel;
        this.vh = vh;

        //proof that it works
        user.setText(LoggedUser.getInstance().getUser().toString());
    }

    public void logOut(){
        mainViewModel.logOut();
        Platform.runLater(() -> {
            try {
                vh.openView("Login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


}

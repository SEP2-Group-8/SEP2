package dk.via.sep.client.view.main;

import dk.via.sep.client.core.ViewHandler;
import dk.via.sep.client.core.ViewModelFactory;
import dk.via.sep.client.model.user.LoggedUser;
import dk.via.sep.client.view.ViewController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class MainViewController extends ViewController {

    private MainViewModel mainViewModel;
    private ViewHandler vh;
    @FXML
    private Button backButton;
    @FXML
    private TextArea user;

    public void init() {
        this.mainViewModel = ViewModelFactory.getInstance().getMainViewModel();
        this.vh = ViewHandler.getInstance();

        user.setText(LoggedUser.getInstance().getUser().toString());
    }

    public void logOut() {
        mainViewModel.logOut();
        Platform.runLater(() -> {
            vh.openLoginView();
        });
    }


}

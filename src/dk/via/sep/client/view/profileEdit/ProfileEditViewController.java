package dk.via.sep.client.view.profileEdit;

import com.jfoenix.controls.JFXTextField;
import dk.via.sep.client.core.ViewHandler;
import dk.via.sep.client.core.ViewModelFactory;
import dk.via.sep.client.view.ViewController;
import dk.via.sep.shared.utils.Clock;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.beans.PropertyChangeEvent;

public class ProfileEditViewController extends ViewController {
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField password;
    @FXML
    private JFXTextField email;
    //might remove this one
    @FXML
    private JFXTextField birthday;
    @FXML
    private Label clock;

    private ViewHandler viewHandler;
    private ProfileEditViewModel viewModel;

    public ProfileEditViewController() {
        viewHandler = ViewHandler.getInstance();
        viewModel = ViewModelFactory.getInstance().getProfileEditViewModel();
    }

    public void init() {
        name.textProperty().bindBidirectional(viewModel.nameLabelProperty());
        password.textProperty().bindBidirectional(viewModel.passwordLabelProperty());
        email.textProperty().bindBidirectional(viewModel.emailLabelProperty());
        birthday.textProperty().bindBidirectional(viewModel.birthdayLabelProperty());

        Clock clock = new Clock();
        Thread thread = new Thread(clock);
        thread.start();
        clock.addListener("Clock", this::updateClock);
    }

    public void updateAccount(){
        viewModel.saveChanges();
        viewHandler.openMainView();
    }

    private void updateClock(PropertyChangeEvent event) {
        String time = (String) event.getNewValue();
        Platform.runLater(() -> {
            clock.setText(time);
        });
    }

    public void exit() {
        System.exit(0);
    }

    public void minimize() {
        viewHandler.minimize();
    }

    public void back(){
        viewHandler.openMainView();
    }

}

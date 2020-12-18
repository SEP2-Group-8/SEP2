package dk.via.sep.client.view.profile;

import dk.via.sep.client.core.ViewHandler;
import dk.via.sep.client.core.ViewModelFactory;
import dk.via.sep.client.view.ViewController;
import dk.via.sep.shared.utils.Clock;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.beans.PropertyChangeEvent;

public class ProfileViewController extends ViewController {
    @FXML
    private AnchorPane currentPane;
    @FXML
    private Label nameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label birthdayLabel;
    @FXML
    private Label bigNameLabel;
    @FXML
    private Label clock;
    private ViewHandler viewHandler;
    private ProfileViewModel viewModel;

    public void init() {
        viewHandler = ViewHandler.getInstance();
        viewModel = ViewModelFactory.getInstance().getProfileViewModel();

        nameLabel.textProperty().bind(viewModel.getNameLabel());
        passwordLabel.textProperty().bind(viewModel.getPasswordLabel());
        emailLabel.textProperty().bind(viewModel.getEmailLabel());
        bigNameLabel.textProperty().bind(viewModel.getNameLabel());
        birthdayLabel.textProperty().bind(viewModel.getBirthdayLabel());
        Clock clock = new Clock();
        Thread thread = new Thread(clock);
        thread.start();
        clock.addListener("Clock", this::updateClock);
    }

    private void updateClock(PropertyChangeEvent propertyChangeEvent) {
        String time = (String) propertyChangeEvent.getNewValue();
        Platform.runLater(() -> {
            clock.setText(time);
        });
    }

    public void deleteAccount() {
        int reply = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to delete your account? This action cannot be undone",
                "Delete account",
                JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            viewModel.deleteAccount();
            viewHandler.openLoginView();
        } else {
            JOptionPane.showMessageDialog(null, "Operation aborted");
        }
    }

    public void editAccount() {
        viewHandler.openProfileEditView(null);
    }


}

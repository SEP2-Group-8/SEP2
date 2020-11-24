package dk.via.sep.client.view.register;

import dk.via.sep.client.core.ViewHandler;
import dk.via.sep.shared.utils.UserAction;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.beans.PropertyChangeEvent;
import java.io.IOException;

public class RegisterViewController {

    @FXML
    private Label errorLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField confPasswordField;
    @FXML
    private TextField emailField;
    @FXML
    private RegisterViewModel vm;
    private ViewHandler vh;

    public void init(RegisterViewModel vm, ViewHandler vh) {
        this.vm = vm;
        this.vh = vh;
        usernameField.textProperty().bindBidirectional(vm.usernameProperty());
        passwordField.textProperty().bindBidirectional(vm.passwordProperty());
        confPasswordField.textProperty().bindBidirectional(vm.confPasswordProperty());
        emailField.textProperty().bindBidirectional(vm.emailProperty());
        errorLabel.textProperty().bind(vm.errorProperty());
        vm.addListener(UserAction.REGISTER_SUCCESS.toString(), this::registerSuccess);
    }

    private void registerSuccess(PropertyChangeEvent evt) {
        onBackButton();
    }

    public void onBackButton() {
        Platform.runLater(() -> {
            try {
                vh.openView("Login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void onRegisterButton(ActionEvent actionEvent) {
        vm.registerUser();
    }

}

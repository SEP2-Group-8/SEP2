package dk.via.sep.client.view.register;

import dk.via.sep.client.core.ViewHandler;
import dk.via.sep.client.core.ViewModelFactory;
import dk.via.sep.client.view.ViewController;
import dk.via.sep.shared.utils.UserAction;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.beans.PropertyChangeEvent;

public class RegisterViewController extends ViewController {

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

    public void init() {
        this.vm = ViewModelFactory.getInstance().getRegisterViewModel();
        this.vh = ViewHandler.getInstance();
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
            vm.clear();
            vh.openLoginView();
        });
    }

    public void onRegisterButton(ActionEvent actionEvent) {
        vm.registerUser();
    }

}

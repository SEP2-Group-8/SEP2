package dk.via.sep.client.view.login;

import dk.via.sep.client.core.ViewHandler;
import dk.via.sep.client.core.ViewModelFactory;
import dk.via.sep.client.model.user.LoggedUser;
import dk.via.sep.client.view.ViewController;

import dk.via.sep.shared.utils.UserAction;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.beans.PropertyChangeEvent;
import java.io.IOException;

public class LoginViewController extends ViewController {
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Label errorLoginLabel;
    @FXML
    private AnchorPane currentPane;

    private LoginViewModel loginViewModel;
    private ViewHandler vh;


    public void init() {
        this.loginViewModel = ViewModelFactory.getInstance().getLoginViewModel();
        this.vh = ViewHandler.getInstance();
        loginViewModel.addListener(UserAction.LOGIN_SUCCESS.toString(), this::openMain);
        loginViewModel.addListener(UserAction.LOGIN_SUCCESS_ADMIN.toString(), this::openAdminView);
        usernameTextField.textProperty().bindBidirectional(this.loginViewModel.getUsernameProperty());
        passwordTextField.textProperty().bindBidirectional(this.loginViewModel.getPasswordProperty());
        errorLoginLabel.textProperty().bindBidirectional(this.loginViewModel.getErrorProperty());
    }

    private void openAdminView(PropertyChangeEvent propertyChangeEvent) {
        Platform.runLater(() -> {
            vh.openMainAdminView();
        });
    }

    private void openMain(PropertyChangeEvent propertyChangeEvent) {
        Platform.runLater(() -> {
//            if (LoggedUser.getInstance().getUser().getAdminCon())
//                vh.openMainAdminView();
//            else vh.openMainView();
            vh.openMainView();
        });
    }

    public void onLoginButton() throws IOException {
        loginViewModel.login();
    }

    public void onRegisterButton() throws IOException {
        vh.openRegisterView(currentPane);
    }

    public void exit(){
        System.exit(0);
    }

    public void minimize(){
        vh.minimize();
    }
}

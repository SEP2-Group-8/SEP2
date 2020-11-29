package dk.via.sep.client.view.login;

import dk.via.sep.client.core.ViewHandler;
import dk.via.sep.client.model.user.LoggedUser;
import dk.via.sep.shared.utils.UserAction;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.beans.PropertyChangeEvent;
import java.io.IOException;

public class LoginViewController {

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Label errorLoginLabel;

    private LoginViewModel loginViewModel;
    private ViewHandler vh;

    public void init(LoginViewModel loginViewModel, ViewHandler vh) {
        this.loginViewModel = loginViewModel;
        this.vh = vh;

        loginViewModel.addListener(UserAction.LOGIN_SUCCESS.toString(), this::openMain);
        loginViewModel.addListener(UserAction.LOGIN_SUCCESS_ADMIN.toString(), this::openAdminView);
        usernameTextField.textProperty().bindBidirectional(this.loginViewModel.getUsernameProperty());
        passwordTextField.textProperty().bindBidirectional(this.loginViewModel.getPasswordProperty());
        errorLoginLabel.textProperty().bindBidirectional(this.loginViewModel.getErrorProperty());
    }

    private void openAdminView(PropertyChangeEvent propertyChangeEvent) {
        Platform.runLater(()->{
            try{
                vh.openView("AdminMain");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void openMain(PropertyChangeEvent propertyChangeEvent) {
        Platform.runLater(() -> {
            try {
                if (LoggedUser.getInstance().getUser().getAdminCon())
                    vh.openView("AdminMain");
                else vh.openView("UserMainView");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void onLoginButton() throws IOException {
        loginViewModel.login();
    }

    public void onRegisterButton() throws IOException {
        vh.openView("Register");
    }
}

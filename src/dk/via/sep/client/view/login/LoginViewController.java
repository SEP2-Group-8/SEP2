package dk.via.sep.client.view.login;

import dk.via.sep.client.core.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginViewController {

  @FXML private TextField usernameTextField;
  @FXML private TextField passwordTextField;
  @FXML private Label errorLoginLabel;

  private LoginViewModel loginViewModel;
  private ViewHandler vh;

  public void init(LoginViewModel loginViewModel, ViewHandler vh) {
    this.loginViewModel = loginViewModel;
    this.vh = vh;
    // viewModel.addListener(UserAction.LOGIN_SUCCESS.toString(), this::openMain);

    usernameTextField.textProperty().bindBidirectional(this.loginViewModel.getUsernameProperty());
    passwordTextField.textProperty().bindBidirectional(this.loginViewModel.getPasswordProperty());
    errorLoginLabel.textProperty().bindBidirectional(this.loginViewModel.getErrorProperty());
  }

  public void onLoginButton() throws IOException {
    loginViewModel.login();
    vh.openView("Main");
  }

  public void onRegisterButton() throws IOException {
    vh.openView("Register");
  }
}

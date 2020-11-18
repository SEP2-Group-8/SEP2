package dk.via.sep.client.view;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import dk.via.sep.client.core.ViewHandler;
import dk.via.sep.client.core.ViewModelFactory;


public class RegisterController
{
  @FXML
  private Label createResultLabel;
  @FXML
  private TextField passwordAgainTextField;
  @FXML
  private TextField passwordTextField;
  @FXML
  private TextField usernameTextField;

  private RegisterVM RegisterVM;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, ViewModelFactory vmf) {

    this.viewHandler = viewHandler;

  }
}

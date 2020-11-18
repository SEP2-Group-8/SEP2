package dk.via.sep.client.view.login;

import dk.via.sep.client.model.ModelInterface;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeSupport;

public class LoginViewModel {
  private final ModelInterface modelInterface;
  private StringProperty username;
  private StringProperty password;
  private StringProperty error;
  private PropertyChangeSupport support;

  public LoginViewModel(ModelInterface modelInterface) {
    this.modelInterface = modelInterface;
    username = new SimpleStringProperty();
    password = new SimpleStringProperty();
    error = new SimpleStringProperty();
    error.setValue("");
    support = new PropertyChangeSupport(this);

    //this.modelInterface.addListener(UserAction.LOGIN_SUCCESS.toString(), this::onLoginOK);
    //this.modelInterface.addListener(UserAction.LOGIN_FAILED.toString(), this::onLoginFail);
  }

  public void login() {
    modelInterface.login(username.getValue(), password.getValue());
  }

//  private void onLoginOK(PropertyChangeEvent event) {
//    support.firePropertyChange(UserAction.LOGIN_SUCCESS.toString(), null, null);
//  }
//
//  private void onLoginFail(PropertyChangeEvent event) {
//    Platform.runLater(() ->{
//      error.setValue("Username already taken.");
//    });
//    System.out.println("failed to login");
//  }

  public StringProperty getUsernameProperty() {
    return username;
  }

  public StringProperty getPasswordProperty() {
    return password;
  }

  public StringProperty getErrorProperty() {
    return error;
  }
}

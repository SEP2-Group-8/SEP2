package dk.via.sep.client.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;

public class RegisterVM
{
  private StringProperty username = new SimpleStringProperty();
  private StringProperty email = new SimpleStringProperty();
  private StringProperty password = new SimpleStringProperty();
  private StringProperty passwordAgain = new SimpleStringProperty();

  public RegisterVM()
  {

  }

  public StringProperty usernameProperty() {
    return username;
  }

  public StringProperty emailProperty() {
    return email;
  }
  public StringProperty passwordProperty() {
    return password;
  }

  public StringProperty passwordAgainProperty() {
    return passwordAgain;
  }


}

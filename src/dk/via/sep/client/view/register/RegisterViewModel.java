package dk.via.sep.client.view.register;

import dk.via.sep.client.model.ModelInterface;
import dk.via.sep.shared.utils.EventType;
import dk.via.sep.shared.utils.Subject;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RegisterViewModel implements Subject {

    private StringProperty username;
    private StringProperty password;
    private StringProperty email;
    private StringProperty confPassword;
    private StringProperty error;
    private ModelInterface model;
    private PropertyChangeSupport support;

    public RegisterViewModel(ModelInterface model)
    {
        this.model=model;
        model.addPropertyListener(EventType.REGISTER_REQUEST.toString(), this::onRegisterResponse);
        username= new SimpleStringProperty();
        password= new SimpleStringProperty();
        email= new SimpleStringProperty();
        confPassword= new SimpleStringProperty();
        error= new SimpleStringProperty();
        support = new PropertyChangeSupport(this);
    }

    private void onRegisterResponse(PropertyChangeEvent evt)
    {
        String result = (String) evt.getNewValue();
        Platform.runLater(()->
        {
         error.set(result);
        });
    }

    public void registerUser() {
        String un = username.get();
        if(un == null || un.equals(""))
        {
            error.set("Username cannot be empty.");
            return;
        }
        String pw = password.get();
        if(pw == null)
        {
            error.set("Password cannot be empty.");
            return;
        }
        String e_mail = email.get();
        if(e_mail == null || e_mail.equals(""))
        {
            error.set("Email cannot be empty.");
            return;
        }
        String confPw = confPassword.get();
        if(confPw == null || confPw.equals(""))
        {
            error.set("Confirmed password cannot be empty.");
            return;
        }
        if(!confPw.equals(pw))
        {
            error.set("Confirmed password must be the same as the password.");
            return;
        }
        System.out.println(un+"\n"+pw+"\n"+e_mail);
        model.register(un,pw,e_mail);
    }


    public StringProperty usernameProperty() {
        return username;
    }


    public StringProperty passwordProperty() {
        return password;
    }


    public StringProperty emailProperty() {
        return email;
    }


    public StringProperty confPasswordProperty() {
        return confPassword;
    }

    public StringProperty errorProperty() {
        return error;
    }

    private void clear()
    {
        username.set("");
        password.set("");
        confPassword.set("");
        email.set("");

    }

    @Override
    public void addPropertyListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }
}

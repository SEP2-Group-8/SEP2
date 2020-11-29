package dk.via.sep.client.view.adminMain;

import dk.via.sep.client.model.userModel.UserModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AdminMainViewModel {

    private UserModel modelManager;

    private final StringProperty firstEventName;
    private final StringProperty firstEventDesc;
    private final StringProperty firstEventCreation;
    private final StringProperty firstEventCity;
    private final StringProperty firstEventDate;
    private final StringProperty secondEventName;
    private final StringProperty secondEventDesc;
    private final StringProperty secondEventCreation;
    private final StringProperty secondEventCity;
    private final StringProperty secondEventDate;
    private final StringProperty thirdEventName;
    private final StringProperty thirdEventDesc;
    private final StringProperty thirdEventCreation;
    private final StringProperty thirdEventCity;
    private final StringProperty thirdEventDate;


    public AdminMainViewModel(UserModel modelManager) {
        this.modelManager = modelManager;
        firstEventName= new SimpleStringProperty();
        firstEventDesc= new SimpleStringProperty();
        firstEventCreation= new SimpleStringProperty();
        firstEventCity= new SimpleStringProperty();
        firstEventDate= new SimpleStringProperty();
        secondEventName= new SimpleStringProperty();
        secondEventDesc= new SimpleStringProperty();
        secondEventCreation= new SimpleStringProperty();
        secondEventCity= new SimpleStringProperty();
        secondEventDate= new SimpleStringProperty();
        thirdEventName= new SimpleStringProperty();
        thirdEventDesc= new SimpleStringProperty();
        thirdEventCreation= new SimpleStringProperty();
        thirdEventCity= new SimpleStringProperty();
        thirdEventDate= new SimpleStringProperty();

    }


    public StringProperty firstEventNameProperty() {
        return firstEventName;
    }

    public StringProperty firstEventDescProperty() {
        return firstEventDesc;
    }


    public StringProperty firstEventCreationProperty() {
        return firstEventCreation;
    }

    public StringProperty firstEventCityProperty() {
        return firstEventCity;
    }

    public StringProperty firstEventDateProperty() {
        return firstEventDate;
    }

    public StringProperty secondEventNameProperty() {
        return secondEventName;
    }

    public StringProperty secondEventDescProperty() {
        return secondEventDesc;
    }

    public StringProperty secondEventCreationProperty() {
        return secondEventCreation;
    }

    public StringProperty secondEventCityProperty() {
        return secondEventCity;
    }

    public StringProperty secondEventDateProperty() {
        return secondEventDate;
    }

    public StringProperty thirdEventNameProperty() {
        return thirdEventName;
    }

    public StringProperty thirdEventDescProperty() {
        return thirdEventDesc;
    }

    public StringProperty thirdEventCreationProperty() {
        return thirdEventCreation;
    }

    public StringProperty thirdEventCityProperty() {
        return thirdEventCity;
    }


    public StringProperty thirdEventDateProperty() {
        return thirdEventDate;
    }

}

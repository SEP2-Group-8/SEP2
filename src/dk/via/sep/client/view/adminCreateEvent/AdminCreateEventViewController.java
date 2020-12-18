package dk.via.sep.client.view.adminCreateEvent;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import dk.via.sep.client.core.ViewHandler;
import dk.via.sep.client.core.ViewModelFactory;
import dk.via.sep.client.view.ViewController;
import dk.via.sep.shared.utils.UserAction;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.beans.PropertyChangeEvent;

public class AdminCreateEventViewController extends ViewController {
    private final ViewHandler viewHandler;
    private final AdminCreateEventViewModel adminCreateEventViewModel;
    @FXML
    private JFXTextField eventName;
    @FXML
    private JFXTextField eventLocation;
    @FXML
    private JFXTextField eventDescription;
    @FXML
    private JFXDatePicker eventDate;
    @FXML
    private JFXTimePicker eventTime;
    @FXML
    private JFXTextField departingLocation;
    @FXML
    private JFXTimePicker departingLeaveTime;
    @FXML
    private JFXTimePicker departingArriveTime;
    @FXML
    private JFXTimePicker arrivingArriveTime;
    @FXML
    private JFXTimePicker arrivingDepartTime;
    @FXML
    private TextField busSeats;

    public AdminCreateEventViewController() {
        viewHandler = ViewHandler.getInstance();
        adminCreateEventViewModel = ViewModelFactory.getInstance().getAdminCreateEventViewModel();
        adminCreateEventViewModel.addListener(UserAction.EVENT_CREATE_SUCCESS.toString(), this::createEventSuccess);
    }

    private void createEventSuccess(PropertyChangeEvent propertyChangeEvent) {
        adminCreateEventViewModel.clear();
        Platform.runLater(this::goBack);
    }

    public void init() {
        eventName.textProperty().bindBidirectional(adminCreateEventViewModel.eventNameProperty());
        eventDate.valueProperty().bindBidirectional(adminCreateEventViewModel.eventDateProperty());
        eventTime.valueProperty().bindBidirectional(adminCreateEventViewModel.eventTimeProperty());
        eventLocation.textProperty().bindBidirectional(adminCreateEventViewModel.eventLocationProperty());
        eventDescription.textProperty().bindBidirectional(adminCreateEventViewModel.eventDescriptionProperty());
        busSeats.textProperty().bindBidirectional(adminCreateEventViewModel.busSeatsProperty());
        departingLocation.textProperty().bindBidirectional(adminCreateEventViewModel.busDepartLocationProperty());
        departingLeaveTime.valueProperty().bindBidirectional(adminCreateEventViewModel.busDepartLocationStartTimeProperty());
        departingArriveTime.valueProperty().bindBidirectional(adminCreateEventViewModel.busDepartLocationEndTimeProperty());
        arrivingArriveTime.valueProperty().bindBidirectional(adminCreateEventViewModel.busArriveLocationStartTimeProperty());
        arrivingDepartTime.valueProperty().bindBidirectional(adminCreateEventViewModel.busArriveLocationEndTimeProperty());

    }

    public void createEvent() {
        adminCreateEventViewModel.createEvent();
        viewHandler.openMainView();
    }

    public void goBack() {
        viewHandler.openMainView();
    }

    public void minimize() {
        viewHandler.minimize();
    }

    public void exit() {
        System.exit(0);
    }
}

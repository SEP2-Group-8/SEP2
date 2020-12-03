package dk.via.sep.client.view.adminCreateEvent;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import dk.via.sep.client.core.ViewHandler;
import dk.via.sep.client.core.ViewModelFactory;
import dk.via.sep.client.view.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AdminCreateEventViewController extends ViewController {
    private ViewHandler viewHandler;
    private AdminCreateEventViewModel adminCreateEventViewModel;
    @FXML
    private TextField eventName;
    @FXML
    private TextField eventLocation;
    @FXML
    private TextField eventDescription;
    @FXML
    private JFXDatePicker eventDate;
    @FXML
    private JFXTimePicker eventTime;
    @FXML
    private TextField departingLocation;
    @FXML
    private TextField departingLeaveTime;
    @FXML
    private TextField departingArriveTime;
    @FXML
    private TextField arrivingArriveTime;
    @FXML
    private TextField arrivingDepartTime;
    @FXML
    private CheckBox isBus;
    @FXML
    private TextField busSeats;

    public AdminCreateEventViewController() {
        viewHandler = ViewHandler.getInstance();
        adminCreateEventViewModel = ViewModelFactory.getInstance().getAdminCreateEventViewModel();
    }

    public void init() {
        eventName.textProperty().bindBidirectional(adminCreateEventViewModel.eventNameProperty());
        //eventDate.accessibleTextProperty().bindBidirectional(adminCreateEventViewModel.eventDateProperty());
        //eventDate.toString();
        eventLocation.textProperty().bindBidirectional(adminCreateEventViewModel.eventLocationProperty());
        eventDescription.textProperty().bindBidirectional(adminCreateEventViewModel.eventDescriptionProperty());
        busSeats.textProperty().bindBidirectional(adminCreateEventViewModel.busSeatsProperty());
        departingLocation.textProperty().bindBidirectional(adminCreateEventViewModel.busDepartLocationProperty());
        departingLeaveTime.textProperty().bindBidirectional(adminCreateEventViewModel.busDepartLocationStartTimeProperty());
        departingArriveTime.textProperty().bindBidirectional(adminCreateEventViewModel.busDepartLocationEndTimeProperty());
        arrivingArriveTime.textProperty().bindBidirectional(adminCreateEventViewModel.busArriveLocationStartTimeProperty());
        arrivingDepartTime.textProperty().bindBidirectional(adminCreateEventViewModel.busArriveLocationEndTimeProperty());
    }

    public void createEvent() {
        adminCreateEventViewModel.createEvent();
        //System.out.println(eventDate.getValue().toString());
        //eventDate.getValue().get
        System.out.println("Date and time "+eventDate.getValue().toString() + eventTime.getValue().toString());


    }

    public void goBack() {
        viewHandler.openMainView();
    }
}

package dk.via.sep.client.view.adminEditEvent;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import dk.via.sep.client.core.ViewHandler;
import dk.via.sep.client.core.ViewModelFactory;
import dk.via.sep.client.view.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AdminEditEventViewController extends ViewController {

    @FXML
    private JFXTextField eventName;
    @FXML
    private JFXDatePicker eventDate;
    @FXML
    private JFXTextField eventDescription;
    @FXML
    private JFXTextField busID;
    @FXML
    private JFXTextField busDepartLocation;
    @FXML
    private JFXTextField busArriveLocation;
    @FXML
    private JFXTimePicker busDepartLocationStartTime;
    @FXML
    private JFXTimePicker busDepartLocationEndTime;
    @FXML
    private JFXTimePicker busArriveLocationStartTime;
    @FXML
    private JFXTimePicker busArriveLocationEndTime;
    @FXML
    private JFXTextField busSeats;
    @FXML
    private JFXTimePicker eventTime;
    @FXML
    private AnchorPane currentPane;

    private ViewHandler viewHandler;
    private AdminEditEventViewModel adminEditEventViewModel;

    public AdminEditEventViewController(){
        viewHandler = ViewHandler.getInstance();
        adminEditEventViewModel = ViewModelFactory.getInstance().getAdminEditEventViewModel();
    }

    public void init(){
        eventName.textProperty().bindBidirectional(adminEditEventViewModel.eventNameProperty());
        eventDate.accessibleTextProperty().bindBidirectional(adminEditEventViewModel.eventDateProperty());
        eventDescription.textProperty().bindBidirectional(adminEditEventViewModel.eventDescriptionProperty());
        eventTime.accessibleTextProperty().bindBidirectional(adminEditEventViewModel.eventTimeProperty());
        busID.textProperty().bindBidirectional(adminEditEventViewModel.busIDProperty());
        busDepartLocation.textProperty().bindBidirectional(adminEditEventViewModel.busDepartLocationProperty());
        busDepartLocationStartTime.accessibleTextProperty().bindBidirectional(adminEditEventViewModel.busDepartLocationStartTimeProperty());
        busDepartLocationEndTime.accessibleTextProperty().bindBidirectional(adminEditEventViewModel.busDepartLocationEndTimeProperty());
        busArriveLocation.textProperty().bindBidirectional(adminEditEventViewModel.busArriveLocationProperty());
        busArriveLocationStartTime.accessibleTextProperty().bindBidirectional(adminEditEventViewModel.busArriveLocationStartTimeProperty());
        busArriveLocationEndTime.accessibleTextProperty().bindBidirectional(adminEditEventViewModel.busArriveLocationEndTimeProperty());
        busSeats.textProperty().bindBidirectional(adminEditEventViewModel.busSeatsProperty());
    }

    public void saveChanges(){
        adminEditEventViewModel.saveEventChanges();
        viewHandler.openAdminEventDetailsView(null);
    }

    public void goBack(){
        viewHandler.openAdminEventDetailsView(null);
    }

    public void minimize(){
        viewHandler.minimize();
    }

    public void exit(){
        System.exit(0);
    }

}

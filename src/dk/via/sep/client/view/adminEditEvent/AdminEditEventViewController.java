package dk.via.sep.client.view.adminEditEvent;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import dk.via.sep.client.core.ViewHandler;
import dk.via.sep.client.core.ViewModelFactory;
import dk.via.sep.client.view.ViewController;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class AdminEditEventViewController extends ViewController {

    @FXML
    private JFXTextField eventName;
    @FXML
    private JFXDatePicker eventDate;
    @FXML
    private JFXTextField eventDescription;
    @FXML
    private JFXTextField busDepartLocation;
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
    private JFXTextField eventLocation;
    @FXML
    private AnchorPane currentPane;

    private final ViewHandler viewHandler;
    private final AdminEditEventViewModel adminEditEventViewModel;

    public AdminEditEventViewController() {
        viewHandler = ViewHandler.getInstance();
        adminEditEventViewModel = ViewModelFactory.getInstance().getAdminEditEventViewModel();
    }

    public void init() {
        eventLocation.textProperty().bindBidirectional(adminEditEventViewModel.eventLocationProperty());
        eventName.textProperty().bindBidirectional(adminEditEventViewModel.eventNameProperty());
        eventDate.valueProperty().bindBidirectional(adminEditEventViewModel.eventDateProperty());
        eventDescription.textProperty().bindBidirectional(adminEditEventViewModel.eventDescriptionProperty());
        eventTime.valueProperty().bindBidirectional(adminEditEventViewModel.eventTimeProperty());
        busDepartLocation.textProperty().bindBidirectional(adminEditEventViewModel.busDepartLocationProperty());
        busDepartLocationStartTime.valueProperty().bindBidirectional(adminEditEventViewModel.busDepartLocationStartTimeProperty());
        busDepartLocationEndTime.valueProperty().bindBidirectional(adminEditEventViewModel.busDepartLocationEndTimeProperty());
        busArriveLocationStartTime.valueProperty().bindBidirectional(adminEditEventViewModel.busArriveLocationStartTimeProperty());
        busArriveLocationEndTime.valueProperty().bindBidirectional(adminEditEventViewModel.busArriveLocationEndTimeProperty());
        busSeats.textProperty().bindBidirectional(adminEditEventViewModel.busSeatsProperty());
    }

    public void saveChanges() {
        adminEditEventViewModel.saveEventChanges();
        viewHandler.openMainView();
    }

    public void goBack() {
        viewHandler.openAdminEventDetailsView(null);
    }

    public void minimize() {
        viewHandler.minimize();
    }

    public void exit() {
        System.exit(0);
    }

}

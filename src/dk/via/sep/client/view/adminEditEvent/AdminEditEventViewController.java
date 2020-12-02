package dk.via.sep.client.view.adminEditEvent;

import dk.via.sep.client.core.ViewHandler;
import dk.via.sep.client.core.ViewModelFactory;
import dk.via.sep.client.view.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AdminEditEventViewController extends ViewController {

    @FXML
    private TextField eventName;
    @FXML
    private TextField eventDate;
    @FXML
    private Label eventDescription;
    @FXML
    private TextField busID;
    @FXML
    private TextField busDepartLocation;
    @FXML
    private TextField busArriveLocation;
    @FXML
    private TextField busDepartLocationStartTime;
    @FXML
    private TextField busDepartLocationEndTime;
    @FXML
    private TextField busArriveLocationStartTime;
    @FXML
    private TextField busArriveLocationEndTime;
    @FXML
    private TextField busSeats;
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
        eventDate.textProperty().bindBidirectional(adminEditEventViewModel.eventDateProperty());
        eventDescription.textProperty().bindBidirectional(adminEditEventViewModel.eventDescriptionProperty());
        busID.textProperty().bindBidirectional(adminEditEventViewModel.busIDProperty());
        busDepartLocation.textProperty().bindBidirectional(adminEditEventViewModel.busDepartLocationProperty());
        busDepartLocationStartTime.textProperty().bindBidirectional(adminEditEventViewModel.busDepartLocationStartTimeProperty());
        busDepartLocationEndTime.textProperty().bindBidirectional(adminEditEventViewModel.busDepartLocationEndTimeProperty());
        busArriveLocation.textProperty().bindBidirectional(adminEditEventViewModel.busArriveLocationProperty());
        busArriveLocationStartTime.textProperty().bindBidirectional(adminEditEventViewModel.busArriveLocationStartTimeProperty());
        busArriveLocationEndTime.textProperty().bindBidirectional(adminEditEventViewModel.busArriveLocationEndTimeProperty());
        busSeats.textProperty().bindBidirectional(adminEditEventViewModel.busSeatsProperty());
    }

    public void saveChanges(){
        adminEditEventViewModel.saveEventChanges();
        viewHandler.openAdminEventDetailsView(null);
    }

    public void goBack(){
        viewHandler.openAdminEventDetailsView(null);
        System.out.println("i think therefore i am");
    }



}

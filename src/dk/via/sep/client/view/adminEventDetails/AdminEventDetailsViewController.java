package dk.via.sep.client.view.adminEventDetails;

import dk.via.sep.client.core.ViewHandler;
import dk.via.sep.client.core.ViewModelFactory;
import dk.via.sep.client.model.user.LoggedUser;
import dk.via.sep.client.view.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javax.swing.*;

public class AdminEventDetailsViewController extends ViewController {
    @FXML
    private Label eventName;
    @FXML
    private Label eventDate;
    @FXML
    private Label eventDescription;
    @FXML
    private Label busID;
    @FXML
    private Label busDepartLocation;
    @FXML
    private Label busArriveLocation;
    @FXML
    private Label busDepartLocationStartTime;
    @FXML
    private Label busDepartLocationEndTime;
    @FXML
    private Label busArriveLocationStartTime;
    @FXML
    private Label busArriveLocationEndTime;
    @FXML
    private Label busSeats;
    @FXML
    private Button backButton;
    @FXML
    private Button chatButton;
    @FXML
    private Button searchUserButton;
    @FXML
    private AnchorPane currentPane;
    @FXML
    private VBox userListVBox;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;
    @FXML
    private Label eventTime;

    private ViewHandler viewHandler;
    private AdminEventDetailsViewModel adminEventDetailsViewModel;

    public AdminEventDetailsViewController() {
        viewHandler = ViewHandler.getInstance();
        adminEventDetailsViewModel = ViewModelFactory.getInstance().getAdminEventDetailsViewModel();
        adminEventDetailsViewModel.initView();
    }

    public void init() {
        eventName.textProperty().bind(adminEventDetailsViewModel.eventNameProperty());
        eventDate.textProperty().bind(adminEventDetailsViewModel.eventDateProperty());
        eventTime.textProperty().bind(adminEventDetailsViewModel.eventTimeProperty());
        eventDescription.textProperty().bind(adminEventDetailsViewModel.eventDescriptionProperty());
        busID.textProperty().bind(adminEventDetailsViewModel.busIDProperty());
        busSeats.textProperty().bind(adminEventDetailsViewModel.busSeatsProperty());
        busArriveLocation.textProperty().bind(adminEventDetailsViewModel.busArriveLocationProperty());
        busDepartLocation.textProperty().bind(adminEventDetailsViewModel.busDepartLocationProperty());
        busArriveLocationStartTime.textProperty().bind(adminEventDetailsViewModel.busArriveLocationStartTimeProperty());
        busArriveLocationEndTime.textProperty().bind(adminEventDetailsViewModel.busArriveLocationEndTimeProperty());
        busDepartLocationStartTime.textProperty().bind(adminEventDetailsViewModel.busDepartLocationStartTimeProperty());
        busDepartLocationEndTime.textProperty().bind(adminEventDetailsViewModel.busDepartLocationEndTimeProperty());
    }

    public void goBack() {
        viewHandler.openMainView();
    }

    public void openChat() {
        System.out.println("no chat for now");
    }

    public void searchUser() {
        System.out.println("no users for now");
    }

    public void deleteEvent() {
        int reply = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to delete this event? This action cannot be undone",
                "Delete event",
                JOptionPane.YES_NO_OPTION);
        if(reply == JOptionPane.YES_OPTION){
            adminEventDetailsViewModel.removeEvent(LoggedUser.getInstance().getSelectedEvent());
            LoggedUser.getInstance().setSelectedEvent(null);
            //how to remove from list???
            viewHandler.openMainView();
        } else{
            JOptionPane.showMessageDialog(null, "Operation aborted");
        }
    }

    public void editEvent() {
        viewHandler.openAdminEditEventView(null);
    }

    public void exit(){
        System.exit(0);
    }

    public void minimize(){
        viewHandler.minimize();
    }

}

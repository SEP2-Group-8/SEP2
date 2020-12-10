package dk.via.sep.client.view.userEventDetails;

import dk.via.sep.client.core.ViewHandler;
import dk.via.sep.client.core.ViewModelFactory;
import dk.via.sep.client.view.ViewController;
import dk.via.sep.shared.transfer.User;
import dk.via.sep.shared.utils.UserAction;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.beans.PropertyChangeEvent;

public class UserEventDetailsViewController extends ViewController {


    private ViewHandler viewHandler;
    private UserEventDetailsViewModel viewModel;

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
    private Button leaveButton;
    @FXML
    private Button joinButton;
    @FXML
    private Label eventTime;
    @FXML
    private CheckBox busCheckBox;

    /*TODO Check if the loggedInUser is already in the list for the event so the correct buttons show up(might replace visibility with disabled as the
    TODO positioning of the buttons looks bad with them only being visible or invisible.

  */
    public UserEventDetailsViewController()
    {
        viewHandler = ViewHandler.getInstance();
        viewModel = ViewModelFactory.getInstance().getUserEventDetailsViewModel();
    }

    public void init()
    {
        viewModel.getUserList();
        chatButton.visibleProperty().setValue(false);
        leaveButton.visibleProperty().setValue(false);
        viewModel.initView();
        eventName.textProperty().bind(viewModel.eventNameProperty());
        eventDate.textProperty().bind(viewModel.eventDateProperty());
        eventTime.textProperty().bind(viewModel.eventTimeProperty());
        eventDescription.textProperty().bind(viewModel.eventDescriptionProperty());
        busID.textProperty().bind(viewModel.busIDProperty());
        busSeats.textProperty().bind(viewModel.busSeatsProperty());
        busArriveLocation.textProperty().bind(viewModel.busArriveLocationProperty());
        busDepartLocation.textProperty().bind(viewModel.busDepartLocationProperty());
        busArriveLocationStartTime.textProperty().bind(viewModel.busArriveLocationStartTimeProperty());
        busArriveLocationEndTime.textProperty().bind(viewModel.busArriveLocationEndTimeProperty());
        busDepartLocationStartTime.textProperty().bind(viewModel.busDepartLocationStartTimeProperty());
        busDepartLocationEndTime.textProperty().bind(viewModel.busDepartLocationEndTimeProperty());
        busCheckBox.selectedProperty().bindBidirectional(viewModel.busCheckProperty());
        //viewModel.addListener(UserAction.EVENT_JOIN.toString(),this::addUserToList);
        //viewModel.addListener(UserAction.EVENT_JOIN_SUCCESS.toString(),this::userListUpdated);
        //viewModel.addListener(UserAction.EVENT_LEAVE_SUCCESS.toString(),this::userListUpdated);
    }

    private void userListUpdated(PropertyChangeEvent event) {
        Platform.runLater(() ->{
            userListVBox.getChildren().clear();
            viewModel.getUserList();
        });
    }

    private void addUserToList(PropertyChangeEvent event)
    {
        userListVBox.getChildren().add(createUserHBox((User) event.getNewValue()));
    }

    public HBox createUserHBox(User user)
    {
        HBox hBox = new HBox();
        return hBox;
    }

    public void openChat() {
        System.out.println("no chat for now");
    }

    public void exit(){
        System.exit(0);
    }

    public void minimize(){
        viewHandler.minimize();
    }

    public void goBack(ActionEvent actionEvent) { viewHandler.openMainView(); }

    public void leaveEvent(ActionEvent actionEvent) {
        viewModel.leaveEvent();
    }

    public void joinEvent(ActionEvent actionEvent) {
        viewModel.joinEvent();
    }
}

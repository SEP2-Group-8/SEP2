package dk.via.sep.client.view.adminMain;

import dk.via.sep.client.core.ViewHandler;
import dk.via.sep.client.core.ViewModelFactory;
import dk.via.sep.client.view.ViewController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AdminMainViewController extends ViewController {

    @FXML
    private Label firstEventNameLabel;
    @FXML
    private Label firstEventDescriptionLabel;
    @FXML
    private Label firstCreationTimeLabel;
    @FXML
    private Label firstCityLabel;
    @FXML
    private Label firstDateLabel;
    @FXML
    private Label secondEventLabel;
    @FXML
    private Label secondDescriptionLabel;
    @FXML
    private Label secondCreationLabel;
    @FXML
    private Label secondCityLabel;
    @FXML
    private Label secondDateLabel;
    @FXML
    private Label thirdEventLabel;
    @FXML
    private Label thirdDescriptionLabel;
    @FXML
    private Label thirdCreationLabel;
    @FXML
    private Label thirdCityLabel;
    @FXML
    private Label thirdDateLabel;


    private ViewHandler viewHandler;
    private AdminMainViewModel adminMainViewModel;

    public void init() {
        this.viewHandler = ViewHandler.getInstance();
        this.adminMainViewModel = ViewModelFactory.getInstance().getAdminMainViewModel();

        firstEventNameLabel.textProperty().bindBidirectional(adminMainViewModel.firstEventNameProperty());
        firstEventDescriptionLabel.textProperty().bindBidirectional(adminMainViewModel.firstEventDescProperty());
        firstCreationTimeLabel.textProperty().bindBidirectional(adminMainViewModel.firstEventCreationProperty());
        firstCityLabel.textProperty().bindBidirectional(adminMainViewModel.firstEventCityProperty());
        firstDateLabel.textProperty().bindBidirectional(adminMainViewModel.firstEventDateProperty());

        secondEventLabel.textProperty().bindBidirectional(adminMainViewModel.secondEventNameProperty());
        secondDescriptionLabel.textProperty().bindBidirectional(adminMainViewModel.secondEventDescProperty());
        secondCreationLabel.textProperty().bindBidirectional(adminMainViewModel.secondEventCreationProperty());
        secondCityLabel.textProperty().bindBidirectional(adminMainViewModel.secondEventCityProperty());
        secondDateLabel.textProperty().bindBidirectional(adminMainViewModel.secondEventDateProperty());

        thirdEventLabel.textProperty().bindBidirectional(adminMainViewModel.thirdEventNameProperty());
        thirdDescriptionLabel.textProperty().bindBidirectional(adminMainViewModel.thirdEventDescProperty());
        thirdCreationLabel.textProperty().bindBidirectional(adminMainViewModel.thirdEventCreationProperty());
        thirdCityLabel.textProperty().bindBidirectional(adminMainViewModel.thirdEventCityProperty());
        thirdDateLabel.textProperty().bindBidirectional(adminMainViewModel.thirdEventDateProperty());
    }


    public void onHomeButton(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            viewHandler.openMainAdminView();
        });
    }

    public void onProfileButton(ActionEvent actionEvent) {

    }

    public void onEventsButton(ActionEvent actionEvent) {

    }

    public void onChatButton(ActionEvent actionEvent) {

    }

    public void onUsersButton(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            viewHandler.openUserListView();
        });
    }

    public void onFirstEvent(ActionEvent actionEvent) {
    }

    public void onSecondEvent(ActionEvent actionEvent) {
    }

    public void onThirdEvent(ActionEvent actionEvent) {
    }
}

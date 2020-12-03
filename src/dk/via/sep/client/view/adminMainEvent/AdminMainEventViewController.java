package dk.via.sep.client.view.adminMainEvent;

import dk.via.sep.client.core.ViewHandler;
import dk.via.sep.client.core.ViewModelFactory;
import dk.via.sep.client.model.user.LoggedUser;
import dk.via.sep.client.view.ViewController;
import dk.via.sep.shared.transfer.Event;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.beans.PropertyChangeEvent;

public class AdminMainEventViewController extends ViewController {
    private ViewHandler viewHandler;
    private AdminMainEventViewModel adminMainEventViewModel;

    @FXML
    private VBox eventVBox;
    @FXML
    private Button addEvent;
    @FXML
    private AnchorPane currentPane;

    public AdminMainEventViewController() {
        viewHandler = ViewHandler.getInstance();
        adminMainEventViewModel = ViewModelFactory.getInstance().getAdminMainEventViewModel();
        adminMainEventViewModel.addListener("EventCreated", this::addEvent);
    }

    private void addEvent(PropertyChangeEvent event) {
        Platform.runLater(() -> {
            eventVBox.getChildren().add(createEventHBox((Event) event.getNewValue()));
        });
    }

    public void addEvent(){
        //adminMainEventViewModel.createEvent();
        viewHandler.openCreateEventView(null);
    }


    private HBox createEventHBox(Event event) {
        HBox hBox = new HBox();
        hBox.getStyleClass().add("admin-main-event");
        hBox.setAlignment(Pos.CENTER_LEFT);
        ImageView img1 = new ImageView(new Image(this.getClass().getResourceAsStream("../style/icons/star.png")));
        ImageView img2 = new ImageView(new Image(this.getClass().getResourceAsStream("../style/icons/man.png")));
        img1.setFitHeight(30);
        img1.setFitWidth(30);
        img2.setFitWidth(70);
        img2.setFitHeight(70);
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setPrefWidth(600);
        HBox innerHBox = new HBox();
        innerHBox.setSpacing(10);
        vBox.getStyleClass().add("admin-main-event-vbox");
        Label title = new Label();
        title.setText(event.getEventName());
        title.getStyleClass().add("admin-main-event-title");
        Label seats = new Label();
        seats.setText(String.valueOf(event.getBus().getNoSeats()));
        seats.getStyleClass().add("admin-main-event-description");
        Label date = new Label();
        date.setText(event.getDate().toString());
        date.getStyleClass().add("admin-main-event-date");
        Label location = new Label();
        location.setText(event.getLocation());
        location.getStyleClass().add("admin-main-event-description");

        innerHBox.getChildren().add(seats);
        innerHBox.getChildren().add(date);
        innerHBox.getChildren().add(location);

        vBox.getChildren().add(title);
        vBox.getChildren().add(innerHBox);

        hBox.getChildren().add(img1);
        hBox.getChildren().add(vBox);
        hBox.getChildren().add(img2);

        hBox.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            LoggedUser.getInstance().setSelectedEvent(event);
            viewHandler.openAdminEventDetailsView(null);
        });

        return hBox;
    }


}

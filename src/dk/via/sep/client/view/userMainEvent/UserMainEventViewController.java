package dk.via.sep.client.view.userMainEvent;

import dk.via.sep.client.core.ViewHandler;
import dk.via.sep.client.core.ViewModelFactory;
import dk.via.sep.client.model.user.LoggedUser;
import dk.via.sep.client.view.ViewController;
import dk.via.sep.shared.transfer.Event;
import dk.via.sep.shared.utils.UserAction;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public class UserMainEventViewController extends ViewController {

    private ViewHandler viewHandler;
    private UserMainEventViewModel viewModel;

    @FXML
    private VBox eventVBox;

    public UserMainEventViewController()
    {
        viewHandler = ViewHandler.getInstance();
        viewModel = ViewModelFactory.getInstance().getUserMainEventViewModel();
        viewModel.addListener(UserAction.EVENT_CREATE_SUCCESS.toString(),this::addEvent);
        viewModel.addListener(UserAction.EVENT_CREATE.toString(),this::addEvent);
        viewModel.addListener(UserAction.EVENT_REMOVE.toString(),this::UpdateEventList);
        viewModel.addListener(UserAction.EVENT_EDIT.toString(),this::UpdateEventList);
    }

    private void UpdateEventList(PropertyChangeEvent event) {
            Platform.runLater(() ->{
                eventVBox.getChildren().clear();
                viewModel.getEventList();
            });
    }

    public void init(){
        viewModel.getEventList();
    }

    private void addEvent(PropertyChangeEvent event) {

        Platform.runLater(()->{
            eventVBox.getChildren().clear();
            for (Event event1 : (ArrayList<Event>)event.getNewValue())
            eventVBox.getChildren().add(createEventHBox(event1));
        });
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
            viewHandler.openUserEventDetailsView(null);
        });

        return hBox;
    }
}

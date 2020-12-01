package dk.via.sep.client.view.userList;

import dk.via.sep.client.core.ViewHandler;
import dk.via.sep.client.core.ViewModelFactory;
import dk.via.sep.client.view.ViewController;
import dk.via.sep.shared.transfer.User;
import dk.via.sep.shared.utils.UserAction;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.beans.PropertyChangeEvent;

public class UserListViewController extends ViewController {

    @FXML
    private TableColumn<String, User> nameColumn;
    @FXML
    private TableColumn<String, User> emailColumn;
    @FXML
    private TableView<User> userTable;


    private ViewHandler vh;
    private UserListViewModel viewModel;

    public void init() {

        vh = ViewHandler.getInstance();
        this.viewModel = ViewModelFactory.getInstance().getUserListViewModel();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        userTable.setItems(viewModel.getUserList());
        viewModel.addListener(UserAction.USER_LIST.toString(), this::onUserListChange);

    }

    private void onUserListChange(PropertyChangeEvent evt) {
        userTable.setItems((ObservableList<User>) evt.getNewValue());
    }

    public void onHomeButton(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            vh.openMainAdminView();
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
            vh.openUserListView();
        });
    }
}

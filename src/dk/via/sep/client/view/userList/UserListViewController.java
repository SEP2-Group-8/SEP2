package dk.via.sep.client.view.userList;

import dk.via.sep.client.core.ViewHandler;
import dk.via.sep.client.view.adminMain.AdminMainViewModel;
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
import java.io.IOException;

public class UserListViewController {

    @FXML
    private TableColumn<String,User> nameColumn;
    @FXML
    private TableColumn<String,User> emailColumn;
    @FXML
    private TableView<User> userTable;


    private ViewHandler vh;
    private UserListViewModel viewModel;

    public void init(UserListViewModel viewModel, ViewHandler viewHandler) {

        vh=viewHandler;
        this.viewModel = viewModel;
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        userTable.setItems(viewModel.getUserList());
        viewModel.addListener(UserAction.USER_LIST.toString(),this::onUserListChange);

    }

    private void onUserListChange(PropertyChangeEvent evt) {
        userTable.setItems((ObservableList<User>) evt.getNewValue());
    }

    public void onHomeButton(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            try {
                vh.openView("AdminMain");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void onProfileButton(ActionEvent actionEvent) {
        /*Platform.runLater(() -> {
            try {
                vh.openView("AdminProfile");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });*/
    }

    public void onEventsButton(ActionEvent actionEvent) {
       /* Platform.runLater(() -> {
            try {
                vh.openView("AdminEvents");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });*/
    }

    public void onChatButton(ActionEvent actionEvent) {
       /* Platform.runLater(() -> {
            try {
                vh.openView("AdminChat");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });*/
    }

    public void onUsersButton(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            try {
                vh.openView("UserList");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}

package dk.via.sep.client.core;

import dk.via.sep.client.view.adminMain.AdminMainViewController;
import dk.via.sep.client.view.login.LoginViewController;
import dk.via.sep.client.view.main.MainViewController;
import dk.via.sep.client.view.register.RegisterViewController;
import dk.via.sep.client.view.userList.UserListViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler {
    private final ViewModelFactory vmf;
    private Stage stage;
    private Scene scene;

    public ViewHandler(ViewModelFactory vmf) {
        this.vmf = vmf;
    }

    public void start() throws IOException {
        stage = new Stage();
        openView("Login");
    }

    public void openView(String view) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;

        switch (view) {
            case "Login": {
                loader.setLocation(
                        getClass().getResource("../view/login/LoginView.fxml")
                );
                root = loader.load();
                LoginViewController loginViewController = loader.getController();
                loginViewController.init(vmf.getLoginViewModel(), this);
                stage.setTitle("Login");
                break;
            }
            case "Register": {
                loader.setLocation(
                        getClass().getResource("../view/register/RegisterView.fxml")
                );
                root = loader.load();
                RegisterViewController registerViewController = loader.getController();
                registerViewController.init(vmf.getRegisterViewModel(), this);
                stage.setTitle("Register");
                break;
            }
            case "Main": {
                loader.setLocation(
                        getClass().getResource("../view/main/MainView.fxml")
                );
                root = loader.load();
                MainViewController mainViewController = loader.getController();
                mainViewController.init(vmf.getMainViewModel(), this);
                stage.setTitle("VIA Football Club - Welcome");
                break;
            }
            case "AdminMain":{
                loader.setLocation(getClass().getResource("../view/adminMain/AdminMainView.fxml"));
                root = loader.load();
                AdminMainViewController mainAdminViewController = loader.getController();
                mainAdminViewController.init(vmf.getAdminMainViewModel(),this);
                stage.setTitle("VIA Football Club - Admin Home Page");
                break;
            }
            case "UserList": {
                loader.setLocation(getClass().getResource("../view/userList/UserListView.fxml"));
                root = loader.load();
                UserListViewController userListViewController = loader.getController();
                userListViewController.init(vmf.getUserListViewModel(), this);
                stage.setTitle("VIA Football Club - Admin User View Page");
                break;
            }
            case "AdminProfile":{
                loader.setLocation(getClass().getResource("../view/adminProfile/AdminProfileView.fxml"));
                root = loader.load();
                UserListViewController userListViewController = loader.getController();
                userListViewController.init(vmf.getUserListViewModel(), this);
                stage.setTitle("VIA Football Club - Admin Profile Page");
                break;
                }
            case "AdminEvents":{
                loader.setLocation(getClass().getResource("../view/adminEvents/AdminEventsView.fxml"));
                root = loader.load();
                UserListViewController userListViewController = loader.getController();
                userListViewController.init(vmf.getUserListViewModel(), this);
                stage.setTitle("VIA Football Club - Admin Events Page");
                break;
            }
            case "AdminChat":{
                loader.setLocation(getClass().getResource("../view/adminChat/AdminChatView.fxml"));
                root = loader.load();
                UserListViewController userListViewController = loader.getController();
                userListViewController.init(vmf.getUserListViewModel(), this);
                stage.setTitle("VIA Football Club - Admin Home Page");
                break;
            }
        }

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

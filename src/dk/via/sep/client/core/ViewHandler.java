package dk.via.sep.client.core;

import dk.via.sep.client.view.login.LoginViewController;
import dk.via.sep.client.view.main.MainViewController;
import dk.via.sep.client.view.register.RegisterViewController;
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
        }

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

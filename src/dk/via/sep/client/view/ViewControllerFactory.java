package dk.via.sep.client.view;

import dk.via.sep.shared.utils.Views;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ViewControllerFactory {

    private static final Map<Views, ViewController> viewControllers = new HashMap<>();

    public static ViewController getViewController(Views id) {
        ViewController viewController = viewControllers.get(id);

        if (viewController == null) {
            switch (id) {
                case LOGIN:
                    viewController = createNewViewController("login/LoginView.fxml");
                    break;
                case PROFILE:
                    viewController = createNewViewController("profile/ProfileView.fxml");
                    break;
                case MAIN:
                    viewController = createNewViewController("main/MainView.fxml");
                    break;
                case REGISTER:
                    viewController = createNewViewController("register/RegisterView.fxml");
                    break;
                case CHAT_MAIN:
                    viewController = createNewViewController("path3");
                    break;
                case USER_MAIN:
                    System.out.println("");
                    break;
                case ADMIN_MAIN:
                    viewController = createNewViewController("adminMain/AdminMainView.fxml");
                    break;
                case EVENTS_MAIN:
                    viewController = createNewViewController("path6");
                    break;
                case ADMIN_USERLIST:
                    viewController = createNewViewController("adminUserList/AdminUserListView.fxml");
                    break;
                case USERLIST:
                    viewController = createNewViewController("userList/UserListView.fxml");
                    break;
                case ADMIN_EVENTS_MAIN:
                    viewController = createNewViewController("adminMainEvent/AdminMainEventView.fxml");
                    break;
                case ADMIN_EVENT_DETAILS:
                    viewController = createNewViewController("adminEventDetails/AdminEventDetailsView.fxml");
                    break;
                case ADMIN_EDIT_EVENT:
                    viewController = createNewViewController("adminEditEvent/AdminEditEvent.fxml");
                    break;
                case ADMIN_CREATE_EVENT:
                    viewController = createNewViewController("adminCreateEvent/AdminCreateEventView.fxml");
                    break;
            }
            viewControllers.put(id, viewController);
        }
        viewController.init();
        return viewController;
    }

    private static ViewController createNewViewController(String path) {
        ViewController controller = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ViewControllerFactory.class.getResource(path));
            Region root = loader.load();
            controller = loader.getController();
            controller.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return controller;
    }

    public static void clearViews() {
        viewControllers.clear();
    }
}

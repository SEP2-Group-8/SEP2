package dk.via.sep.client.core;

import dk.via.sep.client.view.ViewController;
import dk.via.sep.client.view.ViewControllerFactory;
import dk.via.sep.shared.utils.Views;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * The class responsible for managing the views of the program, and switching between them. It contains
 * methods for each view, as well as implementing the singleton design pattern so it can be used throughout the program.
 *
 * @author Bogdan-Florin Cirstoiu
 * @version 2.1
 */
public class ViewHandler {
    private Stage stage;
    private Scene scene;
    private final String css;
    private final String font;
    private ViewController viewController;
    private static ViewHandler viewHandler;

    private double xOffset;
    private double yOffset;

    /**
     * Private no argument constructor that initializes the css and font paths, as well as the offset variables, used in a method below.
     */
    private ViewHandler() {
        xOffset = yOffset = 0;
        css = this.getClass().getResource("../view/style/style.css").toExternalForm();
        font = this.getClass().getResource("../view/style/fonts/Roboto-Light.ttf").toExternalForm();
    }

    /**
     * Static getInstance method, used to gain access to the instance of this class
     *
     * @return The instance of this class
     */
    public static ViewHandler getInstance() {
        if (viewHandler == null) {
            viewHandler = new ViewHandler();
        }
        return viewHandler;
    }

    /**
     * The start method, that initializes the stage and opens the login view.
     * It also gets rid of the default navigation bar, which has been replaced.
     */
    public void start() {
        stage = new Stage();
        if (stage.getScene() == null) stage.initStyle(StageStyle.TRANSPARENT);
        openLoginView();
    }

    /**
     * Method used to open the Login view
     */
    public void openLoginView() {
        ViewControllerFactory.clearViews();
        viewController = ViewControllerFactory.getViewController(Views.LOGIN);
        showView(viewController, null);
    }

    /**
     * Method used to open the register view
     *
     * @param pane the specified pane in which the window can open, can be set to null to open the view by itself, or
     *             can be specified so the view opens in a certain pane.
     */
    public void openRegisterView(Pane pane) {
        viewController = ViewControllerFactory.getViewController(Views.REGISTER);
        showView(viewController, pane);
    }

    /**
     * Method used to open the main view. This contains the side-bar.
     */
    public void openMainView() {
        viewController = ViewControllerFactory.getViewController(Views.MAIN);
        showView(viewController, null);
    }

    /**
     * Method used to open the main admin view.
     */
    public void openMainAdminView() {
        viewController = ViewControllerFactory.getViewController(Views.ADMIN_MAIN);
        showView(viewController, null);
    }

    /**
     * Method used to open the User list view.
     * The view is empty at the time of writing.
     */
    public void openUserListView() {
        viewController = ViewControllerFactory.getViewController(Views.ADMIN_USERLIST);
        showView(viewController, null);
    }

    /**
     * Method used to open the profile view.
     *
     * @param pane the specified pane in which the window can open, can be set to null to open the view by itself, or
     *             can be specified so the view opens in a certain pane.
     */
    public void openProfileView(Pane pane) {
        viewController = ViewControllerFactory.getViewController(Views.PROFILE);
        showView(viewController, pane);
    }

    /**
     * Method used to open the Admin edit events view.
     *
     * @param pane the specified pane in which the window can open, can be set to null to open the view by itself, or
     *             can be specified so the view opens in a certain pane.
     */
    public void openAdminEditEventView(Pane pane) {
        viewController = ViewControllerFactory.getViewController(Views.ADMIN_EDIT_EVENT);
        showView(viewController, pane);
    }

    /**
     * Method used to open the Admin event view.
     *
     * @param pane the specified pane in which the window can open, can be set to null to open the view by itself, or
     *             can be specified so the view opens in a certain pane.
     */
    public void openAdminEventView(Pane pane) {
        viewController = ViewControllerFactory.getViewController(Views.ADMIN_EVENTS_MAIN);
        showView(viewController, pane);
    }

    /**
     * Method used to open the Admin event details view.
     *
     * @param pane the specified pane in which the window can open, can be set to null to open the view by itself, or
     *             can be specified so the view opens in a certain pane.
     */
    public void openAdminEventDetailsView(Pane pane) {
        viewController = ViewControllerFactory.getViewController(Views.ADMIN_EVENT_DETAILS);
        showView(viewController, pane);
    }

    /**
     * Method used to open the create event view.
     *
     * @param pane the specified pane in which the window can open, can be set to null to open the view by itself, or
     *             can be specified so the view opens in a certain pane.
     */
    public void openCreateEventView(Pane pane) {
        viewController = ViewControllerFactory.getViewController(Views.ADMIN_CREATE_EVENT);
        showView(viewController, pane);
    }

    /**
     * Method used to open the user event view.
     *
     * @param pane the specified pane in which the window can open, can be set to null to open the view by itself, or
     *             can be specified so the view opens in a certain pane.
     */
    public void openUserEventView(Pane pane) {
        viewController = ViewControllerFactory.getViewController(Views.USER_EVENT_MAIN);
        showView(viewController, pane);
    }

    /**
     * Method used to open the User event details view.
     *
     * @param pane the specified pane in which the window can open, can be set to null to open the view by itself, or
     *             can be specified so the view opens in a certain pane.
     */
    public void openUserEventDetailsView(Pane pane) {
        viewController = ViewControllerFactory.getViewController(Views.USER_EVENT_DETAILS);
        showView(viewController, pane);
    }

    /**
     * Method used to open the profile edit view.
     *
     * @param pane the specified pane in which the window can open, can be set to null to open the view by itself, or
     *             can be specified so the view opens in a certain pane.
     */
    public void openProfileEditView(Pane pane) {
        viewController = ViewControllerFactory.getViewController(Views.PROFILE_EDIT);
        showView(viewController, pane);
    }

    /**
     * Method used to show the selected view. It takes the pane in which the view should open and the view controller as an argument,
     * and is only used in this class.
     * Other classes can use this method by calling one of the openView methods above.
     *
     * @param viewController The specified controller of the desired view.
     * @param pane           The specified pane in which the view should open. Can be set to null, so the view opens by itself.
     */
    private void showView(ViewController viewController, Pane pane) {
        Platform.runLater(() -> {
            //check if pane is null
            if (pane == null) {
                //adding the possibility of moving the window around by dragging it with your cursor
                moveWindowEvents(viewController.getRoot());

                //creating the scene and setting the stage
                if (scene == null) {
                    scene = new Scene(viewController.getRoot());
                }
                scene.setRoot(viewController.getRoot());

                //adding styling and font
                scene.getStylesheets().add(css);
                Font.loadFont(font, 16);

                if (stage == null) {
                    stage = new Stage();
                }
                stage.setScene(scene);
                Font.loadFonts(font, 16);
                stage.show();
            } else {
                //if there is a specified pane, the view will open in that pane
                pane.getChildren().clear();
                pane.getChildren().setAll(viewController.getRoot());
            }
        });
    }

    /**
     * A minimize method used to minimize the app to the taskbar.
     */
    public void minimize() {
        stage.setIconified(true);
    }

    /**
     * A reset views method, that cleans the view map from the controller factory.
     */
    public void resetViews() {
        ViewControllerFactory.clearViews();
    }

    /**
     * A method used to add the drag-window functionality to the app.
     *
     * @param root the scene root of the app
     */
    private void moveWindowEvents(Parent root) {
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
    }


}

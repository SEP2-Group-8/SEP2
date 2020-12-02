package dk.via.sep.client.core;

import dk.via.sep.client.view.ViewController;
import dk.via.sep.client.view.ViewControllerFactory;
import dk.via.sep.shared.utils.Views;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ViewHandler {
    private Stage stage;
    private Scene scene;
    private final String css;
    private final String font;
    private ViewController viewController;
    private static ViewHandler viewHandler;

    private double xOffset;
    private double yOffset;

    private ViewHandler() {
        xOffset = yOffset = 0;
        css = this.getClass().getResource("../view/style/style.css").toExternalForm();
        font = this.getClass().getResource("../view/style/fonts/Roboto-Light.ttf").toExternalForm();
    }

    public static ViewHandler getInstance() {
        if (viewHandler == null) {
            viewHandler = new ViewHandler();
        }
        return viewHandler;
    }

    public void start() {
        stage = new Stage();
        if (stage.getScene() == null) stage.initStyle(StageStyle.TRANSPARENT);
        openLoginView();
    }

    public void openLoginView() {
        ViewControllerFactory.clearViews();
        viewController = ViewControllerFactory.getViewController(Views.LOGIN);
        showView(viewController, null);
    }

    public void openRegisterView(Pane pane) {
        viewController = ViewControllerFactory.getViewController(Views.REGISTER);
        showView(viewController, pane);
    }

    public void openMainView() {
        viewController = ViewControllerFactory.getViewController(Views.MAIN);
        showView(viewController, null);
    }

    public void openMainAdminView() {
        viewController = ViewControllerFactory.getViewController(Views.ADMIN_MAIN);
        showView(viewController, null);
    }

    public void openUserListView() {
        viewController = ViewControllerFactory.getViewController(Views.ADMIN_USERLIST);
        showView(viewController, null);
    }

    public void openProfileView(Pane pane){
        viewController = ViewControllerFactory.getViewController(Views.PROFILE);
        showView(viewController, pane);
    }

    public void openAdminEditEventView(Pane pane){
        viewController = ViewControllerFactory.getViewController(Views.ADMIN_EDIT_EVENT);
        showView(viewController, pane);
    }

    public void openAdminEventView(Pane pane){
        viewController = ViewControllerFactory.getViewController(Views.ADMIN_EVENTS_MAIN);
        showView(viewController, pane);
    }

    public void openAdminEventDetailsView(Pane pane){
        viewController = ViewControllerFactory.getViewController(Views.ADMIN_EVENT_DETAILS);
        showView(viewController, pane);
    }

    private void showView(ViewController viewController, Pane pane) {
        Platform.runLater(() -> {
            if (pane == null) {
                moveWindowEvents(viewController.getRoot());
                if (scene == null) {
                    scene = new Scene(viewController.getRoot());
                }
                scene.setRoot(viewController.getRoot());
                scene.getStylesheets().add(css);
                Font.loadFont(font, 16);

                if (stage == null) {
                    stage = new Stage();
                }
                stage.setScene(scene);
                Font.loadFonts(font, 16);
                stage.show();
            } else {
                pane.getChildren().clear();
                pane.getChildren().setAll(viewController.getRoot());
            }
        });
    }

    public void minimize() {
        stage.setIconified(true);
    }

    public void resetViews() {
        ViewControllerFactory.clearViews();
    }

    private void moveWindowEvents(Parent root)
    {
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

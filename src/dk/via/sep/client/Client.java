package dk.via.sep.client;

import dk.via.sep.client.core.ViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;


public class Client extends Application {

    @Override
    public void start(Stage stage) {
        System.setProperty("java.security.policy", "security.policy");
        if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());

        ViewHandler viewHandler = ViewHandler.getInstance();
        viewHandler.start();
    }
}

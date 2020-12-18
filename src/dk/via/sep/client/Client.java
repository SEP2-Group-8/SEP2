package dk.via.sep.client;

import dk.via.sep.client.core.ViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Class responsible for starting the app.
 *
 * @author Group 8
 */
public class Client extends Application {

    /**
     * Start method that sets the stage and the security policy for the RMI implementation.
     *
     * @param stage the stage
     */
    @Override
    public void start(Stage stage) {
        System.setProperty("java.security.policy", "security.policy");
        if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());

        ViewHandler viewHandler = ViewHandler.getInstance();
        viewHandler.start();
    }
}

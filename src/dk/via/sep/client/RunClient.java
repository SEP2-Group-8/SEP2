package dk.via.sep.client;

import javafx.application.Application;

/**
 * Class to be run for the client to start. <b>Must start the server first, otherwise, a remote exception will be thrown.</b>
 */
public class RunClient {
    public static void main(String[] args) {
        Application.launch(Client.class);
    }
}

package dk.via.sep.client;

import dk.via.sep.client.core.ClientFactory;
import dk.via.sep.client.core.ModelFactory;
import dk.via.sep.client.core.ViewHandler;
import dk.via.sep.client.core.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class Client extends Application {

  @Override 
  public void start(Stage stage) throws Exception {
    ClientFactory clientFactory = new ClientFactory();
    ModelFactory modelFactory = new ModelFactory(clientFactory);
    ViewModelFactory viewModelFactory = new ViewModelFactory(modelFactory);
    ViewHandler viewHandler = new ViewHandler(viewModelFactory);
    viewHandler.start();
  }
}

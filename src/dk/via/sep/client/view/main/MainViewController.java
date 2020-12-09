package dk.via.sep.client.view.main;

import dk.via.sep.client.core.ViewHandler;
import dk.via.sep.client.core.ViewModelFactory;
import dk.via.sep.client.model.user.LoggedUser;
import dk.via.sep.client.view.ViewController;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class MainViewController extends ViewController {
    private ViewHandler viewHandler;
    private MainViewModel mainViewModel;
    @FXML
    private AnchorPane currentPane;

    public MainViewController(){
        viewHandler = ViewHandler.getInstance();
    }

    public void init() {
        this.mainViewModel = ViewModelFactory.getInstance().getMainViewModel();
        this.viewHandler = ViewHandler.getInstance();
    }

    public void logOut(){
        mainViewModel.logOut();
        viewHandler.openLoginView();
    }

    public void openHomeView(){
        viewHandler.openMainView();
    }

    public void exit(){
        mainViewModel.logOut();
        System.exit(0);
    }

    public void minimize(){
        viewHandler.minimize();
    }

    public void openProfileView(){
        viewHandler.openProfileView(currentPane);
    }

    public void openEventsView(){
        if(LoggedUser.getInstance().getUser().getAdminCon())
        viewHandler.openAdminEventView(currentPane);
        else viewHandler.openUserEventView(currentPane);
    }

    public void openChatView(){

    }
}

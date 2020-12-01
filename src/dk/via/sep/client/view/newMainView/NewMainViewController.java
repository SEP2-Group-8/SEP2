package dk.via.sep.client.view.newMainView;

import dk.via.sep.client.core.ViewHandler;
import dk.via.sep.client.view.ViewController;

public class NewMainViewController extends ViewController {
    private ViewHandler viewHandler;

    public NewMainViewController(){
        viewHandler = ViewHandler.getInstance();
    }

    public void logOut(){
        viewHandler.openLoginView();
    }

    public void openHomeView(){
        viewHandler.openMainAdminView();
    }

    public void exit(){
        System.exit(0);
    }

    public void minimize(){
        viewHandler.minimize();
    }

    public void openProfileView(){
        //
    }

    public void openEventsView(){
        //
    }


}

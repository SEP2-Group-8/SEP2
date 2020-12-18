package dk.via.sep.client.view.adminMain;

import dk.via.sep.client.core.ViewHandler;
import dk.via.sep.client.core.ViewModelFactory;
import dk.via.sep.client.view.ViewController;

public class AdminMainViewController extends ViewController {

    private ViewHandler viewHandler;
    private AdminMainViewModel adminMainViewModel;

    public void init() {
        this.viewHandler = ViewHandler.getInstance();
        this.adminMainViewModel = ViewModelFactory.getInstance().getAdminMainViewModel();

    }

}

package dk.via.sep.client.view.mainChatView;

import dk.via.sep.client.core.ViewHandler;
import dk.via.sep.client.core.ViewModelFactory;
import dk.via.sep.client.view.ViewController;

public class MainChatViewController extends ViewController {
    private ViewHandler viewHandler;
    private MainChatViewModel mainChatViewModel;

    public MainChatViewController(){
        viewHandler = ViewHandler.getInstance();
        mainChatViewModel = ViewModelFactory.getInstance().getMainChatViewModel();
    }

    public void init(){

    }
}

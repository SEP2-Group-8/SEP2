package dk.via.sep.client.core;


import dk.via.sep.client.view.adminMain.AdminMainViewModel;
import dk.via.sep.client.view.login.LoginViewModel;
import dk.via.sep.client.view.main.MainViewModel;
import dk.via.sep.client.view.register.RegisterViewModel;
import dk.via.sep.client.view.userList.UserListViewModel;

public class ViewModelFactory {

    private final ModelFactory mf;

    private LoginViewModel loginViewModel;
    private MainViewModel mainViewModel;
    private RegisterViewModel registerViewModel;
    private AdminMainViewModel adminMainViewModel;
    private UserListViewModel userListViewModel;

    public ViewModelFactory(ModelFactory mf) {
        this.mf = mf;
    }

    public LoginViewModel getLoginViewModel() {
        if (loginViewModel == null)
            loginViewModel = new LoginViewModel(mf.getModelManager());
        return loginViewModel;
    }

    public RegisterViewModel getRegisterViewModel() {
        if (registerViewModel == null)
            registerViewModel = new RegisterViewModel(mf.getModelManager());
        return registerViewModel;
    }

    public MainViewModel getMainViewModel() {
        if (mainViewModel == null)
            mainViewModel = new MainViewModel(mf.getModelManager());
        return mainViewModel;
    }
    public AdminMainViewModel getAdminMainViewModel()
    {
        if(adminMainViewModel == null)
            adminMainViewModel = new AdminMainViewModel(mf.getModelManager());
        return adminMainViewModel;
    }
    public UserListViewModel getUserListViewModel()
    {
        if(userListViewModel == null)
            userListViewModel = new UserListViewModel(mf.getModelManager());
        return userListViewModel;
    }

}

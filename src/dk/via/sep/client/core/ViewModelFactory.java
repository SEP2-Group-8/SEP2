package dk.via.sep.client.core;


import dk.via.sep.client.view.adminMain.AdminMainViewModel;
import dk.via.sep.client.view.login.LoginViewModel;
import dk.via.sep.client.view.main.MainViewModel;
import dk.via.sep.client.view.register.RegisterViewModel;
import dk.via.sep.client.view.userList.UserListViewModel;

public class ViewModelFactory {

    private final LoginViewModel loginViewModel;
    private final MainViewModel mainViewModel;
    private final RegisterViewModel registerViewModel;
    private final AdminMainViewModel adminMainViewModel;
    private final UserListViewModel userListViewModel;
    private static ViewModelFactory viewModelFactory;

    private ViewModelFactory() {
        loginViewModel = new LoginViewModel();
        registerViewModel = new RegisterViewModel();
        mainViewModel = new MainViewModel();
        adminMainViewModel = new AdminMainViewModel();
        userListViewModel = new UserListViewModel();
    }

    public static ViewModelFactory getInstance() {
        if (viewModelFactory == null)
            viewModelFactory = new ViewModelFactory();
        return viewModelFactory;
    }

    public LoginViewModel getLoginViewModel() {
        return loginViewModel;
    }

    public RegisterViewModel getRegisterViewModel() {
        return registerViewModel;
    }

    public MainViewModel getMainViewModel() {
        return mainViewModel;
    }

    public AdminMainViewModel getAdminMainViewModel() {
        return adminMainViewModel;
    }

    public UserListViewModel getUserListViewModel() {
        return userListViewModel;
    }

}

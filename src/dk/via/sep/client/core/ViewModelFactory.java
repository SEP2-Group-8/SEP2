package dk.via.sep.client.core;


import dk.via.sep.client.view.adminEditEvent.AdminEditEventViewModel;
import dk.via.sep.client.view.adminEventDetails.AdminEventDetailsViewModel;
import dk.via.sep.client.view.adminMain.AdminMainViewModel;
import dk.via.sep.client.view.adminMainEvent.AdminMainEventViewModel;
import dk.via.sep.client.view.login.LoginViewModel;
import dk.via.sep.client.view.main.MainViewModel;
import dk.via.sep.client.view.profile.ProfileViewModel;
import dk.via.sep.client.view.register.RegisterViewModel;
import dk.via.sep.client.view.userList.UserListViewModel;

public class ViewModelFactory {

    private final LoginViewModel loginViewModel;
    private final MainViewModel mainViewModel;
    private final RegisterViewModel registerViewModel;
    private final AdminMainViewModel adminMainViewModel;
    private final UserListViewModel userListViewModel;
    private final ProfileViewModel profileViewModel;
    private final AdminMainEventViewModel adminMainEventViewModel;
    private final AdminEventDetailsViewModel adminEventDetailsViewModel;
    private final AdminEditEventViewModel adminEditEventViewModel;
    private static ViewModelFactory viewModelFactory;

    private ViewModelFactory() {
        loginViewModel = new LoginViewModel();
        registerViewModel = new RegisterViewModel();
        mainViewModel = new MainViewModel();
        adminMainViewModel = new AdminMainViewModel();
        userListViewModel = new UserListViewModel();
        profileViewModel = new ProfileViewModel();
        adminMainEventViewModel = new AdminMainEventViewModel();
        adminEventDetailsViewModel = new AdminEventDetailsViewModel();
        adminEditEventViewModel = new AdminEditEventViewModel();
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

    public ProfileViewModel getProfileViewModel() {
        return profileViewModel;
    }

    public AdminMainEventViewModel getAdminMainEventViewModel() {
        return adminMainEventViewModel;
    }

    public AdminEventDetailsViewModel getAdminEventDetailsViewModel() {
        return adminEventDetailsViewModel;
    }

    public AdminEditEventViewModel getAdminEditEventViewModel() {
        return adminEditEventViewModel;
    }
}

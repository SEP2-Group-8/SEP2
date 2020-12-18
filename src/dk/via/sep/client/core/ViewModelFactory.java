package dk.via.sep.client.core;


import dk.via.sep.client.view.adminCreateEvent.AdminCreateEventViewModel;
import dk.via.sep.client.view.adminEditEvent.AdminEditEventViewModel;
import dk.via.sep.client.view.adminEventDetails.AdminEventDetailsViewModel;
import dk.via.sep.client.view.adminMain.AdminMainViewModel;
import dk.via.sep.client.view.adminMainEvent.AdminMainEventViewModel;
import dk.via.sep.client.view.login.LoginViewModel;
import dk.via.sep.client.view.main.MainViewModel;
import dk.via.sep.client.view.profile.ProfileViewModel;
import dk.via.sep.client.view.profileEdit.ProfileEditViewModel;
import dk.via.sep.client.view.register.RegisterViewModel;
import dk.via.sep.client.view.userEventDetails.UserEventDetailsViewModel;
import dk.via.sep.client.view.userList.UserListViewModel;
import dk.via.sep.client.view.userMainEvent.UserMainEventViewModel;

/**
 * A singleton factory class that initializes and contains all the view models.
 *
 * @author Bogdan-Florin Cirstoiu
 * @version 1.0
 */
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
    private final AdminCreateEventViewModel adminCreateEventViewModel;
    private final UserMainEventViewModel userMainEventViewModel;
    private final UserEventDetailsViewModel userEventDetailsViewModel;
    private final ProfileEditViewModel profileEditViewModel;
    private static ViewModelFactory viewModelFactory;

    /**
     * Private constructor that initializes all view models.
     */
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
        adminCreateEventViewModel = new AdminCreateEventViewModel();
        userMainEventViewModel = new UserMainEventViewModel();
        userEventDetailsViewModel = new UserEventDetailsViewModel();
        profileEditViewModel = new ProfileEditViewModel();
    }

    /**
     * The static getInstance method that gives access to the vm factory interface
     *
     * @return the vm factory interface
     */
    public static ViewModelFactory getInstance() {
        if (viewModelFactory == null)
            viewModelFactory = new ViewModelFactory();
        return viewModelFactory;
    }

    /**
     * Getter for the login view model
     *
     * @return the login view model
     */
    public LoginViewModel getLoginViewModel() {
        return loginViewModel;
    }

    /**
     * Getter for the register view model
     *
     * @return the register view model
     */
    public RegisterViewModel getRegisterViewModel() {
        return registerViewModel;
    }

    /**
     * Getter for the main view model
     *
     * @return the main view model
     */
    public MainViewModel getMainViewModel() {
        return mainViewModel;
    }

    /**
     * Getter for the admin main view model
     *
     * @return the admin main view model
     */
    public AdminMainViewModel getAdminMainViewModel() {
        return adminMainViewModel;
    }

    /**
     * Getter for the user list view model
     *
     * @return the user list view model
     */
    public UserListViewModel getUserListViewModel() {
        return userListViewModel;
    }

    /**
     * Getter for the profile view model
     *
     * @return the profile view model
     */
    public ProfileViewModel getProfileViewModel() {
        return profileViewModel;
    }

    /**
     * Getter for the admin main event view model
     *
     * @return the admin main event view model
     */
    public AdminMainEventViewModel getAdminMainEventViewModel() {
        return adminMainEventViewModel;
    }

    /**
     * Getter for the admin event details view model
     *
     * @return the admin event details view model
     */
    public AdminEventDetailsViewModel getAdminEventDetailsViewModel() {
        return adminEventDetailsViewModel;
    }

    /**
     * Getter for the admin edit event view model
     *
     * @return the admin edit event view model
     */
    public AdminEditEventViewModel getAdminEditEventViewModel() {
        return adminEditEventViewModel;
    }

    /**
     * Getter for the admin create event view model
     *
     * @return the admin create event view model
     */
    public AdminCreateEventViewModel getAdminCreateEventViewModel() {
        return adminCreateEventViewModel;
    }

    /**
     * Getter for the user main event view model
     *
     * @return the user main event view model
     */
    public UserMainEventViewModel getUserMainEventViewModel() {
        return userMainEventViewModel;
    }

    /**
     * Getter for the user event details view model
     *
     * @return the user event details view model
     */
    public UserEventDetailsViewModel getUserEventDetailsViewModel() {
        return userEventDetailsViewModel;
    }

    /**
     * Getter for the profile edit view model
     *
     * @return the profile edit view model
     */
    public ProfileEditViewModel getProfileEditViewModel() {
        return profileEditViewModel;
    }
}

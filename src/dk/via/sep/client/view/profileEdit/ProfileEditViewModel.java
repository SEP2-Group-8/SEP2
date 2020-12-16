package dk.via.sep.client.view.profileEdit;

import dk.via.sep.client.core.ModelFactory;
import dk.via.sep.client.model.user.LoggedUser;
import dk.via.sep.client.model.userModel.UserModel;
import dk.via.sep.shared.transfer.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProfileEditViewModel {
    private UserModel userModel;
    private StringProperty nameLabel;
    private StringProperty passwordLabel;
    private StringProperty emailLabel;
    //might delete this later
    private StringProperty birthdayLabel;

    public ProfileEditViewModel() {
        userModel = ModelFactory.getInstance().getUserModelManager();
        nameLabel = new SimpleStringProperty();
        passwordLabel = new SimpleStringProperty();
        emailLabel = new SimpleStringProperty();
        birthdayLabel = new SimpleStringProperty();
    }

    public StringProperty nameLabelProperty() {
        return nameLabel;
    }

    public StringProperty passwordLabelProperty() {
        return passwordLabel;
    }

    public StringProperty emailLabelProperty() {
        return emailLabel;
    }

    public StringProperty birthdayLabelProperty() {
        return birthdayLabel;
    }

    public void saveChanges(){
        User currentUser = LoggedUser.getInstance().getUser();
        if(emailLabel.getValue() !=null)
            currentUser.setEmail(emailLabel.getValue());
        if(passwordLabel.getValue() !=null)
            currentUser.setPassword(passwordLabel.getValue());
        if(nameLabel.getValue() != null)
            currentUser.setUsername(nameLabel.getValue());
        System.out.println(currentUser.toString());
        userModel.editUserDetails(currentUser);
    }
}

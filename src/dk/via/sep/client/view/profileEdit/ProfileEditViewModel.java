package dk.via.sep.client.view.profileEdit;

import dk.via.sep.client.core.ModelFactory;
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
        User user = new User(emailLabel.getValue(), passwordLabel.getValue(), nameLabel.getValue());
        System.out.println(user.toString());
        userModel.editUserDetails(user);
    }
}

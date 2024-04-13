package software.login.viewmodel;

import com.google.firebase.auth.ExportedUserRecord;
import com.google.firebase.auth.ListUsersPage;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import software.utils.FirebaseRepostory;

public class LoginViewModel {
    private final StringProperty userEmail = new SimpleStringProperty();
    private final StringProperty userPassword = new SimpleStringProperty();
    private final ReadOnlyBooleanWrapper loginPosiable = new ReadOnlyBooleanWrapper();
    
    // Firebase connection
    private FirebaseRepostory mFirebaseRepostory;
    public LoginViewModel() {
        loginPosiable.bind(userEmail.isNotEmpty().and(userPassword.isNotEmpty()));
        mFirebaseRepostory = new FirebaseRepostory();
    }

    public StringProperty userEmailProperty()
    {
        return userEmail;
    }
    public StringProperty userPasswordProperty()
    {
        return userPassword;
    }
    public ReadOnlyBooleanProperty isLoginPosiableProperty()
    {
        return loginPosiable.getReadOnlyProperty();
    }

    


}

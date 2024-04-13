package software.login.viewmodel;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginViewModel {
    private final StringProperty userEmail = new SimpleStringProperty();
    private final StringProperty userPassword = new SimpleStringProperty();
    private final ReadOnlyBooleanWrapper loginPosiable = new ReadOnlyBooleanWrapper();
    
    // Firebase connection
    public LoginViewModel() {
        loginPosiable.bind(userEmail.isNotEmpty().and(userPassword.isNotEmpty()));

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

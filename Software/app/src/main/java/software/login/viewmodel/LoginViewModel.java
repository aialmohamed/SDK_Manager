package software.login.viewmodel;
import java.util.concurrent.ExecutionException;

import com.mongodb.client.MongoDatabase;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import software.login.model.UserModel;
import software.utils.Database.MongoDBConnectionAsync;
import software.utils.Database.Dao.UserDao;

public class LoginViewModel {
    private final StringProperty userEmail = new SimpleStringProperty();
    private final StringProperty userPassword = new SimpleStringProperty();
    private final ReadOnlyBooleanWrapper loginPosiable = new ReadOnlyBooleanWrapper();
    private  MongoDBConnectionAsync mConnection;
    
    // Firebase connection
    public LoginViewModel() {
        try {
            mConnection = MongoDBConnectionAsync.getInstance();
            MongoDatabase mDatabase = mConnection.connectAndGetDatabaseAsync().get();
            UserDao userDataAccess = new UserDao(mDatabase, "users");
            UserModel dummyUser = new UserModel("dummyUser2", "dummyEmail@example.com", "dummyPassword");
            userDataAccess.create(dummyUser).get();
            mConnection.close().get();
            
            
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

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

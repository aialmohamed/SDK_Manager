package software.login.viewmodel;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.google.inject.Inject;
import com.mongodb.client.MongoDatabase;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import software.login.model.UserModel;
import software.utils.Database.MongoDBConnectionAsync;
import software.utils.Database.Dao.UserDao;
import software.utils.Sessions.UserSession;


public class LoginViewModel {
    private final StringProperty userName = new SimpleStringProperty();
    private final StringProperty userPassword = new SimpleStringProperty();
    private final ReadOnlyBooleanWrapper loginPosiable = new ReadOnlyBooleanWrapper();
    
    private  MongoDBConnectionAsync mConnection;
    private UserDao mUserDao;
    private MongoDatabase mDatabase;
    private UserSession mUserSession;
    @Inject
    public LoginViewModel(UserSession userSession) 
    {
        this.mUserSession = userSession;
        CreateMongoConnection();
        mUserDao = new UserDao(mDatabase,"users");
        loginPosiable.bind(userName.isNotEmpty().and(userPassword.isNotEmpty()));

    }

    public StringProperty userNameProperty()
    {
        return userName;
    }
    public StringProperty userPasswordProperty()
    {
        return userPassword;
    }
    public ReadOnlyBooleanProperty isLoginPosiableProperty()
    {
        return loginPosiable.getReadOnlyProperty();
    }

    private void CreateMongoConnection()
    {
        try {
            mConnection = MongoDBConnectionAsync.getInstance();
            mDatabase = mConnection.connectAndGetDatabaseAsync().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

    // this shall read the binding values and authenticate the user from the database
    public void AuthenticateUser() throws InterruptedException, ExecutionException
    {
        // Search the user in database : 
        CompletableFuture<UserModel> userFuture = mUserDao.findUserByName(userName.getValue());
        UserModel user = userFuture.get();
        if(user != null)
        {
            this.mUserSession.loginUser(user);
            System.out.println("User Found " + user.getUserName() + " " + user.getuserEmail() + " " + user.getuserPassword());
        }
        else
        {
            this.mUserSession.deleteInstance();
            System.out.println("User Not Found");
        }
    }
    private void  CloseMongoConnection()
    {
        try {
            mConnection.close().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
    


}

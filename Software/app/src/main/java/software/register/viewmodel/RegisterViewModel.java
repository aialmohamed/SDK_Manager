package software.register.viewmodel;

import java.util.concurrent.ExecutionException;

import com.mongodb.client.MongoDatabase;

import javafx.beans.property.SimpleStringProperty;
import software.login.model.UserModel;
import software.utils.Database.MongoDBConnectionAsync;
import software.utils.Database.Dao.UserDao;

public class RegisterViewModel {
    private SimpleStringProperty userEmail = new SimpleStringProperty();
    private SimpleStringProperty userPassword = new SimpleStringProperty();
    private SimpleStringProperty userName = new SimpleStringProperty();
    private SimpleStringProperty registerStatus = new SimpleStringProperty();
    private  MongoDBConnectionAsync mConnection;
    private UserDao mUserDao;
    private MongoDatabase mDatabase;

    public RegisterViewModel() {

        CreateMongoConnection();
        mUserDao = new UserDao(mDatabase,"users");

    }

    public SimpleStringProperty getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(SimpleStringProperty userEmail) {
        this.userEmail = userEmail;
    }

    public SimpleStringProperty getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(SimpleStringProperty userPassword) {
        this.userPassword = userPassword;
    }

    public SimpleStringProperty getUserName() {
        return userName;
    }

    public void setUserName(SimpleStringProperty userName) {
        this.userName = userName;
    }


    public SimpleStringProperty getRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(SimpleStringProperty registerStatus) {
        this.registerStatus = registerStatus;
    }

    

    public void RegisterUser() 
    {
        //  create a UserModel 
        UserModel currentUser = new UserModel();
        currentUser.setuserEmail(userEmail.get());
        currentUser.setuserPassword(userPassword.get());
        currentUser.setUserName(userName.get());
        
         
        if( currentUser.getuserEmail().isBlank() || currentUser.getuserPassword().isEmpty() || currentUser.getUserName().isEmpty())
        {
            registerStatus.setValue("Please Fill all the fields");
            return;
        }
        try {
            registerStatus.setValue("Registering User ...");
            mUserDao.create(currentUser).get();
            registerStatus.setValue("Registering User Successfull");
        } catch (InterruptedException | ExecutionException e) {
            registerStatus.setValue("Registering User Failed");
            e.printStackTrace();
        }
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
}

package software;
import java.util.concurrent.ExecutionException;

import com.mongodb.client.MongoDatabase;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import software.login.model.UserModel;
import software.utils.Database.MongoDBConnectionAsync;
import software.utils.Database.Dao.UserDao;

public class MainApp extends Application {

    
    public MainApp() {

/*             try {
                MongoDBConnectionAsync mConnection = MongoDBConnectionAsync.getInstance();
                MongoDatabase mDatabase = mConnection.connectAndGetDatabaseAsync().get();
                UserDao userDataAccess = new UserDao(mDatabase, "users");
                UserModel dummyUser = new UserModel("dummyUser", "dummyEmail@example.com", "dummyPassword");
                userDataAccess.create(dummyUser);
                //mConnection.close();
                
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } */

        }

    @Override
    public void start(Stage stage) throws Exception {



        FXMLLoader loader =new FXMLLoader();
        
        loader.setLocation(getClass().getClassLoader().getResource("fxml/Login.fxml"));
        Parent login  = loader.load();
        Scene scene = new Scene(login);
        stage.setScene(scene);
        stage.show();
    }


    
}

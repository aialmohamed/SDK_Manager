package software;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class MainApp extends Application {


    

        
    public MainApp() {
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

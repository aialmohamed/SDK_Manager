package software;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import software.utils.SdkListHandler.Handlers.SdkDefaultListHandler;
import software.utils.SdkListHandler.models.SdkDefaulltListModel;
import software.utils.SdkListHandler.models.SdkDefaultHeaderModel;



public class MainApp extends Application {


    

        
    public MainApp() {

        SdkDefaultListHandler sdkDefaultListHandler = new SdkDefaultListHandler();
        try {
            SdkDefaultHeaderModel myList = sdkDefaultListHandler.readDefultSdkFromYaml().get();
            System.out.println(myList);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
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

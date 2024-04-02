package software;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import software.utils.FirebaseConnection;

public class MainApp extends Application {


    FirebaseConnection mConnection;

private Parent createContent() {
        return new StackPane(new Text("Hello World"));
    }
    public MainApp() {
        try
        {
            mConnection = new FirebaseConnection();
        }
        catch(IOException ex)
        {
            System.out.println(ex);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent(), 300, 300));
        stage.show();
        
    }


    
}

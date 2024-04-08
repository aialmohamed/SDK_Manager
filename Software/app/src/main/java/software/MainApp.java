package software;

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
        return new StackPane(new Text("hello World"));
    }
    public MainApp() {
            mConnection = FirebaseConnection.getInstance();
            System.out.println(mConnection.getSdkManagerApp().getName());
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent(), 300, 300));
        stage.show();
        
    }


    
}

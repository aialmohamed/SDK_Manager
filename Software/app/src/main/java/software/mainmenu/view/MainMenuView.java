package software.mainmenu.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import software.utils.Sessions.UserSession;
import javafx.scene.Node;


public class MainMenuView {



    @FXML
    private Button ShowSDKListButton;

    @FXML
    private Button DownloadSDKButton;

    @FXML
    private Button UpdateSDKButton;

    @FXML
    private Button SettingsButton;

    @FXML
    private Button MoreButton;

    @FXML
    private Button LogoutButton;



    @FXML
    private Pane LogoutPane;

    @FXML
    void initialize() {

        UserSession userSession = UserSession.getInstance();
        String username = userSession.getCurrentUser().getUserName();

        LogoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                userSession.logoutUser();
                switchToLoginMenu(event);
                System.out.println(username+ " " +"Logged Out");
            }
        });

    }

    void switchToLoginMenu(ActionEvent event) {
        // Switch to Login Menu
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
            Parent root = loader.load();
            Scene registerScene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(registerScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

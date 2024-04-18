package software.mainmenu.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import software.utils.Sessions.UserSession;
import javafx.scene.Node;


public class MainMenuView {

    @FXML
    private Pane ShowSDKListPane;

    @FXML
    private Pane DownloadSDKPane;

    @FXML
    private Pane UpdateSDKPane;

    @FXML
    private Pane SettingPane;

    @FXML
    private Pane MorePane;

    @FXML
    private Pane LogoutPane;

    @FXML
    void initialize() {

        UserSession userSession = UserSession.getInstance();
        String username = userSession.getCurrentUser().getUserName();

        // if User Clicks on Logout Pane -> Logout User and close Session
        LogoutPane.setOnMouseClicked(event -> {
            //String username = userSession.getCurrentUser().getUserName();
            userSession.logoutUser();
            switchToLoginMenu(event);
            System.out.println(username+ " " +"Logged Out");
        });

    }

    void switchToLoginMenu(MouseEvent event) {
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

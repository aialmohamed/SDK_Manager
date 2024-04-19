package software.login.view;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import software.login.viewmodel.LoginViewModel;
import software.utils.Sessions.UserSession;
import javafx.scene.Node;

public class LoginView {

    @FXML
    private TextField UserNameTextField;

    @FXML
    private PasswordField PasswordTextField;

    @FXML
    private JFXButton LoginButton;

    @FXML
    private JFXButton RegisterButton;

    @FXML
    void initialize() {
        // Create User Session
        UserSession userSession = UserSession.getInstance();

        // Create DataBinding to LoginViewModel
        LoginViewModel loginViewModel = new LoginViewModel(userSession);
        UserNameTextField.textProperty().bindBidirectional(loginViewModel.userNameProperty());
        PasswordTextField.textProperty().bindBidirectional(loginViewModel.userPasswordProperty());
        LoginButton.disableProperty().bind(loginViewModel.isLoginPosiableProperty().not());

        LoginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    // Authenticate User
                    loginViewModel.AuthenticateUser();
                    boolean isAuthenticated = userSession.isAuthenticated();
                    //  Switch to Main Menu
                    if (isAuthenticated) {
                        switchToMainMenu(event, userSession);
                    } else {
                        System.out.println("User Not Found");
                    }

                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

        RegisterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Switch to Register
                switchToRegister(event);
            }
        });
    }

    private void switchToMainMenu(ActionEvent event, UserSession userSession) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainMenu.fxml"));
            Parent root = loader.load();
            Scene registerScene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(registerScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void switchToRegister(ActionEvent event) {
        // switch to register view
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Register.fxml"));
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

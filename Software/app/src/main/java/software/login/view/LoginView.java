package software.login.view;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import software.login.viewmodel.LoginViewModel;
import javafx.scene.Node;

public class LoginView {
    
    @FXML
    private TextField EmailTextField;
    
    @FXML
    private TextField PasswordTextfield;
    
    @FXML
    private Button LoginButton;

    @FXML
    private Button RegisterButton;

    @FXML
    void initialize(){
        LoginViewModel loginViewModel = new LoginViewModel();
        EmailTextField.textProperty().bindBidirectional(loginViewModel.userEmailProperty());
        PasswordTextfield.textProperty().bindBidirectional(loginViewModel.userPasswordProperty());
        LoginButton.disableProperty().bind(loginViewModel.isLoginPosiableProperty().not());

        LoginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    loginViewModel.AuthenticateUser();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

        RegisterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
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
        });
    }
    

}

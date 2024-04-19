package software.register.view;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import software.register.viewmodel.RegisterViewModel;
import javafx.scene.Node;
public class RegisterView {

    @FXML
    private TextField usernameRegister;
    @FXML
    private TextField EmailRegister;
    @FXML
    private TextField PasswordRegister;
    @FXML
    private Label Status;
    @FXML
    private JFXButton RegisterCMDButton;
    @FXML
    private JFXButton CancelButton;

    @FXML
    void initialize() {
        

        RegisterViewModel registerViewModel = new RegisterViewModel();

        usernameRegister.textProperty().bindBidirectional(registerViewModel.getUserName());
        EmailRegister.textProperty().bindBidirectional(registerViewModel.getUserEmail());
        PasswordRegister.textProperty().bindBidirectional(registerViewModel.getUserPassword());
        Status.textProperty().bindBidirectional(registerViewModel.getRegisterStatus());

        
        RegisterCMDButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                registerViewModel.RegisterUser();
            }
        });

        

        CancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // switch to login view
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
                    Parent root = loader.load();
                    Scene loginScene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(loginScene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}


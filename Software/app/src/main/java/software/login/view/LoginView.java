package software.login.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import software.login.viewmodel.LoginViewModel;

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
    }

}

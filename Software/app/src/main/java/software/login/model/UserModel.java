package software.login.model;

public class UserModel {

    private String userName ;
    private String userEmail;
    private String userPassword;
    
    public UserModel() {
    
    }

    public UserModel(String userId, String userName, String userEmail, String userPassword) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }
    
    // for testing purposes
    public UserModel(String userName, String userEmail, String userPassword) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }
    

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if(userName == null)
        {
            this.userName = "";
        }
        else
        {
            this.userName = userName;
        }
    }

    public String getuserEmail() {
        return userEmail;
    }

    public void setuserEmail(String userEmail) {
        if(userEmail == null)
        {
            this.userEmail = "";
        }
        else
        {
            this.userEmail = userEmail;
        
        }
    }

    public String getuserPassword() {
        return userPassword;
    }

    public void setuserPassword(String userPassword) {
        if(userPassword == null)
        {
            this.userPassword = "";
        }
        else
        {
            this.userPassword = userPassword;
        }
    }
    
}

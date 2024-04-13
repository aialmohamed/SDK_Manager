package software.login.model;

public class UserModel {

    private String userId ;
    private String userName ;
    private String userEmail;
    private String userPassword;
    
    public UserModel() {
    
    }

    public UserModel(String userId, String userName, String userEmail, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        if(userId == null)
        {
            this.userId = "";
        }
        else
        {
            this.userId = userId;
        }
        
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getuserEmail() {
        return userEmail;
    }

    public void setuserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getuserPassword() {
        return userPassword;
    }

    public void setuserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    
}

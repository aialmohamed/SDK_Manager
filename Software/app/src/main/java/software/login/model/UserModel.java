/**
 * Description
 *  This class is a model class for the user. It contains the user's name, email, and password.
 *
 * Author: Ahmed Ibrahim Almohamed
 * Class Name: UserModel
 * Last Edit: 2024-04-18
 */
package software.login.model;


public class UserModel {

    private String userName ;
    private String userEmail;
    private String userPassword;
    
    public UserModel() {
    
    }

    /**
     * Description
     * This constructor initializes the user's name, email, and password.
     *
    @param userName UserName of the user  
    @param userEmail Email of the user
    @param userPassword Password of the user
    @return None
     */
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

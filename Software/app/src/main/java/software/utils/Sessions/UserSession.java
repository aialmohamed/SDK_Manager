/**
 * Description
 * This is the UserSession class that is used to manage the user session.
 * Author: Ahmed Ibrahim Almohamed
 * Class Name: UserSession
 * Last Edit: 2024-04-18
 */

package software.utils.Sessions;

import software.login.model.UserModel;


public class UserSession {
    private static UserSession instance;
    private UserModel currentUser ;
    private boolean isAuthenticated;

    private UserSession() {
    }

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void loginUser(UserModel user) {
        currentUser = user;
        isAuthenticated = true;
    }

    public void logoutUser() {
        currentUser = null;
        isAuthenticated = false;
    }
    public boolean isAuthenticated() {
        return isAuthenticated;
    }
    public UserModel getCurrentUser() {
        return currentUser;
    }

}

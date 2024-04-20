package software.showsdklist.viewmodel;

import software.utils.Sessions.UserSession;

public class ShowSdkListViewModel {

    // get a userSession
    UserSession mUserSession;

    public ShowSdkListViewModel(UserSession mUserSession) {
        this.mUserSession = mUserSession;
    }

}

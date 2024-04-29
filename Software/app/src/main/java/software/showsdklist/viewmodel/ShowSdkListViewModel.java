package software.showsdklist.viewmodel;

import java.util.concurrent.ExecutionException;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import software.utils.SdkListHandler.Handlers.SdkDefaultListHandler;
import software.utils.SdkListHandler.models.SdkDefaulltListModel;
import software.utils.SdkListHandler.models.SdkDefaultHeaderModel;
import software.utils.Sessions.UserSession;

public class ShowSdkListViewModel {

    //#region Properties
    private final SimpleStringProperty userName = new SimpleStringProperty();
    private ObservableList<SdkDefaulltListModel> sdkDefaultModel = FXCollections.observableArrayList();

    // get a userSession
    UserSession mUserSession;
    private SdkDefaultListHandler mSdkDefaultListHandler;
    private SdkDefaultHeaderModel mSdkDefaultHeaderModel;
    //#endregion
    

    //#region Constructors
    public ShowSdkListViewModel() {
    }

    public ShowSdkListViewModel(UserSession mUserSession, SdkDefaultListHandler mSdkDefaultListHandler) {
        this.mUserSession = mUserSession;
        this.mSdkDefaultListHandler = mSdkDefaultListHandler;
        setUserName(this.mUserSession.getCurrentUser().getUserName());
    }
    //#endregion

    //#region Methods
    //#endregion

    //#region Getters and Setters
    public SimpleStringProperty getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if(this.userName == null)
        {
            this.userName.set(this.mUserSession.getCurrentUser().getUserName());
        }
        else
        {
            this.userName.set(userName);
        }
        
    }
    public SdkDefaultHeaderModel getmSdkDefaultHeaderModel() {
        
        try {
            mSdkDefaultHeaderModel = mSdkDefaultListHandler.readDefultSdkFromYaml().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return mSdkDefaultHeaderModel;
    }

    public ObservableList<SdkDefaulltListModel> getSdkDefaultModel() {
        return sdkDefaultModel;
    }

    public void setSdkDefaultModel() {
        getmSdkDefaultHeaderModel();
        this.sdkDefaultModel = FXCollections.observableArrayList(mSdkDefaultHeaderModel.getSdkDefaultModel());
    }
    
    //#endregion

}

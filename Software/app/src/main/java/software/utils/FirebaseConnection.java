package software.utils;

import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseConnection {

    //#region fields
    private FirebaseApp sdkManagerApp;
    private FirebaseDatabase sdkManagerdb ;
    private static FirebaseConnection single_connection = null ;
    //#endregion
    private FirebaseConnection(){
        try{
            FirebaseInit();
            System.out.println(sdkManagerApp.getName());
        }catch(IOException ex)
        {
            ex.printStackTrace();
        }

    }

    public static synchronized FirebaseConnection getInstance()
    {
        if(single_connection == null)
            {
                single_connection = new FirebaseConnection();
            }
            return single_connection;
    }

    //#region private methods
    private void FirebaseInit() throws IOException
    {
        FirebaseOptions options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.getApplicationDefault())
        .setDatabaseUrl("https://sdkmanager-3e802-default-rtdb.firebaseio.com/")
        .build();
        FirebaseApp local = FirebaseApp.initializeApp(options);
        setSdkManagerApp(local);
    }
    //#endregion

    //#region Getters and Setters
    public FirebaseApp getSdkManagerApp() {
        return sdkManagerApp;
    }

    public void setSdkManagerApp(FirebaseApp sdkManagerApp) {
        this.sdkManagerApp = sdkManagerApp;
    }
    //#endregion

}

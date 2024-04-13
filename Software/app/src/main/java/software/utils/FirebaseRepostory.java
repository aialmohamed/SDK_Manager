package software.utils;
import com.google.api.core.ApiFuture;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.ListUsersPage;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.protobuf.Api;

public class FirebaseRepostory {
    
    //#region fields
    FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;
    DatabaseReference mReference;
    FirebaseConnection mConnection;
    //#endregion
    
    //#region constructer 
    public FirebaseRepostory()
    {
        this.mConnection = FirebaseConnection.getInstance();
        this.mAuth = FirebaseAuth.getInstance(mConnection.getSdkManagerApp());
        this.mDatabase = FirebaseDatabase.getInstance(mConnection.getSdkManagerApp());
        this.mReference = mDatabase.getReference();
        
        
    }
    //#endregion
    //#region Getters and Setters
    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    public void setmAuth(FirebaseAuth mAuth) {
            this.mAuth = mAuth;

        
    }
    public DatabaseReference getmReference() {
        return mReference;
    }

    public void setmReference(DatabaseReference mReference) {
        this.mReference = mReference;
    }
    //#endregion

    //#region methodstry
        
    public ListUsersPage getAllUsersFromRepository()
    {
        try {
            // Asynchronously list users
            ApiFuture<ListUsersPage> listUsersFuture = mAuth.listUsersAsync(null);
            
            // Block and wait for the result
            ListUsersPage allUsers = listUsersFuture.get();
            
            return allUsers;
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
            return null;
        }
    }
    public boolean IsCurrentUserAuth(String Email)
    {
        this.mAuth.verifyIdToken()
        try {
            // Asynchronously fetch user by email
            ApiFuture<UserRecord> userFuture = this.mAuth.getUserByEmailAsync(Email);
            
            // Block and wait for the result
            UserRecord userRecord = userFuture.get();
            
            // If the user record is not null, the user is authenticated
            return userRecord != null;
        } catch (Exception e) {
            // Handle exceptions (e.g., invalid email, user not found)
            e.printStackTrace();
            return false;
        }
    }
    //#endregion

    //#region private methods
    //#endregion

}

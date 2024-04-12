package software.utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseRepistory {

    // creating a repository to controll our database
    
    //#region fields
    FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;
    DatabaseReference mReference;
    FirebaseConnection mConnection;
    //#endregion
    
    //#region constructer 
    public FirebaseRepistory()
    {
        mConnection = FirebaseConnection.getInstance();
        FirebaseAuth  mAuth = FirebaseAuth.getInstance(mConnection.getSdkManagerApp());
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance(mConnection.getSdkManagerApp());
        DatabaseReference mReference = mDatabase.getReference();
        
    }

    //#endregion
    
    // add users 

   // mConnection = FirebaseConnection.getInstance();
    //System.out.println(mConnection.getSdkManagerApp().getName());
    //FirebaseAuth  defultauth = FirebaseAuth.getInstance(mConnection.getSdkManagerApp());
    //FirebaseDatabase defulDatabase = FirebaseDatabase.getInstance(mConnection.getSdkManagerApp());
    //DatabaseReference mref = defulDatabase.getReference();
    //UserModel myUser = new UserModel();
    //myUser.setUserName("User1");
    //myUser.setEmail("firestuser@gmail.com");
    //myUser.setPassword("1234567");
    //myUser.setUserId("1");
    //mref.child("Users").setValueAsync(myUser);

    //#region methods

    //#endregion

    //#region private methods


    //#endregion

}

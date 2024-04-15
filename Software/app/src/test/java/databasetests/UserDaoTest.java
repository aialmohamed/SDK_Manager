package databasetests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bson.BsonObjectId;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;

import javafx.beans.binding.StringBinding;
import software.login.model.UserModel;
import software.utils.Database.Dao.UserDao;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

@RunWith(MockitoJUnitRunner.class)
public class UserDaoTest {

    @Mock
    private MongoDatabase mockDatabase;

    @Mock
    private MongoCollection<Document> mockCollection;
    @Mock
    private InsertOneResult mockInsertOneResult;
    @Mock
    private BsonValue mockBsonValue;
    @Mock
    private BsonObjectId mockBsonObjectId;
    @Mock
    private FindIterable<Document> mockFindIterable;
    @Mock
    private UpdateResult mockUpdateResult;

    private UserDao userDao;
    

    @Before
    public void setUp() {
        userDao = new UserDao(mockCollection);
    }   

    @Test
    public void testCreateUser_Success() {
        // Create a UserModel object representing the user data
        UserModel userModel = new UserModel();
        userModel.setUserName("testUser");
        userModel.setuserEmail("test@example.com");
        userModel.setuserPassword("password123");

        // Mocking insertOne method
        when(mockCollection.insertOne(any(Document.class))).thenReturn(mockInsertOneResult);

        // Call the create method
        CompletableFuture<Void> result = userDao.create(userModel);
        result.join();

        // Verify that insertOne method was called with the correct document
        verify(mockCollection).insertOne(
            argThat(document -> document.getString("userName").equals("testUser") &&
                                document.getString("userEmail").equals("test@example.com") &&
                                document.getString("userPassword").equals("password123"))
        );


    }

    @Test
    public void testDeleteUser_Success() {
        //  create a filter with ID 
        String testId = "661da231d125306db4dacc75";
        Document document = new Document("_id", new ObjectId(testId));
        // run delete method
        CompletableFuture<Void> value = userDao.delete(testId);
        value.join();
        // verify that deleteOne() was called with the correct arguments
        verify(mockCollection, times(1)).deleteOne(eq(document));
    }

    @Test
    public void testReadUser_Success() {
        String testId = "661da231d125306db4dacc75";
        Document mockDocument = new Document("userName","test")
        .append("userEmail", "test@gmail.com")
        .append("userPassword", "testPassword");
        Document filterDocument = new Document("_id", new ObjectId(testId));

        when(mockCollection.find(eq(filterDocument))).thenReturn(mockFindIterable);
        when(mockFindIterable.first()).thenReturn(mockDocument);

        CompletableFuture<UserModel> value = userDao.read(testId);
        UserModel userModel = value.join();
        
        // Verify that the correct document was retrieved and converted to UserModel
        assertEquals("test", userModel.getUserName());
        assertEquals("test@gmail.com", userModel.getuserEmail());
        assertEquals("testPassword", userModel.getuserPassword());
    }
    @Test
    public void testReadUser_NonExistentUser() {
        // if there is no entry found in our database then return null 

        String testId = "661da231d125306db4dacc75";
        Document filterDocument = new Document("_id", new ObjectId(testId));

        when(mockCollection.find(eq(filterDocument))).thenReturn(mockFindIterable);
        when(mockFindIterable.first()).thenReturn(null);

        CompletableFuture<UserModel> value = userDao.read(testId);
        UserModel userModel = value.join();

        // Verify that null is returned when the user does not exist
        assertNull(userModel);
    }

    @Test
    public void testUpdateUser_Success() {

        String testId = "661da231d125306db4dacc75";
        // Create a UserModel object with updated data
        UserModel updatedUserModel = new UserModel();
        updatedUserModel.setUserName("updatedUserName");
        updatedUserModel.setuserEmail("updatedEmail@gmail.com");
        updatedUserModel.setuserPassword("updatedPassword");
            // Mocking replaceOne method
        when(mockCollection.replaceOne(Filters.eq("_id", new ObjectId(testId)), userModelToDocument(updatedUserModel)))
        .thenReturn(mockUpdateResult);

        CompletableFuture<Void> value = userDao.update(testId, updatedUserModel);
        value.join();
        // Verify that replaceOne method was called with the correct arguments
        verify(mockCollection).replaceOne(
        eq(Filters.eq("_id", new ObjectId(testId))),
        eq(userModelToDocument(updatedUserModel))
        );
        
    }
    // Helper method to convert UserModel to Document
    private Document userModelToDocument(UserModel userModel) {
        return new Document("userName", userModel.getUserName())
            .append("userEmail", userModel.getuserEmail())
            .append("userPassword", userModel.getuserPassword());
    }

}
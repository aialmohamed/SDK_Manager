package databasetests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.Invocation;
import org.mockito.junit.MockitoJUnitRunner;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import software.login.model.UserModel;
import software.utils.Database.Dao.UserDao;

@RunWith(MockitoJUnitRunner.class)
public class UserDaoTest {

    @Mock
    private MongoCollection<Document> collection;
    @Mock
    private FindIterable<Document> mockFindIterable;

    private UserDao userDao;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userDao = new UserDao(collection);
    }
    @Test
    public void testCreateUserDocument() throws Exception{

        UserModel user = new UserModel("testUser", "testEmail@gmail.com", "testPassword");
        doAnswer(invocation -> {
            Document doc = invocation.getArgument(0);
            doc.put("_id", new ObjectId());
            return null;
        }).when(collection).insertOne(any(Document.class));

        doAnswer(invocation -> {
            Document doc = invocation.getArgument(1);
            assertNotNull(doc.getString("userId")); // check if userId is not null
            assertNotNull(user.getUserId()); // check if userId is not null
            assertEquals(doc.getString("userId"), user.getUserId()); // check if userId is equal
            return null;
        }).when(collection).replaceOne(any(Bson.class), any(Document.class));
        
        userDao.create(user).get();
        ArgumentCaptor<Document> documentCaptor = ArgumentCaptor.forClass(Document.class);
        verify(collection, times(1)).insertOne(documentCaptor.capture());
        Document insertedDoc = documentCaptor.getValue();

        assertEquals(user.getUserName(), insertedDoc.getString("userName")); // check if userName is equal to userName in collection
        assertEquals(user.getuserEmail(), insertedDoc.getString("userEmail")); // check if userEmail is equal to userEmail in collection
        assertEquals(user.getuserPassword(), insertedDoc.getString("userPassword")); // check if userPassword is equal to userPassword in collection
    }


    @Test
    public void testReadUserDocument() throws Exception {
        Document mockDocument = new Document("userId", "123")
        .append("userName", "testUser")
        .append("userEmail", "test@example.com")
        .append("userPassword", "password123");

        // Mocking the behavior of collection.find() to return a mock FindIterable
        when(collection.find(new Document("userId", "123"))).thenReturn(mockFindIterable);
        
        // Mocking the behavior of FindIterable.first() to return the mockDocument
        when(mockFindIterable.first()).thenReturn(mockDocument);
        // Creating an instance of UserDao with the mocked collection
        UserDao userDao = new UserDao(collection);

        // Invoking the read method with a mock user ID
        CompletableFuture<UserModel> futureUser = userDao.read("123");

        // Extracting the UserModel from the CompletableFuture
        UserModel user = futureUser.join();

        // Assertions to verify if the returned UserModel matches the expected values
        assertEquals("123", user.getUserId());
        assertEquals("testUser", user.getUserName());
        assertEquals("test@example.com", user.getuserEmail());
        assertEquals("password123", user.getuserPassword());
    }
    
    @Test
    public void testUpdateUserDocument() throws Exception {
    
    }
}

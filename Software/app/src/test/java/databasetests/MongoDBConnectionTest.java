package databasetests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.util.concurrent.CompletableFuture;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

import software.utils.Database.MongoDBConnectionAsync;


@RunWith(MockitoJUnitRunner.class)
public class MongoDBConnectionTest {

    @Mock
    private MongoClient mongoClient;

    @Mock
    private MongoDatabase mongoDatabase;

    private MongoDBConnectionAsync mongoDBConnectionAsync;

    @Before
    public void setUp() {
        MongoDBConnectionAsync.resetInstance();
        lenient().when(mongoClient.getDatabase(ArgumentMatchers.anyString())).thenReturn(mongoDatabase);
        mongoDBConnectionAsync = MongoDBConnectionAsync.getInstance();
        mongoDBConnectionAsync.setMongoClient(mongoClient); // You'll need to add a setter for mongoClient in MongoDBConnectionAsync
        
    }

    @Test
    public void testConnection() {
        assertNotNull(mongoDBConnectionAsync);
    }

    @Test
    public void testDatabaseConnection() {
        CompletableFuture<MongoDatabase> futureDatabase = mongoDBConnectionAsync.connectAndGetDatabaseAsync();
        MongoDatabase database = futureDatabase.join(); // This will block until the future is complete
        assertNotNull(database);
    }
    @Test
    public void testDatabaseName() {
        lenient().when(mongoDatabase.getName()).thenReturn("expectedDatabaseName");
        CompletableFuture<MongoDatabase> futureDatabase = mongoDBConnectionAsync.connectAndGetDatabaseAsync("localhost", 27017, "expectedDatabaseName");
        MongoDatabase database = futureDatabase.join(); // This will block until the future is complete
        assertEquals("expectedDatabaseName", database.getName());
    }

}

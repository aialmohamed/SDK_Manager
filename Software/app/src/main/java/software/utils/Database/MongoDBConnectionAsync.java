package software.utils.Database;

import java.util.concurrent.CompletableFuture;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

// this Class is for the connection to our MongoDB 
// the connection shall be a singelton and async
public class MongoDBConnectionAsync {

     // for the Default Connection :
     private static final String DEFAULT_HOST = "localhost";
     private static final int DEFAULT_PORT = 27017;
     private static final String DEFAULT_DATABASE_NAME = "sdkManagerDB";
     private static MongoClient mongoClient;
 
     // singleton : 
     private static MongoDBConnectionAsync mongoDBConnectionAsyncInstance = null;
 
     private MongoDBConnectionAsync() {
         mongoClient = MongoClients.create(); // Default connection to localhost:27017
     }
 
     public static synchronized MongoDBConnectionAsync getInstance() {
         if (mongoDBConnectionAsyncInstance == null) {
             mongoDBConnectionAsyncInstance = new MongoDBConnectionAsync();
         }
         return mongoDBConnectionAsyncInstance;
     }
 
     public CompletableFuture<MongoDatabase> connectAndGetDatabaseAsync() {
         return connectAndGetDatabaseAsync(DEFAULT_HOST, DEFAULT_PORT, DEFAULT_DATABASE_NAME);
     }
 
     public CompletableFuture<MongoDatabase> connectAndGetDatabaseAsync(String host, int port, String databaseName) {
         return CompletableFuture.supplyAsync(() -> {
             mongoClient = MongoClients.create("mongodb://" + host + ":" + port);
             return mongoClient.getDatabase(databaseName);
         });
     }
    public void setMongoClient(MongoClient mongoClient) {
        MongoDBConnectionAsync.mongoClient = mongoClient;
    }
    public void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }

    // for testing purposes
    public static void resetInstance() {
        mongoDBConnectionAsyncInstance = null;
    }
}

package software.utils.Database.Dao;

import java.util.concurrent.CompletableFuture;
import software.login.model.UserModel;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class UserDao implements IDao<UserModel>{


    private MongoCollection<Document> collection;

    
    public UserDao(MongoDatabase database,String collectionName) {
        this.collection = database.getCollection(collectionName);
    }

    // for test purposes

    public UserDao(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    @Override
    public CompletableFuture<Void> create(UserModel item) {
        
        Document doc = new Document("userName", item.getUserName())
        .append("userEmail", item.getuserEmail())
        .append("userPassword", item.getuserPassword());
        return CompletableFuture.runAsync(() -> {
            collection.insertOne(doc);
        });
    }



    @Override
    public CompletableFuture<Void> delete(String id) {
        return CompletableFuture.runAsync(() -> {
            Document document = new Document("_id", new ObjectId(id));
            collection.deleteOne(document);
        });
    }

    @Override
    public CompletableFuture<UserModel> read(String id) {
        return CompletableFuture.supplyAsync(() -> {
            Document document = collection.find(new Document("_id", new ObjectId(id))).first();
            if (document == null) {
                return null;
            }
            return new UserModel(document.getString("userName"), document.getString("userEmail"), document.getString("userPassword"));
        });
    }

    @Override
    public CompletableFuture<Void> update(String id, UserModel item) {
        Document doc = new Document("userName", item.getUserName())
                .append("userEmail", item.getuserEmail())
                .append("userPassword", item.getuserPassword());
                return CompletableFuture.runAsync(() -> collection.replaceOne(Filters.eq("_id", new ObjectId(id)), doc));
    }

    // TODO : Test those methods
    
    // we also need a method to find a user _Id a user by name
    public CompletableFuture<UserModel> findUserByName(String name) {
        return CompletableFuture.supplyAsync(() -> {
            Document document = collection.find(new Document("userName", name)).first();
            if (document == null) {
                return null;
            }
            return new UserModel(document.getString("userName"), document.getString("userEmail"), document.getString("userPassword"));
        });
    }
    // find user by email
    public CompletableFuture<UserModel> findUserByEmail(String email) {
        return CompletableFuture.supplyAsync(() -> {
            Document document = collection.find(new Document("userEmail", email)).first();
            if (document == null) {
                return null;
            }
            return new UserModel(document.getString("userName"), document.getString("userEmail"), document.getString("userPassword"));
        });
    }
    
    
}

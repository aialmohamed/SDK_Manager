package software.utils.Database.Dao;

import java.util.concurrent.CompletableFuture;
import software.login.model.UserModel;
import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class UserDao implements IDao<UserModel>{


    private MongoCollection<Document> collection;

    
    public UserDao(MongoDatabase database,String collectionName) {
        this.collection = database.getCollection(collectionName);
    }

    @Override
    public CompletableFuture<Void> create(UserModel item) {
        
        Document doc = new Document("userName", item.getUserName())
        .append("userEmail", item.getuserEmail())
        .append("userPassword", item.getuserPassword());
        return CompletableFuture.runAsync(() -> {
            collection.insertOne(doc);
            String id = doc.getObjectId("_id").toString();
            item.setUserId(id);
            Document updatedDoc = new Document("$set", new Document("userId", id));
            collection.updateOne(Filters.eq("_id", doc.getObjectId("_id")), updatedDoc);
        });
    }

    @Override
    public CompletableFuture<Void> delete(String id) {
        return CompletableFuture.runAsync(() -> {
            Document document = new Document("userId", id);
            collection.deleteOne(document);
        });
    }

    @Override
    public CompletableFuture<UserModel> read(String id) {
        return CompletableFuture.supplyAsync(() -> {
            Document document = collection.find(new Document("userId", id)).first();
            if (document == null) {
                return null;
            }
            return new UserModel(document.getString("userId"), document.getString("userName"), document.getString("userEmail"), document.getString("userPassword"));
        });
    }

    @Override
    public CompletableFuture<Void> update(String id, UserModel item) {
        Document doc = new Document("userId", item.getUserId())
                .append("userName", item.getUserName())
                .append("userEmail", item.getuserEmail())
                .append("userPassword", item.getuserPassword());
        return CompletableFuture.runAsync(() -> collection.replaceOne(Filters.eq("userId", id), doc));
    }
    
}

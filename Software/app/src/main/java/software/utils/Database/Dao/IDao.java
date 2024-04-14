package software.utils.Database.Dao;

import java.util.concurrent.CompletableFuture;

public interface IDao<T> {

    CompletableFuture<Void> create(T item);
    CompletableFuture<T> read(String id);
    CompletableFuture<Void> update(String id,T item);
    CompletableFuture<Void> delete(String id);
}

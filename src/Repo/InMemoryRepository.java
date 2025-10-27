package Repo;

import java.util.*;

public class InMemoryRepository<T> {
    protected final Map<String, T> storage = new LinkedHashMap<>();

    public T save(String id, T obj) {
        storage.put(id, obj);
        return obj;
    }

    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Optional<T> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    public void delete(String id) {
        storage.remove(id);
    }
}

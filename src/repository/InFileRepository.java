package repository;

import model.HasId;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// This class replaces InMemoryRepository logic with File Logic
public abstract class InFileRepository<T> implements InMemoryRepository<T> {
    // Note: T must extend an interface (e.g., HasId) that guarantees a getId() method exists.
    // If you don't have a HasId interface, you might need to rely on your Model base class.

    private final String filename;
    private final ObjectMapper objectMapper;
    private List<T> data;
    private final Class<T> type;

    public InFileRepository(String filename, Class<T> type) {
        this.filename = "src/main/resources/data/" + filename;
        this.objectMapper = new ObjectMapper();
        this.type = type;
        this.data = new ArrayList<>();
        readFromFile(); // Load data on startup
    }

    // READ from File
    private void readFromFile() {
        File file = new File(filename);
        if (!file.exists()) {
            this.data = new ArrayList<>();
            return;
        }
        try {
            // Jackson logic to read a List of T
            this.data = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, type));
        } catch (IOException e) {
            e.printStackTrace();
            this.data = new ArrayList<>();
        }
    }

    // WRITE to File
    private void writeToFile() {
        try {
            objectMapper.writeValue(new File(filename), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // --- CRUD Operations (Must match your existing Interface) ---

    @Override
    public T save(T entity) {
        // Logic to handle update vs create based on ID usually goes here
        // For simplicity, assuming add:
        data.add(entity);
        writeToFile(); // Save changes immediately [cite: 53]
        return entity;
    }

    @Override
    public Optional<T> findOne(Integer id) { // Or String id, depending on your model
        return data.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    @Override
    public Iterable<T> findAll() {
        return data;
    }

    @Override
    public void delete(Integer id) {
        data.removeIf(e -> e.getId().equals(id));
        writeToFile(); // Save changes immediately
    }

    @Override
    public T update(T entity) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId().equals(entity.getId())) {
                data.set(i, entity);
                writeToFile();
                return entity;
            }
        }
        return null;
    }
}
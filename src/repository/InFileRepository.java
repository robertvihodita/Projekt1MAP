package repository;

import model.HasId;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class InFileRepository<T extends HasId> {

    protected List<T> data;
    private final String filename;
    private final ObjectMapper objectMapper;
    private final Class<T> type;

    public InFileRepository(String filename, Class<T> type) {
        this.filename = "src/main/resources/data/" + filename;
        this.objectMapper = new ObjectMapper();
        this.type = type;
        this.data = new ArrayList<>();
        readFromFile();
    }

    private void readFromFile() {
        File file = new File(filename);
        if (!file.exists() || file.length() == 0) {
            this.data = new ArrayList<>();
            return;
        }
        try {
            this.data = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, type));
        } catch (IOException e) {
            System.err.println("ERROR: Could not read from file: " + filename);
            e.printStackTrace();
            this.data = new ArrayList<>();
        }
    }

    private void writeToFile() {
        try {
            objectMapper.writeValue(new File(filename), data);
        } catch (IOException e) {
            System.err.println("ERROR: Could not write to file: " + filename);
            e.printStackTrace();
        }
    }

    public T save(String id, T entity) {
        data.removeIf(e -> e.getId().equals(id));
        data.add(entity);
        return entity;
    }

    public List<T> findAll() {
        return new ArrayList<>(data);
    }

    public Optional<T> findById(String id) {
        return data.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    public void delete(String id) {
        boolean removed = data.removeIf(e -> e.getId().equals(id));
        if (removed) {
            writeToFile();
        }
    }
}
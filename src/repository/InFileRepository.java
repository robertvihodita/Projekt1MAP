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
        // FIX: Reverting to a more explicit path relative to the project structure
        // This path is reliable when running from the IDE's project root via Maven/Spring Boot runner.
        this.filename = "Projekt1MAP/src/resources/data/" + filename;

        this.objectMapper = new ObjectMapper();
        this.type = type;
        this.data = new ArrayList<>();
        readFromFile();
    }

    private void readFromFile() {
        File file = new File(filename);

        // --- ADDED DEBUGGING ---
        System.out.println("DEBUG: Attempting to load file at: " + file.getAbsolutePath());
        // -----------------------

        if (!file.exists() || file.length() == 0) {
            System.out.println("DEBUG: File not found or empty: " + file.getAbsolutePath());
            this.data = new ArrayList<>();
            return;
        }
        try {
            this.data = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, type));
            System.out.println("DEBUG: Successfully loaded " + this.data.size() + " records from: " + file.getName());
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

        // FIX: Calling writeToFile to persist the changes
        writeToFile();

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
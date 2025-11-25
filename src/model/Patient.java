package model;

import java.util.ArrayList;
import java.util.List;

public class Patient implements HasId { // ADDED: implements HasId
    private String id;
    private String name;
    private List<String> appointments = new ArrayList<>();

    public Patient() {
    }

    public Patient(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAppointments() {
        return this.appointments;
    }

    public enum Status {
        ADMITTED,
        DISCHARGED,
        UNDER_OBSERVATION,
        IN_TREATMENT
    }
}
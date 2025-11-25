package model;

import java.util.ArrayList;
import java.util.List;

public class Hospital implements HasId { // ADDED: implements HasId
    private String id;
    private String name;
    private String city;
    // NOTE: Removed `departments` and `rooms` Lists to simplify JSON/Jackson compatibility
    // based on original model.

    public Hospital() {
    }

    public Hospital(String id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
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

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
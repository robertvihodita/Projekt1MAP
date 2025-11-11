package model;

import java.util.ArrayList;
import java.util.List;

public class Hospital {
    private String id;
    private String name;
    private String city;
    private List<Department> departments = new ArrayList<>();
    private List<RoomType> rooms = new ArrayList<>();

    public Hospital() {
    }

    public Hospital(String id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public String getId() {
        return this.id;
    }

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

    public List<Department> getDepartments() {
        return this.departments;
    }

    public List<RoomType> getRooms() {
        return this.rooms;
    }

    public enum Department {
        CARDIOLOGY,
        NEUROLOGY,
        ONCOLOGY,
        PEDIATRICS,
        EMERGENCY,
        RADIOLOGY,
        ORTHOPEDICS
    }

    public enum RoomType {
        ICU,
        GENERAL,
        PRIVATE,
        SEMI_PRIVATE,
        OPERATION_THEATER,
        EMERGENCY_ROOM
    }
}

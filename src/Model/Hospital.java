package Model;

import java.util.ArrayList;
import java.util.List;

public class Hospital {
    private String id;
    private String name;
    private String city;
    private List<String> departments = new ArrayList<>();
    private List<String> rooms = new ArrayList<>();

    public Hospital() {}
    public Hospital(String id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public List<String> getDepartments() { return departments; }
    public List<String> getRooms() { return rooms; }
}
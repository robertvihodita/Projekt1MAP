package Model;
import java.util.ArrayList;
import java.util.List;

public class Patient {
    private String id;
    private String name;
    private List<String> appointments = new ArrayList<>();

    public Patient() {}
    public Patient(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<String> getAppointments() { return appointments; }
}
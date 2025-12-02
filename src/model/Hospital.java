package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hospitals")
public class Hospital implements HasId {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "Hospital name is required")
    @Size(min = 5, max = 100, message = "Name must be between 5 and 100 characters")
    private String name;

    @NotBlank(message = "City is required")
    private String city;

    // RELATIONSHIP: One-to-Many with Department
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Department> departments = new HashSet<>();

    // RELATIONSHIP: One-to-Many with Room
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Room> rooms = new HashSet<>();

    public Hospital() {
    }

    // --- Getters and Setters ---

    @Override
    public String getId() { return this.id; }

    @Override
    public void setId(String id) { this.id = id; }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public String getCity() { return this.city; }
    public void setCity(String city) { this.city = city; }

    public Set<Department> getDepartments() { return departments; }
    public void setDepartments(Set<Department> departments) { this.departments = departments; }

    public Set<Room> getRooms() { return rooms; }
    public void setRooms(Set<Room> rooms) { this.rooms = rooms; }
}
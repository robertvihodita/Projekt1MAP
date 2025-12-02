package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient implements HasId {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "Patient name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ADMITTED; // Set a default status

    // RELATIONSHIP: One-to-Many with Appointment
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Appointment> appointments = new HashSet<>();

    public Patient() {
    }

    // --- Getters and Setters ---

    @Override
    public String getId() { return this.id; }
    @Override
    public void setId(String id) { this.id = id; }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public Set<Appointment> getAppointments() { return appointments; }
    public void setAppointments(Set<Appointment> appointments) { this.appointments = appointments; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    // Removed the simple List<String> appointments

    public enum Status {
        ADMITTED,
        DISCHARGED,
        UNDER_OBSERVATION,
        IN_TREATMENT
    }
}
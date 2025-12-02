package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "rooms")
public class Room implements HasId {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    // RELATIONSHIP: Many-to-One with Hospital
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id", nullable = false)
    @NotNull(message = "Hospital is required")
    private Hospital hospital;

    @Min(value = 1, message = "Capacity must be at least 1")
    private double capacity; // Using double is unusual for capacity, often integer. Keep for now.

    @NotBlank(message = "Room number is required")
    @Size(min = 1, max = 10, message = "Number must be between 1 and 10 characters")
    private String number;

    @Enumerated(EnumType.STRING)
    private RoomStatus status;

    // RELATIONSHIP: One-to-Many with Appointment
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Appointment> appointments = new HashSet<>();

    public Room() {
    }

    // --- Getters and Setters ---

    @Override
    public String getId() { return id; }
    @Override
    public void setId(String id) { this.id = id; }

    public Hospital getHospital() { return hospital; }
    public void setHospital(Hospital hospital) { this.hospital = hospital; }

    public double getCapacity() { return capacity; }
    public void setCapacity(double capacity) { this.capacity = capacity; }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    public RoomStatus getStatus() { return status; }
    public void setStatus(RoomStatus status) { this.status = status; }

    public Set<Appointment> getAppointments() { return appointments; }
    public void setAppointments(Set<Appointment> appointments) { this.appointments = appointments; }

    public enum RoomStatus {
        AVAILABLE,
        OCCUPIED
    }
}
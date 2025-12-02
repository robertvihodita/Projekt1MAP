package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "departments")
public class Department implements HasId {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    // RELATIONSHIP: Many-to-One with Hospital
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

    // RELATIONSHIP: One-to-Many with MedicalStaff
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MedicalStaff> staff = new HashSet<>();

    public Department() {
    }

    // --- Getters and Setters ---

    @Override
    public String getId() { return id; }

    @Override
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Hospital getHospital() { return hospital; }
    public void setHospital(Hospital hospital) { this.hospital = hospital; }

    public Set<MedicalStaff> getStaff() { return staff; }
    public void setStaff(Set<MedicalStaff> staff) { this.staff = staff; }

    // Removed the simple String hospitalId field
}
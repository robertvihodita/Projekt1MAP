package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@MappedSuperclass // This class will not have its own table
public abstract class MedicalStaff implements HasId {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "Staff name is required")
    private String name;

    // RELATIONSHIP: Many-to-One with Department
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    // RELATIONSHIP: Many-to-Many with Appointment via MedicalStaffAppointment
    @OneToMany(mappedBy = "medicalStaff", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MedicalStaffAppointment> appointmentAssignments = new HashSet<>();

    public MedicalStaff() {
    }

    public MedicalStaff(String name, String departmentId) {
        this.name = name;
        // The department property must be set via the object, not the ID string
    }

    // --- Getters and Setters ---

    @Override
    public String getId() { return this.id; }

    @Override
    public void setId(String id) { this.id = id; }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    public Set<MedicalStaffAppointment> getAppointmentAssignments() { return appointmentAssignments; }
    public void setAppointmentAssignments(Set<MedicalStaffAppointment> appointmentAssignments) { this.appointmentAssignments = appointmentAssignments; }

    // Removed the simple String departmentId field and List<String> appointments

    public enum Role {
        DOCTOR,
        NURSE,
        SURGEON,
        THERAPIST,
        TECHNICIAN
    }
}
package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "appointments")
public class Appointment implements HasId {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    // RELATIONSHIP: Many-to-One with Department
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    @NotNull(message = "Department is required")
    private Department department;

    // RELATIONSHIP: Many-to-One with Patient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    @NotNull(message = "Patient is required")
    private Patient patient;

    // RELATIONSHIP: Many-to-One with Room
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id") // Assuming room is optional or will be handled
    private Room room;

    @FutureOrPresent(message = "Admission date must be in the present or future")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull(message = "Admission date is required")
    private LocalDateTime admissionDate; // Changed to LocalDateTime for proper date validation

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status is required")
    private AppointmentStatus status;

    // RELATIONSHIP: Many-to-Many with MedicalStaff via MedicalStaffAppointment
    // Handled by the other side (MedicalStaffAppointment)
    @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MedicalStaffAppointment> medicalStaffAssignments = new HashSet<>();

    public Appointment() {
    }

    // --- Getters and Setters ---

    @Override
    public String getId() { return id; }

    @Override
    public void setId(String id) { this.id = id; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }

    public LocalDateTime getAdmissionDate() { return admissionDate; }
    public void setAdmissionDate(LocalDateTime admissionDate) { this.admissionDate = admissionDate; }

    public AppointmentStatus getStatus() { return status; }
    public void setStatus(AppointmentStatus status) { this.status = status; }

    public Set<MedicalStaffAppointment> getMedicalStaffAssignments() { return medicalStaffAssignments; }
    public void setMedicalStaffAssignments(Set<MedicalStaffAppointment> medicalStaffAssignments) { this.medicalStaffAssignments = medicalStaffAssignments; }

    public enum AppointmentStatus {
        ACTIVE,
        COMPLETED
    }
}
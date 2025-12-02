package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "medical_staff_appointments")
public class MedicalStaffAppointment implements HasId {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    // RELATIONSHIP: Many-to-One with Appointment
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id", nullable = false)
    @NotNull(message = "Appointment is required")
    private Appointment appointment;

    // RELATIONSHIP: Many-to-One with MedicalStaff (Doctor used for concrete assignment)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medical_staff_id", nullable = false)
    @NotNull(message = "Medical Staff is required")
    private Doctor medicalStaff;

    // NEW: Field for the Status enum, persisted as a String
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status is required")
    private Status status;

    public MedicalStaffAppointment() {
    }

    // --- Getters and Setters ---

    @Override
    public String getId() { return this.id; }
    @Override
    public void setId(String id) { this.id = id; }

    public Appointment getAppointment() { return appointment; }
    public void setAppointment(Appointment appointment) { this.appointment = appointment; }

    public Doctor getMedicalStaff() { return medicalStaff; }
    public void setMedicalStaff(Doctor medicalStaff) { this.medicalStaff = medicalStaff; }

    // NEW: Getter for Status
    public Status getStatus() { return status; }

    // NEW: Setter for Status (This fixes the 'Cannot resolve method' error)
    public void setStatus(Status status) { this.status = status; }

    public enum Status {
        SCHEDULED,
        COMPLETED,
        CANCELED,
        RESCHEDULED
    }
}
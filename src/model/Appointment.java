package model;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Appointment {
    private String id;
    private String departmentId;
    private String patientId;
    private LocalDateTime admissionDate;
    private String status; // "Active"/"Completed" // enum & la fel pt toate de tipul asta ; datele cu localdate
    private List<String> medicalStaff = new ArrayList<>();

    public Appointment() {}
    public Appointment(String id, String departmentId, String patientId, LocalDateTime admissionDate, String status) {
        this.id = id;
        this.departmentId = departmentId;
        this.patientId = patientId;
        this.admissionDate = admissionDate;
        this.status = status;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getDepartmentId() { return departmentId; }
    public void setDepartmentId(String departmentId) { this.departmentId = departmentId; }
    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }
    public LocalDateTime getAdmissionDate() { return admissionDate; }
    public void setAdmissionDate(LocalDateTime admissionDate) { this.admissionDate = admissionDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<String> getMedicalStaff() { return medicalStaff; }
}

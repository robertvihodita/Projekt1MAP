package model;

import java.util.ArrayList;
import java.util.List;

public class Appointment {
    private String id;
    private String departmentId;
    private String patientId;
    private String admissionDate;
    private AppointmentStatus status;
    private List<String> medicalStaffIds = new ArrayList<>();

    public Appointment(String id, String departmentId, String patientId, String admissionDate, AppointmentStatus status) {
        this.id = id;
        this.departmentId = departmentId;
        this.patientId = patientId;
        this.admissionDate = admissionDate;
        this.status = status;
    }

    public String getId() { return id; }
    public String getDepartmentId() { return departmentId; }
    public String getPatientId() { return patientId; }
    public String getAdmissionDate() { return admissionDate; }
    public AppointmentStatus getStatus() { return status; }
    public void setStatus(AppointmentStatus status) { this.status = status; }

    public List<String> getMedicalStaffIds() { return medicalStaffIds; }
    public void addMedicalStaffId(String staffId) { this.medicalStaffIds.add(staffId); }


    public enum AppointmentStatus {
        ACTIVE,
        COMPLETED
    }
}

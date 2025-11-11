//package model;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Appointment {
//    private String id;
//    private String departmentId;
//    private String patientId;
//    private String admissionDate;
//    private AppointmentStatus status;
//    private List<String> medicalStaffIds = new ArrayList<>();
//
//    public Appointment(String id, String departmentId, String patientId, String admissionDate, AppointmentStatus status) {
//        this.id = id;
//        this.departmentId = departmentId;
//        this.patientId = patientId;
//        this.admissionDate = admissionDate;
//        this.status = status;
//    }
//
//    public String getId() { return id; }
//    public String getDepartmentId() { return departmentId; }
//    public String getPatientId() { return patientId; }
//    public String getAdmissionDate() { return admissionDate; }
//    public AppointmentStatus getStatus() { return status; }
//    public void setStatus(AppointmentStatus status) { this.status = status; }
//
//    public List<String> getMedicalStaffIds() { return medicalStaffIds; }
//    public void addMedicalStaffId(String staffId) { this.medicalStaffIds.add(staffId); }
//
//
//    public enum AppointmentStatus {
//        ACTIVE,
//        COMPLETED
//    }
//}

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

    // ✅ ADD THIS EMPTY CONSTRUCTOR
    public Appointment() {
        // Spring Boot needs this to create an empty object for form binding
    }

    public Appointment(String id, String departmentId, String patientId, String admissionDate, AppointmentStatus status) {
        this.id = id;
        this.departmentId = departmentId;
        this.patientId = patientId;
        this.admissionDate = admissionDate;
        this.status = status;
    }

    // --- Getters (These are fine) ---
    public String getId() { return id; }
    public String getDepartmentId() { return departmentId; }
    public String getPatientId() { return patientId; }
    public String getAdmissionDate() { return admissionDate; }
    public AppointmentStatus getStatus() { return status; }
    public List<String> getMedicalStaffIds() { return medicalStaffIds; }

    // --- Setters (You already have setStatus) ---
    public void setStatus(AppointmentStatus status) { this.status = status; }

    // ✅ ADD THESE MISSING SETTERS
    public void setId(String id) {
        this.id = id;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    // --- Other methods (These are fine) ---
    public void addMedicalStaffId(String staffId) { this.medicalStaffIds.add(staffId); }


    public enum AppointmentStatus {
        ACTIVE,
        COMPLETED
    }
}

package model;

public class MedicalStaffAppointment implements HasId { // ADDED: implements HasId
    private String id;
    private String appointmentId;
    private String medicalStaffId;

    public MedicalStaffAppointment() {
    }

    public MedicalStaffAppointment(String id, String appointmentId, String medicalStaffId) {
        this.id = id;
        this.appointmentId = appointmentId;
        this.medicalStaffId = medicalStaffId;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getAppointmentId() {
        return this.appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getMedicalStaffId() {
        return this.medicalStaffId;
    }

    public void setMedicalStaffId(String medicalStaffId) {
        this.medicalStaffId = medicalStaffId;
    }

    public enum Status {
        SCHEDULED,
        COMPLETED,
        CANCELED,
        RESCHEDULED
    }
}
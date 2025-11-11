package model;

import java.util.ArrayList;
import java.util.List;

public abstract class MedicalStaff {
    private String id;
    private String name;
    private List<String> appointments = new ArrayList<>();
    private String departmentId;

    public MedicalStaff() {
    }

    public MedicalStaff(String id, String name, String departmentId) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAppointments() {
        return this.appointments;
    }

    public String getDepartmentId() {
        return this.departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }


    public enum Role {
        DOCTOR,
        NURSE,
        SURGEON,
        THERAPIST,
        TECHNICIAN
    }
}

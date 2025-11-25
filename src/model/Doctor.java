package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
// FIXED: Doctor must correctly extend MedicalStaff
public class Doctor extends MedicalStaff implements HasId {
    private String licenseNumber;

    public Doctor() {
    }

    public Doctor(String id, String name, String departmentId, String licenseNumber) {
        super(id, name, departmentId);
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseNumber() { return licenseNumber; }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
}
package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "doctors")
public class Doctor extends MedicalStaff {

    @NotBlank(message = "License Number is required")
    @Size(min = 5, max = 20, message = "License Number must be between 5 and 20 characters")
    private String licenseNumber;

    public Doctor() {
        super();
    }


    public Doctor(String name, String departmentId, String licenseNumber) {
        super(name, departmentId);
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }

    @Override
    public String getRole() {
        return "Doctor";
    }
}
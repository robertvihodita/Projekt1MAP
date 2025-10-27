package Model;

public class Doctor extends MedicalStaff {
    private String licenseNumber;

    public Doctor() { super(); }
    public Doctor(String id, String name, String departmentId, String licenseNumber) {
        super(id, name, departmentId);
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }
}


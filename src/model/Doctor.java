package model;

public class Doctor {
    private String id;
    private String name;
    private String departmentId;
    private String licenseNumber;

    public Doctor() {
    }

    public Doctor(String id, String name, String departmentId, String licenseNumber) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
        this.licenseNumber = licenseNumber;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDepartmentId() { return departmentId; }
    public String getLicenseNumber() { return licenseNumber; }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
}
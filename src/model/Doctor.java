//package model;
//
//public class Doctor extends MedicalStaff {
//    private String licenseNumber;
//
//    public Doctor() { super(); }
//    public Doctor(String id, String name, String departmentId, String licenseNumber) {
//        super(id, name, departmentId);
//        this.licenseNumber = licenseNumber;
//    }
//
//    public String getLicenseNumber() { return licenseNumber; }
//    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }
//}
//

package model;

public class Doctor {
    private String id;
    private String name;
    private String departmentId;
    private String licenseNumber;

    // ✅ ADD THIS EMPTY CONSTRUCTOR
    public Doctor() {
        // Spring Boot needs this for form binding
    }

    public Doctor(String id, String name, String departmentId, String licenseNumber) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
        this.licenseNumber = licenseNumber;
    }

    // --- Getters ---
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDepartmentId() { return departmentId; }
    public String getLicenseNumber() { return licenseNumber; }

    // ✅ ADD ALL MISSING SETTERS
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

//package model;
//
//public class Department {
//    private String id;
//    private String name;
//    private String hospitalId;
//
//    public Department(String id, String name, String hospitalId) {
//        this.id = id;
//        this.name = name;
//        this.hospitalId = hospitalId;
//    }
//
//    public String getId() { return id; }
//    public String getName() { return name; }
//    public String getHospitalId() { return hospitalId; }
//
//}
//

package model;

public class Department {
    private String id;
    private String name;
    private String hospitalId;
    // You can add 'private List<String> rooms = new ArrayList();' here
    // if you want, but the controller below doesn't use it yet.

    // ✅ ADD THIS EMPTY CONSTRUCTOR
    public Department() {
        // Spring Boot needs this for form binding
    }

    public Department(String id, String name, String hospitalId) {
        this.id = id;
        this.name = name;
        this.hospitalId = hospitalId;
    }

    // --- Getters ---
    public String getId() { return id; }
    public String getName() { return name; }
    public String getHospitalId() { return hospitalId; }

    // ✅ ADD THESE MISSING SETTERS
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }
}

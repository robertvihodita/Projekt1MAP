////
//// Source code recreated from a .class file by IntelliJ IDEA
//// (powered by FernFlower decompiler)
////
//
//package model;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Department {
//    private String id;
//    private String name;
//    private String hospitalId;
//    private List<String> rooms = new ArrayList();
//
//    public Department() {
//    }
//
//    public Department(String id, String name, String hospitalId) {
//        this.id = id;
//        this.name = name;
//        this.hospitalId = hospitalId;
//    }
//
//    public String getId() {
//        return this.id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return this.name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getHospitalId() {
//        return this.hospitalId;
//    }
//
//    public void setHospitalId(String hospitalId) {
//        this.hospitalId = hospitalId;
//    }
//
//    public List<String> getRooms() {
//        return this.rooms;
//    }
//}

// java
package model;

public class Department {
    private String id;
    private String name;
    private String hospitalId;

    public Department(String id, String name, String hospitalId) {
        this.id = id;
        this.name = name;
        this.hospitalId = hospitalId;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getHospitalId() { return hospitalId; }

}


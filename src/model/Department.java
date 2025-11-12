package model;

public class Department {
    private String id;
    private String name;
    private String hospitalId;

    public Department() {
    }

    public Department(String id, String name, String hospitalId) {
        this.id = id;
        this.name = name;
        this.hospitalId = hospitalId;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getHospitalId() { return hospitalId; }

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
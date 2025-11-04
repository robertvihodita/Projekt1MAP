//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package model;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String id;
    private String hospitalId;
    private double capacity;
    private String number;
    private String status;
    private List<String> appointments = new ArrayList();

    public Room() {
    }

    public Room(String id, String hospitalId, String number, double capacity, String status) {
        this.id = id;
        this.hospitalId = hospitalId;
        this.number = number;
        this.capacity = capacity;
        this.status = status;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHospitalId() {
        return this.hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public double getCapacity() {
        return this.capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getAppointments() {
        return this.appointments;
    }
}

package model;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String id;
    private String hospitalId;
    private double capacity;
    private String number;
    private RoomStatus status;
    private List<String> appointmentIds = new ArrayList<>();

    public Room(String id, String hospitalId, double capacity, String number, RoomStatus status) {
        this.id = id;
        this.hospitalId = hospitalId;
        this.capacity = capacity;
        this.number = number;
        this.status = status;
    }

    public String getId() { return id; }
    public String getHospitalId() { return hospitalId; }
    public double getCapacity() { return capacity; }
    public String getNumber() { return number; }
    public RoomStatus getStatus() { return status; }
    public void setStatus(RoomStatus status) { this.status = status; }

    public List<String> getAppointmentIds() { return appointmentIds; }
    public void addAppointmentId(String appointmentId) { this.appointmentIds.add(appointmentId); }


    public enum RoomStatus {
        AVAILABLE,
        OCCUPIED
    }
}

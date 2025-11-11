package controller;

import service.HospitalService;
import model.Hospital;
import java.util.List;

public class HospitalController {
    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    public void addHospital(Hospital hospital) {
        Hospital.Department departmentfromString = Hospital.Department.valueOf("CARDIOLOGY");
        System.out.println("Converted from string: " + departmentfromString);

        Hospital.RoomType roomTypeFromString = Hospital.RoomType.valueOf("EMERGENCY");
        System.out.println("Converted from string: " + roomTypeFromString);


        hospitalService.addHospital(hospital);
        System.out.println("Hospital added: " + hospital.getName());
    }

    public void showAllHospitals() {
        List<Hospital> hospitals = hospitalService.getAllHospitals();
        System.out.println("=== Hospitals ===");
        for (Hospital h : hospitals) {
            System.out.println(h.getId() + " - " + h.getName() + " (" + h.getCity() + ")");
        }
    }

    public void deleteHospital(String id) {
        hospitalService.deleteHospital(id);
        System.out.println("Deleted hospital with ID: " + id);
    }
}

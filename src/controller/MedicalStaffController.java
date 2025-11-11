package controller;

import service.MedicalStaffService;
import model.MedicalStaff;
import java.util.List;

public class MedicalStaffController {
    private final MedicalStaffService medicalStaffService;

    public MedicalStaffController(MedicalStaffService medicalStaffService) {
        this.medicalStaffService = medicalStaffService;
    }

    public void addStaff(MedicalStaff staff) {
        MedicalStaff.Role roleFromString = MedicalStaff.Role.valueOf("DOCTOR");
        System.out.println("Converted from string: " + roleFromString);

        medicalStaffService.addStaff(staff);
        System.out.println("Added staff: " + staff.getName());
    }

    public void showAllStaff() {
        List<MedicalStaff> staffList = medicalStaffService.getAllStaff();
        System.out.println("=== Medical Staff ===");
        for (MedicalStaff s : staffList) {
            System.out.println(s.getId() + " - " + s.getName() + " | Department: " + s.getDepartmentId());
        }
    }

    public void showStaffByDepartment(String departmentId) {
        List<MedicalStaff> staffList = medicalStaffService.getStaffByDepartment(departmentId);
        System.out.println("=== Staff in Department " + departmentId + " ===");
        for (MedicalStaff s : staffList) {
            System.out.println(s.getId() + " - " + s.getName());
        }
    }

    public void deleteStaff(String id) {
        medicalStaffService.deleteStaff(id);
        System.out.println("Deleted staff with ID: " + id);
    }
}

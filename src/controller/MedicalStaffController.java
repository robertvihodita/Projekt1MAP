//package controller;
//
//import service.MedicalStaffService;
//import model.MedicalStaff;
//import java.util.List;
//
//public class MedicalStaffController {
//    private final MedicalStaffService medicalStaffService;
//
//    public MedicalStaffController(MedicalStaffService medicalStaffService) {
//        this.medicalStaffService = medicalStaffService;
//    }
//
//    public void addStaff(MedicalStaff staff) {
//        MedicalStaff.Role roleFromString = MedicalStaff.Role.valueOf("DOCTOR");
//        System.out.println("Converted from string: " + roleFromString);
//
//        medicalStaffService.addStaff(staff);
//        System.out.println("Added staff: " + staff.getName());
//    }
//
//    public void showAllStaff() {
//        List<MedicalStaff> staffList = medicalStaffService.getAllStaff();
//        System.out.println("=== Medical Staff ===");
//        for (MedicalStaff s : staffList) {
//            System.out.println(s.getId() + " - " + s.getName() + " | Department: " + s.getDepartmentId());
//        }
//    }
//
//    public void showStaffByDepartment(String departmentId) {
//        List<MedicalStaff> staffList = medicalStaffService.getStaffByDepartment(departmentId);
//        System.out.println("=== Staff in Department " + departmentId + " ===");
//        for (MedicalStaff s : staffList) {
//            System.out.println(s.getId() + " - " + s.getName());
//        }
//    }
//
//    public void deleteStaff(String id) {
//        medicalStaffService.deleteStaff(id);
//        System.out.println("Deleted staff with ID: " + id);
//    }
//}

package controller;

import service.MedicalStaffService;
import model.MedicalStaff;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/staff") // All URLs will start with /staff
public class MedicalStaffController {
    private final MedicalStaffService medicalStaffService;

    public MedicalStaffController(MedicalStaffService medicalStaffService) {
        this.medicalStaffService = medicalStaffService;
    }

    /**
     * Replaces showAllStaff()
     * Handles GET http://localhost:8080/staff
     */
    @GetMapping
    public String showAllStaff(Model model) {
        List<MedicalStaff> staffList = medicalStaffService.getAllStaff();
        model.addAttribute("staffList", staffList); // Send list to HTML
        return "medical-staff"; // Loads "medical-staff.html"
    }

    /**
     * Replaces deleteStaff()
     * Handles GET http://localhost:8080/staff/delete/some-id
     */
    @GetMapping("/delete/{id}")
    public String deleteStaff(@PathVariable String id) {
        medicalStaffService.deleteStaff(id);
        return "redirect:/staff"; // Go back to the staff list
    }

    // We don't have an "add" page here because MedicalStaff is abstract.
    // Adding is handled by specific controllers like DoctorController.
}

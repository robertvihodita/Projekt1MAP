package controller;

import service.MedicalStaffService;
import service.HospitalService;
import service.DepartmentService; // NEW IMPORT
import model.MedicalStaff;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/staff")
public class MedicalStaffController {
    private final MedicalStaffService medicalStaffService;
    private final HospitalService hospitalService;
    private final DepartmentService departmentService; // NEW SERVICE

    public MedicalStaffController(MedicalStaffService medicalStaffService,
                                  HospitalService hospitalService,
                                  DepartmentService departmentService) {
        this.medicalStaffService = medicalStaffService;
        this.hospitalService = hospitalService;
        this.departmentService = departmentService;
    }

    @GetMapping
    public String showAllStaff(Model model,
                               @RequestParam(required = false) String hospitalId,
                               @RequestParam(required = false) String departmentId, // NEW PARAM
                               @RequestParam(required = false) String role,
                               @RequestParam(required = false, defaultValue = "name") String sortField,
                               @RequestParam(required = false, defaultValue = "asc") String sortDir) {


        model.addAttribute("staffList", medicalStaffService.getAllStaff(hospitalId, departmentId, role, sortField, sortDir));


        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        model.addAttribute("departments", departmentService.getAllDepartments()); // NEW ATTRIBUTE


        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("departmentId", departmentId); // NEW ATTRIBUTE
        model.addAttribute("role", role);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "medicalstaff/index";
    }

    @GetMapping("/{id}")
    public String showStaffDetails(@PathVariable String id, Model model) {
        Optional<MedicalStaff> staff = medicalStaffService.getStaffById(id);
        if (staff.isPresent()) {
            model.addAttribute("staff", staff.get());
            return "medicalstaff/details";
        }
        return "redirect:/staff";
    }

    @PostMapping("/{id}/delete")
    public String deleteStaff(@PathVariable String id) {
        medicalStaffService.deleteStaff(id);
        return "redirect:/staff";
    }
}
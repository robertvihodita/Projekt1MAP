package controller;

import service.MedicalStaffService;
import model.MedicalStaff;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/staff")
public class MedicalStaffController {
    private final MedicalStaffService medicalStaffService;

    public MedicalStaffController(MedicalStaffService medicalStaffService) {
        this.medicalStaffService = medicalStaffService;
    }

    @GetMapping
    public String showAllStaff(Model model) {
        List<MedicalStaff> staffList = medicalStaffService.getAllStaff();
        model.addAttribute("staffList", staffList);
        return "staff/index";
    }

    @PostMapping("/{id}/delete")
    public String deleteStaff(@PathVariable String id) {
        medicalStaffService.deleteStaff(id);
        return "redirect:/staff";
    }
}
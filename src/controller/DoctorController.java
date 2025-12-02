package controller;

import model.Doctor;
import service.DoctorService;
import service.DepartmentService; // Needed for dropdown
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;
    private final DepartmentService departmentService; // Used to populate Department dropdown

    public DoctorController(DoctorService doctorService, DepartmentService departmentService) {
        this.doctorService = doctorService;
        this.departmentService = departmentService;
    }

    private void loadFormDependencies(Model model) {
        model.addAttribute("departments", departmentService.getAllDepartments());
    }

    @GetMapping
    public String viewAllDoctors(Model model) {
        List<Doctor> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return "doctor/index";
    }


    @GetMapping("/new")
    public String showAddDoctorForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        loadFormDependencies(model);
        return "doctor/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditDoctorForm(@PathVariable String id, Model model) {
        Optional<Doctor> doctor = doctorService.getDoctorById(id);
        if (doctor.isPresent()) {
            model.addAttribute("doctor", doctor.get());
            loadFormDependencies(model);
            return "doctor/form";
        }
        return "redirect:/doctors";
    }


    @PostMapping
    public String addDoctor(@Valid @ModelAttribute("doctor") Doctor doctor,
                            BindingResult bindingResult,
                            Model model) {
        if (bindingResult.hasErrors()) {
            loadFormDependencies(model);
            return "doctor/form";
        }

        // Business Validation Example: Check if department is selected
        if (doctor.getDepartment() == null || doctor.getDepartment().getId().isEmpty()) {
            model.addAttribute("globalError", "A department must be selected for the doctor.");
            loadFormDependencies(model);
            return "doctor/form";
        }

        doctorService.addDoctor(doctor);
        return "redirect:/doctors";
    }


    @PostMapping("/{id}/delete")
    public String deleteDoctor(@PathVariable String id) {
        doctorService.deleteDoctor(id);
        return "redirect:/doctors";
    }
}
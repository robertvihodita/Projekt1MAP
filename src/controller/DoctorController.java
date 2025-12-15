package controller;

import model.Doctor;
import service.DoctorService;
import service.DepartmentService;
import service.HospitalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;
    private final DepartmentService departmentService;
    private final HospitalService hospitalService; // NEW: Needed for the filter dropdown

    public DoctorController(DoctorService doctorService, DepartmentService departmentService, HospitalService hospitalService) {
        this.doctorService = doctorService;
        this.departmentService = departmentService;
        this.hospitalService = hospitalService;
    }

    @GetMapping
    public String viewAllDoctors(Model model,
                                 @RequestParam(required = false) String name,
                                 @RequestParam(required = false) String departmentId,
                                 @RequestParam(required = false) String hospitalId, // NEW
                                 @RequestParam(required = false, defaultValue = "name") String sortField,
                                 @RequestParam(required = false, defaultValue = "asc") String sortDir) {

        model.addAttribute("doctors", doctorService.getAllDoctors(name, departmentId, hospitalId, sortField, sortDir));

        // Load dropdowns
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("hospitals", hospitalService.getAllHospitals()); // NEW

        // Persist params
        model.addAttribute("name", name);
        model.addAttribute("departmentId", departmentId);
        model.addAttribute("hospitalId", hospitalId); // NEW
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "doctor/index";
    }

    @GetMapping("/new")
    public String showAddDoctorForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "doctor/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditDoctorForm(@PathVariable String id, Model model) {
        Optional<Doctor> doctor = doctorService.getDoctorById(id);
        if (doctor.isPresent()) {
            model.addAttribute("doctor", doctor.get());
            model.addAttribute("departments", departmentService.getAllDepartments());
            return "doctor/form";
        }
        return "redirect:/doctors";
    }

    @PostMapping
    public String addDoctor(@Valid @ModelAttribute("doctor") Doctor doctor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.getAllDepartments());
            return "doctor/form";
        }
        doctorService.addDoctor(doctor);
        return "redirect:/doctors";
    }

    @GetMapping("/{id}")
    public String showDoctorDetails(@PathVariable String id, Model model) {
        Optional<Doctor> doctorOpt = doctorService.getDoctorById(id);
        if (doctorOpt.isPresent()) {
            model.addAttribute("doctor", doctorOpt.get());
            return "doctor/details";
        }
        return "redirect:/doctors";
    }

    @PostMapping("/{id}/delete")
    public String deleteDoctor(@PathVariable String id) {
        doctorService.deleteDoctor(id);
        return "redirect:/doctors";
    }
}
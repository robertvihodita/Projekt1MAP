package controller;

import model.Doctor;
import service.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/doctors") // All URLs will start with /doctors
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    /**
     * Replaces viewAllDoctors()
     * Handles GET http://localhost:8080/doctors
     */
    @GetMapping
    public String viewAllDoctors(Model model) {
        List<Doctor> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors); // Send list to HTML
        return "doctor/index"; // Loads "doctors.html"
    }

    /**
     * Shows the "add new doctor" form
     * Handles GET http://localhost:8080/doctors/add
     */
    @GetMapping("/add")
    public String showAddDoctorForm(Model model) {
        model.addAttribute("doctor", new Doctor()); // Send empty object for the form
        return "doctor/form"; // Loads "add-doctor.html"
    }

    /**
     * Replaces addDoctor()
     * Handles the POST request from the "add-doctor" form
     */
    @PostMapping("/add")
    public String addDoctor(@ModelAttribute Doctor doctor) {
        doctorService.addDoctor(doctor);
        return "redirect:/doctors"; // Go back to the doctor list
    }

    /**
     * Replaces deleteDoctor()
     * Handles GET http://localhost:8080/doctors/delete/some-id
     */
    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable String id) {
        doctorService.deleteDoctor(id);
        return "redirect:/doctors"; // Go back to the doctor list
    }
}

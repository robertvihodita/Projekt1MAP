package controller;

import service.PatientService;
import model.Patient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }


    @GetMapping
    public String showAllPatients(Model model) {
        List<Patient> patients = patientService.getAllPatients();
        model.addAttribute("patients", patients);
        return "patient/index";
    }


    @GetMapping("/new")
    public String showAddPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient/form";
    }


    @PostMapping
    public String addPatient(@ModelAttribute Patient patient) {
        patientService.addPatient(patient);
        return "redirect:/patients";
    }


    @PostMapping("/{id}/delete")
    public String deletePatient(@PathVariable String id) {
        patientService.deletePatient(id);
        return "redirect:/patients";
    }
}
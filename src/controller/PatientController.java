package controller;

import service.PatientService;
import model.Patient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public String showAllPatients(Model model,
                                  @RequestParam(required = false) String name,
                                  @RequestParam(required = false) Patient.Status status,
                                  @RequestParam(required = false, defaultValue = "name") String sortField,
                                  @RequestParam(required = false, defaultValue = "asc") String sortDir) {

        model.addAttribute("patients", patientService.getAllPatients(name, status, sortField, sortDir));
        model.addAttribute("statuses", Patient.Status.values()); // For dropdown

        model.addAttribute("name", name);
        model.addAttribute("status", status);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "patient/index";
    }

    @GetMapping("/new")
    public String showAddPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        model.addAttribute("statuses", Patient.Status.values());
        return "patient/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditPatientForm(@PathVariable String id, Model model) {
        Optional<Patient> patient = patientService.getPatientById(id);
        if (patient.isPresent()) {
            model.addAttribute("patient", patient.get());
            model.addAttribute("statuses", Patient.Status.values());
            return "patient/form";
        }
        return "redirect:/patients";
    }

    @PostMapping
    public String addPatient(@Valid @ModelAttribute("patient") Patient patient,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", Patient.Status.values());
            return "patient/form";
        }
        patientService.addPatient(patient);
        return "redirect:/patients";
    }

    @GetMapping("/{id}")
    public String showPatientDetails(@PathVariable String id, Model model) {
        Optional<Patient> patient = patientService.getPatientById(id);
        if (patient.isPresent()) {
            model.addAttribute("patient", patient.get());
            return "patient/details";
        }
        return "redirect:/patients";
    }

    @PostMapping("/{id}/delete")
    public String deletePatient(@PathVariable String id) {
        patientService.deletePatient(id);
        return "redirect:/patients";
    }
}
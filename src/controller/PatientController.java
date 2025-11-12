//package controller;
//
//import service.PatientService;
//import model.Patient;
//import java.util.List;
//
//public class PatientController {
//    private final PatientService patientService;
//
//    public PatientController(PatientService patientService) {
//        this.patientService = patientService;
//    }
//
//    public void addPatient(Patient patient) {
//
//        Patient.Status statusFromString = Patient.Status.valueOf("DISCHARGED");
//        System.out.println("Converted from string: " + statusFromString);
//
//        patientService.addPatient(patient);
//        System.out.println("Added patient: " + patient.getName());
//    }
//
//    public void showAllPatients() {
//        List<Patient> patients = patientService.getAllPatients();
//        System.out.println("=== Patients ===");
//        for (Patient p : patients) {
//            System.out.println(p.getId() + " - " + p.getName());
//        }
//    }
//
//    public void deletePatient(String id) {
//        patientService.deletePatient(id);
//        System.out.println("Deleted patient with ID: " + id);
//    }
//}

package controller;

import service.PatientService;
import model.Patient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patients") // All URLs will start with /patients
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    /**
     * Replaces showAllPatients()
     * Handles GET http://localhost:8080/patients
     */
    @GetMapping
    public String showAllPatients(Model model) {
        List<Patient> patients = patientService.getAllPatients();
        model.addAttribute("patients", patients); // Send list to HTML
        return "patient/index"; // Loads "patients.html"
    }

    /**
     * Shows the "add new patient" form
     * Handles GET http://localhost:8080/patients/add
     */
    @GetMapping("/add")
    public String showAddPatientForm(Model model) {
        model.addAttribute("patient", new Patient()); // Send empty object for the form
        return "patient/form"; // Loads "add-patient.html"
    }

    /**
     * Replaces addPatient()
     * Handles the POST request from the "add-patient" form
     */
    @PostMapping("/add")
    public String addPatient(@ModelAttribute Patient patient) {
        patientService.addPatient(patient);
        return "redirect:/patients"; // Go back to the patient list
    }

    /**
     * Replaces deletePatient()
     * Handles GET http://localhost:8080/patients/delete/some-id
     */
    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable String id) {
        patientService.deletePatient(id);
        return "redirect:/patients"; // Go back to the patient list
    }
}

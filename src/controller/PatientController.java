package controller;

import service.PatientService;
import model.Patient;
import java.util.List;

public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    public void addPatient(Patient patient) {
        patientService.addPatient(patient);
        System.out.println("Added patient: " + patient.getName());
    }

    public void showAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        System.out.println("=== Patients ===");
        for (Patient p : patients) {
            System.out.println(p.getId() + " - " + p.getName());
        }
    }

    public void deletePatient(String id) {
        patientService.deletePatient(id);
        System.out.println("Deleted patient with ID: " + id);
    }
}

package service;

import org.springframework.stereotype.Service;

import repository.PatientRepository;
import model.Patient;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // UPDATED: JPA save uses save(T entity)
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    // OK: findAll() method signature is the same
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // OK: findById(ID id) method signature is the same
    public Optional<Patient> getPatientById(String id) {
        return patientRepository.findById(id);
    }

    // UPDATED: JPA delete is deleteById(ID id)
    public void deletePatient(String id) {
        patientRepository.deleteById(id);
    }
}
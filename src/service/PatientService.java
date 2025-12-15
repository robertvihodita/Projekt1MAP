package service;

import org.springframework.data.domain.Sort;
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

    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    // UPDATED
    public List<Patient> getAllPatients(String name, Patient.Status status, String sortField, String sortDir) {
        Sort sort = Sort.by(sortField != null ? sortField : "name");
        sort = "desc".equals(sortDir) ? sort.descending() : sort.ascending();

        String searchName = (name != null && !name.isEmpty()) ? name : null;

        return patientRepository.searchPatients(searchName, status, sort);
    }

    public List<Patient> getAllPatients() { return patientRepository.findAll(); }

    public Optional<Patient> getPatientById(String id) {
        return patientRepository.findById(id);
    }

    public void deletePatient(String id) {
        patientRepository.deleteById(id);
    }
}
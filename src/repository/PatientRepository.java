package repository;

import org.springframework.stereotype.Repository;
import model.Patient;

@Repository
public class PatientRepository extends InFileRepository<Patient> {
    public PatientRepository() {
        super("patient.json", Patient.class);
    }
}
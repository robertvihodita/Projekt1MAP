package repository;

import org.springframework.stereotype.Repository;
import model.Doctor;

@Repository
public class DoctorRepository extends InFileRepository<Doctor> {
    public DoctorRepository() {
        super("doctor.json", Doctor.class);
    }
}
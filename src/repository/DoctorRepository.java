package repository;

import org.springframework.stereotype.Repository;

import model.Doctor;

@Repository
    public class DoctorRepository extends InFileRepository<Doctor> {}

public DoctorRepository() {
    // Pass the specific filename and the class type
    super("doctors.json", Doctor.class);
}
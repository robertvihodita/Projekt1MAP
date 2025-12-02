package service;

import org.springframework.stereotype.Service;

import repository.DoctorRepository;
import model.Doctor;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    // UPDATED: JPA save uses save(T entity)
    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // OK: findAll() method signature is the same
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // OK: findById(ID id) method signature is the same
    public Optional<Doctor> getDoctorById(String id) {
        return doctorRepository.findById(id);
    }

    // UPDATED: JPA delete is deleteById(ID id)
    public void deleteDoctor(String id) {
        doctorRepository.deleteById(id);
    }
}
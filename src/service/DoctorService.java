package service;

import org.springframework.data.domain.Sort;
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

    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // UPDATED
    public List<Doctor> getAllDoctors(String name, String departmentId, String sortField, String sortDir) {
        Sort sort = Sort.by(sortField != null ? sortField : "name");
        sort = "desc".equals(sortDir) ? sort.descending() : sort.ascending();

        String searchName = (name != null && !name.isEmpty()) ? name : null;
        String searchDept = (departmentId != null && !departmentId.isEmpty()) ? departmentId : null;

        return doctorRepository.searchDoctors(searchName, searchDept, sort);
    }

    public List<Doctor> getAllDoctors() { return doctorRepository.findAll(); } // Helper

    public Optional<Doctor> getDoctorById(String id) {
        return doctorRepository.findById(id);
    }

    public void deleteDoctor(String id) {
        doctorRepository.deleteById(id);
    }
}
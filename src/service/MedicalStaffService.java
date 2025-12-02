package service;

import org.springframework.stereotype.Service;

import repository.MedicalStaffRepository;
import model.MedicalStaff;
import java.util.List;
import java.util.Optional;

@Service
public class MedicalStaffService {
    private final MedicalStaffRepository medicalStaffRepository;

    public MedicalStaffService(MedicalStaffRepository medicalStaffRepository) {
        this.medicalStaffRepository = medicalStaffRepository;
    }

    // UPDATED: JPA save uses save(T entity). The composite repository was adjusted previously.
    public MedicalStaff addStaff(MedicalStaff staff) {
        // Note: We removed the 'id' parameter from the save method here.
        return medicalStaffRepository.save(staff);
    }

    // OK: findAll() method signature is the same
    public List<MedicalStaff> getAllStaff() {
        return medicalStaffRepository.findAll();
    }

    // OK: findByDepartmentId method signature is the same
    public List<MedicalStaff> getStaffByDepartment(String departmentId) {
        return medicalStaffRepository.findByDepartmentId(departmentId);
    }

    // OK: findById method signature is the same
    public Optional<MedicalStaff> getStaffById(String id) {
        return medicalStaffRepository.findById(id);
    }

    // OK: delete method signature is the same (handled in the composite repository)
    public void deleteStaff(String id) {
        medicalStaffRepository.delete(id);
    }
}
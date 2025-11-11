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

    public MedicalStaff addStaff(MedicalStaff staff) {
        return medicalStaffRepository.save(staff.getId(), staff);
    }

    public List<MedicalStaff> getAllStaff() {
        return medicalStaffRepository.findAll();
    }

    public List<MedicalStaff> getStaffByDepartment(String departmentId) {
        return medicalStaffRepository.findByDepartmentId(departmentId);
    }

    public Optional<MedicalStaff> getStaffById(String id) {
        return medicalStaffRepository.findById(id);
    }

    public void deleteStaff(String id) {
        medicalStaffRepository.delete(id);
    }
}

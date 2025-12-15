package service;

import org.springframework.data.domain.Sort;
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
        return medicalStaffRepository.save(staff);
    }

    // UPDATED: Added departmentId parameter
    public List<MedicalStaff> getAllStaff(String hospitalId, String departmentId, String role, String sortField, String sortDir) {
        Sort sort = Sort.by(sortField != null ? sortField : "name");
        sort = "desc".equals(sortDir) ? sort.descending() : sort.ascending();

        String searchHospital = (hospitalId != null && !hospitalId.isEmpty()) ? hospitalId : null;
        String searchDept = (departmentId != null && !departmentId.isEmpty()) ? departmentId : null;
        String searchRole = (role != null && !role.isEmpty()) ? role : null;

        return medicalStaffRepository.searchStaff(searchHospital, searchDept, searchRole, sort);
    }

    public List<MedicalStaff> getAllStaff() {
        return medicalStaffRepository.findAll();
    }

    public Optional<MedicalStaff> getStaffById(String id) {
        return medicalStaffRepository.findById(id);
    }

    public void deleteStaff(String id) {
        medicalStaffRepository.deleteById(id);
    }
}
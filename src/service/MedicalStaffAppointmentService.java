package service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import repository.MedicalStaffAppointmentRepository;
import model.MedicalStaffAppointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MedicalStaffAppointmentService {
    private final MedicalStaffAppointmentRepository repository;

    public MedicalStaffAppointmentService(MedicalStaffAppointmentRepository repository) {
        this.repository = repository;
    }

    public MedicalStaffAppointment addMedicalStaffAppointment(MedicalStaffAppointment appointment) {
        return repository.save(appointment);
    }

    public Optional<MedicalStaffAppointment> getMedicalStaffAppointmentById(String id) {
        return repository.findById(id);
    }

    public void deleteMedicalStaffAppointment(String id) {
        repository.deleteById(id);
    }

    public List<MedicalStaffAppointment> getAllMedicalStaffAppointments() {
        return repository.findAll();
    }


    public List<MedicalStaffAppointment> getAllMedicalStaffAppointments(String hospitalId, String departmentId,
                                                                        String staffId, LocalDate date,
                                                                        String sortField, String sortDir) {
        Sort sort = Sort.by(sortField != null ? sortField : "status");
        sort = "desc".equals(sortDir) ? sort.descending() : sort.ascending();

        String searchHosp = (hospitalId != null && !hospitalId.isEmpty()) ? hospitalId : null;
        String searchDept = (departmentId != null && !departmentId.isEmpty()) ? departmentId : null;
        String searchStaff = (staffId != null && !staffId.isEmpty()) ? staffId : null;

        LocalDateTime start = (date != null) ? date.atStartOfDay() : null;
        LocalDateTime end = (date != null) ? date.atTime(23, 59, 59) : null;

        return repository.searchAssignments(searchHosp, searchDept, searchStaff, start, end, sort);
    }
}
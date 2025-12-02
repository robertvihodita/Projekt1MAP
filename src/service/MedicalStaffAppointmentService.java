package service;

import org.springframework.stereotype.Service;

import repository.MedicalStaffAppointmentRepository;
import model.MedicalStaffAppointment;
import java.util.List;
import java.util.Optional;

@Service
public class MedicalStaffAppointmentService {
    private final MedicalStaffAppointmentRepository medicalStaffAppointmentRepository;

    public MedicalStaffAppointmentService(MedicalStaffAppointmentRepository medicalStaffAppointmentRepository) {
        this.medicalStaffAppointmentRepository = medicalStaffAppointmentRepository;
    }

    // UPDATED: JPA save uses save(T entity)
    public MedicalStaffAppointment addMedicalStaffAppointment(MedicalStaffAppointment appointment) {
        return medicalStaffAppointmentRepository.save(appointment);
    }

    // OK: findAll() method signature is the same
    public List<MedicalStaffAppointment> getAllMedicalStaffAppointments() {
        return medicalStaffAppointmentRepository.findAll();
    }

    // OK: findById(ID id) method signature is the same
    public Optional<MedicalStaffAppointment> getMedicalStaffAppointmentById(String id) {
        return medicalStaffAppointmentRepository.findById(id);
    }

    // UPDATED: JPA delete is deleteById(ID id)
    public void deleteMedicalStaffAppointment(String id) {
        medicalStaffAppointmentRepository.deleteById(id);
    }
}
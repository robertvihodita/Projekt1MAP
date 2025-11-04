package service;

import repository.MedicalStaffAppointmentRepository;
import model.MedicalStaffAppointment;
import java.util.List;
import java.util.Optional;

public class MedicalStaffAppointmentService {
    private final MedicalStaffAppointmentRepository medicalStaffAppointmentRepository;

    public MedicalStaffAppointmentService(MedicalStaffAppointmentRepository medicalStaffAppointmentRepository) {
        this.medicalStaffAppointmentRepository = medicalStaffAppointmentRepository;
    }

    public MedicalStaffAppointment addMedicalStaffAppointment(MedicalStaffAppointment appointment) {
        return medicalStaffAppointmentRepository.save(appointment.getId(), appointment);
    }

    public List<MedicalStaffAppointment> getAllMedicalStaffAppointments() {
        return medicalStaffAppointmentRepository.findAll();
    }

    public Optional<MedicalStaffAppointment> getMedicalStaffAppointmentById(String id) {
        return medicalStaffAppointmentRepository.findById(id);
    }

    public void deleteMedicalStaffAppointment(String id) {
        medicalStaffAppointmentRepository.delete(id);
    }
}

package service;

import org.springframework.stereotype.Service;

import repository.AppointmentRepository;
import model.Appointment;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    // UPDATED: JPA save uses save(T entity)
    public Appointment addAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    // OK: findAll() method signature is the same
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // OK: findById(ID id) method signature is the same
    public Optional<Appointment> getAppointmentById(String id) {
        return appointmentRepository.findById(id);
    }

    // UPDATED: JPA delete is deleteById(ID id)
    public void deleteAppointment(String id) {
        appointmentRepository.deleteById(id);
    }
}
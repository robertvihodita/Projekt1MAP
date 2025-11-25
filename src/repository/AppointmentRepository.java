package repository;

import org.springframework.stereotype.Repository;
import model.Appointment;

@Repository
public class AppointmentRepository extends InFileRepository<Appointment> {
    public AppointmentRepository() {
        super("appointment.json", Appointment.class);
    }
}
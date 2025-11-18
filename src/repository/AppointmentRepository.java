package repository;

import org.springframework.stereotype.Repository;

import model.Appointment;

@Repository
public class AppointmentRepository extends InFileRepository<Appointment> {}

public AppointmentRepository() {
    // Pass the specific filename and the class type
    super("appointment.json", appointment.class);
}
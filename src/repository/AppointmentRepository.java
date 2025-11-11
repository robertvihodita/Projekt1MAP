package repository;

import org.springframework.stereotype.Repository;

import model.Appointment;

@Repository
public class AppointmentRepository extends InMemoryRepository<Appointment> {}

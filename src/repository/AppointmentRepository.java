package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String> {
    // Custom query methods can be added here if needed,
    // e.g., List<Appointment> findByPatientId(String patientId);
}
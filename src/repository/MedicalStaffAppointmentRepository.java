package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import model.MedicalStaffAppointment;

@Repository
public interface MedicalStaffAppointmentRepository extends JpaRepository<MedicalStaffAppointment, String> {
}
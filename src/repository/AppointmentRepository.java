package repository;

import model.Appointment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String> {

    @Query("SELECT a FROM Appointment a WHERE " +
            "(:patientId IS NULL OR a.patient.id = :patientId) AND " +
            "(:status IS NULL OR a.status = :status) AND " +
            "(:hospitalId IS NULL OR a.department.hospital.id = :hospitalId) AND " +
            "(:startDate IS NULL OR a.admissionDate >= :startDate) AND " +
            "(:endDate IS NULL OR a.admissionDate <= :endDate)")
    List<Appointment> searchAppointments(@Param("patientId") String patientId,
                                         @Param("status") Appointment.AppointmentStatus status,
                                         @Param("hospitalId") String hospitalId,
                                         @Param("startDate") LocalDateTime startDate,
                                         @Param("endDate") LocalDateTime endDate,
                                         Sort sort);

    // Count ACTIVE appointments for a specific room to check capacity
    @Query("SELECT COUNT(a) FROM Appointment a WHERE a.room.id = :roomId AND a.status = 'ACTIVE'")
    long countActiveAppointmentsInRoom(@Param("roomId") String roomId);
}
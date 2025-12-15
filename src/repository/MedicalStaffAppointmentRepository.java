package repository;

import model.MedicalStaffAppointment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MedicalStaffAppointmentRepository extends JpaRepository<MedicalStaffAppointment, String> {

    @Query("SELECT m FROM MedicalStaffAppointment m WHERE " +
            "(:hospitalId IS NULL OR m.appointment.department.hospital.id = :hospitalId) AND " +
            "(:departmentId IS NULL OR m.appointment.department.id = :departmentId) AND " +
            "(:staffId IS NULL OR m.medicalStaff.id = :staffId) AND " +
            "(:startDate IS NULL OR m.appointment.admissionDate >= :startDate) AND " +
            "(:endDate IS NULL OR m.appointment.admissionDate <= :endDate)")
    List<MedicalStaffAppointment> searchAssignments(@Param("hospitalId") String hospitalId,
                                                    @Param("departmentId") String departmentId,
                                                    @Param("staffId") String staffId,
                                                    @Param("startDate") LocalDateTime startDate,
                                                    @Param("endDate") LocalDateTime endDate,
                                                    Sort sort);
}
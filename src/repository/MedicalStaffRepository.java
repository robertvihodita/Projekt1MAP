package repository;

import model.MedicalStaff;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalStaffRepository extends JpaRepository<MedicalStaff, String> {

    @Query("SELECT m FROM MedicalStaff m WHERE " +
            "(:hospitalId IS NULL OR m.department.hospital.id = :hospitalId) AND " +
            "(:departmentId IS NULL OR m.department.id = :departmentId) AND " +
            "(:role IS NULL OR (:role = 'Doctor' AND TYPE(m) = Doctor) OR (:role = 'Nurse' AND TYPE(m) = Nurse))")
    List<MedicalStaff> searchStaff(@Param("hospitalId") String hospitalId,
                                   @Param("departmentId") String departmentId,
                                   @Param("role") String role,
                                   Sort sort);
}
package repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import model.Doctor;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String> {

    @Query("SELECT d FROM Doctor d WHERE " +
            "(:name IS NULL OR LOWER(d.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:departmentId IS NULL OR d.department.id = :departmentId) AND " +
            "(:hospitalId IS NULL OR d.department.hospital.id = :hospitalId)")
    List<Doctor> searchDoctors(@Param("name") String name,
                               @Param("departmentId") String departmentId,
                               @Param("hospitalId") String hospitalId,
                               Sort sort);
}
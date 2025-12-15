package repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import model.Patient;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {

    @Query("SELECT p FROM Patient p WHERE " +
            "(:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:status IS NULL OR p.status = :status)")
    List<Patient> searchPatients(@Param("name") String name,
                                 @Param("status") Patient.Status status,
                                 Sort sort);
}
package repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import model.Nurse;
import java.util.List;

@Repository
public interface NurseRepository extends JpaRepository<Nurse, String> {

    @Query("SELECT n FROM Nurse n WHERE " +
            "(:name IS NULL OR LOWER(n.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:departmentId IS NULL OR n.department.id = :departmentId) AND " +
            "(:hospitalId IS NULL OR n.department.hospital.id = :hospitalId)")
    List<Nurse> searchNurses(@Param("name") String name,
                             @Param("departmentId") String departmentId,
                             @Param("hospitalId") String hospitalId,
                             Sort sort);
}
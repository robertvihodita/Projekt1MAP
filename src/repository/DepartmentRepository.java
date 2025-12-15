package repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import model.Department;
import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {
    @Query("SELECT d FROM Department d WHERE " +
            "(:name IS NULL OR LOWER(d.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:hospitalId IS NULL OR d.hospital.id = :hospitalId)")
    List<Department> searchDepartments(@Param("name") String name,
                                       @Param("hospitalId") String hospitalId,
                                       Sort sort);
}
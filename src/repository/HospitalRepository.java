package repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import model.Hospital;
import java.util.List;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, String> {

    @Query("SELECT h FROM Hospital h WHERE " +
            "(:name IS NULL OR LOWER(h.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:city IS NULL OR LOWER(h.city) LIKE LOWER(CONCAT('%', :city, '%')))")
    List<Hospital> searchHospitals(@Param("name") String name,
                                   @Param("city") String city,
                                   Sort sort);
}
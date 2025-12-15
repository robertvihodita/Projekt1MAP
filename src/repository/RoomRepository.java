package repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import model.Room;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {
    @Query("SELECT r FROM Room r WHERE " +
            "(:number IS NULL OR LOWER(r.number) LIKE LOWER(CONCAT('%', :number, '%'))) AND " +
            "(:status IS NULL OR r.status = :status)")
    List<Room> searchRooms(@Param("number") String number,
                           @Param("status") Room.RoomStatus status,
                           Sort sort);
}
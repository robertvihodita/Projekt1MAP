package repository;

import org.springframework.stereotype.Repository;
import model.Room;

@Repository
public class RoomRepository extends InFileRepository<Room> {
    public RoomRepository() {
        super("room.json", Room.class);
    }
}
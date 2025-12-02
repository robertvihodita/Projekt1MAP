package service;

import org.springframework.stereotype.Service;

import repository.RoomRepository;
import model.Room;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    // UPDATED: JPA save uses save(T entity)
    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    // OK: findAll() method signature is the same
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // OK: findById(ID id) method signature is the same
    public Optional<Room> getRoomById(String id) {
        return roomRepository.findById(id);
    }

    // UPDATED: JPA delete is deleteById(ID id)
    public void deleteRoom(String id) {
        roomRepository.deleteById(id);
    }
}
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

    public Room addRoom(Room room) {
        return roomRepository.save(room.getId(), room);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Optional<Room> getRoomById(String id) {
        return roomRepository.findById(id);
    }

    public void deleteRoom(String id) {
        roomRepository.delete(id);
    }
}

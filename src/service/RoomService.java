package service;

import org.springframework.data.domain.Sort;
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

    public Room addRoom(Room room) { return roomRepository.save(room); }
    public Optional<Room> getRoomById(String id) { return roomRepository.findById(id); }
    public void deleteRoom(String id) { roomRepository.deleteById(id); }
    public List<Room> getAllRooms() { return roomRepository.findAll(); }

    public List<Room> getAllRooms(String number, Room.RoomStatus status, String sortField, String sortDir) {
        Sort sort = Sort.by(sortField != null ? sortField : "number");
        sort = "desc".equals(sortDir) ? sort.descending() : sort.ascending();

        String searchNum = (number != null && !number.isEmpty()) ? number : null;

        return roomRepository.searchRooms(searchNum, status, sort);
    }
}
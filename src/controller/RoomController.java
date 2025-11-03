package controller;

import service.RoomService;
import model.Room;
import java.util.List;

public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    public void addRoom(Room room) {
        roomService.addRoom(room);
        System.out.println("Room added: " + room.getNumber());
    }

    public void showAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        System.out.println("=== Rooms ===");
        for (Room r : rooms) {
            System.out.println(r.getId() + " - Room " + r.getNumber() +
                    " | Capacity: " + r.getCapacity() +
                    " | Status: " + r.getStatus());
        }
    }

    public void deleteRoom(String id) {
        roomService.deleteRoom(id);
        System.out.println("Deleted room with ID: " + id);
    }
}

//package controller;
//
//import model.Room;
//import model.Room.RoomStatus;
//import service.RoomService;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.Scanner;
//
//public class RoomController {
//    private final RoomService roomService;
//    private final Scanner scanner = new Scanner(System.in);
//
//    public RoomController(RoomService roomService) {
//        this.roomService = roomService;
//    }
//
//    public void showMenu() {
//        while (true) {
//            System.out.println("\n=== Room Management Menu ===");
//            System.out.println("1. Add Room");
//            System.out.println("2. View All Rooms");
//            System.out.println("3. View Room by ID");
//            System.out.println("4. Delete Room");
//            System.out.println("5. Exit");
//            System.out.print("Choose an option: ");
//            int choice = Integer.parseInt(scanner.nextLine());
//
//            switch (choice) {
//                case 1 -> addRoom();
//                case 2 -> viewAllRooms();
//                case 3 -> viewRoomById();
//                case 4 -> deleteRoom();
//                case 5 -> { return; }
//                default -> System.out.println("Invalid choice.");
//            }
//        }
//    }
//
//    private void addRoom() {
//        System.out.print("Enter Room ID: ");
//        String id = scanner.nextLine();
//        System.out.print("Enter Hospital ID: ");
//        String hospitalId = scanner.nextLine();
//        System.out.print("Enter Capacity: ");
//        double capacity = Double.parseDouble(scanner.nextLine());
//        System.out.print("Enter Room Number: ");
//        String number = scanner.nextLine();
//
//        System.out.print("Enter Status (AVAILABLE / OCCUPIED): ");
//        RoomStatus status = RoomStatus.valueOf(scanner.nextLine().trim().toUpperCase());
//
//        Room room = new Room(id, hospitalId, capacity, number, status);
//        roomService.addRoom(room);
//        System.out.println("✅ Room added successfully!");
//    }
//
//    private void viewAllRooms() {
//        List<Room> rooms = roomService.getAllRooms();
//        if (rooms.isEmpty()) {
//            System.out.println("No rooms found.");
//        } else {
//            System.out.println("\n--- List of Rooms ---");
//            rooms.forEach(r -> System.out.println(
//                    "ID: " + r.getId() +
//                            ", Hospital ID: " + r.getHospitalId() +
//                            ", Capacity: " + r.getCapacity() +
//                            ", Number: " + r.getNumber() +
//                            ", Status: " + r.getStatus()
//            ));
//        }
//    }
//
//    private void viewRoomById() {
//        System.out.print("Enter Room ID: ");
//        String id = scanner.nextLine();
//        Optional<Room> roomOpt = roomService.getRoomById(id);
//
//        if (roomOpt.isPresent()) {
//            Room r = roomOpt.get();
//            System.out.println("\n--- Room Details ---");
//            System.out.println("ID: " + r.getId());
//            System.out.println("Hospital ID: " + r.getHospitalId());
//            System.out.println("Capacity: " + r.getCapacity());
//            System.out.println("Number: " + r.getNumber());
//            System.out.println("Status: " + r.getStatus());
//        } else {
//            System.out.println("Room not found.");
//        }
//    }
//
//    private void deleteRoom() {
//        System.out.print("Enter Room ID to delete: ");
//        String id = scanner.nextLine();
//        roomService.deleteRoom(id);
//        System.out.println("✅ Room deleted successfully!");
//    }
//}

package controller;

import model.Room;
import service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rooms") // All URLs will start with /rooms
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * Replaces viewAllRooms()
     * Handles GET http://localhost:8080/rooms
     */
    @GetMapping
    public String viewAllRooms(Model model) {
        List<Room> rooms = roomService.getAllRooms();
        model.addAttribute("rooms", rooms); // Send list to HTML
        return "room/index"; // Loads "rooms.html"
    }

    /**
     * Shows the "add new room" form
     * Handles GET http://localhost:8080/rooms/add
     */
    @GetMapping("/add")
    public String showAddRoomForm(Model model) {
        model.addAttribute("room", new Room()); // Send empty object for the form
        model.addAttribute("statuses", Room.RoomStatus.values()); // Send list of statuses
        return "room/form"; // Loads "add-room.html"
    }

    /**
     * Replaces addRoom()
     * Handles the POST request from the "add-room" form
     */
    @PostMapping("/add")
    public String addRoom(@ModelAttribute Room room) {
        roomService.addRoom(room);
        return "redirect:/rooms"; // Go back to the room list
    }

    /**
     * Replaces deleteRoom()
     * Handles GET http://localhost:8080/rooms/delete/some-id
     */
    @GetMapping("/delete/{id}")
    public String deleteRoom(@PathVariable String id) {
        roomService.deleteRoom(id);
        return "redirect:/rooms"; // Go back to the room list
    }
}

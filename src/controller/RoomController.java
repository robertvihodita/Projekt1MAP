package controller;

import model.Room;
import model.Room.RoomStatus;
import service.RoomService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class RoomController {
    private final RoomService roomService;
    private final Scanner scanner = new Scanner(System.in);

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== Room Management Menu ===");
            System.out.println("1. Add Room");
            System.out.println("2. View All Rooms");
            System.out.println("3. View Room by ID");
            System.out.println("4. Delete Room");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addRoom();
                case 2 -> viewAllRooms();
                case 3 -> viewRoomById();
                case 4 -> deleteRoom();
                case 5 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void addRoom() {
        System.out.print("Enter Room ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Hospital ID: ");
        String hospitalId = scanner.nextLine();
        System.out.print("Enter Capacity: ");
        double capacity = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter Room Number: ");
        String number = scanner.nextLine();

        System.out.print("Enter Status (AVAILABLE / OCCUPIED): ");
        RoomStatus status = RoomStatus.valueOf(scanner.nextLine().trim().toUpperCase());

        Room room = new Room(id, hospitalId, capacity, number, status);
        roomService.addRoom(room);
        System.out.println("✅ Room added successfully!");
    }

    private void viewAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        if (rooms.isEmpty()) {
            System.out.println("No rooms found.");
        } else {
            System.out.println("\n--- List of Rooms ---");
            rooms.forEach(r -> System.out.println(
                    "ID: " + r.getId() +
                            ", Hospital ID: " + r.getHospitalId() +
                            ", Capacity: " + r.getCapacity() +
                            ", Number: " + r.getNumber() +
                            ", Status: " + r.getStatus()
            ));
        }
    }

    private void viewRoomById() {
        System.out.print("Enter Room ID: ");
        String id = scanner.nextLine();
        Optional<Room> roomOpt = roomService.getRoomById(id);

        if (roomOpt.isPresent()) {
            Room r = roomOpt.get();
            System.out.println("\n--- Room Details ---");
            System.out.println("ID: " + r.getId());
            System.out.println("Hospital ID: " + r.getHospitalId());
            System.out.println("Capacity: " + r.getCapacity());
            System.out.println("Number: " + r.getNumber());
            System.out.println("Status: " + r.getStatus());
        } else {
            System.out.println("Room not found.");
        }
    }

    private void deleteRoom() {
        System.out.print("Enter Room ID to delete: ");
        String id = scanner.nextLine();
        roomService.deleteRoom(id);
        System.out.println("✅ Room deleted successfully!");
    }
}

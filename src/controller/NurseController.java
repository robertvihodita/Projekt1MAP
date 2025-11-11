package controller;

import model.Nurse;
import service.NurseService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class NurseController {
    private final NurseService nurseService;
    private final Scanner scanner = new Scanner(System.in);

    public NurseController(NurseService nurseService) {
        this.nurseService = nurseService;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== Nurse Management Menu ===");
            System.out.println("1. Add Nurse");
            System.out.println("2. View All Nurses");
            System.out.println("3. View Nurse by ID");
            System.out.println("4. Delete Nurse");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addNurse();
                case 2 -> viewAllNurses();
                case 3 -> viewNurseById();
                case 4 -> deleteNurse();
                case 5 -> {
                    System.out.println("Exiting Nurse Management...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void addNurse() {
        System.out.print("Enter Nurse ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Nurse Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Department ID: ");
        String departmentId = scanner.nextLine();
        System.out.print("Enter Qualification Level: ");
        String qualificationLevel = scanner.nextLine();
        System.out.println("Select Shift:");
        for (Nurse.Shift shift : Nurse.Shift.values()) {
            System.out.println(shift.ordinal() + 1 + ". " + shift);
        }
        System.out.print("Enter choice: ");
        int shiftChoice = Integer.parseInt(scanner.nextLine());
        Nurse.Shift selectedShift = Nurse.Shift.values()[shiftChoice - 1];


        Nurse nurse = new Nurse(id, name, departmentId, qualificationLevel);
        System.out.println("Assigned Shift: " + selectedShift);
        nurseService.addNurse(nurse);
        System.out.println("✅ Nurse added successfully!");


    }

    private void viewAllNurses() {
        List<Nurse> nurses = nurseService.getAllNurses();
        if (nurses.isEmpty()) {
            System.out.println("No nurses found.");
        } else {
            System.out.println("\n--- List of Nurses ---");
            nurses.forEach(n -> System.out.println(
                    "ID: " + n.getId() +
                            ", Name: " + n.getName() +
                            ", Department ID: " + n.getDepartmentId() +
                            ", Qualification: " + n.getQualificationLevel()
            ));
        }
    }

    private void viewNurseById() {
        System.out.print("Enter Nurse ID: ");
        String id = scanner.nextLine();
        Optional<Nurse> nurseOpt = nurseService.getNurseById(id);

        if (nurseOpt.isPresent()) {
            Nurse n = nurseOpt.get();
            System.out.println("\n--- Nurse Details ---");
            System.out.println("ID: " + n.getId());
            System.out.println("Name: " + n.getName());
            System.out.println("Department ID: " + n.getDepartmentId());
            System.out.println("Qualification Level: " + n.getQualificationLevel());
        } else {
            System.out.println("Nurse not found.");
        }
    }

    private void deleteNurse() {
        System.out.print("Enter Nurse ID to delete: ");
        String id = scanner.nextLine();
        nurseService.deleteNurse(id);
        System.out.println("✅ Nurse deleted successfully!");
    }
}

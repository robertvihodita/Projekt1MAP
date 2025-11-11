//package controller;
//
//import model.Nurse;
//import service.NurseService;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.Scanner;
//
//public class NurseController {
//    private final NurseService nurseService;
//    private final Scanner scanner = new Scanner(System.in);
//
//    public NurseController(NurseService nurseService) {
//        this.nurseService = nurseService;
//    }
//
//    public void showMenu() {
//        while (true) {
//            System.out.println("\n=== Nurse Management Menu ===");
//            System.out.println("1. Add Nurse");
//            System.out.println("2. View All Nurses");
//            System.out.println("3. View Nurse by ID");
//            System.out.println("4. Delete Nurse");
//            System.out.println("5. Exit");
//            System.out.print("Choose an option: ");
//            int choice = Integer.parseInt(scanner.nextLine());
//
//            switch (choice) {
//                case 1 -> addNurse();
//                case 2 -> viewAllNurses();
//                case 3 -> viewNurseById();
//                case 4 -> deleteNurse();
//                case 5 -> {
//                    System.out.println("Exiting Nurse Management...");
//                    return;
//                }
//                default -> System.out.println("Invalid choice. Try again.");
//            }
//        }
//    }
//
//    private void addNurse() {
//        System.out.print("Enter Nurse ID: ");
//        String id = scanner.nextLine();
//        System.out.print("Enter Nurse Name: ");
//        String name = scanner.nextLine();
//        System.out.print("Enter Department ID: ");
//        String departmentId = scanner.nextLine();
//        System.out.print("Enter Qualification Level: ");
//        String qualificationLevel = scanner.nextLine();
//        System.out.println("Select Shift:");
//        for (Nurse.Shift shift : Nurse.Shift.values()) {
//            System.out.println(shift.ordinal() + 1 + ". " + shift);
//        }
//        System.out.print("Enter choice: ");
//        int shiftChoice = Integer.parseInt(scanner.nextLine());
//        Nurse.Shift selectedShift = Nurse.Shift.values()[shiftChoice - 1];
//
//
//        Nurse nurse = new Nurse(id, name, departmentId, qualificationLevel);
//        System.out.println("Assigned Shift: " + selectedShift);
//        nurseService.addNurse(nurse);
//        System.out.println("✅ Nurse added successfully!");
//
//
//    }
//
//    private void viewAllNurses() {
//        List<Nurse> nurses = nurseService.getAllNurses();
//        if (nurses.isEmpty()) {
//            System.out.println("No nurses found.");
//        } else {
//            System.out.println("\n--- List of Nurses ---");
//            nurses.forEach(n -> System.out.println(
//                    "ID: " + n.getId() +
//                            ", Name: " + n.getName() +
//                            ", Department ID: " + n.getDepartmentId() +
//                            ", Qualification: " + n.getQualificationLevel()
//            ));
//        }
//    }
//
//    private void viewNurseById() {
//        System.out.print("Enter Nurse ID: ");
//        String id = scanner.nextLine();
//        Optional<Nurse> nurseOpt = nurseService.getNurseById(id);
//
//        if (nurseOpt.isPresent()) {
//            Nurse n = nurseOpt.get();
//            System.out.println("\n--- Nurse Details ---");
//            System.out.println("ID: " + n.getId());
//            System.out.println("Name: " + n.getName());
//            System.out.println("Department ID: " + n.getDepartmentId());
//            System.out.println("Qualification Level: " + n.getQualificationLevel());
//        } else {
//            System.out.println("Nurse not found.");
//        }
//    }
//
//    private void deleteNurse() {
//        System.out.print("Enter Nurse ID to delete: ");
//        String id = scanner.nextLine();
//        nurseService.deleteNurse(id);
//        System.out.println("✅ Nurse deleted successfully!");
//    }
//}


package controller;

import model.Nurse;
import service.NurseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/nurses") // All URLs will start with /nurses
public class NurseController {

    private final NurseService nurseService;

    public NurseController(NurseService nurseService) {
        this.nurseService = nurseService;
    }

    /**
     * Replaces viewAllNurses()
     * Handles GET http://localhost:8080/nurses
     */
    @GetMapping
    public String viewAllNurses(Model model) {
        List<Nurse> nurses = nurseService.getAllNurses();
        model.addAttribute("nurses", nurses); // Send list to HTML
        return "appointment/index"; // Loads "nurses.html"
    }

    /**
     * Shows the "add new nurse" form
     * Handles GET http://localhost:8080/nurses/add
     */
    @GetMapping("/add")
    public String showAddNurseForm(Model model) {
        model.addAttribute("nurse", new Nurse()); // Send empty object for the form
        // You can also add the list of shifts here if you want a dropdown
        // model.addAttribute("shifts", Nurse.Shift.values());
        return "appointment/form"; // Loads "add-nurse.html"
    }

    /**
     * Replaces addNurse()
     * Handles the POST request from the "add-nurse" form
     */
    @PostMapping("/add")
    public String addNurse(@ModelAttribute Nurse nurse) {
        nurseService.addNurse(nurse);
        return "redirect:/nurses"; // Go back to the nurse list
    }

    /**
     * Replaces deleteNurse()
     * Handles GET http://localhost:8080/nurses/delete/some-id
     */
    @GetMapping("/delete/{id}")
    public String deleteNurse(@PathVariable String id) {
        nurseService.deleteNurse(id);
        return "redirect:/nurses"; // Go back to the nurse list
    }
}

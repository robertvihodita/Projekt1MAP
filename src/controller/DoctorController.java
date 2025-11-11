//package controller;
//
//import model.Doctor;
//import service.DoctorService;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.Scanner;
//
//public class DoctorController {
//    private final DoctorService doctorService;
//    private final Scanner scanner = new Scanner(System.in);
//
//    public DoctorController(DoctorService doctorService) {
//        this.doctorService = doctorService;
//    }
//
//    public void showMenu() {
//        while (true) {
//            System.out.println("\n=== Doctor Management Menu ===");
//            System.out.println("1. Add Doctor");
//            System.out.println("2. View All Doctors");
//            System.out.println("3. View Doctor by ID");
//            System.out.println("4. Delete Doctor");
//            System.out.println("5. Exit");
//            System.out.print("Choose an option: ");
//            int choice = Integer.parseInt(scanner.nextLine());
//
//            switch (choice) {
//                case 1 -> addDoctor();
//                case 2 -> viewAllDoctors();
//                case 3 -> viewDoctorById();
//                case 4 -> deleteDoctor();
//                case 5 -> {
//                    System.out.println("Exiting Doctor Management...");
//                    return;
//                }
//                default -> System.out.println("Invalid choice. Try again.");
//            }
//        }
//    }
//
//    private void addDoctor() {
//        System.out.print("Enter Doctor ID: ");
//        String id = scanner.nextLine();
//        System.out.print("Enter Doctor Name: ");
//        String name = scanner.nextLine();
//        System.out.print("Enter Department ID: ");
//        String departmentId = scanner.nextLine();
//        System.out.print("Enter License Number: ");
//        String licenseNumber = scanner.nextLine();
//
//
//        Doctor doctor = new Doctor(id, name, departmentId, licenseNumber);
//        doctorService.addDoctor(doctor);
//        System.out.println("✅ Doctor added successfully!");
//    }
//
//    private void viewAllDoctors() {
//        List<Doctor> doctors = doctorService.getAllDoctors();
//        if (doctors.isEmpty()) {
//            System.out.println("No doctors found.");
//        } else {
//            System.out.println("\n--- List of Doctors ---");
//            doctors.forEach(d -> System.out.println(
//                    "ID: " + d.getId() +
//                            ", Name: " + d.getName() +
//                            ", Department ID: " + d.getDepartmentId() +
//                            ", License Number: " + d.getLicenseNumber()
//            ));
//        }
//    }
//
//    private void viewDoctorById() {
//        System.out.print("Enter Doctor ID: ");
//        String id = scanner.nextLine();
//        Optional<Doctor> doctorOpt = doctorService.getDoctorById(id);
//
//        if (doctorOpt.isPresent()) {
//            Doctor d = doctorOpt.get();
//            System.out.println("\n--- Doctor Details ---");
//            System.out.println("ID: " + d.getId());
//            System.out.println("Name: " + d.getName());
//            System.out.println("Department ID: " + d.getDepartmentId());
//            System.out.println("License Number: " + d.getLicenseNumber());
//        } else {
//            System.out.println("Doctor not found.");
//        }
//    }
//
//    private void deleteDoctor() {
//        System.out.print("Enter Doctor ID to delete: ");
//        String id = scanner.nextLine();
//        doctorService.deleteDoctor(id);
//        System.out.println("✅ Doctor deleted successfully!");
//    }
//}

package controller;

import model.Doctor;
import service.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/doctors") // All URLs will start with /doctors
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    /**
     * Replaces viewAllDoctors()
     * Handles GET http://localhost:8080/doctors
     */
    @GetMapping
    public String viewAllDoctors(Model model) {
        List<Doctor> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors); // Send list to HTML
        return "appointment/index"; // Loads "doctors.html"
    }

    /**
     * Shows the "add new doctor" form
     * Handles GET http://localhost:8080/doctors/add
     */
    @GetMapping("/add")
    public String showAddDoctorForm(Model model) {
        model.addAttribute("doctor", new Doctor()); // Send empty object for the form
        return "appointment/form"; // Loads "add-doctor.html"
    }

    /**
     * Replaces addDoctor()
     * Handles the POST request from the "add-doctor" form
     */
    @PostMapping("/add")
    public String addDoctor(@ModelAttribute Doctor doctor) {
        doctorService.addDoctor(doctor);
        return "redirect:/doctors"; // Go back to the doctor list
    }

    /**
     * Replaces deleteDoctor()
     * Handles GET http://localhost:8080/doctors/delete/some-id
     */
    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable String id) {
        doctorService.deleteDoctor(id);
        return "redirect:/doctors"; // Go back to the doctor list
    }
}

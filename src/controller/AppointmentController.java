//package controller;
//
//import model.Appointment;
//import model.Appointment.AppointmentStatus;
//import service.AppointmentService;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.Scanner;
//
//public class AppointmentController {
//    private final AppointmentService appointmentService;
//    private final Scanner scanner = new Scanner(System.in);
//
//    public AppointmentController(AppointmentService appointmentService) {
//        this.appointmentService = appointmentService;
//    }
//
//    public void showMenu() {
//        while (true) {
//            System.out.println("\n=== Appointment Management Menu ===");
//            System.out.println("1. Add Appointment");
//            System.out.println("2. View All Appointments");
//            System.out.println("3. View Appointment by ID");
//            System.out.println("4. Delete Appointment");
//            System.out.println("5. Exit");
//            System.out.print("Choose an option: ");
//            int choice = Integer.parseInt(scanner.nextLine());
//
//            switch (choice) {
//                case 1 -> addAppointment();
//                case 2 -> viewAllAppointments();
//                case 3 -> viewAppointmentById();
//                case 4 -> deleteAppointment();
//                case 5 -> { return; }
//                default -> System.out.println("Invalid choice.");
//            }
//        }
//    }
//
//    private void addAppointment() {
//        System.out.print("Enter Appointment ID: ");
//        String id = scanner.nextLine();
//        System.out.print("Enter Department ID: ");
//        String departmentId = scanner.nextLine();
//        System.out.print("Enter Patient ID: ");
//        String patientId = scanner.nextLine();
//        System.out.print("Enter Admission Date: ");
//        String date = scanner.nextLine();
//
//
//        System.out.print("Enter Status (ACTIVE / COMPLETED): ");
//        AppointmentStatus status = AppointmentStatus.valueOf(scanner.nextLine().trim().toUpperCase());
//
//        Appointment appointment = new Appointment(id, departmentId, patientId, date, status);
//        appointmentService.addAppointment(appointment);
//        System.out.println("✅ Appointment added successfully!");
//    }
//
//    private void viewAllAppointments() {
//        List<Appointment> appointments = appointmentService.getAllAppointments();
//        if (appointments.isEmpty()) {
//            System.out.println("No appointments found.");
//        } else {
//            System.out.println("\n--- Appointments ---");
//            appointments.forEach(a -> System.out.println(
//                    "ID: " + a.getId() +
//                            ", Department ID: " + a.getDepartmentId() +
//                            ", Patient ID: " + a.getPatientId() +
//                            ", Date: " + a.getAdmissionDate() +
//                            ", Status: " + a.getStatus()
//            ));
//        }
//    }
//
//    private void viewAppointmentById() {
//        System.out.print("Enter Appointment ID: ");
//        String id = scanner.nextLine();
//        Optional<Appointment> appointmentOpt = appointmentService.getAppointmentById(id);
//
//        if (appointmentOpt.isPresent()) {
//            Appointment a = appointmentOpt.get();
//            System.out.println("\n--- Appointment Details ---");
//            System.out.println("ID: " + a.getId());
//            System.out.println("Department ID: " + a.getDepartmentId());
//            System.out.println("Patient ID: " + a.getPatientId());
//            System.out.println("Date: " + a.getAdmissionDate());
//            System.out.println("Status: " + a.getStatus());
//        } else {
//            System.out.println("Appointment not found.");
//        }
//    }
//
//    private void deleteAppointment() {
//        System.out.print("Enter Appointment ID to delete: ");
//        String id = scanner.nextLine();
//        appointmentService.deleteAppointment(id);
//        System.out.println("✅ Appointment deleted successfully!");
//    }
//}

package controller;

import model.Appointment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.AppointmentService;

import java.util.List;

@Controller
@RequestMapping("/appointments") // All URLs in this class start with /appointments
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    /**
     * This handles GET requests to /appointments
     * It now returns "index" to match your index.html file.
     */
    @GetMapping
    public String viewAllAppointments(Model model) {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        model.addAttribute("appointments", appointments);
        return "appointment/index"; // <-- ✅ Matches your index.html
    }

    /**
     * This handles GET requests to /appointments/new
     * (to match the link in your index.html)
     * It now returns "form" to match your form.html file.
     */
    @GetMapping("/new")
    public String showAddAppointmentForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        return "appointment/form"; // <-- ✅ Matches your form.html
    }

    /**
     * This handles POST requests to /appointments
     * (to match the th:action in your form.html)
     */
    @PostMapping
    public String addAppointment(@ModelAttribute Appointment appointment) {
        appointmentService.addAppointment(appointment);
        return "redirect:/appointments"; // <-- Go back to the list page
    }

    /**
     * This handles POST requests to /appointments/123/delete
     * (to match the delete form in your index.html)
     */
    @PostMapping("/{id}/delete")
    public String deleteAppointment(@PathVariable String id) {
        appointmentService.deleteAppointment(id);
        return "redirect:/appointments"; // <-- Go back to the list page
    }
}

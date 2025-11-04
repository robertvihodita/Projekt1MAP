package controller;

import model.Appointment;
import model.Appointment.AppointmentStatus;
import service.AppointmentService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AppointmentController {
    private final AppointmentService appointmentService;
    private final Scanner scanner = new Scanner(System.in);

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== Appointment Management Menu ===");
            System.out.println("1. Add Appointment");
            System.out.println("2. View All Appointments");
            System.out.println("3. View Appointment by ID");
            System.out.println("4. Delete Appointment");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addAppointment();
                case 2 -> viewAllAppointments();
                case 3 -> viewAppointmentById();
                case 4 -> deleteAppointment();
                case 5 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void addAppointment() {
        System.out.print("Enter Appointment ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Department ID: ");
        String departmentId = scanner.nextLine();
        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine();
        System.out.print("Enter Admission Date: ");
        String date = scanner.nextLine();


        System.out.print("Enter Status (ACTIVE / COMPLETED): ");
        AppointmentStatus status = AppointmentStatus.valueOf(scanner.nextLine().trim().toUpperCase());

        Appointment appointment = new Appointment(id, departmentId, patientId, date, status);
        appointmentService.addAppointment(appointment);
        System.out.println("✅ Appointment added successfully!");
    }

    private void viewAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
        } else {
            System.out.println("\n--- Appointments ---");
            appointments.forEach(a -> System.out.println(
                    "ID: " + a.getId() +
                            ", Department ID: " + a.getDepartmentId() +
                            ", Patient ID: " + a.getPatientId() +
                            ", Date: " + a.getAdmissionDate() +
                            ", Status: " + a.getStatus()
            ));
        }
    }

    private void viewAppointmentById() {
        System.out.print("Enter Appointment ID: ");
        String id = scanner.nextLine();
        Optional<Appointment> appointmentOpt = appointmentService.getAppointmentById(id);

        if (appointmentOpt.isPresent()) {
            Appointment a = appointmentOpt.get();
            System.out.println("\n--- Appointment Details ---");
            System.out.println("ID: " + a.getId());
            System.out.println("Department ID: " + a.getDepartmentId());
            System.out.println("Patient ID: " + a.getPatientId());
            System.out.println("Date: " + a.getAdmissionDate());
            System.out.println("Status: " + a.getStatus());
        } else {
            System.out.println("Appointment not found.");
        }
    }

    private void deleteAppointment() {
        System.out.print("Enter Appointment ID to delete: ");
        String id = scanner.nextLine();
        appointmentService.deleteAppointment(id);
        System.out.println("✅ Appointment deleted successfully!");
    }
}

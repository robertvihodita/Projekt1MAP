package controller;

import model.MedicalStaffAppointment;
import service.MedicalStaffAppointmentService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MedicalStaffAppointmentController {
    private final MedicalStaffAppointmentService medicalStaffAppointmentService;
    private final Scanner scanner = new Scanner(System.in);

    public MedicalStaffAppointmentController(MedicalStaffAppointmentService medicalStaffAppointmentService) {
        this.medicalStaffAppointmentService = medicalStaffAppointmentService;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== Medical Staff Appointment Management Menu ===");
            System.out.println("1. Add Medical Staff Appointment");
            System.out.println("2. View All Appointments");
            System.out.println("3. View Appointment by ID");
            System.out.println("4. Delete Appointment");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addMedicalStaffAppointment();
                case 2 -> viewAllMedicalStaffAppointments();
                case 3 -> viewMedicalStaffAppointmentById();
                case 4 -> deleteMedicalStaffAppointment();
                case 5 -> {
                    System.out.println("Exiting Medical Staff Appointment Management...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void addMedicalStaffAppointment() {
        System.out.print("Enter Appointment Record ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Appointment ID: ");
        String appointmentId = scanner.nextLine();
        System.out.print("Enter Medical Staff ID: ");
        String medicalStaffId = scanner.nextLine();
        //enum usage example
        MedicalStaffAppointment.Status statusFromString = MedicalStaffAppointment.Status.valueOf("SCHEDULED");
        System.out.println("Converted from string: " + statusFromString);



        MedicalStaffAppointment appointment = new MedicalStaffAppointment(id, appointmentId, medicalStaffId);
        medicalStaffAppointmentService.addMedicalStaffAppointment(appointment);
        System.out.println("✅ Medical Staff Appointment added successfully!");
    }

    private void viewAllMedicalStaffAppointments() {
        List<MedicalStaffAppointment> appointments = medicalStaffAppointmentService.getAllMedicalStaffAppointments();
        if (appointments.isEmpty()) {
            System.out.println("No medical staff appointments found.");
        } else {
            System.out.println("\n--- List of Medical Staff Appointments ---");
            appointments.forEach(a -> System.out.println(
                    "Record ID: " + a.getId() +
                            ", Appointment ID: " + a.getAppointmentId() +
                            ", Medical Staff ID: " + a.getMedicalStaffId()
            ));
        }
    }

    private void viewMedicalStaffAppointmentById() {
        System.out.print("Enter Appointment Record ID: ");
        String id = scanner.nextLine();
        Optional<MedicalStaffAppointment> appointmentOpt = medicalStaffAppointmentService.getMedicalStaffAppointmentById(id);

        if (appointmentOpt.isPresent()) {
            MedicalStaffAppointment a = appointmentOpt.get();
            System.out.println("\n--- Appointment Details ---");
            System.out.println("Record ID: " + a.getId());
            System.out.println("Appointment ID: " + a.getAppointmentId());
            System.out.println("Medical Staff ID: " + a.getMedicalStaffId());
        } else {
            System.out.println("Appointment record not found.");
        }
    }

    private void deleteMedicalStaffAppointment() {
        System.out.print("Enter Appointment Record ID to delete: ");
        String id = scanner.nextLine();
        medicalStaffAppointmentService.deleteMedicalStaffAppointment(id);
        System.out.println("✅ Appointment record deleted successfully!");
    }
}

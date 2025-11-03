package controller;

import service.AppointmentService;
import model.Appointment;
import java.time.LocalDateTime;
import java.util.List;

public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    public void addAppointment(Appointment appointment) {
        appointmentService.addAppointment(appointment);
        System.out.println("Added appointment: " + appointment.getId());
    }

    public void createSampleAppointment() {
        Appointment appointment = new Appointment("A1", "D1", "P1", LocalDateTime.now(), "Active");
        appointmentService.addAppointment(appointment);
        System.out.println("Created sample appointment: " + appointment.getId());
    }

    public void showAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        System.out.println("=== Appointments ===");
        for (Appointment a : appointments) {
            System.out.println(a.getId() + " | Department: " + a.getDepartmentId() +
                    " | Patient: " + a.getPatientId() + " | Status: " + a.getStatus());
        }
    }

    public void deleteAppointment(String id) {
        appointmentService.deleteAppointment(id);
        System.out.println("Deleted appointment with ID: " + id);
    }
}

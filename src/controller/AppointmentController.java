package controller;

import model.Appointment;
import model.Department;
import model.Patient;
import model.Room;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import repository.DepartmentRepository;
import repository.PatientRepository;
import repository.RoomRepository;
import service.AppointmentService;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    // Inject Repositories for dropdowns (Foreign Keys)
    private final PatientRepository patientRepository;
    private final DepartmentRepository departmentRepository;
    private final RoomRepository roomRepository;

    public AppointmentController(AppointmentService appointmentService,
                                 PatientRepository patientRepository,
                                 DepartmentRepository departmentRepository,
                                 RoomRepository roomRepository) {
        this.appointmentService = appointmentService;
        this.patientRepository = patientRepository;
        this.departmentRepository = departmentRepository;
        this.roomRepository = roomRepository;
    }


    @GetMapping
    public String viewAllAppointments(Model model) {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        model.addAttribute("appointments", appointments);
        return "appointment/index";
    }

    // Helper method to load necessary data for the form (patients, departments, rooms)
    private void loadFormDependencies(Model model) {
        model.addAttribute("statuses", Appointment.AppointmentStatus.values());
        model.addAttribute("patients", patientRepository.findAll());
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("rooms", roomRepository.findAll());
    }

    @GetMapping("/new")
    public String showAddAppointmentForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        loadFormDependencies(model);
        return "appointment/form";
    }

    // UPDATED: Show Edit Form
    @GetMapping("/{id}/edit")
    public String showEditAppointmentForm(@PathVariable String id, Model model) {
        Optional<Appointment> appointment = appointmentService.getAppointmentById(id);
        if (appointment.isPresent()) {
            model.addAttribute("appointment", appointment.get());
            loadFormDependencies(model);
            return "appointment/form";
        }
        // Handle not found case (or show a custom error)
        return "redirect:/appointments";
    }


    // UPDATED: Added @Valid and BindingResult for Backend Validation [cite: 28]
    @PostMapping
    public String addAppointment(@Valid @ModelAttribute("appointment") Appointment appointment,
                                 BindingResult bindingResult,
                                 Model model) {

        // 1. Check for Field Validation Errors (e.g., @NotBlank, @Size) [cite: 79]
        if (bindingResult.hasErrors()) {
            loadFormDependencies(model);
            // Return to the form to display errors next to fields [cite: 87]
            return "appointment/form";
        }

        // 2. Implement Business Validations (e.g., Appointment must not be for a non-existent Doctor) [cite: 88, 90]
        // Example of a Business Validation that can be done here:
        if (appointment.getPatient() == null || appointment.getPatient().getId() == null ||
                !patientRepository.existsById(appointment.getPatient().getId())) {

            // Add a global error message
            model.addAttribute("globalError", "The selected patient does not exist.");
            loadFormDependencies(model);
            return "appointment/form";
        }

        // Save the valid entity
        appointmentService.addAppointment(appointment);
        return "redirect:/appointments";
    }


    @PostMapping("/{id}/delete")
    public String deleteAppointment(@PathVariable String id) {
        // You may need to add try-catch here for "Business Validations" [cite: 92]
        // E.g., preventing deletion if other entities depend on it (foreign key violation)
        try {
            appointmentService.deleteAppointment(id);
        } catch (Exception e) {
            // In a real application, you would catch DataIntegrityViolationException 
            // and return to an error page with a message [cite: 96]
            System.err.println("Deletion failed due to integrity constraint: " + e.getMessage());
            // TODO: Redirect to an error page or show a message to the user [cite: 96]
        }
        return "redirect:/appointments";
    }
}
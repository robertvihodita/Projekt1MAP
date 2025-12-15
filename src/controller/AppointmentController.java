package controller;

import model.Appointment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import repository.DepartmentRepository;
import repository.PatientRepository;
import repository.RoomRepository;
import service.AppointmentService;
import service.HospitalService;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final PatientRepository patientRepository;
    private final DepartmentRepository departmentRepository;
    private final RoomRepository roomRepository;
    private final HospitalService hospitalService;

    public AppointmentController(AppointmentService appointmentService,
                                 PatientRepository patientRepository,
                                 DepartmentRepository departmentRepository,
                                 RoomRepository roomRepository,
                                 HospitalService hospitalService) {
        this.appointmentService = appointmentService;
        this.patientRepository = patientRepository;
        this.departmentRepository = departmentRepository;
        this.roomRepository = roomRepository;
        this.hospitalService = hospitalService;
    }

    @GetMapping
    public String viewAllAppointments(Model model,
                                      @RequestParam(required = false) String patientId,
                                      @RequestParam(required = false) String hospitalId,
                                      @RequestParam(required = false) LocalDate date,
                                      @RequestParam(required = false) Appointment.AppointmentStatus status,
                                      @RequestParam(required = false, defaultValue = "admissionDate") String sortField,
                                      @RequestParam(required = false, defaultValue = "desc") String sortDir) {

        model.addAttribute("appointments", appointmentService.getAllAppointments(patientId, status, hospitalId, date, sortField, sortDir));

        model.addAttribute("patients", patientRepository.findAll());
        model.addAttribute("statuses", Appointment.AppointmentStatus.values());
        model.addAttribute("hospitals", hospitalService.getAllHospitals());

        model.addAttribute("patientId", patientId);
        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("date", date);
        model.addAttribute("status", status);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "appointment/index";
    }

    @GetMapping("/{id}")
    public String showAppointmentDetails(@PathVariable String id, Model model) {
        Optional<Appointment> appt = appointmentService.getAppointmentById(id);
        if (appt.isPresent()) {
            model.addAttribute("appointment", appt.get());
            return "appointment/details";
        }
        return "redirect:/appointments";
    }

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

    @GetMapping("/{id}/edit")
    public String showEditAppointmentForm(@PathVariable String id, Model model) {
        Optional<Appointment> appointment = appointmentService.getAppointmentById(id);
        if (appointment.isPresent()) {
            model.addAttribute("appointment", appointment.get());
            loadFormDependencies(model);
            return "appointment/form";
        }
        return "redirect:/appointments";
    }

    @PostMapping
    public String addAppointment(@Valid @ModelAttribute("appointment") Appointment appointment,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            loadFormDependencies(model);
            return "appointment/form";
        }


        if (appointment.getRoom() != null && appointment.getStatus() == Appointment.AppointmentStatus.ACTIVE) {
            // Check capacity, passing current ID to handle "Edit" correctly
            if (appointmentService.isRoomFull(appointment.getRoom().getId(), appointment.getId())) {
                model.addAttribute("globalError", "Room capacity exceeded! This room is full. Please select another.");
                loadFormDependencies(model);
                return "appointment/form";
            }
        }

        appointmentService.addAppointment(appointment);
        return "redirect:/appointments";
    }

    @PostMapping("/{id}/delete")
    public String deleteAppointment(@PathVariable String id) {
        appointmentService.deleteAppointment(id);
        return "redirect:/appointments";
    }
}
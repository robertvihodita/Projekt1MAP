package controller;

import model.MedicalStaffAppointment;
import service.AppointmentService;
import service.MedicalStaffService;
import service.MedicalStaffAppointmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/staff-appointments")
public class MedicalStaffAppointmentController {

    private final MedicalStaffAppointmentService medicalStaffAppointmentService;
    private final AppointmentService appointmentService;
    private final MedicalStaffService medicalStaffService;

    public MedicalStaffAppointmentController(MedicalStaffAppointmentService medicalStaffAppointmentService,
                                             AppointmentService appointmentService,
                                             MedicalStaffService medicalStaffService) {
        this.medicalStaffAppointmentService = medicalStaffAppointmentService;
        this.appointmentService = appointmentService;
        this.medicalStaffService = medicalStaffService;
    }

    private void loadFormDependencies(Model model) {
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        model.addAttribute("medicalStaffList", medicalStaffService.getAllStaff());
    }

    @GetMapping
    public String viewAllMedicalStaffAppointments(Model model) {
        List<MedicalStaffAppointment> appointments = medicalStaffAppointmentService.getAllMedicalStaffAppointments();
        model.addAttribute("medicalstaffappointments", appointments);
        return "medicalstaffappointment/index";
    }

    @GetMapping("/new")
    public String showAddAppointmentForm(Model model) {
        model.addAttribute("appointment", new MedicalStaffAppointment());
        loadFormDependencies(model);
        return "medicalstaffappointment/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditStaffAppointmentForm(@PathVariable String id, Model model) {
        Optional<MedicalStaffAppointment> appointment = medicalStaffAppointmentService.getMedicalStaffAppointmentById(id);
        if (appointment.isPresent()) {
            model.addAttribute("appointment", appointment.get());
            loadFormDependencies(model);
            return "medicalstaffappointment/form";
        }
        return "redirect:/staff-appointments";
    }

    @PostMapping
    public String addMedicalStaffAppointment(@Valid @ModelAttribute("appointment") MedicalStaffAppointment appointment,
                                             BindingResult bindingResult,
                                             Model model) {
        if (bindingResult.hasErrors()) {
            loadFormDependencies(model);
            return "medicalstaffappointment/form";
        }

        // Business Validation: Check if linked entities are selected
        if (appointment.getAppointment() == null || appointment.getMedicalStaff() == null) {
            model.addAttribute("globalError", "Both an Appointment and a Medical Staff member must be selected.");
            loadFormDependencies(model);
            return "medicalstaffappointment/form";
        }

        medicalStaffAppointmentService.addMedicalStaffAppointment(appointment);
        return "redirect:/staff-appointments";
    }

    @PostMapping("/{id}/delete")
    public String deleteMedicalStaffAppointment(@PathVariable String id) {
        medicalStaffAppointmentService.deleteMedicalStaffAppointment(id);
        return "redirect:/staff-appointments";
    }
}
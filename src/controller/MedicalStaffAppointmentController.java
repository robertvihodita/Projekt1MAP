package controller;

import model.MedicalStaffAppointment;
import service.MedicalStaffAppointmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/staff-appointments") // Corrects the 404 error
public class MedicalStaffAppointmentController {

    private final MedicalStaffAppointmentService medicalStaffAppointmentService;

    public MedicalStaffAppointmentController(MedicalStaffAppointmentService medicalStaffAppointmentService) {
        this.medicalStaffAppointmentService = medicalStaffAppointmentService;
    }

    @GetMapping
    public String viewAllMedicalStaffAppointments(Model model) {
        List<MedicalStaffAppointment> appointments = medicalStaffAppointmentService.getAllMedicalStaffAppointments();

        // FIX: The attribute name must match the one used in the Thymeleaf loop.
        model.addAttribute("medicalstaffappointments", appointments);

        return "medicalstaffappointment/index";
    }

    @GetMapping("/new")
    public String showAddAppointmentForm(Model model) {
        model.addAttribute("appointment", new MedicalStaffAppointment());
        return "medicalstaffappointment/form";
    }

    @PostMapping
    public String addMedicalStaffAppointment(@ModelAttribute MedicalStaffAppointment appointment) {
        medicalStaffAppointmentService.addMedicalStaffAppointment(appointment);
        return "redirect:/staff-appointments";
    }

    @PostMapping("/{id}/delete")
    public String deleteMedicalStaffAppointment(@PathVariable String id) {
        medicalStaffAppointmentService.deleteMedicalStaffAppointment(id);
        return "redirect:/staff-appointments";
    }
}
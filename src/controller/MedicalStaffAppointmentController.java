package controller;

import model.MedicalStaffAppointment;
import service.AppointmentService;
import service.MedicalStaffService;
import service.MedicalStaffAppointmentService;
import service.HospitalService; // NEW
import service.DepartmentService; // NEW
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/staff-appointments")
public class MedicalStaffAppointmentController {

    private final MedicalStaffAppointmentService medicalStaffAppointmentService;
    private final AppointmentService appointmentService;
    private final MedicalStaffService medicalStaffService;
    private final HospitalService hospitalService; // NEW
    private final DepartmentService departmentService; // NEW

    public MedicalStaffAppointmentController(MedicalStaffAppointmentService medicalStaffAppointmentService,
                                             AppointmentService appointmentService,
                                             MedicalStaffService medicalStaffService,
                                             HospitalService hospitalService,
                                             DepartmentService departmentService) {
        this.medicalStaffAppointmentService = medicalStaffAppointmentService;
        this.appointmentService = appointmentService;
        this.medicalStaffService = medicalStaffService;
        this.hospitalService = hospitalService;
        this.departmentService = departmentService;
    }

    @GetMapping
    public String viewAllMedicalStaffAppointments(Model model,
                                                  @RequestParam(required = false) String hospitalId,
                                                  @RequestParam(required = false) String departmentId,
                                                  @RequestParam(required = false) String staffId,
                                                  @RequestParam(required = false) LocalDate date,
                                                  @RequestParam(required = false, defaultValue = "status") String sortField,
                                                  @RequestParam(required = false, defaultValue = "asc") String sortDir) {

        model.addAttribute("medicalstaffappointments",
                medicalStaffAppointmentService.getAllMedicalStaffAppointments(hospitalId, departmentId, staffId, date, sortField, sortDir));


        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("staffList", medicalStaffService.getAllStaff());


        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("departmentId", departmentId);
        model.addAttribute("staffId", staffId);
        model.addAttribute("date", date);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "medicalstaffappointment/index";
    }


    @GetMapping("/{id}")
    public String showDetails(@PathVariable String id, Model model) {
        Optional<MedicalStaffAppointment> msa = medicalStaffAppointmentService.getMedicalStaffAppointmentById(id);
        if (msa.isPresent()) {
            model.addAttribute("msa", msa.get());
            return "medicalstaffappointment/details";
        }
        return "redirect:/staff-appointments";
    }

    private void loadFormDependencies(Model model) {
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        model.addAttribute("medicalStaffList", medicalStaffService.getAllStaff());
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
package controller;

import model.Nurse;
import service.NurseService;
import service.DepartmentService;
import service.HospitalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/nurses")
public class NurseController {

    private final NurseService nurseService;
    private final DepartmentService departmentService;
    private final HospitalService hospitalService; // NEW

    public NurseController(NurseService nurseService, DepartmentService departmentService, HospitalService hospitalService) {
        this.nurseService = nurseService;
        this.departmentService = departmentService;
        this.hospitalService = hospitalService;
    }

    @GetMapping
    public String viewAllNurses(Model model,
                                @RequestParam(required = false) String name,
                                @RequestParam(required = false) String departmentId,
                                @RequestParam(required = false) String hospitalId, // NEW
                                @RequestParam(required = false, defaultValue = "name") String sortField,
                                @RequestParam(required = false, defaultValue = "asc") String sortDir) {


        model.addAttribute("nurses", nurseService.getAllNurses(name, departmentId, hospitalId, sortField, sortDir));


        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("hospitals", hospitalService.getAllHospitals()); // NEW for filter


        model.addAttribute("name", name);
        model.addAttribute("departmentId", departmentId);
        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "nurse/index";
    }

    @GetMapping("/new")
    public String showAddNurseForm(Model model) {
        model.addAttribute("nurse", new Nurse());
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("shifts", Nurse.Shift.values());
        return "nurse/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditNurseForm(@PathVariable String id, Model model) {
        Optional<Nurse> nurse = nurseService.getNurseById(id);
        if (nurse.isPresent()) {
            model.addAttribute("nurse", nurse.get());
            model.addAttribute("departments", departmentService.getAllDepartments());
            model.addAttribute("shifts", Nurse.Shift.values());
            return "nurse/form";
        }
        return "redirect:/nurses";
    }

    @PostMapping
    public String addNurse(@Valid @ModelAttribute("nurse") Nurse nurse, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.getAllDepartments());
            model.addAttribute("shifts", Nurse.Shift.values());
            return "nurse/form";
        }
        nurseService.addNurse(nurse);
        return "redirect:/nurses";
    }

    @GetMapping("/{id}")
    public String showNurseDetails(@PathVariable String id, Model model) {
        Optional<Nurse> nurseOpt = nurseService.getNurseById(id);
        if (nurseOpt.isPresent()) {
            model.addAttribute("nurse", nurseOpt.get());
            return "nurse/details";
        }
        return "redirect:/nurses";
    }

    @PostMapping("/{id}/delete")
    public String deleteNurse(@PathVariable String id) {
        nurseService.deleteNurse(id);
        return "redirect:/nurses";
    }
}
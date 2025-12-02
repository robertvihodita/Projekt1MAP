package controller;

import model.Nurse;
import service.NurseService;
import service.DepartmentService; // Needed for dropdown
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/nurses")
public class NurseController {

    private final NurseService nurseService;
    private final DepartmentService departmentService; // Used to populate Department dropdown


    public NurseController(NurseService nurseService, DepartmentService departmentService) {
        this.nurseService = nurseService;
        this.departmentService = departmentService;
    }

    private void loadFormDependencies(Model model) {
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("shifts", Nurse.Shift.values());
    }

    @GetMapping
    public String viewAllNurses(Model model) {
        List<Nurse> nurses = nurseService.getAllNurses();
        model.addAttribute("nurses", nurses);
        return "nurse/index";
    }

    @GetMapping("/new")
    public String showAddNurseForm(Model model) {
        model.addAttribute("nurse", new Nurse());
        loadFormDependencies(model);
        return "nurse/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditNurseForm(@PathVariable String id, Model model) {
        Optional<Nurse> nurse = nurseService.getNurseById(id);
        if (nurse.isPresent()) {
            model.addAttribute("nurse", nurse.get());
            loadFormDependencies(model);
            return "nurse/form";
        }
        return "redirect:/nurses";
    }


    @PostMapping
    public String addNurse(@Valid @ModelAttribute("nurse") Nurse nurse,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            loadFormDependencies(model);
            return "nurse/form";
        }

        // Business Validation Example: Check if department is selected
        if (nurse.getDepartment() == null || nurse.getDepartment().getId().isEmpty()) {
            model.addAttribute("globalError", "A department must be selected for the nurse.");
            loadFormDependencies(model);
            return "nurse/form";
        }

        nurseService.addNurse(nurse);
        return "redirect:/nurses";
    }


    @PostMapping("/{id}/delete")
    public String deleteNurse(@PathVariable String id) {
        nurseService.deleteNurse(id);
        return "redirect:/nurses";
    }
}
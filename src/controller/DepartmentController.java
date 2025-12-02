package controller;

import service.DepartmentService;
import service.HospitalService; // Needed for dropdown
import model.Department;
import model.Hospital;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;
    private final HospitalService hospitalService; // Used to populate Hospital dropdown

    public DepartmentController(DepartmentService departmentService, HospitalService hospitalService) {
        this.departmentService = departmentService;
        this.hospitalService = hospitalService;
    }

    private void loadFormDependencies(Model model) {
        // Load all Hospitals for the selection dropdown
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
    }


    @GetMapping
    public String showAllDepartments(Model model) {
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "department/index";
    }


    @GetMapping("/new")
    public String showAddDepartmentForm(Model model) {
        model.addAttribute("department", new Department());
        loadFormDependencies(model);
        return "department/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditDepartmentForm(@PathVariable String id, Model model) {
        Optional<Department> department = departmentService.getDepartmentById(id);
        if (department.isPresent()) {
            model.addAttribute("department", department.get());
            loadFormDependencies(model);
            return "department/form";
        }
        return "redirect:/departments";
    }


    @PostMapping
    public String addDepartment(@Valid @ModelAttribute("department") Department department,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            loadFormDependencies(model);
            return "department/form";
        }

        // Example Business Validation: Ensure hospital is selected
        if (department.getHospital() == null || department.getHospital().getId().isEmpty()) {
            model.addAttribute("globalError", "A hospital must be selected for the department.");
            loadFormDependencies(model);
            return "department/form";
        }

        departmentService.addDepartment(department);
        return "redirect:/departments";
    }


    @PostMapping("/{id}/delete")
    public String deleteDepartment(@PathVariable String id) {
        departmentService.deleteDepartment(id);
        return "redirect:/departments";
    }
}
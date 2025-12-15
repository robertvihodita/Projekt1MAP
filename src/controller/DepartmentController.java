package controller;

import service.DepartmentService;
import service.HospitalService;
import model.Department;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;
    private final HospitalService hospitalService;

    public DepartmentController(DepartmentService departmentService, HospitalService hospitalService) {
        this.departmentService = departmentService;
        this.hospitalService = hospitalService;
    }

    @GetMapping
    public String showAllDepartments(Model model,
                                     @RequestParam(required = false) String name,
                                     @RequestParam(required = false) String hospitalId,
                                     @RequestParam(required = false, defaultValue = "name") String sortField,
                                     @RequestParam(required = false, defaultValue = "asc") String sortDir) {

        model.addAttribute("departments", departmentService.getAllDepartments(name, hospitalId, sortField, sortDir));
        model.addAttribute("hospitals", hospitalService.getAllHospitals()); // For filter

        model.addAttribute("name", name);
        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "department/index";
    }

    @GetMapping("/{id}")
    public String showDepartmentDetails(@PathVariable String id, Model model) {
        Optional<Department> dept = departmentService.getDepartmentById(id);
        if (dept.isPresent()) {
            model.addAttribute("department", dept.get());
            return "department/details";
        }
        return "redirect:/departments";
    }

    // Add/Edit Methods (same logic as before but ensured dependencies are loaded)
    @GetMapping("/new")
    public String showAddDepartmentForm(Model model) {
        model.addAttribute("department", new Department());
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        return "department/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditDepartmentForm(@PathVariable String id, Model model) {
        Optional<Department> department = departmentService.getDepartmentById(id);
        if (department.isPresent()) {
            model.addAttribute("department", department.get());
            model.addAttribute("hospitals", hospitalService.getAllHospitals());
            return "department/form";
        }
        return "redirect:/departments";
    }

    @PostMapping
    public String addDepartment(@Valid @ModelAttribute("department") Department department, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("hospitals", hospitalService.getAllHospitals());
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
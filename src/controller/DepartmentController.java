package controller;

import service.DepartmentService;
import model.Department;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
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
        return "department/form";
    }


    @PostMapping
    public String addDepartment(@ModelAttribute Department department) {
        departmentService.addDepartment(department);
        return "redirect:/departments";
    }


    @PostMapping("/{id}/delete")
    public String deleteDepartment(@PathVariable String id) {
        departmentService.deleteDepartment(id);
        return "redirect:/departments";
    }
}
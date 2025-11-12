//package controller;
//
//import service.DepartmentService;
//import model.Department;
//import java.util.List;
//
//public class DepartmentController {
//    private final DepartmentService departmentService;
//
//    public DepartmentController(DepartmentService departmentService) {
//        this.departmentService = departmentService;
//    }
//
//    public void addDepartment(Department department) {
//        departmentService.addDepartment(department);
//        System.out.println("Department added: " + department.getName());
//    }
//
//    public void showAllDepartments() {
//        List<Department> departments = departmentService.getAllDepartments();
//        System.out.println("=== Departments ===");
//        for (Department d : departments) {
//            System.out.println(d.getId() + " - " + d.getName() + " (Hospital ID: " + d.getHospitalId() + ")");
//        }
//    }
//
//    public void deleteDepartment(String id) {
//        departmentService.deleteDepartment(id);
//        System.out.println("Deleted department with ID: " + id);
//    }
//}

package controller;

import service.DepartmentService;
import model.Department;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/departments") // All URLs will start with /departments
public class DepartmentController {

    private final DepartmentService departmentService;

    // Spring will automatically inject your service
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * Replaces showAllDepartments()
     * Handles GET http://localhost:8080/departments
     */
    @GetMapping
    public String showAllDepartments(Model model) {
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments); // Send list to HTML
        return "department/index"; // Loads "departments.html"
    }

    /**
     * Shows the "add new department" form
     * Handles GET http://localhost:8080/departments/add
     */
    @GetMapping("/add")
    public String showAddDepartmentForm(Model model) {
        model.addAttribute("department", new Department()); // Send empty object for the form
        return "department/form"; // Loads "add-department.html"
    }

    /**
     * Replaces addDepartment()
     * Handles the POST request from the "add-department" form
     */
    @PostMapping("/add")
    public String addDepartment(@ModelAttribute Department department) {
        departmentService.addDepartment(department);
        return "redirect:/departments"; // Go back to the department list
    }

    /**
     * Replaces deleteDepartment()
     * Handles GET http://localhost:8080/departments/delete/some-id
     */
    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable String id) {
        departmentService.deleteDepartment(id);
        return "redirect:/departments"; // Go back to the department list
    }
}
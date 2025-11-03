package controller;

import service.DepartmentService;
import model.Department;
import java.util.List;

public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public void addDepartment(Department department) {
        departmentService.addDepartment(department);
        System.out.println("Department added: " + department.getName());
    }

    public void showAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        System.out.println("=== Departments ===");
        for (Department d : departments) {
            System.out.println(d.getId() + " - " + d.getName() + " (Hospital ID: " + d.getHospitalId() + ")");
        }
    }

    public void deleteDepartment(String id) {
        departmentService.deleteDepartment(id);
        System.out.println("Deleted department with ID: " + id);
    }
}

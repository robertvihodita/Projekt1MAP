package service;

import org.springframework.stereotype.Service;

import repository.DepartmentRepository;
import model.Department;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    // UPDATED: JPA save uses save(T entity)
    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    // OK: findAll() method signature is the same
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // OK: findById(ID id) method signature is the same
    public Optional<Department> getDepartmentById(String id) {
        return departmentRepository.findById(id);
    }

    // UPDATED: JPA delete is deleteById(ID id)
    public void deleteDepartment(String id) {
        departmentRepository.deleteById(id);
    }
}
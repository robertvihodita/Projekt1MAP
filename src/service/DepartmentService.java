package service;

import org.springframework.data.domain.Sort;
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

    public Department addDepartment(Department department) { return departmentRepository.save(department); }
    public Optional<Department> getDepartmentById(String id) { return departmentRepository.findById(id); }
    public void deleteDepartment(String id) { departmentRepository.deleteById(id); }
    public List<Department> getAllDepartments() { return departmentRepository.findAll(); }

    public List<Department> getAllDepartments(String name, String hospitalId, String sortField, String sortDir) {
        Sort sort = Sort.by(sortField != null ? sortField : "name");
        sort = "desc".equals(sortDir) ? sort.descending() : sort.ascending();

        String searchName = (name != null && !name.isEmpty()) ? name : null;
        String searchHosp = (hospitalId != null && !hospitalId.isEmpty()) ? hospitalId : null;

        return departmentRepository.searchDepartments(searchName, searchHosp, sort);
    }
}
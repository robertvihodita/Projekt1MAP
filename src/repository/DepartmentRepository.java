package repository;

import org.springframework.stereotype.Repository;
import model.Department;

@Repository
public class DepartmentRepository extends InFileRepository<Department> {
    public DepartmentRepository() {
        super("department.json", Department.class);
    }
}
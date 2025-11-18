package repository;

import org.springframework.stereotype.Repository;
import model.Department;

@Repository
public class DepartmentRepository extends InFileRepository<Department> {

    // You must add the constructor to pass the filename
    public DepartmentRepository() {
        super("departments.json", Department.class);
    }
}
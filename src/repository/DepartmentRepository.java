package repository;

import org.springframework.stereotype.Repository;

import model.Department;

@Repository
public class DepartmentRepository extends InMemoryRepository<Department> {}
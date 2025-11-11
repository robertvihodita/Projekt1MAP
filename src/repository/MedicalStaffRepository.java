package repository;

import org.springframework.stereotype.Repository;

import model.MedicalStaff;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MedicalStaffRepository extends InMemoryRepository<MedicalStaff> {
    public List<MedicalStaff> findByDepartmentId(String departmentId) {
        return storage.values().stream()
                .filter(ms -> departmentId.equals(ms.getDepartmentId()))
                .collect(Collectors.toList());
    }
}

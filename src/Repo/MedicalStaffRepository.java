package Repo;

import Model.MedicalStaff;
import java.util.*;
import java.util.stream.Collectors;

public class MedicalStaffRepository extends InMemoryRepository<MedicalStaff> {
    public List<MedicalStaff> findByDepartmentId(String departmentId) {
        return storage.values().stream()
                .filter(ms -> departmentId.equals(ms.getDepartmentId()))
                .collect(Collectors.toList());
    }
}

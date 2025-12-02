package repository;

import model.MedicalStaff;
import model.Doctor;
import model.Nurse;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MedicalStaffRepository {
    private final DoctorRepository doctorRepository;
    private final NurseRepository nurseRepository;

    // Spring injects the new JPA repositories automatically
    public MedicalStaffRepository(DoctorRepository doctorRepository, NurseRepository nurseRepository) {
        this.doctorRepository = doctorRepository;
        this.nurseRepository = nurseRepository;
    }

    public List<MedicalStaff> findAll() {
        return java.util.stream.Stream.of(
                        doctorRepository.findAll(),
                        nurseRepository.findAll()
                )
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    // This method now relies on the entities having the correct 'department' object set,
    // not just a String ID, but we can maintain the signature for now.
    // In a real JPA environment, you would use a JOIN query in a custom method.
    public List<MedicalStaff> findByDepartmentId(String departmentId) {
        return findAll().stream()
                .filter(ms -> {
                    if (ms.getDepartment() != null) {
                        return departmentId.equals(ms.getDepartment().getId());
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    public MedicalStaff save(MedicalStaff staff) {
        // Since JPA repositories have a save method that handles both creating and updating,
        // we can simplify the signature (removed String id parameter).
        if (staff instanceof Doctor) {
            return doctorRepository.save((Doctor) staff);
        } else if (staff instanceof Nurse) {
            return nurseRepository.save((Nurse) staff);
        }
        throw new IllegalArgumentException("Cannot save abstract MedicalStaff type or unknown concrete type.");
    }

    public Optional<MedicalStaff> findById(String id) {
        Optional<Doctor> doctor = doctorRepository.findById(id).map(d -> d);
        if (doctor.isPresent()) return Optional.of(doctor.get());

        Optional<Nurse> nurse = nurseRepository.findById(id).map(n -> n);
        if (nurse.isPresent()) return Optional.of(nurse.get());

        return Optional.empty();
    }

    public void delete(String id) {
        // Try deleting from both concrete repositories
        // The deleteById method is inherited from JpaRepository
        doctorRepository.deleteById(id);
        nurseRepository.deleteById(id);
    }
}
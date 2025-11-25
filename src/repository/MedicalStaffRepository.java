package repository;

import org.springframework.stereotype.Repository;
import model.MedicalStaff;
import model.Doctor;
import model.Nurse;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MedicalStaffRepository {
    private final DoctorRepository doctorRepository;
    private final NurseRepository nurseRepository;

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

    public List<MedicalStaff> findByDepartmentId(String departmentId) {
        return findAll().stream()
                .filter(ms -> departmentId.equals(ms.getDepartmentId()))
                .collect(Collectors.toList());
    }

    public MedicalStaff save(String id, MedicalStaff staff) {
        // FIX: The save operation now works correctly because Doctor and Nurse
        // correctly extend MedicalStaff.
        if (staff instanceof Doctor) {
            return doctorRepository.save(id, (Doctor) staff);
        } else if (staff instanceof Nurse) {
            return nurseRepository.save(id, (Nurse) staff);
        }
        throw new IllegalArgumentException("Cannot save abstract MedicalStaff type or unknown concrete type.");
    }

    public Optional<MedicalStaff> findById(String id) {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        if (doctor.isPresent()) return Optional.of(doctor.get());

        Optional<Nurse> nurse = nurseRepository.findById(id);
        if (nurse.isPresent()) return Optional.of(nurse.get());

        return Optional.empty();
    }

    public void delete(String id) {
        // Try deleting from both concrete repositories
        doctorRepository.delete(id);
        nurseRepository.delete(id);
    }
}
package repository;

import org.springframework.stereotype.Repository;
import model.MedicalStaffAppointment;

@Repository
public class MedicalStaffAppointmentRepository extends InFileRepository<MedicalStaffAppointment> {
    public MedicalStaffAppointmentRepository() {
        super("medicalstaffappointment.json", MedicalStaffAppointment.class);
    }
}
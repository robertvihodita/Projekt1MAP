package Repo;

import Model.Doctor;
import java.util.ArrayList;
import java.util.List;

public class DoctorRepository {
    private List<Doctor> doctors = new ArrayList<>();

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void removeDoctor(int id) {
        doctors.removeIf(d -> d.getId() == id);
    }

    public Doctor findById(int id) {
        for (Doctor d : doctors) {
            if (d.getId() == id) {
                return d;
            }
        }
        return null;
    }

    public List<Doctor> getAllDoctors() {
        return new ArrayList<>(doctors);
    }
}

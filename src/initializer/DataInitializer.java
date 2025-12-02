package initializer;

import model.*;
import repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final HospitalRepository hospitalRepository;
    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final RoomRepository roomRepository;
    private final AppointmentRepository appointmentRepository;
    private final NurseRepository nurseRepository;
    private final MedicalStaffAppointmentRepository medicalStaffAppointmentRepository;

    // Inject all required repositories
    public DataInitializer(HospitalRepository hospitalRepository, DepartmentRepository departmentRepository,
                           DoctorRepository doctorRepository, PatientRepository patientRepository,
                           RoomRepository roomRepository, AppointmentRepository appointmentRepository,
                           NurseRepository nurseRepository, MedicalStaffAppointmentRepository medicalStaffAppointmentRepository) {
        this.hospitalRepository = hospitalRepository;
        this.departmentRepository = departmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.roomRepository = roomRepository;
        this.appointmentRepository = appointmentRepository;
        this.nurseRepository = nurseRepository;
        this.medicalStaffAppointmentRepository = medicalStaffAppointmentRepository;
    }

    @Override
    public void run(String... args) { // FIXED: Removed 'throws Exception'
        // Only initialize if the database is empty (e.g., check Hospital count)
        if (hospitalRepository.count() == 0) {
            System.out.println("Initializing database with 10 records per entity...");
            initializeData();
        } else {
            System.out.println("Database already contains data. Skipping initialization.");
        }
    }

    private void initializeData() {
        // --- 1. Hospitals (Min 2) ---
        Hospital h1 = new Hospital();
        h1.setName("Central Hospital");
        h1.setCity("Munich");
        hospitalRepository.save(h1);

        Hospital h2 = new Hospital();
        h2.setName("Westside Clinic");
        h2.setCity("Berlin");
        hospitalRepository.save(h2);

        List<Hospital> hospitals = Arrays.asList(h1, h2);

        // --- 2. Departments (Min 10) ---
        for (int i = 0; i < 10; i++) {
            Hospital hospital = hospitals.get(i % 2);
            Department department = new Department();
            department.setName("Dept-" + (i + 1));
            department.setHospital(hospital);
            departmentRepository.save(department);
        }
        List<Department> departments = departmentRepository.findAll();

        // --- 3. Doctors (Min 10) ---
        for (int i = 0; i < 10; i++) {
            Department dept = departments.get(i);
            Doctor doctor = new Doctor();
            doctor.setName("Dr. Smith " + (i + 1));
            doctor.setLicenseNumber("LIC-" + (1000 + i + 1));
            doctor.setDepartment(dept);
            doctorRepository.save(doctor);
        }
        List<Doctor> doctors = doctorRepository.findAll();

        // --- 4. Nurses (Min 10) ---
        for (int i = 0; i < 10; i++) {
            Department dept = departments.get(i);
            Nurse nurse = new Nurse();
            nurse.setName("Nurse Kelly " + (i + 1));
            nurse.setQualificationLevel("Level " + (i % 3 + 1));
            // Correctly access the Shift Enum
            nurse.setShift(i % 3 == 0 ? Nurse.Shift.NIGHT : Nurse.Shift.MORNING);
            nurse.setDepartment(dept);
            nurseRepository.save(nurse);
        }
        List<Nurse> nurses = nurseRepository.findAll();

        // --- 5. Patients (Min 10) ---
        for (int i = 0; i < 10; i++) {
            Patient patient = new Patient();
            patient.setName("Patient A" + (i + 1));
            patientRepository.save(patient);
        }
        List<Patient> patients = patientRepository.findAll();

        // --- 6. Rooms (Min 10) ---
        for (int i = 0; i < 10; i++) {
            Room room = new Room();
            room.setNumber("R" + (100 + i + 1));
            room.setCapacity(2.0);
            room.setStatus(Room.RoomStatus.AVAILABLE);
            room.setHospital(hospitals.get(i % 2));
            roomRepository.save(room);
        }
        List<Room> rooms = roomRepository.findAll();

        // --- 7. Appointments (Min 10) ---
        for (int i = 0; i < 10; i++) {
            Appointment appointment = new Appointment();
            appointment.setAdmissionDate(LocalDateTime.now().plusHours(i + 1));
            appointment.setStatus(Appointment.AppointmentStatus.ACTIVE);

            // Link existing entities
            appointment.setPatient(patients.get(i));
            appointment.setDepartment(departments.get(i));
            appointment.setRoom(rooms.get(i));

            appointmentRepository.save(appointment);
        }
        List<Appointment> appointments = appointmentRepository.findAll();

        // --- 8. MedicalStaffAppointments (Min 10 Join Table Data) ---
        for (int i = 0; i < 10; i++) {
            MedicalStaffAppointment msa = new MedicalStaffAppointment();
            msa.setAppointment(appointments.get(i));

            // Assign doctors and nurses alternately
            if (i % 2 == 0) {
                // IMPORTANT: Since MedicalStaffAppointment is linked to Doctor in the JPA model,
                // we must assign a Doctor instance here, even if it's conceptually MedicalStaff.
                msa.setMedicalStaff(doctors.get(i));
            } else {
                // If you want to include nurses, you must ensure your MedicalStaffAppointment model
                // handles the Nurse subclass correctly. For simplicity and compliance with common JPA patterns,
                // we will stick to assigning Doctors or adjust the model relationship definition.
                // Using Doctor here to avoid runtime cast exceptions:
                msa.setMedicalStaff(doctors.get(i));
            }

            msa.setStatus(MedicalStaffAppointment.Status.SCHEDULED);
            medicalStaffAppointmentRepository.save(msa);
        }
    }
}
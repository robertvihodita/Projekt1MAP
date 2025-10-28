import Model.*;
import Repo.*;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        HospitalRepository hospitalRepo = new HospitalRepository();
        DepartmentRepository departmentRepo = new DepartmentRepository();
        RoomRepository roomRepo = new RoomRepository();
        PatientRepository patientRepo = new PatientRepository();
        MedicalStaffRepository medicalStaffRepo = new MedicalStaffRepository();
        AppointmentRepository appointmentRepo = new AppointmentRepository();
        MedicalStaffAppointmentRepository msAppointmentRepo = new MedicalStaffAppointmentRepository();


        Hospital hospital = new Hospital("H1", "City Hospital", "Berlin");
        hospitalRepo.save(hospital.getId(), hospital);

        Department cardiology = new Department("D1", "Cardiology", hospital.getId());
        departmentRepo.save(cardiology.getId(), cardiology);
        hospital.getDepartments().add(cardiology.getId());

        Room room101 = new Room("R1", hospital.getId(), "101", 2, "Available");
        Room room102 = new Room("R2", hospital.getId(), "102", 1, "Occupied");
        roomRepo.save(room101.getId(), room101);
        roomRepo.save(room102.getId(), room102);
        hospital.getRooms().add(room101.getId());
        hospital.getRooms().add(room102.getId());
        cardiology.getRooms().add(room101.getId());

        Doctor doctor = new Doctor("M1", "Dr. Müller", cardiology.getId(), "LIC12345");
        Nurse nurse = new Nurse("M2", "Anna Schmidt", cardiology.getId(), "Senior");
        medicalStaffRepo.save(doctor.getId(), doctor);
        medicalStaffRepo.save(nurse.getId(), nurse);

        Patient patient = new Patient("P1", "John Doe");
        patientRepo.save(patient.getId(), patient);

        Appointment appointment = new Appointment(
                "A1",
                cardiology.getId(),
                patient.getId(),
                LocalDateTime.now(),
                "Active"
        );
        appointmentRepo.save(appointment.getId(), appointment);


        appointment.getMedicalStaff().add(doctor.getId());
        appointment.getMedicalStaff().add(nurse.getId());
        doctor.getAppointments().add(appointment.getId());
        nurse.getAppointments().add(appointment.getId());
        patient.getAppointments().add(appointment.getId());

        MedicalStaffAppointment msApp1 = new MedicalStaffAppointment("MSA1", appointment.getId(), doctor.getId());
        MedicalStaffAppointment msApp2 = new MedicalStaffAppointment("MSA2", appointment.getId(), nurse.getId());
        msAppointmentRepo.save(msApp1.getId(), msApp1);
        msAppointmentRepo.save(msApp2.getId(), msApp2);

        System.out.println("=== Hospital System Overview ===");
        System.out.println("Hospital: " + hospital.getName() + " (" + hospital.getCity() + ")");
        System.out.println("Department: " + cardiology.getName());
        System.out.println("Room IDs: " + hospital.getRooms());
        System.out.println();

        System.out.println("Patient: " + patient.getName());
        System.out.println("Appointment ID: " + appointment.getId());
        System.out.println("Status: " + appointment.getStatus());
        System.out.println("Assigned Medical Staff: ");
        for (String msId : appointment.getMedicalStaff()) {
            medicalStaffRepo.findById(msId).ifPresent(ms ->
                    System.out.println(" - " + ms.getName())
            );
        }

        System.out.println();
        System.out.println("All Appointments: " + appointmentRepo.findAll().size());
        System.out.println("All Patients: " + patientRepo.findAll().size());
    }
}

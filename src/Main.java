import controller.*;
import repository.*;
import service.*;
import model.*;

public class Main {
    public static void main(String[] args) {
        // Initialize repositories
        HospitalRepository hospitalRepo = new HospitalRepository();
        DepartmentRepository departmentRepo = new DepartmentRepository();
        RoomRepository roomRepo = new RoomRepository();
        MedicalStaffRepository staffRepo = new MedicalStaffRepository();
        PatientRepository patientRepo = new PatientRepository();
        AppointmentRepository appointmentRepo = new AppointmentRepository();

        // Initialize services
        HospitalService hospitalService = new HospitalService(hospitalRepo);
        DepartmentService departmentService = new DepartmentService(departmentRepo);
        RoomService roomService = new RoomService(roomRepo);
        MedicalStaffService staffService = new MedicalStaffService(staffRepo);
        PatientService patientService = new PatientService(patientRepo);
        AppointmentService appointmentService = new AppointmentService(appointmentRepo);

        // Initialize controllers
        HospitalController hospitalController = new HospitalController(hospitalService);
        DepartmentController departmentController = new DepartmentController(departmentService);
        RoomController roomController = new RoomController(roomService);
        MedicalStaffController staffController = new MedicalStaffController(staffService);
        PatientController patientController = new PatientController(patientService);
        AppointmentController appointmentController = new AppointmentController(appointmentService);

        // Use controllers
        hospitalController.addHospital(new Hospital("H1", "City Hospital", "Berlin"));
        departmentController.addDepartment(new Department("D1", "Cardiology", "H1"));
        roomController.addRoom(new Room("R1", "H1", "101", 2, "Available"));
        staffController.addStaff(new Doctor("M1", "Dr. Müller", "D1", "LIC123"));
        patientController.addPatient(new Patient("P1", "John Doe"));
        appointmentController.createSampleAppointment();

        // Display data
        hospitalController.showAllHospitals();
        departmentController.showAllDepartments();
        roomController.showAllRooms();
        staffController.showAllStaff();
        patientController.showAllPatients();
        appointmentController.showAllAppointments();
    }
}

package service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import repository.AppointmentRepository;
import model.Appointment;
import repository.RoomRepository;
import model.Room;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final RoomRepository roomRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, RoomRepository roomRepository) {
        this.appointmentRepository = appointmentRepository;
        this.roomRepository = roomRepository;
    }

    public Appointment addAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Optional<Appointment> getAppointmentById(String id) {
        return appointmentRepository.findById(id);
    }

    public void deleteAppointment(String id) {
        appointmentRepository.deleteById(id);
    }


    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }


    public List<Appointment> getAllAppointments(String patientId, Appointment.AppointmentStatus status,
                                                String hospitalId, LocalDate date,
                                                String sortField, String sortDir) {
        Sort sort = Sort.by(sortField != null ? sortField : "admissionDate");
        sort = "desc".equals(sortDir) ? sort.descending() : sort.ascending();

        String searchPat = (patientId != null && !patientId.isEmpty()) ? patientId : null;
        String searchHosp = (hospitalId != null && !hospitalId.isEmpty()) ? hospitalId : null;

        LocalDateTime start = (date != null) ? date.atStartOfDay() : null;
        LocalDateTime end = (date != null) ? date.atTime(23, 59, 59) : null;

        return appointmentRepository.searchAppointments(searchPat, status, searchHosp, start, end, sort);
    }


    public boolean isRoomFull(String roomId, String currentAppointmentId) {
        Room room = roomRepository.findById(roomId).orElse(null);
        if (room == null) return false;

        long currentCount = appointmentRepository.countActiveAppointmentsInRoom(roomId);


        if (currentAppointmentId != null) {
            Optional<Appointment> existing = appointmentRepository.findById(currentAppointmentId);
            if (existing.isPresent() && existing.get().getRoom() != null &&
                    existing.get().getRoom().getId().equals(roomId) &&
                    existing.get().getStatus() == Appointment.AppointmentStatus.ACTIVE) {
                currentCount--;
            }
        }

        return currentCount >= room.getCapacity();
    }
}
package controller;

import model.Room;
import service.RoomService;
import service.HospitalService; // Needed for dropdown
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;
    private final HospitalService hospitalService; // Used to populate Hospital dropdown


    public RoomController(RoomService roomService, HospitalService hospitalService) {
        this.roomService = roomService;
        this.hospitalService = hospitalService;
    }

    private void loadFormDependencies(Model model) {
        model.addAttribute("statuses", Room.RoomStatus.values());
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
    }

    @GetMapping
    public String viewAllRooms(Model model) {
        List<Room> rooms = roomService.getAllRooms();
        model.addAttribute("rooms", rooms);
        return "room/index";
    }

    @GetMapping("/new")
    public String showAddRoomForm(Model model) {
        model.addAttribute("room", new Room());
        loadFormDependencies(model);
        return "room/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditRoomForm(@PathVariable String id, Model model) {
        Optional<Room> room = roomService.getRoomById(id);
        if (room.isPresent()) {
            model.addAttribute("room", room.get());
            loadFormDependencies(model);
            return "room/form";
        }
        return "redirect:/rooms";
    }

    @PostMapping
    public String addRoom(@Valid @ModelAttribute("room") Room room,
                          BindingResult bindingResult,
                          Model model) {
        if (bindingResult.hasErrors()) {
            loadFormDependencies(model);
            return "room/form";
        }

        // Business Validation Example: Check if hospital is selected
        if (room.getHospital() == null || room.getHospital().getId().isEmpty()) {
            model.addAttribute("globalError", "A hospital must be selected for the room.");
            loadFormDependencies(model);
            return "room/form";
        }

        roomService.addRoom(room);
        return "redirect:/rooms";
    }

    @PostMapping("/{id}/delete")
    public String deleteRoom(@PathVariable String id) {
        roomService.deleteRoom(id);
        return "redirect:/rooms";
    }
}
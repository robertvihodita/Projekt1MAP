package controller;

import model.Room;
import service.RoomService;
import service.HospitalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;
    private final HospitalService hospitalService;

    public RoomController(RoomService roomService, HospitalService hospitalService) {
        this.roomService = roomService;
        this.hospitalService = hospitalService;
    }

    @GetMapping
    public String viewAllRooms(Model model,
                               @RequestParam(required = false) String number,
                               @RequestParam(required = false) Room.RoomStatus status,
                               @RequestParam(required = false, defaultValue = "number") String sortField,
                               @RequestParam(required = false, defaultValue = "asc") String sortDir) {

        model.addAttribute("rooms", roomService.getAllRooms(number, status, sortField, sortDir));
        model.addAttribute("statuses", Room.RoomStatus.values());

        model.addAttribute("number", number);
        model.addAttribute("status", status);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "room/index";
    }

    @GetMapping("/{id}")
    public String showRoomDetails(@PathVariable String id, Model model) {
        Optional<Room> roomOpt = roomService.getRoomById(id);
        if (roomOpt.isPresent()) {
            model.addAttribute("room", roomOpt.get());
            return "room/details";
        }
        return "redirect:/rooms";
    }

    // Add/Edit ...
    @GetMapping("/new")
    public String showAddRoomForm(Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        model.addAttribute("statuses", Room.RoomStatus.values());
        return "room/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditRoomForm(@PathVariable String id, Model model) {
        Optional<Room> room = roomService.getRoomById(id);
        if (room.isPresent()) {
            model.addAttribute("room", room.get());
            model.addAttribute("hospitals", hospitalService.getAllHospitals());
            model.addAttribute("statuses", Room.RoomStatus.values());
            return "room/form";
        }
        return "redirect:/rooms";
    }

    @PostMapping
    public String addRoom(@Valid @ModelAttribute("room") Room room, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("hospitals", hospitalService.getAllHospitals());
            model.addAttribute("statuses", Room.RoomStatus.values());
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
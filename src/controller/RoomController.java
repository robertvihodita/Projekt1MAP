package controller;

import model.Room;
import service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
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
        model.addAttribute("statuses", Room.RoomStatus.values());
        return "room/form";
    }

    @PostMapping
    public String addRoom(@ModelAttribute Room room) {
        roomService.addRoom(room);
        return "redirect:/rooms";
    }

    @PostMapping("/{id}/delete")
    public String deleteRoom(@PathVariable String id) {
        roomService.deleteRoom(id);
        return "redirect:/rooms";
    }
}
package controller;

import model.Nurse;
import service.NurseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/nurses") // All URLs will start with /nurses
public class NurseController {

    private final NurseService nurseService;

    public NurseController(NurseService nurseService) {
        this.nurseService = nurseService;
    }

    /**
     * Handles GET /nurses
     * Requirement: GET /<entität> [cite: 64]
     */
    @GetMapping
    public String viewAllNurses(Model model) {
        List<Nurse> nurses = nurseService.getAllNurses();
        model.addAttribute("nurses", nurses);
        // Requirement: Must return "nurse/index" [cite: 71, 72, 74]
        return "nurse/index";
    }

    /**
     * Shows the "add new nurse" form
     * Requirement: GET /<entität>/new 
     */
    @GetMapping("/new") // CHANGED from /add
    public String showAddNurseForm(Model model) {
        model.addAttribute("nurse", new Nurse());
        // Requirement: Must return "nurse/form" [cite: 71, 72, 75]
        return "nurse/form";
    }

    /**
     * Handles the POST request from the form
     * Requirement: POST /<entität> 
     */
    @PostMapping // CHANGED from /add
    public String addNurse(@ModelAttribute Nurse nurse) {
        nurseService.addNurse(nurse);
        return "redirect:/nurses"; // Go back to the nurse list
    }

    /**
     * Handles the delete POST request
     * Requirement: POST /<entität>/{id}/delete 
     */
    @PostMapping("/{id}/delete") // CHANGED from @GetMapping("/delete/{id}")
    public String deleteNurse(@PathVariable String id) {
        nurseService.deleteNurse(id);
        return "redirect:/nurses"; // Go back to the nurse list
    }
}
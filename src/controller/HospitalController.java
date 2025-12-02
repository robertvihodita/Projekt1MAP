package controller;

import service.HospitalService;
import model.Hospital;
import model.Department;
import model.MedicalStaff;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/hospitals")
public class HospitalController {
    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping
    public String showAllHospitals(Model model) {
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        return "hospital/index";
    }

    @GetMapping("/new")
    public String showAddHospitalForm(Model model) {
        model.addAttribute("hospital", new Hospital());
        return "hospital/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditHospitalForm(@PathVariable String id, Model model) {
        Optional<Hospital> hospital = hospitalService.getHospitalById(id);
        if (hospital.isPresent()) {
            model.addAttribute("hospital", hospital.get());
            return "hospital/form";
        }
        return "redirect:/hospitals";
    }

    @PostMapping
    public String addHospital(@Valid @ModelAttribute("hospital") Hospital hospital,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "hospital/form";
        }
        hospitalService.addHospital(hospital);
        return "redirect:/hospitals";
    }

    // NEW: Details Page
    @GetMapping("/{id}")
    public String showHospitalDetails(@PathVariable String id, Model model) {
        Optional<Hospital> hospitalOpt = hospitalService.getHospitalById(id);
        if (hospitalOpt.isPresent()) {
            Hospital hospital = hospitalOpt.get();
            model.addAttribute("hospital", hospital);

            // Gather all staff from all departments in this hospital
            List<MedicalStaff> allStaff = new ArrayList<>();
            for (Department dept : hospital.getDepartments()) {
                allStaff.addAll(dept.getStaff());
            }
            model.addAttribute("staffList", allStaff);

            return "hospital/details";
        }
        return "redirect:/hospitals";
    }

    @PostMapping("/{id}/delete")
    public String deleteHospital(@PathVariable String id) {
        hospitalService.deleteHospital(id);
        return "redirect:/hospitals";
    }
}
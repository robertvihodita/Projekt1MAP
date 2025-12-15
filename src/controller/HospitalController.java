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

    // UPDATED: Accepts search and sort params
    @GetMapping
    public String showAllHospitals(Model model,
                                   @RequestParam(required = false) String keyword,
                                   @RequestParam(required = false) String city,
                                   @RequestParam(required = false, defaultValue = "name") String sortField,
                                   @RequestParam(required = false, defaultValue = "asc") String sortDir) {

        List<Hospital> hospitals = hospitalService.getAllHospitals(keyword, city, sortField, sortDir);

        model.addAttribute("hospitals", hospitals);

        // Pass back params to keep form populated and sort links working
        model.addAttribute("keyword", keyword);
        model.addAttribute("city", city);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

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

    @GetMapping("/{id}")
    public String showHospitalDetails(@PathVariable String id, Model model) {
        Optional<Hospital> hospitalOpt = hospitalService.getHospitalById(id);
        if (hospitalOpt.isPresent()) {
            Hospital hospital = hospitalOpt.get();
            model.addAttribute("hospital", hospital);
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
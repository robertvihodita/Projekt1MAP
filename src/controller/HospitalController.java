package controller;

import service.HospitalService;
import model.Hospital;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

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
        List<Hospital> hospitals = hospitalService.getAllHospitals();
        model.addAttribute("hospitals", hospitals);
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


    @PostMapping("/{id}/delete")
    public String deleteHospital(@PathVariable String id) {
        hospitalService.deleteHospital(id);
        return "redirect:/hospitals";
    }
}
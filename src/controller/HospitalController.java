package controller;

import service.HospitalService;
import model.Hospital;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @PostMapping
    public String addHospital(@ModelAttribute Hospital hospital) {
        hospitalService.addHospital(hospital);
        return "redirect:/hospitals";
    }


    @PostMapping("/{id}/delete")
    public String deleteHospital(@PathVariable String id) {
        hospitalService.deleteHospital(id);
        return "redirect:/hospitals";
    }
}
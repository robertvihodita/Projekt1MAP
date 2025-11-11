//package controller;
//
//import service.HospitalService;
//import model.Hospital;
//import java.util.List;
//
//public class HospitalController {
//    private final HospitalService hospitalService;
//
//    public HospitalController(HospitalService hospitalService) {
//        this.hospitalService = hospitalService;
//    }
//
//    public void addHospital(Hospital hospital) {
//        Hospital.Department departmentfromString = Hospital.Department.valueOf("CARDIOLOGY");
//        System.out.println("Converted from string: " + departmentfromString);
//
//        Hospital.RoomType roomTypeFromString = Hospital.RoomType.valueOf("EMERGENCY");
//        System.out.println("Converted from string: " + roomTypeFromString);
//
//
//        hospitalService.addHospital(hospital);
//        System.out.println("Hospital added: " + hospital.getName());
//    }
//
//    public void showAllHospitals() {
//        List<Hospital> hospitals = hospitalService.getAllHospitals();
//        System.out.println("=== Hospitals ===");
//        for (Hospital h : hospitals) {
//            System.out.println(h.getId() + " - " + h.getName() + " (" + h.getCity() + ")");
//        }
//    }
//
//    public void deleteHospital(String id) {
//        hospitalService.deleteHospital(id);
//        System.out.println("Deleted hospital with ID: " + id);
//    }
//}

package controller;

import service.HospitalService;
import model.Hospital;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/hospitals") // All URLs will start with /hospitals
public class HospitalController {
    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    /**
     * Replaces showAllHospitals()
     * Handles GET http://localhost:8080/hospitals
     */
    @GetMapping
    public String showAllHospitals(Model model) {
        List<Hospital> hospitals = hospitalService.getAllHospitals();
        model.addAttribute("hospitals", hospitals); // Send list to HTML
        return "appointment/index"; // Loads "hospitals.html"
    }

    /**
     * Shows the "add new hospital" form
     * Handles GET http://localhost:8080/hospitals/add
     */
    @GetMapping("/add")
    public String showAddHospitalForm(Model model) {
        model.addAttribute("hospital", new Hospital()); // Send empty object for the form
        return "appointment/form"; // Loads "add-hospital.html"
    }

    /**
     * Replaces addHospital()
     * Handles the POST request from the "add-hospital" form
     */
    @PostMapping("/add")
    public String addHospital(@ModelAttribute Hospital hospital) {
        hospitalService.addHospital(hospital);
        return "redirect:/hospitals"; // Go back to the hospital list
    }

    /**
     * Replaces deleteHospital()
     * Handles GET http://localhost:8080/hospitals/delete/some-id
     */
    @GetMapping("/delete/{id}")
    public String deleteHospital(@PathVariable String id) {
        hospitalService.deleteHospital(id);
        return "redirect:/hospitals"; // Go back to the hospital list
    }
}

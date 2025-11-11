package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /**
     * Handles requests to the homepage (http://localhost:8080/)
     */
    @GetMapping("/")
    public String showHomePage() {
        // This tells Spring to load the file at:
        // src/templates/home/index.html
        return "home/index";
    }
}
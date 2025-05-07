package ru.is.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.is.service.AuthService;

@Controller
public class ChancController {
    private final AuthService authService;

    @Autowired
    public ChancController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String username, @RequestParam String password, Model model) {

        boolean isAuthenticated = authService.authenticate(username, password);
        if (isAuthenticated) {
            return "redirect:/chanc";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/chanc")
    public String showChancPage() {
        return "chanc";
    }
}
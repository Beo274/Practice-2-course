package ru.is.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.is.service.AuthService;

@Controller
public class LoginController {
    private static final Logger logger = LogManager.getLogger(LoginController.class);

    private final AuthService authService;

    @Autowired
    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String username, @RequestParam String password, Model model) {
        logger.info("Login username:" + username + "; password: " + password);
        boolean isAuthenticated = authService.authenticate(username, password);
        if (isAuthenticated) {
            return "redirect:/chanc";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
}
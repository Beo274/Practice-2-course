package ru.is.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.is.models.User;
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

    @Transactional
    @PostMapping("/login")
    public String handleLogin(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam(required = false, defaultValue = "false") boolean rememberMe,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {

        logger.info("Login attempt for username: {}", username);

        User user = authService.authenticate(username, password);

        if (user == null) {
            logger.warn("Authentication failed for username: {}", username);
            model.addAttribute("error", "Неверный логин или пароль");
            return "login";
        }

        // Успешная аутентификация
        request.getSession().setAttribute("loggedUser", user);

        if (rememberMe) {
            Cookie cookie = new Cookie("rememberMe", username);
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60 * 24 * 30); // 30 дней
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
        }

        return "redirect:/chanc";
    }
}
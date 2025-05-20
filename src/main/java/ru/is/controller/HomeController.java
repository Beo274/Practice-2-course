package ru.is.controller;

import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.is.models.Appeal;
import ru.is.service.SendAppealService;

@Controller
@RequestMapping("/home")
public class HomeController {
    private static final Logger logger = LogManager.getLogger(HomeController.class);
    private final SendAppealService sendAppealService;

    @Autowired
    public HomeController(SendAppealService sendAppealService) {
        this.sendAppealService = sendAppealService;
    }

    // Добавляем обработчик для корневого URL
    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/home";
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("appeal", new Appeal());
        return "home";
    }

    @PostMapping
    public String createAppeal(@Valid @ModelAttribute("appeal") Appeal appeal,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            logger.warn("Ошибка валидации при создании заявления: {}", bindingResult.getAllErrors());
            return "home";
        }

        try {
            Long id = sendAppealService.createAppeal(appeal);
            logger.info("Заявление создано: {}", id);
            redirectAttributes.addFlashAttribute("successMessage", "Заявление успешно отправлено!");
        } catch (Exception e) {
            logger.error("Ошибка при создании заявления", e);
            redirectAttributes.addFlashAttribute("errorMessage", "Ошибка при отправке заявления");
        }
        return "redirect:/home";
    }
}
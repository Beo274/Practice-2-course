package ru.is.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping
    public String home() {
        return "home";
    }

    @PostMapping
    public String createAppeal(Appeal appeal, RedirectAttributes redirectAttributes) {
        try {
            Long id = sendAppealService.createAppeal(appeal);
            logger.info("Заявление создано: {}", id);
            redirectAttributes.addFlashAttribute("successMessage", "Заявление успешно отправлено!");
            return "redirect:/home";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Ошибка при отправке заявления");
            return "redirect:/home";
        }
    }

}
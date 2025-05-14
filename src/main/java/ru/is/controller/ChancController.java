package ru.is.controller;

import jakarta.validation.Valid;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.is.models.Appeal;
import ru.is.service.WatchAppealService;
import ru.is.service.SendAppealService;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.List;

@Controller
public class ChancController {

    private final WatchAppealService watchAppealService;
    private final SendAppealService sendAppealService;

    @Autowired
    public ChancController(WatchAppealService watchAppealService, SendAppealService sendAppealService) {
        this.watchAppealService = watchAppealService;
        this.sendAppealService = sendAppealService;
    }

    // Существующий метод для отображения страницы канцелярии
    @GetMapping("/chanc")
    public String showChancPage(Model model) {
        List<Appeal> unresolvedAppeals = watchAppealService.getAppealsWithEmptyResolution();

        unresolvedAppeals.forEach(appeal -> {
            String info = String.format(
                    "Заявитель: %s\nМенеджер: %s\nАдрес: %s\nТема: %s\nСодержание: %s",
                    appeal.getApplicantName(),
                    appeal.getManagerName(),
                    appeal.getAddress(),
                    appeal.getTheme(),
                    appeal.getContent()
            );

            try {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                QRCode.from(info)
                        .withCharset("UTF-8")
                        .writeTo(outputStream);

                String base64Qr = Base64.getEncoder().encodeToString(outputStream.toByteArray());
                appeal.setQrCodeBase64("data:image/png;base64," + base64Qr);
            } catch (Exception e) {
                appeal.setQrCodeBase64("");
                e.printStackTrace();
            }
        });

        model.addAttribute("appeals", unresolvedAppeals);
        return "chanc";
    }

    // Новый метод для обработки отправки заявления
    @PostMapping("/submit-appeal")
    public String submitAppeal(@Valid @ModelAttribute("appeal") Appeal appeal,
                               BindingResult bindingResult,
                               Model model) {

        if (bindingResult.hasErrors()) {
            // Если есть ошибки валидации, возвращаем пользователя на форму с сообщениями об ошибках
            return "home"; // предполагается, что форма находится на странице home
        }

        try {
            sendAppealService.saveAppeal(appeal);
            return "redirect:/home?success"; // редирект с параметром успеха
        } catch (Exception e) {
            model.addAttribute("error", "Произошла ошибка при сохранении заявления");
            return "home"; // возврат на форму с сообщением об ошибке
        }
    }
}
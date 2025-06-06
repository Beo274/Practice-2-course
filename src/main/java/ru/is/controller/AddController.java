package ru.is.controller;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.is.models.Appeal;
import ru.is.repository.AppealRepository;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/chanc")
@Transactional
public class AddController {

    @Autowired
    private AppealRepository appealRepository;

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("appealInput", "");
        return "addAppeal";
    }

    private String decodeQr(InputStream in) throws Exception {
        BufferedImage img = ImageIO.read(in);
        if (img == null) {
            throw new IllegalArgumentException("Невозможно прочитать изображение");
        }
        LuminanceSource source = new BufferedImageLuminanceSource(img);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result = new MultiFormatReader().decode(bitmap);
        return result.getText();
    }

    @PostMapping("/add")
    @Transactional
    public String processAppeal(
            @RequestParam("qrFile") MultipartFile qrFile,
            Model model) {

        String appealInput;
        try {
            appealInput = decodeQr(qrFile.getInputStream());
        } catch (Exception e) {
            model.addAttribute("error", "Ошибка при декодировании QR-кода: " + e.getMessage());
            model.addAttribute("appealInput", "");
            return "addAppeal";
        }

        try {
            String[] lines = appealInput.split("\n");
            Map<String, String> fields = new HashMap<>();

            for (String line : lines) {
                String[] parts = line.split(":", 2);
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    fields.put(key, value);
                }
            }

            Long appealId = null;
            if (fields.containsKey("ID")) {
                try {
                    appealId = Long.parseLong(fields.get("ID"));
                } catch (NumberFormatException e) {
                    model.addAttribute("error", "Ошибка: ID должен быть числом");
                    model.addAttribute("appealInput", appealInput);
                    return "addAppeal";
                }
            }

            Appeal appeal = null;
            if (appealId != null) {
                appeal = appealRepository.findById(appealId);
                if (appeal == null) {
                    model.addAttribute("error", "Ошибка: Обращение с ID " + appealId + " не найдено");
                    model.addAttribute("appealInput", appealInput);
                    return "addAppeal";
                }
            } else {
                appeal = new Appeal();
            }

            if (fields.containsKey("Заявитель")) {
                appeal.setApplicantName(fields.get("Заявитель"));
            }
            if (fields.containsKey("Менеджер")) {
                appeal.setManagerName(fields.get("Менеджер"));
            }
            if (fields.containsKey("Адрес")) {
                appeal.setAddress(fields.get("Адрес"));
            }
            if (fields.containsKey("Тема")) {
                appeal.setTheme(fields.get("Тема"));
            }
            if (fields.containsKey("Содержание")) {
                appeal.setContent(fields.get("Содержание"));
            }
            if (fields.containsKey("Резолюция")) {
                appeal.setResolution(fields.get("Резолюция"));
            }
            if (fields.containsKey("Заметка")) {
                appeal.setNote(fields.get("Заметка"));
            }

            if (appeal.getId() == null) {
                appealRepository.save(appeal);
            } else {
                appealRepository.update(appeal);
            }

            String updatedOutput = String.format(
                    "ID:%d\nЗаявитель: %s\nМенеджер: %s\nАдрес: %s\nТема: %s\nСодержание: %s\nРезолюция: %s\nЗаметка: %s",
                    appeal.getId(),
                    appeal.getApplicantName(),
                    appeal.getManagerName(),
                    appeal.getAddress(),
                    appeal.getTheme(),
                    appeal.getContent(),
                    appeal.getResolution(),
                    appeal.getNote()
            );

            model.addAttribute("message", "Обращение успешно сохранено");
            model.addAttribute("appealInput", updatedOutput);

        } catch (Exception e) {
            model.addAttribute("error", "Ошибка при обработке данных: " + e.getMessage());
            model.addAttribute("appealInput", "");
        }

        return "addAppeal";
    }

}
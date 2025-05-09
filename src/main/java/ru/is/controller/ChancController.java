package ru.is.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChancController {

    @GetMapping("/chanc")
    public String showChancPage() {
        return "chanc";
    }
}

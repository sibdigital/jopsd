package ru.sibdigital.jopsd.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class MainController extends SuperController {
    @GetMapping({"", "/upload/el_budget", "/upload/mpp"})
    public String admin(Model model) {
        return "index";
    }
}

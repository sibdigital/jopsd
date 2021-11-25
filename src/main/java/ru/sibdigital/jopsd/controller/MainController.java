package ru.sibdigital.jopsd.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
public class MainController extends SuperController {
    @GetMapping("/upload/el_budget")
    public String elBudgetPage(Model model) {
        model.addAttribute("title", "Загрузка из Электронного Бюджета | Цифровой проект");
        return "index";
    }

    @GetMapping("/upload/mpp")
    public String mppPage(Model model) {
        model.addAttribute("title", "Загрузка из MS Project | Цифровой проект");
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void method(HttpServletResponse httpServletResponse) {
        // если юзер разлогинился в исуп, то он сам дальше редирекнет внутри на /login, поэтому только на url исупа редирект идет
        httpServletResponse.setHeader("Location", settingService.getOpsdHref());
        httpServletResponse.setStatus(302);
    }

    @GetMapping({"/test"})
    public String test(Model model) {
        return "test";
    }

}

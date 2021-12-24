package ru.sibdigital.jopsd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.sibdigital.jopsd.model.opsd.Position;

@Controller
public class PositionController extends SuperController {
    @PostMapping("position/save")
    public @ResponseBody
    Position savePosition(@RequestBody Position body) {
        return positionService.save(body);
    }
}

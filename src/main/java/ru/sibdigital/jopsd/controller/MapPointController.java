package ru.sibdigital.jopsd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.sibdigital.jopsd.model.opsd.MapPoint;

@Controller
public class MapPointController extends SuperController{
    @PostMapping("mapPoints/save")
    public @ResponseBody
    MapPoint saveMapPoint(@RequestBody MapPoint body) {
        return mapPointService.save(body);
    }
}

package ru.sibdigital.jopsd.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sibdigital.jopsd.config.user.details.CustomUserDetails;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class PermissionController extends SuperController{
    @GetMapping("user/isPermittedTo")
    public List<Map<String, Object>> isPermitted(@RequestParam("permissions") Set<String> permissions,
                                          @RequestParam("projectId") Long projectId,
                                          @AuthenticationPrincipal CustomUserDetails user) {
        return userService.checkPermissions(projectId, user.getUser().getId(), permissions);
    }
}

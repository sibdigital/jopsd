package ru.sibdigital.jopsd.controller.mp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.sibdigital.jopsd.controller.SuperController;

@Slf4j
@Controller
public class MPImportController extends SuperController {

    @GetMapping("/import_mpp")
    public ResponseEntity<String> importMpp() {
        try {
            MPService.importFiles();
        } catch (Exception e) {
            logError(e);
        }
        return ResponseEntity.ok().body("Ok");
    }
}

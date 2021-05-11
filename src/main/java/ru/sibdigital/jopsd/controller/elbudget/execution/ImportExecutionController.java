package ru.sibdigital.jopsd.controller.elbudget.execution;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.sibdigital.jopsd.controller.SuperController;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class ImportExecutionController extends SuperController {
//    @PostMapping("/import/execution")
    @GetMapping("/import/execution")
    public ResponseEntity<String> importExecution(
//                                            @RequestParam("file") MultipartFile multipartFile,
                                            @RequestParam("projectId") Long projectId,
                                            @RequestParam("authorId") Long authorId
                                            ) {
        try {
//            File file = File.createTempFile("upload-", ".mpp");
//            multipartFile.transferTo(file);
            File file = new File("D:/sibdigital/New_workplaces.xml");

            Map<String, Object> params = new HashMap<>();
            params.put("projectId", projectId);
            params.put("authorId", authorId);

            executionService.importFile(file, params);

            return ResponseEntity.ok()
                    .body("{\"cause\": \"Файл успешно загружен\"," +
                            "\"status\": \"server\"," +
//                            "\"sname\": \"" + multipartFile.getOriginalFilename() + "\"}");
                            "}");
        } catch (Exception e) {
            logError(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"status\": \"server\"," +
                            "\"cause\":\"Ошибка сохранения\"," +
//                            "\"sname\": \"" + multipartFile.getOriginalFilename() + "\"}");
                            "}");
        }
    }
}

package ru.sibdigital.jopsd.controller.elbudget.execution;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.sibdigital.jopsd.controller.SuperController;
import ru.sibdigital.jopsd.model.opsd.WorkPackage;
import ru.sibdigital.jopsd.utils.DataFormatUtils;

import java.util.Map;


@RestController
@Slf4j
public class ExportExecutionController extends SuperController {
    @GetMapping(value = "/export/execution/{id_work_package}")
    public ResponseEntity findWorkPackage(@PathVariable("id_work_package") Long idWorkPackage){
        try {
            if (exportExecutionService.readyForExportEb(idWorkPackage)) {
                WorkPackage workPackage = workPackageService.getWorkPackageById(idWorkPackage);
                if (workPackage.getStartDate() == null) {
                    return DataFormatUtils.buildInternalServerErrorResponse(Map.of("status", "no start date", "cause", "Не указана дата начала мероприятия!"));
                }
                byte[] bytes = exportExecutionService.createExecutionFromWP(workPackage);

                MediaType mediaType = MediaType.parseMediaType("text/xml");
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + "wp_eb.xml")
                        .contentType(mediaType)
                        .contentLength(bytes.length)
                        .body(bytes);
            }
            else {
                return DataFormatUtils.buildInternalServerErrorResponse(Map.of("status", "no meta id", "cause", "Нет metaId из ЭБ. Загрузите файл из Электронного Бюджета в ИСУП."));
            }
        }
        catch (Exception exception) {
            log.error("Ошибка записи файла. {}", exception);
            String ad = "dfs";
            return DataFormatUtils.buildInternalServerErrorResponse(Map.of("status", "error"));
        }
    }

}

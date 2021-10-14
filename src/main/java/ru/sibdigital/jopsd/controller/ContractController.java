package ru.sibdigital.jopsd.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.sibdigital.jopsd.dto.ContractDto;
import ru.sibdigital.jopsd.model.opsd.Contract;

import javax.servlet.http.HttpSession;

@Controller
public class ContractController extends SuperController{

    @PostMapping("/contracts/save")
    public @ResponseBody
    Object saveContract(@RequestBody ContractDto contractDto, HttpSession session) {
        try {
//            CustomUserDetails currentUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            User user = currentUser.getUser();

            Contract contract = contractService.saveContract(contractDto);

            if (contract != null) {
                return contract;
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("{\"status\": \"server\"," +
                                "\"cause\":\"costObject is null\"," +
                                "\"sname\": \"Ошибка сохранения\"}");
            }
        }
        catch (Exception e) {
            logError(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"status\": \"server\"," +
                            "\"cause\":\"" + e.getMessage() + "\"," +
                            "\"sname\": \"Ошибка сохранения\"}");
        }
    }
}

package ru.sibdigital.jopsd.dto.bot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class RegTargetSystemUser {

    private String login;
    private String targetSystemCode;
    private String firstname;
    private String lastname;
    private String patronymic;
    private String email;
    private String identificator;
}

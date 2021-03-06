package ru.sibdigital.jopsd.dto.bot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class RegIncomRequest {

    private String userId;
    private String idIncomRequest;
    private String requestBody;
    private String eventTypeCode;
    private String idBot;
    private String codeMessenger;
    private String idProject;
}

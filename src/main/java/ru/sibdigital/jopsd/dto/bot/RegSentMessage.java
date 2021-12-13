package ru.sibdigital.jopsd.dto.bot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class RegSentMessage {

    private String userId;
    private String idIncomRequest;
    private String text;
    private String eventTypeCode;
    private String settings;
    private String targetSystemCode;

}

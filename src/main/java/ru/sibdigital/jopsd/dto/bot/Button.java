package ru.sibdigital.jopsd.dto.bot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Button {


    private String identificator;
    private String label;
    private String eventTypeCode;
    private String text;
    private String idBot;

}

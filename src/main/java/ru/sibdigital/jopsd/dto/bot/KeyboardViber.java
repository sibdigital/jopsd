package ru.sibdigital.jopsd.dto.bot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class KeyboardViber {

    private Integer columns;
    private Integer rows;
    private String ActionType;
    private String ActionBody;
    private String Text;
    private String BgColor;

}

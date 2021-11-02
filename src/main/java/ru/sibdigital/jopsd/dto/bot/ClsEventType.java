package ru.sibdigital.jopsd.dto.bot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ClsEventType {

    private String code;
    private String name;
    private String type;
    private String idParent;
    private String idTargetSystem;
    private String dateCreate;

}

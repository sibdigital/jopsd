package ru.sibdigital.jopsd.dto.mp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sibdigital.jopsd.model.WorkPackage;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class MPWorkPackage {
    private Integer mpId;
    private WorkPackage workPackage;
    private Integer mpParentId;
}

package ru.sibdigital.jopsd.service.elbudget;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.dto.elbudget.execution.Resultsexecution;
import ru.sibdigital.jopsd.service.SuperServiceImpl;

import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Collection;
import java.util.Map;

@Service
@Slf4j
public class ExecutionServiceImpl extends SuperServiceImpl implements ExecutionService {

    @Override
    public void importFile(File file, Map<String, Object> params) {
        Unmarshaller unmarshaller = getUnmarshaller(Resultsexecution.class);
        if (unmarshaller == null) {
            logError("Не удалось создать демаршаллизатор");
            return;
        }

        Resultsexecution resultsexecution = null;
        try {
            resultsexecution = (Resultsexecution) unmarshaller.unmarshal(file);
        } catch (Exception e) {
            logError(e);
        }
    }

}

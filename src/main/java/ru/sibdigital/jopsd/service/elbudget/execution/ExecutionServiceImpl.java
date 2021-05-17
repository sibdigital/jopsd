package ru.sibdigital.jopsd.service.elbudget.execution;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.dto.elbudget.execution.Resultsexecution;
import ru.sibdigital.jopsd.service.SuperServiceImpl;

import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.Map;

@Service
@Slf4j
public class ExecutionServiceImpl extends SuperServiceImpl implements ExecutionService {

    @Override
    public void importFile(InputStream inputStream, Map<String, Object> params) throws Exception {
        Unmarshaller unmarshaller = getUnmarshaller(Resultsexecution.class);
        if (unmarshaller == null) {
            throw new Exception("Не удалось создать демаршаллизатор");
        }

        Resultsexecution resultsExecution = (Resultsexecution) unmarshaller.unmarshal(inputStream);
        processResultExecution(resultsExecution, params);
    }

    private void processResultExecution(Resultsexecution resultsExecution, Map<String, Object> params) {
        Resultsexecution.RegProject regProject = resultsExecution.getRegProject();
        if (regProject != null) {
            riskService.saveRisks(regProject, params);
            targetService.saveTargets(regProject, params);
        }
    }
}

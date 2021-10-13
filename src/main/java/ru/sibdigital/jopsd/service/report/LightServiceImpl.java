package ru.sibdigital.jopsd.service.report;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.model.report.SecondColorlight;

import javax.persistence.Query;
import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class LightServiceImpl extends JasperReportServiceImpl implements LightService {

    @Override
    public byte[] exportLightReport(Long typeId, Long projectId) {
        try {
            List<Object> secondColorlights = getArbitaryObjectContractLightsForReport(typeId, projectId);

            Map<String, Object> parameters = new HashMap<>();

            parameters.put("net.sf.jasperreports.print.keep.full.text", true);
            parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
            parameters.put(JRParameter.REPORT_LOCALE, new Locale("ru", "RU"));

            String jrxmlPath = "classpath:reports/contracts/colorlight2.jrxml";

            return exportJasperReport(jrxmlPath, secondColorlights, parameters, "xlsx");
        } catch (Exception e) {
            logError(e);
        }
        return new byte[0];
    }

    private List<Object> getArbitaryObjectContractLightsForReport(Long typeId, Long projectId) throws IOException {
        Query query = getArbitatyObjectQuery(typeId, projectId);
        return query.getResultList();
    }

    private Query getArbitatyObjectQuery(Long typeId, Long projectId) throws IOException {
        String queryString = getQueryString("classpath:reports/contracts/colorlight2.sql");
        Query query = entityManager.createNativeQuery(queryString, SecondColorlight.class);
        query.setParameter("type_id",typeId);
//        query.setParameter("project_id", projectId);
        return query;
    }

}

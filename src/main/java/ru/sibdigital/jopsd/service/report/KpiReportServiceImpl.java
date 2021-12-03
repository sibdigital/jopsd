package ru.sibdigital.jopsd.service.report;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.dto.KpiReportDto;
import ru.sibdigital.jopsd.model.opsd.Kpi;
import ru.sibdigital.jopsd.model.opsd.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Service
@Slf4j
public class KpiReportServiceImpl extends JasperReportServiceImpl implements KpiReportService{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public byte[] exportKpiReport(Long userId) {
        try {
            List<Kpi> kpis = kpiRepository.findAllByIsDeletedFalse();
            User user = userRepository.findById(userId).orElse(null);
            List<KpiReportDto> kpiReports = new ArrayList<>();

            kpis.forEach(item -> {
                String value = "";
                try {
                    Query query = entityManager.createNativeQuery(item.getQuery());
                    query.setParameter("user_id", userId);
                    value = query.getSingleResult().toString();
                } catch (Exception exception) {
                    value = "Ошибка выполнения запроса";
                    log.error("Ошибка выполнения запроса.", exception);
                }
                KpiReportDto kpiReportDto = KpiReportDto.builder()
                        .kpi(item)
                        .user(user)
                        .value(value)
                        .build();
                kpiReports.add(kpiReportDto);
            });
            JRBeanCollectionDataSource jRBean = new JRBeanCollectionDataSource(kpiReports);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("DataSource", jRBean);

            parameters.put("net.sf.jasperreports.print.keep.full.text", true);
            parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
            parameters.put(JRParameter.REPORT_LOCALE, new Locale("ru", "RU"));
            parameters.put("reportTitle", "Отчет KPI");


            String jrxmlPath = "classpath:reports/kpis/kpi.jrxml";

            return exportJasperReport(jrxmlPath, kpiReports, parameters, "html");
        } catch (Exception e) {
            logError(e);
        }
        return new byte[0];
    }
}

package ru.sibdigital.jopsd.service.report;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.service.SuperServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@Slf4j
@PropertySource("classpath:reports")
public class JasperReportServiceImpl extends SuperServiceImpl implements JasperReportService {

    @Autowired
    ResourceLoader resourceLoader;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public  <T> byte[] exportJasperReport(String jrxmlPath, List<T> dataSourceList, Map<String, Object> parameters, String reportFormat) {
        try {
            // Load file and compile it

            InputStream inputStream = resourceLoader.getResource(jrxmlPath).getInputStream();
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataSourceList);


            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            final ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] bytes = null;
            Exporter exporter = null;
            boolean html = false;

            if (reportFormat.equalsIgnoreCase("pdf")) {
                exporter = new JRPdfExporter();
            } else if (reportFormat.equalsIgnoreCase("html")) {
                exporter = new HtmlExporter();
                exporter.setExporterOutput(new SimpleHtmlExporterOutput(out));
                html = true;
            } else if (reportFormat.equalsIgnoreCase("xlsx")) {
                exporter = new JRXlsxExporter();
            }

            if (!html) {
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
            }
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.exportReport();

            return out.toByteArray();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }

    }

    public String getQueryString(String path) throws IOException {
        InputStream inputStream = resourceLoader.getResource(path).getInputStream();
        String query = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        return query;
    }

}



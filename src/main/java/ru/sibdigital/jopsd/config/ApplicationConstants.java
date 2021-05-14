package ru.sibdigital.jopsd.config;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "constants.yml", encoding = "UTF-8")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApplicationConstants {

    @Value("${application-name}")
    private String applicationName;

    @Value("${ref-test-portal}")
    protected String refTestPortal;

    @Value("${ref-working-portal}")
    protected String refWorkingPortal;
}

package ru.sibdigital.jopsd.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.logging.Logger;

public class RequestUtils {


    private static final org.slf4j.Logger log = LoggerFactory.getLogger(RequestUtils.class);

    public static<T, K> List<T> postEntities(String url, K request, Class clazz){
        List<T> entities = new ArrayList<>();
        try {
            final ObjectMapper objectMapper = new ObjectMapper();

            final HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            final HttpEntity<K> httpEntity = new HttpEntity<>(request, headers);

            final RestTemplate restTemplate = new RestTemplate();
            final ResponseEntity<String[]> response = restTemplate.postForEntity(url, httpEntity, String[].class);
            final String[] stringEntities = response.getBody();

            Arrays.stream(stringEntities).forEach(se -> {
                try {
                    T entity = (T) objectMapper.readValue(se, clazz);
                    entities.add(entity);
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            });
        }catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return entities;
    }

    public static <T> String toJSON(T object){
        final ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        try {
            json = objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return json;
    }
}
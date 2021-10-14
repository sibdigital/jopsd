package ru.sibdigital.jopsd.utils;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DataFormatUtils {

    public static final int DEFAULT_PAGE_SIZE = 25;

    public static final String PAGE_DATA_PARAM = "data";
    public static final String PAGE_POS_PARAM = "pos";
    public static final String PAGE_TOTAL_COUNT_PARAM = "total_count";

    public static ResponseEntity<String> buildResponse(ResponseEntity.BodyBuilder builder, Map<Object, Object> entries){
        String body = "";
        String sentries = entries.entrySet().stream()
                .filter(e -> e.getKey() != null)
                .map(e -> "\"" + e.getKey() + "\" : \"" + Objects.toString(e.getValue(), "") + "\"")
                .reduce((s1, s2) -> s1 + "," + s2)
                .orElse("");
        body = "{" + sentries + "}";
        final ResponseEntity<String> sb = builder.body(body);
        return sb;
    }

    public static ResponseEntity<String> buildResponse(ResponseEntity.BodyBuilder builder, String property, Object value){
        return buildResponse(builder, Map.of(property, value));
    }

    public static ResponseEntity<String> buildInternalServerErrorResponse(Map<Object, Object> entries){
        return buildResponse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR), entries);
    }

    public static ResponseEntity<String> buildOkResponse(Map<Object, Object> entries){
        return buildResponse(ResponseEntity.ok(), entries);
    }

//    public static<T> Map<String, Object> createPage(Page<T> page, Integer start, Integer count, Integer pageSize){
//        int numPage = (start == null || pageSize == 0) ? 0 : start / pageSize;
//        int size = (count == null) ? pageSize : count;
//
//        return createPage(page, numPage, size);
//    }

    public static<T> Map<String, Object> createPage(Page<T> page, Integer numPage, Integer size){
        Map<String, Object> result = new HashMap<>();
        result.put(PAGE_DATA_PARAM, page.getContent());
        result.put(PAGE_POS_PARAM, (long) numPage * size);
        result.put(PAGE_TOTAL_COUNT_PARAM, page.getTotalElements());
        return result;
    }

    public static int getPageNumber(Integer start, Integer pageSize){
        int numPage =  (start == null || pageSize == 0)  ? 0 : start / pageSize;
        return numPage;
    }

    public static int getPageNumber(Integer start){
        return getPageNumber(start, DataFormatUtils.DEFAULT_PAGE_SIZE);
    }
}
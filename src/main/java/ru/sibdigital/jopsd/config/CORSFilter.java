package ru.sibdigital.jopsd.config;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class CORSFilter implements Filter {

    static Logger logger = LoggerFactory.getLogger(CORSFilter.class);

    @Value("${cors.access-control-allow-origin}") String accessControlAllowOrigin;
    @Value("${cors.access-control-allow-credentials}") String accessControlAllowCredentials;
    @Value("${cors.access-control-allow-methods}") String accessControlAllowMethods;
    @Value("${cors.access-control-max-age}") String accessControlMaxAge;
    @Value("${cors.access-control-allow-headers}") String accessControlAllowHeaders;
    @Value("${cors.access-control-expose-headers}") String accessControlExposeHeaders;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        response.setHeader("Access-Control-Allow-Origin", accessControlAllowOrigin);
        response.setHeader("Access-Control-Allow-Credentials", accessControlAllowCredentials);
        response.setHeader("Access-Control-Allow-Methods", accessControlAllowMethods);
        response.setHeader("Access-Control-Max-Age", accessControlMaxAge);
        response.setHeader("Access-Control-Allow-Headers", accessControlAllowHeaders);
        response.setHeader("Access-Control-Expose-Headers", accessControlExposeHeaders);

        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}

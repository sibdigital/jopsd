package ru.sibdigital.jopsd.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.sibdigital.jopsd.config.user.details.CustomUserDetails;
import ru.sibdigital.jopsd.config.user.details.CustomUserDetailsService;
import ru.sibdigital.jopsd.model.opsd.Session;
import ru.sibdigital.jopsd.model.opsd.User;
import ru.sibdigital.jopsd.repository.opsd.SessionRepository;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
@Slf4j
public class SessionFilter implements Filter {

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        Cookie[] allCookies = req.getCookies();
        if (allCookies != null) {
            Cookie sessionCookie =
                    Arrays.stream(allCookies).filter(x -> x.getName().equals("_open_project_session"))
                            .findFirst().orElse(null);

            if ((sessionCookie != null) && (sessionCookie.getValue() != null)) {
                String sessionId = sessionCookie.getValue();
                Session session = sessionRepository.findBySessionId(sessionId);
                if (session != null && session.getUser() != null) {
                    User user = session.getUser();
                    CustomUserDetails customUserDetails = customUserDetailsService.loadUserByUsername(user.getLogin());
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                } else {
                    SecurityContextHolder.clearContext();
                }
            } else {
                SecurityContextHolder.clearContext();
            }
        } else {
            SecurityContextHolder.clearContext();
        }
        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }


}

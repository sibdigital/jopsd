package ru.sibdigital.jopsd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity()
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new UserDetailsServiceImpl();
//    }
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/libs/**", "/css/**").permitAll();
////                .antMatchers("/js/**").permitAll()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/admin").hasRole("ADMIN")
//                .antMatchers("/favicon.ico","/logo.png").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/perform_login")
////                .defaultSuccessUrl("/", true)
//                .failureUrl("/login?error=true")
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .deleteCookies("JSESSIONID");
    }
}
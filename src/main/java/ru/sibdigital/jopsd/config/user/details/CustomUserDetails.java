package ru.sibdigital.jopsd.config.user.details;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.sibdigital.jopsd.model.opsd.User;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private String login;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public static CustomUserDetails fromUserEntityToCustomUserDetails(User user) {
        CustomUserDetails c = new CustomUserDetails();
        c.login = user.getLogin();
        if (user.getAdmin()) {
            c.grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return c;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}

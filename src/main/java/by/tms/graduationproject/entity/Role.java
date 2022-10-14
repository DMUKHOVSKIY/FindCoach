package by.tms.graduationproject.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    COACH, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}

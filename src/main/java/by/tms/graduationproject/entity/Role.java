package by.tms.graduationproject.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    COACH, USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}

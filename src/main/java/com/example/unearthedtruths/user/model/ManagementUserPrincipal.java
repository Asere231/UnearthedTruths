package com.example.unearthedtruths.user.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class ManagementUserPrincipal implements UserDetails {

    ManagementUser managementUser;

    public ManagementUserPrincipal(ManagementUser managementUser) {
        this.managementUser = managementUser;
    }

    /**
     * Returns the role as a granted authority.
     * Spring expects roles to be prefixed with "ROLE_".
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = managementUser.getRole();
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return managementUser.getPassword();
    }

    @Override
    public String getUsername() {
        return managementUser.getUsername();
    }
}

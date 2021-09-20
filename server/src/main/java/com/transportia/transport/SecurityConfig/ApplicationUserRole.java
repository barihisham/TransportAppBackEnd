package com.transportia.transport.SecurityConfig;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.transportia.transport.SecurityConfig.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    DRIVER(Sets.newHashSet(ORDER_READ)),
    TRAFFIC_MANAGER(Sets.newHashSet(ORDER_READ, ORDER_WRITE)),
    ADMIN(Sets.newHashSet(USER_READ, USER_WRITE, ORDER_READ, ORDER_WRITE)),
    CUSTOMER(Sets.newHashSet(ORDER_READ, ORDER_WRITE));


    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}

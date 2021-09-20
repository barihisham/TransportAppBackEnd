package com.transportia.transport.SecurityConfig;

public enum  ApplicationUserPermission {

    ORDER_READ("order:read"),
    ORDER_WRITE("order:write"),
    USER_READ("user:read"),
    USER_WRITE("user:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

}

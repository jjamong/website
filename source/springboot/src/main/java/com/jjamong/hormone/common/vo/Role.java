package com.jjamong.hormone.common.vo;

public enum Role {
    ROLE_USER("ROLE_USER"),
    ROLE_ANONYMOUS("ROLE_ANONYMOUS"),
    ROLE_NURSE("ROLE_NURSE"),
    ROLE_TOP_ADMIN("ROLE_TOP_ADMIN"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_DORMANT("ROLE_DORMANT");

    String role;

    Role(String role) {
        this.role = role;
    }

    public String value() {
        return role;
    }

    public String[] split(String string) {
        return null;
    }
}

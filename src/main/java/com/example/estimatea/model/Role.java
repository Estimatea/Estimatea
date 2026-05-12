package com.example.estimatea.model;

public class Role {

    private int roleId;
    private String roleType;
    private int roleRate;

    public Role(String roleType, int roleRate) {
        this.roleType = roleType;
        this.roleRate = roleRate;
    }

    public Role() {

    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public int getRoleRate() {
        return roleRate;
    }

    public void setRoleRate(int roleRate) {
        this.roleRate = roleRate;
    }
}

package com.example.estimatea.model;

public class Employee {

    private int employeeId;
    private String employeeName;
    private String employeeUsername;
    private String employeePassword;
    private int roleId; // Foreign key from role table (role_id)

    public Employee(int employeeId, String employeeName, String employeeUsername, String employeePassword, int roleId) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeUsername = employeeUsername;
        this.employeePassword = employeePassword;
        this.roleId = roleId;
    }


    public Employee(String employeeName, String employeeUsername, String employeePassword, int roleId) {
        this.employeeName = employeeName;
        this.employeeUsername = employeeUsername;
        this.employeePassword = employeePassword;
        this.roleId = roleId;
    }

    public Employee() {

    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeUsername() {
        return employeeUsername;
    }

    public void setEmployeeUsername(String employeeUsername) {
        this.employeeUsername = employeeUsername;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}

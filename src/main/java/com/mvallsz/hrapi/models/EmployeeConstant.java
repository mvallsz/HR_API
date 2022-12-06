package com.mvallsz.hrapi.models;


public enum EmployeeConstant {

    MANAGER(10,"Manager", ""),
    DEVELOPER_JR(25, "Developer", "Junior"),
    DEVELOPER_SR(35,"Developer", "Senior"),
    QA_TESTER(14,"QATester", "")
    ;

    private final double employeeCost;
    private final String employeeType;
    private final String employeeLevel;

    private EmployeeConstant(double cost, String type, String level) {
        this.employeeCost = cost;
        this.employeeType = type;
        this.employeeLevel = level;
    }

    public double getEmployeeCost() {
        return employeeCost;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public String getEmployeeLevel() {
        return employeeLevel;
    }
}

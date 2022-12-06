package com.mvallsz.hrapi.models;

import com.mvallsz.hrapi.exceptions.HRException;
import lombok.Data;

@Data
public class Employee {
    private String id;
    private String type;
    private String level;
    private double cost;

    public static Integer managerCount = 0;
    public static Integer qaCount = 0;
    public static Integer devJrCount = 0;
    public static Integer devSrCount = 0;

    public Employee(String id, String type, String level) throws HRException {
        this.id = id;
        this.type = type;
        this.level = level;

        switch (type) {
            case "Manager":
                this.cost = EmployeeConstant.MANAGER.getEmployeeCost();
                managerCount++;
                break;
            case "QATester":
                qaCount++;
                this.cost = EmployeeConstant.QA_TESTER.getEmployeeCost();
                break;
            case "Developer":
                if(level.equalsIgnoreCase(EmployeeConstant.DEVELOPER_JR.getEmployeeLevel())) {
                    devJrCount++;
                    this.cost = EmployeeConstant.DEVELOPER_JR.getEmployeeCost();
                } else {
                    devSrCount++;
                    this.cost = EmployeeConstant.DEVELOPER_SR.getEmployeeCost();
                }
                break;
            default:
                throw new HRException("The Department will contain only Managers, Developers, and QA Testers.");
        }
    }

}

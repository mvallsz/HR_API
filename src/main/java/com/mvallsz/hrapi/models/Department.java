package com.mvallsz.hrapi.models;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class Department {
    private String id;
    private List<Staff> listOfStaff;
    private double cost;

    public HashMap<String, Integer> getEmployeeTypesCount(){
        HashMap<String, Integer> result = new HashMap<>();

        result.put(EmployeeConstant.MANAGER.toString(), Employee.managerCount);
        result.put(EmployeeConstant.DEVELOPER_JR.toString(), Employee.devJrCount);
        result.put(EmployeeConstant.DEVELOPER_SR.toString(), Employee.devSrCount);
        result.put(EmployeeConstant.QA_TESTER.toString(), Employee.qaCount);

        return result;
    }

    public double getCost() {

        double managerCost = Employee.managerCount * EmployeeConstant.MANAGER.getEmployeeCost();
        double qaCost = Employee.qaCount * EmployeeConstant.QA_TESTER.getEmployeeCost();
        double devSrCost = Employee.devSrCount * EmployeeConstant.DEVELOPER_SR.getEmployeeCost();
        double devJrCost = Employee.devJrCount * EmployeeConstant.DEVELOPER_JR.getEmployeeCost();

        return managerCost + qaCost + devJrCost + devSrCost;
    }
}

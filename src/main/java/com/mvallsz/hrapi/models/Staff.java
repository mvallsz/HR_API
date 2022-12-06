package com.mvallsz.hrapi.models;

import com.mvallsz.hrapi.exceptions.HRException;
import lombok.Data;
import java.util.List;

@Data
public class Staff {
    private String id;
    private Employee staffManager;
    private List<Employee> staffList;
    private boolean isEfficient;
    private Integer countManager = 0, countDevSr = 0, countDevJr = 0, countQA = 0;

    public Staff(String id, Employee staffManager, List<Employee> staffList) throws HRException {
        if(!staffManager.getType().equalsIgnoreCase("manager")) {
            throw new HRException("The Managing Employee for this Staff should be a Manager");
        } else {
            for (Employee employee: staffList){
                switch (employee.getType()) {
                    case "Manager":
                        countManager++;
                        break;
                    case "QATester":
                        countQA++;
                        break;
                    case "Developer":
                        if(employee.getLevel().equalsIgnoreCase(EmployeeConstant.DEVELOPER_JR.getEmployeeLevel()))
                            countDevJr++;
                        else
                            countDevSr++;
                        break;
                    default:
                        throw new HRException("The Department will contain only Managers, Developers, and QA Testers.");
                }
            }

            if((countManager > 1) || (countQA > 0 || (countDevJr+countDevSr) > 0))
                this.isEfficient = true;
            else
                this.isEfficient = false;

            this.staffManager = staffManager;
            this.staffList = staffList;
            this.id = id;
        }
    }

}

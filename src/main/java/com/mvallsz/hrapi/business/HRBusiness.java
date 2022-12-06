package com.mvallsz.hrapi.business;

import com.mvallsz.hrapi.exceptions.HRException;
import com.mvallsz.hrapi.models.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HRBusiness {

    public Department setDummy(String idDep) throws HRException{

        Department depTI = new Department();
        Employee manager1 = new Employee("A", EmployeeConstant.MANAGER.getEmployeeType(), "");
        Employee manager2 = new Employee("B", EmployeeConstant.MANAGER.getEmployeeType(), "");
        Employee manager3 = new Employee("C", EmployeeConstant.MANAGER.getEmployeeType(), "");
        Employee manager4 = new Employee("D", EmployeeConstant.MANAGER.getEmployeeType(), "");
        Employee manager5 = new Employee("E", EmployeeConstant.MANAGER.getEmployeeType(), "");
        Employee manager6 = new Employee("F", EmployeeConstant.MANAGER.getEmployeeType(), "");

        Employee qa1 = new Employee("1", EmployeeConstant.QA_TESTER.getEmployeeType(), "");
        Employee qa2 = new Employee("2", EmployeeConstant.QA_TESTER.getEmployeeType(), "");
        Employee qa3 = new Employee("3", EmployeeConstant.QA_TESTER.getEmployeeType(), "");

        Employee devSr1 = new Employee("1", EmployeeConstant.DEVELOPER_SR.getEmployeeType(), EmployeeConstant.DEVELOPER_SR.getEmployeeLevel());
        Employee devSr2 = new Employee("2", EmployeeConstant.DEVELOPER_SR.getEmployeeType(), EmployeeConstant.DEVELOPER_SR.getEmployeeLevel());

        Employee devJr1 = new Employee("1", EmployeeConstant.DEVELOPER_JR.getEmployeeType(), EmployeeConstant.DEVELOPER_JR.getEmployeeLevel());
        Employee devJr2 = new Employee("2", EmployeeConstant.DEVELOPER_JR.getEmployeeType(), EmployeeConstant.DEVELOPER_JR.getEmployeeLevel());

        List<Employee> listStaff1 = new ArrayList<>();
        listStaff1.add(devJr1);
        listStaff1.add(qa1);
        listStaff1.add(manager2);

        List<Employee> listStaff2 = new ArrayList<>();
        listStaff2.add(devSr1);

        List<Employee> listStaff3 = new ArrayList<>();
        listStaff3.add(manager4);
        listStaff3.add(manager5);

        List<Employee> listStaff4 = new ArrayList<>();
        listStaff4.add(devSr2);
        listStaff4.add(devJr2);
        listStaff4.add(qa2);

        List<Employee> listStaff5 = new ArrayList<>();
        listStaff5.add(manager6);

        List<Employee> listStaff6 = new ArrayList<>();
        listStaff6.add(qa3);

        Staff staff1 = new Staff("A", manager1, listStaff1);
        Staff staff2 = new Staff("B", manager2,listStaff2);
        Staff staff3 = new Staff("C", manager3,listStaff3);
        Staff staff4 = new Staff("D", manager4,listStaff4);
        Staff staff5 = new Staff("E", manager5,listStaff5);
        Staff staff6 = new Staff("F", manager6,listStaff6);

        List<Staff> staffList = new ArrayList<>();
        staffList.add(staff1);
        staffList.add(staff2);
        staffList.add(staff3);
        staffList.add(staff4);
        staffList.add(staff5);
        staffList.add(staff6);

        depTI.setId(idDep);
        depTI.setListOfStaff(staffList);

        return depTI;
    }

    public Employee getEmployee(Department depto, String type, String level, String id) {
        Employee employee = null;
        for (Staff staff: depto.getListOfStaff()) {
            for (Employee employee_: staff.getStaffList()) {
                if(employee_.getType().equalsIgnoreCase(EmployeeConstant.DEVELOPER_JR.getEmployeeType())){
                    if (employee_.getId().equalsIgnoreCase(id) && employee_.getType().equalsIgnoreCase(type) && employee_.getLevel().equalsIgnoreCase(level)) {
                        employee = employee_;
                    }
                }else {
                    if (employee_.getId().equalsIgnoreCase(id) && employee_.getType().equalsIgnoreCase(type)) {
                        employee = employee_;
                    }
                }

            }
            if (staff.getStaffManager().getId().equalsIgnoreCase(id)) {
                employee = staff.getStaffManager();
            }
        }
        if (employee.getType().equalsIgnoreCase(EmployeeConstant.MANAGER.getEmployeeType())){
            employee.setCost(getStaffCost(depto, getStaffByManager(depto, employee.getId())));
        }

        return employee;
    }

    public Staff getStaffById(Department depto, String id) {
        Staff staff = null;
        for (Staff staff_: depto.getListOfStaff()){
            if(staff_.getId().equalsIgnoreCase(id)){
                staff = staff_;
            }
        }

        return staff;
    }

    public double getStaffCost (Department depto, Staff staff) {
        double cost = 0d;

        for (Employee employee : staff.getStaffList()) {
            if (employee.getType().equalsIgnoreCase(EmployeeConstant.MANAGER.getEmployeeType())) {
                for (Staff staff_ : depto.getListOfStaff()) {
                    if (staff_.getStaffManager().getId().equalsIgnoreCase(employee.getId())) {
                        cost = cost + getStaffCost(depto, staff_);
                    }
                }
            }else {
                cost = cost + employee.getCost();
            }
        }

        cost = cost + staff.getStaffManager().getCost();

        return cost;
    }

    public boolean isUnderstaffed (Staff staff){
        boolean isUnderstaffed = true;
        if ((staff.getCountDevSr() > 0 && staff.getCountQA() > 0) || staff.getCountManager() > 1) {
            isUnderstaffed = false;
        }

        return isUnderstaffed;
    }

    public Staff getStaffByManager(Department dpto, String id) {
        Staff staff = null;
        for (Staff staff_: dpto.getListOfStaff()) {
            if (staff_.getStaffManager().getId().equalsIgnoreCase(id)) {
                staff = staff_;
            }
        }
        return staff;
    }

}

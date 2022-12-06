package com.mvallsz.hrapi.controllers;

import com.mvallsz.hrapi.business.HRBusiness;
import com.mvallsz.hrapi.exceptions.HRException;
import com.mvallsz.hrapi.models.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class HRController {

    private HRBusiness hrBusiness = new HRBusiness();
    public static Department depTI = new Department();

    // setting dummy values
    @PostMapping("/{dept}")
    public String DeptGen(@PathVariable String dept ) {
        try {
            depTI = hrBusiness.setDummy(dept);
        } catch (HRException e) {
            e.printStackTrace();
        }
        return String.valueOf(depTI);
    }

    // get the entire department
    @GetMapping("/{dept}")
    public Department getDepTI(){
        return depTI;
    }

    // get enployees by type [Manager, QATester, Developer] & employeeId
    @GetMapping("/{dept}/{type}/{level}/{id}")
    public Employee getEmployee(@PathVariable String dept,  @PathVariable String type, @PathVariable String level, @PathVariable String id) {
       return hrBusiness.getEmployee(depTI, type, level, id);
    }

    // get a Staff Object from ID
    @GetMapping("/{dept}/staff/{id}")
    public Staff getStaffById(@PathVariable String dept, @PathVariable String id) {
        return hrBusiness.getStaffById(depTI,id);
    }

    // get if a Staff is Understaffed
    @GetMapping("/{dept}/staff/isUnderstaffed/{id}")
    public boolean isUnderstaffed (@PathVariable String dept, @PathVariable String id){
        Staff staff = hrBusiness.getStaffById(depTI,id);
        return hrBusiness.isUnderstaffed(staff);
    }

    // get Staff's Cost from Staff ID same ID as the staffManager
    @GetMapping("/{dept}/cost/staff/{id}")
    public double getStaffCost (@PathVariable String dept, @PathVariable String id){
        Staff staff = hrBusiness.getStaffById(depTI,id);
        return hrBusiness.getStaffCost(depTI, staff);
    }

    // get Staff from staffManager ID
    @GetMapping("/{dept}/staff/manager/{id}")
    public Staff getStaffByManager(@PathVariable String dept, @PathVariable String id) {
        return hrBusiness.getStaffByManager(depTI,id);
    }

}

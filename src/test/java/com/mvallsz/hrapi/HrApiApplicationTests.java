package com.mvallsz.hrapi;

import com.mvallsz.hrapi.controllers.HRController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class HrApiApplicationTests {

    HRController hrController = new HRController();


    @Test
    @DisplayName("Get Manager Cost")
    public void test1() {
        hrController.DeptGen("1");
        assertEquals(hrController.getStaffCost("1", "A"), 94);
        assertEquals(hrController.getStaffCost("1", "B"), 45);
        assertEquals(hrController.getStaffCost("1", "C"), 128);
        assertEquals(hrController.getStaffCost("1", "D"), 84);
        assertEquals(hrController.getStaffCost("1", "E"), 34);
        assertEquals(hrController.getStaffCost("1", "F"), 24);
    }

    @Test
    @DisplayName("Get Department Cost")
    public void test2() {
        assertEquals(hrController.getDepTI().getCost(), 222);
    }

    @Test
    @DisplayName("Get Staff Efficiency")
    public void test3() {
        assertEquals(hrController.getStaffByManager("1", "A").isEfficient(), true);
        assertEquals(hrController.getStaffByManager("1", "B").isEfficient(), true);
        assertEquals(hrController.getStaffByManager("1", "C").isEfficient(), true);
        assertEquals(hrController.getStaffByManager("1", "D").isEfficient(), true);
        assertEquals(hrController.getStaffByManager("1", "E").isEfficient(), false);
        assertEquals(hrController.getStaffByManager("1", "F").isEfficient(), true);
    }

    @Test
    @DisplayName("is Understaffed?")
    public void test4() {
        assertEquals(hrController.isUnderstaffed("1", "A"), true);
        assertEquals(hrController.isUnderstaffed("1", "B"), true);
        assertEquals(hrController.isUnderstaffed("1", "C"), false);
        assertEquals(hrController.isUnderstaffed("1", "D"), false);
        assertEquals(hrController.isUnderstaffed("1", "E"), true);
        assertEquals(hrController.isUnderstaffed("1", "F"), true);
    }
}

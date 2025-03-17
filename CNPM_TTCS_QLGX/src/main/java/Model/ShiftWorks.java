/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalTime;

/**
 *
 * @author HP
 */
public class ShiftWorks {
    int shift_work_id;
    int shift_type_id;
    int building_id;
    int staff_id;
    int task_id;
    LocalTime shift_date;

    public ShiftWorks() {
    }

    public ShiftWorks(int shift_type_id, int building_id, int staff_id, int task_id, LocalTime shift_date) {
        this.shift_type_id = shift_type_id;
        this.building_id = building_id;
        this.staff_id = staff_id;
        this.task_id = task_id;
        this.shift_date = shift_date;
    }

    public ShiftWorks(int shift_work_id, int shift_type_id, int building_id, int staff_id, int task_id, LocalTime shift_date) {
        this.shift_work_id = shift_work_id;
        this.shift_type_id = shift_type_id;
        this.building_id = building_id;
        this.staff_id = staff_id;
        this.task_id = task_id;
        this.shift_date = shift_date;
    }

    
    public int getShift_work_id() {
        return shift_work_id;
    }

    public int getShift_type_id() {
        return shift_type_id;
    }

    public LocalTime getShift_date() {
        return shift_date;
    }

    public int getBuilding_id() {
        return building_id;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setBuilding_id(int building_id) {
        this.building_id = building_id;
    }

    public void setShift_date(LocalTime shift_date) {
        this.shift_date = shift_date;
    }

    public void setShift_type_id(int shift_type_id) {
        this.shift_type_id = shift_type_id;
    }

    public void setShift_work_id(int shift_work_id) {
        this.shift_work_id = shift_work_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }


}

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
public class ShiftTypes {
    private int shift_type_id ;
    private String  shift_type_name;
    private LocalTime start_time;
    private LocalTime end_time;
    
    public ShiftTypes(){
        
    }
    
    public ShiftTypes(String shift_type_name, LocalTime start_time, LocalTime end_time) {
        this.shift_type_name = shift_type_name;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public ShiftTypes(int shift_type_id, String shift_type_name, LocalTime start_time, LocalTime end_time) {
        this.shift_type_id = shift_type_id;
        this.shift_type_name = shift_type_name;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    
    public int getShift_type_id() {
        return shift_type_id;
    }

    public String getShift_type_name() {
        return shift_type_name;
    }

    public LocalTime getEnd_time() {
        return end_time;
    }

    public LocalTime getStart_time() {
        return start_time;
    }

    public void setShift_type_id(int shift_type_id) {
        this.shift_type_id = shift_type_id;
    }

    public void setShift_type_name(String shift_type_name) {
        this.shift_type_name = shift_type_name;
    }

    public void setEnd_time(LocalTime end_time) {
        this.end_time = end_time;
    }

    public void setStart_time(LocalTime start_time) {
        this.start_time = start_time;
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;

/**
 *
 * @author manhh
 */
public class SessionFee {
    private int time_frame_id;
    private int vehicle_type_id;
    private LocalDate decision_date;
    private int session_fee_id;
    private int amount;
    private boolean is_active;

    public SessionFee() {
    }

    public SessionFee(int time_frame_id, int vehicle_type_id, LocalDate decision_date, int session_fee_id, int amount, boolean is_active) {
        this.time_frame_id = time_frame_id;
        this.vehicle_type_id = vehicle_type_id;
        this.decision_date = decision_date;
        this.session_fee_id = session_fee_id;
        this.amount = amount;
        this.is_active = is_active;
    }
    
    public int getTime_frame_id() {
        return time_frame_id;
    }

    public void setTime_frame_id(int time_frame_id) {
        this.time_frame_id = time_frame_id;
    }

    public int getVehicle_type_id() {
        return vehicle_type_id;
    }

    public void setVehicle_type_id(int vehicle_type_id) {
        this.vehicle_type_id = vehicle_type_id;
    }

    public LocalDate getDecision_date() {
        return decision_date;
    }

    public void setDecision_date(LocalDate decision_date) {
        this.decision_date = decision_date;
    }

    public int getSession_fee_id() {
        return session_fee_id;
    }

    public void setSession_fee_id(int session_fee_id) {
        this.session_fee_id = session_fee_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }
    
    
}

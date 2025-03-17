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
public class ServiceFee {
    private int service_fee_id;
    private LocalDate decision_date;
    private int vehicle_type_id;
    private int amount;
    private boolean is_active;

    public ServiceFee() {
    }

    public ServiceFee(int service_fee_id, LocalDate decision_date, int vehicle_type_id, int amount, boolean is_active) {
        this.service_fee_id = service_fee_id;
        this.decision_date = decision_date;
        this.vehicle_type_id = vehicle_type_id;
        this.amount = amount;
        this.is_active = is_active;
    }

    public int getService_fee_id() {
        return service_fee_id;
    }

    public void setService_fee_id(int service_fee_id) {
        this.service_fee_id = service_fee_id;
    }

    public LocalDate getDecision_date() {
        return decision_date;
    }

    public void setDecision_date(LocalDate decision_date) {
        this.decision_date = decision_date;
    }

    public int getVehicle_type_id() {
        return vehicle_type_id;
    }

    public void setVehicle_type_id(int vehicle_type_id) {
        this.vehicle_type_id = vehicle_type_id;
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

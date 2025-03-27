/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author manhh
 */
public class TypeService {
    private int type_service_id;
    private int service_fee_id;
    private int month_unit;
    private String service_name;
    private LocalDate decision_date;
    private int payment_coefficient;
    private boolean is_active;

    public LocalDate getDecision_date() {
        return decision_date;
    }

    public void setDecision_date(LocalDate decision_date) {
        this.decision_date = decision_date;
    }

    public TypeService(int type_service_id, int service_fee_id, int month_unit, String service_name, LocalDate decision_date,int payment_coefficient, boolean is_active) {
        this.type_service_id = type_service_id;
        this.service_fee_id = service_fee_id;
        this.month_unit = month_unit;
        this.service_name = service_name;
        this.payment_coefficient = payment_coefficient;
        this.is_active = is_active;
        this.decision_date = decision_date;
    }

    public TypeService() {
    }
    
    public int getType_service_id() {
        return type_service_id;
    }

    public void setType_service_id(int type_service_id) {
        this.type_service_id = type_service_id;
    }

    public int getService_fee_id() {
        return service_fee_id;
    }

    public void setService_fee_id(int service_fee_id) {
        this.service_fee_id = service_fee_id;
    }

    public int getMonth_unit() {
        return month_unit;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public void setMonth_unit(int month_unit) {
        this.month_unit = month_unit;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public int getPayment_coefficient() {
        return payment_coefficient;
    }

    public void setPayment_coefficient(int payment_coefficient) {
        this.payment_coefficient = payment_coefficient;
    }
    
    
}

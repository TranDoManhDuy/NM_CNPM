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
public class Payment {
    private int payment_id;
    private int registration_id;
    private LocalDate extension_time;
    private boolean payment_state;
    private int service_type_id;

    public Payment() {
    }

    public Payment(int payment_id, int registration_id, LocalDate extension_time, boolean payment_state, int service_type_id) {
        this.payment_id = payment_id;
        this.registration_id = registration_id;
        this.extension_time = extension_time;
        this.payment_state = payment_state;
        this.service_type_id = service_type_id;
    }
    
    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public int getRegistration_id() {
        return registration_id;
    }

    public void setRegistration_id(int registration_id) {
        this.registration_id = registration_id;
    }

    public LocalDate getExtension_time() {
        return extension_time;
    }

    public void setExtension_time(LocalDate extension_time) {
        this.extension_time = extension_time;
    }

    public boolean isPayment_state() {
        return payment_state;
    }

    public void setPayment_state(boolean payment_state) {
        this.payment_state = payment_state;
    }

    public int getService_type_id() {
        return service_type_id;
    }

    public void setService_type_id(int service_type_id) {
        this.service_type_id = service_type_id;
    }
}

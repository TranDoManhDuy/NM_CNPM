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
public class Regisatration {
    private int customer_id;
    private int regisatration_id;
    private LocalDate registration_date;
    private int vehicle_id;
    private char state;

    public Regisatration(int customer_id, int regisatration_id, LocalDate regisatration_date, int vehicle_id, char state) {
        this.customer_id = customer_id;
        this.regisatration_id = regisatration_id;
        this.registration_date = regisatration_date;
        this.vehicle_id = vehicle_id;
        this.state = state;
    }

    public Regisatration() {
    }
    
    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getRegistration_id() {
        return regisatration_id;
    }

    public void setRegistration_id(int registration_id) {
        this.regisatration_id = registration_id;
    }

    public LocalDate getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(LocalDate registration_date) {
        this.registration_date = registration_date;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public char getState() {
        return state;
    }

    public void setState(char state) {
        this.state = state;
    }
}

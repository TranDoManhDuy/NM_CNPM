/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Admin
 */
public class Customer {
    private int customer_id; 
    private String full_name;
    private String ssn; 
    private LocalDate date_of_birth;
    private String gender; 
    private String phone_number;
    private String address;
    private int building_id;
    private String nationality;
    private boolean is_active;

    public Customer() {
    }

    public Customer(String full_name, String ssn, LocalDate date_of_birth, String gender, String phone_number, String address, int building_id, String nationality, boolean is_active) {
        this.full_name = full_name;
        this.ssn = ssn;
        this.date_of_birth = date_of_birth;
        this.gender = gender;
        this.phone_number = phone_number;
        this.address = address;
        this.building_id = building_id;
        this.nationality = nationality;
        this.is_active = is_active;
    }

    public Customer(int customer_id, String full_name, String ssn, LocalDate date_of_birth, String gender, String phone_number, String address, int building_id, String nationality, boolean is_active) {
        this.customer_id = customer_id;
        this.full_name = full_name;
        this.ssn = ssn;
        this.date_of_birth = date_of_birth;
        this.gender = gender;
        this.phone_number = phone_number;
        this.address = address;
        this.building_id = building_id;
        this.nationality = nationality;
        this.is_active = is_active;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(int building_id) {
        this.building_id = building_id;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateString = date_of_birth.format(formatter);
        return  String.valueOf(customer_id) + " " 
                + full_name + " " + ssn + " " + dateString + " " + gender + " " 
                + phone_number + " " + address + " " + String.valueOf(building_id) + " " + nationality;
    }
}

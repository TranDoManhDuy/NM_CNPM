/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class Vehicle {
    private int vehicle_id;
    private String identification_code;
    private int vehicle_type_id;
    private String vehicle_name; 
    private String vehicle_color;

    public Vehicle() {
    }

    public Vehicle(String identification_code, int vehicle_type_id, String vehicle_name, String vehicle_color) {
        this.identification_code = identification_code;
        this.vehicle_type_id = vehicle_type_id;
        this.vehicle_name = vehicle_name;
        this.vehicle_color = vehicle_color;
    }

    public Vehicle(int vehicle_id, String identification_code, int vehicle_type_id, String vehicle_name, String vehicle_color) {
        this.vehicle_id = vehicle_id;
        this.identification_code = identification_code;
        this.vehicle_type_id = vehicle_type_id;
        this.vehicle_name = vehicle_name;
        this.vehicle_color = vehicle_color;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getIdentification_code() {
        return identification_code;
    }

    public void setIdentification_code(String identification_code) {
        this.identification_code = identification_code;
    }

    public int getVehicle_type_id() {
        return vehicle_type_id;
    }

    public void setVehicle_type_id(int vehicle_type_id) {
        this.vehicle_type_id = vehicle_type_id;
    }

    public String getVehicle_name() {
        return vehicle_name;
    }

    public void setVehicle_name(String vehicle_name) {
        this.vehicle_name = vehicle_name;
    }

    public String getVehicle_color() {
        return vehicle_color;
    }

    public void setVehicle_color(String vehicle_color) {
        this.vehicle_color = vehicle_color;
    }
    
}

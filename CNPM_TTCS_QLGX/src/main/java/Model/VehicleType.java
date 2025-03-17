/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author manhh
 */
public class VehicleType {
    private int vehicle_type_id;
    private String vehicle_type_name;

    public VehicleType() {
    }

    public VehicleType(int vehicle_type_id, String vehicle_type_name) {
        this.vehicle_type_id = vehicle_type_id;
        this.vehicle_type_name = vehicle_type_name;
    }

    public int getVehicle_type_id() {
        return vehicle_type_id;
    }

    public void setVehicle_type_id(int vehicle_type_id) {
        this.vehicle_type_id = vehicle_type_id;
    }

    public String getVehicle_type_name() {
        return vehicle_type_name;
    }

    public void setVehicle_type_name(String vehicle_type_name) {
        this.vehicle_type_name = vehicle_type_name;
    }
}

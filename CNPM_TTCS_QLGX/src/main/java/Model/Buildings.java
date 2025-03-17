/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author HP
 */
public class Buildings {
    int building_id;
    String building_name;
    String address;

    public Buildings() {
    }

    public Buildings( String building_name, String address) {
        this.building_name = building_name;
        this.address = address;
    }

    public Buildings(int building_id, String building_name, String address) {
        this.building_id = building_id;
        this.building_name = building_name;
        this.address = address;
    }
    
    public String getAddress() {
        return address;
    }

    public int getBuilding_id() {
        return building_id;
    }

    public String getBuilding_name() {
        return building_name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBuilding_id(int building_id) {
        this.building_id = building_id;
    }

    public void setBuilding_name(String building_name) {
        this.building_name = building_name;
    }
    
}

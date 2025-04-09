/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Global;

import DAO.CustomerDAO;
import DAO.RegisatrationDAO;
import DAO.ServiceFeeDAO;
import DAO.VehicleDAO;
import DatabaseHelper.OpenConnection;
import Model.Customer;
import Model.Regisatration;
import Model.Vehicle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;

/**
 *
 * @author manhh
 */
public class DataGlobal {
    private JFrame viewmain;
    private ArrayList<Regisatration> arrRegistration = new ArrayList<>();
    private ArrayList<Customer> arrCustomer = new ArrayList<>();
    private ArrayList<Vehicle> arrVehicle = new ArrayList<>();
    private ArrayList<ArrayList<String>> arrServiceFee_render = new ArrayList<>();
    
    public DataGlobal() {}
    
    public void updateArrServiceFee_render() {
        this.arrServiceFee_render = ServiceFeeDAO.getInstance().getArrServiceFee_render();
    }
    
    public ArrayList<ArrayList<String>> getArrServiceFee_render() {
        return this.arrServiceFee_render;
    }
    
    public ArrayList<Regisatration> getArrayRegistration() {
        return arrRegistration;
    }
    public void insertArrRegistration_local(Regisatration registration) {
        this.arrRegistration.add(registration);
    }
    public void deleteArrRegistration_local(int registrationId) {
        for (int i = 0; i <= this.arrRegistration.size() - 1; ++i) {
            if (this.arrRegistration.get(i).getRegistration_id() == registrationId) {
                this.arrRegistration.remove(i);
                break;
            }
        }
    }
    public void updateArrRegistration_local(Regisatration registration) {
        for (int i = 0; i <= this.arrRegistration.size() - 1; ++i) {
            if (this.arrRegistration.get(i).getRegistration_id() == registration.getRegistration_id()) {
                this.arrRegistration.remove(i);
                break;
            }
        }
        this.arrRegistration.add(registration);
    }
    public void updateArrRegistration() {
        try {
            arrRegistration = RegisatrationDAO.getInstance().getList();
        } catch (Exception e) {
            System.out.println("LOI KET NOI");
        }
    }
    public ArrayList<Customer> getArrayCustomer() {
        return arrCustomer;
    }
    public void updateArrCustomer() {
        try {
            arrCustomer = CustomerDAO.getInstance().getList();
        } catch (Exception e) {
            System.out.println("LOI KET NOI");
        }
    }
    public ArrayList<Vehicle> getArrayVehicle() {
        return arrVehicle;
    }
    public void updateArrVehicle() {
        try {
            arrVehicle = VehicleDAO.getInstance().getList();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("LOI KET NOI");
        }
    }
    public void updateAllData() {
        updateArrRegistration();
        updateArrCustomer();
        updateArrVehicle();
        updateArrServiceFee_render();
    }
}
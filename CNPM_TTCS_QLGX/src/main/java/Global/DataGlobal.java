/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Global;

import DAO.CustomerDAO;
import DAO.RegisatrationDAO;
import DAO.VehicleDAO;
import Model.Customer;
import Model.Regisatration;
import Model.Vehicle;
import java.util.ArrayList;
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
    
    public DataGlobal() {}
    
    public ArrayList<Regisatration> getArrayRegistration() {
        return arrRegistration;
    }
    public void updateArrRegistration() {
        try {
            arrRegistration = RegisatrationDAO.getInstance().getList();
        } catch (Exception e) {
            e.printStackTrace();
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
            e.printStackTrace();
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
    }
}
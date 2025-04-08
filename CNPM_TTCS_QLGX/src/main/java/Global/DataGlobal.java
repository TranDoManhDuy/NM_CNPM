/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Global;

import DAO.BuildingsDAO;
import DAO.CustomerDAO;
import DAO.RegisatrationDAO;
import DAO.ShiftTypesDAO;
import DAO.ShiftWorksDAO;
import DAO.TasksDAO;
import DAO.VehicleDAO;
import Model.Buildings;
import Model.Customer;
import Model.Regisatration;
import Model.ShiftTypes;
import Model.ShiftWorks;
import Model.Tasks;
import Model.Vehicle;
import java.util.ArrayList;
import java.util.List;
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
    private List<ShiftTypes> arrShiftTypes = new ArrayList<>();
    private List<ShiftWorks> arrShiftWorks = new ArrayList<>();
    private List<Tasks> arrTask = new ArrayList<>();
    private List<Buildings> arrBuildings = new ArrayList<>();
    
    public DataGlobal() {
    }
    
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
    
    public List<ShiftTypes> getArrayShiftTypes(){
        return arrShiftTypes;
    }
    public void updateArrShiftTypes(){
        try {
            arrShiftTypes = ShiftTypesDAO.getInstance().getAllShiftTypes();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("LOI KET NOI");
        }
    }
    
    public List<ShiftWorks> getArrayShiftWorks(){
        return arrShiftWorks;
    }
    public void updateArrShiftWorks(){
        try {
            arrShiftWorks = ShiftWorksDAO.getInstance().getAllShiftWorks();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("LOI KET NOI");
        }
    }
    
    public List<Buildings> getArrayBuildings(){
        return arrBuildings;
    }
    public void updateArrBuildings(){
        try {
            arrBuildings = BuildingsDAO.getInstance().getAllBuildings();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("LOI KET NOI");
        }
    }
    public List<Tasks> getArrayTasks(){
        return arrTask;
    }
    public void updateArrtasks(){
        try {
            arrTask = TasksDAO.getInstance().getAllTasks();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("LOI KET NOI");
        }
    }
}
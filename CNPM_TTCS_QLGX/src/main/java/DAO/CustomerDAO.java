/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DatabaseHelper.OpenConnection;
import InterfaceDAO.InterfaceDAO;
import Model.Customer;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 *
 * @author Admin
 */
public class CustomerDAO implements InterfaceDAO<Customer> {
    public static CustomerDAO getInstance() {
        return new CustomerDAO();
    }

    @Override
    public ArrayList<Customer> getList() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        ArrayList<Customer> lstCus = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        try (
                Connection con = OpenConnection.getConnection();
                Statement  st  = con.createStatement();
                ResultSet  rs  = st.executeQuery(sql);) {
            while (rs.next()) {
                int customer_id = rs.getInt("customer_id");
                int building_id = rs.getInt("building_id");
                String full_name = rs.getString("full_name");
                String ssn = rs.getString("ssn");
                LocalDate date_of_birth = rs.getDate("date_of_birth").toLocalDate();
                String gender = rs.getString("gender");
                String phone_number = rs.getString("phone_number");
                String address = rs.getString("address");
                String nationality = rs.getString("nationality");
                boolean is_active = rs.getBoolean("is_active");
                Customer newCus = new Customer(customer_id, full_name, ssn, date_of_birth, gender, phone_number, address, building_id, nationality, is_active);
                lstCus.add(newCus);
            }
        }
        catch (Exception e) { 
            e.printStackTrace();
        }
        return lstCus;
    }

    @Override
    public boolean insert(Customer customer) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        String sql = "INSERT INTO customers (full_name, ssn, date_of_birth, gender, phone_number, address, building_id, nationality, is_active) VALUES (?,?,?,?,?,?,?,?,?)";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setInt(1, customer.getCustomer_id());
            ps.setString(1, customer.getFull_name());
            ps.setString(2, customer.getSsn());
            ps.setDate(3, Date.valueOf(customer.getDate_of_birth()));
            ps.setString(4, customer.getGender());
            ps.setString(5, customer.getPhone_number());
            ps.setString(6, customer.getAddress());
            ps.setInt(7, customer.getBuilding_id());
            ps.setString(8, customer.getNationality());
            ps.setBoolean(9, customer.isIs_active());
            return ps.executeUpdate() > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Customer customer) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        String sql = "UPDATE customers SET full_name = ?, ssn = ?, date_of_birth = ?, gender = ?, phone_number = ?, address = ?, building_id = ?, nationality = ?, is_active = ? WHERE customer_id = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(10, customer.getCustomer_id());
            ps.setString(1, customer.getFull_name());
            ps.setString(2, customer.getSsn());
            ps.setDate(3, Date.valueOf(customer.getDate_of_birth()));
            ps.setString(4, customer.getGender());
            ps.setString(5, customer.getPhone_number());
            ps.setString(6, customer.getAddress());
            ps.setInt(7, customer.getBuilding_id());
            ps.setString(8, customer.getNationality());
            ps.setBoolean(9, customer.isIs_active());
            return ps.executeUpdate() > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        String sql = "DELETE FROM customers WHERE customer_id = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Customer findbyID(int id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        String sql = "SELECT * FROM customers WHERE customer_id = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                int customer_id = rs.getInt("customer_id");
                int building_id = rs.getInt("building_id");
                String full_name = rs.getString("full_name");
                String ssn = rs.getString("ssn");
                LocalDate date_of_birth = rs.getDate("date_of_birth").toLocalDate();
                String gender = rs.getString("gender");
                String phone_number = rs.getString("phone_number");
                String address = rs.getString("address");
                String nationality = rs.getString("nationality");
                Boolean is_active = rs.getBoolean("is_active");
                
                return new Customer(customer_id, full_name, ssn, date_of_birth, gender, phone_number, address, building_id, nationality, is_active);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) {
        LocalDate dob = LocalDate.of(2004, 1, 1);
        Customer Cus = new Customer("Vu Dinh Khoa", "010101010101", dob, "M", "0202020202", "97 Man Thien - TP HCM", 1 , "VietNam", true);
        Customer updateCus = new Customer(2, "Vu Dinh Khoa", "010101010101", dob, "M", "0202020202", "97 Man Thien - TP HCM", 1 , "American", true);
//        Customer Cus2 = new Customer(8, "Vu Dinh Khoa", "030303030303", dob, "M", "0303030303", "97 Man Thien - TP HCM", 1 , "VietNam");
//        Customer upCus  = new Customer(3, "Vu Dinh Khoa", "030303030303", dob, "M", "0202020202", "97 Man Thien - TP HCM", 1 , "VietNam");
        CustomerDAO cusDao = CustomerDAO.getInstance();
        
//        cusDao.insert(Cus);
//        cusDao.update(updateCus);
//        Customer Cus1 = cusDao.findbyID(2);
//        if (Cus1 != null) {
//            System.out.println(Cus1.getFull_name());
//        }
//        ArrayList<Customer> lstCus = cusDao.getList();
//        if (lstCus != null) {
//            for (Customer customer : lstCus) {
//                System.out.println(customer.getFull_name());
//            }
//        }
//        cusDao.delete(2);
    }
}

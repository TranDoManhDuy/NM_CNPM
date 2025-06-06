/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DatabaseHelper.OpenConnection;
import Model.Regisatration;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 * @author manhh
 */
public class RegisatrationDAO {
    public static RegisatrationDAO getInstance() {
        return new RegisatrationDAO();
    }
    
    public ArrayList<ArrayList<String>> getArrRegistrationRender() {
        ArrayList<ArrayList<String>> dataRegistration = new ArrayList<>();
        String sql = "EXEC Registration_render";
        try (
            Connection conn = OpenConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
        ) {
            int index = 0;
            dataRegistration = new ArrayList<>();
            while (rs.next()) {
                int registration_id = rs.getInt("registration_id");
                int customer_id = rs.getInt("customer_id");
                String full_name = rs.getString("full_name");
                LocalDate registration_date = rs.getDate("registration_date").toLocalDate();
                String identification_code = rs.getString("identification_code");
                String state = rs.getString("state");
                String trangthai = "";
                if (state.equals("A")) {
                    trangthai = "San sang gia han";
                }
                else {
                    if (state.equals("B")) {
                        trangthai = "Dang con han";
                    }
                    else {trangthai = "Bi huy";}
                }
                ArrayList<String> registration_data = new ArrayList<>(Arrays.asList(
                        String.valueOf(registration_id), 
                        String.valueOf(customer_id), 
                        full_name,
                        String.valueOf(registration_date),
                        identification_code,
                        trangthai
                        ));
                dataRegistration.add(registration_data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataRegistration;
    }
    
    public ArrayList<Regisatration> getList() {
        ArrayList<Regisatration> listRegistration = new ArrayList<>();
        String sql = "EXEC getlist_registration";
        try (
            Connection conn = OpenConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
        ) {
            while (rs.next()) {
                int customer_id = rs.getInt("customer_id");
                int regisatration_id = rs.getInt("registration_id");
                LocalDate regisatration_date = rs.getDate("registration_date").toLocalDate();
                int vehicle_id = rs.getInt("vehicle_id");
                char state = rs.getString("state").charAt(0);
                Regisatration registration = new Regisatration(customer_id, regisatration_id, regisatration_date, vehicle_id, state);
                listRegistration.add(registration);
            }
        }
        catch (Exception e) { 
            System.out.println("Truy vấn lấy danh sách đăng kí không thành công");
        }
        return listRegistration;
    }
    public String insert(Regisatration regisatration) {
        String sql = "EXEC insert_registration @customer_id = ?, @registration_date = ?, @vehicle_id = ?, @state = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt =  conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, regisatration.getCustomer_id());
            ptmt.setDate(2, Date.valueOf(regisatration.getRegistration_date()));
            ptmt.setInt(3 ,regisatration.getVehicle_id());
            ptmt.setString(4, String.valueOf(regisatration.getState()));
            
            // Thanh cong
            if (ptmt.executeUpdate() > 0) {
                return "Thêm thành công";
            }
        } catch (Exception e) {
            return "Lỗi: " + e.getMessage();
        }
        return "Thêm thành công";
    }
    public String update(Regisatration registration) {
        String sql = "EXEC update_registration @customer_id = ?, @registration_date = ?, @vehicle_id = ?, @state = ?, @registration_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt =  conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, registration.getCustomer_id());
            ptmt.setDate(2, Date.valueOf(registration.getRegistration_date()));
            ptmt.setInt(3, registration.getVehicle_id());
            ptmt.setString(4, String.valueOf(registration.getState()));
            ptmt.setInt(5, registration.getRegistration_id());
            
            if (ptmt.executeUpdate() > 0) {
                return "Cập nhật thành công";
            };
        } catch (Exception e) {
            return "Thông báo: " + e.getMessage();
        }
        return  "Cập nhật thành công";
    }
    public Regisatration findbyID(int id) {
       String sql = "EXEC findbyID_registration @registration_id = ?";
       try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
           ptmt.setInt(1, id);
           ResultSet rs = ptmt.executeQuery();
           
          if (rs.next()) {
               int customer_id = rs.getInt("customer_id");
                int registration_id = rs.getInt("registration_id");
                LocalDate registration_date = rs.getDate("registration_date").toLocalDate();
                int vehicle_id = rs.getInt("vehicle_id");
                char state = rs.getString("state").charAt(0);
                Regisatration registration = new Regisatration(customer_id, registration_id, registration_date, vehicle_id, state);
                
                return registration;
          }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return null;
    }
    public String delete(int id) {
        String sql = "EXEC delete_registration @registration_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, id);
            if (ptmt.executeUpdate()> 0) {
                return "Thông báo: Xóa thành công";
            }
        } catch (Exception e) {
            return "Lỗi: " + e.getMessage();
        }
        return "Thông báo: Xóa thành công";
    }
    public static void main(String[] args) {
        ArrayList<Regisatration> list = RegisatrationDAO.getInstance().getList();
        RegisatrationDAO.getInstance().delete(0);
        list.forEach(x -> {
            System.out.println(x.getRegistration_id());
        });
    }
}
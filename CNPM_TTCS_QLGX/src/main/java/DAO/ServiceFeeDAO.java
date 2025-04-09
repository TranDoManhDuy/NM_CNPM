/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DatabaseHelper.OpenConnection;
import Model.ServiceFee;
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
public class ServiceFeeDAO  {
    public static ServiceFeeDAO getInstance() {
        return new ServiceFeeDAO();
    }
    public ArrayList<ArrayList<String>> getArrServiceFee_render() {
        String sql = "EXEC ServiceFee_render";
        ArrayList<ArrayList<String>> arrServiceFee_render = new ArrayList<>();
        try (
            Connection conn = OpenConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
        ) {
            int index = 0;
            arrServiceFee_render = new ArrayList<>();
            while (rs.next()) {
                int service_fee_id = rs.getInt("service_fee_id");
                int vehicle_type_id = rs.getInt("vehicle_type_id");
                String vehicle_type_name = rs.getString("vehicle_type_name");
                int amount = rs.getInt("amount");
                LocalDate decision_date = rs.getDate("decision_date").toLocalDate();
                boolean state = rs.getBoolean("is_active");
                String trangthai;
                if (state) {
                    trangthai = "Còn hạn";
                }
                else {
                    trangthai = "Hết hạn";
                }
                ArrayList<String> service_data = new ArrayList<>(Arrays.asList(
                        String.valueOf(service_fee_id),
                        String.valueOf(vehicle_type_id),
                        vehicle_type_name,
                        String.valueOf(amount),
                        String.valueOf(decision_date),
                        trangthai
                        ));
                arrServiceFee_render.add(service_data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrServiceFee_render;
    }
    
    public ArrayList<ServiceFee> getList() {
        ArrayList<ServiceFee> list = new ArrayList<>();
        String sql = "EXEC getlist_service_fees";
        
        try (
            Connection conn = OpenConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(sql);
        ) {
            while (result.next()) {
                int id = result.getInt("service_fee_id");
                LocalDate decisionDate = result.getDate("decision_date").toLocalDate();
                int vehicleTypeId = result.getInt("vehicle_type_id");
                int amount = result.getInt("amount");
                boolean isActive = result.getBoolean("is_active");
                
                list.add(new ServiceFee(id, decisionDate, vehicleTypeId, amount, isActive));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public String insert(ServiceFee serviceFee) {
        String sql = "EXEC insert_service_fee @decision_date = ?, @vehicle_type_id = ?, @amount = ?, @is_active = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setDate(1, Date.valueOf(serviceFee.getDecision_date()));
            ptmt.setInt(2, serviceFee.getVehicle_type_id());
            ptmt.setInt(3, serviceFee.getAmount());
            ptmt.setBoolean(4, serviceFee.isIs_active());
            
            if (ptmt.executeUpdate() >= 0) {
                return "Thêm thành công";
            }
        } catch (Exception e) {
            return "Lỗi: " + e.getMessage();
        }
        return "Thêm thành công";
    }
    public String update(ServiceFee serviceFee) {
        String sql = "EXEC update_service_fee @decision_date = ?, @vehicle_type_id = ?, @amount = ?, @is_active = ?, @service_fee_id = ?";
        
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setDate(1, Date.valueOf(serviceFee.getDecision_date()));
            ptmt.setInt(2, serviceFee.getVehicle_type_id());
            ptmt.setInt(3, serviceFee.getAmount());
            ptmt.setBoolean(4, serviceFee.isIs_active());
            ptmt.setInt(5, serviceFee.getService_fee_id());
            
            if (ptmt.executeUpdate() >= 0) {
                return "Cập nhật thành công";
            }
        } catch (Exception e) {
            return "Lỗi: " + e.getMessage();
        }
        return "Cập nhật thành công";
    }
    public ServiceFee findbyID(int id) {
        String sql = "EXEC findbyID_service_fee @service_fee_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, id);
            ResultSet result = ptmt.executeQuery();
            
            if (result.next()) {
                int service_fee_id = result.getInt("service_fee_id");
                LocalDate decisionDate = result.getDate("decision_date").toLocalDate();
                int vehicleTypeId = result.getInt("vehicle_type_id");
                int amount = result.getInt("amount");
                boolean isActive = result.getBoolean("is_active");
                
                return new ServiceFee(id, decisionDate, vehicleTypeId, amount, isActive);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String delete(int id) {
        String sql = " EXEC delete_service_fee @service_fee_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, id);
            if (ptmt.executeUpdate() >= 0) {
                return "Xóa thành công";
            }
        } catch (Exception e) {
            return "Lỗi: " + e.getMessage();
        }
        return "Xóa thành công";
    }
    public static void main(String[] args) {
        ArrayList<ServiceFee> list = ServiceFeeDAO.getInstance().getList();
        ServiceFeeDAO.getInstance().delete(0);
        list.forEach(x -> {
            System.out.println(x.getService_fee_id());
        });
    }
}
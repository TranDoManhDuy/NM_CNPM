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
/**
 *
 * @author manhh
 */
public class ServiceFeeDAO implements InterfaceDAO.InterfaceDAO <ServiceFee>{
    public static ServiceFeeDAO getInstance() {
        return new ServiceFeeDAO();
    }
    @Override
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
    public boolean insert(ServiceFee serviceFee) {
        String sql = "EXEC insert_service_fee @decision_date = ?, @vehicle_type_id = ?, @amount = ?, @is_active = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setDate(1, Date.valueOf(serviceFee.getDecision_date()));
            ptmt.setInt(2, serviceFee.getVehicle_type_id());
            ptmt.setInt(3, serviceFee.getAmount());
            ptmt.setBoolean(4, serviceFee.isIs_active());
            
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean update(ServiceFee serviceFee) {
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
            
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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
    public boolean delete(int id) {
        String sql = " EXEC delete_service_fee @service_fee_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, id);
            return ptmt.executeUpdate()> 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void main(String[] args) {
        ArrayList<ServiceFee> list = ServiceFeeDAO.getInstance().getList();
        ServiceFeeDAO.getInstance().delete(0);
        list.forEach(x -> {
            System.out.println(x.getService_fee_id());
        });
    }
}
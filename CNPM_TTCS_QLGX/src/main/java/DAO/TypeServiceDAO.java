/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DatabaseHelper.OpenConnection;
import Model.TypeService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author manhh
 */
public class TypeServiceDAO implements InterfaceDAO.InterfaceDAO<TypeService>{
    public static TypeServiceDAO getInstance() {
        return new TypeServiceDAO();
    }
    
    @Override
    public ArrayList<TypeService> getList() {
        ArrayList<TypeService> listTypeService = new ArrayList<>();
        String sql = "SELECT * FROM type_service";
        try (
            Connection conn = OpenConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
        ) {
            while (rs.next()) {
                int type_service_id = rs.getInt("type_service_id");
                int service_fee_id = rs.getInt("service_fee_id");
                int month_unit = rs.getInt("month_unit");
                String service_name = rs.getString("service_name");
                int payment_coefficient = rs.getInt("payment_coefficient");
                boolean is_active = rs.getBoolean("is_active");
                
                TypeService typeService = new TypeService(type_service_id, service_fee_id, month_unit, service_name, payment_coefficient, is_active);
                listTypeService.add(typeService);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTypeService;
    }
    @Override
    public boolean insert(TypeService typeService) {
        String sql = "INSERT INTO type_service (service_fee_id, month_unit, service_name, payment_coefficient) VALUES (?, ?, ?, ?)";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, typeService.getService_fee_id());
            ptmt.setInt(2, typeService.getMonth_unit());
            ptmt.setString(3, typeService.getService_name());
            ptmt.setInt(4, typeService.getPayment_coefficient());
            
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public boolean update(TypeService typeService) {
        String sql = "UPDATE type_service SET service_fee_id = ?, month_unit = ?, service_name = ?, payment_coefficient = ?, is_active = ? WHERE type_service_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, typeService.getService_fee_id());
            ptmt.setInt(2, typeService.getMonth_unit());
            ptmt.setString(3, typeService.getService_name());
            ptmt.setInt(4, typeService.getPayment_coefficient());
            ptmt.setBoolean(5, typeService.isIs_active());
            ptmt.setInt(6, typeService.getType_service_id());
            
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public TypeService findbyID(int id) {
        String sql = "SELECT * FROM type_service WHERE type_service_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, id);
            ResultSet rs = ptmt.executeQuery();
            
            if (rs.next()) {
                int type_service_id = rs.getInt("type_service_id");
                int service_fee_id = rs.getInt("service_fee_id");
                int month_unit = rs.getInt("month_unit");
                String service_name = rs.getString("service_name");
                int payment_coefficient = rs.getInt("payment_coefficient");
                boolean is_active = rs.getBoolean("is_active");
                return new TypeService(type_service_id, service_fee_id, month_unit, service_name, payment_coefficient, is_active);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM type_service WHERE type_service_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, id);
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void main(String[] args) {
        ArrayList<TypeService> list = TypeServiceDAO.getInstance().getList();
        TypeServiceDAO.getInstance().delete(0);
        list.forEach(x -> {
            System.out.println(x.getService_name());
        });
    }
}
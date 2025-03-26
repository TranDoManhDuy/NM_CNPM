/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DatabaseHelper.OpenConnection;
import InterfaceDAO.InterfaceDAO;
import Model.VehicleType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author manhh
 */
public class VehicleTypeDAO  implements InterfaceDAO<VehicleType>{
     public static VehicleTypeDAO getInstance() {
        return new VehicleTypeDAO();
    }
     @Override
     public ArrayList<VehicleType> getList() {
        ArrayList<VehicleType> list = new ArrayList<>();
        String sql = "SELECT * FROM vehicle_types";
        
        try (
            Connection conn = OpenConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(sql);
        ) {
            while (result.next()) {
                int id = result.getInt("vehicle_type_id");
                String name = result.getString("vehicle_type_name");
                boolean isPermission = result.getBoolean("is_availabel");
                list.add(new VehicleType(id, name, isPermission));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
     @Override
     public boolean insert(VehicleType vehicleType) {
        String sql = "INSERT INTO vehicle_types (vehicle_type_name, is_availabel) VALUES (?, ?)";
        
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setString(1, vehicleType.getVehicle_type_name());
            ptmt.setBoolean(2, vehicleType.isIsPermission());
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
     @Override
     public boolean update(VehicleType vehicleType) {
        String sql = "UPDATE vehicle_types SET vehicle_type_name = ?, is_availabel = ? WHERE vehicle_type_id = ?";
        
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setString(1, vehicleType.getVehicle_type_name());
            ptmt.setBoolean(2, vehicleType.isIsPermission());
            ptmt.setInt(3, vehicleType.getVehicle_type_id());
            
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
     @Override
     public VehicleType findbyID(int id) {
        String sql = "SELECT * FROM vehicle_types WHERE vehicle_type_id = ?";
        
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, id);
            ResultSet rs = ptmt.executeQuery();
            
            if (rs.next()) {
                return new VehicleType(
                    rs.getInt("vehicle_type_id"),
                    rs.getString("vehicle_type_name"),
                    rs.getBoolean("is_availabel")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     
     @Override
     public boolean delete(int id) {
        String sql = "DELETE FROM vehicle_types WHERE vehicle_type_id = ?";
        
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
        ArrayList<VehicleType> list = VehicleTypeDAO.getInstance().getList();
        list.forEach(x -> {
            System.out.println(x.getVehicle_type_name());
        });
    }
}

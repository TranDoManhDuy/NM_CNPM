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
public class VehicleTypeDAO {
     public static VehicleTypeDAO getInstance() {
        return new VehicleTypeDAO();
    }
     public ArrayList<VehicleType> getList() {
        ArrayList<VehicleType> list = new ArrayList<>();
        String sql = "EXEC getlist_vehicle_types";
        
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
     public String insert(VehicleType vehicleType) {
        String sql = "EXEC insert_vehicle_type @vehicle_type_name = ?, @is_availabel = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setString(1, vehicleType.getVehicle_type_name());
            ptmt.setBoolean(2, vehicleType.isIsPermission());
            if (ptmt.executeUpdate() > 0) {
                return "Thêm loại phương tiện thành công";
            }
        } catch (Exception e) {
            return "Lỗi: " + e.getMessage();
        }
        return "Thêm loại phương tiện thành công";
    }
    public String update(VehicleType vehicleType) {
        String sql = "EXEC update_vehicle_type @vehicle_type_name = ?, @is_availabel = ?, @vehicle_type_id = ?";
        
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setString(1, vehicleType.getVehicle_type_name());
            ptmt.setBoolean(2, vehicleType.isIsPermission());
            ptmt.setInt(3, vehicleType.getVehicle_type_id());
            
            if (ptmt.executeUpdate() > 0) {
                return "Cập nhật phương tiện thành công";
            }
        } catch (Exception e) {
            return "Lỗi: " + e.getMessage();
        }
        return "Cập nhật phương tiện thành công";
    }
     public VehicleType findbyID(int id) {
        String sql = "EXEC findbyID_vehicle_type @vehicle_type_id = ?";
        
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
     public String delete(int id) {
        String sql = "EXEC delete_vehicle_type @vehicle_type_id = ?";
        
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, id);
            if (ptmt.executeUpdate() > 0) {
                return "Xóa thành công";
            }
        } catch (Exception e) {
            return "Lỗi: " + e.getMessage();
        }
        return "Xóa thành công";
    }
     public static void main(String[] args) {
        ArrayList<VehicleType> list = VehicleTypeDAO.getInstance().getList();
        list.forEach(x -> {
            System.out.println(x.getVehicle_type_name());
        });
    }
}
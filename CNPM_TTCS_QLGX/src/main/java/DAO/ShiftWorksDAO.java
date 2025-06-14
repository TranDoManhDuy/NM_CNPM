/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.ShiftWorks;
import java.util.ArrayList;
import DatabaseHelper.OpenConnection;
import java.sql.*;
import java.util.List;
import java.sql.SQLException;
/**
 *
 * @author HP
 */



public class ShiftWorksDAO {
    public static ShiftWorksDAO getInstance() {
        return new ShiftWorksDAO();
    }
    
    static String extractForeignKeyName(String errorMessage) {
        String keyword = "FOREIGN KEY constraint";
        int startIndex = errorMessage.indexOf('"');
        int endIndex = errorMessage.indexOf('"', startIndex + 1);
        if (startIndex != -1 && endIndex != -1) {
            return errorMessage.substring(startIndex + 1, endIndex);
        }
        return "Unknown";
    }
    
    public List<ShiftWorks> getAllShiftWorks() {
        List<ShiftWorks> list = new ArrayList<>();
        String sql = "{CALL GetAllShiftWorks()}";
        
        try (
            Connection conn = OpenConnection.getConnection();
            CallableStatement stmt = conn.prepareCall(sql);
            ResultSet rs = stmt.executeQuery();
        ) {
            while (rs.next()) {
                ShiftWorks shift = new ShiftWorks(
                    rs.getInt("shift_work_id"),
                    rs.getInt("shift_type_id"),
                    rs.getInt("building_id"),
                    rs.getInt("staff_id"),
                    rs.getInt("task_id"),
                    rs.getDate("shift_date").toLocalDate()
                );
                list.add(shift);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String insert(ShiftWorks shift) {
        String sql = "{CALL InsertShiftWorks(?, ?, ?, ?, ?, ?)}";
        
        try (
            Connection conn = OpenConnection.getConnection();
            CallableStatement ptmt = conn.prepareCall(sql);
        ) {
            ptmt.setInt(1, shift.getShift_type_id());
            ptmt.setInt(2, shift.getBuilding_id());
            ptmt.setInt(3, shift.getStaff_id());
            ptmt.setInt(4, shift.getTask_id());
            ptmt.setDate(5, Date.valueOf(shift.getShift_date()));
            ptmt.registerOutParameter(6, Types.NVARCHAR);
            ptmt.executeUpdate();
            String errorMessage = ptmt.getString(6);
            String foreignKey = extractForeignKeyName(errorMessage);
            if(!foreignKey.equals("Unknown")){
                return "Mã nhân viên không tồn tại.";
            }
            else return errorMessage;
        } catch (SQLException e) {
            return "Thêm không thành công";    
        }
    }

    public String update(ShiftWorks shift) {
        String sql = "{CALL UpdateShiftWorks(?, ?, ?, ?, ?, ?, ?)}";
        
        try (
            Connection conn = OpenConnection.getConnection();
            CallableStatement ptmt = conn.prepareCall(sql);
        ) {
            ptmt.setInt(1, shift.getShift_type_id());
            ptmt.setInt(2, shift.getBuilding_id());
            ptmt.setInt(3, shift.getStaff_id());
            ptmt.setInt(4, shift.getTask_id());
            ptmt.setDate(5, Date.valueOf(shift.getShift_date()));
            ptmt.setInt(6, shift.getShift_work_id());
            ptmt.registerOutParameter(7, Types.NVARCHAR);
            ptmt.executeUpdate();
            String errorMessage = ptmt.getString(7);
            String foreignKey = extractForeignKeyName(errorMessage);
            if(!foreignKey.equals("Unknown")){
                return "Mã nhân viên không tồn tại.";
            }
            else return errorMessage;
        } catch (SQLException e) {
            return "Cập nhật không thành công";
        }
    }

    public String delete(int shift_work_id) {
        String sql = "{CALL DeleteShiftWorks(?, ?)}";
        
        try (
            Connection conn = OpenConnection.getConnection();
            CallableStatement ptmt = conn.prepareCall(sql);
        ) {
            ptmt.setInt(1, shift_work_id);
            ptmt.registerOutParameter(2, Types.NVARCHAR);
            ptmt.executeUpdate();
            String errorMessage = ptmt.getString(2);
            return errorMessage;
        } catch (SQLException e) {
            if(e.getErrorCode() == 247){
                return "Không thể thực hiện thao tác. Ca trực đã có lượt gửi xe được ghi nhận.";
            }
            return "Xóa không thành công";
        }
    }

    public ShiftWorks findByID(int shift_work_id) {
        String sql = "{CALL FindShiftWorkByID(?)}";
        
        try (
            Connection conn = OpenConnection.getConnection();
            CallableStatement ptmt = conn.prepareCall(sql);
        ) {
            ptmt.setInt(1, shift_work_id);
            try (ResultSet rs = ptmt.executeQuery()) {
                if (rs.next()) {
                    return new ShiftWorks(
                        rs.getInt("shift_work_id"),
                        rs.getInt("shift_type_id"),
                        rs.getInt("building_id"),
                        rs.getInt("staff_id"),
                        rs.getInt("task_id"),
                        rs.getDate("shift_date").toLocalDate()
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author HP
 */
import Model.ShiftTypes;
import DatabaseHelper.OpenConnection;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ShiftTypesDAO {
    public static ShiftTypesDAO getInstance() {
        return new ShiftTypesDAO();
    }

    public List<ShiftTypes> getAllShiftTypes() {
        List<ShiftTypes> list = new ArrayList<>();
        String sql = "{CALL GetAllShiftTypes()}";
        
        try (
            Connection conn = OpenConnection.getConnection();
            CallableStatement stmt = conn.prepareCall(sql);
            ResultSet rs = stmt.executeQuery();
        ) {
            while (rs.next()) {
                ShiftTypes shiftType = new ShiftTypes(
                    rs.getInt("shift_type_id"),
                    rs.getString("shift_type_name"),
                    rs.getTime("start_time").toLocalTime(),
                    rs.getTime("end_time").toLocalTime()
                );
                list.add(shiftType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public String insert(ShiftTypes shiftType) {
        String sql = "{CALL InsertShiftTypes(?, ?, ?, ?)}";
        
        try (
            Connection conn = OpenConnection.getConnection();
            CallableStatement ptmt = conn.prepareCall(sql);
        ) {
            ptmt.setString(1, shiftType.getShift_type_name());
            ptmt.setTime(2, Time.valueOf(shiftType.getStart_time()));
            ptmt.setTime(3, Time.valueOf(shiftType.getEnd_time()));
            ptmt.registerOutParameter(4, Types.NVARCHAR);
            ptmt.executeUpdate();
            String errorMessage = ptmt.getString(4);
            return errorMessage;
        } catch (SQLException e) {
                return "Thêm không thành công";    
        }
    }

    public String update(ShiftTypes shiftType) {
        String sql = "{CALL UpdateShiftTypes(?, ?, ?, ?, ?)}";
        
        try (
            Connection conn = OpenConnection.getConnection();
            CallableStatement ptmt = conn.prepareCall(sql);
        ) {
            ptmt.setInt(1, shiftType.getShift_type_id());
            ptmt.setString(2, shiftType.getShift_type_name());
            ptmt.setTime(3, Time.valueOf(shiftType.getStart_time()));
            ptmt.setTime(4, Time.valueOf(shiftType.getEnd_time()));
            ptmt.registerOutParameter(5, Types.NVARCHAR);
            ptmt.executeUpdate();
            String errorMessage = ptmt.getString(5);
            return errorMessage;
        } catch (SQLException e) {
            return "Cập nhật thất bại";
        }
    }

    public String delete(int shift_type_id) {
        String sql = "{CALL DeleteShiftTypes(?)}";
        
        try (
            Connection conn = OpenConnection.getConnection();
            CallableStatement ptmt = conn.prepareCall(sql);
        ) {
            ptmt.setInt(1, shift_type_id);
            ptmt.executeUpdate();
        } catch (SQLException e) {
            if(e.getErrorCode() == 50000){
                return e.getMessage();
            }
            else{
                return "Lỗi không biết";    
                    }
        }
        return "Xóa thành công";
    }

    public ShiftTypes findByID(int shift_type_id) {
        String sql = "{CALL FindByID(?)}";
        
        try (
            Connection conn = OpenConnection.getConnection();
            CallableStatement ptmt = conn.prepareCall(sql);
        ) {
            ptmt.setInt(1, shift_type_id);
            try (ResultSet rs = ptmt.executeQuery()) {
                if (rs.next()) {
                    return new ShiftTypes(
                        rs.getInt("shift_type_id"),
                        rs.getString("shift_type_name"),
                        rs.getTime("start_time").toLocalTime(),
                        rs.getTime("end_time").toLocalTime()
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
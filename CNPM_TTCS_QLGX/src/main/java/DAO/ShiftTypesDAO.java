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
        String sql = "SELECT * FROM shift_types";
        
        try (
            Connection conn = OpenConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
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

    public boolean insert(ShiftTypes shiftType) {
        String sql = "INSERT INTO shift_types ( shift_type_name, start_time, end_time) VALUES (?, ?, ?)";
        
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setString(1, shiftType.getShift_type_name());
            ptmt.setTime(2, Time.valueOf(shiftType.getStart_time()));
            ptmt.setTime(3, Time.valueOf(shiftType.getEnd_time()));

            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(ShiftTypes shiftType) {
        String sql = "UPDATE shift_types SET shift_type_name = ?, start_time = ?, end_time = ? WHERE shift_type_id = ?";
        
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setString(1, shiftType.getShift_type_name());
            ptmt.setTime(2, Time.valueOf(shiftType.getStart_time()));
            ptmt.setTime(3, Time.valueOf(shiftType.getEnd_time()));
            ptmt.setInt(4, shiftType.getShift_type_id());

            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int shift_type_id) {
        String sql = "DELETE FROM shift_types WHERE shift_type_id = ?";
        
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, shift_type_id);
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ShiftTypes findByID(int shift_type_id) {
        String sql = "SELECT * FROM shift_types WHERE shift_type_id = ?";
        
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
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
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.ShiftWorks;
import java.util.ArrayList;
import DatabaseHelper.OpenConnection;
import java.sql.*;
import java.time.LocalTime;
import java.util.List;
/**
 *
 * @author HP
 */



public class ShiftWorksDAO {
    public static ShiftWorksDAO getInstance() {
        return new ShiftWorksDAO();
    }

    public List<ShiftWorks> getAllShiftWorks() {
        List<ShiftWorks> list = new ArrayList<>();
        String sql = "SELECT * FROM shift_works";
        
        try (
            Connection conn = OpenConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
        ) {
            while (rs.next()) {
                ShiftWorks shift = new ShiftWorks(
                    rs.getInt("shift_work_id"),
                    rs.getInt("shift_type_id"),
                    rs.getInt("building_id"),
                    rs.getInt("staff_id"),
                    rs.getInt("task_id"),
                    rs.getTime("shift_date").toLocalTime()
                );
                list.add(shift);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(ShiftWorks shift) {
        String sql = "INSERT INTO shift_works (shift_type_id, building_id, staff_id, task_id, shift_date) VALUES (?, ?, ?, ?, ?)";
        
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, shift.getShift_type_id());
            ptmt.setInt(2, shift.getBuilding_id());
            ptmt.setInt(3, shift.getStaff_id());
            ptmt.setInt(4, shift.getTask_id());
            ptmt.setTime(5, Time.valueOf(shift.getShift_date()));

            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(ShiftWorks shift) {
        String sql = "UPDATE shift_works SET shift_type_id = ?, building_id = ?, staff_id = ?, task_id = ?, shift_date = ? WHERE shift_work_id = ?";
        
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, shift.getShift_type_id());
            ptmt.setInt(2, shift.getBuilding_id());
            ptmt.setInt(3, shift.getStaff_id());
            ptmt.setInt(4, shift.getTask_id());
            ptmt.setTime(5, Time.valueOf(shift.getShift_date()));
            ptmt.setInt(6, shift.getShift_work_id());

            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int shift_work_id) {
        String sql = "DELETE FROM shift_works WHERE shift_work_id = ?";
        
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, shift_work_id);
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ShiftWorks findByID(int shift_work_id) {
        String sql = "SELECT * FROM shift_works WHERE shift_work_id = ?";
        
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
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
                        rs.getTime("shift_date").toLocalTime()
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}


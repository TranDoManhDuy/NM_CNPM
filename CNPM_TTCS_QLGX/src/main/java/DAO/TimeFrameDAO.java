/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DatabaseHelper.OpenConnection;
import Model.TimeFrame;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author manhh
 */

public class TimeFrameDAO  implements InterfaceDAO.InterfaceDAO<TimeFrame>{
    public static TimeFrameDAO getInstance() {
        return new TimeFrameDAO();
    }
    @Override
    public ArrayList<TimeFrame> getList() {
        ArrayList<TimeFrame> listTimeFrames = new ArrayList<>();
        String sql = " EXEC getlist_time_frames";
        try (
            Connection conn = OpenConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
        ) {
            while (rs.next()) {
                int time_frame_id = rs.getInt("time_frame_id");
                LocalDate decision_date = rs.getDate("decision_date").toLocalDate();
                LocalTime time_start = rs.getTime("time_start").toLocalTime();
                LocalTime time_end = rs.getTime("time_end").toLocalTime();
                boolean is_active = rs.getBoolean("is_active");
                
                TimeFrame timeFrame = new TimeFrame(time_frame_id, decision_date, time_start, time_end, is_active);
                listTimeFrames.add(timeFrame);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTimeFrames;
    }
    
    @Override
    public boolean insert(TimeFrame timeFrame) {
        String sql = "EXEC insert_time_frame @decision_date = ?, @time_start = ?, @time_end = ?, @is_active = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setDate(1, Date.valueOf(timeFrame.getDecision_date()));
            ptmt.setTime(2, Time.valueOf(timeFrame.getTime_start()));
            ptmt.setTime(3, Time.valueOf(timeFrame.getTime_end()));
            ptmt.setBoolean(4, timeFrame.isIs_active());
            
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean update(TimeFrame timeFrame) {
        String sql = "EXEC update_time_frame @decision_date = ?, @time_start = ?, @time_end = ?, @is_active = ?,  @time_frame_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setDate(1, Date.valueOf(timeFrame.getDecision_date()));
            ptmt.setTime(2, Time.valueOf(timeFrame.getTime_start()));
            ptmt.setTime(3, Time.valueOf(timeFrame.getTime_end()));
            ptmt.setBoolean(4, timeFrame.isIs_active());
            ptmt.setInt(5, timeFrame.getTime_frame_id());
            
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public TimeFrame findbyID(int id) {
        String sql = "EXEC findbyID_time_frame @time_frame_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, id);
            ResultSet rs = ptmt.executeQuery();
            
            if (rs.next()) {
                int time_frame_id = rs.getInt("time_frame_id");
                LocalDate decision_date = rs.getDate("decision_date").toLocalDate();
                LocalTime time_start = rs.getTime("time_start").toLocalTime();
                LocalTime time_end = rs.getTime("time_end").toLocalTime();
                boolean is_active = rs.getBoolean("is_active");
                
                return new TimeFrame(time_frame_id, decision_date, time_start, time_end, is_active);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public boolean delete(int id) {
        String sql = "EXEC delete_time_frame @time_frame_id = ?";
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
        ArrayList<TimeFrame> list = TimeFrameDAO.getInstance().getList();
        TimeFrameDAO.getInstance().delete(0);
        list.forEach(x -> {
            System.out.println(x.getTime_frame_id());
        });
    }
}

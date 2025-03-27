/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DatabaseHelper.OpenConnection;
import Model.SessionFee;
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
public class SessionFeeDAO implements InterfaceDAO.InterfaceDAO<SessionFee> {
    public static SessionFeeDAO getInstance() {
        return new SessionFeeDAO();
    }
    
    @Override
    public ArrayList<SessionFee> getList() {
        ArrayList<SessionFee> listSessionFees = new ArrayList<>();
        String sql = "EXEC getlist_session_fees";
        try (
            Connection conn = OpenConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
        ) {
            while (rs.next()) {
                int session_fee_id = rs.getInt("session_fee_id");
                int time_frame_id = rs.getInt("time_frame_id");
                int vehicle_type_id = rs.getInt("vehicle_type_id");
                LocalDate decision_date = rs.getDate("decision_date").toLocalDate();
                int amount = rs.getInt("amount");
                boolean is_active = rs.getBoolean("is_active");
                
                SessionFee sessionFee = new SessionFee(time_frame_id, vehicle_type_id, decision_date, session_fee_id , amount, is_active);
                listSessionFees.add(sessionFee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSessionFees;
    }
    @Override
    public boolean insert(SessionFee sessionFee) {
        String sql = "EXEC insert_session_fee @time_frame_id = ?, @vehicle_type_id = ?, @decision_date = ?, @amount = ?, @is_active = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, sessionFee.getTime_frame_id());
            ptmt.setInt(2, sessionFee.getVehicle_type_id());
            ptmt.setDate(3, Date.valueOf(sessionFee.getDecision_date()));
            ptmt.setInt(4, sessionFee.getAmount());
            ptmt.setBoolean(5, sessionFee.isIs_active());
            
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public boolean update(SessionFee sessionFee) {
        String sql = "EXEC update_session_fee @time_frame_id = ?, @vehicle_type_id = ?, @decision_date = ?, @amount = ?, @is_active = ?, @session_fee_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, sessionFee.getTime_frame_id());
            ptmt.setInt(2, sessionFee.getVehicle_type_id());
            ptmt.setDate(3, Date.valueOf(sessionFee.getDecision_date()));
            ptmt.setInt(4, sessionFee.getAmount());
            ptmt.setBoolean(5, sessionFee.isIs_active());
            ptmt.setInt(6, sessionFee.getSession_fee_id());
            
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public SessionFee findbyID(int id) {
        String sql = "EXEC findbyID_session_fee @session_fee_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, id);
            ResultSet rs = ptmt.executeQuery();
            
            if (rs.next()) {
                int session_fee_id = rs.getInt("session_fee_id");
                int time_frame_id = rs.getInt("time_frame_id");
                int vehicle_type_id = rs.getInt("vehicle_type_id");
                LocalDate decision_date = rs.getDate("decision_date").toLocalDate();
                int amount = rs.getInt("amount");
                boolean is_active = rs.getBoolean("is_active");
                 SessionFee sessionFee = new SessionFee(time_frame_id, vehicle_type_id, decision_date, session_fee_id , amount, is_active);
                return sessionFee;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public boolean delete(int id) {
        String sql = " EXEC delete_session_fee @session_fee_id = ?";
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
        ArrayList<SessionFee> list = SessionFeeDAO.getInstance().getList();
        SessionFeeDAO.getInstance().delete(0);
        list.forEach(x -> {
            System.out.println(x.getSession_fee_id());
        });
    }
}   

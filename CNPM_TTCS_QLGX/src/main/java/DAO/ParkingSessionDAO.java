package DAO;

import DatabaseHelper.OpenConnection;
import Model.ParkingSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ParkingSessionDAO{
    public static ParkingSessionDAO getInstance() {
        return new ParkingSessionDAO();
    }

    public ArrayList<ParkingSession> getList() {
        ArrayList<ParkingSession> lstSessions = new ArrayList<>();
        String sql = "EXEC GET_ALL_PARKING_SESSIONS";
        try (
                Connection con = OpenConnection.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                int parking_session_id = rs.getInt("parking_session_id");
                int card_id = rs.getInt("card_id");
                boolean is_service = rs.getBoolean("is_service");
                LocalDateTime check_in_time = rs.getTimestamp("check_in_time").toLocalDateTime();
                LocalDateTime check_out_time = (rs.getTimestamp("check_out_time") != null) ? rs.getTimestamp("check_out_time").toLocalDateTime() : null;
                int check_in_shift_id = rs.getInt("check_in_shift_id");
                int check_out_shift_id = rs.getInt("check_out_shift_id");
                int vehicle_id = rs.getInt("vehicle_id");
                int amount = rs.getInt("amount");
                ParkingSession session = new ParkingSession(parking_session_id, card_id, is_service, check_in_time, check_out_time, check_in_shift_id, check_out_shift_id, vehicle_id, amount);
                lstSessions.add(session);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstSessions;
    }
    
    public Map<String, ArrayList<?>> getAllData() {
        ArrayList<ParkingSession> lstParking_sessions = new ArrayList<>();
        ArrayList<String> lstCheck_in_shift_type_name = new ArrayList<>();
        ArrayList<String> lstCheck_out_shift_type_name = new ArrayList<>();
        String sql = "EXEC GET_ALL_PARKING_SESSIONS";
        
        try (
                Connection con = OpenConnection.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                int parking_session_id = rs.getInt("parking_session_id");
                int card_id = rs.getInt("card_id");
                boolean is_service = rs.getBoolean("is_service");
                LocalDateTime check_in_time = rs.getTimestamp("check_in_time").toLocalDateTime();
                LocalDateTime check_out_time = (rs.getTimestamp("check_out_time") != null) ? rs.getTimestamp("check_out_time").toLocalDateTime() : null;
                int check_in_shift_id = rs.getInt("check_in_shift_id");
                int check_out_shift_id = rs.getInt("check_out_shift_id");
                int vehicle_id = rs.getInt("vehicle_id");
                int amount = rs.getInt("amount");
                String check_in_shift_type_name = rs.getString("in_shift_type_name");
                String check_out_shift_type_name = rs.getString("out_shift_type_name");
                ParkingSession session = new ParkingSession(parking_session_id, card_id, is_service, check_in_time, check_out_time, check_in_shift_id, check_out_shift_id, vehicle_id, amount);
                
                lstParking_sessions.add(session);
                lstCheck_in_shift_type_name.add(check_in_shift_type_name);
                if (check_out_shift_type_name != null) {
                    lstCheck_out_shift_type_name.add(check_out_shift_type_name);
                }
                else {
                    lstCheck_out_shift_type_name.add(null);
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Map<String, ArrayList<?>> result = new HashMap<>();
        result.put("parking_sessions", lstParking_sessions);
        result.put("check_in_shift_type_names", lstCheck_in_shift_type_name);
        result.put("check_out_shift_type_names", lstCheck_out_shift_type_name);
        return result;
    }

    public String insert(ParkingSession session) {
        String sql = "EXEC INSERT_PARKING_SESSION @card_id = ?, @is_service = ?, @check_in_time = ?, @check_out_time = ?, @check_in_shift_id = ?, @check_out_shift_id = ?, @vehicle_id = ?, @amount = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, session.getCard_id());
            ps.setBoolean(2, session.isIs_service());
            ps.setTimestamp(3, Timestamp.valueOf(session.getCheck_in_time())); 
            
            if (session.getCheck_out_time() != null) {
                ps.setTimestamp(4, Timestamp.valueOf(session.getCheck_out_time())); 
            } else {
                ps.setNull(4, java.sql.Types.TIME);
            }
            
            ps.setInt(5, session.getCheck_in_shift_id());
            
            if (session.getCheck_out_shift_id() != -1) { 
                ps.setInt(6, session.getCheck_out_shift_id());
            } else {
                ps.setNull(6, java.sql.Types.INTEGER);
            }
            
            ps.setInt(7, session.getVehicle_id());
            
            if (session.getAmount() != -1) {
                ps.setInt(8, session.getAmount());
            } else {
                ps.setNull(8, java.sql.Types.INTEGER);
            }

            
            if (ps.executeUpdate() > 0) { 
                return "Thêm Thành Công";
            }
        }
        catch (Exception e) {
            return e.getMessage();
        }
        return "Thêm Thành Công";
    }

    public String update(ParkingSession session, boolean is_extend) {
        String sql = "EXEC UPDATE_PARKING_SESSION @card_id = ?, @is_service = ?, @vehicle_id = ?, @parking_session_id = ?, @is_extend = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, session.getCard_id());
            ps.setBoolean(2, session.isIs_service());
            ps.setInt(3, session.getVehicle_id());
            ps.setInt(4, session.getParking_session_id());
            ps.setBoolean(5, is_extend);
            if (ps.executeUpdate() > 0) { 
                return "Cập Nhật Thành Công";
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
        return "Cập Nhật Thành Công";
    }

    public ParkingSession findbyID(int id) {
        String sql = "EXEC GET_PARKING_SESSION_BY_ID @parking_session_id = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                LocalDateTime check_in_time = rs.getTimestamp("check_in_time").toLocalDateTime();
                LocalDateTime check_out_time = (rs.getTimestamp("check_out_time") != null) ? rs.getTimestamp("check_out_time").toLocalDateTime() : null;
                return new ParkingSession(
                    rs.getInt("parking_session_id"),
                    rs.getInt("card_id"),
                    rs.getBoolean("is_service"),
                    check_in_time,
                    check_out_time,
                    rs.getInt("check_in_shift_id"),
                    rs.getInt("check_out_shift_id"),
                    rs.getInt("vehicle_id"),
                    rs.getInt("amount")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String delete(int id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        String sql = "EXEC DELETE_PARKING_SESSION @parking_session_id = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
            if (ps.executeUpdate() > 0) { 
                return "Xóa Thành Công";
            }
        }
        catch (Exception e) {
            return e.getMessage();
        }
        return "Xóa Thành Công";
    }
    
    public String getState(int card_id, int vehicle_id) { 
        String sql = "EXEC SP_GET_STATE @card_id = ?, @vehicle_id = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, card_id);
            ps.setInt(2, vehicle_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String state = rs.getString("RegistrationState").toString().trim();
                return state;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

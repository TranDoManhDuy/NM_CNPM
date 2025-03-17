package DAO;

import DatabaseHelper.OpenConnection;
import InterfaceDAO.InterfaceDAO;
import Model.Customer;
import Model.ParkingSession;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;

public class ParkingSessionDAO implements InterfaceDAO<ParkingSession> {
    public static ParkingSessionDAO getInstance() {
        return new ParkingSessionDAO();
    }

    @Override
    public ArrayList<ParkingSession> getList() {
        ArrayList<ParkingSession> lstSessions = new ArrayList<>();
        String sql = "SELECT * FROM parking_sessions";
        try (
                Connection con = OpenConnection.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                int parking_session_id = rs.getInt("parking_session_id");
                int card_id = rs.getInt("card_id");
                boolean is_service = rs.getBoolean("is_service");
                LocalTime check_in_time = (rs.getTime("check_in_time") != null) ? rs.getTime("check_in_time").toLocalTime() : null;
                LocalTime check_out_time = (rs.getTime("check_out_time") != null) ? rs.getTime("check_out_time").toLocalTime() : null;
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

    @Override
    public boolean insert(ParkingSession session) {
        String sql = "INSERT INTO parking_sessions (card_id, is_service, check_in_time, check_out_time, check_in_shift_id, check_out_shift_id, vehicle_id, amount) VALUES (?,?,?,?,?,?,?,?)";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, session.getCard_id());
            ps.setBoolean(2, session.isIs_service());
            ps.setTime(3, Time.valueOf(session.getCheck_in_time()));
            
            if (session.getCheck_out_time() != null) {
                ps.setTime(4, Time.valueOf(session.getCheck_out_time()));
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

            
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(ParkingSession session) {
        String sql = "UPDATE parking_sessions SET card_id = ?, is_service = ?, check_in_time = ?, check_out_time = ?, check_in_shift_id = ?, check_out_shift_id = ?, vehicle_id = ?, amount = ? WHERE parking_session_id = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, session.getCard_id());
            ps.setBoolean(2, session.isIs_service());
            ps.setTime(3, Time.valueOf(session.getCheck_in_time()));
            
            if (session.getCheck_out_time() != null) {
                ps.setTime(4, Time.valueOf(session.getCheck_out_time()));
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
            
            ps.setInt(9, session.getParking_session_id());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(ParkingSession session) {
        String sql = "DELETE FROM parking_sessions WHERE parking_session_id = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, session.getParking_session_id());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ParkingSession findbyID(int id) {
        String sql = "SELECT * FROM parking_sessions WHERE parking_session_id = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                LocalTime check_in_time = (rs.getTime("check_in_time") != null) ? rs.getTime("check_in_time").toLocalTime() : null;
                LocalTime check_out_time = (rs.getTime("check_out_time") != null) ? rs.getTime("check_out_time").toLocalTime() : null;
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
    
    @Override
    public boolean delete(int id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        String sql = "DELETE FROM parking_sessions WHERE parking_session_id = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static void main(String[] args) {
        LocalTime s1 = LocalTime.of(13, 35);
        LocalTime s2 = LocalTime.of(13, 40);
        ParkingSession par = new ParkingSession(1, true, s1, null, 1, -1, 1, -1);
        ParkingSession par2 = new ParkingSession(4, 1, true, s1, s2, 1, -1, 1, -1);
//        Customer upCus  = new Customer(3, "Vu Dinh Khoa", "030303030303", dob, "M", "0202020202", "97 Man Thien - TP HCM", 1 , "VietNam");
        ParkingSessionDAO parDao = ParkingSessionDAO.getInstance();
        
//        parDao.insert(par);
//        parDao.update(par2);
//        ParkingSession par1 = parDao.findbyID(4);
//        if (par1 != null) {
//            System.out.println(par1.getVehicle_id());
//        }
//        ArrayList<ParkingSession> lstPar = parDao.getList();
//        if (lstPar != null) {
//            for (ParkingSession parkingsession : lstPar) {
//                System.out.println(parkingsession.getVehicle_id());
//            }
//        }
        parDao.delete(4);
    }
}

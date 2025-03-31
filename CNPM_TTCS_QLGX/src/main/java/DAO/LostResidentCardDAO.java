package DAO;

import DatabaseHelper.OpenConnection;
import InterfaceDAO.InterfaceDAO;
import Model.LostResidentCard;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LostResidentCardDAO implements InterfaceDAO<LostResidentCard> {
    public static LostResidentCardDAO getInstance() {
        return new LostResidentCardDAO();
    }

    @Override
    public ArrayList<LostResidentCard> getList() {
        ArrayList<LostResidentCard> lstCards = new ArrayList<>();
        String sql = "SELECT * FROM lost_resident_cards";
        try (
                Connection con = OpenConnection.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lstCards.add(new LostResidentCard(
                        rs.getInt("lost_resident_card_id"),
                        rs.getInt("pk_resident_card"),
                        rs.getInt("parking_session_id")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstCards;
    }
    
    
     public Map<String, ArrayList<?>> getAllData() {
        ArrayList<LostResidentCard> lstLost_resident_cards = new ArrayList<>();
        ArrayList<String> lstName = new ArrayList<>();
        ArrayList<LocalDateTime> check_in_times = new ArrayList<>();
        ArrayList<LocalDateTime> check_out_times = new ArrayList<>();
        String sql = "EXEC GET_ALL_LOST_RESIDENT_CARDS";
        try (
                Connection con = OpenConnection.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                int lost_resident_card_id = rs.getInt("lost_resident_card_id");
                int pk_resident_card = rs.getInt("pk_resident_card");
                int parking_session_id = rs.getInt("parking_session_id");
                LocalDateTime check_in_time = rs.getTimestamp("check_in_time").toLocalDateTime();
                LocalDateTime check_out_time = (rs.getTimestamp("check_out_time") != null) ? rs.getTimestamp("check_out_time").toLocalDateTime() : null;
                String ful_name = rs.getString("full_name");
                
                LostResidentCard lost_resident_card = new LostResidentCard(lost_resident_card_id, pk_resident_card, parking_session_id);
                lstLost_resident_cards.add(lost_resident_card);
                lstName.add(ful_name);
                check_in_times.add(check_in_time);
                if (check_out_time != null) {
                    check_out_times.add(check_out_time);
                }
                else {
                    check_out_times.add(null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, ArrayList<?>> result = new HashMap<>();
        result.put("lost_resident_cards", lstLost_resident_cards);
        result.put("check_in_times", check_in_times);
        result.put("check_out_times", check_out_times);
        result.put("full_names", lstName);
        return result;
    }

    @Override
    public boolean insert(LostResidentCard card) {
        String sql = "INSERT INTO lost_resident_cards (pk_resident_card, parking_session_id) VALUES (?, ?)";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, card.getPk_resident_card());
            ps.setInt(2, card.getParking_session_id());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(LostResidentCard card) {
        String sql = "UPDATE lost_resident_cards SET parking_session_id = ?, pk_resident_card = ? WHERE lost_resident_card_id = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, card.getParking_session_id());
            ps.setInt(2, card.getPk_resident_card());
            ps.setInt(3, card.getLost_resident_card_id());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    
    
    public boolean delete(LostResidentCard card) {
        String sql = "DELETE FROM lost_resident_cards WHERE lost_resident_card_id = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, card.getLost_resident_card_id());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public LostResidentCard findbyID(String pk_resident_card, int parking_session_id) {
        String sql = "SELECT * FROM lost_resident_cards WHERE pk_resident_card = ? AND parking_session_id = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, String.valueOf(pk_resident_card));
            ps.setInt(2, parking_session_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new LostResidentCard(
                            rs.getInt("lost_resident_card_id"),
                            rs.getInt("pk_resident_card"),
                            rs.getInt("parking_session_id")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    
    
    @Override
    public LostResidentCard findbyID(int id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        String sql = "SELECT * FROM lost_resident_cards WHERE lost_resident_card_id = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new LostResidentCard(
                            rs.getInt("lost_resident_card_id"),
                            rs.getInt("pk_resident_card"),
                            rs.getInt("parking_session_id")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        String sql = "DELETE FROM lost_resident_cards WHERE lost_resident_card_id = ?";
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
        LostResidentCard lre = new LostResidentCard(3, 2);
//        Customer upCus  = new Customer(3, "Vu Dinh Khoa", "030303030303", dob, "M", "0202020202", "97 Man Thien - TP HCM", 1 , "VietNam");
        LostResidentCardDAO lreDao = LostResidentCardDAO.getInstance();
        
        lreDao.insert(lre);
//        parDao.update(par);
//        LostResidentCard lrez = lreDao.findbyID(1);
//        if (lrez != null) {
//            System.out.println(lrez.getParking_session_id() + " " + lrez.getPk_resident_card());
//        }
//        ArrayList<LostResidentCard> lstLre = lreDao.getList();
//        if (lstLre != null) {
//            for (LostResidentCard lostre : lstLre) {
//                System.out.println(lostre.getParking_session_id());
//            }
//        }
//        lreDao.delete(1);
    }
}

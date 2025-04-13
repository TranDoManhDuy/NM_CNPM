package DAO;

import DatabaseHelper.OpenConnection;
import Model.LostResidentCard;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LostResidentCardDAO {
    public static LostResidentCardDAO getInstance() {
        return new LostResidentCardDAO();
    }

    public ArrayList<LostResidentCard> getList() {
        ArrayList<LostResidentCard> lstCards = new ArrayList<>();
        String sql = "EXEC GET_ALL_LOST_RESIDENT_CARDS";
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
        String sql = "EXEC GET_ALL_LOST_RESIDENT_CARDS";
        try (
                Connection con = OpenConnection.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                int lost_resident_card_id = rs.getInt("lost_resident_card_id");
                int pk_resident_card = rs.getInt("pk_resident_card");
                int parking_session_id = rs.getInt("parking_session_id");
                String ful_name = rs.getString("full_name");
                
                LostResidentCard lost_resident_card = new LostResidentCard(lost_resident_card_id, pk_resident_card, parking_session_id);
                lstLost_resident_cards.add(lost_resident_card);
                lstName.add(ful_name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, ArrayList<?>> result = new HashMap<>();
        result.put("lost_resident_cards", lstLost_resident_cards);
        result.put("full_names", lstName);
        return result;
    }

    public String insert(LostResidentCard card) {
        String sql = "EXEC INSERT_LOST_RESIDENT_CARD @pk_resident_card = ?, @parking_session_id = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, card.getPk_resident_card());
            ps.setInt(2, card.getParking_session_id());
            if (ps.executeUpdate() > 0) { 
                return "Thêm Thành Công";
            }
        }
        catch (Exception e) {
            return e.getMessage();
        }
        return "Thêm Thành Công";
    }

    public String update(LostResidentCard card) {
        String sql = "EXEC UPDATE_LOST_RESIDENT_CARD @parking_session_id = ?, @pk_resident_card = ?, @lost_resident_card_id = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, card.getParking_session_id());
            ps.setInt(2, card.getPk_resident_card());
            ps.setInt(3, card.getLost_resident_card_id());
            if (ps.executeUpdate() > 0) { 
                return "Cập Nhật Thành Công";
            }
        }
        catch (Exception e) {
            return e.getMessage();
        }
        return "Cập Nhật Thành Công";
    }

    public LostResidentCard findbyID(int id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        String sql = "EXEC GET_LOST_RESIDENT_CARD_BY_ID @lost_resident_card_id = ?";
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

    public String delete(int id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        String sql = "EXEC DELETE_LOST_RESIDENT_CARD @lost_resident_card_id = ?";
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
}

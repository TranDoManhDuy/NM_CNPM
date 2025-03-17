package DAO;

import DatabaseHelper.OpenConnection;
import InterfaceDAO.InterfaceDAO;
import Model.LostResidentCard;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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

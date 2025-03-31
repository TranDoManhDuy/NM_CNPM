package DAO;

import DatabaseHelper.OpenConnection;
import InterfaceDAO.InterfaceDAO;
import Model.ResidentCard;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * DAO cho bảng resident_cards
 */
public class ResidentCardDAO implements InterfaceDAO<ResidentCard> {
    
    public static ResidentCardDAO getInstance() {
        return new ResidentCardDAO();
    }

    @Override
    public ArrayList<ResidentCard> getList() {
        ArrayList<ResidentCard> lstCards = new ArrayList<>();
        String sql = "SELECT * FROM resident_cards";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                int pk_resident_card = rs.getInt("pk_resident_card");
                int customer_id = rs.getInt("customer_id");
                boolean is_active = rs.getBoolean("is_active");

                ResidentCard card = new ResidentCard(pk_resident_card, customer_id, is_active);
                lstCards.add(card);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstCards;
    }
    
    public Map<String, ArrayList<?>> getAllData() {
        ArrayList<ResidentCard> lstCards = new ArrayList<>();
        ArrayList<String> lstName = new ArrayList<>();
        ArrayList<String> lstBuilding_name = new ArrayList<>();
        String sql = "GET_ALL_RESIDENT_CARDS";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                int pk_resident_card = rs.getInt("pk_resident_card");
                int customer_id = rs.getInt("customer_id");
                boolean is_active = rs.getBoolean("is_active");
                String full_name = rs.getString("full_name");
                String building_name = rs.getString("building_name");

                ResidentCard card = new ResidentCard(pk_resident_card, customer_id, is_active);
                lstCards.add(card);
                lstName.add(full_name);
                lstBuilding_name.add(building_name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, ArrayList<?>> result = new HashMap<>();
        result.put("customers", lstCards);
        result.put("building_names", lstBuilding_name);
        result.put("full_names", lstName);
        return result;
    }

    @Override
    public boolean insert(ResidentCard card) {
        String sql = "INSERT INTO resident_cards (customer_id, is_active) VALUES (?, ?)";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, card.getCustomer_id());
            ps.setBoolean(2, card.isIs_active());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(ResidentCard card) {
        String sql = "UPDATE resident_cards SET customer_id = ?, is_active = ? WHERE pk_resident_card = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, card.getCustomer_id());
            ps.setBoolean(2, card.isIs_active());
            ps.setInt(3, card.getPk_resident_card());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(ResidentCard card) {
        String sql = "DELETE FROM resident_cards WHERE pk_resident_card = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, card.getPk_resident_card());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ResidentCard findbyID(int id) {
        String sql = "SELECT * FROM resident_cards WHERE pk_resident_card = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int pk_resident_card = rs.getInt("pk_resident_card");
                    int customer_id = rs.getInt("customer_id");
                    boolean is_active = rs.getBoolean("is_active");

                    return new ResidentCard(pk_resident_card, customer_id, is_active);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM resident_cards WHERE pk_resident_card = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public static void main(String[] args) {
        ResidentCard resident1 = new ResidentCard(2, 1, false);
        ResidentCard resident2 = new ResidentCard(3, 1, true);
//        ResidentCard resident2 = new ResidentCard("12", 3, "1", 9, true);
//        ResidentCard r = new ResidentCard("3", 11, true);
        ResidentCardDAO reDao = ResidentCardDAO.getInstance();
//        reDao.insert(resident2);
//        reDao.update(resident2);
//        ResidentCard re1 = reDao.findbyID(2);
//        if (re1 != null) {
//            System.out.println(re1.getCustomer_id());
//        }
//
//        ArrayList<ResidentCard> lstRe = reDao.getList();
//        
//        if (lstRe != null) {
//            for (ResidentCard resident : lstRe) {
//                System.out.println(resident.getCustomer_id());
//            }
//        }
        reDao.delete(resident1);
    }
}

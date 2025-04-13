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
public class ResidentCardDAO {
    
    public static ResidentCardDAO getInstance() {
        return new ResidentCardDAO();
    }
    
    public ArrayList<ResidentCard> getList() {
        ArrayList<ResidentCard> lstCards = new ArrayList<>();
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

    public String insert(ResidentCard card) {
        String sql = "EXEC INSERT_RESIDENT_CARD @customer_id = ?, @is_active = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, card.getCustomer_id());
            ps.setBoolean(2, card.isIs_active());

            if (ps.executeUpdate() > 0) { 
                return "Thêm Thành Công";
            }
        }
        catch (Exception e) {
            return e.getMessage();
        }
        return "Thêm Thành Công";
    }

    public String update(ResidentCard card) {
        String sql = "EXEC UPDATE_RESIDENT_CARD @customer_id = ?, @is_active = ?, @pk_resident_card = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, card.getCustomer_id());
            ps.setBoolean(2, card.isIs_active());
            ps.setInt(3, card.getPk_resident_card());
            if (ps.executeUpdate() > 0) { 
                return "Cập Nhật Thành Công";
            }
        }
        catch (Exception e) {
            return e.getMessage();
        }
        return "Cập Nhật Thành Công";
    }

    public ResidentCard findbyID(int id) {
        String sql = "EXEC GET_RESIDENT_CARD_BY_ID @pk_resident_card = ?";
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

    public String delete(int id) {
        String sql = "EXEC DELETE_RESIDENT_CARD @pk_resident_card = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
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

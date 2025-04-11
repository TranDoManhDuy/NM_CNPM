/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Model.VisitorParkingCards;
import DatabaseHelper.OpenConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author HP
 */

public class VisitorParkingCardsDAO {
    
    public static VisitorParkingCardsDAO getInstance() {
        return new VisitorParkingCardsDAO();
    }

    public List<VisitorParkingCards> getAll() {
        List<VisitorParkingCards> list = new ArrayList<>();
        String sql = "{CALL GetAllVisitorParkingCards()}";
        
        try (
            Connection conn = OpenConnection.getConnection();
            CallableStatement stmt = conn.prepareCall(sql);
            ResultSet rs = stmt.executeQuery();
        ) {
            while (rs.next()) {
                VisitorParkingCards card = new VisitorParkingCards(
                    rs.getInt("visitor_parking_card_id"),
                    rs.getBoolean("is_active")
                );
                list.add(card);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<VisitorParkingCards> getEmptyCards() {
        List<VisitorParkingCards> list = new ArrayList<>();
        String sql = "{CALL GetEmptyVisitorParkingCards()}";
        
        try (
            Connection conn = OpenConnection.getConnection();
            CallableStatement stmt = conn.prepareCall(sql);
            ResultSet rs = stmt.executeQuery();
        ) {
            while (rs.next()) {
                VisitorParkingCards card = new VisitorParkingCards(
                    rs.getInt("visitor_parking_card_id"),
                    rs.getBoolean("is_active")
                );
                list.add(card);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<VisitorParkingCards> getNonEmptyCards() {
        List<VisitorParkingCards> list = new ArrayList<>();
        String sql = "{CALL GetNonEmptyVisitorParkingCards()}";
        
        try (
            Connection conn = OpenConnection.getConnection();
            CallableStatement stmt = conn.prepareCall(sql);
            ResultSet rs = stmt.executeQuery();
        ) {
            while (rs.next()) {
                VisitorParkingCards card = new VisitorParkingCards(
                    rs.getInt("visitor_parking_card_id"),
                    rs.getBoolean("is_active")
                );
                list.add(card);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean insertVisitorParkingCard() {
        String sql = "{CALL InsertVisitorParkingCard()}";
        
        try (
            Connection conn = OpenConnection.getConnection();
            CallableStatement ptmt = conn.prepareCall(sql);
        ) {
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean update(int visitor_parking_card_id) {
        String sql = "{CALL UpdateVisitorParkingCard(?)}";
        
        try (
            Connection conn = OpenConnection.getConnection();
            CallableStatement ptmt = conn.prepareCall(sql);
        ) {
            ptmt.setInt(1, visitor_parking_card_id);
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int visitor_parking_card_id) {
        String sql = "{CALL DeleteVisitorParkingCard(?)}";
        
        try (
            Connection conn = OpenConnection.getConnection();
            CallableStatement ptmt = conn.prepareCall(sql);
        ) {
            ptmt.setInt(1, visitor_parking_card_id);
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public VisitorParkingCards findById(int visitor_parking_card_id) {
        String sql = "{CALL FindVisitorParkingCardByID(?)}";
        
        try (
            Connection conn = OpenConnection.getConnection();
            CallableStatement ptmt = conn.prepareCall(sql);
        ) {
            ptmt.setInt(1, visitor_parking_card_id);
            try (ResultSet rs = ptmt.executeQuery()) {
                if (rs.next()) {
                    return new VisitorParkingCards(
                        rs.getInt("visitor_parking_card_id"),
                        rs.getBoolean("is_active")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}


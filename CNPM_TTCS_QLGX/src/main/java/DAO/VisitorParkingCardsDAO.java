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
        String sql = "SELECT * FROM visitor_parking_cards";
        
        try (
            Connection conn = OpenConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
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

    public boolean insert(VisitorParkingCards card) {
        String sql = "INSERT INTO visitor_parking_cards (is_active) VALUES (?)";
        
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setBoolean(1, card.isIs_active());

            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(VisitorParkingCards card) {
        String sql = "UPDATE visitor_parking_cards SET is_active = ? WHERE visitor_parking_card_id = ?";
        
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setBoolean(1, card.isIs_active());
            ptmt.setInt(2, card.getVisitor_parking_card_id());

            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int visitor_parking_card_id) {
        String sql = "DELETE FROM visitor_parking_cards WHERE visitor_parking_card_id = ?";
        
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, visitor_parking_card_id);
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public VisitorParkingCards findById(int visitor_parking_card_id) {
        String sql = "SELECT * FROM visitor_parking_cards WHERE visitor_parking_card_id = ?";
        
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
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


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Model.LostVisitorParkingCards;
import DatabaseHelper.OpenConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author HP
 */
public class LostVisitorParkingCardsDAO {
    public static LostVisitorParkingCardsDAO getInstance() {
        return new LostVisitorParkingCardsDAO();
    }

    public List<LostVisitorParkingCards> getAll() {
        List<LostVisitorParkingCards> list = new ArrayList<>();
        String sql = "SELECT * FROM lost_visitor_parking_cards";

        try (
            Connection conn = OpenConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
        ) {
            while (rs.next()) {
                LostVisitorParkingCards lostCard = new LostVisitorParkingCards(
                    rs.getInt("lost_visitor_parking_card_id"),
                    rs.getInt("parking_session_id")
                );
                list.add(lostCard);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(LostVisitorParkingCards lostCard) {
        String sql = "INSERT INTO lost_visitor_parking_cards (parking_session_id) VALUES (?)";

        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, lostCard.getParking_session_id());

            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(LostVisitorParkingCards lostCard) {
        String sql = "UPDATE lost_visitor_parking_cards SET parking_session_id = ? WHERE lost_visitor_parking_card_id = ?";

        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, lostCard.getParking_session_id());
            ptmt.setInt(2, lostCard.getLost_visitor_parking_card_id());

            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int lost_visitor_parking_card_id) {
        String sql = "DELETE FROM lost_visitor_parking_cards WHERE lost_visitor_parking_card_id = ?";

        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, lost_visitor_parking_card_id);
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public LostVisitorParkingCards findById(int lost_visitor_parking_card_id) {
        String sql = "SELECT * FROM lost_visitor_parking_cards WHERE lost_visitor_parking_card_id = ?";

        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, lost_visitor_parking_card_id);
            try (ResultSet rs = ptmt.executeQuery()) {
                if (rs.next()) {
                    return new LostVisitorParkingCards(
                        rs.getInt("lost_visitor_parking_card_id"),
                        rs.getInt("parking_session_id")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

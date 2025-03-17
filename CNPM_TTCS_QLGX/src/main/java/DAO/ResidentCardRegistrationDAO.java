package DAO;

import DatabaseHelper.OpenConnection;
import InterfaceDAO.InterfaceDAO;
import Model.ResidentCardRegistration;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ResidentCardRegistrationDAO implements InterfaceDAO<ResidentCardRegistration> {
    public static ResidentCardRegistrationDAO getInstance() {
        return new ResidentCardRegistrationDAO();
    }

    @Override
    public ArrayList<ResidentCardRegistration> getList() {
        ArrayList<ResidentCardRegistration> lstRegistrations = new ArrayList<>();
        String sql = "SELECT * FROM resident_card_registrations";
        try (
                Connection con = OpenConnection.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lstRegistrations.add(new ResidentCardRegistration(
                        rs.getInt("resident_cards_registration_id"),
                        rs.getInt("pk_resident_card"),
                        rs.getInt("registration_id")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstRegistrations;
    }

    @Override
    public boolean insert(ResidentCardRegistration registration) {
        String sql = "INSERT INTO resident_card_registrations (pk_resident_card, registration_id) VALUES (?, ?)";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, registration.getPk_resident_card());
            ps.setInt(2, registration.getRegistration_id());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(ResidentCardRegistration registration) {
        String sql = "UPDATE resident_card_registrations SET registration_id = ?, pk_resident_card = ? WHERE resident_cards_registration_id = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, registration.getRegistration_id());
            ps.setInt(2, registration.getPk_resident_card());
            ps.setInt(3, registration.getResident_cards_registration_id());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(ResidentCardRegistration registration) {
        String sql = "SELECT * FROM resident_card_registrations WHERE resident_cards_registration_id = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, registration.getResident_cards_registration_id());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ResidentCardRegistration findbyID(int resident_cards_registration_id) {
        String sql = "SELECT * FROM resident_card_registrations WHERE resident_cards_registration_id = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, resident_cards_registration_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new ResidentCardRegistration(
                        rs.getInt("resident_cards_registration_id"),
                        rs.getInt("pk_resident_card"),
                        rs.getInt("registration_id")
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

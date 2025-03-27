/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DatabaseHelper.OpenConnection;
import Model.Payment;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author manhh
 */
public class PaymentDAO implements InterfaceDAO.InterfaceDAO<Payment>{
    public static PaymentDAO getInstance() {
        return new PaymentDAO();
    }
     @Override
    public ArrayList<Payment> getList() {
        ArrayList<Payment> listPayments = new ArrayList<>();
        String sql = "EXEC getlist_payments";
        try (
            Connection conn = OpenConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
        ) {
            while (rs.next()) {
                int payment_id = rs.getInt("payment_id");
                int registration_id = rs.getInt("registration_id");
                LocalDate extension_time = rs.getDate("extension_time").toLocalDate();
                boolean payment_state = rs.getBoolean("payment_state");
                int service_type_id = rs.getInt("service_type_id");
                
                Payment payment = new Payment(payment_id, registration_id, extension_time, payment_state, service_type_id);
                listPayments.add(payment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPayments;
    }
    @Override
    public boolean insert(Payment payment) {
        String sql = "EXEC insert_payment @registration_id = ?, @extension_time = ?, @payment_state = ?, @service_type_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, payment.getRegistration_id());
            ptmt.setDate(2, Date.valueOf(payment.getExtension_time()));
            ptmt.setBoolean(3, payment.isPayment_state());
            ptmt.setInt(4, payment.getService_type_id());
            
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean update(Payment payment) {
        String sql = "EXEC update_payment @registration_id = ?, @extension_time = ?, @payment_state = ?, @service_type_id = ?, @payment_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, payment.getRegistration_id());
            ptmt.setDate(2, Date.valueOf(payment.getExtension_time()));
            ptmt.setBoolean(3, payment.isPayment_state());
            ptmt.setInt(4, payment.getService_type_id());
            ptmt.setInt(5, payment.getPayment_id());
            
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public Payment findbyID(int id) {
        String sql = "EXEC findbyID_payment @payment_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, id);
            ResultSet rs = ptmt.executeQuery();
            
            if (rs.next()) {
                int payment_id = rs.getInt("payment_id");
                int registration_id = rs.getInt("registration_id");
                LocalDate extension_time = rs.getDate("extension_time").toLocalDate();
                boolean payment_state = rs.getBoolean("payment_state");
                int service_type_id = rs.getInt("service_type_id");
                
                return new Payment(payment_id, registration_id, extension_time, payment_state, service_type_id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public boolean delete(int id) {
        String sql = "EXEC delete_payment @payment_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, id);
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void main(String[] args) {
        ArrayList<Payment> list = PaymentDAO.getInstance().getList();
        PaymentDAO.getInstance().delete(5);
        list.forEach(x -> {
            System.out.println(x.getRegistration_id());
        });
    }
}
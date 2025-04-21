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
import java.util.Arrays;

/**
 *
 * @author manhh
 */
public class PaymentDAO {
    public static PaymentDAO getInstance() {
        return new PaymentDAO();
    }
    public ArrayList<ArrayList<String>> getPaymentRender() {
        ArrayList<ArrayList<String>> dataPayment = new ArrayList<>();
        String sql = "EXEC Payment_render";
        try (
            Connection conn = OpenConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
        ) {
            while (rs.next()) {
                int payment_id = rs.getInt("payment_id");
                int registration_id = rs.getInt("registration_id");
                String customer_name = rs.getString("full_name");
                LocalDate extension_time = rs.getDate("extension_time").toLocalDate();
                String type_service_id = String.valueOf(rs.getInt("type_service_id"));
                String type_service_name = rs.getString("service_name");
                boolean payment_state = rs.getBoolean("payment_state");
                String trangthai = payment_state ? "Đã hoàn tất" : "Chưa hoàn tất";
                
                int sothang = rs.getInt("month_unit");
                float heso = rs.getFloat("payment_coefficient");
                int sotien = rs.getInt("amount");
                ArrayList<String> payment_data = new ArrayList<>(Arrays.asList(
                        String.valueOf(payment_id),
                        String.valueOf(registration_id),
                        customer_name,
                        String.valueOf(extension_time),
                        type_service_name,
                        trangthai,
                        type_service_id,
                        String.valueOf(sothang),
                        String.valueOf(heso),
                        String.valueOf(sotien)
                ));
                dataPayment.add(payment_data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataPayment;
    }
    
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
    public String insert(Payment payment) {
        String sql = "EXEC insert_payment @registration_id = ?, @extension_time = ?, @payment_state = ?, @service_type_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, payment.getRegistration_id());
            ptmt.setDate(2, Date.valueOf(payment.getExtension_time()));
            ptmt.setBoolean(3, payment.isPayment_state());
            ptmt.setInt(4, payment.getService_type_id());
            
            if (ptmt.executeUpdate() >= 0) {
                return "Thêm đơn thanh toán thành công";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Lỗi: " + e.getMessage();
        }
        return "Thêm đơn thanh toán thành công";
    }
    public String update(Payment payment) {
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
            
            if (ptmt.executeUpdate() >= 0) {
                return "Cập nhật thành công";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Lỗi: " + e.getMessage();
        }
        return "Cập nhật thành công";
    }
    
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
  
    public String delete(int id) {
        String sql = "EXEC delete_payment @payment_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, id);
            if (ptmt.executeUpdate() >= 0) {
                return "Xoá thành công";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Lỗi: " + e.getMessage();
        }
        return "Xoá thành công";
    }
    public static void main(String[] args) {
        ArrayList<Payment> list = PaymentDAO.getInstance().getList();
        PaymentDAO.getInstance().delete(5);
        list.forEach(x -> {
            System.out.println(x.getRegistration_id());
        });
    }
}
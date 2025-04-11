/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DatabaseHelper.OpenConnection;
import Model.TypeService;
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
public class TypeServiceDAO {
    public static TypeServiceDAO getInstance() {
        return new TypeServiceDAO();
    }
    
    public ArrayList<ArrayList<String>> getArrServiceTypeRender() {
        ArrayList<ArrayList<String>> arrServiceTypeRender = new ArrayList<>();
        String sql = "EXEC ServiceType_render";
        try (
            Connection conn = OpenConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
        ) {
            while (rs.next()) {
                
                int type_service_id = rs.getInt("type_service_id");
                String service_name = rs.getString("service_name");
                int service_fee_id = rs.getInt("service_fee_id");
                double amount = rs.getDouble("amount");
                int vehicle_type_id = rs.getInt("vehicle_type_id");
                String vehicle_type_name = rs.getString("vehicle_type_name");
                int month_unit = rs.getInt("month_unit");
                float payment_coefficient = rs.getFloat("payment_coefficient");
                boolean is_active = rs.getBoolean("is_active");
                LocalDate decisionDate = rs.getDate("decision_date").toLocalDate();
                String state;
                if (is_active) {
                    state = "Còn hạn";
                }
                else {
                    state = "Hết hạn";
                }
                ArrayList<String> dataRow = new ArrayList<>(Arrays.asList(
                    String.valueOf(type_service_id),
                    service_name,
                    String.valueOf(amount),
                    String.valueOf(service_fee_id),
                    vehicle_type_name,
                    String.valueOf(month_unit),
                    String.valueOf(payment_coefficient),
                    state,
                    String.valueOf(service_fee_id),
                    String.valueOf(decisionDate)
                ));
                arrServiceTypeRender.add(dataRow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }   
        return arrServiceTypeRender;
    }
    
    public ArrayList<TypeService> getList() {
        ArrayList<TypeService> listTypeService = new ArrayList<>();
        String sql = "EXEC getlist_type_service";
        try (
            Connection conn = OpenConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
        ) {
            while (rs.next()) {
                int type_service_id = rs.getInt("type_service_id");
                int service_fee_id = rs.getInt("service_fee_id");
                int month_unit = rs.getInt("month_unit");
                String service_name = rs.getString("service_name");
                LocalDate decision_date = rs.getDate("decision_date").toLocalDate();
                float payment_coefficient = rs.getFloat("payment_coefficient");
                boolean is_active = rs.getBoolean("is_active");
                
                TypeService typeService = new TypeService(type_service_id, service_fee_id, month_unit, service_name,decision_date, payment_coefficient, is_active);
                listTypeService.add(typeService);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTypeService;
    }
    public String insert(TypeService typeService) {
        String sql = "EXEC insert_type_service @service_fee_id = ?, @month_unit = ?,@service_name = ?, @decision_date = ?, @payment_coefficient = ?, @is_active = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, typeService.getService_fee_id());
            ptmt.setInt(2, typeService.getMonth_unit());
            ptmt.setString(3, typeService.getService_name());
            ptmt.setDate(4, Date.valueOf(typeService.getDecision_date()));
            ptmt.setFloat(5, (float) typeService.getPayment_coefficient());
            ptmt.setBoolean(6, typeService.isIs_active());
            
            if (ptmt.executeUpdate() >= 0) {
                return "Thêm loại dịch vụ thành công";
            }
        } catch (Exception e) {
            return "Lỗi: " + e.getMessage();
        }
        return "Thêm loại dịch vụ thành công";
    }
    
    public String update(TypeService typeService) {
        String sql = "EXEC update_type_service @service_fee_id = ?, @month_unit = ?, @service_name = ?, @decision_date = ?, @payment_coefficient = ?, @is_active = ?, @type_service_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, typeService.getService_fee_id());
            ptmt.setInt(2, typeService.getMonth_unit());
            ptmt.setString(3, typeService.getService_name());
            ptmt.setDate(4, Date.valueOf(typeService.getDecision_date()));
            ptmt.setFloat(5, (float) typeService.getPayment_coefficient());
            ptmt.setBoolean(6, typeService.isIs_active());
            ptmt.setInt(7, typeService.getType_service_id());
            
            if (ptmt.executeUpdate() >= 0) {
                return "Cập nhật thành công";
            }
        } catch (Exception e) {
            return "Lỗi: " + e.getMessage();
        }
        return "Cập nhật thành công";
    }
    
    public TypeService findbyID(int id) {
        String sql = "EXEC findbyID_type_service @type_service_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, id);
            ResultSet rs = ptmt.executeQuery();
            
            if (rs.next()) {
                int type_service_id = rs.getInt("type_service_id");
                int service_fee_id = rs.getInt("service_fee_id");
                int month_unit = rs.getInt("month_unit");
                String service_name = rs.getString("service_name");
                LocalDate decision_date = rs.getDate("decision_date").toLocalDate();
                float payment_coefficient = rs.getFloat("payment_coefficient");
                boolean is_active = rs.getBoolean("is_active");
                return new TypeService(type_service_id, service_fee_id, month_unit, service_name, decision_date , payment_coefficient, is_active);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String delete(int id) {
        String sql = "EXEC delete_type_service @type_service_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, id);
            if (ptmt.executeUpdate() >= 0) {
                return "Xóa thành công";
            }
        } catch (Exception e) {
            return "Lỗi: " + e.getMessage();
        }
        return "Xóa thành công";
    }
    public static void main(String[] args) {
        ArrayList<TypeService> list = TypeServiceDAO.getInstance().getList();
        TypeServiceDAO.getInstance().delete(0);
        list.forEach(x -> {
            System.out.println(x.getService_name());
        });
    }
}
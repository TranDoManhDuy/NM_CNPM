package DAO;

import Annotation.LogMessage;
import Model.Staff;
import java.util.ArrayList;
import DatabaseHelper.OpenConnection;
import GUI.ViewMain;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.time.LocalDate;

public class StaffDAO implements InterfaceDAO.InterfaceDAO<Staff> {
    private LogMessage logMessage;
    private ViewMain viewmain;
    public static StaffDAO getInstance() {
        return new StaffDAO();
    }
    @Override 
    public ArrayList<Staff> getList() {
        ArrayList<Staff> list_staff = new ArrayList<>();
        String sql = "EXEC getlist_staff";

        try (
            Connection conn = OpenConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(sql);
        ) {
            while (result.next()) {
                int staff_id = result.getInt("staff_id");
                int role_id = result.getInt("role_id");
                String full_name = result.getString("full_name");
                String ssn = result.getString("ssn");
                LocalDate date_of_birth = result.getDate("date_of_birth").toLocalDate();
                String gender = result.getString("gender");
                String phone_number = result.getString("phone_number");
                String address = result.getString("address");
                String email = result.getString("email");
                boolean is_active = result.getBoolean("is_active");
                int position_id = result.getInt("position_id");
                int account_number = result.getInt("account_number");                              
                Staff staff = new Staff(staff_id, role_id, full_name, ssn, date_of_birth, gender, phone_number, address, email, is_active, position_id, account_number);
                list_staff.add(staff);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list_staff;
    }

    @Override 
    // Thêm nhân viên mới
    public boolean insert(Staff st)  {
        String sql = "EXEC insert_staff @role_id = ?, @full_name = ?, @ssn = ?, @date_of_birth = ?, @gender = ?, @phone_number = ?, @address = ?, @email = ?, @is_active = ?, @position_id = ?, @account_number = ? ";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, st.getRoleId());
            ptmt.setString(2, st.getFullName());
            ptmt.setString(3, st.getSsn());
            ptmt.setDate(4, Date.valueOf(st.getDateOfBirth())); 
            ptmt.setString(5, st.getGender());
            ptmt.setString(6, st.getPhoneNumber());
            ptmt.setString(7, st.getAddress());
            ptmt.setString(8, st.getEmail());
            ptmt.setBoolean(9, st.isActive());
            ptmt.setInt(10, st.getPositionId());
            ptmt.setInt(11, st.getAccountNumber());
           
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override 
    // Cập nhật thông tin nhân viên
    public boolean update(Staff st) {
        String sql = "EXEC update_staff @role_id = ?, @full_name = ?, @ssn = ?, @date_of_birth = ?, @gender = ?, @phone_number = ?, @address = ?, @email = ?, @is_active = ?, @position_id = ?, @account_number = ?, @staff_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, st.getRoleId());
            ptmt.setString(2, st.getFullName());
            ptmt.setString(3, st.getSsn());
            ptmt.setDate(4, Date.valueOf(st.getDateOfBirth())); 
            ptmt.setString(5, st.getGender());
            ptmt.setString(6, st.getPhoneNumber());
            ptmt.setString(7, st.getAddress());
            ptmt.setString(8, st.getEmail());
            ptmt.setBoolean(9, st.isActive());
            ptmt.setInt(10, st.getPositionId());
            ptmt.setInt(11, st.getAccountNumber());
            ptmt.setInt(12, st.getStaffId());
            
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    

    @Override
    public Staff findbyID(int id) {
        String sql = "EXEC findbyID_staff @staff_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, id);
            ResultSet result = ptmt.executeQuery();
            
            if (result.next()) {
                int staff_id = result.getInt("staff_id");
                int role_id = result.getInt("role_id");
                String full_name = result.getString("full_name");
                String ssn = result.getString("ssn");
                LocalDate date_of_birth = result.getDate("date_of_birth").toLocalDate();
                String gender = result.getString("gender");
                String phone_number = result.getString("phone_number");
                String address = result.getString("address");
                String email = result.getString("email");
                boolean is_active = result.getBoolean("is_active");
                int position_id = result.getInt("position_id");
                int account_number = result.getInt("account_number"); 
                
                return new Staff(staff_id, role_id, full_name, ssn, date_of_birth, gender, phone_number, address, email, is_active, position_id, account_number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
   
    @Override 
    // Xóa nhân viên
    public boolean delete(int id) {
        String sql = "EXEC delete_staff @staff_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, id);
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            throw new RuntimeException("Lỗi: " + e.getMessage(), e);
            }
        }
    
    public boolean isAccountUsedByStaff(int accountNumber) {
        String sql = "SELECT 1 FROM staff WHERE account_number = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, accountNumber);
            ResultSet rs = ptmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean isSsnExists(String ssn, int excludeStaffId) {
        String sql = "SELECT 1 FROM staff WHERE ssn = ? AND staff_id <> ?";
        try (Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql)) {
            ptmt.setString(1, ssn);
            ptmt.setInt(2, excludeStaffId);
            ResultSet rs = ptmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isPhoneExists(String phone, int excludeStaffId) {
        String sql = "SELECT 1 FROM staff WHERE phone_number = ? AND staff_id <> ?";
        try (Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql)) {
            ptmt.setString(1, phone);
            ptmt.setInt(2, excludeStaffId);
            ResultSet rs = ptmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isEmailExists(String email, int excludeStaffId) {
        String sql = "SELECT 1 FROM staff WHERE email = ? AND staff_id <> ?";
        try (Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql)) {
            ptmt.setString(1, email);
            ptmt.setInt(2, excludeStaffId);
            ResultSet rs = ptmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
        private void log_message(String message) {
        this.logMessage = new LogMessage(message) {
            @Override
            public void action() {
                this.setVisible(false);
            }
        };
        logMessage.setLocationRelativeTo(null);
        this.logMessage.setVisible(true);
    }

    
} 


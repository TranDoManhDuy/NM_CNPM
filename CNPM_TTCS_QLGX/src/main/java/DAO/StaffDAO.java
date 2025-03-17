package DAO;

import Model.Staff;
import java.util.ArrayList;
import DatabaseHelper.OpenConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.time.LocalDate;

public class StaffDAO {
    public static StaffDAO getInstance() {
        return new StaffDAO();
    }

    // Lấy danh sách nhân viên
    public ArrayList<Staff> getListStaff() {
        ArrayList<Staff> list_staff = new ArrayList<>();
        String sql = "SELECT * FROM staff";
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
                
                list_staff.add(new Staff(staff_id, role_id, full_name, ssn, date_of_birth, gender, phone_number, address, email, is_active));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list_staff;
    }
    
    // Thêm nhân viên mới
    public boolean insert(Staff st) {
        String sql = "INSERT INTO staff (role_id, full_name, ssn, date_of_birth, gender, phone_number, address, email, is_active) VALUES (?,?,?,?,?,?,?,?,?)";
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
            
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Cập nhật thông tin nhân viên
    public boolean update(Staff st) {
        String sql = "UPDATE staff SET role_id = ?, full_name = ?, ssn = ?, date_of_birth = ?, gender = ?, phone_number = ?, address = ?, email = ?, is_active = ? WHERE staff_id = ?";
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
            ptmt.setInt(10, st.getStaffId());
            
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Tìm kiếm nhân viên bằng staff_id
    public Staff findById(int staff_id) {
        String sql = "SELECT * FROM staff WHERE staff_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, staff_id);
            ResultSet rs = ptmt.executeQuery();
            
            if (rs.next()) {
                Staff st = new Staff();
                st.setStaffId(rs.getInt("staff_id"));
                st.setRoleId(rs.getInt("role_id"));
                st.setFullName(rs.getString("full_name"));
                st.setSsn(rs.getString("ssn"));
                st.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate()); // Chuyển đổi Date về LocalDate
                st.setGender(rs.getString("gender"));
                st.setPhoneNumber(rs.getString("phone_number"));
                st.setAddress(rs.getString("address"));
                st.setEmail(rs.getString("email"));
                st.setActive(rs.getBoolean("is_active"));
                
                return st;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Xóa nhân viên
    public boolean delete(int staff_id) {
        String sql = "DELETE FROM staff WHERE staff_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, staff_id);
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

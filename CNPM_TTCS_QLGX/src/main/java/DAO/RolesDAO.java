package DAO;

import Model.Roles;
import DatabaseHelper.OpenConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class RolesDAO {
    
    public static RolesDAO getInstance() {
        return new RolesDAO();
    }

    public ArrayList<Roles> getAllRoles() {
        ArrayList<Roles> rolesList = new ArrayList<>();
        String sql = "SELECT * FROM roles";
        
        try (
            Connection conn = OpenConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
        ) {
            while (rs.next()) {
                int roleId = rs.getInt("role_id");
                String roleName = rs.getString("role_name");
                rolesList.add(new Roles(roleId, roleName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rolesList;
    }

    public boolean insert(Roles role) {
        String sql = "INSERT INTO roles (role_name) VALUES (?)";
        
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setString(1, role.getRoleName());
            
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Roles role) {
        String sql = "UPDATE roles SET role_name = ? WHERE role_id = ?";
        
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setString(1, role.getRoleName());
            ptmt.setInt(2, role.getRoleId());
            
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int roleId) {
        String sql = "DELETE FROM roles WHERE role_id = ?";
        
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, roleId);
            
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Tìm vai trò theo ID
    public Roles findById(int roleId) {
        String sql = "SELECT * FROM roles WHERE role_id = ?";
        
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, roleId);
            ResultSet rs = ptmt.executeQuery();
            
            if (rs.next()) {
                return new Roles(rs.getInt("role_id"), rs.getString("role_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

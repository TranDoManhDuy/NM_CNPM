package DAO;

import Model.Role;
import java.util.ArrayList;
import DatabaseHelper.OpenConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class RoleDAO implements InterfaceDAO.InterfaceDAO<Role> {
    public static RoleDAO getInstance() {
        return new RoleDAO();
    }
    
    @Override
    public ArrayList<Role> getList() {
        ArrayList<Role> list_role = new ArrayList<>();
        String sql = "EXEC getlist_role";
        
        try (
            Connection conn = OpenConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(sql);
        ) {
            while (result.next()) {
                int role_id = result.getInt("role_id");
                String role_name = result.getString("role_name");
                Role role = new Role(role_id, role_name);
                list_role.add(role);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list_role;
    }
    
    @Override
    public boolean insert(Role role) {
        String sql = "EXEC insert_role @role_name = ?";
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
    
    @Override
    public boolean update(Role role) {
        String sql = "EXEC update_role @role_id = ?, @role_name = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, role.getRoleId());
            ptmt.setString(2, role.getRoleName());
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public Role findbyID(int id) {
        String sql = "EXEC findbyID_role @role_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, id);
            ResultSet result = ptmt.executeQuery();
            
            if (result.next()) {
                int role_id = result.getInt("role_id");
                String role_name = result.getString("role_name");
                return new Role(role_id, role_name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public boolean delete(int id) {
        String sql = "EXEC delete_role @role_id = ?";
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
}

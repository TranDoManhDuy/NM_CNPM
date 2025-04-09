package DAO;

import Model.Permission;
import java.util.ArrayList;
import DatabaseHelper.OpenConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PermissionDAO implements InterfaceDAO.InterfaceDAO<Permission> {
    public static PermissionDAO getInstance() {
        return new PermissionDAO();
    }
    
    @Override
    public ArrayList<Permission> getList() {
        ArrayList<Permission> list_permission = new ArrayList<>();
        String sql = "EXEC getlist_permission";
        
        try (
            Connection conn = OpenConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(sql);
        ) {
            while (result.next()) {
                int permission_id = result.getInt("permission_id");
                String permission_name = result.getString("permission_name");
                String permission_desc = result.getString("permission_desc");
                Permission permission = new Permission(permission_id, permission_name, permission_desc);
                list_permission.add(permission);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list_permission;
    }
    
    @Override
    public boolean insert(Permission permission) {
        String sql = "EXEC insert_permission @permission_name = ?, @permission_desc = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setString(1, permission.getPermissionName());
            ptmt.setString(2, permission.getPermissionDesc());
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public boolean update(Permission permission) {
        String sql = "EXEC update_permission @permission_id = ?, @permission_name = ?, @permission_desc = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, permission.getPermissionId());
            ptmt.setString(2, permission.getPermissionName());
            ptmt.setString(3, permission.getPermissionDesc());
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public Permission findbyID(int id) {
        String sql = "EXEC findbyID_permission @permission_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, id);
            ResultSet result = ptmt.executeQuery();
            
            if (result.next()) {
                int permission_id = result.getInt("permission_id");
                String permission_name = result.getString("permission_name");
                String permission_desc = result.getString("permission_desc");
                return new Permission(permission_id, permission_name, permission_desc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public boolean delete(int id) {
        String sql = "EXEC delete_permission @permission_id = ?";
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

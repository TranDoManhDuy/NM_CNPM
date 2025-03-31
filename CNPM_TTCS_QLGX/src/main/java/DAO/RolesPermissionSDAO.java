package DAO;

import Model.RolePermission;
import java.util.ArrayList;
import DatabaseHelper.OpenConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class RolesPermissionSDAO implements InterfaceDAO.InterfaceDAO<RolePermission> {
    public static RolesPermissionSDAO getInstance() {
        return new RolesPermissionSDAO();
    }
    
    @Override
    public ArrayList<RolePermission> getList() {
        ArrayList<RolePermission> list_rolePermissions = new ArrayList<>();
        String sql = "EXEC getlist_role_permissions";
        
        try (
            Connection conn = OpenConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(sql);
        ) {
            while (result.next()) {
                int role_id = result.getInt("role_id");
                int permission_id = result.getInt("permission_id");
                int roles_permission_id = result.getInt("roles_permission_id");
                RolePermission rolePermission = new RolePermission(role_id, permission_id, roles_permission_id);
                list_rolePermissions.add(rolePermission);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list_rolePermissions;
    }
    
    @Override
    public boolean insert(RolePermission rolePermission) {
        String sql = "EXEC insert_role_permission @role_id = ?, @permission_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, rolePermission.getRoleId());
            ptmt.setInt(2, rolePermission.getPermissionId());
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public boolean update(RolePermission rolePermission) {
        String sql = "EXEC update_role_permission @roles_permission_id = ?, @role_id = ?, @permission_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, rolePermission.getRolesPermissionId());
            ptmt.setInt(2, rolePermission.getRoleId());
            ptmt.setInt(3, rolePermission.getPermissionId());
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public RolePermission findbyID(int id) {
        String sql = "EXEC findbyID_role_permission @roles_permission_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, id);
            ResultSet result = ptmt.executeQuery();
            
            if (result.next()) {
                int role_id = result.getInt("role_id");
                int permission_id = result.getInt("permission_id");
                int roles_permission_id = result.getInt("roles_permission_id");
                return new RolePermission(role_id, permission_id, roles_permission_id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public boolean delete(int id) {
        String sql = "EXEC delete_role_permission @roles_permission_id = ?";
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

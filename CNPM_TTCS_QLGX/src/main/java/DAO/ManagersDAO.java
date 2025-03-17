package DAO;

import Model.Managers;
import java.util.ArrayList;
import DatabaseHelper.OpenConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ManagersDAO {
    public static ManagersDAO getInstance() {
        return new ManagersDAO();
    }

    public ArrayList<Managers> getListManagers() {
        ArrayList<Managers> list_managers = new ArrayList<>();
        String sql = "SELECT * FROM managers";
        try (
                Connection conn = OpenConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet result = stmt.executeQuery(sql);
        ) {
            while (result.next()) {
                int staff_id = result.getInt("staff_id");
                list_managers.add(new Managers(staff_id));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list_managers;
    }
    
    public boolean insert(Managers manager) {
        String sql = "INSERT INTO managers (staff_id) VALUES (?)";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, manager.getStaffId());
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean update(Managers manager) {
        String sql = "UPDATE managers SET staff_id = ? WHERE staff_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, manager.getStaffId());
            ptmt.setInt(2, manager.getStaffId());
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Managers findById(int staff_id) {
        String sql = "SELECT * FROM managers WHERE staff_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, staff_id);
            ResultSet rs = ptmt.executeQuery();
            if (rs.next()) {
                return new Managers(rs.getInt("staff_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean delete(int staff_id) {
        String sql = "DELETE FROM managers WHERE staff_id = ?";
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

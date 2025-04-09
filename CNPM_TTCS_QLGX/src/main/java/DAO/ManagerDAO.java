package DAO;

import Model.Manager;
import java.util.ArrayList;
import DatabaseHelper.OpenConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ManagerDAO implements InterfaceDAO.InterfaceDAO<Manager> {
    public static ManagerDAO getInstance() {
        return new ManagerDAO();
    }
    
    @Override
    public ArrayList<Manager> getList() {
        ArrayList<Manager> list_managers = new ArrayList<>();
        String sql = "EXEC getlist_managers";
        
        try (
            Connection conn = OpenConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(sql);
        ) {
            while (result.next()) {
                int staff_id = result.getInt("staff_id");
                Manager manager = new Manager(staff_id);
                list_managers.add(manager);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list_managers;
    }
    
    @Override
    public boolean insert(Manager manager) {
        String sql = "EXEC insert_manager @staff_id = ?";
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
    
    @Override
    public boolean update(Manager manager) {
        String sql = "EXEC update_manager @staff_id = ?";
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
    
    @Override
    public Manager findbyID(int id) {
        String sql = "EXEC findbyID_manager @staff_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, id);
            ResultSet result = ptmt.executeQuery();
            
            if (result.next()) {
                int staff_id = result.getInt("staff_id");
                return new Manager(staff_id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public boolean delete(int id) {
        String sql = "EXEC delete_manager @staff_id = ?";
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

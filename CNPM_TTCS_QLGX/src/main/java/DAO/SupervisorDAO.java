package DAO;

import Model.Supervisor;
import java.util.ArrayList;
import DatabaseHelper.OpenConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class SupervisorDAO implements InterfaceDAO.InterfaceDAO<Supervisor> {
    public static SupervisorDAO getInstance() {
        return new SupervisorDAO();
    }
    
    @Override
    public ArrayList<Supervisor> getList() {
        ArrayList<Supervisor> list_supervisors = new ArrayList<>();
        String sql = "EXEC getlist_supervisors";
        
        try (
            Connection conn = OpenConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(sql);
        ) {
            while (result.next()) {
                int manager_id = result.getInt("manager_id");
                int staff_id = result.getInt("staff_id");
                int supervisor_id = result.getInt("supervisor_id");
                Supervisor supervisor = new Supervisor(manager_id, staff_id, supervisor_id);
                list_supervisors.add(supervisor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list_supervisors;
    }
    
    @Override
    public boolean insert(Supervisor supervisor) {
        String sql = "EXEC insert_supervisor @manager_id = ?, @staff_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, supervisor.getManagerId());
            ptmt.setInt(2, supervisor.getStaffId());
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public boolean update(Supervisor supervisor) {
        String sql = "EXEC update_supervisor @supervisor_id = ?, @manager_id = ?, @staff_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, supervisor.getSupervisorId());
            ptmt.setInt(2, supervisor.getManagerId());
            ptmt.setInt(3, supervisor.getStaffId());
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public Supervisor findbyID(int id) {
        String sql = "EXEC findbyID_supervisor @supervisor_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, id);
            ResultSet result = ptmt.executeQuery();
            
            if (result.next()) {
                int manager_id = result.getInt("manager_id");
                int staff_id = result.getInt("staff_id");
                int supervisor_id = result.getInt("supervisor_id");
                return new Supervisor(manager_id, staff_id, supervisor_id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public boolean delete(int id) {
        String sql = "EXEC delete_supervisor @supervisor_id = ?";
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
    
    public boolean delete(int manager_id, int staff_id) {
    String sql = "DELETE FROM supervisors WHERE manager_id = ? AND staff_id = ?";

    try (
        Connection conn = OpenConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
    ) {
        pstmt.setInt(1, manager_id);
        pstmt.setInt(2, staff_id);

        int rowsDeleted = pstmt.executeUpdate();
        return rowsDeleted > 0;

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null,
            "Lỗi khi xóa mối quan hệ giám sát:\n" + e.getMessage(),
            "Lỗi xóa", JOptionPane.ERROR_MESSAGE);
        return false;
    }
}

    
}

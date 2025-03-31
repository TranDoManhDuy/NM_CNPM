/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Model.Tasks;
import DatabaseHelper.OpenConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author HP
 */


public class TasksDAO {
    public static TasksDAO getInstance() {
        return new TasksDAO();
    }

    public List<Tasks> getAllTasks() {
        List<Tasks> list = new ArrayList<>();
        String sql = "{CALL GetAllTasks()}";
        
        try (
            Connection conn = OpenConnection.getConnection();
            CallableStatement stmt = conn.prepareCall(sql);
            ResultSet rs = stmt.executeQuery();
        ) {
            while (rs.next()) {
                Tasks task = new Tasks(
                    rs.getInt("task_id"),
                    rs.getString("task_name"),
                    rs.getString("task_desc")
                );
                list.add(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(Tasks task) {
        String sql = "{CALL InsertTask(?, ?)}";
        
        try (
            Connection conn = OpenConnection.getConnection();
            CallableStatement ptmt = conn.prepareCall(sql);
        ) {
            ptmt.setString(1, task.getTask_name());
            ptmt.setString(2, task.getTask_desc());

            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Tasks task) {
        String sql = "{CALL UpdateTask(?, ?, ?)}";
        
        try (
            Connection conn = OpenConnection.getConnection();
            CallableStatement ptmt = conn.prepareCall(sql);
        ) { ptmt.setInt(1, task.getTask_id());
            ptmt.setString(2, task.getTask_name());
            ptmt.setString(3, task.getTask_desc());
            
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int task_id) {
        String sql = "{CALL DeleteTask(?)}";
        
        try (
            Connection conn = OpenConnection.getConnection();
            CallableStatement ptmt = conn.prepareCall(sql);
        ) {
            ptmt.setInt(1, task_id);
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Tasks findById(int task_id) {
        String sql = "{CALL FindTaskByID(?)}";
        
        try (
            Connection conn = OpenConnection.getConnection();
            CallableStatement ptmt = conn.prepareCall(sql);
        ) {
            ptmt.setInt(1, task_id);
            try (ResultSet rs = ptmt.executeQuery()) {
                if (rs.next()) {
                    return new Tasks(
                        rs.getInt("task_id"),
                        rs.getString("task_name"),
                        rs.getString("task_desc")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

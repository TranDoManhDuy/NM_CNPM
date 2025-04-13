/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package DAO;

import DatabaseHelper.OpenConnection;
import Model.Position;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author eramisme
 */
public class PositionDAO implements InterfaceDAO.InterfaceDAO<Position> {
public static PositionDAO getInstance() {
        return new PositionDAO();
    }
    @Override 
    public ArrayList<Position> getList() {
        ArrayList<Position> list_position = new ArrayList<>();
        String sql = "EXEC getlist_position";

        try (
            Connection conn = OpenConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(sql);
        ) {
            while (result.next()) {
                int position_id = result.getInt("position_id");
                String position_name = result.getString("position_name");                            
                Position pst = new Position(position_id, position_name);
                list_position.add(pst);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list_position;
    }

    @Override 
    public boolean insert(Position pst) {
        String sql = "EXEC insert_position @position_name = ? ";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setString(1, pst.getPositionName());          
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override 
    public boolean update(Position pst) {
        String sql = "EXEC update_position @position_name = ?, position_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {          
            ptmt.setString(1, pst.getPositionName());
            ptmt.setInt(2, pst.getPositionId());
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    

    @Override
    public Position findbyID(int id) {
        String sql = "EXEC findbyID_position @position_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, id);
            ResultSet result = ptmt.executeQuery();
            
            if (result.next()) {
                int position_id = result.getInt("position_id");
                String position_name = result.getString("position_name");

                
                return new Position(position_id, position_name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
   
    @Override 
    // Xóa nhân viên
    public boolean delete(int id) {
        String sql = "EXEC delete_position @position_id = ?";
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
    
    public int getPositionIdByName(String positionName) {
        String sql = "SELECT position_id FROM position WHERE position_name = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setString(1, positionName);
            ResultSet rs = ptmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("position_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // Trả về 0 nếu không tìm thấy
    }
    
    public String getPositionNameById(int positionId) {
        String sql = "SELECT position_name FROM position WHERE position_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, positionId);
            ResultSet rs = ptmt.executeQuery();
            if (rs.next()) {
                return rs.getString("position_name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Không rõ";
    }


    
}

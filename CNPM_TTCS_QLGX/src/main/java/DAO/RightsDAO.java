package DAO;

import Model.Rights;
import java.util.ArrayList;
import DatabaseHelper.OpenConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class RightsDAO {
    public static RightsDAO getInstance() {
        return new RightsDAO();
    }

    public ArrayList<Rights> getListRights() {
        ArrayList<Rights> list_rights = new ArrayList<>();
        String sql = "SELECT * FROM rights";
        try (
                Connection conn = OpenConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet result = stmt.executeQuery(sql);
        ) {
            while (result.next()) {
                int right_id = result.getInt("right_id");
                String right_name = result.getString("right_name");
                String right_desc = result.getString("right_desc");
                
                list_rights.add(new Rights(right_id, right_name, right_desc));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list_rights;
    }
    
    public boolean insert(Rights right) {
        String sql = "INSERT INTO rights (right_name, right_desc) VALUES (?, ?)";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setString(1, right.getRightName());
            ptmt.setString(2, right.getRightDesc());
            
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean update(Rights right) {
        String sql = "UPDATE rights SET right_name = ?, right_desc = ? WHERE right_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setString(1, right.getRightName());
            ptmt.setString(2, right.getRightDesc());
            ptmt.setInt(3, right.getRightId());
            
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Rights findById(int right_id) {
        String sql = "SELECT * FROM rights WHERE right_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, right_id);
            ResultSet rs = ptmt.executeQuery();
            
            if (rs.next()) {
                return new Rights(
                    rs.getInt("right_id"),
                    rs.getString("right_name"),
                    rs.getString("right_desc")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean delete(int right_id) {
        String sql = "DELETE FROM rights WHERE right_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, right_id);
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

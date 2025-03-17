/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Buildings;
import DatabaseHelper.OpenConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author HP
 */
public class BuildingsDAO {
    public static BuildingsDAO getInstance(){
        return new BuildingsDAO();
    }
    public List<Buildings> getAllBuildings(){
        List<Buildings> listBuildings = new ArrayList<>();
        String sql = "SELECT * FROM buildings";
        
        try (
            Connection conn = OpenConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
        ){
            while(rs.next()){
                Buildings bd = new Buildings(rs.getInt("building_id"),
                rs.getString("building_name"),
                rs.getString("address"));
                listBuildings.add(bd);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return listBuildings;
    }
    
    public boolean insert(Buildings bd){
        String sql = "INSERT INTO buildings (building_name, address) VALUES (?, ?)";
        try(
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ){
            ptmt.setString(1, bd.getBuilding_name());
            ptmt.setString(2, bd.getAddress());
            
            return ptmt.executeUpdate() > 0;
        }catch (Exception e) {
            e.printStackTrace();
        }
    return false;
    }
    
    public boolean update(Buildings bd){
        String sql = " UPDATE buildings SET building_name = ?, address = ? WHERE building_id = ?";
        try(
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ){
            ptmt.setInt(3, bd.getBuilding_id());
            ptmt.setString(1, bd.getBuilding_name());
            ptmt.setString(2, bd.getAddress());
            
            return ptmt.executeUpdate() > 0;
        }catch (Exception e) {
            e.printStackTrace();
        }
    return false;
    }
    
    public boolean delete(int building_id){
        String sql = "DELETE FROM buildings WHERE building_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, building_id);
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public Buildings findByID( int building_id){
        String sql = "SELECT * FROM buildings WHERE building_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, building_id);
            try (ResultSet rs = ptmt.executeQuery()) {
                if (rs.next()) {
                    return new Buildings(
                        rs.getInt("building_id"),
                        rs.getString("building_name"),
                        rs.getString("address")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

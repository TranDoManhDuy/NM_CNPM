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
        String sql = "{CALL GetAllBuildings()}";
        
        try (
            Connection conn = OpenConnection.getConnection();
            CallableStatement stmt = conn.prepareCall(sql);
            ResultSet rs = stmt.executeQuery();
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
    
    public String insert(Buildings bd){
        String sql = "{CALL InsertBuilding(?, ?)}";
        try(
            Connection conn = OpenConnection.getConnection();
            CallableStatement ptmt = conn.prepareCall(sql);
        ){
            ptmt.setString(1, bd.getBuilding_name());
            ptmt.setString(2, bd.getAddress());
            ptmt.executeUpdate();
        }catch (SQLException e) {
            if(e.getErrorCode() == 50000){
                return e.getMessage();
            }
            if(e.getErrorCode() == 229){
                return "Không có quyền thực hiện thao tác";   
            }
            else{
                return "Thêm thất bại "+e.getErrorCode();
                    }
        }
        return "Thêm thành công";
    }
    
    public String update(Buildings bd){
        String sql = "{CALL UpdateBuilding(?, ?, ?)}";
        try(
            Connection conn = OpenConnection.getConnection();
            CallableStatement ptmt = conn.prepareCall(sql);
        ){
            ptmt.setInt(1, bd.getBuilding_id());
            ptmt.setString(2, bd.getBuilding_name());
            ptmt.setString(3, bd.getAddress());
            
            ptmt.executeUpdate();
        }catch (SQLException e) {
            if(e.getErrorCode() == 50000){
                return e.getMessage();
            }
            if(e.getErrorCode() == 229){
                return "Không có quyền thực hiện thao tác";   
            }
            else{
                return "cập nhật thất bại";
                    }
        }
        return "Cập nhật thành công";
    }
    
    public String delete(int building_id){
        String sql = "{CALL DeleteBuilding(?)}";
        try (
            Connection conn = OpenConnection.getConnection();
            CallableStatement ptmt = conn.prepareCall(sql);
        ) {
            ptmt.setInt(1, building_id);
            ptmt.executeUpdate();
        } catch (SQLException e) {
            if(e.getErrorCode() == 50000){
                return e.getMessage();
            }
            if(e.getErrorCode() == 229){
                return "Không có quyền thực hiện thao tác";   
            }
            else{
                return "xóa thất bại" + e.getErrorCode();
                    }
        }
        return "Xóa thành công";
    }
    public Buildings findByID( int building_id){
        String sql = "{CALL FindBuildingByID(?)}";
        try (
            Connection conn = OpenConnection.getConnection();
            CallableStatement ptmt = conn.prepareCall(sql);
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

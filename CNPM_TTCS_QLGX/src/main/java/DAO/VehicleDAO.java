package DAO;

import DatabaseHelper.OpenConnection;
import InterfaceDAO.InterfaceDAO;
import Model.Vehicle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VehicleDAO implements InterfaceDAO<Vehicle> {
    public static VehicleDAO getInstance() {
        return new VehicleDAO();
    }

    @Override
    public ArrayList<Vehicle> getList() {
        ArrayList<Vehicle> lstVehicle = new ArrayList<>();
        String sql = "EXEC GET_ALL_VEHICLES";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {
                int vehicle_id = rs.getInt("vehicle_id");
                String identification_code = rs.getString("identification_code");
                int vehicle_type_id = rs.getInt("vehicle_type_id");
                String vehicle_name = rs.getString("vehicle_name");
                String vehicle_color = rs.getString("vehicle_color");
                
                Vehicle vehicle = new Vehicle(vehicle_id, identification_code, vehicle_type_id, vehicle_name, vehicle_color);
                lstVehicle.add(vehicle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstVehicle;
    }
    
    public Map<String, ArrayList<?>> getAllData() {
        ArrayList<Vehicle> lstVehicle = new ArrayList<>();
        ArrayList<String> lstVehicle_type_name = new ArrayList<>();
        
        String sql = "EXEC GET_ALL_VEHICLES";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {
                int vehicle_id = rs.getInt("vehicle_id");
                String identification_code = rs.getString("identification_code");
                int vehicle_type_id = rs.getInt("vehicle_type_id");
                String vehicle_name = rs.getString("vehicle_name");
                String vehicle_color = rs.getString("vehicle_color");
                String vehicle_type_name = rs.getString("vehicle_type_name");
                
                Vehicle vehicle = new Vehicle(vehicle_id, identification_code, vehicle_type_id, vehicle_name, vehicle_color);
                lstVehicle.add(vehicle);
                lstVehicle_type_name.add(vehicle_type_name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, ArrayList<?>> result = new HashMap<>();
        result.put("vehicles", lstVehicle);
        result.put("vehicle_type_names", lstVehicle_type_name);
        return result;
    }

    @Override
    public boolean insert(Vehicle vehicle) {
        String sql = "EXEC INSERT_VEHICLE @identification_code = ?, @vehicle_type_id = ?, @vehicle_name = ?, @vehicle_color = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, vehicle.getIdentification_code());
            ps.setInt(2, vehicle.getVehicle_type_id());
            ps.setString(3, vehicle.getVehicle_name());
            ps.setString(4, vehicle.getVehicle_color());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Vehicle vehicle) {
        String sql = "EXEC UPDATE_VEHICLE @identification_code = ?, @vehicle_type_id = ?, @vehicle_name = ?, @vehicle_color = ?, @vehicle_id = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, vehicle.getIdentification_code());
            ps.setInt(2, vehicle.getVehicle_type_id());
            ps.setString(3, vehicle.getVehicle_name());
            ps.setString(4, vehicle.getVehicle_color());
            ps.setInt(5, vehicle.getVehicle_id());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "EXEC DELETE_VEHICLE @vehicle_id = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Vehicle findbyID(int id) {
        String sql = "EXEC GET_VEHICLE_BY_ID @vehicle_id = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int vehicle_id = rs.getInt("vehicle_id");
                String identification_code = rs.getString("identification_code");
                int vehicle_type_id = rs.getInt("vehicle_type_id");
                String vehicle_name = rs.getString("vehicle_name");
                String vehicle_color = rs.getString("vehicle_color");
                
                return new Vehicle(vehicle_id, identification_code, vehicle_type_id, vehicle_name, vehicle_color);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }  
}

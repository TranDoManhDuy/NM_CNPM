package DAO;

import Model.Accounts;
import java.util.ArrayList;
import DatabaseHelper.OpenConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AccountsDAO {
    public static AccountsDAO getInstance() {
        return new AccountsDAO();
    }

    public ArrayList<Accounts> getListAccounts() {
        ArrayList<Accounts> list_accounts = new ArrayList<>();
        String sql = "SELECT * FROM accounts";
        try (
                Connection conn = OpenConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet result = stmt.executeQuery(sql);
        ) {
            while (result.next()) {
                int account_number = result.getInt("account_number");
                String password = result.getString("password");
                boolean is_active = result.getBoolean("is_active");
                String staff_id = result.getString("staff_id");
                
                list_accounts.add(new Accounts(account_number, password, is_active, staff_id));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list_accounts;
    }
    
    public boolean insert(Accounts acc) {
        String sql = "INSERT INTO accounts (account_number, password, is_active, staff_id) VALUES (?, ?, ?, ?)";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, acc.getAccountNumber());
            ptmt.setString(2, acc.getPassword());
            ptmt.setBoolean(3, acc.isActive());
            ptmt.setString(4, acc.getStaffId());
            
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean update(Accounts acc) {
        String sql = "UPDATE accounts SET password = ?, is_active = ?, staff_id = ? WHERE account_number = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setString(1, acc.getPassword());
            ptmt.setBoolean(2, acc.isActive());
            ptmt.setString(3, acc.getStaffId());
            ptmt.setInt(4, acc.getAccountNumber());
            
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Accounts findById(int account_number) {
        String sql = "SELECT * FROM accounts WHERE account_number = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, account_number);
            ResultSet rs = ptmt.executeQuery();
            
            if (rs.next()) {
                Accounts acc = new Accounts();
                acc.setAccountNumber(rs.getInt("account_number"));
                acc.setPassword(rs.getString("password"));
                acc.setActive(rs.getBoolean("is_active"));
                acc.setStaffId(rs.getString("staff_id"));
                
                return acc;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean delete(int account_number) {
        String sql = "DELETE FROM accounts WHERE account_number = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, account_number);
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

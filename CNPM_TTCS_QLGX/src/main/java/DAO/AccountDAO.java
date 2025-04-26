package DAO;

import Model.Account;
import java.util.ArrayList;
import DatabaseHelper.OpenConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AccountDAO implements InterfaceDAO.InterfaceDAO<Account> {
    public static AccountDAO getInstance() {
        return new AccountDAO();
    }
    @Override
    public ArrayList<Account> getList() {
        ArrayList<Account> list_accounts = new ArrayList<>();
        String sql = "EXEC getlist_account";
        try (
                Connection conn = OpenConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet result = stmt.executeQuery(sql);
        ) {
            while (result.next()) {
                int account_number = result.getInt("account_number");
//                String password = result.getString("password");
                boolean is_active = result.getBoolean("is_active");
                int role_id = result.getInt("role_id");
                
                list_accounts.add(new Account(account_number, is_active, role_id));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list_accounts;
    }
    @Override
    public boolean insert(Account acc) {
        String sql = "EXEC insert_account @account_number = ?, @password = ?, @is_active = ?, @role_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, acc.getAccountNumber());
            ptmt.setString(2, acc.getPassword());
            ptmt.setBoolean(3, acc.isActive());
            ptmt.setInt(4, acc.getRoleId());
            
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean update(Account acc) {
        String sql = "EXEC update_account @is_active = ?, @role_id = ?, @account_number = ?" ;
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            
            ptmt.setBoolean(1, acc.isActive());
            ptmt.setInt(2, acc.getRoleId());
            ptmt.setInt(3, acc.getAccountNumber());
            
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public Account findbyID(int id) {
        String sql = "EXEC findbyAN_account @account_number = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, id);
            ResultSet rs = ptmt.executeQuery();
            
            if (rs.next()) {
                int account_number = rs.getInt("account_number");
                String password = rs.getString("password");
                boolean is_active = rs.getBoolean("is_active");
                int role_id = rs.getInt("role_id");
                
                return new Account(account_number, password, is_active, role_id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public boolean delete(int id) {
        String sql = "EXEC delete_account @account_number = ?";
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
    
    public boolean isAccountNumberExists(int accountNumber) {
        String sql = "SELECT 1 FROM accounts WHERE account_number = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, accountNumber);
            ResultSet rs = ptmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public String getRoleNameByAccount(int accountNumber) {
        String sql = "SELECT r.role_name FROM accounts a JOIN roles r ON a.role_id = r.role_id WHERE a.account_number = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, accountNumber);
            ResultSet rs = ptmt.executeQuery();
            if (rs.next()) {
                return rs.getString("role_name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int getMaxAccountNumber() {
    int max = 0;
    try (
        Connection conn = OpenConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT MAX(account_number) AS max_acc FROM accounts");
    ) {
        if (rs.next()) {
            max = rs.getInt("max_acc");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return max;
}
    
    public String getFullNameByAccountNumber(int accountNumber) {
    String sql = "SELECT full_name FROM staff WHERE account_number = ?";
    try (Connection conn = OpenConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, accountNumber);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getString("full_name");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return "Không rõ";
}



    
}

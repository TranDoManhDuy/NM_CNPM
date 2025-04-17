/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Global;

import Model.Staff;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
/**
 *
 * @author manhh
 */
public class Global_variable {
    public static String position = "staff";  // manager
    public static Staff currentLogin;
    public static void getCurrentLogin(int account_id) throws ClassNotFoundException, SQLException{
        String sql = "{CALL findStaffByAccountID(?)}";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionURL = "jdbc:sqlserver://localhost;database=VINHOMES; encrypt=true;trustServerCertificate=true;";
        String username = "check_login";
        String password = "123";
        Connection conn = DriverManager.getConnection(connectionURL, username, password);
        try(
            CallableStatement cst = conn.prepareCall(sql);
        ){
            cst.setInt(1, account_id);
            ResultSet rs = cst.executeQuery();
            int staff_id = rs.getInt("staff_id");
            int role_id = rs.getInt("role_id");
            String full_name = rs.getString("full_name");
            String ssn = rs.getString("ssn");
            LocalDate date_of_birth = rs.getDate("date_of_birth").toLocalDate();
            String gender = rs.getString("gender");
            String phone_number = rs.getString("phone_number");
            String address = rs.getString("address");
            String email = rs.getString("email");
            boolean is_active = rs.getBoolean("is_active");
            int position_id = rs.getInt("position_id");
            int account_number = rs.getInt("account_number"); 
                
            currentLogin = new Staff(staff_id, role_id, full_name, ssn, date_of_birth, gender, phone_number, address, email, is_active, position_id, account_number);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseHelper;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author manhh
 */
public class OpenConnection {
    public static Connection getConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionURL =  "jdbc:sqlserver://localhost;database=TEST_VINHOMEPARKING;encrypt=true;trustServerCertificate=true;";
        String username = "sa";
        String password = "123";
        Connection conn = DriverManager.getConnection(connectionURL, username, password);
        return conn;
    }
    public static void main(String[] args) {
        try {
            Connection con = getConnection();
            System.out.println("Connect successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
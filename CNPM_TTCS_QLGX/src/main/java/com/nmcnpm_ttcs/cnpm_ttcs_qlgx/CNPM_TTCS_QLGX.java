/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.nmcnpm_ttcs.cnpm_ttcs_qlgx;

import static DatabaseHelper.OpenConnection.initializaConnection;
import GUI.Login;
import GUI.ViewMain;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
/**
 * @author manhh
 */
public class CNPM_TTCS_QLGX {
    public static ArrayList<String> getUserPassManager(Connection conn) {
        String sql = "EXEC getLoginServerManager";
        ArrayList<String> userpass = new ArrayList<>();
        try (
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ResultSet rs = ptmt.executeQuery();
            if (rs.next()) {
                userpass.add(rs.getString("login"));
                userpass.add(rs.getString("password"));
            }
            else {
                System.out.println("đăng nhập không thành công");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userpass;
    }
    public static ArrayList<String> getUserPassStaff(Connection conn) {
        String sql = "EXEC getLoginServerStaff";
        ArrayList<String> userpass = new ArrayList<>();
        try (
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ResultSet rs = ptmt.executeQuery();
            if (rs.next()) {
                userpass.add(rs.getString("login"));
                userpass.add(rs.getString("password"));
            }
            else {
                System.out.println("đăng nhập không thành công");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userpass;
    }
    
    public static void login() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionURL = "jdbc:sqlserver://localhost;database=VINHOMES; encrypt=true;trustServerCertificate=true;";
        String username = "check_login";
        String password = "123";
        Connection conn = DriverManager.getConnection(connectionURL, username, password);
        
        GUI.Login login = new Login(conn) {
            @Override
            public void ConnectSuccessful(ArrayList<String> info) {
                String user;
                String password;
                ArrayList<String> userPassword = new ArrayList<>();
                if (info.get(8).equals("1")) {
                    userPassword = getUserPassManager(conn);
                    user = userPassword.get(0);
                    password = userPassword.get(1);
                }
                else {
                    userPassword = getUserPassStaff(conn);
                    user = userPassword.get(0);
                    password = userPassword.get(1);
                }
                
                // info
                
                initializaConnection(user, password);
                ViewMain app = new ViewMain();
                app.setLocationRelativeTo(null);
                app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                app.setVisible(true);
                this.setVisible(false);
            }
        };
        login.setLocationRelativeTo(null);
        login.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        login.setVisible(true);
    }
    public static void main(String[] args) throws ClassNotFoundException {
        try {
            login();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
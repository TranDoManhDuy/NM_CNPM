/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.nmcnpm_ttcs.cnpm_ttcs_qlgx;

import static DatabaseHelper.OpenConnection.initializaConnection;
import GUI.Login;
import GUI.ViewMain;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
/**
 * @author manhh
 */
public class CNPM_TTCS_QLGX {
    public static void login() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionURL = "jdbc:sqlserver://localhost;database=VINHOMES; encrypt=true;trustServerCertificate=true;";
        String username = "check_login";
        String password = "123";
        Connection conn = DriverManager.getConnection(connectionURL, username, password);
        
        GUI.Login login = new Login(conn) {
            @Override
            public void ConnectSuccessful(ArrayList<String> info) {
                String user = info.get(10);
                String password = info.get(11);
                initializaConnection(user, password);
                ViewMain app = new ViewMain(user);
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
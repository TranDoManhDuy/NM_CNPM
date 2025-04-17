/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.nmcnpm_ttcs.cnpm_ttcs_qlgx;

import static DatabaseHelper.OpenConnection.initializaConnection;
import GUI.Login;
import GUI.ViewMain;
import java.awt.Font;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;
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
        setUIFont(new Font("Arial", Font.PLAIN, 14));
        try {
            login();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void setUIFont(Font font) {
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof Font) {
                UIManager.put(key, font);
            }
        }
    }
}
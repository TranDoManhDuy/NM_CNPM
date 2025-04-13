/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.nmcnpm_ttcs.cnpm_ttcs_qlgx;

import static DatabaseHelper.OpenConnection.initializaConnection;
import GUI.ViewMain;
import javax.swing.JFrame;

/**
 *
 * @author manhh
 */
public class CNPM_TTCS_QLGX {
    public static void main(String[] args) {
        initializaConnection("sa", "123");
        ViewMain app = new ViewMain();
//        LogSelection app = new LogSelection();
        app.setLocationRelativeTo(null);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }
}
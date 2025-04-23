/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.DICHVU;

import Annotation.LogConfirm;
import Annotation.LogMessage;
import DAO.TimeFrameDAO;
import DatabaseHelper.OpenConnection;
import GUI.ViewMain;
import Global.DataGlobal;
import Model.SessionFee;
import Model.TimeFrameToRender;
import java.awt.Frame;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

/**
 *
 * @author manhh
 */
public class gui_timeframe extends javax.swing.JPanel {
    private JPanel containerPanel;
    private ViewMain viewmain;
    private LogConfirm logConfirm;
    private LogMessage logMessage;
    private DataGlobal dataglobal;
    
    private boolean cursorBreak = false;
    /**
     * Creates new form gui_timeframe
     */
    public gui_timeframe(ViewMain viewMain, LogConfirm logConfirm, LogMessage logMessage, DataGlobal dataglobal) {
        this.viewmain = viewMain;
        this.logConfirm = logConfirm;
        this.logMessage = logMessage;
        this.dataglobal = dataglobal;
        
        initComponents();
        scroll_table.getVerticalScrollBar().setUnitIncrement(20);
        txt_ngaybanhanh_rendermain.setText(String.valueOf(LocalDate.now()));
        containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        dataglobal.updateArrTimeFrameToRender();
        fillTable();
        scroll_table.setViewportView(containerPanel);
        
        combo_gioBatdau1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"00" ,"01", "02", "03", "04", "05", "06", "07", "08", "09", "10"
        , "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
        combo_gioBatdau2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"00" ,"01", "02", "03", "04", "05", "06", "07", "08", "09", "10"
        , "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
        combo_gioBatdau3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"00" ,"01", "02", "03", "04", "05", "06", "07", "08", "09", "10"
        , "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
        
        combo_gioKetthuc1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"00" ,"01", "02", "03", "04", "05", "06", "07", "08", "09", "10"
        , "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
        combo_gioKetthuc2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"00" ,"01", "02", "03", "04", "05", "06", "07", "08", "09", "10"
        , "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
        combo_gioKetthuc3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"00" ,"01", "02", "03", "04", "05", "06", "07", "08", "09", "10"
        , "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
        
        String[] minutes = new String[60];
        for (int i = 0; i < 60; i++) {
            minutes[i] = String.format("%02d", i);
        }
        
        combo_phutBatdau1.setModel(new javax.swing.DefaultComboBoxModel<>(minutes));
        combo_phutBatdau2.setModel(new javax.swing.DefaultComboBoxModel<>(minutes));
        combo_phutBatdau3.setModel(new javax.swing.DefaultComboBoxModel<>(minutes));
        
        combo_phutKetthuc1.setModel(new javax.swing.DefaultComboBoxModel<>(minutes));
        combo_phutKetthuc2.setModel(new javax.swing.DefaultComboBoxModel<>(minutes));
        combo_phutKetthuc3.setModel(new javax.swing.DefaultComboBoxModel<>(minutes));
    }
    public void fillTable() {
        containerPanel.removeAll();
        for (TimeFrameToRender tf : this.dataglobal.getArrTimeFrameToRender()) {
            LocalDate decision_date = tf.getDecision_date();
            LocalTime TS1 = tf.getTS1();
            LocalTime TS2 = tf.getTS2();
            LocalTime TS3 = tf.getTS3();
            LocalTime TE1 = tf.getTE1();
            LocalTime TE2 = tf.getTE2();
            LocalTime TE3 = tf.getTE3();
            boolean isActive = tf.isIsActive();
            
            gui_timeframe_detail timeframeDetail_gui = new gui_timeframe_detail(decision_date, TS1, TS2, TS3, TE1, TE2, TE3, isActive){
                @Override
                public void EventClick_btnXoa() {
                    int id_delete = Integer.min(tf.getT1_id(), Integer.max(tf.getT2_id(), tf.getT3_id()));
                    deleteHandle(id_delete);
                    
                    
                    
                }
                @Override
                public void EventClick_btnChinhSua() {
                    txt_ngaybanhanh_rendermain.setText(String.valueOf(decision_date));
                    
                    combo_gioBatdau1.setSelectedItem(String.valueOf(TS1.getHour()).length() < 2 ? "0" + String.valueOf(TS1.getHour()): String.valueOf(TS1.getHour()));
                    combo_gioBatdau2.setSelectedItem(String.valueOf(TS2.getHour()).length() < 2 ? "0" + String.valueOf(TS2.getHour()): String.valueOf(TS2.getHour()));
                    combo_gioBatdau3.setSelectedItem(String.valueOf(TS3.getHour()).length() < 2 ? "0" + String.valueOf(TS3.getHour()): String.valueOf(TS3.getHour()));
                            
                    combo_gioKetthuc1.setSelectedItem(String.valueOf(TE1.getHour()).length() < 2 ? "0" + String.valueOf(TE1.getHour()): String.valueOf(TE1.getHour()));
                    combo_gioKetthuc2.setSelectedItem(String.valueOf(TE2.getHour()).length() < 2 ? "0" + String.valueOf(TE2.getHour()): String.valueOf(TE2.getHour()));
                    combo_gioKetthuc3.setSelectedItem(String.valueOf(TE3.getHour()).length() < 2 ? "0" + String.valueOf(TE3.getHour()): String.valueOf(TE3.getHour()));
                    
                    combo_phutBatdau1.setSelectedItem(String.valueOf(TS1.getMinute()).length() < 2 ? "0" + String.valueOf(TS1.getMinute()): String.valueOf(TS1.getMinute()));
                    combo_phutBatdau2.setSelectedItem(String.valueOf(TS2.getMinute()).length() < 2 ? "0" + String.valueOf(TS2.getMinute()): String.valueOf(TS2.getMinute()));
                    combo_phutBatdau3.setSelectedItem(String.valueOf(TS3.getMinute()).length() < 2 ? "0" + String.valueOf(TS3.getMinute()): String.valueOf(TS3.getMinute()));
                    
                    combo_phutKetthuc1.setSelectedItem(String.valueOf(TE1.getMinute()).length() < 2 ? "0" + String.valueOf(TE1.getMinute()): String.valueOf(TE1.getMinute()));
                    combo_phutKetthuc2.setSelectedItem(String.valueOf(TE2.getMinute()).length() < 2 ? "0" + String.valueOf(TE2.getMinute()): String.valueOf(TE2.getMinute()));
                    combo_phutKetthuc3.setSelectedItem(String.valueOf(TE3.getMinute()).length() < 2 ? "0" + String.valueOf(TE3.getMinute()): String.valueOf(TE3.getMinute()));
                }
            };
            
            containerPanel.add(timeframeDetail_gui);
            txt_tinnhan.setText("Đang hiển thị tất cả các khung thời gian");
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        frame = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        scroll_table = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txt_ngaybanhanh_rendermain = new javax.swing.JTextField();
        btn_them = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        combo_gioBatdau1 = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        combo_phutBatdau1 = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        combo_gioKetthuc1 = new javax.swing.JComboBox<>();
        combo_phutKetthuc1 = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        combo_gioKetthuc2 = new javax.swing.JComboBox<>();
        combo_gioBatdau2 = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        combo_phutKetthuc2 = new javax.swing.JComboBox<>();
        combo_phutBatdau2 = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        combo_gioKetthuc3 = new javax.swing.JComboBox<>();
        combo_gioBatdau3 = new javax.swing.JComboBox<>();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        combo_phutKetthuc3 = new javax.swing.JComboBox<>();
        combo_phutBatdau3 = new javax.swing.JComboBox<>();
        btn_datlai = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btn_conhieuluc = new javax.swing.JButton();
        btn_hethieuluc = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        btn_tatca = new javax.swing.JButton();
        txt_tinnhan = new javax.swing.JTextField();
        btn_tailai = new javax.swing.JButton();

        frame.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel1.setText("Khung thời gian thứ 1");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Bắt đầu");

        jLabel3.setText("Kết thúc");

        jLabel4.setText("Khung thời gian thứ 2");

        jLabel5.setText("Bắt đầu");

        jLabel6.setText("Kết thúc");

        jLabel7.setText("Khung thời gian thứ 3");

        jLabel8.setText("Bắt đầu");

        jLabel9.setText("Kết thúc");

        javax.swing.GroupLayout frameLayout = new javax.swing.GroupLayout(frame);
        frame.setLayout(frameLayout);
        frameLayout.setHorizontalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameLayout.createSequentialGroup()
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7)))
                    .addGroup(frameLayout.createSequentialGroup()
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(frameLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, frameLayout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(frameLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(frameLayout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(44, 44, 44)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(frameLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(frameLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField4))
                            .addGroup(frameLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField6)))))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        frameLayout.setVerticalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        setBackground(new java.awt.Color(204, 255, 255));

        scroll_table.setAlignmentY(1.0F);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroll_table)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroll_table, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel10.setText("Khung thời gian thứ 1");

        jLabel11.setText("Bắt đầu");

        jLabel12.setText("Kết thúc");

        jLabel13.setText("Khung thời gian thứ 2");

        jLabel14.setText("Bắt đầu");

        jLabel16.setText("Khung thời gian thứ 3");

        jLabel19.setText("Ngày ban hành");

        txt_ngaybanhanh_rendermain.setFocusable(false);
        txt_ngaybanhanh_rendermain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ngaybanhanh_rendermainActionPerformed(evt);
            }
        });

        btn_them.setText("Thêm");
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        jLabel17.setText("Giờ");

        combo_gioBatdau1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel18.setText("Phút");

        combo_phutBatdau1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel21.setText("Giờ");

        jLabel22.setText("Phút");

        combo_gioKetthuc1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        combo_phutKetthuc1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel23.setText("Kết thúc");

        jLabel32.setText("Bắt đầu");

        jLabel33.setText("Kết thúc");

        jLabel34.setText("Giờ");

        jLabel35.setText("Giờ");

        combo_gioKetthuc2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        combo_gioBatdau2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel36.setText("Phút");

        jLabel37.setText("Phút");

        combo_phutKetthuc2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        combo_phutBatdau2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel38.setText("Giờ");

        jLabel39.setText("Giờ");

        combo_gioKetthuc3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        combo_gioBatdau3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel40.setText("Phút");

        jLabel41.setText("Phút");

        combo_phutKetthuc3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        combo_phutBatdau3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btn_datlai.setText("Đặt lại");
        btn_datlai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_datlaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel10)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel14))
                                .addGap(38, 38, 38)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel34)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(combo_gioBatdau2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel35)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(combo_gioKetthuc2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel36)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(combo_phutBatdau2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel37)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(combo_phutKetthuc2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(208, 208, 208))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12))
                                .addGap(36, 36, 36)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel17)
                                            .addComponent(jLabel15))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(combo_gioBatdau1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel21)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(combo_gioKetthuc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(combo_phutBatdau1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel22)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(combo_phutKetthuc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel33)
                                    .addComponent(jLabel32))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel38)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(combo_gioBatdau3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel39)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(combo_gioKetthuc3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel40)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(combo_phutBatdau3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel41)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(combo_phutKetthuc3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btn_them)
                                .addGap(164, 164, 164)
                                .addComponent(btn_datlai))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_ngaybanhanh_rendermain, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txt_ngaybanhanh_rendermain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel15)
                    .addComponent(jLabel17)
                    .addComponent(combo_gioBatdau1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(combo_phutBatdau1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22)
                    .addComponent(combo_gioKetthuc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_phutKetthuc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jLabel13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel23))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(combo_gioBatdau2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36)
                            .addComponent(combo_phutBatdau2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(jLabel37)
                            .addComponent(combo_gioKetthuc2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(combo_phutKetthuc2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(25, 25, 25)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(combo_gioBatdau3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40)
                    .addComponent(combo_phutBatdau3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(jLabel41)
                    .addComponent(combo_gioKetthuc3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_phutKetthuc3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_them)
                    .addComponent(btn_datlai))
                .addGap(24, 24, 24))
        );

        btn_conhieuluc.setText("Còn hiệu lực");
        btn_conhieuluc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_conhieulucActionPerformed(evt);
            }
        });

        btn_hethieuluc.setText("Hết hiệu lực");
        btn_hethieuluc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hethieulucActionPerformed(evt);
            }
        });

        jLabel20.setText("Danh sách:");

        btn_tatca.setText("Tất cả");
        btn_tatca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tatcaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_conhieuluc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_hethieuluc)
                .addGap(9, 9, 9)
                .addComponent(btn_tatca)
                .addContainerGap(267, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_conhieuluc)
                    .addComponent(btn_hethieuluc)
                    .addComponent(jLabel20)
                    .addComponent(btn_tatca))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        txt_tinnhan.setText("Đang hiển thị danh sách còn hiệu lực");
        txt_tinnhan.setEnabled(false);
        txt_tinnhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tinnhanActionPerformed(evt);
            }
        });

        btn_tailai.setText("Tải lại");
        btn_tailai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tailaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txt_tinnhan, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_tailai))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_tinnhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_tailai))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    private void deleteHandle(int index) {
        this.viewmain.setEnabled(false);
        this.cursorBreak = false;
        
        for (SessionFee ss : dataglobal.getArrSessionFees()) {
            if (ss.getTime_frame_id() == index ||
                    ss.getTime_frame_id() == index + 1 ||
                    ss.getTime_frame_id() == index + 2
                    ){
                logError("Đã có giá lượt trong khung thời gian này, không thể xóa");
                return;
            } 
        }
        
        this.logConfirm = new LogConfirm("Bạn có chắc là muốn cập nhật?") {
            @Override
            public void action() {
                cursorBreak = true;
                this.setVisible(false);
                viewmain.setEnabled(true);
                viewmain.requestFocus();
            }

            @Override
            public void reject() {
                cursorBreak = false;
                this.setVisible(false);
                viewmain.setEnabled(true);
                viewmain.requestFocus();
            }
        };

        this.logConfirm.setVisible(true);

        // Dùng SwingWorker để kiểm tra hộp thoại mà không làm treo UI
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                while (logConfirm.isVisible()) { // Chờ đến khi hộp thoại đóng
                    Thread.sleep(100);
                }
                return null;
            }

            @Override
            protected void done() {
                if (!cursorBreak) {
                    return;
                }
                String rs = TimeFrameDAO.getInstance().delete(index);
                viewmain.setEnabled(false);
                logMessage = new LogMessage(rs) {
                    @Override
                    public void action() {
                        this.setVisible(false);
                        viewmain.setEnabled(true);
                        viewmain.requestFocus();
                    }
                };
                dataglobal.updateArrTimeFrameToRender();
                fillTable();
                revalidate();
                repaint();
                logMessage.setVisible(true);
            }
        };
        worker.execute();
        worker = null;
    }
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void btn_conhieulucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_conhieulucActionPerformed
        // TODO add your handling code here:
        containerPanel.removeAll();
        for (TimeFrameToRender tf : this.dataglobal.getArrTimeFrameToRender()) {
            LocalDate decision_date = tf.getDecision_date();
            LocalTime TS1 = tf.getTS1();
            LocalTime TS2 = tf.getTS2();
            LocalTime TS3 = tf.getTS3();
            LocalTime TE1 = tf.getTE1();
            LocalTime TE2 = tf.getTE2();
            LocalTime TE3 = tf.getTE3();
            boolean isActive = tf.isIsActive();
            if (!isActive) {
                continue;
            }
            gui_timeframe_detail timeframeDetail_gui = new gui_timeframe_detail(decision_date, TS1, TS2, TS3, TE1, TE2, TE3, isActive){
                @Override
                public void EventClick_btnXoa() {
                    int id_delete = Integer.min(tf.getT1_id(), Integer.max(tf.getT2_id(), tf.getT3_id()));
                    deleteHandle(id_delete);
                }
                
                @Override
                public void EventClick_btnChinhSua() {
                    txt_ngaybanhanh_rendermain.setText(String.valueOf(decision_date));
                    
                    combo_gioBatdau1.setSelectedItem(String.valueOf(TS1.getHour()).length() < 2 ? "0" + String.valueOf(TS1.getHour()): String.valueOf(TS1.getHour()));
                    combo_gioBatdau2.setSelectedItem(String.valueOf(TS2.getHour()).length() < 2 ? "0" + String.valueOf(TS2.getHour()): String.valueOf(TS2.getHour()));
                    combo_gioBatdau3.setSelectedItem(String.valueOf(TS3.getHour()).length() < 2 ? "0" + String.valueOf(TS3.getHour()): String.valueOf(TS3.getHour()));
                            
                    combo_gioKetthuc1.setSelectedItem(String.valueOf(TE1.getHour()).length() < 2 ? "0" + String.valueOf(TE1.getHour()): String.valueOf(TE1.getHour()));
                    combo_gioKetthuc2.setSelectedItem(String.valueOf(TE2.getHour()).length() < 2 ? "0" + String.valueOf(TE2.getHour()): String.valueOf(TE2.getHour()));
                    combo_gioKetthuc3.setSelectedItem(String.valueOf(TE3.getHour()).length() < 2 ? "0" + String.valueOf(TE3.getHour()): String.valueOf(TE3.getHour()));
                    
                    combo_phutBatdau1.setSelectedItem(String.valueOf(TS1.getMinute()).length() < 2 ? "0" + String.valueOf(TS1.getMinute()): String.valueOf(TS1.getMinute()));
                    combo_phutBatdau2.setSelectedItem(String.valueOf(TS2.getMinute()).length() < 2 ? "0" + String.valueOf(TS2.getMinute()): String.valueOf(TS2.getMinute()));
                    combo_phutBatdau3.setSelectedItem(String.valueOf(TS3.getMinute()).length() < 2 ? "0" + String.valueOf(TS3.getMinute()): String.valueOf(TS3.getMinute()));
                    
                    combo_phutKetthuc1.setSelectedItem(String.valueOf(TE1.getMinute()).length() < 2 ? "0" + String.valueOf(TE1.getMinute()): String.valueOf(TE1.getMinute()));
                    combo_phutKetthuc2.setSelectedItem(String.valueOf(TE2.getMinute()).length() < 2 ? "0" + String.valueOf(TE2.getMinute()): String.valueOf(TE2.getMinute()));
                    combo_phutKetthuc3.setSelectedItem(String.valueOf(TE3.getMinute()).length() < 2 ? "0" + String.valueOf(TE3.getMinute()): String.valueOf(TE3.getMinute()));
                }
            };
            containerPanel.add(timeframeDetail_gui);
        }
        txt_tinnhan.setText("Đang hiển thị tất cả các khung thời gian còn hiệu lực");
        this.revalidate();
        this.repaint();
    }//GEN-LAST:event_btn_conhieulucActionPerformed

    private void btn_hethieulucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hethieulucActionPerformed
        // TODO add your handling code here:
        containerPanel.removeAll();
        for (TimeFrameToRender tf : this.dataglobal.getArrTimeFrameToRender()) {
            LocalDate decision_date = tf.getDecision_date();
            LocalTime TS1 = tf.getTS1();
            LocalTime TS2 = tf.getTS2();
            LocalTime TS3 = tf.getTS3();
            LocalTime TE1 = tf.getTE1();
            LocalTime TE2 = tf.getTE2();
            LocalTime TE3 = tf.getTE3();
            boolean isActive = tf.isIsActive();
            if (isActive) {
                continue;
            }
            gui_timeframe_detail timeframeDetail_gui = new gui_timeframe_detail(decision_date, TS1, TS2, TS3, TE1, TE2, TE3, isActive){
                @Override
                public void EventClick_btnXoa() {
                    int id_delete = Integer.min(tf.getT1_id(), Integer.max(tf.getT2_id(), tf.getT3_id()));
                    deleteHandle(id_delete);
                }
                @Override
                public void EventClick_btnChinhSua() {
                    txt_ngaybanhanh_rendermain.setText(String.valueOf(decision_date));
                    
                    combo_gioBatdau1.setSelectedItem(String.valueOf(TS1.getHour()).length() < 2 ? "0" + String.valueOf(TS1.getHour()): String.valueOf(TS1.getHour()));
                    combo_gioBatdau2.setSelectedItem(String.valueOf(TS2.getHour()).length() < 2 ? "0" + String.valueOf(TS2.getHour()): String.valueOf(TS2.getHour()));
                    combo_gioBatdau3.setSelectedItem(String.valueOf(TS3.getHour()).length() < 2 ? "0" + String.valueOf(TS3.getHour()): String.valueOf(TS3.getHour()));
                            
                    combo_gioKetthuc1.setSelectedItem(String.valueOf(TE1.getHour()).length() < 2 ? "0" + String.valueOf(TE1.getHour()): String.valueOf(TE1.getHour()));
                    combo_gioKetthuc2.setSelectedItem(String.valueOf(TE2.getHour()).length() < 2 ? "0" + String.valueOf(TE2.getHour()): String.valueOf(TE2.getHour()));
                    combo_gioKetthuc3.setSelectedItem(String.valueOf(TE3.getHour()).length() < 2 ? "0" + String.valueOf(TE3.getHour()): String.valueOf(TE3.getHour()));
                    
                    combo_phutBatdau1.setSelectedItem(String.valueOf(TS1.getMinute()).length() < 2 ? "0" + String.valueOf(TS1.getMinute()): String.valueOf(TS1.getMinute()));
                    combo_phutBatdau2.setSelectedItem(String.valueOf(TS2.getMinute()).length() < 2 ? "0" + String.valueOf(TS2.getMinute()): String.valueOf(TS2.getMinute()));
                    combo_phutBatdau3.setSelectedItem(String.valueOf(TS3.getMinute()).length() < 2 ? "0" + String.valueOf(TS3.getMinute()): String.valueOf(TS3.getMinute()));
                    
                    combo_phutKetthuc1.setSelectedItem(String.valueOf(TE1.getMinute()).length() < 2 ? "0" + String.valueOf(TE1.getMinute()): String.valueOf(TE1.getMinute()));
                    combo_phutKetthuc2.setSelectedItem(String.valueOf(TE2.getMinute()).length() < 2 ? "0" + String.valueOf(TE2.getMinute()): String.valueOf(TE2.getMinute()));
                    combo_phutKetthuc3.setSelectedItem(String.valueOf(TE3.getMinute()).length() < 2 ? "0" + String.valueOf(TE3.getMinute()): String.valueOf(TE3.getMinute()));
                }
            };
            
            containerPanel.add(timeframeDetail_gui);
        }
        txt_tinnhan.setText("Đang hiển thị tất cả các khung thời gian đã hết hiệu lực");
        this.revalidate();
        this.repaint();
    }//GEN-LAST:event_btn_hethieulucActionPerformed

    private void txt_tinnhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tinnhanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tinnhanActionPerformed

    private void txt_ngaybanhanh_rendermainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ngaybanhanh_rendermainActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ngaybanhanh_rendermainActionPerformed

    private void btn_datlaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_datlaiActionPerformed
        // TODO add your handling code here:
        combo_gioBatdau1.setSelectedIndex(0);
        combo_gioBatdau2.setSelectedIndex(0);
        combo_gioBatdau3.setSelectedIndex(0);
        
        combo_gioKetthuc1.setSelectedIndex(0);
        combo_gioKetthuc2.setSelectedIndex(0);
        combo_gioKetthuc3.setSelectedIndex(0);
        
        combo_phutBatdau1.setSelectedIndex(0);
        combo_phutBatdau2.setSelectedIndex(0);
        combo_phutBatdau3.setSelectedIndex(0);
        
        combo_phutKetthuc1.setSelectedIndex(0);
        combo_phutKetthuc2.setSelectedIndex(0);
        combo_phutKetthuc3.setSelectedIndex(0);
    }//GEN-LAST:event_btn_datlaiActionPerformed

    private void btn_tatcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tatcaActionPerformed
        // TODO add your handling code here:
        this.dataglobal.updateArrTimeFrameToRender();
        fillTable();
        this.revalidate();
        this.repaint();
    }//GEN-LAST:event_btn_tatcaActionPerformed

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        // TODO add your handling code here:
        String giobatdau1 = (String )combo_gioBatdau1.getSelectedItem();
        String giobatdau2 = (String )combo_gioBatdau2.getSelectedItem();
        String giobatdau3 = (String )combo_gioBatdau3.getSelectedItem();
        
        String gioketthuc1 = (String )combo_gioKetthuc1.getSelectedItem();
        String gioketthuc2 = (String )combo_gioKetthuc2.getSelectedItem();
        String gioketthuc3 = (String )combo_gioKetthuc3.getSelectedItem();
        
        String phutbatdau1 = (String)combo_phutBatdau1.getSelectedItem();
        String phutbatdau2 = (String)combo_phutBatdau2.getSelectedItem();
        String phutbatdau3 = (String)combo_phutBatdau3.getSelectedItem();
        
        String phutketthuc1 = (String)combo_phutKetthuc1.getSelectedItem();
        String phutketthuc2 = (String)combo_phutKetthuc2.getSelectedItem();
        String phutketthuc3 = (String)combo_phutKetthuc3.getSelectedItem();
        
        LocalDate decision_date = LocalDate.now();
        
        LocalTime start1 = LocalTime.of(Integer.parseInt(giobatdau1), Integer.parseInt(phutbatdau1));
        LocalTime end1 = LocalTime.of(Integer.parseInt(gioketthuc1), Integer.parseInt(phutketthuc1));
        long duration_1 = Duration.between(start1, end1).toMinutes();
        
        LocalTime start2 = LocalTime.of(Integer.parseInt(giobatdau2), Integer.parseInt(phutbatdau2));
        LocalTime end2 = LocalTime.of(Integer.parseInt(gioketthuc2), Integer.parseInt(phutketthuc2));
        long duration_2 = Duration.between(start2, end2).toMinutes();
        
        LocalTime start3 = LocalTime.of(Integer.parseInt(giobatdau3), Integer.parseInt(phutbatdau3));
        LocalTime end3 = LocalTime.of(Integer.parseInt(gioketthuc3), Integer.parseInt(phutketthuc3));
        long duration_3 = Duration.between(start3, end3).toMinutes();
        
        if (duration_1 + duration_2 + duration_3 <= 1436) {
            logError("Tổng thời gian phải đủ 24h");
            return;
        }
        
        String rs = TimeFrameDAO.getInstance().insert_timeframe_container(decision_date, start1, start2, start3, end1, end2, end3, true);
        
        viewmain.setEnabled(false);
        logMessage = new LogMessage(rs) {
            @Override
            public void action() {
                this.setVisible(false);
                viewmain.setEnabled(true);
                viewmain.requestFocus();
            }
        };
        this.dataglobal.updateArrTimeFrameToRender();
        fillTable();
        revalidate();
        repaint();
        logMessage.setVisible(true);
    }//GEN-LAST:event_btn_themActionPerformed
    private void logError(String rs) {
        this.viewmain.setEnabled(false);
        this.logMessage = new LogMessage(rs) {
            @Override
            public void action() {
                this.setVisible(false);
                viewmain.setEnabled(true);
                viewmain.requestFocus();
            }
        };
        this.logMessage.setVisible(true);
    }
    private void btn_tailaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tailaiActionPerformed
        // TODO add your handling code here:
        this.dataglobal.updateArrTimeFrameToRender();
        fillTable();
    }//GEN-LAST:event_btn_tailaiActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_conhieuluc;
    private javax.swing.JButton btn_datlai;
    private javax.swing.JButton btn_hethieuluc;
    private javax.swing.JButton btn_tailai;
    private javax.swing.JButton btn_tatca;
    private javax.swing.JButton btn_them;
    private javax.swing.JComboBox<String> combo_gioBatdau1;
    private javax.swing.JComboBox<String> combo_gioBatdau2;
    private javax.swing.JComboBox<String> combo_gioBatdau3;
    private javax.swing.JComboBox<String> combo_gioKetthuc1;
    private javax.swing.JComboBox<String> combo_gioKetthuc2;
    private javax.swing.JComboBox<String> combo_gioKetthuc3;
    private javax.swing.JComboBox<String> combo_phutBatdau1;
    private javax.swing.JComboBox<String> combo_phutBatdau2;
    private javax.swing.JComboBox<String> combo_phutBatdau3;
    private javax.swing.JComboBox<String> combo_phutKetthuc1;
    private javax.swing.JComboBox<String> combo_phutKetthuc2;
    private javax.swing.JComboBox<String> combo_phutKetthuc3;
    private javax.swing.JPanel frame;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JScrollPane scroll_table;
    private javax.swing.JTextField txt_ngaybanhanh_rendermain;
    private javax.swing.JTextField txt_tinnhan;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.GUIXE;

import DAO.ParkingSessionDAO;
import GUI.ViewMain;
import Model.ParkingSession;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class EntryAndExit extends javax.swing.JPanel {
    private ViewMain viewmain;
    private DefaultTableModel tblModel = new DefaultTableModel(){
        @Override 
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private ArrayList<ParkingSession> parking_sessions;
    private String[] sDay, sMonth, sYear;
    private String[] s1Day, s1Month, s1Year;
    private boolean isUpdating = false;
    
    /**
     * Creates new form EntryAndExit
     */
    public EntryAndExit(ViewMain viewmain) {
        initComponents();
        this.viewmain = viewmain;
        resetFields();
        initTable();
        loadData();
        fillTable();
        sDay = Library.Library.getDay(0, 0);
        sMonth = Library.Library.getMonth(0, 0);
        sYear = Library.Library.getYear(0, 0);
        s1Day = Library.Library.getDay(0, 0);
        s1Month = Library.Library.getMonth(0, 0);
        s1Year = Library.Library.getYear(0, 0);
        
        cob_ngay_bat_dau.setModel(new javax.swing.DefaultComboBoxModel<>(sDay));
        cob_ngay_ket_thuc.setModel(new javax.swing.DefaultComboBoxModel<>(s1Day));
        cob_thang_bat_dau.setModel(new javax.swing.DefaultComboBoxModel<>(sMonth));
        cob_thang_ket_thuc.setModel(new javax.swing.DefaultComboBoxModel<>(s1Month));
        cob_nam_bat_dau.setModel(new javax.swing.DefaultComboBoxModel<>(sYear));
        cob_nam_ket_thuc.setModel(new javax.swing.DefaultComboBoxModel<>(s1Year));
        addComboBoxListeners();
    }
    
    private void checkDayMonthYearSort() {
        boolean isDateSelected =    !cob_ngay_bat_dau.getSelectedItem().toString().equals("0") &&
                                    !cob_ngay_ket_thuc.getSelectedItem().toString().equals("0") &&
                                    !cob_thang_bat_dau.getSelectedItem().toString().equals("0") &&
                                    !cob_thang_ket_thuc.getSelectedItem().toString().equals("0") &&
                                    !cob_nam_bat_dau.getSelectedItem().toString().equals("0") &&
                                    !cob_nam_ket_thuc.getSelectedItem().toString().equals("0");
        btn_loc.setEnabled(isDateSelected);
    }
    
    private void addComboBoxListeners() {
        ActionListener comboListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkDayMonthYearSort();
            }
        };
        cob_ngay_bat_dau.addActionListener(comboListener);
        cob_thang_bat_dau.addActionListener(comboListener);
        cob_nam_bat_dau.addActionListener(comboListener);
        cob_ngay_ket_thuc.addActionListener(comboListener);
        cob_thang_ket_thuc.addActionListener(comboListener);
        cob_nam_ket_thuc.addActionListener(comboListener);
    }
    
    public void initTable() { 
        String[] header = new String[] {"Mã Gửi Xe", "Thời Gian Vào", "Thời Gian Ra", "Giá Tiền"};
        tblModel.setColumnIdentifiers(header);
        tblModel.setRowCount(0);
        tbl_parking_session.setModel(tblModel);
    }
    
    private void loadData() {
        try {
            this.parking_sessions = ParkingSessionDAO.getInstance().getList();
        }
        catch (Exception e) { 
            e.printStackTrace();
        }
    }
    
    private void fillTable() {
        int count = -1;
        for (ParkingSession par: this.parking_sessions) { 
            count += 1;
            String dt_start = "null";
            String dt_end = "null";
            
            dt_start =  String.valueOf(par.getCheck_in_time().toLocalDate()) + " " + 
                        String.valueOf(par.getCheck_in_time().toLocalTime());
            
            if (par.getCheck_out_time() != null ){ 
                dt_end =    String.valueOf(par.getCheck_out_time().toLocalDate()) + " " +
                        String.valueOf(par.getCheck_out_time().toLocalTime());
            }
            tblModel.addRow(new String[] {  String.valueOf(par.getParking_session_id()),
                                            dt_start, dt_end,
                                            String.valueOf(par.getAmount())
            });
        }
        tblModel.fireTableDataChanged();
    }  
    
    private void resetBtn() { 
        btn_loc.setEnabled(false);
        btn_bo_loc.setEnabled(false);
    }
    
    private void resetFields() { 
        txt_parking_session_id.setText("");
        txt_check_in_time.setText("");
        txt_check_out_time.setText("");
        txt_amount.setText("");
        txt_gui_xe.setText("");
        txt_xe_vao.setText("");
        txt_xe_ra.setText("");
        tbl_parking_session.clearSelection();
        this.resetBtn();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        sc_pariking_session = new javax.swing.JScrollPane();
        tbl_parking_session = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        btn_loc = new javax.swing.JButton();
        btn_bo_loc = new javax.swing.JButton();
        cob_ngay_bat_dau = new javax.swing.JComboBox<>();
        cob_thang_bat_dau = new javax.swing.JComboBox<>();
        cob_nam_bat_dau = new javax.swing.JComboBox<>();
        cob_ngay_ket_thuc = new javax.swing.JComboBox<>();
        cob_thang_ket_thuc = new javax.swing.JComboBox<>();
        cob_nam_ket_thuc = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        txt_tin_nhan = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btn_reset = new javax.swing.JButton();
        txt_parking_session_id = new javax.swing.JTextField();
        txt_check_in_time = new javax.swing.JTextField();
        txt_check_out_time = new javax.swing.JTextField();
        txt_amount = new javax.swing.JTextField();
        txt_gui_xe = new javax.swing.JTextField();
        txt_xe_vao = new javax.swing.JTextField();
        txt_xe_ra = new javax.swing.JTextField();
        txt_tong_tien = new javax.swing.JTextField();
        jL_title = new javax.swing.JLabel();
        jL_parking_session_id = new javax.swing.JLabel();
        jL_title1 = new javax.swing.JLabel();
        jL_parking_session_id1 = new javax.swing.JLabel();
        jL_parking_session_id2 = new javax.swing.JLabel();
        jL_parking_session_id3 = new javax.swing.JLabel();
        jL_parking_session_id4 = new javax.swing.JLabel();
        jL_parking_session_id5 = new javax.swing.JLabel();
        jL_parking_session_id6 = new javax.swing.JLabel();
        jL_parking_session_id7 = new javax.swing.JLabel();
        jL_parking_session_id8 = new javax.swing.JLabel();
        jL_parking_session_id9 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 255, 255));
        setForeground(new java.awt.Color(204, 255, 255));

        tbl_parking_session.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_parking_session.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Gửi Xe", "Thời Gian Vào", "Thời Gian Ra", "Giá Tiền"
            }
        ));
        tbl_parking_session.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_parking_sessionMouseClicked(evt);
            }
        });
        sc_pariking_session.setViewportView(tbl_parking_session);

        btn_loc.setText("Lọc");
        btn_loc.setRequestFocusEnabled(false);
        btn_loc.setRolloverEnabled(false);
        btn_loc.setVerifyInputWhenFocusTarget(false);
        btn_loc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_locActionPerformed(evt);
            }
        });

        btn_bo_loc.setText("Bỏ lọc");
        btn_bo_loc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bo_locActionPerformed(evt);
            }
        });

        cob_ngay_bat_dau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cob_ngay_bat_dau.setMinimumSize(new java.awt.Dimension(100, 22));
        cob_ngay_bat_dau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cob_ngay_bat_dauActionPerformed(evt);
            }
        });

        cob_thang_bat_dau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cob_thang_bat_dau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cob_thang_bat_dauActionPerformed(evt);
            }
        });

        cob_nam_bat_dau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cob_nam_bat_dau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cob_nam_bat_dauActionPerformed(evt);
            }
        });

        cob_ngay_ket_thuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cob_ngay_ket_thuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cob_ngay_ket_thucActionPerformed(evt);
            }
        });

        cob_thang_ket_thuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cob_thang_ket_thuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cob_thang_ket_thucActionPerformed(evt);
            }
        });

        cob_nam_ket_thuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cob_nam_ket_thuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cob_nam_ket_thucActionPerformed(evt);
            }
        });

        jLabel34.setText("Thời Gian: Từ");

        jLabel35.setText("Ngày");

        jLabel36.setText("Tháng");

        jLabel37.setText("Năm");

        jLabel38.setText("Đến");

        jLabel39.setText("Ngày");

        jLabel40.setText("Tháng");

        jLabel41.setText("Năm");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cob_ngay_bat_dau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cob_thang_bat_dau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(cob_nam_bat_dau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel38))
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cob_ngay_ket_thuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cob_thang_ket_thuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cob_nam_ket_thuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_bo_loc, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                    .addComponent(btn_loc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(jLabel36)
                    .addComponent(jLabel37)
                    .addComponent(jLabel39)
                    .addComponent(jLabel40)
                    .addComponent(jLabel41)
                    .addComponent(btn_bo_loc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cob_ngay_bat_dau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(cob_thang_bat_dau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cob_nam_bat_dau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38)
                    .addComponent(cob_ngay_ket_thuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cob_thang_ket_thuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cob_nam_ket_thuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_loc))
                .addGap(14, 14, 14))
        );

        txt_tin_nhan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_tin_nhan.setText("Đang hiển thị danh sách tất cả các lượt gửi xe");
        txt_tin_nhan.setEnabled(false);
        txt_tin_nhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tin_nhanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sc_pariking_session)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_tin_nhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_tin_nhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sc_pariking_session, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setRequestFocusEnabled(false);
        jPanel2.setVerifyInputWhenFocusTarget(false);

        btn_reset.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_reset.setText("Tải Lại");
        btn_reset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_resetMouseClicked(evt);
            }
        });
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });

        txt_parking_session_id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_parking_session_id.setEnabled(false);

        txt_check_in_time.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_check_in_time.setEnabled(false);

        txt_check_out_time.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_check_out_time.setEnabled(false);

        txt_amount.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_amount.setEnabled(false);

        txt_gui_xe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_gui_xe.setEnabled(false);

        txt_xe_vao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_xe_vao.setEnabled(false);

        txt_xe_ra.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_xe_ra.setEnabled(false);

        txt_tong_tien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_tong_tien.setEnabled(false);

        jL_title.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jL_title.setText("Thông Tin Gửi Xe");

        jL_parking_session_id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jL_parking_session_id.setText("Mã Gửi Xe:");

        jL_title1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jL_title1.setText("Thông Tin Thống Kê");

        jL_parking_session_id1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jL_parking_session_id1.setText("Thời Gian Vào:");

        jL_parking_session_id2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jL_parking_session_id2.setText("Thời Gian Ra:");

        jL_parking_session_id3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jL_parking_session_id3.setText("Giá Tiền:");

        jL_parking_session_id4.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jL_parking_session_id4.setText("Tổng số lượt");

        jL_parking_session_id5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jL_parking_session_id5.setText("Gửi Xe");

        jL_parking_session_id6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jL_parking_session_id6.setText("Xe Vào");

        jL_parking_session_id7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jL_parking_session_id7.setText("Xe Ra");

        jL_parking_session_id8.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jL_parking_session_id8.setText("Tổng số tiền");

        jL_parking_session_id9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jL_parking_session_id9.setText("Đã Thanh Toán");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jL_parking_session_id8, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jL_parking_session_id9, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_tong_tien, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jL_title, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)
                                .addComponent(btn_reset))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jL_parking_session_id, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_parking_session_id, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jL_title1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jL_parking_session_id1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_check_in_time, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jL_parking_session_id2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_check_out_time, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jL_parking_session_id3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jL_parking_session_id4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jL_parking_session_id5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_gui_xe, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jL_parking_session_id6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_xe_vao, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jL_parking_session_id7, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_xe_ra, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jL_title)
                    .addComponent(btn_reset))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jL_parking_session_id)
                    .addComponent(txt_parking_session_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jL_parking_session_id1)
                    .addComponent(txt_check_in_time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jL_parking_session_id2)
                    .addComponent(txt_check_out_time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jL_parking_session_id3)
                    .addComponent(txt_amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jL_title1)
                .addGap(18, 18, 18)
                .addComponent(jL_parking_session_id4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jL_parking_session_id5)
                    .addComponent(txt_gui_xe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jL_parking_session_id6)
                    .addComponent(txt_xe_vao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jL_parking_session_id7)
                    .addComponent(txt_xe_ra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jL_parking_session_id8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jL_parking_session_id9)
                    .addComponent(txt_tong_tien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_parking_sessionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_parking_sessionMouseClicked
        // TODO add your handling code here:
        int selectedRow = tbl_parking_session.getSelectedRow();
        // Kiểm tra xem có hàng nào được chọn không
        if (selectedRow != -1) {
            // Lấy dữ liệu từ bảng và gán vào các biến
            int parkingSessionId = Integer.parseInt(tbl_parking_session.getValueAt(selectedRow, 0).toString());
            String checkInTime = tbl_parking_session.getValueAt(selectedRow, 1).toString();
            String checkOutTime = tbl_parking_session.getValueAt(selectedRow, 2).toString();
            int amount = Integer.parseInt(tbl_parking_session.getValueAt(selectedRow, 3).toString());
            
            // Hiển thị dữ liệu lên các ô nhập liệu
            txt_parking_session_id.setText(String.valueOf(parkingSessionId));
            txt_check_in_time.setText(checkInTime);
            txt_check_out_time.setText(checkOutTime); 
            txt_amount.setText(String.valueOf(amount));
        }
    }//GEN-LAST:event_tbl_parking_sessionMouseClicked

    private void btn_locActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_locActionPerformed
        // TODO add your handling code here:
        int countNull = 0;
        int countVao = 0;
        int countRa = 0;
        int Samount = 0;
        initTable();
        int ngay_bat_dau = Integer.parseInt(cob_ngay_bat_dau.getSelectedItem().toString());
        int ngay_ket_thuc = Integer.parseInt(cob_ngay_ket_thuc.getSelectedItem().toString());
        int thang_bat_dau = Integer.parseInt(cob_thang_bat_dau.getSelectedItem().toString());
        int thang_ket_thuc = Integer.parseInt(cob_thang_ket_thuc.getSelectedItem().toString());
        int nam_bat_dau = Integer.parseInt(cob_nam_bat_dau.getSelectedItem().toString());
        int nam_ket_thuc = Integer.parseInt(cob_nam_ket_thuc.getSelectedItem().toString());
        
        if (nam_bat_dau >= 50) nam_bat_dau += 1900;
        else nam_bat_dau += 2000;
        
        if (nam_ket_thuc >= 50) nam_ket_thuc += 1900;
        else nam_ket_thuc += 2000;
        
        LocalDate dateStart = LocalDate.of(nam_bat_dau, thang_bat_dau, ngay_bat_dau);
        LocalDate dateEnd = LocalDate.of(nam_ket_thuc, thang_ket_thuc, ngay_ket_thuc);
        
        int count = -1;
        LocalDate dateStartPs;
        LocalDate dateEndPs;
        for (ParkingSession par: parking_sessions) { 
            
            count += 1;
            dateStartPs = par.getCheck_in_time().toLocalDate();
            dateEndPs = par.getCheck_out_time() != null ? par.getCheck_out_time().toLocalDate() : null;
//            System.out.println(dateEndPs + " " + dateStart);
            if (
                    (dateEndPs != null &&
                        (dateStart.isBefore(dateStartPs) || dateStart.equals(dateStartPs)) &&
                        (dateEnd.isAfter(dateEndPs) || dateEnd.equals(dateEndPs))
                    ) || 
                    (   
                        dateEndPs == null &&
                        (dateStart.isBefore(dateStartPs) || dateStart.equals(dateStartPs)) &&
                        (dateEnd.isAfter(LocalDate.now()) || dateEnd.equals(LocalDate.now()))
                    )
                )
            {
                countVao += 1;
                String dt_start = "null";
                String dt_end = "null";

                dt_start =  String.valueOf(par.getCheck_in_time().toLocalDate()) + " " + 
                            String.valueOf(par.getCheck_in_time().toLocalTime());

                if (par.getCheck_out_time() != null ){ 
                    dt_end =    String.valueOf(par.getCheck_out_time().toLocalDate()) + " " +
                            String.valueOf(par.getCheck_out_time().toLocalTime());
                    Samount += par.getAmount();
                }
                else { 
                    countNull += 1;
                }
                
                tblModel.addRow(new String[] {  String.valueOf(par.getParking_session_id()),
                                                dt_start, dt_end,
                                                String.valueOf(par.getAmount())
                }); 
            }
            else if (
                    (
                        dateEndPs != null &&
                        !(dateStart.isBefore(dateStartPs) || dateStart.equals(dateStartPs)) &&
                        (dateStart.isBefore(dateEndPs) || dateStart.equals(dateEndPs)) &&
                        (dateEnd.isAfter(dateEndPs) || dateEnd.equals(dateEndPs))
                    )
                )
            {
                countRa += 1;
                String dt_start = "null";
                String dt_end = "null";

                dt_start =  String.valueOf(par.getCheck_in_time().toLocalDate()) + " " + 
                            String.valueOf(par.getCheck_in_time().toLocalTime());

                if (par.getCheck_out_time() != null ){ 
                    dt_end =    String.valueOf(par.getCheck_out_time().toLocalDate()) + " " +
                            String.valueOf(par.getCheck_out_time().toLocalTime());
                    Samount += par.getAmount();
                }
                else { 
                    countNull += 1;
                }
                
                tblModel.addRow(new String[] {  String.valueOf(par.getParking_session_id()),
                                                "--", dt_end,
                                                String.valueOf(par.getAmount())
                }); 
            }
        }
        tblModel.fireTableDataChanged();
        resetFields();
        txt_xe_vao.setText(String.valueOf(countVao));
        txt_xe_ra.setText(String.valueOf(countVao - countNull + countRa));
        txt_gui_xe.setText(String.valueOf(countNull));
        txt_tong_tien.setText(String.valueOf(Samount));
        btn_loc.setEnabled(false);
        btn_bo_loc.setEnabled(true);
        
    }//GEN-LAST:event_btn_locActionPerformed

    private void btn_bo_locActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bo_locActionPerformed
        // TODO add your handling code here:
        initTable();
        fillTable();
        resetBtn();
        resetFields();
    }//GEN-LAST:event_btn_bo_locActionPerformed

    private void cob_ngay_bat_dauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cob_ngay_bat_dauActionPerformed
        // TODO add your handling code here:
        if (isUpdating) return;
        isUpdating = true;
        
        int day = Integer.parseInt(cob_ngay_bat_dau.getSelectedItem().toString());
        int month = Integer.parseInt(cob_thang_bat_dau.getSelectedItem().toString());
        int year = Integer.parseInt(cob_nam_bat_dau.getSelectedItem().toString());
        
        sMonth = Library.Library.getMonth(day, year);
        sYear = Library.Library.getYear(day, month);
        cob_thang_bat_dau.setModel(new javax.swing.DefaultComboBoxModel<>(sMonth));
        cob_nam_bat_dau.setModel(new javax.swing.DefaultComboBoxModel<>(sYear));
        
        int monthIndex = Arrays.asList(sMonth).indexOf(String.format("%02d", month));
        int yearIndex = Arrays.asList(sYear).indexOf(String.format("%02d", year));
        
        if (monthIndex == -1 || yearIndex == -1) {
            cob_thang_bat_dau.setSelectedIndex(0);
            cob_nam_bat_dau.setSelectedIndex(0);
        }
        else {
            cob_thang_bat_dau.setSelectedIndex(monthIndex);
            cob_nam_bat_dau.setSelectedIndex(yearIndex);
        }
        
        isUpdating = false;
    }//GEN-LAST:event_cob_ngay_bat_dauActionPerformed

    private void cob_thang_bat_dauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cob_thang_bat_dauActionPerformed
        // TODO add your handling code here:
        if (isUpdating) return;
        isUpdating = true;
        
        int day = Integer.parseInt(cob_ngay_bat_dau.getSelectedItem().toString());
        int month = Integer.parseInt(cob_thang_bat_dau.getSelectedItem().toString());
        int year = Integer.parseInt(cob_nam_bat_dau.getSelectedItem().toString());
        
        sDay = Library.Library.getDay(month, year);
        sYear = Library.Library.getYear(day, month);
        
//        System.out.println(day + " " + month + " " + year);
        cob_ngay_bat_dau.setModel(new javax.swing.DefaultComboBoxModel<>(sDay));
        cob_nam_bat_dau.setModel(new javax.swing.DefaultComboBoxModel<>(sYear));
        
        int dayIndex = Arrays.asList(sDay).indexOf(String.format("%02d", day));
        int yearIndex = Arrays.asList(sYear).indexOf(String.format("%02d", year));
        
        if (dayIndex == -1 || yearIndex == -1) {
            cob_ngay_bat_dau.setSelectedIndex(0);
            cob_nam_bat_dau.setSelectedIndex(0);
        }
        else {
            cob_ngay_bat_dau.setSelectedIndex(dayIndex);
            cob_nam_bat_dau.setSelectedIndex(yearIndex);
        }
        isUpdating = false;
    }//GEN-LAST:event_cob_thang_bat_dauActionPerformed

    private void cob_nam_bat_dauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cob_nam_bat_dauActionPerformed
        // TODO add your handling code here:
        if (isUpdating) return;
        isUpdating = true;
        int day = Integer.parseInt(cob_ngay_bat_dau.getSelectedItem().toString());
        int month = Integer.parseInt(cob_thang_bat_dau.getSelectedItem().toString());
        int year = Integer.parseInt(cob_nam_bat_dau.getSelectedItem().toString());
//        System.out.println(day + " " + month + " " + year);
        
        sDay = Library.Library.getDay(month, year);
        sMonth = Library.Library.getMonth(day, year);
        
        cob_ngay_bat_dau.setModel(new javax.swing.DefaultComboBoxModel<>(sDay));
        cob_thang_bat_dau.setModel(new javax.swing.DefaultComboBoxModel<>(sMonth));
        
        int dayIndex = Arrays.asList(sDay).indexOf(String.format("%02d", day));
        int monthIndex = Arrays.asList(sMonth).indexOf(String.format("%02d", month));
        
        if (monthIndex == -1 || dayIndex == -1) {
            cob_ngay_bat_dau.setSelectedIndex(0);
            cob_thang_bat_dau.setSelectedIndex(0);
        }
        else {
            cob_ngay_bat_dau.setSelectedIndex(dayIndex);
            cob_thang_bat_dau.setSelectedIndex(monthIndex);
        }
        isUpdating = false;
    }//GEN-LAST:event_cob_nam_bat_dauActionPerformed

    private void cob_ngay_ket_thucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cob_ngay_ket_thucActionPerformed
        // TODO add your handling code here:
        if (isUpdating) return;
        isUpdating = true;
        int day = Integer.parseInt(cob_ngay_ket_thuc.getSelectedItem().toString());
        int month = Integer.parseInt(cob_thang_ket_thuc.getSelectedItem().toString());
        int year = Integer.parseInt(cob_nam_ket_thuc.getSelectedItem().toString());
//        System.out.println(day + " " + month + " " + year);
        
        s1Month = Library.Library.getMonth(day, year);
        s1Year = Library.Library.getYear(day, month);
        cob_thang_ket_thuc.setModel(new javax.swing.DefaultComboBoxModel<>(s1Month));
        cob_nam_ket_thuc.setModel(new javax.swing.DefaultComboBoxModel<>(s1Year));
        
        int monthIndex = Arrays.asList(s1Month).indexOf(String.format("%02d", month));
        int yearIndex = Arrays.asList(s1Year).indexOf(String.format("%02d", year));
        
        if (monthIndex == -1 || yearIndex == -1) {
            cob_thang_ket_thuc.setSelectedIndex(0);
            cob_nam_ket_thuc.setSelectedIndex(0);
        }
        else {
            cob_thang_ket_thuc.setSelectedIndex(monthIndex);
            cob_nam_ket_thuc.setSelectedIndex(yearIndex);
        }
        isUpdating = false;
    }//GEN-LAST:event_cob_ngay_ket_thucActionPerformed

    private void cob_thang_ket_thucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cob_thang_ket_thucActionPerformed
        // TODO add your handling code here:
        if (isUpdating) return;
        isUpdating = true;
        int day = Integer.parseInt(cob_ngay_ket_thuc.getSelectedItem().toString());
        int month = Integer.parseInt(cob_thang_ket_thuc.getSelectedItem().toString());
        int year = Integer.parseInt(cob_nam_ket_thuc.getSelectedItem().toString());
        
        s1Day = Library.Library.getDay(month, year);
        s1Year = Library.Library.getYear(day, month);
        
//        System.out.println(day + " " + month + " " + year);
        cob_ngay_ket_thuc.setModel(new javax.swing.DefaultComboBoxModel<>(s1Day));
        cob_nam_ket_thuc.setModel(new javax.swing.DefaultComboBoxModel<>(s1Year));
        
        int dayIndex = Arrays.asList(s1Day).indexOf(String.format("%02d", day));
        int yearIndex = Arrays.asList(s1Year).indexOf(String.format("%02d", year));
        
        if (dayIndex == -1 || yearIndex == -1) {
            cob_ngay_ket_thuc.setSelectedIndex(0);
            cob_nam_ket_thuc.setSelectedIndex(0);
        }
        else {
            cob_ngay_ket_thuc.setSelectedIndex(dayIndex);
            cob_nam_ket_thuc.setSelectedIndex(yearIndex);
        }
        isUpdating = false;
    }//GEN-LAST:event_cob_thang_ket_thucActionPerformed

    private void cob_nam_ket_thucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cob_nam_ket_thucActionPerformed
        // TODO add your handling code here:
        if (isUpdating) return;
        isUpdating = true;
        int day = Integer.parseInt(cob_ngay_ket_thuc.getSelectedItem().toString());
        int month = Integer.parseInt(cob_thang_ket_thuc.getSelectedItem().toString());
        int year = Integer.parseInt(cob_nam_ket_thuc.getSelectedItem().toString());
//        System.out.println(day + " " + month + " " + year);
        
        s1Day = Library.Library.getDay(month, year);
        s1Month = Library.Library.getMonth(day, year);
        
        cob_ngay_ket_thuc.setModel(new javax.swing.DefaultComboBoxModel<>(s1Day));
        cob_thang_ket_thuc.setModel(new javax.swing.DefaultComboBoxModel<>(s1Month));
        
        int dayIndex = Arrays.asList(s1Day).indexOf(String.format("%02d", day));
        int monthIndex = Arrays.asList(s1Month).indexOf(String.format("%02d", month));
        
        if (monthIndex == -1 || dayIndex == -1) {
            cob_ngay_ket_thuc.setSelectedIndex(0);
            cob_thang_ket_thuc.setSelectedIndex(0);
        }
        else {
            cob_ngay_ket_thuc.setSelectedIndex(dayIndex);
            cob_thang_ket_thuc.setSelectedIndex(monthIndex);
        }
        
        isUpdating = false;
    }//GEN-LAST:event_cob_nam_ket_thucActionPerformed

    private void txt_tin_nhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tin_nhanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tin_nhanActionPerformed

    private void btn_resetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_resetMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_resetMouseClicked

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        // TODO add your handling code here:
        resetFields();
    }//GEN-LAST:event_btn_resetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_bo_loc;
    private javax.swing.JButton btn_loc;
    private javax.swing.JButton btn_reset;
    private javax.swing.JComboBox<String> cob_nam_bat_dau;
    private javax.swing.JComboBox<String> cob_nam_ket_thuc;
    private javax.swing.JComboBox<String> cob_ngay_bat_dau;
    private javax.swing.JComboBox<String> cob_ngay_ket_thuc;
    private javax.swing.JComboBox<String> cob_thang_bat_dau;
    private javax.swing.JComboBox<String> cob_thang_ket_thuc;
    private javax.swing.JLabel jL_parking_session_id;
    private javax.swing.JLabel jL_parking_session_id1;
    private javax.swing.JLabel jL_parking_session_id2;
    private javax.swing.JLabel jL_parking_session_id3;
    private javax.swing.JLabel jL_parking_session_id4;
    private javax.swing.JLabel jL_parking_session_id5;
    private javax.swing.JLabel jL_parking_session_id6;
    private javax.swing.JLabel jL_parking_session_id7;
    private javax.swing.JLabel jL_parking_session_id8;
    private javax.swing.JLabel jL_parking_session_id9;
    private javax.swing.JLabel jL_title;
    private javax.swing.JLabel jL_title1;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane sc_pariking_session;
    private javax.swing.JTable tbl_parking_session;
    private javax.swing.JTextField txt_amount;
    private javax.swing.JTextField txt_check_in_time;
    private javax.swing.JTextField txt_check_out_time;
    private javax.swing.JTextField txt_gui_xe;
    private javax.swing.JTextField txt_parking_session_id;
    private javax.swing.JTextField txt_tin_nhan;
    private javax.swing.JTextField txt_tong_tien;
    private javax.swing.JTextField txt_xe_ra;
    private javax.swing.JTextField txt_xe_vao;
    // End of variables declaration//GEN-END:variables
}

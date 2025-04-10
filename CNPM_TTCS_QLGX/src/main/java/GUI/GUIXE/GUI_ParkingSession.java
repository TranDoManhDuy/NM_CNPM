/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.GUIXE;

import Annotation.LogSelection;
import DAO.ParkingSessionDAO;
import DAO.VehicleDAO;
import DAO.VisitorParkingCardsDAO;
import DatabaseHelper.OpenConnection;
import GUI.ViewMain;
import javax.swing.table.DefaultTableModel;
import Model.ParkingSession;
import Model.ResidentCard;
import Model.SessionFee;
import Model.TimeFrame;
import Model.Vehicle;
import Model.VehicleType;
import Model.VisitorParkingCards;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Admin
 */
public class GUI_ParkingSession extends javax.swing.JPanel {
    private ViewMain viewmain;
    private DefaultTableModel tblModel = new DefaultTableModel(){
        @Override 
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    Map<String, ArrayList<?>> data;
    ArrayList<ParkingSession> parking_sessions;
    ArrayList<String> check_in_shift_type_names;
    ArrayList<String> check_out_shift_type_names;
    private String[] sDay, sMonth, sYear;
    private String[] s1Day, s1Month, s1Year;
    private boolean isUpdating = false;
    private LogSelection logSelection;
    private int chooseCardId = -1;
    private int chooseVehicleId = -1;
    private int choooseIndexVehicleType = -1;
    private GUI_Vehicle gui_vehicle;
    
    
    /**
     * Creates new form GUI_Customer
     */
    public GUI_ParkingSession(ViewMain viewmain, LogSelection logSelection, GUI_Vehicle gui_vehicle) {
        this.viewmain = viewmain;
        this.logSelection = logSelection;
        this.gui_vehicle = gui_vehicle;
        
        initComponents();
        resetFields();
        resetBtn();
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
        addCheckBoxListeners();
        addDocumentListeners();
    }
    
    public void initTable() { 
        String[] header = new String[] {"Mã Gửi Xe", "Mã Thẻ",  "Dịch Vụ", "Giờ Vào", "Giờ Ra", "Ca Trực Vào", "Ca Trực Ra", "Xe", "Giá Tiền"};
        tblModel.setColumnIdentifiers(header);
        tblModel.setRowCount(0);
        tbl_parking_session.setModel(tblModel);
        btn_insert.setEnabled(false);
    }
    
    private void loadData() {
        try {
            this.data = ParkingSessionDAO.getInstance().getAllData();
            this.parking_sessions = (ArrayList<ParkingSession>) data.get("parking_sessions");
            this.check_in_shift_type_names = (ArrayList<String>) data.get("check_in_shift_type_names");
            this.check_out_shift_type_names = (ArrayList<String>) data.get("check_out_shift_type_names");
        }
        catch (Exception e) { 
            e.printStackTrace();
        }
    }
    
    
    private void fillTable() {
        int count = -1;
        String crCheck_in_shift_type_name = "";
        String crCheck_out_shift_type_name = "";
        
        
        for (ParkingSession par: parking_sessions) { 
            count += 1;
            crCheck_in_shift_type_name = check_in_shift_type_names.get(count);
            crCheck_out_shift_type_name = check_out_shift_type_names.get(count);
            String dt_start = "null";
            String dt_end = "null";
            
            dt_start =  String.valueOf(par.getCheck_in_time().toLocalDate()) + " " + 
                        String.valueOf(par.getCheck_in_time().toLocalTime());
            
            if (par.getCheck_out_time() != null ){ 
                dt_end =    String.valueOf(par.getCheck_out_time().toLocalDate()) + " " +
                        String.valueOf(par.getCheck_out_time().toLocalTime());
            }
            
            if (crCheck_out_shift_type_name == null) {
                crCheck_out_shift_type_name = "Trong Bai";
            }
            tblModel.addRow(new String[] {  String.valueOf(par.getParking_session_id()), String.valueOf(par.getCard_id()), String.valueOf(par.isIs_service()),
                                            dt_start, dt_end,
                                            crCheck_in_shift_type_name, 
                                            crCheck_out_shift_type_name, 
                                            String.valueOf(par.getVehicle_id()), String.valueOf(par.getAmount())
            });
        }
        tblModel.fireTableDataChanged();
    }  
    
    private void resetFields() { 
        txt_parking_session_id.setText("");
        txt_card_id.setText("");
        cb_is_service.setSelected(false);
        cb_not_service.setSelected(false);
        txt_check_in_time.setText("");
        txt_check_out_time.setText("");
        txt_check_in_shift_id.setText("");
        txt_check_out_shift_id.setText("");            
        txt_vehicle.setText("");
        txt_amount.setText("");
        txt_ma_nhan_dang_xe.setText("");
        txt_vehicle_type.setText("");
        tbl_parking_session.clearSelection();
        
        txt_parking_session_id.setEnabled(false);
        cb_is_service.setEnabled(true);
        cb_not_service.setEnabled(true);
        txt_check_in_time.setEnabled(false);
        txt_check_out_time.setEnabled(false);
        txt_check_in_shift_id.setEnabled(false);
        txt_check_out_shift_id.setEnabled(false);
        txt_vehicle.setEnabled(false);
        txt_amount.setEnabled(false);
        txt_ma_nhan_dang_xe.setEnabled(false);
        txt_vehicle_type.setEnabled(false);
    }
    
    private void ShowNotInsert() { 
        btn_card_id.setEnabled(false);
        txt_ma_nhan_dang_xe.setEnabled(false);
        btn_vehicle_type.setEnabled(false);
        cb_is_service.setEnabled(false);
        cb_not_service.setEnabled(false);
        btn_vehicle.setEnabled(false);
        btn_insert.setEnabled(false);
    }
    
    private void showUpdate() { 
        this.ShowNotInsert();
        btn_update.setEnabled(true);
        btn_delete.setEnabled(false);
    }
    
    private void showDelete() { 
        this.ShowNotInsert();
        btn_update.setEnabled(false);
        btn_delete.setEnabled(true);
    }
    
    private void resetBtn() {
        btn_loc.setEnabled(false);
        btn_bo_loc.setEnabled(false);
        btn_card_id.setEnabled(false);
        btn_vehicle.setEnabled(false);
        btn_delete.setEnabled(false);
        btn_update.setEnabled(false);
    }
    
    public void reloadData() { 
        initTable();
        loadData();
        resetFields();
        fillTable();
    }
    
    private void checkDayMonthYearSort() {
        boolean isDateSelected =    !cob_ngay_bat_dau.getSelectedItem().toString().equals("-1") &&
                                    !cob_ngay_ket_thuc.getSelectedItem().toString().equals("-1") &&
                                    !cob_thang_bat_dau.getSelectedItem().toString().equals("-1") &&
                                    !cob_thang_ket_thuc.getSelectedItem().toString().equals("-1") &&
                                    !cob_nam_bat_dau.getSelectedItem().toString().equals("-1") &&
                                    !cob_nam_ket_thuc.getSelectedItem().toString().equals("-1");
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
    
    private void checkTurnOnButtonCardId () { 
        btn_vehicle.setEnabled(cb_is_service.isSelected());
        txt_ma_nhan_dang_xe.setEnabled(cb_not_service.isSelected());
        btn_vehicle_type.setEnabled(cb_not_service.isSelected());
        btn_card_id.setEnabled(cb_is_service.isSelected() || cb_not_service.isSelected());
    }
    
    private void checkBtnInsert() {
        boolean isFilled =      !txt_card_id.getText().trim().isEmpty() && 
                                (
                                    !txt_vehicle.getText().trim().isEmpty() ||
                                    (
                                        !txt_ma_nhan_dang_xe.getText().trim().isEmpty() &&
                                        !txt_vehicle_type.getText().trim().isEmpty()
                                    )
                                );
        boolean isButton =      (cb_is_service.isSelected() || cb_not_service.isSelected());
        btn_insert.setEnabled(isFilled && isButton);
    }
    
    private void addCheckBoxListeners() {
        ActionListener checkboxListener = new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e) {
                checkTurnOnButtonCardId();
                checkBtnInsert();
            }
        };
        cb_is_service.addActionListener(checkboxListener);
        cb_not_service.addActionListener(checkboxListener); 
    }
    
    private void addDocumentListeners() {
        DocumentListener docListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkBtnInsert();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkBtnInsert();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkBtnInsert();
            }
        };
        txt_card_id.getDocument().addDocumentListener(docListener);
        txt_vehicle.getDocument().addDocumentListener(docListener);
        txt_ma_nhan_dang_xe.getDocument().addDocumentListener(docListener);
        txt_vehicle_type.getDocument().addDocumentListener(docListener);
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
        jPanel3 = new javax.swing.JPanel();
        txt_tim_kiem = new javax.swing.JTextField();
        btn_tim_kiem = new javax.swing.JButton();
        btn_tang = new javax.swing.JButton();
        btn_giam = new javax.swing.JButton();
        btn_tat_ca = new javax.swing.JButton();
        btn_tang_gia = new javax.swing.JButton();
        btn_giam_gia = new javax.swing.JButton();
        btn_mac_dinh_gia = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_tin_nhan = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btn_insert = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_reset = new javax.swing.JButton();
        btn_card_id = new javax.swing.JButton();
        btn_vehicle = new javax.swing.JButton();
        btn_vehicle_type = new javax.swing.JButton();
        txt_parking_session_id = new javax.swing.JTextField();
        txt_check_in_time = new javax.swing.JTextField();
        txt_check_out_time = new javax.swing.JTextField();
        txt_vehicle = new javax.swing.JTextField();
        txt_amount = new javax.swing.JTextField();
        txt_card_id = new javax.swing.JTextField();
        txt_check_in_shift_id = new javax.swing.JTextField();
        txt_check_out_shift_id = new javax.swing.JTextField();
        txt_ma_nhan_dang_xe = new javax.swing.JTextField();
        txt_vehicle_type = new javax.swing.JTextField();
        cb_is_service = new javax.swing.JCheckBox();
        cb_not_service = new javax.swing.JCheckBox();
        jL_title = new javax.swing.JLabel();
        jL_parking_session_id = new javax.swing.JLabel();
        jL_card_id = new javax.swing.JLabel();
        jL_is_service = new javax.swing.JLabel();
        jL_check_in_time = new javax.swing.JLabel();
        jL_check_out_time = new javax.swing.JLabel();
        jL_check_in_shift_id = new javax.swing.JLabel();
        jL_check_out_shift_d = new javax.swing.JLabel();
        jL_vehicle_id = new javax.swing.JLabel();
        jL_amount = new javax.swing.JLabel();
        jL_amount1 = new javax.swing.JLabel();
        jL_amount2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(153, 255, 255));

        tbl_parking_session.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_parking_session.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Gửi Xe", "Mã Thẻ", "Dịch Vụ", "Giờ Vào", "Giờ Ra", "Ca Trực Vào", "Ca Trực Ra", "Xe", "Giá Tiền"
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

        txt_tim_kiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_tim_kiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tim_kiemActionPerformed(evt);
            }
        });

        btn_tim_kiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_tim_kiem.setText("Tìm");
        btn_tim_kiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tim_kiemActionPerformed(evt);
            }
        });

        btn_tang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_tang.setText("Tăng");
        btn_tang.setToolTipText("");
        btn_tang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tangActionPerformed(evt);
            }
        });

        btn_giam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_giam.setText("Giảm");
        btn_giam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_giamActionPerformed(evt);
            }
        });

        btn_tat_ca.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_tat_ca.setText("Tất cả");
        btn_tat_ca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tat_caActionPerformed(evt);
            }
        });

        btn_tang_gia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_tang_gia.setText("Tăng");
        btn_tang_gia.setToolTipText("");
        btn_tang_gia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tang_giaActionPerformed(evt);
            }
        });

        btn_giam_gia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_giam_gia.setText("Giảm");
        btn_giam_gia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_giam_giaActionPerformed(evt);
            }
        });

        btn_mac_dinh_gia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_mac_dinh_gia.setText("Mặc Định");
        btn_mac_dinh_gia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mac_dinh_giaActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Thời Gian");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Tìm Mã Gửi:");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Giá Tiền");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btn_tang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_giam)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_tat_ca))
                    .addComponent(txt_tim_kiem))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_tim_kiem)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_tang_gia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_giam_gia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_mac_dinh_gia)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tim_kiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(btn_tim_kiem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_tang)
                    .addComponent(jLabel12)
                    .addComponent(btn_giam)
                    .addComponent(btn_tat_ca)
                    .addComponent(btn_tang_gia)
                    .addComponent(jLabel14)
                    .addComponent(btn_giam_gia)
                    .addComponent(btn_mac_dinh_gia))
                .addContainerGap(12, Short.MAX_VALUE))
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
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(txt_tin_nhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sc_pariking_session, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setRequestFocusEnabled(false);
        jPanel2.setVerifyInputWhenFocusTarget(false);

        btn_insert.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_insert.setText("Thêm");
        btn_insert.setEnabled(false);
        btn_insert.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_insertMouseClicked(evt);
            }
        });
        btn_insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertActionPerformed(evt);
            }
        });

        btn_update.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_update.setText("Cập Nhật");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_delete.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_delete.setText("Xóa");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_reset.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_reset.setText("Reset");
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

        btn_card_id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_card_id.setText("Chọn");
        btn_card_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_card_idActionPerformed(evt);
            }
        });

        btn_vehicle.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_vehicle.setText("Chọn");
        btn_vehicle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_vehicleActionPerformed(evt);
            }
        });

        btn_vehicle_type.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_vehicle_type.setText("Chọn");
        btn_vehicle_type.setEnabled(false);
        btn_vehicle_type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_vehicle_typeActionPerformed(evt);
            }
        });

        txt_parking_session_id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_parking_session_id.setEnabled(false);

        txt_check_in_time.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_check_in_time.setEnabled(false);

        txt_check_out_time.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_check_out_time.setEnabled(false);

        txt_vehicle.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txt_amount.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_amount.setEnabled(false);

        txt_card_id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_card_id.setEnabled(false);

        txt_check_in_shift_id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_check_in_shift_id.setEnabled(false);

        txt_check_out_shift_id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_check_out_shift_id.setEnabled(false);

        txt_ma_nhan_dang_xe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_ma_nhan_dang_xe.setEnabled(false);

        txt_vehicle_type.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_vehicle_type.setEnabled(false);

        cb_is_service.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cb_is_service.setText("Có");
        cb_is_service.setEnabled(false);
        cb_is_service.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_is_serviceActionPerformed(evt);
            }
        });

        cb_not_service.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cb_not_service.setText("Không");
        cb_not_service.setEnabled(false);
        cb_not_service.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_not_serviceActionPerformed(evt);
            }
        });

        jL_title.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jL_title.setText("Thông Tin Gửi Xe");

        jL_parking_session_id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jL_parking_session_id.setText("Mã Gửi Xe:");

        jL_card_id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jL_card_id.setText("Mã Thẻ:");

        jL_is_service.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jL_is_service.setText("Dịch Vụ");

        jL_check_in_time.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jL_check_in_time.setText("Giờ Vào:");

        jL_check_out_time.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jL_check_out_time.setText("Giờ Ra:");

        jL_check_in_shift_id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jL_check_in_shift_id.setText("Ca Trực Vào");

        jL_check_out_shift_d.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jL_check_out_shift_d.setText("Ca Trực Ra");

        jL_vehicle_id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jL_vehicle_id.setText("Mã Xe");

        jL_amount.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jL_amount.setText("Giá Tiền");

        jL_amount1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jL_amount1.setText("Nhận dạng xe");

        jL_amount2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jL_amount2.setText("Loai Xe");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jL_amount1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_ma_nhan_dang_xe, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jL_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txt_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jL_title, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(64, 64, 64)
                                    .addComponent(btn_reset))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jL_parking_session_id, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jL_card_id, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jL_is_service, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jL_check_in_time, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jL_check_out_time, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jL_vehicle_id, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jL_check_out_shift_d, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jL_check_in_shift_id, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                            .addComponent(txt_card_id, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btn_card_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txt_check_out_time)
                                                .addComponent(txt_check_in_time)
                                                .addComponent(txt_parking_session_id)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addComponent(cb_is_service)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(cb_not_service))
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addComponent(txt_vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(btn_vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(txt_check_in_shift_id)
                                                .addComponent(txt_check_out_shift_id))
                                            .addGap(0, 0, Short.MAX_VALUE)))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jL_amount2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_vehicle_type, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_vehicle_type, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 22, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_insert, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_delete)
                .addGap(44, 44, 44))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jL_title)
                    .addComponent(btn_reset))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jL_parking_session_id)
                    .addComponent(txt_parking_session_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_card_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_card_id))
                    .addComponent(jL_card_id))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jL_is_service)
                    .addComponent(cb_is_service)
                    .addComponent(cb_not_service))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jL_check_in_time)
                    .addComponent(txt_check_in_time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jL_check_out_time)
                    .addComponent(txt_check_out_time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_check_in_shift_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jL_check_in_shift_id))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_check_out_shift_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jL_check_out_shift_d))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jL_vehicle_id)
                    .addComponent(txt_vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_vehicle))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jL_amount)
                    .addComponent(txt_amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jL_amount1)
                    .addComponent(txt_ma_nhan_dang_xe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jL_amount2)
                    .addComponent(txt_vehicle_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_vehicle_type))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_insert)
                    .addComponent(btn_update)
                    .addComponent(btn_delete))
                .addContainerGap())
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            String cardId = tbl_parking_session.getValueAt(selectedRow, 1).toString();
            boolean isService = Boolean.parseBoolean(tbl_parking_session.getValueAt(selectedRow, 2).toString());
            String checkInTime = tbl_parking_session.getValueAt(selectedRow, 3).toString();
            String checkOutTime = tbl_parking_session.getValueAt(selectedRow, 4).toString();
            String checkInShiftId = tbl_parking_session.getValueAt(selectedRow, 5).toString();
            String checkOutShiftId = tbl_parking_session.getValueAt(selectedRow, 6).toString();
            String vehicleId = tbl_parking_session.getValueAt(selectedRow, 7).toString();
            int amount = Integer.parseInt(tbl_parking_session.getValueAt(selectedRow, 8).toString());
            
            // Hiển thị dữ liệu lên các ô nhập liệu
            txt_parking_session_id.setText(String.valueOf(parkingSessionId));
            txt_card_id.setText(cardId);
            if (isService) { 
                cb_is_service.setSelected(true);
                cb_not_service.setSelected(false);
            }
            else { 
                cb_is_service.setSelected(false);
                cb_not_service.setSelected(true);
            }
            txt_check_in_time.setText(checkInTime);
            txt_check_out_time.setText(checkOutTime);
            txt_check_in_shift_id.setText(checkInShiftId);
            txt_check_out_shift_id.setText(checkOutShiftId);            
            txt_vehicle.setText(vehicleId);
            txt_amount.setText(String.valueOf(amount));
            
            if (checkOutTime == "null") {
                
                showUpdate();
            }
            else {
                showDelete();
            }
        }
    }//GEN-LAST:event_tbl_parking_sessionMouseClicked

    private void btn_insertMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_insertMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_insertMouseClicked

    private void btn_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertActionPerformed
        // TODO add your handling code here:
        boolean isService = true;
        if (cb_is_service.isSelected()) {
            isService = true;
        }
        else if (cb_not_service.isSelected()) { 
            isService = false;
        }
        int shift_work_id = -1;
        String sql = "EXEC GET_SHIFT_WORK_ID";
        try (
                Connection con = OpenConnection.getConnection();
                Statement  st  = con.createStatement();
                ResultSet  rs  = st.executeQuery(sql);) {
                while (rs.next()) {
                    shift_work_id = rs.getInt("ShiftWorkId");
                }
        }
        catch (Exception e) { 
            e.printStackTrace();
            return;
        }
        LocalDateTime now = LocalDateTime.now();
        if (shift_work_id != -1) { 
            String vehicle_identification = txt_ma_nhan_dang_xe.getText().toString().trim();
            Vehicle newVehicle = new Vehicle(vehicle_identification, choooseIndexVehicleType, "", "");
            try {
                VehicleDAO.getInstance().insert(newVehicle);
                this.choooseIndexVehicleType = -1;
                viewmain.vehicles = VehicleDAO.getInstance().getList();
            } 
            catch (Exception e) { 
                return;
            }
            for (Vehicle vel : viewmain.vehicles) {
                if (vel.getIdentification_code().equals(vehicle_identification)) {
                    chooseVehicleId = vel.getVehicle_id();
                }
            }
        }
        ParkingSession par = new ParkingSession(chooseCardId, isService, now, chooseVehicleId);
        ParkingSessionDAO.getInstance().insert(par);
        resetFields();
        resetBtn();
        initTable();
        loadData();
        fillTable();
        viewmain.parking_sessions = ParkingSessionDAO.getInstance().getList();
        if (isService == false) {
            viewmain.visitor_parking_cards = VisitorParkingCardsDAO.getInstance().getAll();
        }
        this.gui_vehicle.reloadData();
    }//GEN-LAST:event_btn_insertActionPerformed

    private void btn_resetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_resetMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_resetMouseClicked

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        // TODO add your handling code here:
        resetFields();
        resetBtn();
    }//GEN-LAST:event_btn_resetActionPerformed

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

    private void txt_tim_kiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tim_kiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tim_kiemActionPerformed

    private void btn_tangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tangActionPerformed
        // TODO add your handling code here:
        initTable();
        int n = this.parking_sessions.size();
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (this.parking_sessions.get(i).getCheck_in_time().compareTo(this.parking_sessions.get(j).getCheck_in_time()) > 0) {
                    ParkingSession tempParkingSession = this.parking_sessions.get(i);
                    this.parking_sessions.set(i, this.parking_sessions.get(j));
                    this.parking_sessions.set(j, tempParkingSession);

                    String tempShiftTypeName = this.check_in_shift_type_names.get(i);
                    this.check_in_shift_type_names.set(i, this.check_in_shift_type_names.get(j));
                    this.check_in_shift_type_names.set(j, tempShiftTypeName);
                    
                    tempShiftTypeName = this.check_out_shift_type_names.get(i);
                    this.check_out_shift_type_names.set(i, this.check_out_shift_type_names.get(j));
                    this.check_out_shift_type_names.set(j, tempShiftTypeName);
                }
            }    
        }
        
        int count = -1;
        String crCheck_in_shift_type_name = "";
        String crCheck_out_shift_type_name = "";
        
        for (ParkingSession par: parking_sessions) { 
            count += 1;
            crCheck_in_shift_type_name = check_in_shift_type_names.get(count);
            crCheck_out_shift_type_name = check_out_shift_type_names.get(count);
            String dt_start = "null";
            String dt_end = "null";
            
            dt_start =  String.valueOf(par.getCheck_in_time().toLocalDate()) + " " + 
                        String.valueOf(par.getCheck_in_time().toLocalTime());
            
            if (par.getCheck_out_time() != null ){ 
                dt_end =    String.valueOf(par.getCheck_out_time().toLocalDate()) + " " +
                        String.valueOf(par.getCheck_out_time().toLocalTime());
            }
            
            if (crCheck_out_shift_type_name == null) {
                crCheck_out_shift_type_name = "Trong Bai";
            }
            tblModel.addRow(new String[] {  String.valueOf(par.getParking_session_id()), String.valueOf(par.getCard_id()), String.valueOf(par.isIs_service()),
                                            dt_start, dt_end,
                                            crCheck_in_shift_type_name, 
                                            crCheck_out_shift_type_name, 
                                            String.valueOf(par.getVehicle_id()), String.valueOf(par.getAmount())
            });
        }
        tblModel.fireTableDataChanged();
        resetFields();
    }//GEN-LAST:event_btn_tangActionPerformed

    private void btn_giamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_giamActionPerformed
        // TODO add your handling code here:
        initTable();
        int n = this.parking_sessions.size();
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (this.parking_sessions.get(i).getCheck_in_time().compareTo(this.parking_sessions.get(j).getCheck_in_time()) < 0) { 
                    // Hoán đổi vị trí trong lstCustomer
                    ParkingSession tempParkingSession = this.parking_sessions.get(i);
                    this.parking_sessions.set(i, this.parking_sessions.get(j));
                    this.parking_sessions.set(j, tempParkingSession);

                    String tempShiftTypeName = this.check_in_shift_type_names.get(i);
                    this.check_in_shift_type_names.set(i, this.check_in_shift_type_names.get(j));
                    this.check_in_shift_type_names.set(j, tempShiftTypeName);
                    
                    tempShiftTypeName = this.check_out_shift_type_names.get(i);
                    this.check_out_shift_type_names.set(i, this.check_out_shift_type_names.get(j));
                    this.check_out_shift_type_names.set(j, tempShiftTypeName);
                }
            }    
        }
        
        int count = -1;
        String crCheck_in_shift_type_name = "";
        String crCheck_out_shift_type_name = "";
        
        
        for (ParkingSession par: parking_sessions) { 
            count += 1;
            crCheck_in_shift_type_name = check_in_shift_type_names.get(count);
            crCheck_out_shift_type_name = check_out_shift_type_names.get(count);
            String dt_start = "null";
            String dt_end = "null";
            
            dt_start =  String.valueOf(par.getCheck_in_time().toLocalDate()) + " " + 
                        String.valueOf(par.getCheck_in_time().toLocalTime());
            
            if (par.getCheck_out_time() != null ){ 
                dt_end =    String.valueOf(par.getCheck_out_time().toLocalDate()) + " " +
                        String.valueOf(par.getCheck_out_time().toLocalTime());
            }
            
            if (crCheck_out_shift_type_name == null) {
                crCheck_out_shift_type_name = "Trong Bai";
            }
            tblModel.addRow(new String[] {  String.valueOf(par.getParking_session_id()), String.valueOf(par.getCard_id()), String.valueOf(par.isIs_service()),
                                            dt_start, dt_end,
                                            crCheck_in_shift_type_name, 
                                            crCheck_out_shift_type_name, 
                                            String.valueOf(par.getVehicle_id()), String.valueOf(par.getAmount())
            });
        }
        tblModel.fireTableDataChanged();
        resetFields();
    }//GEN-LAST:event_btn_giamActionPerformed

    private void btn_tang_giaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tang_giaActionPerformed
        // TODO add your handling code here:
        initTable();
        int n = this.parking_sessions.size();
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (this.parking_sessions.get(i).getAmount() > this.parking_sessions.get(j).getAmount()) { 
                    // Hoán đổi vị trí trong lstCustomer
                    ParkingSession tempParkingSession = this.parking_sessions.get(i);
                    this.parking_sessions.set(i, this.parking_sessions.get(j));
                    this.parking_sessions.set(j, tempParkingSession);

                    String tempShiftTypeName = this.check_in_shift_type_names.get(i);
                    this.check_in_shift_type_names.set(i, this.check_in_shift_type_names.get(j));
                    this.check_in_shift_type_names.set(j, tempShiftTypeName);
                    
                    tempShiftTypeName = this.check_out_shift_type_names.get(i);
                    this.check_out_shift_type_names.set(i, this.check_out_shift_type_names.get(j));
                    this.check_out_shift_type_names.set(j, tempShiftTypeName);
                }
            }    
        }
        
        int count = -1;
        String crCheck_in_shift_type_name = "";
        String crCheck_out_shift_type_name = "";
        
        
        for (ParkingSession par: parking_sessions) { 
            count += 1;
            crCheck_in_shift_type_name = check_in_shift_type_names.get(count);
            crCheck_out_shift_type_name = check_out_shift_type_names.get(count);
            String dt_start = "null";
            String dt_end = "null";
            
            dt_start =  String.valueOf(par.getCheck_in_time().toLocalDate()) + " " + 
                        String.valueOf(par.getCheck_in_time().toLocalTime());
            
            if (par.getCheck_out_time() != null ){ 
                dt_end =    String.valueOf(par.getCheck_out_time().toLocalDate()) + " " +
                        String.valueOf(par.getCheck_out_time().toLocalTime());
            }
            
            if (crCheck_out_shift_type_name == null) {
                crCheck_out_shift_type_name = "Trong Bai";
            }
            tblModel.addRow(new String[] {  String.valueOf(par.getParking_session_id()), String.valueOf(par.getCard_id()), String.valueOf(par.isIs_service()),
                                            dt_start, dt_end,
                                            crCheck_in_shift_type_name, 
                                            crCheck_out_shift_type_name, 
                                            String.valueOf(par.getVehicle_id()), String.valueOf(par.getAmount())
            });
        }
        tblModel.fireTableDataChanged();
        resetFields();
    }//GEN-LAST:event_btn_tang_giaActionPerformed

    private void btn_giam_giaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_giam_giaActionPerformed
        // TODO add your handling code here:
        initTable();
        int n = this.parking_sessions.size();
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (this.parking_sessions.get(i).getAmount() < this.parking_sessions.get(j).getAmount()) { 
                    // Hoán đổi vị trí trong lstCustomer
                    ParkingSession tempParkingSession = this.parking_sessions.get(i);
                    this.parking_sessions.set(i, this.parking_sessions.get(j));
                    this.parking_sessions.set(j, tempParkingSession);

                    String tempShiftTypeName = this.check_in_shift_type_names.get(i);
                    this.check_in_shift_type_names.set(i, this.check_in_shift_type_names.get(j));
                    this.check_in_shift_type_names.set(j, tempShiftTypeName);
                    
                    tempShiftTypeName = this.check_out_shift_type_names.get(i);
                    this.check_out_shift_type_names.set(i, this.check_out_shift_type_names.get(j));
                    this.check_out_shift_type_names.set(j, tempShiftTypeName);
                }
            }    
        }
        
        int count = -1;
        String crCheck_in_shift_type_name = "";
        String crCheck_out_shift_type_name = "";
        
        
        for (ParkingSession par: parking_sessions) { 
            count += 1;
            crCheck_in_shift_type_name = check_in_shift_type_names.get(count);
            crCheck_out_shift_type_name = check_out_shift_type_names.get(count);
            String dt_start = "null";
            String dt_end = "null";
            
            dt_start =  String.valueOf(par.getCheck_in_time().toLocalDate()) + " " + 
                        String.valueOf(par.getCheck_in_time().toLocalTime());
            
            if (par.getCheck_out_time() != null ){ 
                dt_end =    String.valueOf(par.getCheck_out_time().toLocalDate()) + " " +
                        String.valueOf(par.getCheck_out_time().toLocalTime());
            }
            
            if (crCheck_out_shift_type_name == null) {
                crCheck_out_shift_type_name = "Trong Bai";
            }
            tblModel.addRow(new String[] {  String.valueOf(par.getParking_session_id()), String.valueOf(par.getCard_id()), String.valueOf(par.isIs_service()),
                                            dt_start, dt_end,
                                            crCheck_in_shift_type_name, 
                                            crCheck_out_shift_type_name, 
                                            String.valueOf(par.getVehicle_id()), String.valueOf(par.getAmount())
            });
        }
        tblModel.fireTableDataChanged();
        resetFields();
    }//GEN-LAST:event_btn_giam_giaActionPerformed

    private void txt_tin_nhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tin_nhanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tin_nhanActionPerformed

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

    private void btn_locActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_locActionPerformed
        // TODO add your handling code here:
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
        String crCheck_in_shift_type_name = "";
        String crCheck_out_shift_type_name = "";
        LocalDate dateStartPs;
        LocalDate dateEndPs;
        for (ParkingSession par: parking_sessions) { 
            count += 1;
            dateStartPs = par.getCheck_in_time().toLocalDate();
            dateEndPs = par.getCheck_out_time() != null ? par.getCheck_out_time().toLocalDate() : null;
            if (    
                    dateEndPs != null &&
                    (
                        dateStart.isBefore(dateStartPs) || dateStart.equals(dateStartPs)
                    ) &&
                    (
                        dateEnd.isAfter(dateEndPs) || dateEnd.equals(dateEndPs)
                    )
                ) 
            {
                crCheck_in_shift_type_name = check_in_shift_type_names.get(count);
                crCheck_out_shift_type_name = check_out_shift_type_names.get(count);
                
                String dt_start = "null";
                String dt_end = "null";

                dt_start =  String.valueOf(par.getCheck_in_time().toLocalDate()) + " " + 
                            String.valueOf(par.getCheck_in_time().toLocalTime());

                if (par.getCheck_out_time() != null ){ 
                    dt_end =    String.valueOf(par.getCheck_out_time().toLocalDate()) + " " +
                            String.valueOf(par.getCheck_out_time().toLocalTime());
                }
                
                tblModel.addRow(new String[] {  String.valueOf(par.getParking_session_id()), String.valueOf(par.getCard_id()), String.valueOf(par.isIs_service()),
                                                dt_start, dt_end,
                                                crCheck_in_shift_type_name, 
                                                crCheck_out_shift_type_name, 
                                                String.valueOf(par.getVehicle_id()), String.valueOf(par.getAmount())
                }); 
            }
        }
        tblModel.fireTableDataChanged();
        resetFields();
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

    private void btn_tim_kiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tim_kiemActionPerformed
        // TODO add your handling code here:
        initTable();
        
        int count = -1;
        String crCheck_in_shift_type_name = "";
        String crCheck_out_shift_type_name = "";
        
        for (ParkingSession par: parking_sessions) { 
            count += 1;
            if (Library.Library.StringOnString(this.txt_tim_kiem.getText().toString().trim(), String.valueOf(par.getParking_session_id()))) {
                crCheck_in_shift_type_name = check_in_shift_type_names.get(count);
                crCheck_out_shift_type_name = check_out_shift_type_names.get(count);
                String dt_start = "null";
                String dt_end = "null";

                dt_start =  String.valueOf(par.getCheck_in_time().toLocalDate()) + " " + 
                            String.valueOf(par.getCheck_in_time().toLocalTime());

                if (par.getCheck_out_time() != null ){ 
                    dt_end =    String.valueOf(par.getCheck_out_time().toLocalDate()) + " " +
                            String.valueOf(par.getCheck_out_time().toLocalTime());
                }

                if (crCheck_out_shift_type_name == null) {
                    crCheck_out_shift_type_name = "Trong Bai";
                }
                tblModel.addRow(new String[] {  String.valueOf(par.getParking_session_id()), String.valueOf(par.getCard_id()), String.valueOf(par.isIs_service()),
                                                dt_start, dt_end,
                                                crCheck_in_shift_type_name, 
                                                crCheck_out_shift_type_name, 
                                                String.valueOf(par.getVehicle_id()), String.valueOf(par.getAmount())
                });
            }
        }
        tblModel.fireTableDataChanged();
        resetFields();
    }//GEN-LAST:event_btn_tim_kiemActionPerformed

    private void btn_tat_caActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tat_caActionPerformed
        // TODO add your handling code here:
        loadData();
        initTable();
        fillTable();
        resetFields();
    }//GEN-LAST:event_btn_tat_caActionPerformed

    private void btn_card_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_card_idActionPerformed
        // TODO add your handling code here:
        this.viewmain.setEnabled(false);
        if (cb_is_service.isSelected()) {
            this.logSelection = new LogSelection() {
                @Override
                public void initContent() {
                    this.label_property.setText("Mã Định Danh Các Thẻ Cư Dân");
                    this.tableModel = new DefaultTableModel() {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        };
                    };
                    String[] header = new String[] {"Mã Thẻ", "Khách Hàng", "Còn/Mất"};
                    this.tableModel.setColumnIdentifiers(header);
                    this.table.setModel(tableModel);
                    this.table.addMouseListener(new MouseAdapter()
                    {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            int row = table.rowAtPoint(e.getPoint());
                            txt_card_id.setText((String) table.getValueAt(row, 0));
                            chooseCardId = Integer.parseInt((String)table.getValueAt(row, 0));
                            logSelection.setVisible(false);
                            viewmain.setEnabled(true);
                            viewmain.requestFocus();
                        }
                    });
                    for (ResidentCard re : viewmain.resident_cards) {
                        tableModel.addRow(new String[] {String.valueOf(re.getPk_resident_card()), String.valueOf(re.getCustomer_id()),
                                                        String.valueOf(re.isIs_active())});
                    }
                    this.tableModel.fireTableDataChanged();
                    this.btn_loc.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                        }
                    });
                }
                
                @Override
                public void back() {
                    this.setVisible(false);
                    viewmain.setEnabled(true);
                    viewmain.requestFocus();
                }
            };
        }
        else if (cb_not_service.isSelected()) { 
            this.logSelection = new LogSelection() {
                @Override
                public void initContent() {
                    this.label_property.setText("Mã Định Danh Các Thẻ Vãn Lai");
                    this.tableModel = new DefaultTableModel() {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        };
                    };
                    String[] header = new String[] {"Mã Thẻ", "Còn/Mất"};
                    this.tableModel.setColumnIdentifiers(header);
                    this.table.setModel(tableModel);
                    this.table.addMouseListener(new MouseAdapter()
                    {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            int row = table.rowAtPoint(e.getPoint());
                            txt_card_id.setText((String) table.getValueAt(row, 0));
                            chooseCardId = Integer.parseInt((String)table.getValueAt(row, 0));
                            logSelection.setVisible(false);
                            viewmain.setEnabled(true);
                            viewmain.requestFocus();
                        }
                    });
                    for (VisitorParkingCards vc : viewmain.visitor_parking_cards) {
                        tableModel.addRow(new String[] {String.valueOf(vc.getVisitor_parking_card_id()),
                                                        String.valueOf(vc.isIs_active())});
                    }
                    this.tableModel.fireTableDataChanged();
                    this.btn_loc.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                        }
                    });
                }
                
                @Override
                public void back() {
                    this.setVisible(false);
                    viewmain.setEnabled(true);
                    viewmain.requestFocus();
                }
            };
        }
        this.logSelection.initContent();
        this.logSelection.setVisible(true);
    }//GEN-LAST:event_btn_card_idActionPerformed

    private void cb_is_serviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_is_serviceActionPerformed
        // TODO add your handling code here:
        if (cb_is_service.isSelected()) { 
            cb_is_service.setSelected(true);
            cb_not_service.setSelected(false);
            btn_vehicle.setEnabled(true);
            txt_ma_nhan_dang_xe.setEnabled(false);
            btn_vehicle_type.setEnabled(false);
        }
    }//GEN-LAST:event_cb_is_serviceActionPerformed

    private void cb_not_serviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_not_serviceActionPerformed
        // TODO add your handling code here:
        if (cb_not_service.isSelected()) { 
            cb_is_service.setSelected(false);
            cb_not_service.setSelected(true);
            btn_vehicle.setEnabled(false);
            txt_ma_nhan_dang_xe.setEnabled(true);
            btn_vehicle_type.setEnabled(true);
        }
    }//GEN-LAST:event_cb_not_serviceActionPerformed

    private void btn_vehicleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_vehicleActionPerformed
        // TODO add your handling code here:
        this.viewmain.setEnabled(false);
        this.logSelection = new LogSelection() {
            @Override
            public void initContent() {
                this.label_property.setText("Mã Định Danh Các Phương Tiện");
                this.tableModel = new DefaultTableModel() {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    };
                };
                String[] header = new String[] {"Mã Phương Tiện", "Mã Nhận Dạng", "Loại Phương Tiện", "Tên Phương Tiện", "Màu Phương Tiện"};
                this.tableModel.setColumnIdentifiers(header);
                this.table.setModel(tableModel);
                this.table.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int row = table.rowAtPoint(e.getPoint());
                        txt_vehicle.setText((String) table.getValueAt(row, 1));
                        chooseVehicleId = Integer.parseInt((String)table.getValueAt(row, 0));
                        logSelection.setVisible(false);
                        viewmain.setEnabled(true);
                        viewmain.requestFocus();
                    }
                });
                for (Vehicle vel : viewmain.vehicles) {
                    tableModel.addRow(new String[] {String.valueOf(vel.getVehicle_id()), vel.getIdentification_code(), 
                                                    String.valueOf(vel.getVehicle_type_id()), vel.getVehicle_name(), 
                                                    vel.getVehicle_color()
                    });
                }
                this.tableModel.fireTableDataChanged();
                this.btn_loc.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    }
                });
            }
            @Override
            public void back() {
                this.setVisible(false);
                viewmain.setEnabled(true);
                viewmain.requestFocus();
            }
        };
        this.logSelection.initContent();
        this.logSelection.setVisible(true);
    }//GEN-LAST:event_btn_vehicleActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
        LocalDateTime start_date_time = null;
        int id_end_time = 0, id_start_time = 0;
        LocalDateTime end_date_time = null;
        LocalDate end_date = null;
        LocalTime end_time = null;
        int vehicle_id = -1; 
        int vehicle_type_id = -1;
        int amount = 0;
        long dateDistance = 0;
        int check_in_shift_id = -1;

        int pk_id = Integer.parseInt(txt_parking_session_id.getText().toString().trim());
        for (ParkingSession par: this.parking_sessions) { 
            if (par.getParking_session_id() == pk_id) { 
                start_date_time = par.getCheck_in_time();
                vehicle_id = par.getVehicle_id();
                check_in_shift_id = par.getCheck_in_shift_id();
            }
        }
        
        for (Vehicle vel: viewmain.vehicles) { 
            if (vel.getVehicle_id() == vehicle_id) { 
                vehicle_type_id = vel.getVehicle_type_id();
            }
        }
        
        
        LocalDate start_date = start_date_time.toLocalDate();
        LocalTime start_time = start_date_time.toLocalTime();
                
        String sql = "EXEC GET_DATE_TIME";
        try (
                Connection con = OpenConnection.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                end_date_time = rs.getTimestamp("CurrentDateTime").toLocalDateTime();
                end_date = end_date_time.toLocalDate();
                end_time = end_date_time.toLocalTime();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        for (TimeFrame tf : viewmain.listTimeFrames) {
            if 
                (
                    (tf.getTime_start().isBefore(end_time) || tf.getTime_start().equals(end_time)) &&
                    (tf.getTime_end().isAfter(end_time) || tf.getTime_end().equals(end_time)) &&
                    tf.isIs_active() == true
                )
            { 
                id_end_time = tf.getTime_frame_id();
            }
            
            if 
                (
                    (tf.getTime_start().isBefore(start_time) || tf.getTime_start().equals(start_time)) &&
                    (tf.getTime_end().isAfter(start_time) || tf.getTime_end().equals(start_time)) &&
                    tf.isIs_active() == true
                )
            { 
                id_start_time = tf.getTime_frame_id();
            }
        }
        
        dateDistance = ChronoUnit.DAYS.between(start_date, end_date);
        int sumAmountOfDay = 0;
        if (dateDistance > 0) {
            for (TimeFrame tf : viewmain.listTimeFrames) {
                int id = tf.getTime_frame_id();

                if (tf.isIs_active()) { 
                    int money = 0;
                    for (SessionFee sf: viewmain.listSessionFees) { 
                        if (sf.getVehicle_type_id() == vehicle_type_id && sf.getTime_frame_id() == id && sf.isIs_active()) {
                            money = sf.getAmount();
                        }
                    }

                    sumAmountOfDay += money;

                    if  (id_start_time <= id){ 
                        amount += money;
                    }

                    if (id_end_time >= id) {
                        amount += money;
                    }
                }
            }
            amount += (dateDistance - 1) * sumAmountOfDay;
        }
        else { 
            for (TimeFrame tf : viewmain.listTimeFrames) {
                int id = tf.getTime_frame_id();

                if (tf.isIs_active()) { 
                    int money = 0;
                    for (SessionFee sf: viewmain.listSessionFees) { 
                        if (sf.getVehicle_type_id() == vehicle_type_id && sf.getTime_frame_id() == id && sf.isIs_active()) {
                            money = sf.getAmount();
                        }
                    }
                    
                    if (id_start_time <= id && id_end_time >= id) { 
                        amount += money;
                    }
                }
            }
        }
        
        int parking_session_id = Integer.parseInt(txt_parking_session_id.getText().toString().trim());
        int card_id = Integer.parseInt(txt_card_id.getText().toString().trim());
        boolean is_service;
        if (cb_is_service.isSelected()) { 
            is_service = true;
        }
        else { 
            is_service = false;
        }
        ParkingSession par = new ParkingSession(parking_session_id, card_id, is_service, start_date_time, null, check_in_shift_id, -1, vehicle_id, amount);
        System.out.println(par.getParking_session_id() + " " + par.getCard_id() + " " + par.isIs_service() + " " + par.getCheck_in_time() + " " + " " +  par.getCheck_in_shift_id() + " " + par.getAmount());
        ParkingSessionDAO.getInstance().update(par);
        resetFields();
        resetBtn();
        initTable();
        loadData();
        fillTable();
        viewmain.visitor_parking_cards = VisitorParkingCardsDAO.getInstance().getAll();
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
        int parking_session_id = Integer.parseInt(txt_parking_session_id.getText().toString().trim());
        ParkingSessionDAO.getInstance().delete(parking_session_id);
        resetFields();
        resetBtn();
        initTable();
        loadData();
        fillTable();
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_mac_dinh_giaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mac_dinh_giaActionPerformed
        // TODO add your handling code here:
        loadData();
        initTable();
        fillTable();
        resetFields();
    }//GEN-LAST:event_btn_mac_dinh_giaActionPerformed

    private void btn_vehicle_typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_vehicle_typeActionPerformed
        // TODO add your handling code here:
        this.viewmain.setEnabled(false);
        this.logSelection = new LogSelection() {
            @Override
            public void initContent() {
                this.label_property.setText("Mã Định Danh Các Loại Phương Tiện");
                this.tableModel = new DefaultTableModel() {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    };
                };
                String[] header = new String[] {"ID Loại Phương Tiện", "Tên Loại Phương Tiện", "Còn cho phép"};
                this.tableModel.setColumnIdentifiers(header);
                this.table.setModel(tableModel);
                this.table.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int row = table.rowAtPoint(e.getPoint());
                        txt_vehicle_type.setText((String) table.getValueAt(row, 1));
                        choooseIndexVehicleType = Integer.parseInt((String)table.getValueAt(row, 0));
//                        System.out.println(choooseIndexVehicleType);
                        logSelection.setVisible(false);
                        viewmain.setEnabled(true);
                        viewmain.requestFocus();
                    }
                });
                for (VehicleType vt : viewmain.vehicle_types) {
                    tableModel.addRow(new String[] {String.valueOf(vt.getVehicle_type_id()), vt.getVehicle_type_name(), String.valueOf(vt.isIsPermission())});
                }
                this.tableModel.fireTableDataChanged();
                this.btn_loc.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    }
                });
            }
            @Override
            public void back() {
                this.setVisible(false);
                viewmain.setEnabled(true);
                viewmain.requestFocus();
            }
        };
        this.logSelection.initContent();
        this.logSelection.setVisible(true);
    }//GEN-LAST:event_btn_vehicle_typeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_bo_loc;
    private javax.swing.JButton btn_card_id;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_giam;
    private javax.swing.JButton btn_giam_gia;
    private javax.swing.JButton btn_insert;
    private javax.swing.JButton btn_loc;
    private javax.swing.JButton btn_mac_dinh_gia;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_tang;
    private javax.swing.JButton btn_tang_gia;
    private javax.swing.JButton btn_tat_ca;
    private javax.swing.JButton btn_tim_kiem;
    private javax.swing.JButton btn_update;
    private javax.swing.JButton btn_vehicle;
    private javax.swing.JButton btn_vehicle_type;
    private javax.swing.JCheckBox cb_is_service;
    private javax.swing.JCheckBox cb_not_service;
    private javax.swing.JComboBox<String> cob_nam_bat_dau;
    private javax.swing.JComboBox<String> cob_nam_ket_thuc;
    private javax.swing.JComboBox<String> cob_ngay_bat_dau;
    private javax.swing.JComboBox<String> cob_ngay_ket_thuc;
    private javax.swing.JComboBox<String> cob_thang_bat_dau;
    private javax.swing.JComboBox<String> cob_thang_ket_thuc;
    private javax.swing.JLabel jL_amount;
    private javax.swing.JLabel jL_amount1;
    private javax.swing.JLabel jL_amount2;
    private javax.swing.JLabel jL_card_id;
    private javax.swing.JLabel jL_check_in_shift_id;
    private javax.swing.JLabel jL_check_in_time;
    private javax.swing.JLabel jL_check_out_shift_d;
    private javax.swing.JLabel jL_check_out_time;
    private javax.swing.JLabel jL_is_service;
    private javax.swing.JLabel jL_parking_session_id;
    private javax.swing.JLabel jL_title;
    private javax.swing.JLabel jL_vehicle_id;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane sc_pariking_session;
    private javax.swing.JTable tbl_parking_session;
    private javax.swing.JTextField txt_amount;
    private javax.swing.JTextField txt_card_id;
    private javax.swing.JTextField txt_check_in_shift_id;
    private javax.swing.JTextField txt_check_in_time;
    private javax.swing.JTextField txt_check_out_shift_id;
    private javax.swing.JTextField txt_check_out_time;
    private javax.swing.JTextField txt_ma_nhan_dang_xe;
    private javax.swing.JTextField txt_parking_session_id;
    private javax.swing.JTextField txt_tim_kiem;
    private javax.swing.JTextField txt_tin_nhan;
    private javax.swing.JTextField txt_vehicle;
    private javax.swing.JTextField txt_vehicle_type;
    // End of variables declaration//GEN-END:variables
}

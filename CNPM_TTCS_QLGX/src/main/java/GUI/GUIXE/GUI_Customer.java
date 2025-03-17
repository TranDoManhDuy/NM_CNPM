/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.GUIXE;

import DAO.BuildingsDAO;
import DAO.CustomerDAO;
import DatabaseHelper.OpenConnection;
import Global.DataGlobal;
import Model.Buildings;
import Model.Customer;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class GUI_Customer extends javax.swing.JPanel {
    private DataGlobal dataGlobal;
    private DefaultTableModel tblModel = new DefaultTableModel();
    /**
     * Creates new form GUI_Customer
     */
    public GUI_Customer(DataGlobal dataGlobal) {
        initComponents(); 
        this.dataGlobal = dataGlobal;
        initTable();
        fillTable();
        addDocumentListeners();
    }
    
    public void initTable() { 
        String[] header = new String[] {"ID KH", "ID Tòa Nhà",  "Họ Tên", "Căn Cước", "Ngày Sinh", "Giới Tính", "Điện Thoại", "Thường Trú", "Quốc Tịch", "Cư Dân"};
        tblModel.setColumnIdentifiers(header);
        tblCustomer.setModel(tblModel);
        btnInsert.setEnabled(false);
    }
    
    public void fillTable() {
        try {
            Connection con = OpenConnection.getConnection();
            ArrayList<Customer> lstCustomer = CustomerDAO.getInstance().getList();
            tblModel.setRowCount(0);
            Buildings building = new Buildings();
            for (Customer cus : lstCustomer) { 
                try {
                    building = BuildingsDAO.getInstance().findByID(cus.getBuilding_id());
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                tblModel.addRow(new String[] {  String.valueOf(cus.getCustomer_id()), String.valueOf(building.getBuilding_id()), cus.getFull_name(), cus.getSsn(), 
                                                String.valueOf(cus.getDate_of_birth()), cus.getGender(),
                                                cus.getPhone_number(), cus.getAddress(), cus.getNationality(), String.valueOf(cus.isIs_active())
                });
            }
        }
        catch (Exception e) { 
                e.printStackTrace();
            }
        tblModel.fireTableDataChanged();
    }
    
    private void resetFields() { 
        txtCustomer_id.setText("");
        txtBuilding_id.setText("");
        txtFull_name.setText("");
        txtSsn.setText("");
        txtDate_of_birth.setText("");
        txtPhone_number.setText("");
        txtAddress.setText("");
        TxtNationality.setText("");
        cb_isActive.setSelected(false);
        cb_gender_F.setSelected(false);
        cb_gender_M.setSelected(false);
        cb_gender_O.setSelected(false);
        tblCustomer.clearSelection();
    }

    private void checkInsertButton () { 
         // Kiểm tra nếu tất cả các trường không rỗng
        boolean isFilled =  txtCustomer_id.getText().trim().isEmpty() &&
                            !txtBuilding_id.getText().trim().isEmpty() &&
                            !txtFull_name.getText().trim().isEmpty() &&
                            !txtSsn.getText().trim().isEmpty() &&
                            !txtDate_of_birth.getText().trim().isEmpty() &&
                            !txtPhone_number.getText().trim().isEmpty() &&
                            !txtAddress.getText().trim().isEmpty() &&
                            !TxtNationality.getText().trim().isEmpty();
        boolean isGenderSelected = cb_gender_F.isSelected() || cb_gender_M.isSelected() || cb_gender_O.isSelected();
        boolean isResidentSelected = cb_isActive.isSelected();

        System.out.println(isFilled + " " + isGenderSelected + " " + isResidentSelected);
        // Kiểm tra nếu txtCustomer_id đang được bật (enabled)
//        boolean isCustomerIdEnabled = txtCustomer_id.isEnabled();
        btnInsert.setEnabled(isFilled && isGenderSelected && isResidentSelected);
    }
    private void addDocumentListeners() {
        DocumentListener docListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkInsertButton();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkInsertButton();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkInsertButton();
            }
        };
        // Thêm lắng nghe cho tất cả các JTextField
        txtCustomer_id.getDocument().addDocumentListener(docListener);
        txtBuilding_id.getDocument().addDocumentListener(docListener);
        txtFull_name.getDocument().addDocumentListener(docListener);
        txtSsn.getDocument().addDocumentListener(docListener);
        txtDate_of_birth.getDocument().addDocumentListener(docListener);
        txtPhone_number.getDocument().addDocumentListener(docListener);
        txtAddress.getDocument().addDocumentListener(docListener);
        TxtNationality.getDocument().addDocumentListener(docListener);
        ActionListener actionListener = e -> checkInsertButton();

        cb_isActive.addActionListener(actionListener);
        cb_gender_F.addActionListener(actionListener);
        cb_gender_M.addActionListener(actionListener);
        cb_gender_O.addActionListener(actionListener);
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
        scrollPaneCustomer = new javax.swing.JScrollPane();
        tblCustomer = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtCustomer_id = new javax.swing.JTextField();
        txtBuilding_id = new javax.swing.JTextField();
        txtFull_name = new javax.swing.JTextField();
        txtSsn = new javax.swing.JTextField();
        txtDate_of_birth = new javax.swing.JTextField();
        txtPhone_number = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        TxtNationality = new javax.swing.JTextField();
        btnInsert = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        cb_isActive = new javax.swing.JCheckBox();
        cb_gender_M = new javax.swing.JCheckBox();
        cb_gender_F = new javax.swing.JCheckBox();
        cb_gender_O = new javax.swing.JCheckBox();
        btnReset = new javax.swing.JButton();

        setBackground(new java.awt.Color(153, 255, 255));
        setPreferredSize(new java.awt.Dimension(1135, 490));

        tblCustomer.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã", "Tòa Nhà", "Họ Tên", "Căn Cước", "Ngày Sinh", "Giới Tính", "Điện Thoại", "Thường Trú", "Quốc Tịch", "Còn ở"
            }
        ));
        tblCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCustomerMouseClicked(evt);
            }
        });
        scrollPaneCustomer.setViewportView(tblCustomer);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPaneCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPaneCustomer, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel2.setRequestFocusEnabled(false);
        jPanel2.setVerifyInputWhenFocusTarget(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Thông Tin Khách Hàng");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mã Khách Hàng:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tòa Nhà:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Họ Và Tên:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Số Căn Cước:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Ngày Sinh:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Giới Tính:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Số Điện Thoại:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Địa Chỉ Thường Trú:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Cư Dân Chung Cư: ");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Quốc Tịch:");

        txtCustomer_id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCustomer_id.setEnabled(false);

        txtBuilding_id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtFull_name.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtSsn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtDate_of_birth.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtPhone_number.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtAddress.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        TxtNationality.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnInsert.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnInsert.setText("Thêm");
        btnInsert.setEnabled(false);
        btnInsert.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInsertMouseClicked(evt);
            }
        });
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setText("Cập Nhật");

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton4.setText("Sửa");

        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton5.setText("Xóa");

        cb_isActive.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cb_isActive.setText("Cư Dân");

        cb_gender_M.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cb_gender_M.setText("Nam");

        cb_gender_F.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cb_gender_F.setText("Nữ");

        cb_gender_O.setText("Khác");

        btnReset.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnReset.setText("Reset");
        btnReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetMouseClicked(evt);
            }
        });
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtNationality, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_isActive)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(64, 64, 64)
                            .addComponent(btnReset))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtPhone_number, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDate_of_birth, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSsn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtFull_name, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtBuilding_id, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCustomer_id, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(cb_gender_M)
                                    .addGap(18, 18, 18)
                                    .addComponent(cb_gender_F)
                                    .addGap(18, 18, 18)
                                    .addComponent(cb_gender_O))))))
                .addGap(0, 21, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnReset))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCustomer_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtBuilding_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtFull_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtSsn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtDate_of_birth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cb_gender_M)
                    .addComponent(cb_gender_F)
                    .addComponent(cb_gender_O))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtPhone_number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(TxtNationality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cb_isActive))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInsert)
                    .addComponent(jButton2)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCustomerMouseClicked
        // TODO add your handling code here:
        int selectedRow = tblCustomer.getSelectedRow();
    
        // Kiểm tra xem có hàng nào được chọn không
        if (selectedRow != -1) {
            // Lấy dữ liệu từ bảng và gán vào các biến
            int customerId = Integer.parseInt(tblCustomer.getValueAt(selectedRow, 0).toString());
            String buildingName = tblCustomer.getValueAt(selectedRow, 1).toString();
            String fullName = tblCustomer.getValueAt(selectedRow, 2).toString();
            String ssn = tblCustomer.getValueAt(selectedRow, 3).toString();
            String dateOfBirth = tblCustomer.getValueAt(selectedRow, 4).toString();
            String gender = tblCustomer.getValueAt(selectedRow, 5).toString();
            String phoneNumber = tblCustomer.getValueAt(selectedRow, 6).toString();
            String address = tblCustomer.getValueAt(selectedRow, 7).toString();
            String nationality = tblCustomer.getValueAt(selectedRow, 8).toString();
            boolean isActive = Boolean.parseBoolean(tblCustomer.getValueAt(selectedRow, 9).toString());

            // Hiển thị dữ liệu lên các ô nhập liệu
            txtCustomer_id.setText(String.valueOf(customerId));
            txtBuilding_id.setText(buildingName);
            txtFull_name.setText(fullName);
            txtSsn.setText(ssn);
            txtDate_of_birth.setText(dateOfBirth);
            txtPhone_number.setText(phoneNumber);
            txtAddress.setText(address);
            TxtNationality.setText(nationality);
            
            // Nếu cần xử lý giới tính hoặc trạng thái, có thể cập nhật thêm
            cb_isActive.setSelected(isActive); // Giả sử có JCheckBox hiển thị trạng thái
            if (gender.equals("M")) {
                cb_gender_F.setSelected(false);
                cb_gender_M.setSelected(true);
                cb_gender_O.setSelected(false);
            }
            else if (gender.equals("F")) {
                cb_gender_F.setSelected(true);
                cb_gender_M.setSelected(false);
                cb_gender_O.setSelected(false);
            }
            else {
                cb_gender_F.setSelected(false);
                cb_gender_M.setSelected(false);
                cb_gender_O.setSelected(true);
            }
        }
    }//GEN-LAST:event_tblCustomerMouseClicked

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        resetFields();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseClicked
        // TODO add your handling code here:
        txtCustomer_id.setText("");
        txtBuilding_id.setText("");
        txtFull_name.setText("");
        txtSsn.setText("");
        txtDate_of_birth.setText("");
        txtPhone_number.setText("");
        txtAddress.setText("");
        TxtNationality.setText("");
        cb_isActive.setSelected(false);
        cb_gender_F.setSelected(false);
        cb_gender_M.setSelected(false);
        cb_gender_O.setSelected(false);
    }//GEN-LAST:event_btnResetMouseClicked

    private void btnInsertMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInsertMouseClicked
        // TODO add your handling code here:
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
        LocalDate dateOfBirth = LocalDate.parse(txtDate_of_birth.getText().trim(), formatter);
        Customer customer = new Customer(
            txtFull_name.getText().trim(),
            txtSsn.getText().trim(),
            dateOfBirth,
            cb_gender_F.isSelected() ? "F" :
            cb_gender_M.isSelected() ? "M" : "O",
            txtPhone_number.getText().trim(),
            txtAddress.getText().trim(),
            Integer.parseInt(txtBuilding_id.getText().trim()),
            TxtNationality.getText().trim(),
            cb_isActive.isSelected()
        );
        System.out.println(customer.getFull_name());
        try {
            Connection con = OpenConnection.getConnection();
            CustomerDAO.getInstance().insert(customer);
            resetFields();
            fillTable();
        }
        catch (Exception e) { 
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnInsertMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TxtNationality;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnReset;
    private javax.swing.JCheckBox cb_gender_F;
    private javax.swing.JCheckBox cb_gender_M;
    private javax.swing.JCheckBox cb_gender_O;
    private javax.swing.JCheckBox cb_isActive;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane scrollPaneCustomer;
    private javax.swing.JTable tblCustomer;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtBuilding_id;
    private javax.swing.JTextField txtCustomer_id;
    private javax.swing.JTextField txtDate_of_birth;
    private javax.swing.JTextField txtFull_name;
    private javax.swing.JTextField txtPhone_number;
    private javax.swing.JTextField txtSsn;
    // End of variables declaration//GEN-END:variables
}

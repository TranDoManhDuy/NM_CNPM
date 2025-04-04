/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.GUIXE;

import Annotation.LogSelection;
import DAO.CustomerDAO;
import DatabaseHelper.OpenConnection;
import GUI.ViewMain;
import Model.Buildings;
import Model.Customer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class GUI_Customer extends javax.swing.JPanel {
    private ViewMain viewmain;
    private DefaultTableModel tblModel = new DefaultTableModel(){
        @Override 
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private Map<String, ArrayList<?>> data =  new HashMap<>();
    ArrayList<Customer> customers = new ArrayList<>();
    ArrayList<String> buildingNames = new ArrayList<>();
    private String[] sDay, sMonth, sYear;
    private LogSelection logSelection;
    private int choooseIndexBuilding = 0;
    
    
    
    /**
     * Creates new form GUI_Customer
     */
    public GUI_Customer(ViewMain viewmain, LogSelection logSelection) {
        this.viewmain = viewmain;
        this.logSelection = logSelection;
        
        initComponents(); 
        resetFields();
        resetEnable();
        initTable();
        loadData();
        fillTable();
        
        sDay = Library.Library.getDay(0, 0);
        sMonth = Library.Library.getMonth(0, 0);
        sYear = Library.Library.getYear(0, 0);
        
        cob_ngay.setModel(new javax.swing.DefaultComboBoxModel<>(sDay));
        cob_thang.setModel(new javax.swing.DefaultComboBoxModel<>(sMonth));
        cob_nam.setModel(new javax.swing.DefaultComboBoxModel<>(sYear));
        
        txt_tin_nhan.setText("Đang hiển thị danh sách tất cả các khách hàng");
        addDocumentListeners();
    }
    
    protected void loadData() {
        try {
            this.data = CustomerDAO.getInstance().getAllCustomer();
            this.customers = (ArrayList<Customer>) data.get("customers");
            this.buildingNames = (ArrayList<String>) data.get("building_names");
        }
        catch (Exception e) { 
            e.printStackTrace();
        }
    }
    
    public void initTable() { 
        String[] header = new String[] {"ID KH", "ID Tòa Nhà",  "Họ Tên", "Căn Cước", "Ngày Sinh", "Giới Tính", "Điện Thoại", "Thường Trú", "Quốc Tịch", "Cư Dân"};
        tblModel.setColumnIdentifiers(header);
        tblModel.setRowCount(0);
        tblCustomer.setModel(tblModel);
        btn_insert.setEnabled(false);
    }
    
    public void fillTable() {
        try {
            int count = -1;
            String crBuildingName = "";
            for (Customer cus : this.customers) { 
                try {
                    count += 1;
                    crBuildingName = this.buildingNames.get(count);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                tblModel.addRow(new String[] {  String.valueOf(cus.getCustomer_id()), crBuildingName, cus.getFull_name(), cus.getSsn(), 
                                                String.valueOf(cus.getDate_of_birth()), cus.getGender(),
                                                cus.getPhone_number(), cus.getAddress(), cus.getNationality(), String.valueOf(cus.isIs_active())
                });
            }
        }
        catch (Exception e) { 
                e.printStackTrace();
            }
        tblModel.fireTableDataChanged();
//        LocalDate dob = LocalDate.of(1990, 01, 01);
//        Customer Cus = new Customer("Nguyen Van B", "000000000002", dob, "M", "0000000002", "123 Le Loi, Quan 1", 1 , "Vietnamese", true);
//        CustomerDAO.getInstance().insert(Cus);
//        Customer TestUpdateCus = new Customer(14, "Nguyen Van B", "000000000002", dob, "M", "0000000010", "123 Le Loi, Quan 1", 1 , "Vietnamese", true);
//        CustomerDAO.getInstance().update(TestUpdateCus);
//        System.out.println(CustomerDAO.getInstance().findbyID(14));
//        CustomerDAO.getInstance().delete(14);
    }
    
    private void resetEnable() {
        txt_full_name.setEnabled(true);
        txt_ssn.setEnabled(true);
        cob_ngay.setEnabled(true);
        cob_thang.setEnabled(true);
        cob_nam.setEnabled(true);
        cb_gender_M.setEnabled(true);
        cb_gender_F.setEnabled(true);
        cb_gender_O.setEnabled(true);
        cb_is_active.setEnabled(true);
        txt_phone_number.setEnabled(true);
        txt_address.setEnabled(true);
        Txt_nationality.setEnabled(true);
        btn_update.setEnabled(false);
        btn_delete.setEnabled(false);
    }
    
    private void resetFields() { 
        txt_customer_id.setText("");
        txt_building_id.setText("");
        txt_full_name.setText("");
        txt_ssn.setText("");
        txt_phone_number.setText("");
        txt_address.setText("");
        Txt_nationality.setText("");
        cb_is_active.setSelected(false);
        cb_gender_F.setSelected(false);
        cb_gender_M.setSelected(false);
        cb_gender_O.setSelected(false);
        tblCustomer.clearSelection();
        cob_ngay.setSelectedIndex(0);
        cob_thang.setSelectedIndex(0);
        cob_nam.setSelectedIndex(0);
        txt_tim_kiem.setText("");
    }
    
    private void showUpdate() {
        txt_full_name.setEnabled(false);
        txt_ssn.setEnabled(false);
        cob_ngay.setEnabled(false);
        cob_thang.setEnabled(false);
        cob_nam.setEnabled(false);
        cb_gender_M.setEnabled(true);
        cb_gender_F.setEnabled(true);
        cb_gender_O.setEnabled(true);
        cb_is_active.setEnabled(true);
        txt_phone_number.setEnabled(true);
        txt_address.setEnabled(true);
        Txt_nationality.setEnabled(true);
        btn_update.setEnabled(true);
        btn_delete.setEnabled(true);
    }

    private void checkInsertButton () { 
         // Kiểm tra nếu tất cả các trường không rỗng
        boolean isFilled =  txt_customer_id.getText().trim().isEmpty() &&
                            !txt_building_id.getText().trim().isEmpty() &&
                            !txt_full_name.getText().trim().isEmpty() &&
                            !txt_ssn.getText().trim().isEmpty() &&
//                            !txtDate_of_birth.getText().trim().isEmpty() &&
                            !txt_phone_number.getText().trim().isEmpty() &&
                            !txt_address.getText().trim().isEmpty() &&
                            !Txt_nationality.getText().trim().isEmpty();
        boolean isGenderSelected = cb_gender_F.isSelected() || cb_gender_M.isSelected() || cb_gender_O.isSelected();
        boolean isResidentSelected = cb_is_active.isSelected();
        boolean isDateSelected =    !cob_ngay.getSelectedItem().toString().equals("") &&
                                    !cob_thang.getSelectedItem().toString().equals("") &&
                                    !cob_nam.getSelectedItem().toString().equals("");

        System.out.println(isFilled + " " + isGenderSelected + " " + isResidentSelected + " " + isDateSelected);
        // Kiểm tra nếu txtCustomer_id đang được bật (enabled)
//        boolean isCustomerIdEnabled = txtCustomer_id.isEnabled();
        btn_insert.setEnabled(isFilled && isGenderSelected && isResidentSelected && isDateSelected);
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
        txt_customer_id.getDocument().addDocumentListener(docListener);
        txt_building_id.getDocument().addDocumentListener(docListener);
        txt_full_name.getDocument().addDocumentListener(docListener);
        txt_ssn.getDocument().addDocumentListener(docListener);
//        txtDate_of_birth.getDocument().addDocumentListener(docListener);
        txt_phone_number.getDocument().addDocumentListener(docListener);
        txt_address.getDocument().addDocumentListener(docListener);
        Txt_nationality.getDocument().addDocumentListener(docListener);
        ActionListener actionListener = e -> checkInsertButton();

        cb_is_active.addActionListener(actionListener);
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
        sp_customer = new javax.swing.JScrollPane();
        tblCustomer = new javax.swing.JTable();
        txt_tin_nhan = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        txt_tim_kiem = new javax.swing.JTextField();
        btn_tim_kiem = new javax.swing.JButton();
        btn_cu_dan = new javax.swing.JButton();
        btn_not_cu_dan = new javax.swing.JButton();
        btn_tat_ca = new javax.swing.JButton();
        btn_tang = new javax.swing.JButton();
        btn_giam = new javax.swing.JButton();
        btn_mac_dinh = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btn_VietNam = new javax.swing.JButton();
        btn_ngoai_quoc = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txt_customer_id = new javax.swing.JTextField();
        txt_building_id = new javax.swing.JTextField();
        txt_full_name = new javax.swing.JTextField();
        txt_ssn = new javax.swing.JTextField();
        txt_phone_number = new javax.swing.JTextField();
        txt_address = new javax.swing.JTextField();
        Txt_nationality = new javax.swing.JTextField();
        btn_insert = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_reset = new javax.swing.JButton();
        btn_chon = new javax.swing.JButton();
        cb_is_active = new javax.swing.JCheckBox();
        cb_gender_M = new javax.swing.JCheckBox();
        cb_gender_F = new javax.swing.JCheckBox();
        cb_gender_O = new javax.swing.JCheckBox();
        cob_ngay = new javax.swing.JComboBox<>();
        cob_thang = new javax.swing.JComboBox<>();
        cob_nam = new javax.swing.JComboBox<>();
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

        setBackground(new java.awt.Color(153, 255, 255));
        setPreferredSize(new java.awt.Dimension(1135, 490));

        jPanel1.setPreferredSize(new java.awt.Dimension(700, 448));

        tblCustomer.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã", "Tòa Nhà", "Họ Tên", "Căn Cước", "Ngày Sinh", "Giới Tính", "Điện Thoại", "Thường Trú", "Quốc Tịch", "Đang ở"
            }
        ));
        tblCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCustomerMouseClicked(evt);
            }
        });
        sp_customer.setViewportView(tblCustomer);

        txt_tin_nhan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_tin_nhan.setText("Đang hiển thị danh sách tất cả các khách hàng");
        txt_tin_nhan.setEnabled(false);
        txt_tin_nhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tin_nhanActionPerformed(evt);
            }
        });

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

        btn_cu_dan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_cu_dan.setText("Cư Dân");
        btn_cu_dan.setToolTipText("");
        btn_cu_dan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cu_danActionPerformed(evt);
            }
        });

        btn_not_cu_dan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_not_cu_dan.setText("Không");
        btn_not_cu_dan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_not_cu_danActionPerformed(evt);
            }
        });

        btn_tat_ca.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_tat_ca.setText("Tất cả");
        btn_tat_ca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tat_caActionPerformed(evt);
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

        btn_mac_dinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_mac_dinh.setText("Mặc Định");
        btn_mac_dinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mac_dinhActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Cư Dân");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Tìm Tên");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Căn Cước");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btn_cu_dan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_not_cu_dan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_tat_ca))
                    .addComponent(txt_tim_kiem))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_tim_kiem)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_tang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_giam)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_mac_dinh)))
                .addContainerGap(31, Short.MAX_VALUE))
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
                    .addComponent(btn_cu_dan)
                    .addComponent(jLabel12)
                    .addComponent(btn_not_cu_dan)
                    .addComponent(btn_tat_ca)
                    .addComponent(btn_tang)
                    .addComponent(jLabel14)
                    .addComponent(btn_giam)
                    .addComponent(btn_mac_dinh))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_VietNam.setText("Việt Nam");
        btn_VietNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_VietNamActionPerformed(evt);
            }
        });

        btn_ngoai_quoc.setText("Ngoại Quốc");
        btn_ngoai_quoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ngoai_quocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_tin_nhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_ngoai_quoc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_VietNam)
                        .addGap(32, 32, 32))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sp_customer))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tin_nhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ngoai_quoc)
                    .addComponent(btn_VietNam))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp_customer, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setPreferredSize(new java.awt.Dimension(365, 415));
        jPanel2.setRequestFocusEnabled(false);
        jPanel2.setVerifyInputWhenFocusTarget(false);

        txt_customer_id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_customer_id.setEnabled(false);

        txt_building_id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_building_id.setEnabled(false);

        txt_full_name.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_full_name.setEnabled(false);

        txt_ssn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_ssn.setEnabled(false);

        txt_phone_number.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_phone_number.setEnabled(false);

        txt_address.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_address.setEnabled(false);

        Txt_nationality.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Txt_nationality.setEnabled(false);

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
        btn_update.setEnabled(false);
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_delete.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_delete.setText("Xóa");
        btn_delete.setEnabled(false);
        btn_delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_deleteMouseClicked(evt);
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

        btn_chon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_chon.setText("Chọn");
        btn_chon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chonActionPerformed(evt);
            }
        });

        cb_is_active.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cb_is_active.setText("Cư Dân");
        cb_is_active.setEnabled(false);

        cb_gender_M.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cb_gender_M.setText("Nam");
        cb_gender_M.setEnabled(false);

        cb_gender_F.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cb_gender_F.setText("Nữ");
        cb_gender_F.setEnabled(false);

        cb_gender_O.setText("Khác");
        cb_gender_O.setEnabled(false);

        cob_ngay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cob_ngay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "31", "1", "2", "3" }));
        cob_ngay.setEnabled(false);
        cob_ngay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cob_ngayActionPerformed(evt);
            }
        });

        cob_thang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cob_thang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "31", "1", "2", "3" }));
        cob_thang.setEnabled(false);
        cob_thang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cob_thangActionPerformed(evt);
            }
        });

        cob_nam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cob_nam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "98", "04" }));
        cob_nam.setEnabled(false);
        cob_nam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cob_namActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Txt_nationality, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_is_active)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(btn_reset))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
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
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txt_address, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_building_id, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_chon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt_phone_number, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_ssn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_full_name, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_customer_id, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(cb_gender_M)
                                        .addGap(18, 18, 18)
                                        .addComponent(cb_gender_F)
                                        .addGap(18, 18, 18)
                                        .addComponent(cb_gender_O))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(cob_ngay, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cob_thang, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cob_nam, 0, 1, Short.MAX_VALUE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_insert, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_delete)
                        .addGap(33, 33, 33)))
                .addGap(0, 35, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btn_reset))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_customer_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_building_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_chon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_full_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_ssn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cob_ngay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cob_thang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cob_nam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cb_gender_M)
                    .addComponent(cb_gender_F)
                    .addComponent(cb_gender_O))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_phone_number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(Txt_nationality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cb_is_active))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_insert)
                    .addComponent(btn_update)
                    .addComponent(btn_delete))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
                .addGap(19, 19, 19))
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
            LocalDate dob = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String gender = tblCustomer.getValueAt(selectedRow, 5).toString();
            String phoneNumber = tblCustomer.getValueAt(selectedRow, 6).toString();
            String address = tblCustomer.getValueAt(selectedRow, 7).toString();
            String nationality = tblCustomer.getValueAt(selectedRow, 8).toString();
            boolean isActive = Boolean.parseBoolean(tblCustomer.getValueAt(selectedRow, 9).toString());

            // Hiển thị dữ liệu lên các ô nhập liệu
            txt_customer_id.setText(String.valueOf(customerId));
            txt_building_id.setText(buildingName);
            txt_full_name.setText(fullName);
            txt_ssn.setText(ssn);
            cob_ngay.setSelectedIndex( dob.getDayOfMonth() );
            cob_thang.setSelectedIndex( dob.getMonthValue() );
            cob_nam.setSelectedIndex( dob.getYear() - 1950 );
            txt_phone_number.setText(phoneNumber);
            txt_address.setText(address);
            Txt_nationality.setText(nationality);
            
            // Nếu cần xử lý giới tính hoặc trạng thái, có thể cập nhật thêm
            cb_is_active.setSelected(isActive); // Giả sử có JCheckBox hiển thị trạng thái
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
        showUpdate();
        }
    }//GEN-LAST:event_tblCustomerMouseClicked

    private void btn_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btn_insertActionPerformed

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        // TODO add your handling code here:
        resetFields();
        resetEnable();
    }//GEN-LAST:event_btn_resetActionPerformed

    private void btn_resetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_resetMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_resetMouseClicked

    private void btn_insertMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_insertMouseClicked
        // TODO add your handling code here:
        String day = cob_ngay.getSelectedItem().toString();
        String month = cob_thang.getSelectedItem().toString();
        String year = cob_nam.getSelectedItem().toString();
        if (Integer.parseInt(year) >= 50) year = "19" + year;
        else year = "20" + year;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
        String txtDate_of_birth = day + "/" + month + "/" + year;
        LocalDate dateOfBirth = LocalDate.parse(txtDate_of_birth.trim(), formatter);
        Customer customer = new Customer(
            txt_full_name.getText().trim(),
            txt_ssn.getText().trim(),
            dateOfBirth,
            cb_gender_F.isSelected() ? "F" :
            cb_gender_M.isSelected() ? "M" : "O",
            txt_phone_number.getText().trim(),
            txt_address.getText().trim(),
            choooseIndexBuilding,
            Txt_nationality.getText().trim(),
            cb_is_active.isSelected()
        );
        System.out.println(customer.getFull_name());
        try {
            Connection con = OpenConnection.getConnection();
            CustomerDAO.getInstance().insert(customer);
            resetFields();
            resetEnable();
            initTable();
            loadData();
            fillTable();
            choooseIndexBuilding = 0;
        }
        catch (Exception e) { 
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_insertMouseClicked

    private void txt_tin_nhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tin_nhanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tin_nhanActionPerformed

    private void txt_tim_kiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tim_kiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tim_kiemActionPerformed

    private void btn_cu_danActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cu_danActionPerformed
        // TODO add your handling code here:
        int index = -1;
        tblModel.setRowCount(0);
        for (Customer arr : this.customers) { 
            index += 1;
            if (arr.isIs_active()) {
                tblModel.addRow(new String[] {  String.valueOf(arr.getCustomer_id()), this.buildingNames.get(index), arr.getFull_name(), arr.getSsn(),
                                                String.valueOf(arr.getDate_of_birth()), arr.getGender(),
                                                arr.getPhone_number(), arr.getAddress(), arr.getNationality(), String.valueOf(arr.isIs_active())
                                });
            }
        }
        tblModel.fireTableDataChanged();
    }//GEN-LAST:event_btn_cu_danActionPerformed

    private void btn_not_cu_danActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_not_cu_danActionPerformed
        // TODO add your handling code here:
        int index = -1;
        tblModel.setRowCount(0);
        for (Customer arr : this.customers) { 
            index += 1;
            if (!arr.isIs_active()) {
                tblModel.addRow(new String[] {  String.valueOf(arr.getCustomer_id()), this.buildingNames.get(index), arr.getFull_name(), arr.getSsn(),
                                                String.valueOf(arr.getDate_of_birth()), arr.getGender(),
                                                arr.getPhone_number(), arr.getAddress(), arr.getNationality(), String.valueOf(arr.isIs_active())
                                });
            }
        }
        tblModel.fireTableDataChanged();
    }//GEN-LAST:event_btn_not_cu_danActionPerformed

    private void btn_giamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_giamActionPerformed
        // TODO add your handling code here:
        int n = this.customers.size();
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (this.customers.get(i).getSsn().compareTo(this.customers.get(j).getSsn()) < 0) { 
                    // Hoán đổi vị trí trong lstCustomer
                    Customer tempCustomer = this.customers.get(i);
                    this.customers.set(i, this.customers.get(j));
                    this.customers.set(j, tempCustomer);

                    // Hoán đổi vị trí tương ứng trong lstBuildingName
                    String tempBuildingName = this.buildingNames.get(i);
                    this.buildingNames.set(i, this.buildingNames.get(j));
                    this.buildingNames.set(j, tempBuildingName);
                }
            }    
        }
        
        int count = -1;
        String crBuildingName = "";
        initTable();
        for (Customer cus : this.customers) { 
                count += 1;
                crBuildingName = this.buildingNames.get(count);
            tblModel.addRow(new String[] {  String.valueOf(cus.getCustomer_id()), crBuildingName, cus.getFull_name(), cus.getSsn(), 
                                            String.valueOf(cus.getDate_of_birth()), cus.getGender(),
                                            cus.getPhone_number(), cus.getAddress(), cus.getNationality(), String.valueOf(cus.isIs_active())
            });
        }
        tblModel.fireTableDataChanged();
        resetFields();
    }//GEN-LAST:event_btn_giamActionPerformed

    private void btn_tangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tangActionPerformed
        // TODO add your handling code here:
        int n = this.customers.size();
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (this.customers.get(i).getSsn().compareTo(this.customers.get(j).getSsn()) > 0) { 
                    // Hoán đổi vị trí trong lstCustomer
                    Customer tempCustomer = this.customers.get(i);
                    this.customers.set(i, this.customers.get(j));
                    this.customers.set(j, tempCustomer);

                    // Hoán đổi vị trí tương ứng trong lstBuildingName
                    String tempBuildingName = this.buildingNames.get(i);
                    this.buildingNames.set(i, this.buildingNames.get(j));
                    this.buildingNames.set(j, tempBuildingName);
                }
            }    
        }
        
        int count = -1;
        String crBuildingName = "";
        initTable();
        for (Customer cus : this.customers) { 
                count += 1;
                crBuildingName = this.buildingNames.get(count);
            tblModel.addRow(new String[] {  String.valueOf(cus.getCustomer_id()), crBuildingName, cus.getFull_name(), cus.getSsn(), 
                                            String.valueOf(cus.getDate_of_birth()), cus.getGender(),
                                            cus.getPhone_number(), cus.getAddress(), cus.getNationality(), String.valueOf(cus.isIs_active())
            });
        }
        tblModel.fireTableDataChanged();
        resetFields();
    }//GEN-LAST:event_btn_tangActionPerformed

    private void btn_ngoai_quocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ngoai_quocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ngoai_quocActionPerformed

    private void btn_VietNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_VietNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_VietNamActionPerformed

    private void cob_namActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cob_namActionPerformed
        // TODO add your handling code here:
        int day = Integer.parseInt(cob_ngay.getSelectedItem().toString());
        int month = Integer.parseInt(cob_thang.getSelectedItem().toString());
        int year = Integer.parseInt(cob_nam.getSelectedItem().toString());
        System.out.println(day + " " + month + " " + year);
        
        sDay = Library.Library.getDay(month, year);
        sMonth = Library.Library.getMonth(day, year);
        
        cob_ngay.setModel(new javax.swing.DefaultComboBoxModel<>(sDay));
        cob_thang.setModel(new javax.swing.DefaultComboBoxModel<>(sMonth));
        
        int dayIndex = Arrays.asList(sDay).indexOf(String.format("%02d", day));
        int monthIndex = Arrays.asList(sMonth).indexOf(String.format("%02d", month));
        
        if (monthIndex == -1 || dayIndex == -1) {
            cob_ngay.setSelectedIndex(0);
            cob_thang.setSelectedIndex(0);
        }
        else {
            cob_ngay.setSelectedIndex(dayIndex);
            cob_thang.setSelectedIndex(monthIndex);
        }
    }//GEN-LAST:event_cob_namActionPerformed

    private void btn_tim_kiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tim_kiemActionPerformed
        // TODO add your handling code here:
        int index = -1;
        tblModel.setRowCount(0);
        for (Customer arr : this.customers) { 
            index += 1;
            if (Library.Library.StringOnString(this.txt_tim_kiem.getText(), arr.getFull_name())) {
                tblModel.addRow(new String[] {  String.valueOf(arr.getCustomer_id()), this.buildingNames.get(index), arr.getFull_name(), arr.getSsn(),
                                                String.valueOf(arr.getDate_of_birth()), arr.getGender(),
                                                arr.getPhone_number(), arr.getAddress(), arr.getNationality(), String.valueOf(arr.isIs_active())
                                });
            }
        }
        tblModel.fireTableDataChanged();
    }//GEN-LAST:event_btn_tim_kiemActionPerformed

    private void btn_tat_caActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tat_caActionPerformed
        // TODO add your handling code here:
        int index = -1;
        tblModel.setRowCount(0);
        for (Customer arr : this.customers) { 
            index += 1;
            tblModel.addRow(new String[] {  String.valueOf(arr.getCustomer_id()), this.buildingNames.get(index), arr.getFull_name(), arr.getSsn(),
                                            String.valueOf(arr.getDate_of_birth()), arr.getGender(),
                                            arr.getPhone_number(), arr.getAddress(), arr.getNationality(), String.valueOf(arr.isIs_active())
                            });
        }
        tblModel.fireTableDataChanged();
    }//GEN-LAST:event_btn_tat_caActionPerformed

    private void cob_ngayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cob_ngayActionPerformed
        // TODO add your handling code here:
        int day = Integer.parseInt(cob_ngay.getSelectedItem().toString());
        int month = Integer.parseInt(cob_thang.getSelectedItem().toString());
        int year = Integer.parseInt(cob_nam.getSelectedItem().toString());
        System.out.println(day + " " + month + " " + year);
        
        sMonth = Library.Library.getMonth(day, year);
        sYear = Library.Library.getYear(day, month);
        cob_thang.setModel(new javax.swing.DefaultComboBoxModel<>(sMonth));
        cob_nam.setModel(new javax.swing.DefaultComboBoxModel<>(sYear));
        
        int monthIndex = Arrays.asList(sMonth).indexOf(String.format("%02d", month));
        int yearIndex = Arrays.asList(sYear).indexOf(String.format("%02d", year));
        
        if (monthIndex == -1 || yearIndex == -1) {
            cob_thang.setSelectedIndex(0);
            cob_nam.setSelectedIndex(0);
        }
        else {
            cob_thang.setSelectedIndex(monthIndex);
            cob_nam.setSelectedIndex(yearIndex);
        }
    }//GEN-LAST:event_cob_ngayActionPerformed

    private void cob_thangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cob_thangActionPerformed
        // TODO add your handling code here:
        int day = Integer.parseInt(cob_ngay.getSelectedItem().toString());
        int month = Integer.parseInt(cob_thang.getSelectedItem().toString());
        int year = Integer.parseInt(cob_nam.getSelectedItem().toString());
        
        sDay = Library.Library.getDay(month, year);
        sYear = Library.Library.getYear(day, month);
        
        System.out.println(day + " " + month + " " + year);
        cob_ngay.setModel(new javax.swing.DefaultComboBoxModel<>(sDay));
        cob_nam.setModel(new javax.swing.DefaultComboBoxModel<>(sYear));
        
        int dayIndex = Arrays.asList(sDay).indexOf(String.format("%02d", day));
        int yearIndex = Arrays.asList(sYear).indexOf(String.format("%02d", year));
        
        if (dayIndex == -1 || yearIndex == -1) {
            cob_ngay.setSelectedIndex(0);
            cob_nam.setSelectedIndex(0);
        }
        else {
            cob_ngay.setSelectedIndex(dayIndex);
            cob_nam.setSelectedIndex(yearIndex);
        }
    }//GEN-LAST:event_cob_thangActionPerformed

    private void btn_chonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chonActionPerformed
        // TODO add your handling code here:
        this.viewmain.setEnabled(false);
        this.logSelection = new LogSelection() {
            @Override
            public void initContent() {
                this.label_property.setText("Mã Định Danh Các Tòa Nhà");
                this.tableModel = new DefaultTableModel() {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    };
                };
                String[] header = new String[] {"ID tòa nhà", "Tên tòa nhà",  "Địa chỉ"};
                this.tableModel.setColumnIdentifiers(header);
                this.table.setModel(tableModel);
                this.table.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int row = table.rowAtPoint(e.getPoint());
                        txt_building_id.setText((String) table.getValueAt(row, 1));
                        choooseIndexBuilding = Integer.parseInt((String)table.getValueAt(row, 0));
                        logSelection.setVisible(false);
                        viewmain.setEnabled(true);
                        viewmain.requestFocus();
                    }
                });
                for (Buildings b : viewmain.buildings) {
                    tableModel.addRow(new String[] {String.valueOf(b.getBuilding_id()), b.getBuilding_name(), b.getAddress()});
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
    }//GEN-LAST:event_btn_chonActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
        String day = cob_ngay.getSelectedItem().toString();
        String month = cob_thang.getSelectedItem().toString();
        String year = cob_nam.getSelectedItem().toString();
        if (Integer.parseInt(year) >= 50) year = "19" + year;
        else year = "20" + year;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
        String txtDate_of_birth = day + "/" + month + "/" + year;
        LocalDate dateOfBirth = LocalDate.parse(txtDate_of_birth.trim(), formatter);
        
        for (Buildings b : viewmain.buildings) {
            String buildingName = b.getBuilding_name();
            String customerInput = txt_building_id.getText().trim();

            if (buildingName != null && customerInput != null && buildingName.equals(customerInput)) {
                choooseIndexBuilding = b.getBuilding_id();
            }
        }
        
        Customer customer = new Customer(
            Integer.parseInt(txt_customer_id.getText().trim()),
            txt_full_name.getText().trim(),
            txt_ssn.getText().trim(),
            dateOfBirth,
            cb_gender_F.isSelected() ? "F" :
            cb_gender_M.isSelected() ? "M" : "O",
            txt_phone_number.getText().trim(),
            txt_address.getText().trim(),
            choooseIndexBuilding,
            Txt_nationality.getText().trim(),
            cb_is_active.isSelected()
        );
//        System.out.println(txt_building_id.getText() +  customer.getBuilding_id());
        try {
            Connection con = OpenConnection.getConnection();
            CustomerDAO.getInstance().update(customer);
            resetFields();
            resetEnable();
            loadData();
            for (String n: this.buildingNames) {
                System.out.println(n);
            }
            initTable();
            fillTable();
            choooseIndexBuilding = 0;
        }
        catch (Exception e) { 
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_deleteMouseClicked
        // TODO add your handling code here:
//        System.out.println(txt_customer_id.getText().trim());
        try {
            Connection con = OpenConnection.getConnection();
            CustomerDAO.getInstance().delete(Integer.parseInt(txt_customer_id.getText().trim()));
            resetFields();
            resetEnable();
            loadData();
            initTable();
            fillTable();
            choooseIndexBuilding = 0;
        }
        catch (Exception e) { 
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_deleteMouseClicked

    private void btn_mac_dinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mac_dinhActionPerformed
        // TODO add your handling code here:
        loadData();
        initTable();
        fillTable();
        resetFields();
    }//GEN-LAST:event_btn_mac_dinhActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Txt_nationality;
    private javax.swing.JButton btn_VietNam;
    private javax.swing.JButton btn_chon;
    private javax.swing.JButton btn_cu_dan;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_giam;
    private javax.swing.JButton btn_insert;
    private javax.swing.JButton btn_mac_dinh;
    private javax.swing.JButton btn_ngoai_quoc;
    private javax.swing.JButton btn_not_cu_dan;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_tang;
    private javax.swing.JButton btn_tat_ca;
    private javax.swing.JButton btn_tim_kiem;
    private javax.swing.JButton btn_update;
    private javax.swing.JCheckBox cb_gender_F;
    private javax.swing.JCheckBox cb_gender_M;
    private javax.swing.JCheckBox cb_gender_O;
    private javax.swing.JCheckBox cb_is_active;
    private javax.swing.JComboBox<String> cob_nam;
    private javax.swing.JComboBox<String> cob_ngay;
    private javax.swing.JComboBox<String> cob_thang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane sp_customer;
    private javax.swing.JTable tblCustomer;
    private javax.swing.JTextField txt_address;
    private javax.swing.JTextField txt_building_id;
    private javax.swing.JTextField txt_customer_id;
    private javax.swing.JTextField txt_full_name;
    private javax.swing.JTextField txt_phone_number;
    private javax.swing.JTextField txt_ssn;
    private javax.swing.JTextField txt_tim_kiem;
    private javax.swing.JTextField txt_tin_nhan;
    // End of variables declaration//GEN-END:variables
}

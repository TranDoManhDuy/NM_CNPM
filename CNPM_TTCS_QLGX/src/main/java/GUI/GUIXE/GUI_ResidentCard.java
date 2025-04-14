/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.GUIXE;

import Annotation.LogMessage;
import Annotation.LogSelection;
import DAO.ResidentCardDAO;
import GUI.ViewMain;
import Global.DataGlobal;
import Model.Buildings;
import Model.Customer;
import Model.ResidentCard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class GUI_ResidentCard extends javax.swing.JPanel {
    private ViewMain viewmain;
    private DefaultTableModel tblModel = new DefaultTableModel(){
        @Override 
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private Map<String, ArrayList<?>> data;
    private ArrayList<ResidentCard> residents;
    private ArrayList<String> building_names;
    private ArrayList<String> full_names;
    private LogSelection logSelection;
    private int chooseCustomerId = -1;
    private LogMessage logMessage;
    private DataGlobal dataGlobal;
    
    /**
     * Creates new form GUI_Customer
     */
    public GUI_ResidentCard(DataGlobal dataGlobal, ViewMain viewmain, LogSelection logSelection, LogMessage logMessage) {
        this.viewmain = viewmain;
        this.logSelection = logSelection;
        this.logMessage = logMessage;
        this.dataGlobal = dataGlobal;
        this.dataGlobal.updateArrayResidentCard();
        this.dataGlobal.updateArrBuildings();
        
        initComponents();
        initTable();
        resetFields();
        loadData();
        fillTable();
        fillComboBoxActive();
        fillComboBoxBuildings();
        addDocumentListeners();
        addButtonListeners();
    }
    
    public void initTable() { 
        String[] header = new String[] {"Mã Thẻ", "Khách Hàng", "Tòa Nhà", "Khả Dụng"};
        tblModel.setColumnIdentifiers(header);
        tblModel.setRowCount(0);
        tbl_resident_card.setModel(tblModel);
    }
    
    public void loadData() { 
        try {
            this.data =  ResidentCardDAO.getInstance().getAllData();
            this.residents = (ArrayList<ResidentCard>) data.get("customers");
            this.building_names = (ArrayList<String>) data.get("building_names");
            this.full_names = (ArrayList<String>) data.get("full_names");
        }
        catch (Exception e) { 
            e.printStackTrace();
        }
    }
    
    public void fillTable() {
        int count = -1;
        String crBuilding_name = "";
        String crFull_name = "";
        for (ResidentCard res : residents) {
            count += 1;
            crBuilding_name = building_names.get(count);
            crFull_name = full_names.get(count);
            tblModel.addRow(new String[] {String.valueOf(res.getPk_resident_card()), crFull_name, crBuilding_name, String.valueOf(res.isIs_active())} );
        }
        tblModel.fireTableDataChanged();
    }
    
    private void fillComboBoxActive() { 
        String[] items = { "None", "Có", "Không" };
        cob_con_mat.setModel(new DefaultComboBoxModel<>(items));
    }
    
    private void fillComboBoxBuildings() { 
        List<String> lstBuildings = new ArrayList<>();
        lstBuildings.add("None");
        for (Buildings b: this.dataGlobal.getArrayBuildings()) {
            lstBuildings.add(b.getBuilding_name());
        }
        String[] buildings = lstBuildings.toArray(new String[0]);
        cob_toa_nha.setModel(new DefaultComboBoxModel<>(buildings));
    }
    
    public void resetFields() { 
        txt_building_id.setText("");
        txt_customer_id.setText("");
        txt_pk_resident_card.setText("");
        
        tbl_resident_card.clearSelection();
        cob_con_mat.setSelectedIndex(0);
        cob_con_mat.setEnabled(false);
        btn_chon_customer.setEnabled(true);
        
        btn_update.setEnabled(false);
        btn_delete.setEnabled(false);
        btnInsert.setEnabled(false);
        
        chooseCustomerId = -1;
    }
    
    public void reloadData() { 
        initTable();
        loadData();
        resetFields();
        fillTable();
    }
    
    private void showUpdate() { 
        cob_con_mat.setEnabled(true);
        btn_chon_customer.setEnabled(false);
        
        btn_update.setEnabled(true);
        btn_delete.setEnabled(true);
        btnInsert.setEnabled(false);
    }
    
    private void checkBtnInsert() {
        boolean isFilled = !txt_customer_id.getText().trim().isEmpty();
        btnInsert.setEnabled(isFilled);
    }
    
    private void checkBtnUpdate() { 
        boolean isButton = cob_con_mat.getSelectedIndex() > 0;
        btn_update.setEnabled(isButton);
    }
    
    private void addButtonListeners() { 
        ActionListener comboListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBtnUpdate();
            }
        };
        cob_con_mat.addActionListener(comboListener);
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
        
        txt_customer_id.getDocument().addDocumentListener(docListener);
    }
    
    private void SetLog(String s) { 
        this.logMessage = new LogMessage(s) {
            @Override
            public void action() {
                this.setVisible(false);
                viewmain.setEnabled(true);
                viewmain.requestFocus();
            }
        };
        this.logMessage.setVisible(true);
        return;
    }
    
    private String GetError(String s) { 
        int index = 0; 
        String sError = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '\"') {
                index += 1;
                if (index == 2) {
                    return sError;
                } 
                else {
                    continue;
                }
            }
            if (index == 1) { 
                sError = sError + s.charAt(i);
            }
        }
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '\'') {
                index += 1;
                if (index == 2) {
                    return sError;
                } 
                else {
                    continue;
                }
            }
            if (index == 1) { 
                sError = sError + s.charAt(i);
            }
        }
        return s;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txt_tim_kiem = new javax.swing.JTextField();
        btn_tim_kiem = new javax.swing.JButton();
        btn_con = new javax.swing.JButton();
        btn_mat = new javax.swing.JButton();
        btn_tat_ca = new javax.swing.JButton();
        btn_sap_xep = new javax.swing.JButton();
        cob_toa_nha = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        sp_resident_card = new javax.swing.JScrollPane();
        tbl_resident_card = new javax.swing.JTable();
        txt_tin_nhan = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        txt_pk_resident_card = new javax.swing.JTextField();
        txt_customer_id = new javax.swing.JTextField();
        txt_building_id = new javax.swing.JTextField();
        btnInsert = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_reset = new javax.swing.JButton();
        btn_chon_customer = new javax.swing.JButton();
        cob_con_mat = new javax.swing.JComboBox<>();
        JL_Title = new javax.swing.JLabel();
        JL_MaTheCuDan = new javax.swing.JLabel();
        JL_MaKhachHang = new javax.swing.JLabel();
        JL_building = new javax.swing.JLabel();
        JL_IsActive = new javax.swing.JLabel();

        setBackground(new java.awt.Color(153, 255, 255));

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

        btn_con.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_con.setText("Có");
        btn_con.setToolTipText("");
        btn_con.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_conActionPerformed(evt);
            }
        });

        btn_mat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_mat.setText("Không");
        btn_mat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_matActionPerformed(evt);
            }
        });

        btn_tat_ca.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_tat_ca.setText("Tất cả");
        btn_tat_ca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tat_caActionPerformed(evt);
            }
        });

        btn_sap_xep.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_sap_xep.setText("Sắp Xếp");
        btn_sap_xep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sap_xepActionPerformed(evt);
            }
        });

        cob_toa_nha.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cob_toa_nha.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Khả Dụng");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Tìm Mã");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Tòa Nhà");

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
                        .addComponent(btn_con)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_mat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_tat_ca))
                    .addComponent(txt_tim_kiem))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btn_tim_kiem)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cob_toa_nha, 0, 161, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_sap_xep, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
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
                    .addComponent(btn_con)
                    .addComponent(jLabel12)
                    .addComponent(btn_mat)
                    .addComponent(btn_tat_ca)
                    .addComponent(jLabel14)
                    .addComponent(cob_toa_nha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_sap_xep))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbl_resident_card.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_resident_card.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Mã Thẻ", "Khách Hàng", "Tòa Nhà", "Còn/Mất"
            }
        ));
        tbl_resident_card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_resident_cardMouseClicked(evt);
            }
        });
        sp_resident_card.setViewportView(tbl_resident_card);

        txt_tin_nhan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_tin_nhan.setText("Đang hiển thị danh sách tất cả thẻ cư dân dùng dịch vụ");
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
                    .addComponent(sp_resident_card)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_tin_nhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_tin_nhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sp_resident_card, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel2.setRequestFocusEnabled(false);
        jPanel2.setVerifyInputWhenFocusTarget(false);

        txt_pk_resident_card.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_pk_resident_card.setEnabled(false);

        txt_customer_id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_customer_id.setEnabled(false);

        txt_building_id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_building_id.setEnabled(false);

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

        btn_chon_customer.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_chon_customer.setText("Chọn");
        btn_chon_customer.setEnabled(false);
        btn_chon_customer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chon_customerActionPerformed(evt);
            }
        });

        cob_con_mat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cob_con_mat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Còn", "Mất" }));
        cob_con_mat.setEnabled(false);

        JL_Title.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        JL_Title.setText("Thông Tin Thẻ");
        JL_Title.setToolTipText("");

        JL_MaTheCuDan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JL_MaTheCuDan.setText("Mã Thẻ:");

        JL_MaKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JL_MaKhachHang.setText("Mã Khách Hàng:");

        JL_building.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JL_building.setText("Tòa Nhà");

        JL_IsActive.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JL_IsActive.setText("Khả Dụng");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(JL_building, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_building_id))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(JL_Title, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JL_MaTheCuDan, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JL_MaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_pk_resident_card, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_reset, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_customer_id, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_chon_customer, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(JL_IsActive, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cob_con_mat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(82, 82, 82))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_delete)))
                .addGap(0, 22, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_Title)
                    .addComponent(btn_reset))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_MaTheCuDan)
                    .addComponent(txt_pk_resident_card, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_customer_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_chon_customer))
                    .addComponent(JL_MaKhachHang))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_building_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_building))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_IsActive)
                    .addComponent(cob_con_mat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInsert)
                    .addComponent(btn_update)
                    .addComponent(btn_delete))
                .addContainerGap(134, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_resident_cardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_resident_cardMouseClicked
        // TODO add your handling code here:
        int selectedRow = tbl_resident_card.getSelectedRow();
        // Kiểm tra xem có hàng nào được chọn không
        if (selectedRow != -1) {
            // Lấy dữ liệu từ bảng và gán vào các biến
            int residentCardId = Integer.parseInt(tbl_resident_card.getValueAt(selectedRow, 0).toString());
            String customer = tbl_resident_card.getValueAt(selectedRow, 1).toString();
            String building = tbl_resident_card.getValueAt(selectedRow, 2).toString();
            boolean isActive = Boolean.parseBoolean(tbl_resident_card.getValueAt(selectedRow, 3).toString());
            
            // Hiển thị dữ liệu lên các ô nhập liệu
            txt_pk_resident_card.setText(String.valueOf(residentCardId));
            txt_customer_id.setText(customer);
            txt_building_id.setText(building);
            if (isActive) { 
                cob_con_mat.setSelectedIndex(1);
            }
            else { 
                cob_con_mat.setSelectedIndex(2);
            }
            
            this.chooseCustomerId = this.residents.get(selectedRow).getCustomer_id();
            showUpdate();
        }
        
    }//GEN-LAST:event_tbl_resident_cardMouseClicked

    private void btnInsertMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInsertMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInsertMouseClicked

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        // TODO add your handling code here:
        ResidentCard resident = new ResidentCard(chooseCustomerId, true);
        String check = "";
        check = ResidentCardDAO.getInstance().insert(resident);
        if (check.equals("Thêm Thành Công")) {
            resetFields();
            initTable(); 
            loadData();
            fillTable();
            this.dataGlobal.updateArrayResidentCard();
        }
        else { 
            this.SetLog(GetError(check));
            return;
        }
        this.SetLog(check);
        return;
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btn_resetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_resetMouseClicked

    }//GEN-LAST:event_btn_resetMouseClicked

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        // TODO add your handling code here:
        resetFields();
    }//GEN-LAST:event_btn_resetActionPerformed

    private void txt_tin_nhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tin_nhanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tin_nhanActionPerformed

    private void txt_tim_kiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tim_kiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tim_kiemActionPerformed

    private void btn_conActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_conActionPerformed
        // TODO add your handling code here:
        initTable();
        int count = -1;
        String crBuilding_name = "";
        String crFull_name = "";
        for (ResidentCard res : residents) {
            if (res.isIs_active()) {
                count += 1;
                crBuilding_name = building_names.get(count);
                crFull_name = full_names.get(count);
                tblModel.addRow(new String[] {String.valueOf(res.getPk_resident_card()), crFull_name, crBuilding_name, String.valueOf(res.isIs_active())} );
            }
        }
        tblModel.fireTableDataChanged();
        resetFields();
    }//GEN-LAST:event_btn_conActionPerformed

    private void btn_matActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_matActionPerformed
        // TODO add your handling code here:
        initTable();
        int count = -1;
        String crBuilding_name = "";
        String crFull_name = "";
        for (ResidentCard res : residents) {
            if (!res.isIs_active()) {
                count += 1;
                crBuilding_name = building_names.get(count);
                crFull_name = full_names.get(count);
                tblModel.addRow(new String[] {String.valueOf(res.getPk_resident_card()), crFull_name, crBuilding_name, String.valueOf(res.isIs_active())} );
            }
        }
        tblModel.fireTableDataChanged();
        resetFields();
    }//GEN-LAST:event_btn_matActionPerformed

    private void btn_tim_kiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tim_kiemActionPerformed
        // TODO add your handling code here:
        initTable();
        int count = -1;
        String crBuilding_name = "";
        String crFull_name = "";
        for (ResidentCard res : residents) {
            if (Library.Library.StringOnString(txt_tim_kiem.getText().toString().trim(), String.valueOf(res.getPk_resident_card()))) {
                count += 1;
                crBuilding_name = building_names.get(count);
                crFull_name = full_names.get(count);
                tblModel.addRow(new String[] {String.valueOf(res.getPk_resident_card()), crFull_name, crBuilding_name, String.valueOf(res.isIs_active())} );
            }
        }
        tblModel.fireTableDataChanged();
        cob_toa_nha.setSelectedIndex(0);
        resetFields();
    }//GEN-LAST:event_btn_tim_kiemActionPerformed

    private void btn_tat_caActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tat_caActionPerformed
        // TODO add your handling code here:
        initTable();
        resetFields();
        fillTable();
    }//GEN-LAST:event_btn_tat_caActionPerformed

    private void btn_sap_xepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sap_xepActionPerformed
        // TODO add your handling code here:
        String buildingName = cob_toa_nha.getSelectedItem().toString().trim();
        txt_tim_kiem.setText("");
        if (buildingName == "None") {
            initTable();
            resetFields();
            fillTable();
            return;
        }
        initTable();
        int count = -1;
        String crBuilding_name = "";
        String crFull_name = "";
        for (ResidentCard res : residents) {
            count += 1;
            crBuilding_name = building_names.get(count).trim();
            if (crBuilding_name.equals(buildingName)) {
                crFull_name = full_names.get(count);
                tblModel.addRow(new String[] {String.valueOf(res.getPk_resident_card()), crFull_name, crBuilding_name, String.valueOf(res.isIs_active())} );
            }
        }
        tblModel.fireTableDataChanged();
        resetFields();
    }//GEN-LAST:event_btn_sap_xepActionPerformed

    private void btn_chon_customerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chon_customerActionPerformed
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
                String[] header = new String[] {"ID KH", "ID Tòa Nhà", "Họ Tên", "Căn Cước", "Ngày Sinh", "Giới Tính", "Điện Thoại", "Thường Trú", "Quốc Tịch", "Cư Dân"};
                this.tableModel.setColumnIdentifiers(header);
                this.table.setModel(tableModel);
                this.table.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int row = table.rowAtPoint(e.getPoint());
                        txt_customer_id.setText((String) table.getValueAt(row, 2));
                        chooseCustomerId = Integer.parseInt((String)table.getValueAt(row, 0));
                        logSelection.setVisible(false);
                        viewmain.setEnabled(true);
                        viewmain.requestFocus();
                    }
                });
                dataGlobal.updateArrCustomer();
                for (Customer c : dataGlobal.getArrayCustomer()) {
                    if (c.isIs_active()) {
                        tableModel.addRow(new String[] {String.valueOf(c.getCustomer_id()), String.valueOf(c.getBuilding_id()), 
                                                        c.getFull_name(), c.getSsn(), String.valueOf(c.getDate_of_birth()), 
                                                        c.getGender(), c.getPhone_number(), c.getAddress(), c.getNationality(), String.valueOf(c.isIs_active())
                        });
                    }
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
    }//GEN-LAST:event_btn_chon_customerActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
        
        int residentCardId = Integer.parseInt(txt_pk_resident_card.getText().toString().trim());
        
        boolean isActive = true;
        if (cob_con_mat.getSelectedIndex() == 2) { 
            isActive = false;
        }
        ResidentCard resident = new ResidentCard(residentCardId, chooseCustomerId, isActive);
        
        String check = ResidentCardDAO.getInstance().update(resident);
        if (check.equals("Cập Nhật Thành Công")) {
            resetFields();
            initTable(); 
            loadData();
            fillTable();
            this.dataGlobal.updateArrayResidentCard();
        }
        else { 
            this.SetLog(GetError(check));
            return;
        }
        this.SetLog(check);
        return;
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
        int residentCardId = Integer.parseInt(txt_pk_resident_card.getText().toString().trim());
        String check = ResidentCardDAO.getInstance().delete(residentCardId);
        if (check.equals("Xóa Thành Công")) {
            resetFields();
            initTable(); 
            loadData();
            fillTable();
            this.dataGlobal.updateArrayResidentCard();
        }
        else { 
            this.SetLog(GetError(check));
            return;
        }
        this.SetLog(check);
        return;
    }//GEN-LAST:event_btn_deleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JL_IsActive;
    private javax.swing.JLabel JL_MaKhachHang;
    private javax.swing.JLabel JL_MaTheCuDan;
    private javax.swing.JLabel JL_Title;
    private javax.swing.JLabel JL_building;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btn_chon_customer;
    private javax.swing.JButton btn_con;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_mat;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_sap_xep;
    private javax.swing.JButton btn_tat_ca;
    private javax.swing.JButton btn_tim_kiem;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox<String> cob_con_mat;
    private javax.swing.JComboBox<String> cob_toa_nha;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane sp_resident_card;
    private javax.swing.JTable tbl_resident_card;
    private javax.swing.JTextField txt_building_id;
    private javax.swing.JTextField txt_customer_id;
    private javax.swing.JTextField txt_pk_resident_card;
    private javax.swing.JTextField txt_tim_kiem;
    private javax.swing.JTextField txt_tin_nhan;
    // End of variables declaration//GEN-END:variables
}

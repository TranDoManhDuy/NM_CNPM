/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.GUIXE;

import DAO.CustomerDAO;
import DAO.ResidentCardDAO;
import GUI.ViewMain;
import Model.ResidentCard;
import java.util.ArrayList;
import java.util.Map;
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
    /**
     * Creates new form GUI_Customer
     */
    public GUI_ResidentCard(ViewMain viewmain) {
        this.viewmain = viewmain;
        initComponents(); 
        initTable();
        fillTable();
//        addDocumentListeners();
    }
    
    public void initTable() { 
        String[] header = new String[] {"Mã Thẻ", "Khách Hàng", "Tòa Nhà", "Còn/Mất"};
        tblModel.setColumnIdentifiers(header);
        tblModel.setRowCount(0);
        tbl_resident_card.setModel(tblModel);
//        btn_insert.setEnabled(false);
    }
    
    public void fillTable() {
        try {
            Map<String, ArrayList<?>> data =  ResidentCardDAO.getInstance().getAllData();
            ArrayList<ResidentCard> residents = (ArrayList<ResidentCard>) data.get("customers");
            ArrayList<String> building_names = (ArrayList<String>) data.get("building_names");
            ArrayList<String> full_names = (ArrayList<String>) data.get("full_names");
            int count = -1;
            String crBuilding_name = "";
            String crFull_name = "";
            for (ResidentCard res : residents) { 
                try {
                    count += 1;
                    crBuilding_name = building_names.get(count);
                    crFull_name = full_names.get(count);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                tblModel.addRow(new String[] {String.valueOf(res.getPk_resident_card()), crFull_name, crBuilding_name, String.valueOf(res.isIs_active())} );
            }
        }
        catch (Exception e) { 
                e.printStackTrace();
            }
        tblModel.fireTableDataChanged();
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
        btn_chon_building = new javax.swing.JButton();
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

        btn_con.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_con.setText("Còn");
        btn_con.setToolTipText("");
        btn_con.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_conActionPerformed(evt);
            }
        });

        btn_mat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_mat.setText("Mất");
        btn_mat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_matActionPerformed(evt);
            }
        });

        btn_tat_ca.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_tat_ca.setText("Tất cả");

        btn_sap_xep.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_sap_xep.setText("Sắp Xếp");

        cob_toa_nha.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cob_toa_nha.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Còn/Mất");

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
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
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

        btn_delete.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_delete.setText("Xóa");

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

        btn_chon_building.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_chon_building.setText("Chọn");

        cob_con_mat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cob_con_mat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Còn", "Mất" }));

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
        JL_IsActive.setText("Còn/Mất");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(JL_building, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_building_id, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_chon_building, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
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
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_building_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_chon_building))
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
                .addContainerGap(133, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
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
    }//GEN-LAST:event_tbl_resident_cardMouseClicked

    private void btnInsertMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInsertMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInsertMouseClicked

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btn_resetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_resetMouseClicked

    }//GEN-LAST:event_btn_resetMouseClicked

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_resetActionPerformed

    private void txt_tin_nhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tin_nhanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tin_nhanActionPerformed

    private void txt_tim_kiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tim_kiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tim_kiemActionPerformed

    private void btn_conActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_conActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_conActionPerformed

    private void btn_matActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_matActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_matActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JL_IsActive;
    private javax.swing.JLabel JL_MaKhachHang;
    private javax.swing.JLabel JL_MaTheCuDan;
    private javax.swing.JLabel JL_Title;
    private javax.swing.JLabel JL_building;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btn_chon_building;
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

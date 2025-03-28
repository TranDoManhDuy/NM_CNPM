/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.GUIXE;

import GUI.ViewMain;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class GUI_Vehicle extends javax.swing.JPanel {

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
    public GUI_Vehicle(ViewMain viewmain) {
        this.viewmain = viewmain;
        initComponents(); 
        initTable();
//        fillTable();
//        addDocumentListeners();
    }
    
    public void initTable() { 
        String[] header = new String[] {"Mã Phương Tiện", "Mã Nhận Dạng", "Loại Phương Tiện", "Tên Phương Tiện", "Màu Phương Tiện"};
        tblModel.setColumnIdentifiers(header);
        tblModel.setRowCount(2);
        tbl_vehicle.setModel(tblModel);
//        btn_insert.setEnabled(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        sp_vehicle = new javax.swing.JScrollPane();
        tbl_vehicle = new javax.swing.JTable();
        txt_tin_nhan = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        txt_tim_kiem = new javax.swing.JTextField();
        btn_tim_kiem = new javax.swing.JButton();
        btn_sap_xep = new javax.swing.JButton();
        cob_loai_phuong_tien = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txt_vehicle_id = new javax.swing.JTextField();
        txt_identification_code = new javax.swing.JTextField();
        txt_vehicle_type = new javax.swing.JTextField();
        txt_vehicle_name = new javax.swing.JTextField();
        txt_vehicle_color = new javax.swing.JTextField();
        btn_insert = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_xoa = new javax.swing.JButton();
        btn_reset = new javax.swing.JButton();
        btn_chon_vehicle_type = new javax.swing.JButton();
        JL_title = new javax.swing.JLabel();
        JL_MaPhuongTien = new javax.swing.JLabel();
        JL_MaNhanDang = new javax.swing.JLabel();
        JL_LoaiPhuongTien = new javax.swing.JLabel();
        JL_TenPhuongTien = new javax.swing.JLabel();
        JLMauPhuongTien = new javax.swing.JLabel();

        setBackground(new java.awt.Color(153, 255, 255));

        tbl_vehicle.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_vehicle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Phương Tiện", "Mã Nhận Dạng", "Loại Phương Tiện", "Tên Phương Tiện", "Màu Phương Tiện"
            }
        ));
        tbl_vehicle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_vehicleMouseClicked(evt);
            }
        });
        sp_vehicle.setViewportView(tbl_vehicle);

        txt_tin_nhan.setText("Đang hiển thị danh sách tất cả thẻ phương tiện");
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

        btn_sap_xep.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_sap_xep.setText("Sắp Xếp");

        cob_loai_phuong_tien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cob_loai_phuong_tien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Tìm Mã Nhận Dạng");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Loại Phương Tiện");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cob_loai_phuong_tien, 0, 228, Short.MAX_VALUE)
                    .addComponent(txt_tim_kiem))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_sap_xep, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                    .addComponent(btn_tim_kiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(jLabel14)
                    .addComponent(cob_loai_phuong_tien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_sap_xep))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sp_vehicle)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_tin_nhan, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 215, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(txt_tin_nhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp_vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setRequestFocusEnabled(false);
        jPanel2.setVerifyInputWhenFocusTarget(false);

        txt_vehicle_id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_vehicle_id.setEnabled(false);

        txt_identification_code.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txt_vehicle_type.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_vehicle_type.setEnabled(false);

        txt_vehicle_name.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txt_vehicle_color.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

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

        btn_xoa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_xoa.setText("Xóa");

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

        btn_chon_vehicle_type.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_chon_vehicle_type.setText("Chọn");

        JL_title.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        JL_title.setText("Thông Tin phương Tiện");

        JL_MaPhuongTien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JL_MaPhuongTien.setText("Mã Phương Tiện:");

        JL_MaNhanDang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JL_MaNhanDang.setText("Mã Nhận Dạng:");

        JL_LoaiPhuongTien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JL_LoaiPhuongTien.setText("Loại Phương Tiện:");

        JL_TenPhuongTien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JL_TenPhuongTien.setText("Tên Phương Tiện:");

        JLMauPhuongTien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JLMauPhuongTien.setText("Màu Phương Tiện:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(JL_title, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(btn_reset))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JL_MaPhuongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JL_MaNhanDang, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JL_LoaiPhuongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JL_TenPhuongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JLMauPhuongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_vehicle_color, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_vehicle_name, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_identification_code, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_vehicle_id, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_vehicle_type, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_chon_vehicle_type, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_insert, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_xoa)
                        .addGap(36, 36, 36)))
                .addGap(0, 21, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_title)
                    .addComponent(btn_reset))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_MaPhuongTien)
                    .addComponent(txt_vehicle_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_MaNhanDang)
                    .addComponent(txt_identification_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_vehicle_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_chon_vehicle_type))
                    .addComponent(JL_LoaiPhuongTien))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_TenPhuongTien)
                    .addComponent(txt_vehicle_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLMauPhuongTien)
                    .addComponent(txt_vehicle_color, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_insert)
                    .addComponent(btn_update)
                    .addComponent(btn_xoa))
                .addContainerGap(107, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_vehicleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_vehicleMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_vehicleMouseClicked

    private void btn_insertMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_insertMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_insertMouseClicked

    private void btn_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_insertActionPerformed

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLMauPhuongTien;
    private javax.swing.JLabel JL_LoaiPhuongTien;
    private javax.swing.JLabel JL_MaNhanDang;
    private javax.swing.JLabel JL_MaPhuongTien;
    private javax.swing.JLabel JL_TenPhuongTien;
    private javax.swing.JLabel JL_title;
    private javax.swing.JButton btn_chon_vehicle_type;
    private javax.swing.JButton btn_insert;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_sap_xep;
    private javax.swing.JButton btn_tim_kiem;
    private javax.swing.JButton btn_update;
    private javax.swing.JButton btn_xoa;
    private javax.swing.JComboBox<String> cob_loai_phuong_tien;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane sp_vehicle;
    private javax.swing.JTable tbl_vehicle;
    private javax.swing.JTextField txt_identification_code;
    private javax.swing.JTextField txt_tim_kiem;
    private javax.swing.JTextField txt_tin_nhan;
    private javax.swing.JTextField txt_vehicle_color;
    private javax.swing.JTextField txt_vehicle_id;
    private javax.swing.JTextField txt_vehicle_name;
    private javax.swing.JTextField txt_vehicle_type;
    // End of variables declaration//GEN-END:variables
}

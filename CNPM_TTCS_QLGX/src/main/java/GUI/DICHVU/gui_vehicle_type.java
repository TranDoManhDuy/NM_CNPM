/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.DICHVU;

import Annotation.LogConfirm;
import Annotation.LogMessage;
import Annotation.LogSelection;
import DAO.VehicleTypeDAO;
import GUI.ViewMain;
import Model.VehicleType;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author manhh
 */
public class gui_vehicle_type extends javax.swing.JPanel {
    private DefaultTableModel tableModel;
    private ViewMain viewmain;
    private LogConfirm logConfirm;
    private LogMessage logMessage;
    private LogSelection logSelection;
    private boolean cursorBreak = false;
    private ArrayList<VehicleType> dataVehicleType = new ArrayList<>();
    /**
     * Creates new form gui_vehicle_type
     */
    public gui_vehicle_type() {}
    public gui_vehicle_type(ViewMain viewmain, LogConfirm logConfirm, LogMessage logMessage, LogSelection logSelection) {
        this.viewmain = viewmain;
        this.logConfirm = logConfirm;
        this.logMessage = logMessage;
        this.logSelection = logSelection;
        
        initComponents();
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        combo_trangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Được phép", "Không được phép", ""}));
        table_loaiphuongtien.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e) {
                btn_them.setEnabled(false);
                btn_capnhat.setEnabled(true);
                btn_xoa.setEnabled(true);
                combo_trangthai.setEnabled(true);
                
                int row = table_loaiphuongtien.rowAtPoint(e.getPoint());
                
                VehicleType vehicleType = dataVehicleType.get(row);
                
                txt_idloaiphuongtien.setText(String.valueOf(vehicleType.getVehicle_type_id()));
                txt_tenloaiphuongtien.setText(vehicleType.getVehicle_type_name());
                if (vehicleType.isIsPermission()) {
                    combo_trangthai.setSelectedIndex(0);
                } else {combo_trangthai.setSelectedIndex(1);}
            }
        });
        initTable();
        loadData();
        fillTable();
        combo_trangthai.setSelectedIndex(2);
        table_loaiphuongtien.setRowHeight(30);
    }
    private void initTable() {
        String[] header = new String[] {"ID Loại Phương Tiện", "Tên Loại Phương Tiện", "Còn cho phép"};
        tableModel.setColumnIdentifiers(header);
        table_loaiphuongtien.setModel(tableModel);
    }
    private void fillTable() {
        tableModel.setRowCount(0);
        for (VehicleType vt : this.dataVehicleType) {
            String trangthai;
            if (vt.isIsPermission() == true) {
                trangthai = "Được phép";
            }
            else {
                trangthai = "Không cho phép";
            }
            tableModel.addRow(new String[] {String.valueOf(vt.getVehicle_type_id()), vt.getVehicle_type_name(), trangthai});
        }
        tableModel.fireTableDataChanged();
        txt_tinnhan.setText("Đang hiển thị toàn bộ danh sách các loại phương tiện");
    }
    private void loadData() {
        this.dataVehicleType = VehicleTypeDAO.getInstance().getList();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_loaiphuongtien = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        txt_idloaiphuongtien = new javax.swing.JTextField();
        label_id_dangki = new javax.swing.JLabel();
        btn_them = new javax.swing.JButton();
        btn_capnhat = new javax.swing.JButton();
        btn_xoa = new javax.swing.JButton();
        inforDetail = new javax.swing.JLabel();
        btn_datlai = new javax.swing.JButton();
        label_id_khachhang = new javax.swing.JLabel();
        txt_tenloaiphuongtien = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        combo_trangthai = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        txt_timkiem = new javax.swing.JTextField();
        btn_timkiem = new javax.swing.JButton();
        btn_conchophep = new javax.swing.JButton();
        btn_khongconchophep = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        btn_tatca = new javax.swing.JButton();
        txt_tinnhan = new javax.swing.JTextField();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setBackground(new java.awt.Color(204, 255, 255));

        table_loaiphuongtien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table_loaiphuongtien);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
        );

        txt_idloaiphuongtien.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txt_idloaiphuongtien.setFocusable(false);

        label_id_dangki.setText("ID loại phương tiện");

        btn_them.setText("Thêm");
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        btn_capnhat.setText("Cập nhật");
        btn_capnhat.setEnabled(false);
        btn_capnhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_capnhatActionPerformed(evt);
            }
        });

        btn_xoa.setText("Xóa");
        btn_xoa.setEnabled(false);
        btn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaActionPerformed(evt);
            }
        });

        inforDetail.setText("Thông tin chi tiết");

        btn_datlai.setText("Đặt lại");
        btn_datlai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_datlaiActionPerformed(evt);
            }
        });

        label_id_khachhang.setText("Tên loại phương tiện");

        txt_tenloaiphuongtien.setDisabledTextColor(new java.awt.Color(102, 102, 102));

        jLabel1.setText("Trạng thái");

        combo_trangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        combo_trangthai.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(inforDetail)
                .addGap(128, 128, 128))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_datlai))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label_id_dangki)
                                    .addComponent(label_id_khachhang)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_idloaiphuongtien, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                                    .addComponent(txt_tenloaiphuongtien)
                                    .addComponent(combo_trangthai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                .addComponent(btn_them)
                                .addGap(18, 18, 18)
                                .addComponent(btn_capnhat)
                                .addGap(18, 18, 18)
                                .addComponent(btn_xoa)))))
                .addGap(32, 32, 32))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(inforDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_id_dangki)
                    .addComponent(txt_idloaiphuongtien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_id_khachhang)
                    .addComponent(txt_tenloaiphuongtien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(combo_trangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(btn_datlai)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_them)
                    .addComponent(btn_capnhat)
                    .addComponent(btn_xoa))
                .addGap(18, 18, 18))
        );

        txt_timkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_timkiemActionPerformed(evt);
            }
        });

        btn_timkiem.setText("Tìm kiếm tên");
        btn_timkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timkiemActionPerformed(evt);
            }
        });

        btn_conchophep.setText("Còn cho phép");
        btn_conchophep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_conchophepActionPerformed(evt);
            }
        });

        btn_khongconchophep.setText("Không còn cho phép");
        btn_khongconchophep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_khongconchophepActionPerformed(evt);
            }
        });

        jLabel21.setText("Danh sách:");

        btn_tatca.setText("Tất cả");
        btn_tatca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tatcaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_conchophep)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_khongconchophep)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_tatca))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btn_timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_timkiem)))
                .addContainerGap(265, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_timkiem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_conchophep)
                    .addComponent(btn_khongconchophep)
                    .addComponent(jLabel21)
                    .addComponent(btn_tatca))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        txt_tinnhan.setText("Đang hiển thị danh sách các loại phương tiện được cho phép gửi ở nhà xe");
        txt_tinnhan.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_tinnhan.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txt_tinnhan, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(401, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_tinnhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(188, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(134, 134, 134)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(23, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_datlaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_datlaiActionPerformed
        // TODO add your handling code here:
        txt_idloaiphuongtien.setText("");
        txt_tenloaiphuongtien.setText("");
        combo_trangthai.setSelectedIndex(2);
        combo_trangthai.setEnabled(false);
        
        btn_them.setEnabled(true);
        btn_capnhat.setEnabled(false);
        btn_xoa.setEnabled(false);
    }//GEN-LAST:event_btn_datlaiActionPerformed
    private void processDelete() {
        String rs = VehicleTypeDAO.getInstance().delete(Integer.parseInt(txt_idloaiphuongtien.getText()));
        
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
        loadData();
        fillTable();
    }
    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
        // TODO add your handling code here:
        this.viewmain.setEnabled(false);
        this.cursorBreak = false;

        this.logConfirm = new LogConfirm("Bạn có chắc là muốn xóa ?") {
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
                processDelete();
            }
        };
        worker.execute();
        worker = null;
    }//GEN-LAST:event_btn_xoaActionPerformed

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        // TODO add your handling code here:
        VehicleType vehicle_type = new VehicleType();
        vehicle_type.setIsPermission((combo_trangthai.getSelectedIndex() == 0));
        vehicle_type.setVehicle_type_name(txt_tenloaiphuongtien.getText());
        
        String rs = VehicleTypeDAO.getInstance().insert(vehicle_type);
        
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
        loadData();
        fillTable();
    }//GEN-LAST:event_btn_themActionPerformed

    private void txt_timkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timkiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timkiemActionPerformed

    private void btn_conchophepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_conchophepActionPerformed
        // TODO add your handling code here:
        tableModel.setRowCount(0);
        
        int index = 0;
        for (VehicleType vt : this.dataVehicleType) {
            String trangthai;
            if (vt.isIsPermission() == true) {
                trangthai = "Được phép";
            }
            else {
                trangthai = "Không cho phép";
            }
            if (vt.isIsPermission()) {
                index++;
                tableModel.addRow(new String[] {String.valueOf(vt.getVehicle_type_id()), vt.getVehicle_type_name(), trangthai});
            }
        }
        tableModel.fireTableDataChanged();
        txt_tinnhan.setText("Đang hiển thị danh sách các loại phương tiện được cho phép");
        if (index == this.dataVehicleType.size()) {
            txt_tinnhan.setText("Đang hiển thị toàn bộ danh sách các loại phương tiện");
        }
    }//GEN-LAST:event_btn_conchophepActionPerformed

    private void btn_khongconchophepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_khongconchophepActionPerformed
        // TODO add your handling code here:
        tableModel.setRowCount(0);
        
        int index = 0;
        for (VehicleType vt : this.dataVehicleType) {
            String trangthai;
            if (vt.isIsPermission() == true) {
                trangthai = "Được phép";
            }
            else {
                trangthai = "Không cho phép";
            }
            if (!vt.isIsPermission()) {
                index++;
                tableModel.addRow(new String[] {String.valueOf(vt.getVehicle_type_id()), vt.getVehicle_type_name(), trangthai});
            }
        }
        tableModel.fireTableDataChanged();
        txt_tinnhan.setText("Đang hiển thị danh sách các loại phương tiện không còn được cho phép");
        if (index == this.dataVehicleType.size()) {
            txt_tinnhan.setText("Đang hiển thị toàn bộ danh sách các loại phương tiện");
        }
    }//GEN-LAST:event_btn_khongconchophepActionPerformed

    private void btn_timkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timkiemActionPerformed
        // TODO add your handling code here:
        tableModel.setRowCount(0);
        
        int index = 0;
        for (VehicleType vt : this.dataVehicleType) {
            String trangthai;
            if (vt.isIsPermission() == true) {
                trangthai = "Được phép";
            }
            else {
                trangthai = "Không cho phép";
            }
            if (Library.Library.StringOnString(txt_timkiem.getText(), vt.getVehicle_type_name())) {
                index++;
                tableModel.addRow(new String[] {String.valueOf(vt.getVehicle_type_id()), vt.getVehicle_type_name(), trangthai});
            }
        }
        tableModel.fireTableDataChanged();
        txt_tinnhan.setText("Đang hiển thị danh sách các loại phương tiện lọc theo tên");
        if (index == this.dataVehicleType.size()) {
            txt_tinnhan.setText("Đang hiển thị toàn bộ danh sách các loại phương tiện");
        }
    }//GEN-LAST:event_btn_timkiemActionPerformed

    private void btn_tatcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tatcaActionPerformed
        // TODO add your handling code here:
        fillTable();
    }//GEN-LAST:event_btn_tatcaActionPerformed
    private void processUpdate() {
        VehicleType vehicle_type = new VehicleType();
        
        vehicle_type.setIsPermission((combo_trangthai.getSelectedIndex() == 0));
        vehicle_type.setVehicle_type_name(txt_tenloaiphuongtien.getText());
        vehicle_type.setVehicle_type_id(Integer.parseInt(txt_idloaiphuongtien.getText()));
        
        String rs = VehicleTypeDAO.getInstance().update(vehicle_type);
        
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
        loadData();
        fillTable();
    }
    private void btn_capnhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_capnhatActionPerformed
        if (!Library.Library.isValidString(txt_tenloaiphuongtien.getText()) || txt_tenloaiphuongtien.getText().length() < 3) {
            this.viewmain.setEnabled(false);
            this.logMessage = new LogMessage("Tên chỉ gồm chữ cái, chữ số và độ dài >= 3") {
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
        this.viewmain.setEnabled(false);
        this.cursorBreak = false;

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
                processUpdate();
            }
        };
        worker.execute();
        worker = null;
    }//GEN-LAST:event_btn_capnhatActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_capnhat;
    private javax.swing.JButton btn_conchophep;
    private javax.swing.JButton btn_datlai;
    private javax.swing.JButton btn_khongconchophep;
    private javax.swing.JButton btn_tatca;
    private javax.swing.JButton btn_them;
    private javax.swing.JButton btn_timkiem;
    private javax.swing.JButton btn_xoa;
    private javax.swing.JComboBox<String> combo_trangthai;
    private javax.swing.JLabel inforDetail;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_id_dangki;
    private javax.swing.JLabel label_id_khachhang;
    private javax.swing.JTable table_loaiphuongtien;
    private javax.swing.JTextField txt_idloaiphuongtien;
    private javax.swing.JTextField txt_tenloaiphuongtien;
    private javax.swing.JTextField txt_timkiem;
    private javax.swing.JTextField txt_tinnhan;
    // End of variables declaration//GEN-END:variables
}

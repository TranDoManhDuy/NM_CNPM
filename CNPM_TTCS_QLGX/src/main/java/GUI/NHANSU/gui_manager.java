package GUI.NHANSU;

import DAO.ManagerDAO;
import DAO.SupervisorDAO;
import DatabaseHelper.OpenConnection;
import GUI.ViewMain;
import Model.Manager;
import Model.Supervisor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class gui_manager extends javax.swing.JPanel {

    private DefaultTableModel tableModel;
    private ViewMain viewmain;

    public gui_manager(ViewMain viewmain) {
        this.viewmain = viewmain;
        initComponents();       
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
        }
        };
        initTable();
        fillTable();
    }    
    public void initTable() {
        String[] header = new String[] {"ID Quản lí", "Họ tên nhân viên", "ID Nhân viên", "Họ tên nhân viên"};
        tableModel.setColumnIdentifiers(header);
        Table_Supervisor.setModel(tableModel);
    }
    
    public void fillTable() {
    // Câu lệnh SQL gọi stored procedure hoặc truy vấn với JOIN để lấy thông tin nhân viên và quản lý
    String sql = "EXEC Supervisor_render";  // Hoặc câu lệnh SQL thay thế tùy theo yêu cầu
    
    try (
        // Mở kết nối cơ sở dữ liệu
        Connection conn = OpenConnection.getConnection();
        // Sử dụng Statement để thực thi câu truy vấn
        Statement stmt = conn.createStatement();
        // Thực thi câu lệnh SQL
        ResultSet result = stmt.executeQuery(sql);
    ) {
        // Xóa các dòng cũ trong bảng
        tableModel.setRowCount(0);

        // Duyệt qua từng dòng kết quả từ ResultSet
        while (result.next()) {
            int manager_id = result.getInt("manager_id");
            String manager_name = result.getString("manager_name");
            int supervised_staff_id = result.getInt("supervised_staff_id");  // ID của nhân viên mà quản lý giám sát
            String supervised_staff_name = result.getString("supervised_staff_name");  // Họ tên nhân viên

            // Thêm dữ liệu vào bảng
            tableModel.addRow(new Object[]{
                manager_id,                // ID của quản lý
                manager_name,            // Tên quản lý               // Chức vụ của quản lý
                supervised_staff_id,     // ID của nhân viên dưới quyền
                supervised_staff_name    // Tên nhân viên dưới quyền
            });
        }

        // Cập nhật bảng sau khi thêm dữ liệu
        tableModel.fireTableDataChanged();
    } catch (Exception e) {
        e.printStackTrace();  // In ra lỗi nếu có
}

    Table_Supervisor.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int selectedRow = Table_Supervisor.getSelectedRow();
            if (selectedRow >= 0) {
                txtQuanly.setText(Table_Supervisor.getValueAt(selectedRow, 0).toString());
                txtTenquanly.setText(Table_Supervisor.getValueAt(selectedRow, 1).toString());  
                txtNhanvien.setText(Table_Supervisor.getValueAt(selectedRow, 2).toString());
                txtTennhanvien.setText(Table_Supervisor.getValueAt(selectedRow, 3).toString());
            }
        }
        
    });
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Panel_DS = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Table_Supervisor = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtQuanly = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTenquanly = new javax.swing.JTextField();
        btnLammoi = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtNhanvien = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTennhanvien = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        btnXoa = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jTextField4 = new javax.swing.JTextField();
        btnLammoiTK = new javax.swing.JButton();
        btnTim = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        Panel_DS.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("DANH SÁCH GIÁM SÁT");

        Table_Supervisor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Quản lý", "Họ tên quản lý", "ID Nhân viên", "Họ tên nhân viên"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(Table_Supervisor);

        javax.swing.GroupLayout Panel_DSLayout = new javax.swing.GroupLayout(Panel_DS);
        Panel_DS.setLayout(Panel_DSLayout);
        Panel_DSLayout.setHorizontalGroup(
            Panel_DSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_DSLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_DSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_DSLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE))
                .addContainerGap())
        );
        Panel_DSLayout.setVerticalGroup(
            Panel_DSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_DSLayout.createSequentialGroup()
                .addComponent(jLabel11)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("ID Quản lý");

        jLabel2.setText("Họ tên quản lý");

        btnLammoi.setText("Làm mới");
        btnLammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLammoiActionPerformed(evt);
            }
        });

        jLabel3.setText("ID Nhân viên");

        jLabel4.setText("Họ tên nhân viên");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtQuanly, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnLammoi)))
                        .addGap(36, 36, 36))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(txtNhanvien)
                            .addComponent(jLabel4)
                            .addComponent(txtTenquanly, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                            .addComponent(txtTennhanvien))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtQuanly, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenquanly, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTennhanvien, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLammoi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("CHỨC NĂNG");

        btnXoa.setText("XÓA");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnThem.setText("THÊM");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jButton7.setText("SỬA");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel13))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnLammoiTK.setText("Làm mới");
        btnLammoiTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLammoiTKActionPerformed(evt);
            }
        });

        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTim)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLammoiTK, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLammoiTK, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTextField4))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Panel_DS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Panel_DS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1125, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 485, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        String keyword = jTextField4.getText().trim().toLowerCase();

        DefaultTableModel model = (DefaultTableModel) Table_Supervisor.getModel();
        DefaultTableModel newModel = new DefaultTableModel(new Object[]{"ID Quản lí", "Họ tên nhân viên", "ID Nhân viên", "Họ tên nhân viên"}, 0);

        for (int i = 0; i < model.getRowCount(); i++) {
            String idquanly = model.getValueAt(i, 0).toString().toLowerCase();
            String tenquanly = model.getValueAt(i, 1).toString().toLowerCase();
            //            String idnhanvien = model.getValueAt(i, 2).toString().toLowerCase();
            String tennhanvien = model.getValueAt(i, 3).toString().toLowerCase();

            if (idquanly.contains(keyword) || tenquanly.contains(keyword) || tennhanvien.contains(keyword)) {
                newModel.addRow(new Object[]{model.getValueAt(i, 0), model.getValueAt(i, 1), model.getValueAt(i, 2), model.getValueAt(i, 3)});
            }
        }

        Table_Supervisor.setModel(newModel);

    }//GEN-LAST:event_btnTimActionPerformed

    private void btnLammoiTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLammoiTKActionPerformed
        // TODO add your handling code here:
        jTextField4.setText(""); // Xoá ô tìm kiếm
        loadTable();
    }//GEN-LAST:event_btnLammoiTKActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
         try {
        int managerId = Integer.parseInt(txtQuanly.getText().trim());
        int staffId = Integer.parseInt(txtNhanvien.getText().trim());

        if (managerId == staffId) {
            JOptionPane.showMessageDialog(this, "Không thể tự giám sát chính mình.");
            return;
        }

        Supervisor s = new Supervisor(managerId, staffId);
        SupervisorDAO dao = new SupervisorDAO();

        boolean success = dao.insert(s);
        if (success) {
            JOptionPane.showMessageDialog(this, "Thêm giám sát viên thành công.");
            loadTable();  // cập nhật lại bảng sau khi thêm
        }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng ID (số nguyên).", "Lỗi nhập", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Lỗi hệ thống: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnLammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLammoiActionPerformed
        // TODO add your handling code here:
        ResetThongTin();
    }//GEN-LAST:event_btnLammoiActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        try {
        int managerId = Integer.parseInt(txtQuanly.getText().trim());
        int staffId = Integer.parseInt(txtNhanvien.getText().trim());

        int confirm = JOptionPane.showConfirmDialog(this,
            "Bạn có chắc muốn xóa mối quan hệ giám sát này?",
            "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            SupervisorDAO dao = new SupervisorDAO();
            if (dao.delete(managerId, staffId)) {
                JOptionPane.showMessageDialog(this, "Xóa thành công.");
                loadTable();
                
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy hoặc không thể xóa.");
            }
        }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "ID phải là số nguyên.", "Lỗi định dạng", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnXoaActionPerformed

    public void loadTable() {
        
     Table_Supervisor.setModel(tableModel);
    // Câu lệnh SQL gọi stored procedure hoặc truy vấn với JOIN để lấy thông tin nhân viên và quản lý
    String sql = "EXEC Supervisor_render";  // Hoặc câu lệnh SQL thay thế tùy theo yêu cầu

    try (
        // Mở kết nối cơ sở dữ liệu
        Connection conn = OpenConnection.getConnection();
        // Sử dụng Statement để thực thi câu truy vấn
        Statement stmt = conn.createStatement();
        // Thực thi câu lệnh SQL
        ResultSet result = stmt.executeQuery(sql);
    ) {
        // Xóa các dòng cũ trong bảng
        tableModel.setRowCount(0);  // Xóa hết tất cả các dòng trong bảng

        // Duyệt qua từng dòng kết quả từ ResultSet
        while (result.next()) {
            // Lấy thông tin từ ResultSet
            int manager_id = result.getInt("manager_id");
            String manager_name = result.getString("manager_name");
            int supervised_staff_id = result.getInt("supervised_staff_id");
            String supervised_staff_name = result.getString("supervised_staff_name");

            // Thêm dữ liệu vào bảng (tableModel)
            tableModel.addRow(new Object[]{
                manager_id,                // ID của quản lý
                manager_name,              // Tên quản lý
                supervised_staff_id,       // ID của nhân viên dưới quyền
                supervised_staff_name      // Tên nhân viên dưới quyền
            });
        }

        // Cập nhật lại bảng sau khi thêm dữ liệu mới
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                tableModel.fireTableDataChanged();  // Làm mới bảng sau khi thêm dữ liệu mới
            }
        });

    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
    private void ResetThongTin(){
        txtQuanly.setText("");
        txtTenquanly.setText("");
        txtNhanvien.setText("");
        txtTennhanvien.setText("");
    }

    



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel_DS;
    private javax.swing.JTable Table_Supervisor;
    private javax.swing.JButton btnLammoi;
    private javax.swing.JButton btnLammoiTK;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField txtNhanvien;
    private javax.swing.JTextField txtQuanly;
    private javax.swing.JTextField txtTennhanvien;
    private javax.swing.JTextField txtTenquanly;
    // End of variables declaration//GEN-END:variables
}

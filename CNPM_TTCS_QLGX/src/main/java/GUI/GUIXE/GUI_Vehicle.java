/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.GUIXE;

import Annotation.LogConfirm;
import Annotation.LogMessage;
import Annotation.LogSelection;
import DAO.VehicleDAO;
import GUI.ViewMain;
import Global.DataGlobal;
import Model.Vehicle;
import Model.VehicleType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
    Map<String, ArrayList<?>> data;
    ArrayList<Vehicle> vehicles;
    ArrayList<String> vehicle_type_names;
    private LogSelection logSelection;
    private int choooseIndexVehicleType = 0;
    private LogMessage logMessage;
    private DataGlobal dataGlobal;
    private LogConfirm logConfirm;
    private boolean cursorBreak = false;
    
    /**
     * Creates new form GUI_Customer
     */
    public GUI_Vehicle(DataGlobal dataGlobal, ViewMain viewmain, LogSelection logSelection, LogMessage logMessage, LogConfirm logConfirm) {
        this.viewmain = viewmain;
        this.logSelection = logSelection;
        this.logMessage = logMessage;
        this.dataGlobal = dataGlobal;
        this.logConfirm = logConfirm;
        
        this.dataGlobal.updateArrVehicleType();
        
        initComponents(); 
        initTable();
        loadData();
        fillTable();
        fillVehicleType();
        resetFields();
        resetActive();
        addDocumentListeners();
    }
    
    protected void loadData() {
        try {
            this.data = VehicleDAO.getInstance().getAllData();
            this.vehicles = (ArrayList<Vehicle>) data.get("vehicles");
            this.vehicle_type_names = (ArrayList<String>) data.get("vehicle_type_names");
        }
        catch (Exception e) { 
            e.printStackTrace();
        }
    }
    
    public void initTable() { 
        String[] header = new String[] {"Mã Phương Tiện", "Mã Nhận Dạng", "Loại Phương Tiện", "Tên Phương Tiện", "Màu Phương Tiện"};
        tblModel.setColumnIdentifiers(header);
        tblModel.setRowCount(0);
        tbl_vehicle.setModel(tblModel);
    }
    
    
    public void fillTable() {
        int count = -1;
        String crVehicle_type_name = "";
        for (Vehicle vel : this.vehicles) { 
            count += 1;
            crVehicle_type_name = this.vehicle_type_names.get(count);
            tblModel.addRow(new String[] { String.valueOf(vel.getVehicle_id()), vel.getIdentification_code(), crVehicle_type_name, vel.getVehicle_name(), vel.getVehicle_color()
            });
        }
        tblModel.fireTableDataChanged();
    }
    
    private void fillVehicleType() { 
        cob_loai_phuong_tien.removeAllItems();
        cob_loai_phuong_tien.addItem("");
        for (VehicleType vehicle_type : this.dataGlobal.getArrVehicleType()) { 
            if (vehicle_type.isIsPermission()) { 
                cob_loai_phuong_tien.addItem(vehicle_type.getVehicle_type_name());
            }
            
        }
    }
        
    private void resetActive() { 
        btn_insert.setEnabled(false);
        btn_update.setEnabled(false);
        btn_xoa.setEnabled(false);
        btn_chon_vehicle_type.setEnabled(true);
        
        txt_vehicle_id.setEnabled(false);
        txt_identification_code.setEnabled(true);
        txt_vehicle_name.setEnabled(true);
        txt_vehicle_type.setEnabled(false);
    }
    
    private void showUpdate() { 
        btn_insert.setEnabled(false);
        btn_update.setEnabled(true);
        btn_xoa.setEnabled(true);
        btn_chon_vehicle_type.setEnabled(false);
        
        txt_vehicle_id.setEnabled(false);
        txt_identification_code.setEnabled(true);
        txt_vehicle_name.setEnabled(true);
        txt_vehicle_type.setEnabled(false);
    }
    
    private void resetFields() { 
        txt_vehicle_id.setText("");
        txt_identification_code.setText("");
        txt_vehicle_type.setText("");
        txt_vehicle_name.setText("");
        txt_vehicle_color.setText("");
        this.choooseIndexVehicleType = 0;
        tbl_vehicle.clearSelection();
    }
    
    private void checkInsertButton () { 
         // Kiểm tra nếu tất cả các trường không rỗng
        boolean isFilled =  txt_vehicle_id.getText().trim().isEmpty() &&
                            !txt_identification_code.getText().trim().isEmpty() &&
                            !txt_vehicle_type.getText().trim().isEmpty();

//        System.out.println(isFilled);
        btn_insert.setEnabled(isFilled);
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
        txt_vehicle_id.getDocument().addDocumentListener(docListener);
        txt_identification_code.getDocument().addDocumentListener(docListener);
        txt_vehicle_type.getDocument().addDocumentListener(docListener);
    }
    
    public void reloadData() { 
        initTable();
        loadData();
        resetFields();
        fillTable();
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
        sp_vehicle = new javax.swing.JScrollPane();
        tbl_vehicle = new javax.swing.JTable();
        txt_tin_nhan = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        txt_tim_kiem = new javax.swing.JTextField();
        btn_tim_kiem = new javax.swing.JButton();
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
        btn_tim_kiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tim_kiemActionPerformed(evt);
            }
        });

        cob_loai_phuong_tien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cob_loai_phuong_tien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cob_loai_phuong_tien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cob_loai_phuong_tienActionPerformed(evt);
            }
        });

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
                .addComponent(btn_tim_kiem, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(cob_loai_phuong_tien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(7, Short.MAX_VALUE))
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
        btn_update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_updateMouseClicked(evt);
            }
        });

        btn_xoa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_xoa.setText("Xóa");
        btn_xoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_xoaMouseClicked(evt);
            }
        });

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

        btn_chon_vehicle_type.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_chon_vehicle_type.setText("Chọn");
        btn_chon_vehicle_type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chon_vehicle_typeActionPerformed(evt);
            }
        });

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
        int selectedRow = tbl_vehicle.getSelectedRow();
        // Kiểm tra xem có hàng nào được chọn không
        if (selectedRow != -1) {
            int vehicleId = Integer.parseInt(tbl_vehicle.getValueAt(selectedRow, 0).toString());
            String vehicleIden = tbl_vehicle.getValueAt(selectedRow, 1).toString();
            String vehicleType = tbl_vehicle.getValueAt(selectedRow, 2).toString();
            String vehicleName = tbl_vehicle.getValueAt(selectedRow, 3).toString();
            String vehicleColor = tbl_vehicle.getValueAt(selectedRow, 4).toString();
            
            txt_vehicle_id.setText(String.valueOf(vehicleId));
            txt_identification_code.setText(vehicleIden);
            txt_vehicle_type.setText(vehicleType);
            txt_vehicle_name.setText(vehicleName);
            txt_vehicle_color.setText(vehicleColor);
        }
        this.showUpdate();
        
    }//GEN-LAST:event_tbl_vehicleMouseClicked

    private void btn_insertMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_insertMouseClicked
        // TODO add your handling code here:
        String vehicleIden = txt_identification_code.getText().toString().trim();
        int vehicleTypeId = this.choooseIndexVehicleType;
        String vehicleName = txt_vehicle_name.getText().toString().trim();
        String vehicleColor = txt_vehicle_color.getText().toString().trim();
        
        Vehicle vel = new Vehicle(vehicleIden, vehicleTypeId, vehicleName, vehicleColor); 
        String check = "";
        check = VehicleDAO.getInstance().insert(vel);
        if (check.equals("Thêm Thành Công")) {
            initTable();
            resetActive();
            resetFields();
            loadData();
            fillTable();
        }
        else { 
            this.SetLog(GetError(check));
            return;
        }
        this.SetLog(check);
        return;
    }//GEN-LAST:event_btn_insertMouseClicked

    private void btn_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_insertActionPerformed

    private void btn_resetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_resetMouseClicked

    }//GEN-LAST:event_btn_resetMouseClicked

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        // TODO add your handling code here:
        this.resetFields();
        this.resetActive();
    }//GEN-LAST:event_btn_resetActionPerformed

    private void txt_tin_nhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tin_nhanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tin_nhanActionPerformed

    private void txt_tim_kiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tim_kiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tim_kiemActionPerformed

    private void cob_loai_phuong_tienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cob_loai_phuong_tienActionPerformed
        // TODO add your handling code here:
        initTable();
        this.resetActive();
        resetFields();
        
        String ve_t = cob_loai_phuong_tien.getSelectedItem() == null ? "" : cob_loai_phuong_tien.getSelectedItem().toString().trim();
        int ve_t_id = 0;
        for (VehicleType vehicle_type: this.dataGlobal.getArrVehicleType()) {
            if (ve_t.equals(vehicle_type.getVehicle_type_name())) {
                ve_t_id = vehicle_type.getVehicle_type_id();
            }
        }
        String crVehicle_type_name = "";
        
//        System.out.println(ve_t_id);
        int count = -1;
        for (Vehicle vel : this.vehicles) { 
            count += 1;
            if (vel.getVehicle_type_id() == ve_t_id || ve_t_id == 0) {
                crVehicle_type_name = this.vehicle_type_names.get(count);
                tblModel.addRow(new String[] {  String.valueOf(vel.getVehicle_id()), 
                                                vel.getIdentification_code(), crVehicle_type_name, vel.getVehicle_name(), 
                                                vel.getVehicle_color()
                });
            }
        }
        tblModel.fireTableDataChanged();
    }//GEN-LAST:event_cob_loai_phuong_tienActionPerformed

    private void btn_tim_kiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tim_kiemActionPerformed
        // TODO add your handling code here:
        initTable();
        this.resetActive();
        resetFields();
        String crVehicle_type_name = "";
        int count = -1;
        for (Vehicle vel : this.vehicles) { 
            count += 1;
            if (Library.Library.StringOnString(this.txt_tim_kiem.getText(), vel.getIdentification_code())) {
                crVehicle_type_name = this.vehicle_type_names.get(count);
                tblModel.addRow(new String[] {  String.valueOf(vel.getVehicle_id()), 
                                                vel.getIdentification_code(), crVehicle_type_name, vel.getVehicle_name(), 
                                                vel.getVehicle_color()
                });
            }
        }
        tblModel.fireTableDataChanged();
    }//GEN-LAST:event_btn_tim_kiemActionPerformed

    private void btn_chon_vehicle_typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chon_vehicle_typeActionPerformed
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
                for (VehicleType vt : dataGlobal.getArrVehicleType()) {
                    if (vt.isIsPermission())
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
    }//GEN-LAST:event_btn_chon_vehicle_typeActionPerformed

    private void processUpdate() { 
        int vehicleId = Integer.parseInt(txt_vehicle_id.getText().toString().trim());
        String vehicleIden = txt_identification_code.getText().toString().trim();
        
        for (VehicleType vt : this.dataGlobal.getArrVehicleType()) {
            if (vt.getVehicle_type_name().equals(txt_vehicle_type.getText().toString().trim())) {
                this.choooseIndexVehicleType = vt.getVehicle_type_id(); 
                break;
            }
        }
        int vehicleTypeId = this.choooseIndexVehicleType;
                
        String vehicleName = txt_vehicle_name.getText().toString().trim();
        String vehicleColor = txt_vehicle_color.getText().toString().trim();
        
        Vehicle vel = new Vehicle(vehicleId, vehicleIden, vehicleTypeId, vehicleName, vehicleColor);
        String check = VehicleDAO.getInstance().update(vel);
        if (check.equals("Cập Nhật Thành Công")) {
            initTable();
            resetActive();
            resetFields();
            loadData();
            fillTable();
        }
        else { 
            this.SetLog(GetError(check));
            return;
        }
        this.SetLog(check);
        return;
    }
    
    private void btn_updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_updateMouseClicked
        // TODO add your handling code here:
        this.cursorBreak = false;
        viewmain.setEnabled(false);
        this.logConfirm = new LogConfirm("Bạn có chắc là muốn xóa lượt gửi xe này ?") {
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
                processUpdate();
            }
        };
        worker.execute();
        worker = null;
    }//GEN-LAST:event_btn_updateMouseClicked

    private void processDelete() { 
        // TODO add your handling code here:
        int vehicleId = Integer.parseInt(txt_vehicle_id.getText().toString().trim());
        String check = VehicleDAO.getInstance().delete(vehicleId);
        if (check.equals("Xóa Thành Công")) {
            initTable();
            resetActive();
            resetFields();
            loadData();
            fillTable();
        }
        else { 
            this.SetLog(GetError(check));
            return;
        }
        this.SetLog(check);
        return;
    }
    
    private void btn_xoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_xoaMouseClicked
        this.cursorBreak = false;
        viewmain.setEnabled(false);
        this.logConfirm = new LogConfirm("Bạn có chắc là muốn xóa lượt gửi xe này ?") {
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
    }//GEN-LAST:event_btn_xoaMouseClicked


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

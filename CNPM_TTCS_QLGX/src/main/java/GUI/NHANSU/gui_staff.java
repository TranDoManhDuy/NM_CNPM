/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.NHANSU;

import Annotation.LogConfirm;
import Annotation.LogMessage;
import Annotation.LogSelection;
import DAO.AccountDAO;
import DAO.PositionDAO;
import DAO.RoleDAO;
import DAO.StaffDAO;
import DatabaseHelper.OpenConnection;
import GUI.ViewMain;
import Model.Staff;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author eramisme
 */
public class gui_staff extends javax.swing.JPanel {
    private DefaultTableModel tableModel;
    private ViewMain viewmain;
    private LogConfirm logConfirm;
    private LogMessage logMessage;
    private LogSelection logSelection;
    private boolean isEditing = false;
    private int editingStaffId = -1;
    

    public gui_staff(ViewMain viewmain) {
        this.viewmain = viewmain;
        this.logConfirm = logConfirm;
        this.logMessage = logMessage;
        this.logSelection = logSelection;
        initComponents();
        btnLuu.setEnabled(false);
        btnHuy.setEnabled(false);
        txt_ID.setEditable(false);
        combobox_GioiTinh.removeAllItems();
        combobox_TrangThai.removeAllItems();
        combobox_Vaitro.removeAllItems();
        combobox_Vitri.removeAllItems();
        

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
        String[] header = new String[] {"ID Nhân viên", "Số tài khoản", "Họ tên", "CCCD", "Ngày sinh", "Giới tính", "SĐT", "Địa chỉ", "Email", "Trạng thái", "Vị trí", "Vai trò"};
        tableModel.setColumnIdentifiers(header);
        Table_Staff.setModel(tableModel);
    }
    
    public void fillTable() {
    String sql = "EXEC Staff_render";
    try (
        Connection conn = OpenConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(sql);
    ) {
        tableModel.setRowCount(0); 
        while (result.next()) {
            int staff_id = result.getInt("staff_id");
            int account_number = result.getInt("account_number");
            String full_name = result.getString("full_name");
            String ssn = result.getString("ssn");
            LocalDate date_of_birth = result.getDate("date_of_birth").toLocalDate();
            String gender = result.getString("gender").equalsIgnoreCase("M") ? "Nam" : "Nữ";
            String phone_number = result.getString("phone_number");
            String address = result.getString("address");
            String email = result.getString("email");
            boolean is_active = result.getBoolean("is_active");
            String position_name = result.getString("position_name");
//                    .equalsIgnoreCase("Manager") ? "Quản lý" : "Nhân viên";
            String role_name = result.getString("role_name");
//            switch (role_name) {
//                case "Manager Staff In":
//                    role_name = "Quản lý nhân viên vào";
//                    break;
//                case "Manager Staff Out":
//                    role_name = "Quản lý nhân viên ra";
//                    break;
//                case "Staff In":
//                    role_name = "Nhân viên vào";
//                    break;
//                case "Staff Out":
//                    role_name = "Nhân viên ra";
//                    break;
//            }


            tableModel.addRow(new Object[]{
                staff_id,
                account_number,
                full_name,
                ssn,
                date_of_birth,
                gender,
                phone_number,
                address,
                email,
                is_active ? "Còn làm việc" : "Nghỉ việc",
                position_name,
                role_name,
                
            });
        }
        tableModel.fireTableDataChanged();
    } catch (Exception e) {
        e.printStackTrace();
    }
    Table_Staff.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        int selectedRow = Table_Staff.getSelectedRow();
        if (selectedRow != -1) {
            txt_ID.setText(Table_Staff.getValueAt(selectedRow, 0).toString());         
            txt_TaiKhoan.setText(Table_Staff.getValueAt(selectedRow, 1).toString());   
            txt_HoTen.setText(Table_Staff.getValueAt(selectedRow, 2).toString());      
            txt_CCCD.setText(Table_Staff.getValueAt(selectedRow, 3).toString());       
            txt_NgaySinh.setText(Table_Staff.getValueAt(selectedRow, 4).toString());   
            combobox_GioiTinh.setSelectedItem(Table_Staff.getValueAt(selectedRow, 5).toString()); 
            txt_SDT.setText(Table_Staff.getValueAt(selectedRow, 6).toString());       
            txt_DiaChi.setText(Table_Staff.getValueAt(selectedRow, 7).toString());     
            txt_Email.setText(Table_Staff.getValueAt(selectedRow, 8).toString());      
            combobox_TrangThai.setSelectedItem(Table_Staff.getValueAt(selectedRow, 9).toString());
            combobox_Vitri.setSelectedItem(Table_Staff.getValueAt(selectedRow, 10).toString());
            combobox_Vaitro.setSelectedItem(Table_Staff.getValueAt(selectedRow, 11).toString());
//            txt_ViTri.setText(Table_Staff.getValueAt(selectedRow, 10).toString());    
//            txt_VaiTro.setText(Table_Staff.getValueAt(selectedRow, 11).toString());    
        }       
        txt_ID.setEnabled(false);
//        txt_TaiKhoan.setEditable(false);
//        txt_HoTen.setEditable(false);
//        txt_CCCD.setEditable(false);
//        txt_NgaySinh.setEditable(false);
//        combobox_GioiTinh.setEditable(false);
//        txt_SDT.setEditable(false);
//        txt_DiaChi.setEditable(false);
//        txt_Email.setEditable(false);
//        combobox_TrangThai.setEditable(false);
//        txt_ViTri.setEditable(false);
//        txt_VaiTro.setEditable(false);
    }
});  
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel14 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        Panel_TKCN = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btn_Lammoitimkiem = new javax.swing.JButton();
        Combobox_ViTri = new javax.swing.JComboBox<>();
        txt_Timkiem = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btn_Timkiem = new javax.swing.JButton();
        Panel_DS = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Table_Staff = new javax.swing.JTable();
        Panel_TT = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_ID = new javax.swing.JTextField();
        txt_HoTen = new javax.swing.JTextField();
        txt_DiaChi = new javax.swing.JTextField();
        txt_NgaySinh = new javax.swing.JTextField();
        combobox_GioiTinh = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_Email = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txt_CCCD = new javax.swing.JTextField();
        txt_SDT = new javax.swing.JTextField();
        btnLuu = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        btn_LammoiTT = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txt_TaiKhoan = new javax.swing.JTextField();
        combobox_TrangThai = new javax.swing.JComboBox<>();
        combobox_Vaitro = new javax.swing.JComboBox<>();
        combobox_Vitri = new javax.swing.JComboBox<>();

        jLabel14.setText("Vai trò");

        setBackground(new java.awt.Color(204, 255, 255));
        setPreferredSize(new java.awt.Dimension(1125, 485));

        Panel_TKCN.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemMouseClicked(evt);
            }
        });
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaMouseClicked(evt);
            }
        });
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaMouseClicked(evt);
            }
        });
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btn_Lammoitimkiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_Lammoitimkiem.setText("Làm mới");
        btn_Lammoitimkiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_LammoitimkiemMouseClicked(evt);
            }
        });
        btn_Lammoitimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LammoitimkiemActionPerformed(evt);
            }
        });

        Combobox_ViTri.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Manager", "Staff" }));
        Combobox_ViTri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Combobox_ViTriActionPerformed(evt);
            }
        });

        txt_Timkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TimkiemActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("TÌM KIẾM");

        btn_Timkiem.setText("Tìm");
        btn_Timkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimkiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel_TKCNLayout = new javax.swing.GroupLayout(Panel_TKCN);
        Panel_TKCN.setLayout(Panel_TKCNLayout);
        Panel_TKCNLayout.setHorizontalGroup(
            Panel_TKCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_TKCNLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(txt_Timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Timkiem, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Lammoitimkiem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Combobox_ViTri, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );
        Panel_TKCNLayout.setVerticalGroup(
            Panel_TKCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_TKCNLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_TKCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Timkiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(Panel_TKCNLayout.createSequentialGroup()
                        .addGroup(Panel_TKCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_Timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Lammoitimkiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Combobox_ViTri)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_TKCNLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(Panel_TKCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        Panel_DS.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Table_Staff.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Nhân viên", "Số tài khoản", "Họ tên", "CCCD", "Ngày sinh", "Giới tính", "SĐT", "Địa chỉ", "Email", "Trạng thái", "Vị trí", "Chức vụ"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(Table_Staff);

        javax.swing.GroupLayout Panel_DSLayout = new javax.swing.GroupLayout(Panel_DS);
        Panel_DS.setLayout(Panel_DSLayout);
        Panel_DSLayout.setHorizontalGroup(
            Panel_DSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_DSLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        Panel_DSLayout.setVerticalGroup(
            Panel_DSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_DSLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                .addContainerGap())
        );

        Panel_TT.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("THÔNG TIN NHÂN VIÊN");

        jLabel2.setText("ID Nhân viên");

        jLabel3.setText("Họ tên");

        jLabel4.setText("CCCD");

        jLabel5.setText("Giới tính");

        jLabel6.setText("Vai trò");

        jLabel7.setText("Địa chỉ");

        jLabel8.setText("Ngày sinh");

        combobox_GioiTinh.setEditable(true);
        combobox_GioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ ", "Khác", " " }));

        jLabel12.setText("Số điện thoại");

        jLabel13.setText("Email");

        jLabel15.setText("Vị trí");

        btnLuu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLuuMouseClicked(evt);
            }
        });
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnHuy.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnHuy.setText("Hủy");
        btnHuy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHuyMouseClicked(evt);
            }
        });
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        btn_LammoiTT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_LammoiTT.setText("Làm mới");
        btn_LammoiTT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_LammoiTTMouseClicked(evt);
            }
        });
        btn_LammoiTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LammoiTTActionPerformed(evt);
            }
        });

        jLabel16.setText("Số tài khoản");

        jLabel17.setText("Trạng thái");

        combobox_TrangThai.setEditable(true);
        combobox_TrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Còn làm việc", "Nghỉ việc" }));

        combobox_Vaitro.setEditable(true);
        combobox_Vaitro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Manager Staff In", "Manager Staff Out", "Staff In", "Staff Out" }));

        combobox_Vitri.setEditable(true);
        combobox_Vitri.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Manager", "Staff" }));

        javax.swing.GroupLayout Panel_TTLayout = new javax.swing.GroupLayout(Panel_TT);
        Panel_TT.setLayout(Panel_TTLayout);
        Panel_TTLayout.setHorizontalGroup(
            Panel_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_TTLayout.createSequentialGroup()
                .addGroup(Panel_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Panel_TTLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(Panel_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(Panel_TTLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(22, 22, 22)
                                .addComponent(txt_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Panel_TTLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_NgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Panel_TTLayout.createSequentialGroup()
                                .addGroup(Panel_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(Panel_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_DiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                                    .addComponent(combobox_Vitri, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(62, 62, 62)
                        .addGroup(Panel_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(jLabel13)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addGroup(Panel_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_Email)
                            .addComponent(combobox_GioiTinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_HoTen, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                            .addComponent(txt_TaiKhoan))
                        .addGap(51, 51, 51)
                        .addGroup(Panel_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addGroup(Panel_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_CCCD)
                            .addComponent(txt_SDT, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                            .addComponent(combobox_TrangThai, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(combobox_Vaitro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(Panel_TTLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_LammoiTT)))
                .addGap(30, 30, 30))
        );
        Panel_TTLayout.setVerticalGroup(
            Panel_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_TTLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Panel_TTLayout.createSequentialGroup()
                        .addGroup(Panel_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnLuu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnHuy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(Panel_TTLayout.createSequentialGroup()
                                .addGap(0, 6, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn_LammoiTT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(9, 9, 9)
                        .addGroup(Panel_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(txt_CCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(Panel_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_NgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(txt_SDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)))
                    .addGroup(Panel_TTLayout.createSequentialGroup()
                        .addGroup(Panel_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(Panel_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(combobox_GioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(Panel_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_DiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(combobox_Vaitro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Panel_TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(txt_TaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combobox_TrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combobox_Vitri, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_TT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Panel_TKCN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Panel_DS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Panel_TT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Panel_TKCN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Panel_DS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        btnLuu.setEnabled(true);
        btnHuy.setEnabled(true);
        
        txt_HoTen.setText("");
        txt_CCCD.setText("");
        txt_NgaySinh.setText("");
        txt_SDT.setText("");
        txt_DiaChi.setText("");
        txt_Email.setText("");
//        txt_VaiTro.setText("");
//        txt_ViTri.setText("Staff");
        txt_TaiKhoan.setText("");
        
        combobox_GioiTinh.removeAllItems();
        combobox_GioiTinh.addItem("Nam");
        combobox_GioiTinh.addItem("Nữ");
        combobox_GioiTinh.setSelectedItem("Nam");

        combobox_TrangThai.removeAllItems();
        combobox_TrangThai.addItem("Còn làm việc");
        combobox_TrangThai.setSelectedItem("Còn làm việc");
        
        combobox_Vaitro.removeAllItems();
        combobox_Vaitro.addItem("Manager Staff In");
        combobox_Vaitro.addItem("Manager Staff Out");
        combobox_Vaitro.addItem("Staff In");
        combobox_Vaitro.addItem("Staff Out");
        combobox_Vaitro.setSelectedItem("Manager Staff In");
        
        combobox_GioiTinh.removeAllItems();
        combobox_GioiTinh.addItem("Manager");
        combobox_GioiTinh.addItem("Staff");
        combobox_GioiTinh.setSelectedItem("Manager");
        
        txt_HoTen.requestFocus(); 
        isEditing = false;
        editingStaffId = -1;
        
        btnXoa.setEnabled(false);
        btnSua.setEnabled(false);
        btn_LammoiTT.setEnabled(false);
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int selectedRow = Table_Staff.getSelectedRow();
        if (selectedRow == -1) {
            log_message("Vui lòng chọn nhân viên để xóa!");
            return;
        }

        String trangThai = Table_Staff.getValueAt(selectedRow, 9).toString().trim();
        String viTri = Table_Staff.getValueAt(selectedRow, 10).toString().trim();
        if (trangThai.equalsIgnoreCase("Còn làm việc")) {
            log_message("Không thể xóa");
            return;
        }
        if (viTri.equalsIgnoreCase("Manager")) {
            log_message("Không thể xóa");
            return;
        }

        int staff_id = Integer.parseInt(Table_Staff.getValueAt(selectedRow, 0).toString());

        logConfirm = new LogConfirm("Xác nhận xóa") {
            @Override
            public void action() {
                setVisible(false);
                viewmain.setEnabled(true);
                if (StaffDAO.getInstance().delete(staff_id)) {
                    log_message("Xóa thành công!");
                    loadTable();
                    resetThongTin();
                } else {
                    log_message("Xóa thất bại");
                }
            }

            @Override
            public void reject() {
                setVisible(false);
                viewmain.setEnabled(true);
            }
        };
        logConfirm.setLocationRelativeTo(null);
        logConfirm.setVisible(true);
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSuaMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        txt_CCCD.setEditable(false);
        txt_TaiKhoan.setEditable(false);
        btnLuu.setEnabled(true);
        btnHuy.setEnabled(true);       
        int selectedRow = Table_Staff.getSelectedRow();
        if (selectedRow == -1) {
             log_message("Vui lòng chọn nhân viên để sửa!");
             return;
        }

//        String viTriCheck = Table_Staff.getValueAt(selectedRow, 10).toString().trim(); 
//        if (viTriCheck.equalsIgnoreCase("Quản lý")) {
//             log_message("Không được phép sửa");
//             return;
//        }

        combobox_GioiTinh.removeAllItems();
        combobox_GioiTinh.addItem("Nam");
        combobox_GioiTinh.addItem("Nữ");

        combobox_TrangThai.removeAllItems();
        combobox_TrangThai.addItem("Còn làm việc");
        combobox_TrangThai.addItem("Đã nghỉ");
        
        combobox_Vaitro.removeAllItems();
        combobox_Vaitro.addItem("Manager Staff In");
        combobox_Vaitro.addItem("Manager Staff Out");
        combobox_Vaitro.addItem("Staff In");
        combobox_Vaitro.addItem("Staff Out");
        
        combobox_Vitri.removeAllItems();
        combobox_Vitri.addItem("Manager");
        combobox_Vitri.addItem("Staff");

        txt_ID.setText(Table_Staff.getValueAt(selectedRow, 0).toString());             
        txt_TaiKhoan.setText(Table_Staff.getValueAt(selectedRow, 1).toString());       
        txt_HoTen.setText(Table_Staff.getValueAt(selectedRow, 2).toString());          
        txt_CCCD.setText(Table_Staff.getValueAt(selectedRow, 3).toString());           
        txt_NgaySinh.setText(Table_Staff.getValueAt(selectedRow, 4).toString());       
        String gender = Table_Staff.getValueAt(selectedRow, 5).toString().trim();      
        combobox_GioiTinh.setSelectedItem(gender);
        txt_SDT.setText(Table_Staff.getValueAt(selectedRow, 6).toString());            
        txt_DiaChi.setText(Table_Staff.getValueAt(selectedRow, 7).toString());         
        txt_Email.setText(Table_Staff.getValueAt(selectedRow, 8).toString());          
        String status = Table_Staff.getValueAt(selectedRow, 9).toString().trim();      
        combobox_TrangThai.setSelectedItem(status);
        combobox_Vitri.setSelectedItem(Table_Staff.getValueAt(selectedRow, 10).toString().trim());
        combobox_Vaitro.setSelectedItem(Table_Staff.getValueAt(selectedRow, 11).toString().trim());
        
//        txt_ViTri.setText(Table_Staff.getValueAt(selectedRow, 10).toString());         
//        txt_VaiTro.setText(Table_Staff.getValueAt(selectedRow, 11).toString());        

        txt_HoTen.requestFocus();

        isEditing = true;
        editingStaffId = Integer.parseInt(txt_ID.getText());
        
        btnXoa.setEnabled(false);
        btnThem.setEnabled(false);
        btn_LammoiTT.setEnabled(false);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btn_LammoitimkiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LammoitimkiemMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_LammoitimkiemMouseClicked

    private void btn_LammoitimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LammoitimkiemActionPerformed
        // TODO add your handling code here:
        txt_Timkiem.setText("");
        List<Staff> allStaff = StaffDAO.getInstance().getList();
        loadAndFilterTable(allStaff);
    }//GEN-LAST:event_btn_LammoitimkiemActionPerformed

    private void btnLuuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLuuMouseClicked

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        String fullName = txt_HoTen.getText().trim();
        String ssn = txt_CCCD.getText().trim();
        String dobText = txt_NgaySinh.getText().trim();
        String genderText = combobox_GioiTinh.getSelectedItem().toString();
        String phone = txt_SDT.getText().trim();
        String address = txt_DiaChi.getText().trim();
        String email = txt_Email.getText().trim();
        String roleText = combobox_Vaitro.getSelectedItem().toString();
        String positionText = combobox_Vitri.getSelectedItem().toString();
        String statusText = combobox_TrangThai.getSelectedItem().toString();
        String accNumText = txt_TaiKhoan.getText().trim();

        if (fullName.isEmpty() || ssn.isEmpty() || dobText.isEmpty() || genderText.isEmpty() || phone.isEmpty() ||
            address.isEmpty() || email.isEmpty() || roleText.isEmpty() || positionText.isEmpty() || statusText.isEmpty() || accNumText.isEmpty()) {
            log_message("Vui lòng điền đầy đủ thông tin.");
            return;
        }

        if (!ssn.matches("^0\\d{11}$")) {
            log_message("CCCD không hợp lệ! Phải đủ 12 số và bắt đầu bằng 0.");
            return;
        }

        if (!phone.matches("^0\\d{9}$")) {
            log_message("Số điện thoại không hợp lệ! Phải đủ 10 số và bắt đầu bằng 0.");
            return;
        }

        if (!email.matches("^[\\w.+\\-]+@gmail\\.com$")) {
            log_message("Email phải là địa chỉ Gmail hợp lệ.");
            return;
        }

        LocalDate dob;
        try {
            dob = LocalDate.parse(dobText);
        } catch (Exception e) {
            log_message("Ngày sinh không đúng định dạng yyyy-MM-dd.");
            return;
        }

        int accountNumber;
        try {
            accountNumber = Integer.parseInt(accNumText);
        } catch (NumberFormatException e) {
            log_message("Số tài khoản phải là số");
            return;
        }

        int currentStaffId;
        if (isEditing) {
            currentStaffId = editingStaffId;
        } else {
            currentStaffId = 0;
        }
        
                // Kiểm tra trùng ssn, phone, email
        if (StaffDAO.getInstance().isSsnExists(ssn, currentStaffId)) {
            log_message("CCCD đã tồn tại!");
            return;
        }

        if (StaffDAO.getInstance().isPhoneExists(phone, currentStaffId)) {
            log_message("Số điện thoại đã tồn tại!");
            return;
        }

        if (StaffDAO.getInstance().isEmailExists(email, currentStaffId)) {
            log_message("Email đã tồn tại!");
            return;
        }

        // Chỉ kiểm tra account khi thêm
        if (!isEditing) {
            if (!AccountDAO.getInstance().isAccountNumberExists(accountNumber)) {
                log_message("Số tài khoản chưa tồn tại. Vui lòng tạo tài khoản trước.");
                return;
            }

            if (StaffDAO.getInstance().isAccountUsedByStaff(accountNumber)) {
                log_message("Số tài khoản đã được gán cho nhân viên khác.");
                return;
            }
        }

        String gender = switch (genderText) {
            case "Nam" -> "M";
            case "Nữ" -> "F";
            case "Khác" -> "O";
            default -> "";
        };

        boolean isActive = statusText.equalsIgnoreCase("Còn làm việc");
        int roleId = RoleDAO.getInstance().getRoleIdByName(roleText);
        int positionId = PositionDAO.getInstance().getPositionIdByName(positionText);
        
        if ((positionText.equalsIgnoreCase("Staff") && roleText.equalsIgnoreCase("Manager")) ||
            (positionText.equalsIgnoreCase("Manager") && roleText.equalsIgnoreCase("Staff"))) {
            log_message("Vai trò không hợp lệ với vị trí");
            return;
        }

//        if (roleId == 0 || positionId == 0) {
//            log_message("Vai trò hoặc vị trí không hợp lệ.");
//            return;
//        }

        Staff st = new Staff(
            currentStaffId,
            roleId, fullName, ssn, dob, gender, phone, address, email, isActive, positionId, accountNumber
        );

        boolean success = isEditing
            ? StaffDAO.getInstance().update(st)
            : StaffDAO.getInstance().insert(st);

        if (success) {
            log_message(isEditing ? "Cập nhật thành công!" : "Thêm thành công!");

            List<Staff> allStaff = StaffDAO.getInstance().getList();
            loadAndFilterTable(allStaff);   

            
            resetThongTin();    
           

            isEditing = false;
            editingStaffId = -1;
            btnThem.setEnabled(true);
            btnXoa.setEnabled(true);
            btnSua.setEnabled(true);
            btnLuu.setEnabled(false);
            btnHuy.setEnabled(false);
            btn_LammoiTT.setEnabled(true);
            txt_CCCD.setEditable(true);
            txt_TaiKhoan.setEditable(true);

        } else {
            log_message(isEditing ? "Cập nhật thất bại!" : "Thêm thất bại!");
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnHuyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHuyMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHuyMouseClicked

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        log_comfirm("Bạn có muốn hủy không?");
        btnLuu.setEnabled(false);
        btnHuy.setEnabled(false);
        btn_LammoiTT.setEnabled(true);
        txt_CCCD.setEditable(true);
        txt_TaiKhoan.setEditable(true);
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btn_LammoiTTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LammoiTTMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_LammoiTTMouseClicked

    private void btn_LammoiTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LammoiTTActionPerformed
        // TODO add your handling code here:
        resetThongTin();
    }//GEN-LAST:event_btn_LammoiTTActionPerformed

    private void txt_TimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TimkiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_TimkiemActionPerformed

    private void btn_TimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimkiemActionPerformed
        // TODO add your handling code here:
        List<Staff> allStaff = StaffDAO.getInstance().getList();
        loadAndFilterTable(allStaff);
        combobox_GioiTinh.removeAllItems();
        combobox_TrangThai.removeAllItems();
    }//GEN-LAST:event_btn_TimkiemActionPerformed

    private void Combobox_ViTriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Combobox_ViTriActionPerformed
        // TODO add your handling code here:
        List<Staff> allStaff = StaffDAO.getInstance().getList();
        loadAndFilterTable(allStaff);
    }//GEN-LAST:event_Combobox_ViTriActionPerformed
    
    private void loadAndFilterTable(List<Staff> list) {
        DefaultTableModel model = (DefaultTableModel) Table_Staff.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ

        // Đổ lại dữ liệu
        for (Staff s : list) {
            model.addRow(new Object[]{
                s.getStaffId(),
                s.getAccountNumber(),
                s.getFullName(),
                s.getSsn(),
                s.getDateOfBirth().toString(),
                switch (s.getGender()) {
                    case "M" -> "Nam";
                    case "F" -> "Nữ";
                    case "O" -> "Khác";
                    default -> "";
                },
                s.getPhoneNumber(),
                s.getAddress(),
                s.getEmail(),
                s.isActive() ? "Còn làm việc" : "Đã nghỉ",
                PositionDAO.getInstance().getPositionNameById(s.getPositionId()), 
                RoleDAO.getInstance().getRoleNameById(s.getRoleId())                         
            });
        }

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        Table_Staff.setRowSorter(sorter);

        // Lấy filter từ UI
        String keyword = txt_Timkiem.getText().trim();
        String selectedViTri = Combobox_ViTri.getSelectedItem().toString();

        List<RowFilter<Object, Object>> filters = new ArrayList<>();

        if (!keyword.isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + keyword, 0, 1, 2, 6));
        }

        if (!selectedViTri.equalsIgnoreCase("Tất cả")) {
            filters.add(RowFilter.regexFilter("(?i)^" + selectedViTri + "$", 10)); 
        }

        if (filters.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.andFilter(filters));
        }
    }

        
    private void resetThongTin() {
        txt_ID.setText("");
        txt_TaiKhoan.setText("");
        txt_HoTen.setText("");
        txt_CCCD.setText("");
        txt_NgaySinh.setText(""); 
        combobox_GioiTinh.setSelectedIndex(-1);
        txt_SDT.setText("");
        txt_DiaChi.setText("");
        txt_Email.setText("");
        combobox_TrangThai.setSelectedIndex(-1);
        combobox_Vaitro.setSelectedIndex(-1);
        combobox_Vitri.setSelectedIndex(-1);

        Table_Staff.clearSelection();
    }
        
    private void log_message(String message) {
        this.viewmain.setEnabled(false);
        this.logMessage = new LogMessage(message) {
            @Override
            public void action() {
                this.setVisible(false);
                viewmain.setEnabled(true);
                viewmain.requestFocus();
            }
        };
        logMessage.setLocationRelativeTo(null);
        this.logMessage.setVisible(true);
    }
        
    private void log_comfirm(String message) {
        this.viewmain.setEnabled(false);

        this.logConfirm = new LogConfirm(message) {
        @Override
        public void action() {
            this.setVisible(false);
            viewmain.setEnabled(true);
            viewmain.requestFocus();
            resetThongTin(); 
            btnXoa.setEnabled(true);
            btnThem.setEnabled(true);
            btnSua.setEnabled(true);
        }

        @Override
        public void reject() {
            this.setVisible(false);
            viewmain.setEnabled(true);
            viewmain.requestFocus();
        }
    };
    logConfirm.setLocationRelativeTo(null);
    this.logConfirm.setVisible(true);
    }
        
    private void loadTable() {
        DefaultTableModel model = (DefaultTableModel) Table_Staff.getModel();
        model.setRowCount(0); 

        ArrayList<Staff> list = StaffDAO.getInstance().getList();

        for (Staff st : list) {
            String gender = "";
            if ("M".equals(st.getGender())) {
                gender = "Nam";
            } else if ("F".equals(st.getGender())) {
                gender = "Nữ";

            String status = st.isActive() ? "Còn làm việc" : "Nghỉ việc";

            // Dùng DAO để lấy tên role và position
            String roleName = RoleDAO.getInstance().getRoleNameById(st.getRoleId());
            String positionName = PositionDAO.getInstance().getPositionNameById(st.getPositionId());

            model.addRow(new Object[]{
                st.getStaffId(),
                st.getAccountNumber(),
                st.getFullName(),
                st.getSsn(),
                st.getDateOfBirth(),
                gender,
                st.getPhoneNumber(),
                st.getAddress(),
                st.getEmail(),
                status,
                positionName,
                roleName
            });
        }
    }   
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Combobox_ViTri;
    private javax.swing.JPanel Panel_DS;
    private javax.swing.JPanel Panel_TKCN;
    private javax.swing.JPanel Panel_TT;
    private javax.swing.JTable Table_Staff;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btn_LammoiTT;
    private javax.swing.JButton btn_Lammoitimkiem;
    private javax.swing.JButton btn_Timkiem;
    private javax.swing.JComboBox<String> combobox_GioiTinh;
    private javax.swing.JComboBox<String> combobox_TrangThai;
    private javax.swing.JComboBox<String> combobox_Vaitro;
    private javax.swing.JComboBox<String> combobox_Vitri;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txt_CCCD;
    private javax.swing.JTextField txt_DiaChi;
    private javax.swing.JTextField txt_Email;
    private javax.swing.JTextField txt_HoTen;
    private javax.swing.JTextField txt_ID;
    private javax.swing.JTextField txt_NgaySinh;
    private javax.swing.JTextField txt_SDT;
    private javax.swing.JTextField txt_TaiKhoan;
    private javax.swing.JTextField txt_Timkiem;
    // End of variables declaration//GEN-END:variables
}

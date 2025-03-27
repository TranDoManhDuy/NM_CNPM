package GUI.DICHVU;
import Annotation.LogConfirm;
import Annotation.LogMessage;
import DAO.CustomerDAO;
import DAO.RegisatrationDAO;
import DAO.VehicleDAO;
import DatabaseHelper.OpenConnection;
import GUI.ViewMain;
import Global.DataGlobal;
import Model.Customer;
import Model.Regisatration;
import Model.Vehicle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

/**
 *
 * @author manhh
 */
public class gui_registration extends javax.swing.JPanel {
    private DefaultTableModel tableModel;
    private ViewMain viewmain;
    /**
     * Creates new form registration
     */
    public gui_registration(ViewMain viewmain) {
        this.viewmain = viewmain;
        initComponents();
        combo_trangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Còn thời hạn", "Hết thời hạn", ""}));
        combo_trangthai.setSelectedIndex(2);
        txt_ngaydangki.setText(String.valueOf(LocalDate.now()));
        
        // 99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        initTable();
        fillTable();
        // 99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999
        
        combo_ngaybatdau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
        , "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"
        }));
        combo_ngayketthuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
        , "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"
        }));
        combo_thangbatdau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
        , "11", "12"}));
        combo_thangketthuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
        , "11", "12"}));
        
        combo_nambatdau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", 
        "2027", "2028"}));
        combo_namketthuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", 
        "2027", "2028"}));
        
        combo_ngaybatdau.setSelectedIndex(0);
        combo_ngayketthuc.setSelectedIndex(0);
        combo_thangbatdau.setSelectedIndex(0);
        combo_thangketthuc.setSelectedIndex(0);
        combo_nambatdau.setSelectedIndex(0);
        combo_namketthuc.setSelectedIndex(0);
        
        table_dangki.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e) {
                btn_them.setEnabled(false);
                btn_capnhat.setEnabled(true);
                btn_xoa.setEnabled(true);
                
                combo_trangthai.setEnabled(true);
                Customer customer = new Customer();
                Vehicle vehicle = new Vehicle();
                int row = table_dangki.rowAtPoint(e.getPoint());
                ArrayList <Regisatration> arr = RegisatrationDAO.getInstance().getList();
                Regisatration dk = arr.get(row);
                customer = CustomerDAO.getInstance().findbyID(dk.getCustomer_id());
                vehicle = VehicleDAO.getInstance().findbyID(dk.getVehicle_id());
                txt_iddangki.setText(String.valueOf(dk.getRegistration_id()));
                txt_id_khachhang.setText(String.valueOf(customer.getCustomer_id()));
                txt_ten_Khachhang.setText(customer.getFull_name());
                txt_ngaydangki.setText(String.valueOf(dk.getRegistration_date()));
                txt_phuongtien.setText(vehicle.getIdentification_code());
                String trangthai = "";
                if (dk.getState() == 'A') {
                    combo_trangthai.setSelectedIndex(0);
                }
                else {
                    combo_trangthai.setSelectedIndex(1);
                }
                
                arr = null;
                dk = null;
                customer = null;
                vehicle = null;
            }
        });
    }
    public void initTable() {
        String[] header = new String[] {"ID đăng kí", "Khách hàng", "Ngày đăng kí", "Định danh phương tiện", "Trạng thái"};
        tableModel.setColumnIdentifiers(header);
        table_dangki.setModel(tableModel);
    }
    
    public void fillTable() {
    String sql = "EXEC Registration_render";
    try (
        Connection conn = OpenConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
    ) {
        while (rs.next()) {
            int registration_id = rs.getInt("registration_id");
            String full_name = rs.getString("full_name");
            LocalDate registration_date = rs.getDate("registration_date").toLocalDate();
            String identification_code = rs.getString("identification_code");
            String state = rs.getString("state");
            tableModel.addRow(new String[] {String.valueOf(registration_id), full_name, String.valueOf(registration_date), identification_code, state});
        }
        tableModel.fireTableDataChanged();
    } catch (Exception e) {
        e.printStackTrace();
    }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        table_dangki = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        txt_iddangki = new javax.swing.JTextField();
        label_iddangki = new javax.swing.JLabel();
        label_khachhang = new javax.swing.JLabel();
        label_ngaydangki = new javax.swing.JLabel();
        label_id_pt = new javax.swing.JLabel();
        label_trangthai = new javax.swing.JLabel();
        btn_them = new javax.swing.JButton();
        btn_capnhat = new javax.swing.JButton();
        btn_xoa = new javax.swing.JButton();
        txt_ten_Khachhang = new javax.swing.JTextField();
        txt_phuongtien = new javax.swing.JTextField();
        inforDetail = new javax.swing.JLabel();
        btn_chonphuongtien = new javax.swing.JButton();
        txt_ngaydangki = new javax.swing.JTextField();
        combo_trangthai = new javax.swing.JComboBox<>();
        btn_datlai = new javax.swing.JButton();
        label_id_khachhang = new javax.swing.JLabel();
        txt_id_khachhang = new javax.swing.JTextField();
        btn_chonkhachhang = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txt_timkiem = new javax.swing.JTextField();
        btn_timkiem = new javax.swing.JButton();
        btn_conhan = new javax.swing.JButton();
        btn_hethan = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btn_tatca = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        combo_ngaybatdau = new javax.swing.JComboBox<>();
        combo_thangbatdau = new javax.swing.JComboBox<>();
        combo_nambatdau = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        combo_ngayketthuc = new javax.swing.JComboBox<>();
        combo_thangketthuc = new javax.swing.JComboBox<>();
        combo_namketthuc = new javax.swing.JComboBox<>();
        btn_loc = new javax.swing.JButton();
        btn_bo_loc = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_tinnhan = new javax.swing.JTextField();

        setBackground(new java.awt.Color(204, 255, 255));

        table_dangki.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(table_dangki);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        txt_iddangki.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_iddangki.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        txt_iddangki.setEnabled(false);

        label_iddangki.setText("Id đăng kí");

        label_khachhang.setText("Tên Khách hàng");

        label_ngaydangki.setText("Ngày đăng kí");

        label_id_pt.setText("Phương tiện");

        label_trangthai.setText("Trạng thái");

        btn_them.setText("Thêm");
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        btn_capnhat.setText("Cập nhật");
        btn_capnhat.setEnabled(false);

        btn_xoa.setText("Xóa");
        btn_xoa.setEnabled(false);
        btn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaActionPerformed(evt);
            }
        });

        txt_ten_Khachhang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_ten_Khachhang.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        txt_ten_Khachhang.setEnabled(false);
        txt_ten_Khachhang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ten_KhachhangActionPerformed(evt);
            }
        });

        txt_phuongtien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_phuongtien.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        txt_phuongtien.setEnabled(false);
        txt_phuongtien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_phuongtienActionPerformed(evt);
            }
        });

        inforDetail.setText("Thông tin chi tiết");

        btn_chonphuongtien.setText("Chọn");
        btn_chonphuongtien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chonphuongtienActionPerformed(evt);
            }
        });

        txt_ngaydangki.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_ngaydangki.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        txt_ngaydangki.setEnabled(false);

        combo_trangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        combo_trangthai.setEnabled(false);
        combo_trangthai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_trangthaiActionPerformed(evt);
            }
        });

        btn_datlai.setText("Đặt lại");
        btn_datlai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_datlaiActionPerformed(evt);
            }
        });

        label_id_khachhang.setText("ID Khách hàng");

        txt_id_khachhang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_id_khachhang.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        txt_id_khachhang.setEnabled(false);

        btn_chonkhachhang.setText("Chọn");
        btn_chonkhachhang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chonkhachhangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(inforDetail)
                .addGap(128, 128, 128))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label_ngaydangki)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_ngaydangki, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(btn_them)
                                .addGap(28, 28, 28)
                                .addComponent(btn_capnhat))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(label_id_pt)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txt_phuongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btn_chonphuongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(label_trangthai)
                                    .addGap(36, 36, 36)
                                    .addComponent(combo_trangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(label_iddangki)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_iddangki))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(label_id_khachhang)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_id_khachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_chonkhachhang, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(label_khachhang)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_ten_Khachhang))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_xoa)
                                    .addComponent(btn_datlai))))
                        .addGap(32, 32, 32))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(inforDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label_iddangki)
                            .addComponent(txt_iddangki, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_id_khachhang)
                            .addComponent(txt_id_khachhang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_chonkhachhang))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_khachhang)
                            .addComponent(txt_ten_Khachhang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_ngaydangki)
                            .addComponent(txt_ngaydangki, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_id_pt)
                            .addComponent(txt_phuongtien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_chonphuongtien))
                        .addGap(18, 18, 18)
                        .addComponent(combo_trangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(label_trangthai))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_datlai)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_them)
                    .addComponent(btn_capnhat)
                    .addComponent(btn_xoa))
                .addGap(52, 52, 52))
        );

        txt_timkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_timkiemActionPerformed(evt);
            }
        });

        btn_timkiem.setText("Tìm kiếm tên");

        btn_conhan.setText("Còn hạn");
        btn_conhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_conhanActionPerformed(evt);
            }
        });

        btn_hethan.setText("Hết hạn");
        btn_hethan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hethanActionPerformed(evt);
            }
        });

        jLabel1.setText("Danh sách:");

        btn_tatca.setText("Tất cả");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_conhan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_hethan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_tatca))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btn_timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_timkiem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_conhan)
                    .addComponent(btn_hethan)
                    .addComponent(jLabel1)
                    .addComponent(btn_tatca))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel2.setText("Thời gian: Từ");

        combo_ngaybatdau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        combo_ngaybatdau.setMinimumSize(new java.awt.Dimension(100, 22));
        combo_ngaybatdau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_ngaybatdauActionPerformed(evt);
            }
        });

        combo_thangbatdau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        combo_nambatdau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Ngày");

        jLabel4.setText("Tháng");

        jLabel5.setText("Năm");

        jLabel6.setText("Đến");

        combo_ngayketthuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        combo_thangketthuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        combo_namketthuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btn_loc.setText("Lọc");

        btn_bo_loc.setText("Bỏ lọc");

        jLabel7.setText("Ngày");

        jLabel8.setText("Tháng");

        jLabel9.setText("Năm");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(combo_ngaybatdau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(combo_thangbatdau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(combo_nambatdau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6))
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(combo_ngayketthuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(combo_thangketthuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_bo_loc, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(combo_namketthuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addComponent(btn_loc)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_bo_loc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combo_ngaybatdau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(combo_thangbatdau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_nambatdau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(combo_ngayketthuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_thangketthuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_namketthuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_loc))
                .addGap(14, 14, 14))
        );

        txt_tinnhan.setText("Đang hiển thị danh sách tất cả các đăng kí");
        txt_tinnhan.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_tinnhan.setEnabled(false);
        txt_tinnhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tinnhanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txt_tinnhan, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_tinnhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_ten_KhachhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ten_KhachhangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ten_KhachhangActionPerformed

    private void txt_phuongtienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_phuongtienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_phuongtienActionPerformed

    private void combo_trangthaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_trangthaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_trangthaiActionPerformed

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btn_xoaActionPerformed

    private void btn_datlaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_datlaiActionPerformed
        // TODO add your handling code here:
        txt_iddangki.setText("");
        txt_ten_Khachhang.setText("");
        txt_ngaydangki.setText(String.valueOf(LocalDate.now()));
        txt_phuongtien.setText("");
        txt_id_khachhang.setText("");
        combo_trangthai.setSelectedIndex(2);
        combo_trangthai.setEnabled(false);
        
        btn_them.setEnabled(true);
        btn_capnhat.setEnabled(false);
        btn_xoa.setEnabled(false);
    }//GEN-LAST:event_btn_datlaiActionPerformed

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        // TODO add your handling code here:
        if (txt_iddangki.getText().trim().length() > 0) {
            System.out.println("Phai tao dki moi");
            return;
        }
        if (txt_id_khachhang.getText().trim().length() <= 0) {
            System.out.println("Chon khach hang");
            return;
        }
        if (txt_phuongtien.getText().trim().length() <= 0) {
            System.out.println("phuong tien dau");
            return;
        }
    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_chonkhachhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chonkhachhangActionPerformed
        // TODO add your handling code here:
        
        // vo hieu hoa 
        this.viewmain.setEnabled(false);
        
        // ghi đè cho các phím xác nhận
        LogMessage logmessage = new LogMessage("Không thể xóa") {
            @Override
            public void action() {
                System.out.println("Tat thong bao");
                this.setVisible(false);
                viewmain.setEnabled(true);
                viewmain.requestFocus();
            }
        };
        logmessage.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // chặn đóng cửa sổ
        logmessage.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
//            JOptionPane.showMessageDialog(null, "Bạn không thể đóng cửa sổ này!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        }
        });
        // hien cua so thong bao
        logmessage.setLocationRelativeTo(null);
        logmessage.setVisible(true);
    }//GEN-LAST:event_btn_chonkhachhangActionPerformed

    private void btn_chonphuongtienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chonphuongtienActionPerformed
        // TODO add your handling code here:
        // init log confirm
        
        // Khống chế viewmain
        this.viewmain.setEnabled(false);
        
        // ghi đè cho các phím xác nhận
        LogConfirm logconfirm = new LogConfirm( "Cảnh báo cảnh báo") {
            @Override
            public void action() {
                System.out.println("DO SOMETHING");
                this.setVisible(false);
                viewmain.setEnabled(true);
                viewmain.requestFocus();
                
                System.out.println("hkjdawhkjđnaokwdnkjàcbnưaeoilfjằdưeanlk");
            }
            @Override
            public void reject() {
                System.out.println("Huy bo thao tac");
                this.setVisible(false);
                viewmain.setEnabled(true);
                viewmain.requestFocus();
            }
        };
        logconfirm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // chặn đóng cửa sổ
        logconfirm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
    //            JOptionPane.showMessageDialog(null, "Bạn không thể đóng cửa sổ này!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        // hiện log xác nhận
        logconfirm.setLocationRelativeTo(null);
        logconfirm.setVisible(true);
    }//GEN-LAST:event_btn_chonphuongtienActionPerformed

    private void txt_timkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timkiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timkiemActionPerformed

    private void btn_conhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_conhanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_conhanActionPerformed

    private void btn_hethanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hethanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_hethanActionPerformed

    private void combo_ngaybatdauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_ngaybatdauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_ngaybatdauActionPerformed

    private void txt_tinnhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tinnhanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tinnhanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_bo_loc;
    private javax.swing.JButton btn_capnhat;
    private javax.swing.JButton btn_chonkhachhang;
    private javax.swing.JButton btn_chonphuongtien;
    private javax.swing.JButton btn_conhan;
    private javax.swing.JButton btn_datlai;
    private javax.swing.JButton btn_hethan;
    private javax.swing.JButton btn_loc;
    private javax.swing.JButton btn_tatca;
    private javax.swing.JButton btn_them;
    private javax.swing.JButton btn_timkiem;
    private javax.swing.JButton btn_xoa;
    private javax.swing.JComboBox<String> combo_nambatdau;
    private javax.swing.JComboBox<String> combo_namketthuc;
    private javax.swing.JComboBox<String> combo_ngaybatdau;
    private javax.swing.JComboBox<String> combo_ngayketthuc;
    private javax.swing.JComboBox<String> combo_thangbatdau;
    private javax.swing.JComboBox<String> combo_thangketthuc;
    private javax.swing.JComboBox<String> combo_trangthai;
    private javax.swing.JLabel inforDetail;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_id_khachhang;
    private javax.swing.JLabel label_id_pt;
    private javax.swing.JLabel label_iddangki;
    private javax.swing.JLabel label_khachhang;
    private javax.swing.JLabel label_ngaydangki;
    private javax.swing.JLabel label_trangthai;
    private javax.swing.JTable table_dangki;
    private javax.swing.JTextField txt_id_khachhang;
    private javax.swing.JTextField txt_iddangki;
    private javax.swing.JTextField txt_ngaydangki;
    private javax.swing.JTextField txt_phuongtien;
    private javax.swing.JTextField txt_ten_Khachhang;
    private javax.swing.JTextField txt_timkiem;
    private javax.swing.JTextField txt_tinnhan;
    // End of variables declaration//GEN-END:variables
}

package GUI.DICHVU;
import Annotation.LogConfirm;
import Annotation.LogMessage;
import DAO.CustomerDAO;
import DAO.RegisatrationDAO;
import DAO.VehicleDAO;
import GUI.ViewMain;
import Global.DataGlobal;
import Model.Customer;
import Model.Regisatration;
import Model.Vehicle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
        comboTrangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Còn thời hạn", "Hết thời hạn", ""}));
        comboTrangthai.setSelectedIndex(2);
        txt_ngaydangki.setText(String.valueOf(LocalDate.now()));
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        initTable();
        fillTable();
        comboDayStart.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
        , "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"
        }));
        comboDayEnd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
        , "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"
        }));
        comboMonthStart.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
        , "11", "12"}));
        comboMonthEnd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
        , "11", "12"}));
        
        comboYearStart.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", 
        "2027", "2028"}));
        comboYearEnd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", 
        "2027", "2028"}));
        
        comboDayStart.setSelectedIndex(0);
        comboDayEnd.setSelectedIndex(0);
        comboMonthStart.setSelectedIndex(0);
        comboMonthEnd.setSelectedIndex(0);
        comboYearStart.setSelectedIndex(0);
        comboYearEnd.setSelectedIndex(0);
        
        Table_dangki.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e) {
                btn_add.setEnabled(false);
                btn_update.setEnabled(true);
                btn_delete.setEnabled(true);
                
                comboTrangthai.setEnabled(true);
                Customer customer = new Customer();
                Vehicle vehicle = new Vehicle();
                int row = Table_dangki.rowAtPoint(e.getPoint());
                ArrayList <Regisatration> arr = RegisatrationDAO.getInstance().getList();
                Regisatration dk = arr.get(row);
                customer = CustomerDAO.getInstance().findbyID(dk.getCustomer_id());
                vehicle = VehicleDAO.getInstance().findbyID(dk.getVehicle_id());
                txt_idDangki.setText(String.valueOf(dk.getRegistration_id()));
                txt_id_khachhang.setText(String.valueOf(customer.getCustomer_id()));
                txt_ten_Khachhang.setText(customer.getFull_name());
                txt_ngaydangki.setText(String.valueOf(dk.getRegistration_date()));
                txt_phuongtien.setText(vehicle.getIdentification_code());
                String trangthai = "";
                if (dk.getState() == 'A') {
                    comboTrangthai.setSelectedIndex(0);
                }
                else {
                    comboTrangthai.setSelectedIndex(1);
                }
                
                arr = null;
                dk = null;
                customer = null;
                vehicle = null;
            }
        });
    }
    public void initTable() {
        String[] header = new String[] {"ID đăng kí", "id Khách hàng",  "Khách hàng", "Ngày đăng kí", "Định danh phương tiện", "Trạng thái"};
        tableModel.setColumnIdentifiers(header);
        Table_dangki.setModel(tableModel);
    }
    
    public void fillTable() {
        ArrayList <Regisatration> listDK = RegisatrationDAO.getInstance().getList();
        Customer customer = new Customer();
        Vehicle vehicle = new Vehicle();
        
        tableModel.setRowCount(0);
        for (Regisatration dk : listDK) {
            try {
                customer = CustomerDAO.getInstance().findbyID(dk.getCustomer_id());
                vehicle = VehicleDAO.getInstance().findbyID(dk.getVehicle_id());
            } catch (Exception e) {
                e.printStackTrace();
            }
            String trangthai = "";
            if (dk.getState() == 'A') {
                trangthai = "Còn thời hạn";
            }
            else {
                trangthai = "Hết thời hạn";
            }
            tableModel.addRow(new String[] {String.valueOf(dk.getRegistration_id()), String.valueOf(customer.getCustomer_id()),customer.getFull_name(), String.valueOf(dk.getRegistration_date()), vehicle.getIdentification_code(), trangthai});
        }
        tableModel.fireTableDataChanged();
        listDK = null;
        customer = null;
        vehicle = null;
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
        Table_dangki = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        txt_idDangki = new javax.swing.JTextField();
        label_id_dangki = new javax.swing.JLabel();
        label_khachhang = new javax.swing.JLabel();
        label_ngaydangki = new javax.swing.JLabel();
        label_id_pt = new javax.swing.JLabel();
        label_trangthai = new javax.swing.JLabel();
        btn_add = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        txt_ten_Khachhang = new javax.swing.JTextField();
        txt_phuongtien = new javax.swing.JTextField();
        inforDetail = new javax.swing.JLabel();
        btn_xem_pt = new javax.swing.JButton();
        txt_ngaydangki = new javax.swing.JTextField();
        comboTrangthai = new javax.swing.JComboBox<>();
        btnResetForm = new javax.swing.JButton();
        label_id_khachhang = new javax.swing.JLabel();
        txt_id_khachhang = new javax.swing.JTextField();
        btnXem_khachhang = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtFind = new javax.swing.JTextField();
        btn_find = new javax.swing.JButton();
        btn_conhan = new javax.swing.JButton();
        btn_hethan = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btn_tatca = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        comboDayStart = new javax.swing.JComboBox<>();
        comboMonthStart = new javax.swing.JComboBox<>();
        comboYearStart = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        comboDayEnd = new javax.swing.JComboBox<>();
        comboMonthEnd = new javax.swing.JComboBox<>();
        comboYearEnd = new javax.swing.JComboBox<>();
        btn_loc = new javax.swing.JButton();
        btn_bo_loc = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setBackground(new java.awt.Color(204, 255, 255));

        Table_dangki.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(Table_dangki);

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

        txt_idDangki.setEnabled(false);

        label_id_dangki.setText("Id đăng kí");

        label_khachhang.setText("Tên Khách hàng");

        label_ngaydangki.setText("Ngày đăng kí");

        label_id_pt.setText("Phương tiện");

        label_trangthai.setText("Trạng thái");

        btn_add.setText("Thêm");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        btn_update.setText("Cập nhật");
        btn_update.setEnabled(false);

        btn_delete.setText("Xóa");
        btn_delete.setEnabled(false);
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        txt_ten_Khachhang.setEnabled(false);
        txt_ten_Khachhang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ten_KhachhangActionPerformed(evt);
            }
        });

        txt_phuongtien.setEnabled(false);
        txt_phuongtien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_phuongtienActionPerformed(evt);
            }
        });

        inforDetail.setText("Thông tin chi tiết");

        btn_xem_pt.setText("Chọn");
        btn_xem_pt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xem_ptActionPerformed(evt);
            }
        });

        txt_ngaydangki.setEnabled(false);

        comboTrangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboTrangthai.setEnabled(false);
        comboTrangthai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTrangthaiActionPerformed(evt);
            }
        });

        btnResetForm.setText("Reset");
        btnResetForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetFormActionPerformed(evt);
            }
        });

        label_id_khachhang.setText("ID Khách hàng");

        txt_id_khachhang.setEnabled(false);

        btnXem_khachhang.setText("Chọn");
        btnXem_khachhang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXem_khachhangActionPerformed(evt);
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
                                .addComponent(btn_add)
                                .addGap(28, 28, 28)
                                .addComponent(btn_update))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(label_id_pt)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txt_phuongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btn_xem_pt, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(label_trangthai)
                                    .addGap(36, 36, 36)
                                    .addComponent(comboTrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(label_id_dangki)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_idDangki))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(label_id_khachhang)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_id_khachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnXem_khachhang, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(label_khachhang)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_ten_Khachhang))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_delete)
                                    .addComponent(btnResetForm))))
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
                            .addComponent(label_id_dangki)
                            .addComponent(txt_idDangki, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_id_khachhang)
                            .addComponent(txt_id_khachhang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXem_khachhang))
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
                            .addComponent(btn_xem_pt))
                        .addGap(18, 18, 18)
                        .addComponent(comboTrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(label_trangthai))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnResetForm)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_add)
                    .addComponent(btn_update)
                    .addComponent(btn_delete))
                .addGap(52, 52, 52))
        );

        txtFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFindActionPerformed(evt);
            }
        });

        btn_find.setText("Tìm kiếm tên");

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
                        .addComponent(btn_find, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_find))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_conhan)
                    .addComponent(btn_hethan)
                    .addComponent(jLabel1)
                    .addComponent(btn_tatca))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel2.setText("Thời gian: Từ");

        comboDayStart.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboDayStart.setMinimumSize(new java.awt.Dimension(100, 22));
        comboDayStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboDayStartActionPerformed(evt);
            }
        });

        comboMonthStart.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboYearStart.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Ngày");

        jLabel4.setText("Tháng");

        jLabel5.setText("Năm");

        jLabel6.setText("Đến");

        comboDayEnd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboMonthEnd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboYearEnd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
                    .addComponent(comboDayStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboMonthStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(comboYearStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6))
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboDayEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboMonthEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_bo_loc))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(comboYearEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(comboDayStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(comboMonthStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboYearStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(comboDayEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboMonthEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboYearEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_loc))
                .addGap(14, 14, 14))
        );

        jTextField1.setText("Đang hiển thị danh sách các đăng kí còn hạn");
        jTextField1.setEnabled(false);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
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
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

    private void comboTrangthaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTrangthaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboTrangthaiActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btnResetFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetFormActionPerformed
        // TODO add your handling code here:
        txt_idDangki.setText("");
        txt_ten_Khachhang.setText("");
        txt_ngaydangki.setText(String.valueOf(LocalDate.now()));
        txt_phuongtien.setText("");
        txt_id_khachhang.setText("");
        comboTrangthai.setSelectedIndex(2);
        comboTrangthai.setEnabled(false);
        
        btn_add.setEnabled(true);
        btn_update.setEnabled(false);
        btn_delete.setEnabled(false);
    }//GEN-LAST:event_btnResetFormActionPerformed

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        // TODO add your handling code here:
        if (txt_idDangki.getText().trim().length() > 0) {
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
    }//GEN-LAST:event_btn_addActionPerformed

    private void btnXem_khachhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXem_khachhangActionPerformed
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
    }//GEN-LAST:event_btnXem_khachhangActionPerformed

    private void btn_xem_ptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xem_ptActionPerformed
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
    }//GEN-LAST:event_btn_xem_ptActionPerformed

    private void txtFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFindActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFindActionPerformed

    private void btn_conhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_conhanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_conhanActionPerformed

    private void btn_hethanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hethanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_hethanActionPerformed

    private void comboDayStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboDayStartActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboDayStartActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Table_dangki;
    private javax.swing.JButton btnResetForm;
    private javax.swing.JButton btnXem_khachhang;
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_bo_loc;
    private javax.swing.JButton btn_conhan;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_find;
    private javax.swing.JButton btn_hethan;
    private javax.swing.JButton btn_loc;
    private javax.swing.JButton btn_tatca;
    private javax.swing.JButton btn_update;
    private javax.swing.JButton btn_xem_pt;
    private javax.swing.JComboBox<String> comboDayEnd;
    private javax.swing.JComboBox<String> comboDayStart;
    private javax.swing.JComboBox<String> comboMonthEnd;
    private javax.swing.JComboBox<String> comboMonthStart;
    private javax.swing.JComboBox<String> comboTrangthai;
    private javax.swing.JComboBox<String> comboYearEnd;
    private javax.swing.JComboBox<String> comboYearStart;
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
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel label_id_dangki;
    private javax.swing.JLabel label_id_khachhang;
    private javax.swing.JLabel label_id_pt;
    private javax.swing.JLabel label_khachhang;
    private javax.swing.JLabel label_ngaydangki;
    private javax.swing.JLabel label_trangthai;
    private javax.swing.JTextField txtFind;
    private javax.swing.JTextField txt_idDangki;
    private javax.swing.JTextField txt_id_khachhang;
    private javax.swing.JTextField txt_ngaydangki;
    private javax.swing.JTextField txt_phuongtien;
    private javax.swing.JTextField txt_ten_Khachhang;
    // End of variables declaration//GEN-END:variables
}

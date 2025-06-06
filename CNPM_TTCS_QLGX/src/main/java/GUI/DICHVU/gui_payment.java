/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.DICHVU;

import Annotation.LogConfirm;
import Annotation.LogMessage;
import Annotation.LogSelection;
import DAO.CustomerDAO;
import DAO.PaymentDAO;
import DAO.RegisatrationDAO;
import DAO.ServiceFeeDAO;
import DAO.TypeServiceDAO;
import DAO.VehicleDAO;
import DatabaseHelper.OpenConnection;
import GUI.ViewMain;
import Global.DataGlobal;
import Model.Customer;
import Model.Payment;
import Model.Regisatration;
import Model.TypeService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author manhh
 */
public class gui_payment extends javax.swing.JPanel {
    private DefaultTableModel tableModel;
    private ViewMain viewmain;
    private LogConfirm logConfirm;
    private LogMessage logMessage;
    private LogSelection logSelection;
    private boolean cursorBreak = false;
    private DataGlobal dataglobal;
    /**
     * Creates new form gui_payment
     * @param viewmain
     * @param logConfirm
     * @param logMessage
     * @param logSelection
     */
    public gui_payment(ViewMain viewmain, LogConfirm logConfirm, LogMessage logMessage, LogSelection logSelection, DataGlobal dataglobal) {
        this.viewmain = viewmain;
        this.logConfirm = logConfirm;
        this.logMessage = logMessage;
        this.logSelection = logSelection;
        this.dataglobal = dataglobal;
        
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        initComponents();
        txt_ngaylendon.setText(String.valueOf(LocalDate.now()));
        combo_trangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đã hoàn tất", "Chưa hoàn tất"}));
        combo_trangthai.setSelectedIndex(1);
        
        initTable();
        this.dataglobal.updateArrPaymentRender();
        fillTable();
        
        comboDayStart.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10"
        , "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"
        }));
        comboDayEnd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10"
        , "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"
        }));
        comboMonthStart.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10"
        , "11", "12"}));
        comboMonthEnd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10"
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
        
        table_thanhtoan.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e) {
                btn_them.setEnabled(false);
                btn_capnhat.setEnabled(true);
                btn_xoa.setEnabled(true);
                combo_trangthai.setEnabled(true);
                
                int row = table_thanhtoan.rowAtPoint(e.getPoint());
                ArrayList<String> dataToShow = new ArrayList<>();
                for (ArrayList<String> data : dataglobal.getArrPayment_render()) {
                    if (data.get(0).equals((String) table_thanhtoan.getValueAt(row, 0))) {
                        dataToShow = data;
                        break;
                    }
                }
                
                txt_idthanhtoan.setText((String) table_thanhtoan.getValueAt(row, 0));
                txt_iddangki.setText((String) table_thanhtoan.getValueAt(row, 1));
                txt_tenkhachhang.setText((String) table_thanhtoan.getValueAt(row, 2));
                txt_ngaylendon.setText((String) table_thanhtoan.getValueAt(row, 3));
                txt_tendichvu.setText((String) table_thanhtoan.getValueAt(row, 4));
                txt_idloaidichvu.setText(dataToShow.get(6));
                if (((String) table_thanhtoan.getValueAt(row, 5)).equals("Đã hoàn tất")) {
                    combo_trangthai.setSelectedIndex(0);
                }
                else {
                    combo_trangthai.setSelectedIndex(1);
                }
            }
        });
        LocalDate currentDate = LocalDate.now();
        String daynow = String.valueOf(currentDate.getDayOfMonth());
        if (daynow.length() == 1) {daynow = "0" + daynow;}
        String monthnow = String.valueOf(currentDate.getMonthValue());
        if (monthnow.length() == 1) {monthnow = "0" + monthnow;}
        
        comboDayEnd.setSelectedItem(daynow);
        comboMonthEnd.setSelectedItem(monthnow);
        comboYearEnd.setSelectedItem(String.valueOf(currentDate.getYear()));
        table_thanhtoan.setRowHeight(30);
    }
    
    private void initTable() {
        String[] header = new String[] {"ID thanh toán", "ID đăng kí", "Tên khách hàng","Ngày lên đơn", "Loại dịch vụ" ,"Trạng thái"};
        tableModel.setColumnIdentifiers(header);
        table_thanhtoan.setModel(tableModel);
    }
    
    public void fillTable() {
        tableModel.setRowCount(0);
        for (ArrayList<String> payment : this.dataglobal.getArrPayment_render()) {
            tableModel.addRow(new String[] {payment.get(0), payment.get(1),payment.get(2),payment.get(3),payment.get(4), payment.get(5)});
        }
        tableModel.fireTableDataChanged();
        txt_tinnhan.setText("Đang hiển thị danh sách tất cả các thanh toán");
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
        table_thanhtoan = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        label_khachhang = new javax.swing.JLabel();
        label_ngaydangki = new javax.swing.JLabel();
        label_tenloaidichvu = new javax.swing.JLabel();
        label_trangthai = new javax.swing.JLabel();
        btn_them = new javax.swing.JButton();
        btn_capnhat = new javax.swing.JButton();
        btn_xoa = new javax.swing.JButton();
        txt_iddangki = new javax.swing.JTextField();
        txt_idloaidichvu = new javax.swing.JTextField();
        inforDetail = new javax.swing.JLabel();
        txt_ngaylendon = new javax.swing.JTextField();
        combo_trangthai = new javax.swing.JComboBox<>();
        btn_datlai = new javax.swing.JButton();
        label_tenkhachhang = new javax.swing.JLabel();
        txt_tenkhachhang = new javax.swing.JTextField();
        btn_chondangki = new javax.swing.JButton();
        btn_chondichvu = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txt_idthanhtoan = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_tendichvu = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        txt_timkiem = new javax.swing.JTextField();
        btn_timkiem = new javax.swing.JButton();
        btn_dathanhtoan = new javax.swing.JButton();
        btn_chuathanhtoan = new javax.swing.JButton();
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
        txt_tinnhan = new javax.swing.JTextField();
        btn_tailai = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 255, 255));

        table_thanhtoan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(table_thanhtoan);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        label_khachhang.setText("ID đăng kí");

        label_ngaydangki.setText("Ngày lên đơn");

        label_tenloaidichvu.setText("ID loại dịch vụ");

        label_trangthai.setText("Trạng thái");

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

        txt_iddangki.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        txt_iddangki.setEnabled(false);
        txt_iddangki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_iddangkiActionPerformed(evt);
            }
        });

        txt_idloaidichvu.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        txt_idloaidichvu.setEnabled(false);
        txt_idloaidichvu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idloaidichvuActionPerformed(evt);
            }
        });

        inforDetail.setText("Thông tin chi tiết");

        txt_ngaylendon.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        txt_ngaylendon.setEnabled(false);

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

        label_tenkhachhang.setText("Tên khách hàng");

        txt_tenkhachhang.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        txt_tenkhachhang.setEnabled(false);

        btn_chondangki.setText("Chọn");
        btn_chondangki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chondangkiActionPerformed(evt);
            }
        });

        btn_chondichvu.setText("Chọn dịch vụ");
        btn_chondichvu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chondichvuActionPerformed(evt);
            }
        });

        jLabel10.setText("ID thanh toán");

        txt_idthanhtoan.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        txt_idthanhtoan.setEnabled(false);

        jLabel11.setText("Tên loại dịch vụ");

        txt_tendichvu.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(label_tenkhachhang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(txt_idloaidichvu, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btn_chondichvu, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txt_ngaylendon, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(txt_iddangki, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btn_chondangki))
                                    .addComponent(txt_tenkhachhang, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txt_idthanhtoan, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(label_trangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(label_ngaydangki, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(label_khachhang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(23, 23, 23))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(label_tenloaidichvu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_tendichvu)
                                    .addComponent(combo_trangthai, 0, 162, Short.MAX_VALUE))))
                        .addGap(22, 22, 22))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_them)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_capnhat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_xoa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_datlai)
                        .addGap(0, 18, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(inforDetail)
                .addGap(128, 128, 128))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(inforDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txt_idthanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_tenkhachhang)
                    .addComponent(txt_tenkhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_khachhang)
                    .addComponent(txt_iddangki, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_chondangki))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_ngaydangki)
                    .addComponent(txt_ngaylendon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_tenloaidichvu)
                    .addComponent(txt_idloaidichvu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_chondichvu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txt_tendichvu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combo_trangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_trangthai))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_them)
                    .addComponent(btn_capnhat)
                    .addComponent(btn_xoa)
                    .addComponent(btn_datlai))
                .addGap(30, 30, 30))
        );

        txt_timkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_timkiemActionPerformed(evt);
            }
        });
        txt_timkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_timkiemKeyReleased(evt);
            }
        });

        btn_timkiem.setText("Tìm kiếm");
        btn_timkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timkiemActionPerformed(evt);
            }
        });

        btn_dathanhtoan.setText("Đã HT");
        btn_dathanhtoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dathanhtoanActionPerformed(evt);
            }
        });

        btn_chuathanhtoan.setText("Chưa HT");
        btn_chuathanhtoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chuathanhtoanActionPerformed(evt);
            }
        });

        jLabel1.setText("Danh sách:");

        btn_tatca.setText("Tất cả");
        btn_tatca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tatcaActionPerformed(evt);
            }
        });

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
                        .addComponent(btn_dathanhtoan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_chuathanhtoan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_tatca))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btn_timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
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
                    .addComponent(btn_dathanhtoan)
                    .addComponent(btn_chuathanhtoan)
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
        btn_loc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_locActionPerformed(evt);
            }
        });

        btn_bo_loc.setText("Bỏ lọc");
        btn_bo_loc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bo_locActionPerformed(evt);
            }
        });

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
                    .addComponent(jLabel9)
                    .addComponent(comboYearEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_loc, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                    .addComponent(btn_bo_loc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        txt_tinnhan.setText("Đang hiển thị danh sách tất cả các thanh toán.");
        txt_tinnhan.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        txt_tinnhan.setEnabled(false);

        btn_tailai.setText("Tải lại");
        btn_tailai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tailaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt_tinnhan, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 277, Short.MAX_VALUE)
                                .addComponent(btn_tailai))
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_tinnhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_tailai))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_timkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timkiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timkiemActionPerformed

    private void btn_chuathanhtoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chuathanhtoanActionPerformed
        // TODO add your handling code here:
        int index = 0;
        tableModel.setRowCount(0);
        for (ArrayList<String> arr : this.dataglobal.getArrPayment_render()) {
            if (arr.get(5).equals("Chưa hoàn tất")) {
                tableModel.addRow(new String[] {arr.get(0), arr.get(1), arr.get(2), arr.get(3), arr.get(4), arr.get(5)});
                ++index;
            }
        }
        txt_tinnhan.setText("Đang hiển thị danh sách các thanh toán chưa hoàn tất thanh toán");
        if (index == this.dataglobal.getArrPayment_render().size()) {
            txt_tinnhan.setText("Đang hiển thị danh sách tất cả các đơn thanh toán");
        }
    }//GEN-LAST:event_btn_chuathanhtoanActionPerformed

    private void btn_dathanhtoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dathanhtoanActionPerformed
        // TODO add your handling code here:
        int index = 0;
        tableModel.setRowCount(0);
        for (ArrayList<String> arr : this.dataglobal.getArrPayment_render()) {
            if (arr.get(5).equals("Đã hoàn tất")) {
                tableModel.addRow(new String[] {arr.get(0), arr.get(1), arr.get(2), arr.get(3), arr.get(4), arr.get(5)});
                ++index;
            }
        }
        txt_tinnhan.setText("Đang hiển thị danh sách các thanh toán đã hoàn tất thanh toán");
        if (index == this.dataglobal.getArrPayment_render().size()) {
            txt_tinnhan.setText("Đang hiển thị danh sách tất cả các thanh toán");
        }
    }//GEN-LAST:event_btn_dathanhtoanActionPerformed

    private void comboDayStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboDayStartActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboDayStartActionPerformed

    private void btn_datlaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_datlaiActionPerformed
        btn_them.setEnabled(true);
        btn_capnhat.setEnabled(false);
        btn_xoa.setEnabled(false);
        combo_trangthai.setEnabled(false);
        combo_trangthai.setSelectedIndex(1);
        
        txt_idthanhtoan.setText("");
        txt_iddangki.setText("");
        txt_idloaidichvu.setText("");
        txt_tenkhachhang.setText("");
        txt_ngaylendon.setText(String.valueOf(LocalDate.now()));    
        txt_tendichvu.setText("");
    }//GEN-LAST:event_btn_datlaiActionPerformed

    private void combo_trangthaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_trangthaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_trangthaiActionPerformed

    private void txt_idloaidichvuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idloaidichvuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idloaidichvuActionPerformed

    private void txt_iddangkiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_iddangkiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_iddangkiActionPerformed
    private void processDelete() {
        if (PaymentDAO.getInstance().findbyID(Integer.parseInt(txt_idthanhtoan.getText())).isPayment_state()) {
            logError("Không thể xóa khi đã thanh toán thành công");
            return;
        }
        String rs = PaymentDAO.getInstance().delete(Integer.parseInt(txt_idthanhtoan.getText()));
        logError(rs);
        this.dataglobal.updateArrPaymentRender();
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
    private void logError(String rs) {
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
    }
    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        // TODO add your handling code here:
        if (txt_iddangki.getText().equals("")) {
            logError("Phải chọn đăng kí để thanh toán");
            return;
        }
        if (txt_idloaidichvu.getText().equals("")) {
            logError("Phải chọn loại dịch vụ");
            return;
        }
        int id_registration = Integer.parseInt(txt_iddangki.getText());
        int id_serviceType = Integer.parseInt(txt_idloaidichvu.getText());
        Payment payment = new Payment(1, id_registration , LocalDate.now(), false, id_serviceType);
        
        for (Payment pm : PaymentDAO.getInstance().getList()) {
            if (pm.getRegistration_id() == payment.getRegistration_id() && pm.isPayment_state() == false) {
                logError("Đăng kí này chưa hoàn tất một hóa đơn khác, không thể tạo lập hóa đơn mới");
                return; 
            }
        }
        int id_regis = payment.getRegistration_id();
        int id_vehicle = RegisatrationDAO.getInstance().findbyID(id_regis).getVehicle_id();
        int id_vehicle_type1 = VehicleDAO.getInstance().findbyID(id_vehicle).getVehicle_type_id();
        
        int serviceType_id = payment.getService_type_id();
        int serviceFee_id = TypeServiceDAO.getInstance().findbyID(serviceType_id).getService_fee_id();
        int id_vehicle_type2 = ServiceFeeDAO.getInstance().findbyID(serviceFee_id).getVehicle_type_id();
        
        if (id_vehicle_type1 != id_vehicle_type2) {
            logError("Loại phương tiện trên đăng kí và loại dịch vụ không tương thích");
            return;
        }
        
        String rs = PaymentDAO.getInstance().insert(payment);
        logError(rs);
        this.dataglobal.updateArrPaymentRender();
        fillTable();
    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_locActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_locActionPerformed
        // TODO add your handling code here:
        int index = 0;
        LocalDate dateStart;
        LocalDate dateEnd;
        try {
            dateStart = LocalDate.parse(comboYearStart.getSelectedItem() + "-" + comboMonthStart.getSelectedItem() + "-" + comboDayStart.getSelectedItem());
            dateEnd = LocalDate.parse(comboYearEnd.getSelectedItem() + "-" + comboMonthEnd.getSelectedItem() + "-" + comboDayEnd.getSelectedItem());
        } catch (Exception e) {
            logError("Ngày tháng không hợp lệ");
            return;
        }
        
        tableModel.setRowCount(0);
        for (ArrayList<String> arr : this.dataglobal.getArrPayment_render()) {
            LocalDate dateofArr = LocalDate.parse(arr.get(3));
            if (dateStart.isBefore(dateofArr.plusDays(1)) && dateEnd.isAfter(dateofArr.minusDays(1))) {
                ++index;
                tableModel.addRow(new String[] {arr.get(0), arr.get(1) ,arr.get(2), arr.get(3), arr.get(4), arr.get(5)});
            }
        }
        tableModel.fireTableDataChanged();
        txt_tinnhan.setText("Đang hiển thị danh sách các thanh toán đã lọc theo thời gian");
        if (index == this.dataglobal.getArrPayment_render().size()) {
            txt_tinnhan.setText("Đang hiển thị danh sách tất cả các thanh toán");
        }
    }//GEN-LAST:event_btn_locActionPerformed

    private void btn_tatcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tatcaActionPerformed
        // TODO add your handling code here:
        fillTable();
    }//GEN-LAST:event_btn_tatcaActionPerformed

    private void btn_timkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timkiemActionPerformed
        // TODO add your handling code here:
        int index = 0;
        tableModel.setRowCount(0);
        for (ArrayList<String> arr : this.dataglobal.getArrPayment_render()) {
            if (Library.Library.StringOnString(txt_timkiem.getText(), arr.get(2))) {
                tableModel.addRow(new String[] {arr.get(0), arr.get(1), arr.get(2), arr.get(3), arr.get(4), arr.get(5)});
                ++index;
            }
        }
        txt_tinnhan.setText("Đang hiển thị danh sách các thanh toán đã lọc theo tên khách hàng");
        if (index == this.dataglobal.getArrPayment_render().size()) {
            txt_tinnhan.setText("Đang hiển thị danh sách tất cả các thanh toán");
        }
    }//GEN-LAST:event_btn_timkiemActionPerformed

    private void btn_bo_locActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bo_locActionPerformed
        // TODO add your handling code here:
        fillTable();
    }//GEN-LAST:event_btn_bo_locActionPerformed

    private void btn_chondangkiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chondangkiActionPerformed
        // TODO add your handling code here:
        this.viewmain.setEnabled(false);
        this.logSelection = new LogSelection() {
            @Override
            public void initContent() {
                this.label_property.setText("Tên khách hàng");
                this.tableModel = new DefaultTableModel() {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    };
                };
                // khoi tao cac thanh phan bang o day
                String[] header = new String[] {"ID đăng kí", "Khách hàng", "Ngày đăng kí", "Định danh phương tiện", "Trạng thái"};
                this.tableModel.setColumnIdentifiers(header);
                this.table.setModel(tableModel);
                this.table.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int row = table.rowAtPoint(e.getPoint());
                        if (!((String) table.getValueAt(row, 4)).equals("Bi huy")) {
                            txt_tenkhachhang.setText((String) table.getValueAt(row, 1));
                            txt_iddangki.setText((String) table.getValueAt(row, 0));
                            
                            logSelection.setVisible(false);
                            viewmain.setEnabled(true);
                            viewmain.requestFocus();
                        }
                        else {
                            viewmain.setEnabled(false);
                            logMessage = new LogMessage("Không thể chọn đăng kí đã hết hạn") {
                                @Override
                                public void action() {
                                    this.setVisible(false);
                                    viewmain.setEnabled(true);
                                    viewmain.requestFocus();
                                    logSelection.requestFocus();
                                }
                            };
                            logMessage.setVisible(true);
                        }
                    }
                });
                gui_registration tmp = new gui_registration();
                ArrayList<ArrayList<String>> tmpDataRegistrationSelect = dataglobal.getArrRegistration_render();

                for (ArrayList<String> rs : tmpDataRegistrationSelect) {
                    if (!rs.get(5).equals("Bi huy")) {
                        this.tableModel.addRow(new String[] {rs.get(0), rs.get(2), rs.get(3), rs.get(4), rs.get(5)});
                    }
                }
                
                this.tableModel.fireTableDataChanged();
                
                this.btn_loc.addActionListener((ActionEvent e) -> {
                    
                });
                this.btn_boloc.addActionListener(((ActionEvent e) -> {
                    
                }));
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
    }//GEN-LAST:event_btn_chondangkiActionPerformed
    private String last = "";
    private void txt_timkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timkiemKeyReleased
        // TODO add your handling code here:
        if (Library.Library.isValidString(txt_timkiem.getText())) {
            this.last = txt_timkiem.getText();
        }
        else {
            txt_timkiem.setText(this.last);
        }
    }//GEN-LAST:event_txt_timkiemKeyReleased

    private void btn_tailaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tailaiActionPerformed
        // TODO add your handling code here:
        this.dataglobal.updateArrPaymentRender();
        fillTable();
    }//GEN-LAST:event_btn_tailaiActionPerformed

    private void btn_chondichvuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chondichvuActionPerformed
        // TODO add your handling code here:
        this.viewmain.setEnabled(false);
        this.logSelection = new LogSelection() {
            @Override
            public void initContent() {
                this.label_property.setText("Tên loại dịch vụ");
                this.tableModel = new DefaultTableModel() {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    };
                };
                // khoi tao cac thanh phan bang o day
                String[] header = new String[] {"ID loại dịch vụ", "Tên dịch vụ", "Tên loại xe", "Số tháng", "Tổng chi phí"};
                this.tableModel.setColumnIdentifiers(header);
                this.table.setModel(tableModel);
                this.table.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int row = table.rowAtPoint(e.getPoint());
                        // hien thi cai xe dc chọn
                        txt_idloaidichvu.setText((String) table.getValueAt(row, 0));
                        txt_tendichvu.setText((String) table.getValueAt(row, 1));
                        logSelection.setVisible(false);
                        viewmain.setEnabled(true);
                        viewmain.requestFocus();
                    }
                });
                ArrayList<ArrayList<String>> arrServiceType = dataglobal.getArrServiceType_render();
                
                for (ArrayList<String> service_type : arrServiceType) {
                    String id_loaidichvu = service_type.get(0);
                    String tenLoaidichvu = service_type.get(1);
                    String loaiPT = service_type.get(4);
                    String soThang = service_type.get(5);
                    String soTien1Thang = service_type.get(2);
                    String heso = service_type.get(6);
                    
                    float tongsotien = Float.parseFloat(soThang) * Float.parseFloat(soTien1Thang) * Float.parseFloat(heso) / 100;
                    if (service_type.get(7).equals("Còn hạn")) {
                        this.tableModel.addRow(new String[] {id_loaidichvu, tenLoaidichvu, loaiPT, soThang, String.valueOf(tongsotien) + " VNĐ"});
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
    }//GEN-LAST:event_btn_chondichvuActionPerformed
    private void processUpdate() {
        // TODO add your handling code here:
        int id_thanhtoan = Integer.parseInt(txt_idthanhtoan.getText());
        int id_dangki = Integer.parseInt(txt_iddangki.getText());
        LocalDate extension_time = LocalDate.parse(txt_ngaylendon.getText());
        String trangthai = (String) combo_trangthai.getSelectedItem();
        boolean state = false;
        if (trangthai.equals("Đã hoàn tất")) {
            state = true;
        }
        int serviceTypeId = Integer.parseInt(txt_idloaidichvu.getText());
        Payment payment = new Payment(id_thanhtoan, id_dangki, extension_time, state, serviceTypeId);
        
        if (payment.isPayment_state()) {
            logError("Không thể thay đổi khi đã thanh toán thành công");
            return;
        }
        
        int id_regis = payment.getRegistration_id();
        int id_vehicle = RegisatrationDAO.getInstance().findbyID(id_regis).getVehicle_id();
        int id_vehicle_type1 = VehicleDAO.getInstance().findbyID(id_vehicle).getVehicle_type_id();
        
        int serviceType_id = payment.getService_type_id();
        int serviceFee_id = TypeServiceDAO.getInstance().findbyID(serviceType_id).getService_fee_id();
        int id_vehicle_type2 = ServiceFeeDAO.getInstance().findbyID(serviceFee_id).getVehicle_type_id();
        
        if (id_vehicle_type1 != id_vehicle_type2) {
            logError("Loại phương tiện trên đăng kí và loại dịch vụ không tương thích");
            return;
        }
        
        String rs = PaymentDAO.getInstance().update(payment);
        logError(rs);
        dataglobal.updateArrPaymentRender();
        fillTable();
    }
    private void btn_capnhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_capnhatActionPerformed
        this.viewmain.setEnabled(false);
        this.cursorBreak = false;

        this.logConfirm = new LogConfirm("Bạn có chắc là muốn cập nhật ?") {
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
    }//GEN-LAST:event_btn_capnhatActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_bo_loc;
    private javax.swing.JButton btn_capnhat;
    private javax.swing.JButton btn_chondangki;
    private javax.swing.JButton btn_chondichvu;
    private javax.swing.JButton btn_chuathanhtoan;
    private javax.swing.JButton btn_dathanhtoan;
    private javax.swing.JButton btn_datlai;
    private javax.swing.JButton btn_loc;
    private javax.swing.JButton btn_tailai;
    private javax.swing.JButton btn_tatca;
    private javax.swing.JButton btn_them;
    private javax.swing.JButton btn_timkiem;
    private javax.swing.JButton btn_xoa;
    private javax.swing.JComboBox<String> comboDayEnd;
    private javax.swing.JComboBox<String> comboDayStart;
    private javax.swing.JComboBox<String> comboMonthEnd;
    private javax.swing.JComboBox<String> comboMonthStart;
    private javax.swing.JComboBox<String> comboYearEnd;
    private javax.swing.JComboBox<String> comboYearStart;
    private javax.swing.JComboBox<String> combo_trangthai;
    private javax.swing.JLabel inforDetail;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JLabel label_khachhang;
    private javax.swing.JLabel label_ngaydangki;
    private javax.swing.JLabel label_tenkhachhang;
    private javax.swing.JLabel label_tenloaidichvu;
    private javax.swing.JLabel label_trangthai;
    private javax.swing.JTable table_thanhtoan;
    private javax.swing.JTextField txt_iddangki;
    private javax.swing.JTextField txt_idloaidichvu;
    private javax.swing.JTextField txt_idthanhtoan;
    private javax.swing.JTextField txt_ngaylendon;
    private javax.swing.JTextField txt_tendichvu;
    private javax.swing.JTextField txt_tenkhachhang;
    private javax.swing.JTextField txt_timkiem;
    private javax.swing.JTextField txt_tinnhan;
    // End of variables declaration//GEN-END:variables
}

package GUI.DICHVU;
import Annotation.LogConfirm;
import Annotation.LogMessage;
import Annotation.LogSelection;
import DAO.CustomerDAO;
import DAO.PaymentDAO;
import DAO.RegisatrationDAO;
import DAO.VehicleDAO;
import DAO.VehicleTypeDAO;
import DatabaseHelper.OpenConnection;
import GUI.ViewMain;
import Global.DataGlobal;
import Model.Customer;
import Model.Payment;
import Model.Regisatration;
import Model.Vehicle;
import Model.VehicleType;
import Model.Payment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
public class gui_registration extends javax.swing.JPanel {
    private DefaultTableModel tableModel;
    private ViewMain viewmain;
    private LogConfirm logConfirm;
    private LogMessage logMessage;
    private LogSelection logSelection;
    private boolean cursorBreak = false;
    private DataGlobal dataglobal;
    /**
     * Creates new form registration
     * @return 
     */
    public ArrayList<ArrayList<String>> shareDataregistration() {
        dataglobal.updateArrRegistrationRender();
        return this.dataglobal.getArrRegistration_render().stream()
                .map(sublist -> new ArrayList<>(sublist))
                .collect(Collectors.toCollection(ArrayList::new));
    }
    public gui_registration() {}
    public gui_registration(ViewMain viewmain, LogConfirm logConfirm, LogMessage logMessage, LogSelection logSelection, DataGlobal dataglobal) {
        this.viewmain = viewmain;
        this.logConfirm = logConfirm;
        this.logMessage = logMessage;
        this.logSelection = logSelection;
        this.dataglobal = dataglobal;
        
        initComponents();
        combo_trangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "San sang gia han", "Dang con han", "Bi huy"}));
        combo_trangthai.setSelectedIndex(0);
        txt_ngaydangki.setText(String.valueOf(LocalDate.now()));
        // 
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        initTable();
        this.dataglobal.updateArrRegistrationRender();
        fillTable();
        
        combo_ngaybatdau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10"
        , "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"
        }));
        combo_ngayketthuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10"
        , "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"
        }));
        combo_thangbatdau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10"
        , "11", "12"}));
        combo_thangketthuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10"
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
                
                ArrayList <String> rs_view = dataglobal.getArrRegistration_render().get(row);
                
                txt_iddangki.setText(rs_view.get(0));
                txt_id_khachhang.setText(rs_view.get(1));
                txt_ten_Khachhang.setText(rs_view.get(2));
                txt_ngaydangki.setText(rs_view.get(3));
                txt_phuongtien.setText(rs_view.get(4));
                if (rs_view.get(5).equals("San sang gia han")) {
                    combo_trangthai.setSelectedIndex(0);
                } else {
                    if (rs_view.get(5).equals("Dang con han")) {
                        combo_trangthai.setSelectedIndex(1);
                    } else {
                        combo_trangthai.setSelectedIndex(2);
                    }
                }
            }
        });
        LocalDate currentDate = LocalDate.now();
        String daynow = String.valueOf(currentDate.getDayOfMonth());
        if (daynow.length() == 1) {daynow = "0" + daynow;}
        String monthnow = String.valueOf(currentDate.getMonthValue());
        if (monthnow.length() == 1) {monthnow = "0" + monthnow;}
        
        combo_ngayketthuc.setSelectedItem(daynow);
        combo_thangketthuc.setSelectedItem(monthnow);
        combo_namketthuc.setSelectedItem(String.valueOf(currentDate.getYear()));
        table_dangki.setRowHeight(30);
    }
    private void initTable() {
        String[] header = new String[] {"ID đăng kí", "Khách hàng", "Ngày đăng kí", "Định danh phương tiện", "Trạng thái"};
        tableModel.setColumnIdentifiers(header);
        table_dangki.setModel(tableModel);
    }
    public void fillTable() {
        tableModel.setRowCount(0);
        for (ArrayList<String> arr : this.dataglobal.getArrRegistration_render()) {
            tableModel.addRow(new String[] {arr.get(0), arr.get(2), arr.get(3), arr.get(4), arr.get(5)});
        }
        tableModel.fireTableDataChanged();
        txt_tinnhan.setText("Đang hiển thị danh sách tất cả các đăng kí");
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
        btn_tailai = new javax.swing.JButton();

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
        txt_iddangki.setFocusable(false);
        txt_iddangki.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_iddangkiKeyReleased(evt);
            }
        });

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

        txt_ten_Khachhang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_ten_Khachhang.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        txt_ten_Khachhang.setFocusable(false);
        txt_ten_Khachhang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ten_KhachhangActionPerformed(evt);
            }
        });

        txt_phuongtien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_phuongtien.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        txt_phuongtien.setFocusable(false);
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
        txt_ngaydangki.setFocusable(false);

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
        txt_id_khachhang.setFocusable(false);

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
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inforDetail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_xoa)
                                    .addComponent(btn_datlai)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label_iddangki)
                                    .addComponent(label_id_khachhang))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txt_id_khachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_chonkhachhang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(txt_iddangki)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(label_khachhang)
                                        .addGap(18, 18, 18)
                                        .addComponent(txt_ten_Khachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(label_ngaydangki)
                                            .addComponent(label_id_pt))
                                        .addGap(34, 34, 34)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(txt_phuongtien)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btn_chonphuongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txt_ngaydangki, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(btn_them)
                                        .addGap(28, 28, 28)
                                        .addComponent(btn_capnhat)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(label_trangthai)
                                .addGap(50, 50, 50)
                                .addComponent(combo_trangthai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_iddangki)
                            .addComponent(txt_iddangki, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        txt_timkiem.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txt_timkiemInputMethodTextChanged(evt);
            }
        });
        txt_timkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_timkiemActionPerformed(evt);
            }
        });
        txt_timkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_timkiemKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_timkiemKeyReleased(evt);
            }
        });

        btn_timkiem.setText("Tìm kiếm tên");
        btn_timkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timkiemActionPerformed(evt);
            }
        });

        btn_conhan.setText("Còn hạn");
        btn_conhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_conhanActionPerformed(evt);
            }
        });

        btn_hethan.setText("SS gia hạn");
        btn_hethan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hethanActionPerformed(evt);
            }
        });

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
                        .addComponent(btn_conhan, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_hethan, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_tatca, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txt_tinnhan, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_tailai)))
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_tinnhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_tailai))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    private void processDelete() {
        int registrationId = Integer.parseInt(txt_iddangki.getText());
        
        for (Payment pm : PaymentDAO.getInstance().getList()) {
            if (pm.getRegistration_id() == registrationId) {
                logError("Đã tồn tại đơn thanh toán cho đăng kí này, không thể xóa");
                return;
            }
        }
        
        String rs = RegisatrationDAO.getInstance().delete(registrationId);
        
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
        this.dataglobal.updateArrRegistrationRender();
        fillTable();
    }
    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
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

    private void btn_datlaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_datlaiActionPerformed
        // TODO add your handling code here:
        txt_iddangki.setText("");
        txt_ten_Khachhang.setText("");
        txt_ngaydangki.setText(String.valueOf(LocalDate.now()));
        txt_phuongtien.setText("");
        txt_id_khachhang.setText("");
        combo_trangthai.setSelectedIndex(0);
        combo_trangthai.setEnabled(false);
        
        btn_them.setEnabled(true);
        btn_capnhat.setEnabled(false);
        btn_xoa.setEnabled(false);
    }//GEN-LAST:event_btn_datlaiActionPerformed

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        if (txt_iddangki.getText().trim().length() > 0) {
            this.viewmain.setEnabled(false);
            this.logMessage = new LogMessage("Phải khởi tạo đăng kí mới") {
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
        if (txt_id_khachhang.getText().trim().length() <= 0) {
            this.viewmain.setEnabled(false);
            this.logMessage = new LogMessage("Phải chọn khách hàng") {
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
        if (txt_phuongtien.getText().trim().length() <= 0) {
            this.viewmain.setEnabled(false);
            this.logMessage = new LogMessage("Phải chọn phương tiện") {
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
        int id_pt = -1;
        for (Vehicle vehicle : VehicleDAO.getInstance().getList()) {
            if (vehicle.getIdentification_code().equals(txt_phuongtien.getText())) {
                id_pt = vehicle.getVehicle_id();
                break;
            }
        }
        
        Regisatration registration = new Regisatration(Integer.parseInt(txt_id_khachhang.getText()), 1, LocalDate.now(), id_pt, 'A');
        
        for (Regisatration rs : dataglobal.getArrayRegistration()) {
            if (rs.getCustomer_id() == registration.getCustomer_id() && rs.getVehicle_id() == registration.getVehicle_id() && rs.getState() != 'C') {
                logError("Đăng kí của khách hàng này vẫn đang hoạt động.");
                return;
            }
        }
        for (Regisatration rs : dataglobal.getArrayRegistration()) {
            if (rs.getVehicle_id() == registration.getVehicle_id() && rs.getState() != 'C') {
                logError("Phương tiện này đã được sử dụng bởi người khác");
                return;
            }
        }
        
        int length_before = dataglobal.getArrayRegistration().size();
        String rs = RegisatrationDAO.getInstance().insert(registration);
        dataglobal.updateArrRegistration();
        int length_after = dataglobal.getArrayRegistration().size();
        for (Regisatration tmp : RegisatrationDAO.getInstance().getList()) {
            if (tmp.getCustomer_id() == registration.getCustomer_id() && tmp.getVehicle_id() == registration.getVehicle_id() && rs.equals("Thêm thành công") && length_after == length_before) {
                rs = "Tái kích hoạt thành công";
            }
        }
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
        this.dataglobal.updateArrRegistrationRender();
        fillTable();
    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_chonkhachhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chonkhachhangActionPerformed
        this.viewmain.setEnabled(false);
        this.logSelection = new LogSelection() {
            @Override
            public void initContent() {
                this.btn_sapxep.setText("Sắp xếp theo tên");
                this.label_property.setText("Tên khách hàng");
                this.tableModel = new DefaultTableModel() {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    };
                };
                // khoi tao cac thanh phan bang o day
                String[] header = new String[] {"ID khách hàng", "Tên khách hàng", "Số CCCD", "Số điện thoại"};
                this.tableModel.setColumnIdentifiers(header);
                this.table.setModel(tableModel);
                this.table.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int row = table.rowAtPoint(e.getPoint());
                        txt_id_khachhang.setText((String) table.getValueAt(row, 0));
                        txt_ten_Khachhang.setText((String) table.getValueAt(row, 1));
                        logSelection.setVisible(false);
                        viewmain.setEnabled(true);
                        viewmain.requestFocus();
                    }
                });
                ArrayList<Customer> arrCustomer = CustomerDAO.getInstance().getList();
                for (Customer customer : arrCustomer) {
                    if (customer.isIs_active()) {
                        this.tableModel.addRow(new String[] {String.valueOf(customer.getCustomer_id()), customer.getFull_name(), customer.getSsn(), customer.getPhone_number()});
                    }
                }
                this.tableModel.fireTableDataChanged();
                
                this.btn_loc.addActionListener((ActionEvent e) -> {
                    this.tableModel.setRowCount(0);
                    for (Customer customer : arrCustomer) {
                        if (Library.Library.StringOnString(this.txt_property.getText(), customer.getFull_name())) {
                            this.tableModel.addRow(new String[] {String.valueOf(customer.getCustomer_id()), customer.getFull_name(), customer.getSsn(), customer.getPhone_number()});
                        }
                    }
                    this.tableModel.fireTableDataChanged();
                });
                this.btn_boloc.addActionListener((ActionEvent e) -> {
                    this.tableModel.setRowCount(0);
                    for (Customer customer : arrCustomer) {
                        this.tableModel.addRow(new String[] {String.valueOf(customer.getCustomer_id()), customer.getFull_name(), customer.getSsn(), customer.getPhone_number()});
                    }
                    this.tableModel.fireTableDataChanged();
                });
                
                this.btn_sapxep.addActionListener((ActionEvent e) -> {
                    this.tableModel.setRowCount(0);
                    ArrayList<Customer> tmp_sorted = new ArrayList<>(arrCustomer);
                    tmp_sorted.sort((c1, c2) -> c1.getFull_name().compareTo(c2.getFull_name()));
                    for (Customer customer : tmp_sorted) {
                        this.tableModel.addRow(new String[] {String.valueOf(customer.getCustomer_id()), customer.getFull_name(), customer.getSsn(), customer.getPhone_number()});
                    }
                    this.tableModel.fireTableDataChanged();
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
    }//GEN-LAST:event_btn_chonkhachhangActionPerformed

    private void btn_chonphuongtienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chonphuongtienActionPerformed
        this.viewmain.setEnabled(false);
        this.logSelection = new LogSelection() {
            @Override
            public void initContent() {
                this.label_property.setText("Mã định danh phương tiện");
                this.tableModel = new DefaultTableModel() {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    };
                };
                // khoi tao cac thanh phan bang o day
                String[] header = new String[] {"ID phương tiện", "Mã định danh", "Tên xe"};
                this.tableModel.setColumnIdentifiers(header);
                this.table.setModel(tableModel);
                this.table.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int row = table.rowAtPoint(e.getPoint());
                        // hien thi cai xe dc chọn
                        txt_phuongtien.setText((String) table.getValueAt(row, 1));
                        logSelection.setVisible(false);
                        viewmain.setEnabled(true);
                        viewmain.requestFocus();
                    }
                });
                
                ArrayList<Vehicle> arrVehicles = VehicleDAO.getInstance().getList();
                for (Vehicle vehicle : arrVehicles) {
                    int index_vhType = vehicle.getVehicle_type_id();
                    VehicleType vht = VehicleTypeDAO.getInstance().findbyID(index_vhType);
                    if (vht.isIsPermission()) {
                        this.tableModel.addRow(new String[] {String.valueOf(vehicle.getVehicle_id()), vehicle.getIdentification_code(), vehicle.getVehicle_name()});
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
    }//GEN-LAST:event_btn_chonphuongtienActionPerformed

    private void txt_timkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timkiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timkiemActionPerformed

    private void btn_conhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_conhanActionPerformed
        // TODO add your handling code here:
        tableModel.setRowCount(0);
        int index = 0;
        for (ArrayList<String> arr : this.dataglobal.getArrRegistration_render()) {
            if (arr.get(5).equals("Dang con han")) {
                tableModel.addRow(new String[] {arr.get(0), arr.get(2), arr.get(3), arr.get(4), arr.get(5)});
            }
        }
        tableModel.fireTableDataChanged();
        txt_tinnhan.setText("Đang hiển thị danh sách các đăng kí đã lọc theo trạng thái");
        if (index == this.dataglobal.getArrRegistration_render().size()) {
            txt_tinnhan.setText("Đang hiển thị danh sách tất cả các đăng kí");
        }
    }//GEN-LAST:event_btn_conhanActionPerformed

    private void btn_hethanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hethanActionPerformed
        // TODO add your handling code here:
        tableModel.setRowCount(0);
        int index = 0;
        for (ArrayList<String> arr : this.dataglobal.getArrRegistration_render()) {
            if (arr.get(5).equals("San sang gia han")) {
                tableModel.addRow(new String[] {arr.get(0), arr.get(2), arr.get(3), arr.get(4), arr.get(5)});
            }
        }
        tableModel.fireTableDataChanged();
        txt_tinnhan.setText("Đang hiển thị danh sách các đăng kí đã lọc theo trạng thái");
        if (index == this.dataglobal.getArrRegistration_render().size()) {
            txt_tinnhan.setText("Đang hiển thị danh sách tất cả các đăng kí");
        }
    }//GEN-LAST:event_btn_hethanActionPerformed

    private void combo_ngaybatdauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_ngaybatdauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_ngaybatdauActionPerformed

    private void txt_tinnhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tinnhanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tinnhanActionPerformed
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
    private void btn_locActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_locActionPerformed
        // TODO add your handling code here:
        int index = 0;
        LocalDate dateStart;
        LocalDate dateEnd;
        try {
            dateStart = LocalDate.parse(combo_nambatdau.getSelectedItem() + "-" + combo_thangbatdau.getSelectedItem() + "-" + combo_ngaybatdau.getSelectedItem());
            dateEnd = LocalDate.parse(combo_namketthuc.getSelectedItem() + "-" + combo_thangketthuc.getSelectedItem() + "-" + combo_ngayketthuc.getSelectedItem()); 
        } catch (Exception e) {
            logError("Ngày tháng không hợp lệ");
            return;
        }
        
        tableModel.setRowCount(0);
        for (ArrayList<String> arr : this.dataglobal.getArrRegistration_render()) {
            LocalDate dateofArr = LocalDate.parse(arr.get(3));
            if (dateStart.isBefore(dateofArr.plusDays(1)) && dateEnd.isAfter(dateofArr.minusDays(1))) {
                ++index;
                tableModel.addRow(new String[] {arr.get(0), arr.get(2), arr.get(3), arr.get(4), arr.get(5)});
            }
        }
        tableModel.fireTableDataChanged();
        txt_tinnhan.setText("Đang hiển thị danh sách các đăng kí đã lọc theo thời gian");
        if (index == this.dataglobal.getArrRegistration_render().size()) {
            txt_tinnhan.setText("Đang hiển thị danh sách tất cả các đăng kí");
        }
    }//GEN-LAST:event_btn_locActionPerformed

    private void btn_bo_locActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bo_locActionPerformed
        // TODO add your handling code here:
        fillTable();
    }//GEN-LAST:event_btn_bo_locActionPerformed

    private void btn_timkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timkiemActionPerformed
        int index = 0;
        tableModel.setRowCount(0);
        for (ArrayList<String> arr : this.dataglobal.getArrRegistration_render()) { 
            if (Library.Library.StringOnString(txt_timkiem.getText(), arr.get(2))) {
                tableModel.addRow(new String[] {arr.get(0), arr.get(2), arr.get(3), arr.get(4), arr.get(5)});
                ++index;
            }
        }
        txt_tinnhan.setText("Đang hiển thị danh sách các đăng kí đã lọc theo tên");
        if (index == this.dataglobal.getArrRegistration_render().size()) {
            txt_tinnhan.setText("Đang hiển thị danh sách tất cả các đăng kí");
        }
    }//GEN-LAST:event_btn_timkiemActionPerformed

    private void txt_timkiemInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txt_timkiemInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timkiemInputMethodTextChanged

    private void txt_timkiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timkiemKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timkiemKeyPressed
    private void processUpdate() {
        int id_pt = -1;
        for (Vehicle vehicle : VehicleDAO.getInstance().getList()) {
            if (vehicle.getIdentification_code().equals(txt_phuongtien.getText())) {
                id_pt = vehicle.getVehicle_id();
                break;
            }
        }

        LocalDate datetime = LocalDate.parse(txt_ngaydangki.getText());
        char state = switch (combo_trangthai.getSelectedIndex()) {
            case 1 -> 'B';
            case 2 -> 'C';
            default -> 'A';
        };

        Regisatration registration = new Regisatration(
            Integer.parseInt(txt_id_khachhang.getText()),
            Integer.parseInt(txt_iddangki.getText()),
            datetime, id_pt, state
        );
        
        for (Regisatration rs : dataglobal.getArrayRegistration()) {
            if (rs.getVehicle_id() == registration.getVehicle_id() && 
                    rs.getRegistration_id() != registration.getRegistration_id() &&
                    rs.getState() != 'C'
                    ) {
                logError("Phương tiện này đã được sử dụng ở đăng kí khác");
                return;
            }
        }
        for (Regisatration rs : dataglobal.getArrayRegistration()) {
            if (registration.getRegistration_id() == rs.getRegistration_id() && 
                    (registration.getVehicle_id() != rs.getVehicle_id() || registration.getCustomer_id() != rs.getCustomer_id())) {
                for (Payment pm : PaymentDAO.getInstance().getList()) {
                    if (pm.getRegistration_id() == registration.getRegistration_id()) {
                        logError("Không sửa thông tin về khách hàng và phương tiện");
                        return;
                    }
                }
            }
        }
        for (Regisatration rs : dataglobal.getArrayRegistration()) {
            if (registration.getRegistration_id() != rs.getRegistration_id() && 
                    registration.getVehicle_id() == rs.getVehicle_id() && registration.getCustomer_id() == rs.getCustomer_id()) {
                logError("Trùng lặp đăng kí");
                return;
            }
        }
        
        
        String rs = RegisatrationDAO.getInstance().update(registration);
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
        this.dataglobal.updateArrRegistrationRender();
        fillTable();
}
    private void btn_tatcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tatcaActionPerformed
        // TODO add your handling code here:
        fillTable();
    }//GEN-LAST:event_btn_tatcaActionPerformed
    private void btn_capnhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_capnhatActionPerformed
        this.viewmain.setEnabled(false);
        this.cursorBreak = false;
        
        String idDangki = txt_iddangki.getText();
        String idKhachhang = txt_id_khachhang.getText();
        String idPhuongtien = txt_phuongtien.getText();
        
        for (ArrayList<String> datacheck : this.dataglobal.getArrRegistration_render()) {
            if (!idDangki.equals(datacheck.get(0)) && idKhachhang.equals(datacheck.get(1)) && idPhuongtien.equals(datacheck.get(4))) {
                this.viewmain.setEnabled(false);
                this.logMessage = new LogMessage("Trùng lặp khách hàng và phương tiện") {
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
        }
        
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

    private void txt_timkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timkiemKeyReleased
        // TODO add your handling code here:
        if (Library.Library.isValidString(txt_timkiem.getText())) {
            this.last = txt_timkiem.getText();
        }
        else {
            txt_timkiem.setText(this.last);
        }
    }//GEN-LAST:event_txt_timkiemKeyReleased
    private String last = "";
    private void txt_iddangkiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_iddangkiKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txt_iddangkiKeyReleased

    private void btn_tailaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tailaiActionPerformed
        // TODO add your handling code here:
        this.dataglobal.updateArrRegistrationRender();
        fillTable();
    }//GEN-LAST:event_btn_tailaiActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_bo_loc;
    private javax.swing.JButton btn_capnhat;
    private javax.swing.JButton btn_chonkhachhang;
    private javax.swing.JButton btn_chonphuongtien;
    private javax.swing.JButton btn_conhan;
    private javax.swing.JButton btn_datlai;
    private javax.swing.JButton btn_hethan;
    private javax.swing.JButton btn_loc;
    private javax.swing.JButton btn_tailai;
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

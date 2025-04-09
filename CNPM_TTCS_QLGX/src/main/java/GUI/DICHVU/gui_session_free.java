/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.DICHVU;

import Annotation.LogConfirm;
import Annotation.LogMessage;
import Annotation.LogSelection;
import DAO.SessionFeeDAO;
import DAO.TimeFrameDAO;
import DAO.VehicleTypeDAO;
import DatabaseHelper.OpenConnection;
import GUI.ViewMain;
import Model.SessionFee;
import Model.TimeFrame;
import Model.VehicleType;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author manhh
 */
public class gui_session_free extends javax.swing.JPanel {
    private DefaultTableModel tableModel;
    private ViewMain viewmain;
    private LogConfirm logConfirm;
    private LogMessage logMessage;
    private LogSelection logSelection;
    private boolean cursorBreak = false;
    private ArrayList<ArrayList<String>> dataSessionFee = new ArrayList<>();
    /**
     * Creates new form gui_session_free
     */
    private String lastAmout = "";
    public gui_session_free() {}
    public gui_session_free(ViewMain viewmain, LogConfirm logConfirm, LogMessage logMessage, LogSelection logSelection) {
        this.viewmain = viewmain;
        this.logConfirm = logConfirm;
        this.logMessage = logMessage;
        this.logSelection = logSelection;
        
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        initComponents();
        initTable();
        loadData();
        fillTable();
        table_gialuot.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table_gialuot.rowAtPoint(e.getPoint());
                ArrayList<String> dataRow = dataSessionFee.get(row);
                int index = 0;
                for (ArrayList<String> rs : dataSessionFee) {
                    if (rs.get(0).equals(tableModel.getValueAt(row, 0))) {
                        dataRow = rs;
                        break;
                    }
                }
                btn_them.setEnabled(false);
                btn_capnhat.setEnabled(true);
                btn_xoa.setEnabled(true);
                        
                txt_idbanghimucgia.setText(dataRow.get(0));
                txt_idloaiphuongtien.setText(dataRow.get(1));
                txt_tenphuongtien.setText(dataRow.get(2));
                txt_idkhungthoigian.setText(dataRow.get(3));
                txt_giobatdau.setText(dataRow.get(4));
                txt_gioketthuc.setText(dataRow.get(5));
                txt_giatien1tieng.setText(dataRow.get(6));
                txt_ngaybanhanh.setText(dataRow.get(7));
                if (dataRow.get(8).equals("Còn hạn")) {
                    combo_trangthai.setSelectedIndex(0);
                } else {combo_trangthai.setSelectedIndex(1);}
                combo_trangthai.setEnabled(true);
            }
        });
        
        combo_trangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Còn hạn", "Hết hạn", ""}));
        combo_trangthai.setSelectedIndex(2);
        combo_trangthai.setEnabled(false);
        txt_ngaybanhanh.setText(String.valueOf(LocalDate.now()));
        table_gialuot.setRowHeight(30);
    }
    private void initTable() {
        String[] header = new String[] {"ID giá lượt", "Tên loại phương tiện","Giờ bắt đầu", "Giờ kết thúc" ,"Giá 1 tiếng", "Ngày ban hành", "Trạng thái"};
        tableModel.setColumnIdentifiers(header);
        table_gialuot.setModel(tableModel);
    }
    private void fillTable() {
        tableModel.setRowCount(0);
        for (ArrayList<String> dataRow : this.dataSessionFee) {
            tableModel.addRow(new String[] {dataRow.get(0), dataRow.get(2), dataRow.get(4), dataRow.get(5), Library.Library.formatCurrency(Float.parseFloat(dataRow.get(6))) + " VNĐ", dataRow.get(7), dataRow.get(8)});
        }
        txt_tinnhan.setText("Hiển thị tất cả các mức giá theo lượt của từng loại xe ở các khung giờ");
    }
    private void loadData() {
        this.dataSessionFee.clear();
        String sql = "EXEC SessionFee_render";
        try (
            Connection conn = OpenConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
        )   {
                while (rs.next()) {
                    int session_fee_id = rs.getInt("session_fee_id");
                    int vehicle_type_id = rs.getInt("vehicle_type_id");
                    String vehicle_type_name = rs.getString("vehicle_type_name");
                    int time_frame_id = rs.getInt("time_frame_id");
                    LocalTime time_start = rs.getTime("time_start").toLocalTime();
                    LocalTime time_end = rs.getTime("time_end").toLocalTime();
                    double amount = rs.getDouble("amount");
                    LocalDate decision_date = rs.getDate("decision_date").toLocalDate();
                    boolean is_active = rs.getBoolean("is_active");
                    
                    ArrayList<String> dataRow = new ArrayList<>();
                    
                    dataRow.add(String.valueOf(session_fee_id));
                    dataRow.add(String.valueOf(vehicle_type_id));
                    dataRow.add(vehicle_type_name);
                    dataRow.add(String.valueOf(time_frame_id));
                    dataRow.add(String.valueOf(time_start));
                    dataRow.add(String.valueOf(time_end));
                    dataRow.add(String.valueOf(amount));
                    dataRow.add(String.valueOf(decision_date));
                    
                    if (is_active) {
                        dataRow.add("Còn hạn");
                    }
                    else {
                        dataRow.add("Hết hạn");
                    }
                    
                    this.dataSessionFee.add(dataRow);
                }
                
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
        table_gialuot = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        txt_idbanghimucgia = new javax.swing.JTextField();
        label_id_dangki = new javax.swing.JLabel();
        label_khachhang = new javax.swing.JLabel();
        label_ngaydangki = new javax.swing.JLabel();
        label_id_pt = new javax.swing.JLabel();
        label_trangthai = new javax.swing.JLabel();
        btn_them = new javax.swing.JButton();
        btn_capnhat = new javax.swing.JButton();
        btn_xoa = new javax.swing.JButton();
        txt_tenphuongtien = new javax.swing.JTextField();
        txt_gioketthuc = new javax.swing.JTextField();
        inforDetail = new javax.swing.JLabel();
        txt_giobatdau = new javax.swing.JTextField();
        combo_trangthai = new javax.swing.JComboBox<>();
        btn_datlai = new javax.swing.JButton();
        label_id_khachhang = new javax.swing.JLabel();
        txt_idloaiphuongtien = new javax.swing.JTextField();
        btn_chonloaiphuongtien = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txt_giatien1tieng = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_idkhungthoigian = new javax.swing.JTextField();
        btn_chonkhungthoigian = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txt_ngaybanhanh = new javax.swing.JTextField();
        PanelFilter = new javax.swing.JPanel();
        txt_timkiem = new javax.swing.JTextField();
        btn_timkiem = new javax.swing.JButton();
        btn_conhieuluc = new javax.swing.JButton();
        btn_hethieuluc = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btn_tatca = new javax.swing.JButton();
        txt_tinnhan = new javax.swing.JTextField();

        setBackground(new java.awt.Color(204, 255, 255));

        table_gialuot.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(table_gialuot);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
        );

        txt_idbanghimucgia.setFocusable(false);
        txt_idbanghimucgia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idbanghimucgiaActionPerformed(evt);
            }
        });

        label_id_dangki.setText("ID bản ghi giá lượt");

        label_khachhang.setText("Tên loại phương tiện");

        label_ngaydangki.setText("Giờ bắt đầu");

        label_id_pt.setText("Giờ kết thúc");

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

        txt_tenphuongtien.setFocusable(false);
        txt_tenphuongtien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tenphuongtienActionPerformed(evt);
            }
        });

        txt_gioketthuc.setFocusable(false);
        txt_gioketthuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_gioketthucActionPerformed(evt);
            }
        });

        inforDetail.setText("Thông tin chi tiết về các khung thời gian gửi các loại xe");

        txt_giobatdau.setFocusable(false);

        combo_trangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
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

        label_id_khachhang.setText("ID Loại phương tiện");

        txt_idloaiphuongtien.setFocusable(false);

        btn_chonloaiphuongtien.setText("Chọn");
        btn_chonloaiphuongtien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chonloaiphuongtienActionPerformed(evt);
            }
        });

        jLabel1.setText("Giá tiền/1 tiếng");

        txt_giatien1tieng.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_giatien1tiengKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_giatien1tiengKeyReleased(evt);
            }
        });

        jLabel2.setText("ID Khung thời gian");

        txt_idkhungthoigian.setFocusable(false);

        btn_chonkhungthoigian.setText("Chọn");
        btn_chonkhungthoigian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chonkhungthoigianActionPerformed(evt);
            }
        });

        jLabel3.setText("Ngày ban hành");

        txt_ngaybanhanh.setFocusable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(inforDetail)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_them)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_capnhat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_xoa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_datlai)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(label_id_dangki, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label_ngaydangki, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(label_id_pt)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label_trangthai, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label_khachhang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label_id_khachhang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_idbanghimucgia)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_idloaiphuongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_chonloaiphuongtien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txt_tenphuongtien)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_idkhungthoigian, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_chonkhungthoigian, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE))
                            .addComponent(txt_giobatdau)
                            .addComponent(txt_gioketthuc)
                            .addComponent(txt_giatien1tieng)
                            .addComponent(txt_ngaybanhanh)
                            .addComponent(combo_trangthai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(inforDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_id_dangki)
                    .addComponent(txt_idbanghimucgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_id_khachhang)
                    .addComponent(txt_idloaiphuongtien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_chonloaiphuongtien))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_khachhang)
                    .addComponent(txt_tenphuongtien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_idkhungthoigian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_chonkhungthoigian))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_giobatdau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_ngaydangki))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_id_pt, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_gioketthuc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txt_giatien1tieng, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_ngaybanhanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_trangthai)
                    .addComponent(combo_trangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_them)
                    .addComponent(btn_capnhat)
                    .addComponent(btn_xoa)
                    .addComponent(btn_datlai))
                .addGap(14, 14, 14))
        );

        txt_timkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_timkiemActionPerformed(evt);
            }
        });

        btn_timkiem.setText("Tìm kiếm tên phương tiện");
        btn_timkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timkiemActionPerformed(evt);
            }
        });

        btn_conhieuluc.setText("Còn hiệu lực");
        btn_conhieuluc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_conhieulucActionPerformed(evt);
            }
        });

        btn_hethieuluc.setText("Hết hiệu lực");
        btn_hethieuluc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hethieulucActionPerformed(evt);
            }
        });

        jLabel4.setText("Danh sách:");

        btn_tatca.setText("Tất cả");
        btn_tatca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tatcaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelFilterLayout = new javax.swing.GroupLayout(PanelFilter);
        PanelFilter.setLayout(PanelFilterLayout);
        PanelFilterLayout.setHorizontalGroup(
            PanelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelFilterLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(btn_timkiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(PanelFilterLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_conhieuluc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_hethieuluc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_tatca)
                .addContainerGap())
        );
        PanelFilterLayout.setVerticalGroup(
            PanelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelFilterLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(PanelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_timkiem)
                    .addComponent(txt_timkiem, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_conhieuluc)
                    .addComponent(btn_hethieuluc)
                    .addComponent(jLabel4)
                    .addComponent(btn_tatca))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        txt_tinnhan.setText("Đang hiển thị bảng giá có hiệu lực");
        txt_tinnhan.setFocusable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tinnhan, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(36, 36, 36)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(391, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PanelFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_tinnhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(139, 139, 139)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(29, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        if (txt_idloaiphuongtien.getText().equals("")) {
            this.viewmain.setEnabled(false);
            this.logMessage = new LogMessage("Hãy chọn loại phương tiện") {
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
        if (txt_idkhungthoigian.getText().equals("")) {
            this.viewmain.setEnabled(false);
            this.logMessage = new LogMessage("Hãy chọn khung thời gian") {
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
        if (txt_giatien1tieng.getText().equals("")) {
            this.viewmain.setEnabled(false);
            this.logMessage = new LogMessage("Hãy chọn mức giá tiền") {
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
        
        SessionFee sessionFee = new SessionFee(Integer.parseInt(txt_idkhungthoigian.getText()), 
                Integer.parseInt(txt_idloaiphuongtien.getText()), LocalDate.now(), 1, (int) Float.parseFloat(txt_giatien1tieng.getText()), true);
        String rs = SessionFeeDAO.getInstance().insert(sessionFee);
        
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
    private void processDelete() {
        int id_session_fee = Integer.parseInt(txt_idbanghimucgia.getText());
        String rs = SessionFeeDAO.getInstance().delete(id_session_fee);
        
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

    private void txt_tenphuongtienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tenphuongtienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tenphuongtienActionPerformed

    private void txt_gioketthucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_gioketthucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_gioketthucActionPerformed

    private void combo_trangthaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_trangthaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_trangthaiActionPerformed

    private void btn_datlaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_datlaiActionPerformed
        txt_idbanghimucgia.setText("");
        txt_idloaiphuongtien.setText("");
        txt_tenphuongtien.setText("");
        txt_idkhungthoigian.setText("");
        txt_giobatdau.setText("");
        txt_gioketthuc.setText("");
        txt_giatien1tieng.setText("");
        txt_ngaybanhanh.setText(String.valueOf(LocalDate.now()));
        combo_trangthai.setSelectedIndex(2);
        combo_trangthai.setEnabled(false);
        
        btn_them.setEnabled(true);
        btn_capnhat.setEnabled(false);
        btn_xoa.setEnabled(false);
    }//GEN-LAST:event_btn_datlaiActionPerformed

    private void btn_chonloaiphuongtienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chonloaiphuongtienActionPerformed
        this.viewmain.setEnabled(false);
        this.logSelection = new LogSelection() {
            @Override
            public void initContent() {
                this.btn_sapxep.setText("Sắp xếp theo tên");
                this.label_property.setText("Tên loại phương tiện");
                this.tableModel = new DefaultTableModel() {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    };
                };
                // khoi tao cac thanh phan bang o day
                String[] header = new String[] {"ID loại phương tiện", "Tên loại phương tiện", "Trạng thái"};
                this.tableModel.setColumnIdentifiers(header);
                this.table.setModel(tableModel);
                this.table.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int row = table.rowAtPoint(e.getPoint());
                        txt_idloaiphuongtien.setText((String) table.getValueAt(row, 0));
                        txt_tenphuongtien.setText((String) table.getValueAt(row, 1));
                        logSelection.setVisible(false);
                        viewmain.setEnabled(true);
                        viewmain.requestFocus();
                    }
                });
                ArrayList<VehicleType> vehicle_types_tmp = VehicleTypeDAO.getInstance().getList();
                ArrayList<VehicleType> vehicle_types = new ArrayList<>();
                for (VehicleType vehicle_type : vehicle_types_tmp) {
                    if (vehicle_type.isIsPermission()) {
                        vehicle_types.add(vehicle_type);
                    }
                }
                for (VehicleType vehicle_type : vehicle_types) {
                    String trangthai;
                    if (vehicle_type.isIsPermission()) {
                        trangthai = "Được phép";
                    } else {trangthai = "Không được phép";}
                    this.tableModel.addRow(new String[] {String.valueOf(vehicle_type.getVehicle_type_id()), vehicle_type.getVehicle_type_name(), trangthai});
                }
                this.tableModel.fireTableDataChanged();
                
                this.btn_loc.addActionListener((ActionEvent e) -> {
                    this.tableModel.setRowCount(0);
                    for (VehicleType vehicle_type : vehicle_types) {
                        if (Library.Library.StringOnString(this.txt_property.getText(), vehicle_type.getVehicle_type_name())) {
                            String trangthai;
                            if (vehicle_type.isIsPermission()) {
                                trangthai = "Được phép";
                            } else {trangthai = "Không được phép";}
                            this.tableModel.addRow(new String[] {String.valueOf(vehicle_type.getVehicle_type_id()), vehicle_type.getVehicle_type_name(), trangthai});
                        }
                    }
                    this.tableModel.fireTableDataChanged();
                });
                this.btn_boloc.addActionListener((ActionEvent e) -> {
                    this.tableModel.setRowCount(0);
                    for (VehicleType vehicle_type : vehicle_types) {
                        String trangthai;
                        if (vehicle_type.isIsPermission()) {
                            trangthai = "Được phép";
                        } else {trangthai = "Không được phép";}
                        this.tableModel.addRow(new String[] {String.valueOf(vehicle_type.getVehicle_type_id()), vehicle_type.getVehicle_type_name(), trangthai});
                    }
                });
                
                this.btn_sapxep.addActionListener((ActionEvent e) -> {
                    this.tableModel.setRowCount(0);
                    ArrayList<VehicleType> tmp_sorted = new ArrayList<>(vehicle_types);
                    tmp_sorted.sort((c1, c2) -> c1.getVehicle_type_name().compareTo(c2.getVehicle_type_name()));
                    for (VehicleType vehicle_type : tmp_sorted) {
                            String trangthai;
                            if (vehicle_type.isIsPermission()) {
                                trangthai = "Được phép";
                            } else {trangthai = "Không được phép";}
                            this.tableModel.addRow(new String[] {String.valueOf(vehicle_type.getVehicle_type_id()), vehicle_type.getVehicle_type_name(), trangthai});
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
    }//GEN-LAST:event_btn_chonloaiphuongtienActionPerformed

    private void txt_timkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timkiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timkiemActionPerformed

    private void btn_conhieulucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_conhieulucActionPerformed
        // TODO add your handling code here:
        int index = 0;
        tableModel.setRowCount(0);
        for (ArrayList<String> dataRow : this.dataSessionFee) {
            if (dataRow.get(8).equals("Còn hạn")) {
                tableModel.addRow(new String[] {dataRow.get(0), dataRow.get(2), dataRow.get(4), dataRow.get(5), dataRow.get(6), dataRow.get(7), dataRow.get(8)});
                index++;
            }
        }
        txt_tinnhan.setText("Hiển thị các mức giá theo lượt của từng loại xe ở các khung giờ còn hạn");
        if (index == this.dataSessionFee.size()) {
            txt_tinnhan.setText("Hiển thị tất cả các mức giá theo lượt của từng loại xe ở các khung giờ");
        }
    }//GEN-LAST:event_btn_conhieulucActionPerformed

    private void btn_hethieulucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hethieulucActionPerformed
        // TODO add your handling code here:
        int index = 0;
        tableModel.setRowCount(0);
        for (ArrayList<String> dataRow : this.dataSessionFee) {
            if (!dataRow.get(8).equals("Còn hạn")) {
                tableModel.addRow(new String[] {dataRow.get(0), dataRow.get(2), dataRow.get(4), dataRow.get(5), Library.Library.formatCurrency(Float.parseFloat(dataRow.get(6))) + " VNĐ", dataRow.get(7), dataRow.get(8)});
                index++;
            }
        }
        txt_tinnhan.setText("Hiển thị các mức giá theo lượt của từng loại xe ở các khung giờ hết hạn");
        if (index == this.dataSessionFee.size()) {
            txt_tinnhan.setText("Hiển thị tất cả các mức giá theo lượt của từng loại xe ở các khung giờ");
        }
    }//GEN-LAST:event_btn_hethieulucActionPerformed

    private void txt_idbanghimucgiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idbanghimucgiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idbanghimucgiaActionPerformed

    private void btn_timkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timkiemActionPerformed
        // TODO add your handling code here:
        int index = 0;
        tableModel.setRowCount(0);
        for (ArrayList<String> dataRow : this.dataSessionFee) {
            if (Library.Library.StringOnString(txt_timkiem.getText(), dataRow.get(2))) {
                tableModel.addRow(new String[] {dataRow.get(0), dataRow.get(2), dataRow.get(4), dataRow.get(5), Library.Library.formatCurrency(Float.parseFloat(dataRow.get(6))) + " VNĐ", dataRow.get(7), dataRow.get(8)});
                index++;
            }
        }
        txt_tinnhan.setText("Hiển thị các mức giá theo lượt của từng loại xe ở các khung giờ lọc theo tên");
        if (index == this.dataSessionFee.size()) {
            txt_tinnhan.setText("Hiển thị tất cả các mức giá theo lượt của từng loại xe ở các khung giờ");
        }
    }//GEN-LAST:event_btn_timkiemActionPerformed

    private void btn_tatcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tatcaActionPerformed
        // TODO add your handling code here:
        fillTable();
    }//GEN-LAST:event_btn_tatcaActionPerformed

    private void btn_chonkhungthoigianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chonkhungthoigianActionPerformed
        // TODO add your handling code here:
        this.viewmain.setEnabled(false);
        this.logSelection = new LogSelection() {
            @Override
            public void initContent() {
                this.panel_filter.setVisible(false);
                this.tableModel = new DefaultTableModel() {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    };
                };
                
                String[] header = new String[] {"ID khung thời gian", "Thời gian bắt đầu", "Thòi gian kết thúc"};
                this.tableModel.setColumnIdentifiers(header);
                this.table.setModel(tableModel);
                this.table.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int row = table.rowAtPoint(e.getPoint());
                        txt_idkhungthoigian.setText((String) table.getValueAt(row, 0));
                        txt_giobatdau.setText((String) table.getValueAt(row, 1));
                        txt_gioketthuc.setText((String) table.getValueAt(row, 2));
                        
                        logSelection.setVisible(false);
                        viewmain.setEnabled(true);
                        viewmain.requestFocus();
                    }
                });
                ArrayList<TimeFrame> timeframes = TimeFrameDAO.getInstance().getListToSelect();

                for (TimeFrame timeframe : timeframes) {
                    this.tableModel.addRow(new String[] {String.valueOf(timeframe.getTime_frame_id()), String.valueOf(timeframe.getTime_start()), String.valueOf(timeframe.getTime_end())});
                }
                this.tableModel.fireTableDataChanged();
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
    }//GEN-LAST:event_btn_chonkhungthoigianActionPerformed

    private void txt_giatien1tiengKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_giatien1tiengKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_giatien1tiengKeyPressed

    private void txt_giatien1tiengKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_giatien1tiengKeyReleased
        // TODO add your handling code here:
        if (txt_giatien1tieng.getText().equals("")) { 
            lastAmout = "";
            return;
        }
        if (!Library.Library.isNumber(txt_giatien1tieng.getText())) {
            txt_giatien1tieng.setText(lastAmout);
        }
        else {
            lastAmout = txt_giatien1tieng.getText();
        }
    }//GEN-LAST:event_txt_giatien1tiengKeyReleased
    private void processUpdate() {
        boolean status;
        if (combo_trangthai.getSelectedIndex() == 0) {
            status = true;
        } 
        else {status = false;}
        SessionFee sessionFee = new SessionFee(Integer.parseInt(txt_idkhungthoigian.getText()), 
                Integer.parseInt(txt_idloaiphuongtien.getText()), LocalDate.parse(txt_ngaybanhanh.getText()), Integer.parseInt(txt_idbanghimucgia.getText()), (int) Float.parseFloat(txt_giatien1tieng.getText()), status);
        String rs = SessionFeeDAO.getInstance().update(sessionFee);
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
        // TODO add your handling code here:
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
    public javax.swing.JPanel PanelFilter;
    private javax.swing.JButton btn_capnhat;
    private javax.swing.JButton btn_chonkhungthoigian;
    private javax.swing.JButton btn_chonloaiphuongtien;
    private javax.swing.JButton btn_conhieuluc;
    private javax.swing.JButton btn_datlai;
    private javax.swing.JButton btn_hethieuluc;
    private javax.swing.JButton btn_tatca;
    private javax.swing.JButton btn_them;
    private javax.swing.JButton btn_timkiem;
    private javax.swing.JButton btn_xoa;
    private javax.swing.JComboBox<String> combo_trangthai;
    private javax.swing.JLabel inforDetail;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_id_dangki;
    private javax.swing.JLabel label_id_khachhang;
    private javax.swing.JLabel label_id_pt;
    private javax.swing.JLabel label_khachhang;
    private javax.swing.JLabel label_ngaydangki;
    private javax.swing.JLabel label_trangthai;
    private javax.swing.JTable table_gialuot;
    private javax.swing.JTextField txt_giatien1tieng;
    private javax.swing.JTextField txt_giobatdau;
    private javax.swing.JTextField txt_gioketthuc;
    private javax.swing.JTextField txt_idbanghimucgia;
    private javax.swing.JTextField txt_idkhungthoigian;
    private javax.swing.JTextField txt_idloaiphuongtien;
    private javax.swing.JTextField txt_ngaybanhanh;
    private javax.swing.JTextField txt_tenphuongtien;
    private javax.swing.JTextField txt_timkiem;
    private javax.swing.JTextField txt_tinnhan;
    // End of variables declaration//GEN-END:variables
}

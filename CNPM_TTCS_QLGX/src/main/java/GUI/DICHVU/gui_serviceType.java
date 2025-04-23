/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.DICHVU;

import Annotation.LogConfirm;
import Annotation.LogMessage;
import Annotation.LogSelection;
import DAO.PaymentDAO;
import DAO.ServiceFeeDAO;
import DAO.TypeServiceDAO;
import DAO.VehicleDAO;
import DAO.VehicleTypeDAO;
import DatabaseHelper.OpenConnection;
import GUI.ViewMain;
import Global.DataGlobal;
import Model.Payment;
import Model.ServiceFee;
import Model.TypeService;
import Model.Vehicle;
import Model.VehicleType;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author manhh
 */
public final class gui_serviceType extends javax.swing.JPanel {
    private DefaultTableModel tableModel;
    private ViewMain viewmain;
    private LogConfirm logConfirm;
    private LogMessage logMessage;
    private LogSelection logSelection;
    private boolean cursorBreak = false;
    private DataGlobal dataGlobal;
    /**
     * Creates new form gui_serviceType
     */
    public gui_serviceType(ViewMain viewmain, LogConfirm logConfirm, LogMessage logMessage, LogSelection logSelection, DataGlobal dataGlobal) {
        this.viewmain = viewmain;
        this.logConfirm = logConfirm;
        this.logMessage = logMessage;
        this.logSelection = logSelection;
        this.dataGlobal = dataGlobal;
        
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        initComponents();
        
        combo_trangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Còn hạn", "Hết hạn"}));
        combo_sothang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10"
        , "11", "12"}));
        combo_trangthai.setSelectedIndex(0);
        
        table_loaidichvu.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e) {
                btn_them.setEnabled(false);
                btn_capnhat.setEnabled(true);
                btn_xoa.setEnabled(true);
                combo_trangthai.setEnabled(true);
                combo_sothang.setEnabled(true);
                
                int row = table_loaidichvu.rowAtPoint(e.getPoint());
                String index = (String) table_loaidichvu.getValueAt(row, 0);
                ArrayList<String> dataRenderDetail = new ArrayList<>();
                
                for (ArrayList<String> i : dataGlobal.getArrServiceType_render()) {
                    if (i.get(0).equals(index)) {
                        dataRenderDetail = i;
                        break;
                    }
                }
                txt_idloaidichvu.setText(dataRenderDetail.get(0));
                txt_tendichvu.setText(dataRenderDetail.get(1));
                txt_phidichvu.setText(dataRenderDetail.get(2));
                txt_idPhidichvu.setText(dataRenderDetail.get(3));
                txt_tenloaixe.setText(dataRenderDetail.get(4));
                String soThang = dataRenderDetail.get(5);
                String coefficient = dataRenderDetail.get(6);
                txt_heso.setText(String.valueOf(coefficient));
                String state = dataRenderDetail.get(7);
                txt_ngayapdung.setText(dataRenderDetail.get(9));
                
                if (soThang.length() == 1) {
                    soThang = "0" + soThang;
                }
                combo_sothang.setSelectedItem(soThang);
                if (state.equals("Còn hạn")) {
                    combo_trangthai.setSelectedIndex(0);
                } else {
                    combo_trangthai.setSelectedIndex(1);
                }
            }
        });
        initTable();
        fillTable();
        table_loaidichvu.setRowHeight(30);
        txt_ngayapdung.setText(String.valueOf(LocalDate.now()));
    }
    private void initTable() {
        String[] header = new String[] {"ID dịch vụ", "Tên dịch vụ", "Phí dịch vụ/tháng (%)",  "Loại xe", "Số tháng", "Hệ số", "Trạng thái"};
        tableModel.setColumnIdentifiers(header);
        table_loaidichvu.setModel(tableModel);
    }
    public void fillTable() {
        tableModel.setRowCount(0);
        for (ArrayList<String> dataRow : this.dataGlobal.getArrServiceType_render()) {
            tableModel.addRow(new String[] {
                dataRow.get(0),
                dataRow.get(1),
                dataRow.get(2) + " VNĐ",
                dataRow.get(4),
                dataRow.get(5),
                dataRow.get(6),
                dataRow.get(7)
            });
        }
        txt_tinnhan.setText("Đang hiển thị tất cả các loại dịch vụ");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        hiddenValueID = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_loaidichvu = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        txt_idloaidichvu = new javax.swing.JTextField();
        label_id_dangki = new javax.swing.JLabel();
        label_khachhang = new javax.swing.JLabel();
        label_ngaydangki = new javax.swing.JLabel();
        label_id_pt = new javax.swing.JLabel();
        label_trangthai = new javax.swing.JLabel();
        btn_them = new javax.swing.JButton();
        btn_capnhat = new javax.swing.JButton();
        btn_xoa = new javax.swing.JButton();
        txt_tenloaixe = new javax.swing.JTextField();
        txt_heso = new javax.swing.JTextField();
        inforDetail = new javax.swing.JLabel();
        combo_trangthai = new javax.swing.JComboBox<>();
        btn_datlai = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txt_tendichvu = new javax.swing.JTextField();
        combo_sothang = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txt_phidichvu = new javax.swing.JTextField();
        btn_chonphidichvu = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txt_ngayapdung = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_idPhidichvu = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        txt_timkiem = new javax.swing.JTextField();
        btn_timkiem = new javax.swing.JButton();
        btn_conhan = new javax.swing.JButton();
        btn_hethan = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btn_tatca = new javax.swing.JButton();
        txt_tinnhan = new javax.swing.JTextField();
        btn_tailai = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 255, 255));

        table_loaidichvu.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(table_loaidichvu);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
        );

        txt_idloaidichvu.setFocusable(false);

        label_id_dangki.setText("ID loại dịch vụ");

        label_khachhang.setText("Tên loại xe");

        label_ngaydangki.setText("Số tháng");

        label_id_pt.setText("Hệ số thanh toán (%)");

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

        txt_tenloaixe.setFocusable(false);
        txt_tenloaixe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tenloaixeActionPerformed(evt);
            }
        });

        txt_heso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_hesoActionPerformed(evt);
            }
        });
        txt_heso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_hesoKeyReleased(evt);
            }
        });

        inforDetail.setText("Thông tin chi tiết");

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

        jLabel2.setText("Tên loại dịch vụ");

        txt_tendichvu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_tendichvuKeyReleased(evt);
            }
        });

        combo_sothang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Phí DV/tháng");

        txt_phidichvu.setFocusable(false);

        btn_chonphidichvu.setText("Chọn");
        btn_chonphidichvu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chonphidichvuActionPerformed(evt);
            }
        });

        jLabel4.setText("Ngày áp dụng");

        txt_ngayapdung.setFocusable(false);

        jLabel5.setText("ID phí DV tháng");

        txt_idPhidichvu.setFocusable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(inforDetail)
                .addGap(128, 128, 128))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label_id_pt)
                            .addComponent(label_ngaydangki, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_heso, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_ngayapdung, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(combo_sothang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(label_id_dangki))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addGap(3, 3, 3)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addComponent(label_khachhang))
                        .addGap(65, 65, 65)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_tenloaixe)
                            .addComponent(txt_tendichvu)
                            .addComponent(txt_idloaidichvu)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_phidichvu, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_chonphidichvu, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txt_idPhidichvu)))
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
                        .addComponent(label_trangthai)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(combo_trangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(inforDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_id_dangki)
                    .addComponent(txt_idloaidichvu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_tendichvu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_idPhidichvu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_phidichvu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_chonphidichvu))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_khachhang)
                    .addComponent(txt_tenloaixe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_ngaydangki)
                    .addComponent(combo_sothang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_heso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_id_pt)
                    .addComponent(jLabel4)
                    .addComponent(txt_ngayapdung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combo_trangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_trangthai))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_them)
                    .addComponent(btn_capnhat)
                    .addComponent(btn_xoa)
                    .addComponent(btn_datlai))
                .addContainerGap(42, Short.MAX_VALUE))
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

        btn_timkiem.setText("Tìm kiếm tên");
        btn_timkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timkiemActionPerformed(evt);
            }
        });

        btn_conhan.setText("Còn hạn dùng");
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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(btn_timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_timkiem))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_conhan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_hethan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_tatca)
                        .addGap(0, 317, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_timkiem)
                    .addComponent(txt_timkiem, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_conhan)
                    .addComponent(btn_hethan)
                    .addComponent(jLabel1)
                    .addComponent(btn_tatca))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txt_tinnhan.setText("Đang hiển thị danh sách tất cả các loại dịch vụ.");
        txt_tinnhan.setFocusable(false);

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
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(txt_tinnhan, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(95, 95, 95)
                            .addComponent(btn_tailai))))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_tinnhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_tailai))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_timkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timkiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timkiemActionPerformed

    private void btn_conhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_conhanActionPerformed
        // TODO add your handling code here:
        int index = 0;
        tableModel.setRowCount(0);
        for (ArrayList<String> dataRow : this.dataGlobal.getArrServiceType_render()) {
            if (dataRow.get(7).equals("Còn hạn")) {
                index++;
                tableModel.addRow(new String[] {
                    dataRow.get(0),
                    dataRow.get(1),
                    dataRow.get(2),
                    dataRow.get(4),
                    dataRow.get(5),
                    dataRow.get(6),
                    dataRow.get(7)
                });
            }
        }
        txt_tinnhan.setText("Đang hiển thị tất cả các loại dịch vụ còn hạn");
        if (index == this.dataGlobal.getArrServiceFee_render().size()) {
            txt_tinnhan.setText("Đang hiển thị tất cả các loại dịch vụ");
        }
    }//GEN-LAST:event_btn_conhanActionPerformed

    private void btn_hethanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hethanActionPerformed
        // TODO add your handling code here:
        int index = 0;
        tableModel.setRowCount(0);
        for (ArrayList<String> dataRow : this.dataGlobal.getArrServiceType_render()) {
            if (dataRow.get(7).equals("Hết hạn")) {
                ++index;
                tableModel.addRow(new String[] {
                    dataRow.get(0),
                    dataRow.get(1),
                    dataRow.get(2),
                    dataRow.get(4),
                    dataRow.get(5),
                    dataRow.get(6),
                    dataRow.get(7)
                }); 
            }
        }
        txt_tinnhan.setText("Đang hiển thị tất cả các loại dịch vụ hết hạn");
        if (index == this.dataGlobal.getArrServiceFee_render().size()) {
            txt_tinnhan.setText("Đang hiển thị tất cả các loại dịch vụ");
        }
    }//GEN-LAST:event_btn_hethanActionPerformed

    private void btn_datlaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_datlaiActionPerformed
        // TODO add your handling code here:
        txt_idloaidichvu.setText("");
        txt_tendichvu.setText("");
        txt_phidichvu.setText("");
        txt_tenloaixe.setText("");
        combo_sothang.setSelectedIndex(0);
        txt_heso.setText("");
        txt_ngayapdung.setText(String.valueOf(LocalDate.now()));
        combo_trangthai.setSelectedIndex(0);
        txt_idPhidichvu.setText("");
        
        combo_trangthai.setEnabled(false);
        btn_them.setEnabled(true);
        btn_capnhat.setEnabled(false);
        btn_xoa.setEnabled(false);
    }//GEN-LAST:event_btn_datlaiActionPerformed

    private void combo_trangthaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_trangthaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_trangthaiActionPerformed

    private void txt_hesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_hesoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_hesoActionPerformed

    private void txt_tenloaixeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tenloaixeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tenloaixeActionPerformed
    private void processDelete() {
        int id = Integer.parseInt(txt_idloaidichvu.getText());
        
        for (Payment pm : PaymentDAO.getInstance().getList()) {
            if (pm.getService_type_id() == id) {
                logError("Có đơn thanh toán trên loại dịch vụ này, không thể xóa");
                return;
            }
        }
        
        String rs = TypeServiceDAO.getInstance().delete(id);
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
        dataGlobal.updateArrServiceType_render();
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
        if (txt_tendichvu.getText().length() <= 5) {
            logError("Tên dịch vụ phải dài hơn 5 kí tự");
            return;
        }
        if (txt_idPhidichvu.getText().equals("")) {
            logError("Phải chọn một đơn vị dịch vụ");
            return;
        }
        if (txt_heso.getText().equals("")) {
            logError("Phải điền hệ số thanh toán");
            return;
        }
        String ServiceTypeName = txt_tendichvu.getText();
        int idServiceFee = Integer.parseInt(txt_idPhidichvu.getText());
        int monthUnit = Integer.parseInt((String) combo_sothang.getSelectedItem());
        float coefficient = Float.parseFloat(txt_heso.getText());
        
        TypeService serviceType = new TypeService(1, idServiceFee, monthUnit, ServiceTypeName, LocalDate.now(), coefficient, true);
        
        for (TypeService tsv : TypeServiceDAO.getInstance().getList()) {
            if (serviceType.getService_name().equals(tsv.getService_name())) {
                logError("Không được trùng loại tên");
                return;
            }
        }
        
        for (TypeService tsv : TypeServiceDAO.getInstance().getList()) {
            if (tsv.getMonth_unit() == serviceType.getMonth_unit() && tsv.getPayment_coefficient() == serviceType.getPayment_coefficient()
                    && tsv.getService_fee_id() == serviceType.getService_fee_id()
                    ) {
                logError("Không được trùng thông tin dịch vụ khác");
                return;
            }
        }
        
        String rs = TypeServiceDAO.getInstance().insert(serviceType);
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
        dataGlobal.updateArrServiceType_render();
        fillTable();
    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_tatcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tatcaActionPerformed
        // TODO add your handling code here:
        fillTable();
    }//GEN-LAST:event_btn_tatcaActionPerformed

    private void btn_timkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timkiemActionPerformed
        // TODO add your handling code here:
        int index = 0;
        tableModel.setRowCount(0);
        for (ArrayList<String> dataRow : this.dataGlobal.getArrServiceType_render()) {
            if (Library.Library.StringOnString(txt_timkiem.getText(), dataRow.get(1))) {
                tableModel.addRow(new String[] {
                    dataRow.get(0),
                    dataRow.get(1),
                    dataRow.get(2),
                    dataRow.get(4),
                    dataRow.get(5),
                    dataRow.get(6),
                    dataRow.get(7)
                }); 
            }
        }
        txt_tinnhan.setText("Đang hiển thị các loại dịch vụ lọc theo tên");
        if (index == this.dataGlobal.getArrServiceFee_render().size()) {
            txt_tinnhan.setText("Đang hiển thị tất cả các loại dịch vụ");
        }
    }//GEN-LAST:event_btn_timkiemActionPerformed

    private void btn_tailaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tailaiActionPerformed
        // TODO add your handling code here:
        this.dataGlobal.updateArrServiceType_render();
        fillTable();
    }//GEN-LAST:event_btn_tailaiActionPerformed

    private void btn_chonphidichvuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chonphidichvuActionPerformed
        // TODO add your handling code here:
        this.viewmain.setEnabled(false);
        this.logSelection = new LogSelection() {
            @Override
            public void initContent() {
                this.btn_sapxep.setVisible(false);
                this.label_property.setVisible(false);
                this.tableModel = new DefaultTableModel() {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    };
                };
                // khoi tao cac thanh phan bang o day
                String[] header = new String[] {"ID", "Tên Loại xe", "Giá tiền trên tháng"};
                this.tableModel.setColumnIdentifiers(header);
                this.table.setModel(tableModel);
                this.table.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int row = table.rowAtPoint(e.getPoint());
                        txt_phidichvu.setText((String) table.getValueAt(row, 2));
                        txt_tenloaixe.setText((String) table.getValueAt(row, 1));
                        txt_idPhidichvu.setText((String) table.getValueAt(row, 0));
                        logSelection.setVisible(false);
                        viewmain.setEnabled(true);
                        viewmain.requestFocus();
                    }
                });
                ArrayList<ArrayList<String>> arrDataToSelect = new ArrayList<>();
                
                arrDataToSelect = ServiceFeeDAO.getInstance().getArrServiceFee_render();
                
                for (ArrayList<String> rowSelect : arrDataToSelect) {
                    if (rowSelect.get(5).equals("Còn hạn")) {
                        this.tableModel.addRow(new String[] {rowSelect.get(0), rowSelect.get(2), Library.Library.formatCurrency(Float.parseFloat(rowSelect.get(3)))});
                    }
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
    }//GEN-LAST:event_btn_chonphidichvuActionPerformed
    private String lastTenDichVu = "";
    private void txt_tendichvuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tendichvuKeyReleased
        // TODO add your handling code here:
        if (Library.Library.isValidString(txt_tendichvu.getText())) {
            this.lastTenDichVu = txt_tendichvu.getText();
        }
        else {
            txt_tendichvu.setText(this.lastTenDichVu);
        }
    }//GEN-LAST:event_txt_tendichvuKeyReleased
    private String lastHeso = "";
    private void txt_hesoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_hesoKeyReleased
        if (Library.Library.isNumber(txt_heso.getText())) {
            this.lastHeso = txt_heso.getText();
        }
        else {
            txt_heso.setText(this.lastHeso);
        }
        if(txt_heso.getText().equals("")) {
            return;
        }
        if (Float.parseFloat(txt_heso.getText()) > 100) {
            txt_heso.setText(String.valueOf(Float.parseFloat(txt_heso.getText()) / 10));
        }
    }//GEN-LAST:event_txt_hesoKeyReleased
    private String lasttimkiem = "";
    private void txt_timkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timkiemKeyReleased
        // TODO add your handling code here:
        if (Library.Library.isValidString(txt_timkiem.getText())) {
            this.lasttimkiem = txt_timkiem.getText();
        }
        else {
            txt_timkiem.setText(this.lasttimkiem);
        }
    }//GEN-LAST:event_txt_timkiemKeyReleased
    private void processUpdate() {
        if (txt_tendichvu.getText().length() <= 5) {
            logError("Tên dịch vụ phải dài hơn 5 kí tự");
            return;
        }
        if (txt_idPhidichvu.getText().equals("")) {
            logError("Phải chọn một đơn vị dịch vụ");
            return;
        }
        if (txt_heso.getText().equals("")) {
            logError("Phải điền hệ số thanh toán");
            return;
        }
        int serviceTypeID = Integer.parseInt(txt_idloaidichvu.getText());
        String ServiceTypeName = txt_tendichvu.getText();
        int idServiceFee = Integer.parseInt(txt_idPhidichvu.getText());
        int monthUnit = Integer.parseInt((String) combo_sothang.getSelectedItem());
        float coefficient =  Float.parseFloat(txt_heso.getText());
        boolean state;
        if (combo_trangthai.getSelectedIndex() == 0) {
            state = true;
        }
        else {
            state = false;
        }
        LocalDate decision_date = LocalDate.parse(txt_ngayapdung.getText());
        
        TypeService serviceType = new TypeService(serviceTypeID, idServiceFee, monthUnit, ServiceTypeName, decision_date, coefficient, state);
        
        String rs = TypeServiceDAO.getInstance().update(serviceType);
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
        dataGlobal.updateArrServiceType_render();
        fillTable();
    }
    private void btn_capnhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_capnhatActionPerformed
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
    private javax.swing.JButton btn_capnhat;
    private javax.swing.JButton btn_chonphidichvu;
    private javax.swing.JButton btn_conhan;
    private javax.swing.JButton btn_datlai;
    private javax.swing.JButton btn_hethan;
    private javax.swing.JButton btn_tailai;
    private javax.swing.JButton btn_tatca;
    private javax.swing.JButton btn_them;
    private javax.swing.JButton btn_timkiem;
    private javax.swing.JButton btn_xoa;
    private javax.swing.JComboBox<String> combo_sothang;
    private javax.swing.JComboBox<String> combo_trangthai;
    private javax.swing.JTextField hiddenValueID;
    private javax.swing.JLabel inforDetail;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_id_dangki;
    private javax.swing.JLabel label_id_pt;
    private javax.swing.JLabel label_khachhang;
    private javax.swing.JLabel label_ngaydangki;
    private javax.swing.JLabel label_trangthai;
    private javax.swing.JTable table_loaidichvu;
    private javax.swing.JTextField txt_heso;
    private javax.swing.JTextField txt_idPhidichvu;
    private javax.swing.JTextField txt_idloaidichvu;
    private javax.swing.JTextField txt_ngayapdung;
    private javax.swing.JTextField txt_phidichvu;
    private javax.swing.JTextField txt_tendichvu;
    private javax.swing.JTextField txt_tenloaixe;
    private javax.swing.JTextField txt_timkiem;
    private javax.swing.JTextField txt_tinnhan;
    // End of variables declaration//GEN-END:variables
}
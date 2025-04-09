/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.GUIXE;

import Annotation.LogSelection;
import DAO.LostResidentCardDAO;
import GUI.ViewMain;
import Model.LostResidentCard;
import Model.ParkingSession;
import Model.ResidentCard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class GUI_LostResidentCard extends javax.swing.JPanel {
    private ViewMain viewmain;
    private DefaultTableModel tblModel = new DefaultTableModel(){
        @Override 
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private Map<String, ArrayList<?>> data ;
    private ArrayList<LostResidentCard> lost_resident_cards;
    private ArrayList<String> full_names;
    LogSelection logSelection = new LogSelection();
    private int chooseParkingSession = -1; 
    private int chooseResidentCard = -1;
    private GUI_ResidentCard gui_resident_card;
    
    /**
     * Creates new form GUI_Customer
     */
    public GUI_LostResidentCard(ViewMain viewmain, LogSelection logSelection, GUI_ResidentCard gui_resident_card) {
        this.viewmain = viewmain;
        this.logSelection = logSelection;
        this.gui_resident_card = gui_resident_card;
        
        initComponents();
        initTable();
        resetFields();
        setActive();
        loadData();
        fillTable();
        addDocumentListeners();
    }
    
    public void initTable() { 
        String[] header = new String[] {"Mã Mất Thẻ", "Mã Thẻ",  "Tên Khách Hàng", "Mã Lượt Gửi Xe"};
        tblModel.setColumnIdentifiers(header);
        tblModel.setRowCount(0);
        tbl_lost_resident_card.setModel(tblModel);
        btn_insert.setEnabled(false);
    }
    
    private void loadData() { 
        try {
            this.data = LostResidentCardDAO.getInstance().getAllData();
            this.lost_resident_cards = (ArrayList<LostResidentCard>) data.get("lost_resident_cards");
            this.full_names = (ArrayList<String>) data.get("full_names");
        }
        catch (Exception e) { 
            e.printStackTrace();
        }
    }
    
    public void fillTable() {
        int count = -1;
        String crfull_name = "";
        for (LostResidentCard lres : lost_resident_cards) { 
            count += 1;
            crfull_name = full_names.get(count);
            tblModel.addRow(new String[] {  String.valueOf(lres.getLost_resident_card_id()), String.valueOf(lres.getPk_resident_card()), 
                                            crfull_name, String.valueOf(lres.getParking_session_id())
            });
        }
        
        tblModel.fireTableDataChanged();
    }
    
    private void resetFields() { 
        txt_lost_resident_card.setText("");
        txt_resident_id.setText("");
        txt_parking_session_id.setText("");
        txt_customer_name.setText("");
        
        tbl_lost_resident_card.clearSelection();
    }
    
    private void setActive() { 
        txt_lost_resident_card.setEnabled(false);
        txt_resident_id.setEnabled(false);
        txt_parking_session_id.setEnabled(false);
        txt_customer_name.setEnabled(false);
        
        btn_chon_ma_gui_xe.setEnabled(true);
        btn_chon_ma_the.setEnabled(true);
        btn_delete.setEnabled(false);
    }
    
    private void checkBtnInsert() {
        boolean isFilled =      !txt_parking_session_id.getText().trim().isEmpty() && 
                                !txt_resident_id.getText().trim().isEmpty();

//        System.out.println(isFilled);
        btn_insert.setEnabled(isFilled);
    }
    
    private void addDocumentListeners() {
        DocumentListener docListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkBtnInsert();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkBtnInsert();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkBtnInsert();
            }
        };
        
        txt_parking_session_id.getDocument().addDocumentListener(docListener);
        txt_resident_id.getDocument().addDocumentListener(docListener);
    }
    
    private void showDelete() { 
        txt_lost_resident_card.setEditable(false);
        txt_customer_name.setEnabled(false);
        txt_parking_session_id.setEditable(false);
        txt_resident_id.setEditable(false);
        
        btn_chon_ma_gui_xe.setEnabled(false);
        btn_chon_ma_the.setEnabled(false);
        btn_delete.setEnabled(true);
        btn_insert.setEnabled(false);
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
        cp_lost_resident_card = new javax.swing.JScrollPane();
        tbl_lost_resident_card = new javax.swing.JTable();
        txt_tin_nhan = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        txt_tim_kiem = new javax.swing.JTextField();
        btn_tim_kiem = new javax.swing.JButton();
        btn_tang = new javax.swing.JButton();
        btn_giam = new javax.swing.JButton();
        btn_default = new javax.swing.JButton();
        JLLost_resident_card1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txt_lost_resident_card = new javax.swing.JTextField();
        txt_parking_session_id = new javax.swing.JTextField();
        txt_customer_name = new javax.swing.JTextField();
        txt_resident_id = new javax.swing.JTextField();
        btn_insert = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_reset = new javax.swing.JButton();
        btn_chon_ma_the = new javax.swing.JButton();
        btn_chon_ma_gui_xe = new javax.swing.JButton();
        JL_Title = new javax.swing.JLabel();
        JLLost_resident_card = new javax.swing.JLabel();
        JLResident_card = new javax.swing.JLabel();
        JLParking_session_id = new javax.swing.JLabel();
        JLCustomer_name = new javax.swing.JLabel();

        setBackground(new java.awt.Color(153, 255, 255));

        tbl_lost_resident_card.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_lost_resident_card.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Mã Mất Thẻ", "Mã Thẻ", "Mã Gửi Xe", "Tên Khách Hàng"
            }
        ));
        tbl_lost_resident_card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_lost_resident_cardMouseClicked(evt);
            }
        });
        cp_lost_resident_card.setViewportView(tbl_lost_resident_card);

        txt_tin_nhan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_tin_nhan.setText("Đang hiển thị danh sách tất cả các lượt mất thẻ dịch vụ");
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
        btn_tim_kiem.setText("Tìm");
        btn_tim_kiem.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btn_tim_kiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tim_kiemActionPerformed(evt);
            }
        });

        btn_tang.setText("Tăng");
        btn_tang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tangActionPerformed(evt);
            }
        });

        btn_giam.setText("Giảm");
        btn_giam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_giamActionPerformed(evt);
            }
        });

        btn_default.setText("Mặc Định");
        btn_default.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_defaultActionPerformed(evt);
            }
        });

        JLLost_resident_card1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JLLost_resident_card1.setText("Tìm Kiếm Mã:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Tên Khách Hàng");
        jLabel12.setToolTipText("");
        jLabel12.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel12.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(JLLost_resident_card1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btn_tang, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_giam))
                    .addComponent(txt_tim_kiem))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_tim_kiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_default, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tim_kiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLLost_resident_card1)
                    .addComponent(btn_tim_kiem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_giam)
                        .addComponent(btn_tang)
                        .addComponent(btn_default)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cp_lost_resident_card, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_tin_nhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_tin_nhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cp_lost_resident_card, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setPreferredSize(new java.awt.Dimension(365, 415));
        jPanel2.setRequestFocusEnabled(false);
        jPanel2.setVerifyInputWhenFocusTarget(false);

        txt_lost_resident_card.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_lost_resident_card.setEnabled(false);
        txt_lost_resident_card.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_lost_resident_cardActionPerformed(evt);
            }
        });

        txt_parking_session_id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txt_customer_name.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_customer_name.setEnabled(false);
        txt_customer_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_customer_nameActionPerformed(evt);
            }
        });

        txt_resident_id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_resident_id.setEnabled(false);

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

        btn_delete.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_delete.setText("Xóa");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_reset.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_reset.setText("Reset");
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

        btn_chon_ma_the.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_chon_ma_the.setText("Chọn");
        btn_chon_ma_the.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chon_ma_theActionPerformed(evt);
            }
        });

        btn_chon_ma_gui_xe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_chon_ma_gui_xe.setText("Chọn");
        btn_chon_ma_gui_xe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chon_ma_gui_xeActionPerformed(evt);
            }
        });

        JL_Title.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        JL_Title.setText("Thông Tin Thẻ");
        JL_Title.setToolTipText("");

        JLLost_resident_card.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JLLost_resident_card.setText("Mã Mất Thẻ:");

        JLResident_card.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JLResident_card.setText("Mã Thẻ:");

        JLParking_session_id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JLParking_session_id.setText("Mã Gửi Xe");

        JLCustomer_name.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JLCustomer_name.setText("Tên Khách Hàng;");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(JL_Title, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(JLLost_resident_card, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(JLResident_card, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(JLParking_session_id, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btn_reset))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_parking_session_id, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_chon_ma_gui_xe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_resident_id, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_chon_ma_the, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txt_lost_resident_card, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(JLCustomer_name, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_customer_name, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 2, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_insert, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_delete)
                        .addGap(87, 87, 87)))
                .addGap(21, 21, 21))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_Title)
                    .addComponent(btn_reset))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLLost_resident_card)
                    .addComponent(txt_lost_resident_card, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_resident_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_chon_ma_the))
                    .addComponent(JLResident_card))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLCustomer_name)
                    .addComponent(txt_customer_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLParking_session_id)
                    .addComponent(txt_parking_session_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_chon_ma_gui_xe))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_insert)
                    .addComponent(btn_delete))
                .addGap(100, 100, 100))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_lost_resident_cardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_lost_resident_cardMouseClicked
        // TODO add your handling code here:
        int selectedRow = tbl_lost_resident_card.getSelectedRow();
        // Kiểm tra xem có hàng nào được chọn không
        if (selectedRow != -1) {
            // Lấy dữ liệu từ bảng và gán vào các biến
            int lostResidentCardId = Integer.parseInt(tbl_lost_resident_card.getValueAt(selectedRow, 0).toString());
            int residentCardId = Integer.parseInt(tbl_lost_resident_card.getValueAt(selectedRow, 1).toString());
            String customer = tbl_lost_resident_card.getValueAt(selectedRow, 2).toString();
            int parkingSessionId = Integer.parseInt(tbl_lost_resident_card.getValueAt(selectedRow, 3).toString());
            
            // Hiển thị dữ liệu lên các ô nhập liệu
            txt_lost_resident_card.setText(String.valueOf(lostResidentCardId));
            txt_resident_id.setText(String.valueOf(residentCardId));
            txt_customer_name.setText(customer);
            txt_parking_session_id.setText(String.valueOf(parkingSessionId));
        }
        showDelete();
    }//GEN-LAST:event_tbl_lost_resident_cardMouseClicked

    private void txt_tin_nhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tin_nhanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tin_nhanActionPerformed

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        // TODO add your handling code here:
        resetFields();
        setActive();
    }//GEN-LAST:event_btn_resetActionPerformed

    private void btn_resetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_resetMouseClicked

    }//GEN-LAST:event_btn_resetMouseClicked

    private void btn_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertActionPerformed
        // TODO add your handling code here:
        LostResidentCard lre = new LostResidentCard(chooseResidentCard, chooseParkingSession);
//        System.out.println(lre.getPk_resident_card() + " " + lre.getParking_session_id());
        LostResidentCardDAO.getInstance().insert(lre);
        initTable();
        loadData();
        fillTable();
        resetFields();
        this.gui_resident_card.reloadData();
    }//GEN-LAST:event_btn_insertActionPerformed

    private void btn_insertMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_insertMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_insertMouseClicked

    private void txt_customer_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_customer_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_customer_nameActionPerformed

    private void txt_lost_resident_cardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_lost_resident_cardActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_lost_resident_cardActionPerformed

    private void btn_tangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tangActionPerformed
        // TODO add your handling code here:
        int n = this.lost_resident_cards.size();
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (this.full_names.get(i).compareTo(this.full_names.get(j)) > 0) { 
                    // Hoán đổi vị trí trong lstCustomer
                    LostResidentCard tempCustomer = this.lost_resident_cards.get(i);
                    this.lost_resident_cards.set(i, this.lost_resident_cards.get(j));
                    this.lost_resident_cards.set(j, tempCustomer);

                    // Hoán đổi vị trí tương ứng trong lstBuildingName
                    String tempFullName = this.full_names.get(i);
                    this.full_names.set(i, this.full_names.get(j));
                    this.full_names.set(j, tempFullName);
                }
            }    
        }
        int index = -1;
        initTable();
        String crFullName = "";
        for (LostResidentCard lre : this.lost_resident_cards) { 
            index += 1;
            crFullName = full_names.get(index);
            tblModel.addRow(new String[] {  String.valueOf(lre.getLost_resident_card_id()), String.valueOf(lre.getPk_resident_card()), 
                                            crFullName, String.valueOf(lre.getParking_session_id())
            });
        }
        tblModel.fireTableDataChanged();
        resetFields();
    }//GEN-LAST:event_btn_tangActionPerformed

    private void btn_tim_kiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tim_kiemActionPerformed
        // TODO add your handling code here:
        int index = -1;
        initTable();
        String crFullName = "";
        for (LostResidentCard lre : this.lost_resident_cards) { 
            index += 1;
            if (Library.Library.StringOnString(this.txt_tim_kiem.getText(), String.valueOf(lre.getLost_resident_card_id()))) {
                crFullName = full_names.get(index);
                tblModel.addRow(new String[] {  String.valueOf(lre.getLost_resident_card_id()), String.valueOf(lre.getPk_resident_card()), 
                                                crFullName, String.valueOf(lre.getParking_session_id())
                });
            }
        }
        tblModel.fireTableDataChanged();
        resetFields();
    }//GEN-LAST:event_btn_tim_kiemActionPerformed

    private void txt_tim_kiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tim_kiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tim_kiemActionPerformed

    private void btn_giamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_giamActionPerformed
        // TODO add your handling code here:
        int n = this.lost_resident_cards.size();
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (this.full_names.get(i).compareTo(this.full_names.get(j)) < 0) { 
                    // Hoán đổi vị trí trong lstCustomer
                    LostResidentCard tempCustomer = this.lost_resident_cards.get(i);
                    this.lost_resident_cards.set(i, this.lost_resident_cards.get(j));
                    this.lost_resident_cards.set(j, tempCustomer);

                    // Hoán đổi vị trí tương ứng trong lstBuildingName
                    String tempFullName = this.full_names.get(i);
                    this.full_names.set(i, this.full_names.get(j));
                    this.full_names.set(j, tempFullName);
                }
            }    
        }
        int index = -1;
        initTable();
        String crFullName = "";
        for (LostResidentCard lre : this.lost_resident_cards) { 
            index += 1;
            crFullName = full_names.get(index);
            tblModel.addRow(new String[] {  String.valueOf(lre.getLost_resident_card_id()), String.valueOf(lre.getPk_resident_card()), 
                                            crFullName, String.valueOf(lre.getParking_session_id())
            });
        }
        tblModel.fireTableDataChanged();
        resetFields();
    }//GEN-LAST:event_btn_giamActionPerformed

    private void btn_defaultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_defaultActionPerformed
        // TODO add your handling code here:
        initTable();
        resetFields();
        loadData();
        fillTable();
    }//GEN-LAST:event_btn_defaultActionPerformed

    private void btn_chon_ma_theActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chon_ma_theActionPerformed
        // TODO add your handling code here:
        this.viewmain.setEnabled(false);
        this.logSelection = new LogSelection() {
            @Override
            public void initContent() {
                this.label_property.setText("Mã Định Danh Các Thẻ Cư Dân");
                this.tableModel = new DefaultTableModel() {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    };
                };
                String[] header = new String[] {"Mã Thẻ", "Khách Hàng", "Còn/Mất"};
                this.tableModel.setColumnIdentifiers(header);
                this.table.setModel(tableModel);
                this.table.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int row = table.rowAtPoint(e.getPoint());
                        txt_resident_id.setText((String) table.getValueAt(row, 0));
                        chooseResidentCard = Integer.parseInt((String)table.getValueAt(row, 0));
                        logSelection.setVisible(false);
                        viewmain.setEnabled(true);
                        viewmain.requestFocus();
                    }
                });
                for (ResidentCard re : viewmain.resident_cards) {
                    tableModel.addRow(new String[] {    String.valueOf(re.getPk_resident_card()), String.valueOf(re.getCustomer_id()), 
                                                        String.valueOf(re.isIs_active())
                    });
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
    }//GEN-LAST:event_btn_chon_ma_theActionPerformed

    private void btn_chon_ma_gui_xeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chon_ma_gui_xeActionPerformed
        // TODO add your handling code here:
        this.viewmain.setEnabled(false);
        this.logSelection = new LogSelection() {
            @Override
            public void initContent() {
                this.label_property.setText("Mã Định Danh Các Thẻ Cư Dân");
                this.tableModel = new DefaultTableModel() {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    };
                };
                String[] header = new String[] {"Mã Gửi Xe", "Mã Thẻ",  "Dịch Vụ", "Giờ Vào", "Giờ Ra", "Ca Trực Vào", "Ca Trực Ra", "Xe", "Giá Tiền"};
                this.tableModel.setColumnIdentifiers(header);
                this.table.setModel(tableModel);
                this.table.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int row = table.rowAtPoint(e.getPoint());
                        txt_parking_session_id.setText((String) table.getValueAt(row, 0));
                        chooseParkingSession = Integer.parseInt((String)table.getValueAt(row, 0));
                        logSelection.setVisible(false);
                        viewmain.setEnabled(true);
                        viewmain.requestFocus();
                    }
                });
                for (ParkingSession par: viewmain.parking_sessions) {
                    if (par.isIs_service() && (txt_resident_id.getText().toString().trim().equals("") || par.getCard_id() == Integer.parseInt(txt_resident_id.getText().toString().trim()))) {
                        String dt_start = "null";
                        String dt_end = "null";

                        dt_start =  String.valueOf(par.getCheck_in_time().toLocalDate()) + " " + 
                                    String.valueOf(par.getCheck_in_time().toLocalTime());

                        if (par.getCheck_out_time() != null ){ 
                            dt_end =    String.valueOf(par.getCheck_out_time().toLocalDate()) + " " +
                                    String.valueOf(par.getCheck_out_time().toLocalTime());
                        }
                        tableModel.addRow(new String[] {  String.valueOf(par.getParking_session_id()), String.valueOf(par.getCard_id()), 
                                                        String.valueOf(par.isIs_service()), dt_start, dt_end,
                                                        String.valueOf(par.getCheck_in_shift_id()), String.valueOf(par.getCheck_out_shift_id()),
                                                        String.valueOf(par.getVehicle_id()), String.valueOf(par.getAmount())
                        });
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
    }//GEN-LAST:event_btn_chon_ma_gui_xeActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
        int lostResidentCardId = Integer.parseInt(txt_lost_resident_card.getText().toString().trim());
        LostResidentCardDAO.getInstance().delete(lostResidentCardId);
        initTable();
        loadData();
        fillTable();
        resetFields();
    }//GEN-LAST:event_btn_deleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLCustomer_name;
    private javax.swing.JLabel JLLost_resident_card;
    private javax.swing.JLabel JLLost_resident_card1;
    private javax.swing.JLabel JLParking_session_id;
    private javax.swing.JLabel JLResident_card;
    private javax.swing.JLabel JL_Title;
    private javax.swing.JButton btn_chon_ma_gui_xe;
    private javax.swing.JButton btn_chon_ma_the;
    private javax.swing.JButton btn_default;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_giam;
    private javax.swing.JButton btn_insert;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_tang;
    private javax.swing.JButton btn_tim_kiem;
    private javax.swing.JScrollPane cp_lost_resident_card;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTable tbl_lost_resident_card;
    private javax.swing.JTextField txt_customer_name;
    private javax.swing.JTextField txt_lost_resident_card;
    private javax.swing.JTextField txt_parking_session_id;
    private javax.swing.JTextField txt_resident_id;
    private javax.swing.JTextField txt_tim_kiem;
    private javax.swing.JTextField txt_tin_nhan;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.CATRUC;
import Annotation.LogConfirm;
import Annotation.LogMessage;
import Model.ShiftTypes;
import DAO.ShiftTypesDAO; 
import GUI.ViewMain;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class gui_shift_type extends javax.swing.JPanel {

    /**
     * Creates new form gui_shift_type
     */
    private DefaultTableModel tableModel;
    private List<ShiftTypes> ListShiftTypes = new ArrayList<>();
    private ViewMain viewMain;
    public gui_shift_type(ViewMain viewMain) {
        this.viewMain = viewMain;
        tableModel = new DefaultTableModel(){
            @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
        initComponents();
        jTextField1.setEnabled(false);
//        jButton1.setEnabled(false);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
        loadListShiftTypes();
        initTable();
        fillTable(ListShiftTypes);
    }
    
    public void initTable() {
        String[] header = new String[] {"ID loại ca trực", "Tên loại ca trưc",  "Thời gian bắt đầu", "Thời gian kết thúc"};
        tableModel.setColumnIdentifiers(header);
        jTable1.setModel(tableModel);
        jTable1.setRowHeight(25);
    }
    
    public void loadListShiftTypes(){
        ListShiftTypes = ShiftTypesDAO.getInstance().getAllShiftTypes();
    }
    
    public void fillTable(List<ShiftTypes> ListShiftTypes){
        tableModel.setRowCount(0);
        for (ShiftTypes lct : ListShiftTypes) {
            tableModel.addRow(new String[] {String.valueOf(lct.getShift_type_id()),lct.getShift_type_name(),String.valueOf(lct.getStart_time()), String.valueOf(lct.getEnd_time())});
        }
        tableModel.fireTableDataChanged();
    }
    
    public void insertShiftType(){
        LocalTime start = LocalTime.of(jComboBox1.getSelectedIndex(),jComboBox2.getSelectedIndex());
        LocalTime end = LocalTime.of(jComboBox4.getSelectedIndex(),jComboBox5.getSelectedIndex());
        ShiftTypes a = new ShiftTypes();
        a.setShift_type_name(jTextField2.getText());
        a.setStart_time(start);
        a.setEnd_time(end);
        boolean r = ShiftTypesDAO.getInstance().insert(a);
        if(r){
            viewMain.setEnabled(true);
            viewMain.requestFocus();
            ListShiftTypes = ShiftTypesDAO.getInstance().getAllShiftTypes();
            fillTable(ListShiftTypes);
        }
        else{
            viewMain.setEnabled(false);
            LogMessage message = new LogMessage("Không thể thêm"){
                @Override
                public void action() {
                    viewMain.setEnabled(true);
                    viewMain.requestFocus();
                    this.dispose();
                }
            };
            message.setLocationRelativeTo(null);
            message.setVisible(true);
            message.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }
    
    public void updateShiftType(){
        LocalTime start = LocalTime.of(jComboBox1.getSelectedIndex(),jComboBox2.getSelectedIndex());
        LocalTime end = LocalTime.of(jComboBox4.getSelectedIndex(),jComboBox5.getSelectedIndex());
        ShiftTypes a = new ShiftTypes();
        a.setShift_type_id(Integer.parseInt(jTextField1.getText()));
        a.setShift_type_name(jTextField2.getText());
        a.setStart_time(start);
        a.setEnd_time(end);
        boolean r = ShiftTypesDAO.getInstance().update(a);
        if(r){
            viewMain.setEnabled(true);
            viewMain.requestFocus();
            ListShiftTypes = ShiftTypesDAO.getInstance().getAllShiftTypes();
            fillTable(ListShiftTypes);
        }else{
            LogMessage message = new LogMessage("Không thể cập nhật"){
                @Override
                public void action() {
                    viewMain.setEnabled(true);
                    viewMain.requestFocus();
                    this.dispose();
                }
            };
            message.setLocationRelativeTo(null);
            message.setVisible(true);
            message.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }
    
    public void deleteShiftType(){
        boolean r = ShiftTypesDAO.getInstance().delete(Integer.parseInt(jTextField1.getText()));
        if(r){
            viewMain.setEnabled(true);
            viewMain.requestFocus();
            ListShiftTypes = ShiftTypesDAO.getInstance().getAllShiftTypes();
            fillTable(ListShiftTypes);
        }else{
            LogMessage message = new LogMessage("Không thể xoá"){
                @Override
                public void action() {
                    viewMain.setEnabled(true);
                    viewMain.requestFocus();
                    this.dispose();
                }
            };
            message.setLocationRelativeTo(null);
            message.setVisible(true);
            message.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox5 = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();

        setBackground(new java.awt.Color(204, 255, 255));
        setForeground(new java.awt.Color(51, 255, 255));
        setPreferredSize(new java.awt.Dimension(1120, 485));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Thông tin chi tiết");

        jLabel2.setText("Mã Loại ca trực ");

        jLabel3.setText("Tên loại ca trực");

        jLabel4.setText("Thời gian bắt đầu");

        jLabel5.setText("Thời gian kết thúc");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cập nhật");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Xóa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setText("New");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        String h[] = new String[24];
        for(int i = 0; i<24; i++){
            h[i] = String.valueOf(i);
        }
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(h));

        String ms[] = new String[60];
        for(int i = 0; i<60; i++){
            ms[i] = String.valueOf(i);
        }
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(ms));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(h));

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(ms));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                                .addComponent(jButton3)))
                        .addGap(28, 28, 28))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton5))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(45, 45, 45)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jButton4.setText("Tìm");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel6.setText("Id cần tìm");

        jTextField5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(jTextField2.getText() == null||jTextField2.getText().isEmpty()){
            LogMessage message = new LogMessage("Không được để trống tên ca loại trực"){
                @Override
                public void action() {
                    viewMain.setEnabled(true);
                    viewMain.requestFocus();
                    this.dispose();
                }
            };
            message.setLocationRelativeTo(null);
            message.setVisible(true);
            message.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
        else{
            LogConfirm confirm = new LogConfirm("Xác nhận thêm"){
                @Override
                public void action() {
                    insertShiftType();
                    this.dispose();
                }
                @Override
                public void reject() {
                    viewMain.setEnabled(true);
                    viewMain.requestFocus();
                    this.dispose();
                }

            };
            viewMain.setEnabled(false);
            confirm.setEnabled(true);
            confirm.setVisible(true);
            confirm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            confirm.setLocationRelativeTo(null);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int a = Integer.parseInt(jTextField5.getText().trim());
        ShiftTypes r = ShiftTypesDAO.getInstance().findByID(a);
        jTextField1.setText(String.valueOf(r.getShift_type_id()));
        jTextField2.setText(r.getShift_type_name());
        jComboBox1.setSelectedIndex(r.getStart_time().getHour());
        jComboBox4.setSelectedIndex(r.getEnd_time().getHour());
        jComboBox2.setSelectedIndex(r.getStart_time().getMinute());
        jComboBox5.setSelectedIndex(r.getEnd_time().getMinute());
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        jButton1.setEnabled(false);
        jButton2.setEnabled(true);
        jButton3.setEnabled(true);
        jButton5.setEnabled(true);
        
        int row = jTable1.rowAtPoint(evt.getPoint());
        ShiftTypes arr = ListShiftTypes.get(row);
        jTextField1.setText(String.valueOf(arr.getShift_type_id()));
        jTextField2.setText(arr.getShift_type_name());
        jComboBox1.setSelectedIndex(arr.getStart_time().getHour());
        jComboBox4.setSelectedIndex(arr.getEnd_time().getHour());
        jComboBox2.setSelectedIndex(arr.getStart_time().getMinute());
        jComboBox5.setSelectedIndex(arr.getEnd_time().getMinute());
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        jTextField1.setText(null);
        jTextField2.setText(null);
        jComboBox1.setSelectedIndex(0);
        jComboBox4.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jComboBox5.setSelectedIndex(0);
        jButton1.setEnabled(true);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        LogConfirm confirm = new LogConfirm("Xác nhận xoá"){
                @Override
                public void action() {
                    deleteShiftType();
                    this.dispose();
                }
                @Override
                public void reject() {
                    viewMain.setEnabled(true);
                    viewMain.requestFocus();
                    this.dispose();
                }

            };
            viewMain.setEnabled(false);
            confirm.setEnabled(true);
            confirm.setVisible(true);
            confirm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            confirm.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(jTextField2.getText() == null||jTextField2.getText().isEmpty()){
            viewMain.setEnabled(false);
            LogMessage message = new LogMessage("Không được để trống tên ca loại trực"){
                @Override
                public void action() {
                    viewMain.setEnabled(true);
                    viewMain.requestFocus();
                    this.dispose();
                }
            };
            message.setLocationRelativeTo(null);
            message.setVisible(true);
            message.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
        else{
            LogConfirm confirm = new LogConfirm("Xác nhận cập nhật"){
                @Override
                public void action() {
                    updateShiftType();
                    this.dispose();
                }
                @Override
                public void reject() {
                    viewMain.setEnabled(true);
                    viewMain.requestFocus();
                    this.dispose();
                }

            };
            viewMain.setEnabled(false);
            confirm.setEnabled(true);
            confirm.setVisible(true);
            confirm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            confirm.setLocationRelativeTo(null);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField5MouseClicked
        jTextField5.setText("");
    }//GEN-LAST:event_jTextField5MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}

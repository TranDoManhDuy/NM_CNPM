/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.DICHVU;

import Model.TimeFrame;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author manhh
 */
public class gui_timeframe_detail extends javax.swing.JPanel {
    private LocalDate decision_date;
    private LocalTime TS1;
    private LocalTime TS2;
    private LocalTime TS3;
    private LocalTime TE1;
    private LocalTime TE2;
    private LocalTime TE3;
    private boolean isActive;

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    public void EventClick_btnXoa() {} 
    public void EventClick_btnChinhSua() {}    
    
    public gui_timeframe_detail(LocalDate decision_date, LocalTime TS1, LocalTime TS2, LocalTime TS3, LocalTime TE1, LocalTime TE2, LocalTime TE3, boolean isActive) {
        initComponents();
        this.decision_date = decision_date;
        this.TS1 = TS1;
        this.TS2 = TS2;
        this.TS3 = TS3;
        this.TE1 = TE1;
        this.TE2 = TE2;
        this.TE3 = TE3;
        this.isActive = isActive;
        fillTable();
    }
    
    public LocalTime getTS1() {
        return TS1;
    }

    public void setTS1(LocalTime TS1) {
        this.TS1 = TS1;
    }

    public LocalTime getTS2() {
        return TS2;
    }

    public void setTS2(LocalTime TS2) {
        this.TS2 = TS2;
    }

    public LocalTime getTS3() {
        return TS3;
    }

    public void setTS3(LocalTime TS3) {
        this.TS3 = TS3;
    }

    public LocalTime getTE1() {
        return TE1;
    }

    public void setTE1(LocalTime TE1) {
        this.TE1 = TE1;
    }

    public LocalTime getTE2() {
        return TE2;
    }

    public void setTE2(LocalTime TE2) {
        this.TE2 = TE2;
    }

    public LocalTime getTE3() {
        return TE3;
    }

    public void setTE3(LocalTime TE3) {
        this.TE3 = TE3;
    }
    /**
     * Creates new form gui_timeframe_detail
     */
    private void fillTable() {
        txt_batdau1.setText(String.valueOf(TS1));
        txt_batdau2.setText(String.valueOf(TS2));
        txt_batdau3.setText(String.valueOf(TS3));
        
        txt_ketthuc1.setText(String.valueOf(TE1));
        txt_ketthuc2.setText(String.valueOf(TE2));
        txt_ketthuc3.setText(String.valueOf(TE3));
        
        txt_ngaybanhanh.setText(String.valueOf(decision_date));
        checkbox_conhieuluc.setSelected(isActive);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txt_batdau1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_ketthuc1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_batdau2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_ketthuc2 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_batdau3 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_ketthuc3 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_ngaybanhanh = new javax.swing.JTextField();
        checkbox_conhieuluc = new javax.swing.JCheckBox();
        btn_xoa = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Khung thời gian thứ 1");

        txt_batdau1.setFocusable(false);
        txt_batdau1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_batdau1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Bắt đầu");

        jLabel3.setText("Kết thúc");

        txt_ketthuc1.setFocusable(false);

        jLabel4.setText("Khung thời gian thứ 2");

        jLabel5.setText("Bắt đầu");

        txt_batdau2.setFocusable(false);

        jLabel6.setText("Kết thúc");

        txt_ketthuc2.setFocusable(false);

        jLabel7.setText("Khung thời gian thứ 3");

        jLabel8.setText("Bắt đầu");

        txt_batdau3.setFocusable(false);

        jLabel9.setText("Kết thúc");

        txt_ketthuc3.setFocusable(false);

        jLabel10.setText("Ngày ban hành");

        txt_ngaybanhanh.setFocusable(false);
        txt_ngaybanhanh.setRequestFocusEnabled(false);

        checkbox_conhieuluc.setText("Còn hiệu lực");
        checkbox_conhieuluc.setEnabled(false);
        checkbox_conhieuluc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_conhieulucActionPerformed(evt);
            }
        });

        btn_xoa.setText("XÓA");
        btn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_ngaybanhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(checkbox_conhieuluc)
                .addContainerGap(243, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4)
                        .addComponent(jLabel1)
                        .addComponent(jLabel7)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(25, 25, 25)
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txt_batdau3, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(27, 27, 27)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txt_batdau2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txt_batdau1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGap(44, 44, 44)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txt_ketthuc1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txt_ketthuc2))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txt_ketthuc3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addContainerGap(148, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txt_ngaybanhanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkbox_conhieuluc))
                .addGap(26, 26, 26)
                .addComponent(btn_xoa)
                .addContainerGap(124, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txt_batdau1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_ketthuc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel4)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txt_batdau2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(txt_ketthuc2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel7)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txt_batdau3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(txt_ketthuc3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(25, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_batdau1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_batdau1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_batdau1ActionPerformed

    private void checkbox_conhieulucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_conhieulucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkbox_conhieulucActionPerformed

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
        // TODO add your handling code here:
        this.EventClick_btnXoa();
    }//GEN-LAST:event_btn_xoaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_xoa;
    private javax.swing.JCheckBox checkbox_conhieuluc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txt_batdau1;
    private javax.swing.JTextField txt_batdau2;
    private javax.swing.JTextField txt_batdau3;
    private javax.swing.JTextField txt_ketthuc1;
    private javax.swing.JTextField txt_ketthuc2;
    private javax.swing.JTextField txt_ketthuc3;
    public javax.swing.JTextField txt_ngaybanhanh;
    // End of variables declaration//GEN-END:variables
}

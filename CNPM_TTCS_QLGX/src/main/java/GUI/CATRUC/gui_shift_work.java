/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.CATRUC;

import Annotation.LogConfirm;
import Annotation.LogMessage;
import Annotation.LogSelection;
import DAO.BuildingsDAO;
import DAO.ShiftTypesDAO;
import DAO.ShiftWorksDAO;
import DAO.StaffDAO;
import DAO.TasksDAO;
import DatabaseHelper.OpenConnection;
import GUI.ViewMain;
import Global.DataGlobal;
import Model.Buildings;
import Model.ShiftTypes;
import Model.ShiftWorks;
import Model.Staff;
import Model.Tasks;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import static java.sql.Types.INTEGER;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class gui_shift_work extends javax.swing.JPanel {

    /**
     * Creates new form gui_shift_work
     */
    private DefaultTableModel tableModel;
    private ViewMain viewMain;
    private DataGlobal dataGlobal = new DataGlobal();
    private LogSelection logSelection;
    private LogConfirm confirm;
    private LogMessage message;
    public gui_shift_work(ViewMain viewMain) {
        tableModel = new DefaultTableModel(){
            @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
        this.viewMain = viewMain;
        initComponents();
        initTable();
        fillTable();
        jTextField1.setEnabled(false);
        jTextField2.setEnabled(false);
        jTextField3.setEnabled(false);
        jTextField6.setEnabled(false);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
    }
    
    public void initTable() {
        String[] header = new String[] {"ID ca trực", "Tên loại ca trực", "Tên tòa nhà", "Mã nhân viên" ,"Tên nhân viên", "Tên nhiệm vụ", "Ngày trực"};
        tableModel.setColumnIdentifiers(header);
        jTable1.setModel(tableModel);
        jTable1.setEnabled(false);
    }
    
    public void fillTable(){
        tableModel.setRowCount(0);
        String sql = "SELECT * FROM SHIFTWORKS";
        dataGlobal.updateArrShiftWorks();
        try (
            Connection conn = OpenConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
        ) {
            while (rs.next()) {
                tableModel.addRow(new String[] {String.valueOf(rs.getInt("shift_work_id")),
                                                rs.getString("shift_type_name"),
                                                rs.getString("building_name"),
                                                String.valueOf(rs.getInt("staff_id")),
                                                rs.getString("full_name"),
                                                rs.getString("task_name"),
                                                rs.getString("shift_date")});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  
    
    public void insertShiftWork(){
        LocalDate shiftDate = LocalDate.of(jComboBox6.getSelectedIndex()+2000,jComboBox5.getSelectedIndex() ,jComboBox4.getSelectedIndex()); 
        int stID = Integer.parseInt(jTextField2.getText());
        int bID = Integer.parseInt(jTextField3.getText());
        int sID = Integer.parseInt(jTextField4.getText());
        int tID = Integer.parseInt(jTextField5.getText());
        ShiftWorks a = new ShiftWorks( stID, bID, sID, tID, shiftDate);
        boolean r = ShiftWorksDAO.getInstance().insert(a);
        if(r == false){
            viewMain.setEnabled(false);
            this.message = new LogMessage("Không thể thêm"){
                @Override
                public void action() {
                    viewMain.setEnabled(true);
                    viewMain.requestFocus();
                    this.setVisible(false);
                }
            };
            message.setLocationRelativeTo(null);
            message.setVisible(true);
            message.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }else{
            viewMain.setEnabled(true);
            viewMain.requestFocus();
            fillTable();
        }
    }
    public void updateShiftWork(){
        LocalDate shiftDate = LocalDate.of(jComboBox6.getSelectedIndex()+2000,jComboBox5.getSelectedIndex()+1 ,jComboBox4.getSelectedIndex()+1); 
        int shID = Integer.parseInt(jTextField1.getText());
        int stID = Integer.parseInt(jTextField2.getText());
        int bID = Integer.parseInt(jTextField3.getText());
        int sID = Integer.parseInt(jTextField4.getText());
        int tID = Integer.parseInt(jTextField5.getText());
        ShiftWorks a = new ShiftWorks(shID, stID, bID, sID, tID, shiftDate);
        boolean r = ShiftWorksDAO.getInstance().update(a);
        if(!r){
            viewMain.setEnabled(false);
            this.message = new LogMessage("Lỗi cập nhật"){
                @Override
                public void action() {
                    viewMain.setEnabled(true);
                    viewMain.requestFocus();
                    this.setVisible(false);
                }
            };
            message.setLocationRelativeTo(null);
            message.setVisible(true);
            message.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }else{
            viewMain.setEnabled(true);
            viewMain.requestFocus();
            fillTable();
        }
    }
    public void deleteShiftWork(){
        int t = Integer.parseInt(jTextField1.getText());
        boolean r = ShiftWorksDAO.getInstance().delete(t);
        if(!r){
            viewMain.setEnabled(false);
            this.message = new LogMessage("Không thể xóa"){
                @Override
                public void action() {
                    viewMain.setEnabled(true);
                    viewMain.requestFocus();
                    this.setVisible(false);
                }
            };
            message.setLocationRelativeTo(null);
            message.setVisible(true);
            message.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
        else{
            viewMain.setEnabled(true);
            viewMain.requestFocus();
            fillTable();
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
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox5 = new javax.swing.JComboBox<>();
        jComboBox6 = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jComboBox7 = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox<>();
        jComboBox9 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 255, 255));
        setForeground(new java.awt.Color(102, 255, 255));
        setPreferredSize(new java.awt.Dimension(1120, 485));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Thông tin chi tiết");

        jLabel2.setText("Mã ca trực");

        jLabel5.setText("Mã nhân viên");

        jLabel7.setText("Ngày trực");

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

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
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

        String day[] = new String[32];
        day[0]= "";
        for(int i = 1; i<=31; i++){
            day[i] = String.valueOf(i);
        }
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(day));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        String month[] = new String[13];
        month[0] = "";
        for(int i = 1; i<=12; i++){
            month[i] = String.valueOf(i);
        }
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(month));

        int a = LocalDate.now().getYear();
        String year[] = new String[a-2000 + 1];
        year[0] = "";
        for(int i = 2001; i<= a; i++){
            year[i - 2000] = String.valueOf(i);
        }
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(year));

        jLabel14.setText("Tên nhân viên");

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        dataGlobal.updateArrtasks();
        List<Tasks> arrTask = dataGlobal.getArrayTasks();
        String strT[] = new String[arrTask.size() + 1];
        strT[0] = "";
        for(int i = 1; i<arrTask.size(); i++){
            strT[i] = arrTask.get(i-1).getTask_name();
        }
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(strT));
        jComboBox7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox7MouseClicked(evt);
            }
        });
        jComboBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox7ActionPerformed(evt);
            }
        });

        jLabel15.setText("Tên nhiệm vụ");

        jLabel16.setText("Loại ca trực");

        jLabel17.setText("Tên tòa nhà");

        dataGlobal.updateArrShiftTypes();
        List<ShiftTypes> arrShiftTypes = dataGlobal.getArrayShiftTypes();;
        String st[] = new String[arrShiftTypes.size() +1];
        st[0] = "";
        for(int i = 1; i< arrShiftTypes.size(); i++){
            st[i] = arrShiftTypes.get(i-1).getShift_type_name();
        }
        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(st));
        jComboBox8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox8MouseClicked(evt);
            }
        });
        jComboBox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox8ActionPerformed(evt);
            }
        });

        dataGlobal.updateArrBuildings();
        List<Buildings> listBuildings = dataGlobal.getArrayBuildings();
        String strB[] = new String[listBuildings.size()+1];
        strB[0] = "";
        for(int i =1; i<listBuildings.size(); i++ ){
            strB[i] = listBuildings.get(i-1).getBuilding_name();
        }
        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(strB));
        jComboBox9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox9MouseClicked(evt);
            }
        });
        jComboBox9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox9ActionPerformed(evt);
            }
        });

        jLabel3.setText("Mã loại ca trực");

        jLabel4.setText("Mã tòa nhà");

        jLabel6.setText("Mã nhiệm vụ");

        jButton7.setText("Chọn");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1)
                                    .addComponent(jLabel16))
                                .addGap(6, 6, 6))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(8, 8, 8)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButton2)
                                        .addGap(79, 79, 79)
                                        .addComponent(jButton3))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton7))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox9, 0, 105, Short.MAX_VALUE)
                                    .addComponent(jComboBox8, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(24, 24, 24))
        );

        jLabel8.setText("Mã Loại ca trực");

        jLabel9.setText("Mã Nhân viên");

        jLabel10.setText("Ngày trực");

        jButton4.setText("Lọc");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(day));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(month));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(year));

        jLabel11.setText("Ngày");

        jLabel12.setText("Tháng");

        jLabel13.setText("Năm");

        jButton6.setText("Bỏ lọc");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel11)
                        .addGap(51, 51, 51)
                        .addComponent(jLabel12)
                        .addGap(43, 43, 43)
                        .addComponent(jLabel13)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton4)
                                .addGap(61, 61, 61)
                                .addComponent(jButton6))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6)
                    .addComponent(jButton4))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        LocalDate shiftDate ;
        if(jComboBox6.getSelectedIndex() == 0 || jComboBox5.getSelectedIndex() == 0 || jComboBox4.getSelectedIndex() == 0){
            shiftDate = null;
        }else{
            shiftDate = LocalDate.of(jComboBox6.getSelectedIndex()+2000,jComboBox5.getSelectedIndex() ,jComboBox4.getSelectedIndex());
        }
        if( shiftDate == null || jTextField2.getText().trim().isEmpty()
                            || jTextField3.getText().trim().isEmpty()
                            || jTextField4.getText().trim().isEmpty()
                            || jTextField5.getText().trim().isEmpty()){
            viewMain.setEnabled(false);
            this.message = new LogMessage("Không để trống thông tin"){
                @Override
                public void action() {
                    viewMain.setEnabled(true);
                    viewMain.requestFocus();
                    this.setVisible(false);
                }
            };
            message.setLocationRelativeTo(null);
            message.setVisible(true);
            message.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }else{
            this.confirm = new LogConfirm("Xác nhận cập nhật"){
                @Override
                public void action() {
                    updateShiftWork();
                    this.setVisible(false);
                }
                @Override
                public void reject() {
                    viewMain.setEnabled(true);
                    viewMain.requestFocus();
                    this.setVisible(false);
                }
            };
            confirm.setEnabled(true);
            confirm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            confirm.setLocationRelativeTo(null);
            confirm.setVisible(true);
            viewMain.setEnabled(false);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        jTextField7.setText(null);
        jTextField8.setText(null);
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jComboBox3.setSelectedIndex(0);
        fillTable();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        jButton1.setEnabled(true);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
        jTextField1.setText(null);
        jTextField2.setText(null);
        jTextField3.setText(null);
        jTextField4.setText(null);
        jTextField5.setText(null);
        jTextField6.setText(null);
        jComboBox4.setSelectedIndex(0);
        jComboBox5.setSelectedIndex(0);
        jComboBox6.setSelectedIndex(0);
        jComboBox7.setSelectedIndex(0);
        jComboBox8.setSelectedIndex(0);
        jComboBox9.setSelectedIndex(0);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        LocalDate shiftDate ;
        if(jComboBox6.getSelectedIndex() == 0 || jComboBox5.getSelectedIndex() == 0 || jComboBox4.getSelectedIndex() == 0){
            shiftDate = null;
        }else{
            shiftDate = LocalDate.of(jComboBox6.getSelectedIndex()+2000,jComboBox5.getSelectedIndex() ,jComboBox4.getSelectedIndex());
        }
        if(shiftDate == null || jTextField2.getText() == null||jTextField2.getText().isEmpty()
                             || jTextField3.getText() == null||jTextField3.getText().isEmpty()
                             || jTextField4.getText() == null||jTextField4.getText().isEmpty()
                             || jTextField5.getText() == null||jTextField5.getText().isEmpty()){
            viewMain.setEnabled(false);
            this.message = new LogMessage("Không để trống thông tin"){
                @Override
                public void action() {
                    viewMain.setEnabled(true);
                    viewMain.requestFocus();
                    this.setVisible(false);
                }
            };
            message.setLocationRelativeTo(null);
            message.setVisible(true);
            message.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }else{
            this.confirm = new LogConfirm("Xác nhận thêm"){
                @Override
                public void action() {
                    insertShiftWork();
                    this.setVisible(false);
                }
                @Override
                public void reject() {
                    viewMain.setEnabled(true);
                    viewMain.requestFocus();
                    this.setVisible(false);
                }
            };
            viewMain.setEnabled(false);
            confirm.setEnabled(true);
            confirm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            confirm.setLocationRelativeTo(null);
            confirm.setVisible(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.confirm = new LogConfirm("Xác nhận xóa"){
            @Override
            public void action() {
                deleteShiftWork();
                this.setVisible(false);
                }
            @Override
            public void reject() {
                viewMain.setEnabled(true);
                viewMain.requestFocus();
                this.setVisible(false);
            }
        };
        viewMain.setEnabled(false);
        confirm.setEnabled(true);
        confirm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        confirm.setLocationRelativeTo(null);
        confirm.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        jButton1.setEnabled(false);
        jButton2.setEnabled(true);
        jButton3.setEnabled(true);
        jButton5.setEnabled(true);
        jTextField1.setText((String) jTable1.getValueAt(jTable1.rowAtPoint(evt.getPoint()), 0));
        jComboBox8.setSelectedItem((String) jTable1.getValueAt(jTable1.rowAtPoint(evt.getPoint()), 1));
        jTextField2.setText(String.valueOf( dataGlobal.getArrayShiftTypes().get(jComboBox8.getSelectedIndex() - 1).getShift_type_id()));
        jComboBox9.setSelectedItem((String) jTable1.getValueAt(jTable1.rowAtPoint(evt.getPoint()), 2));
        jTextField3.setText(String.valueOf( dataGlobal.getArrayBuildings().get(jComboBox9.getSelectedIndex() - 1).getBuilding_id()));
        jTextField4.setText((String) jTable1.getValueAt(jTable1.rowAtPoint(evt.getPoint()), 3));
        jTextField6.setText((String) jTable1.getValueAt(jTable1.rowAtPoint(evt.getPoint()), 4));
        jComboBox7.setSelectedItem((String) jTable1.getValueAt(jTable1.rowAtPoint(evt.getPoint()), 5));
        jTextField5.setText(String.valueOf( dataGlobal.getArrayTasks().get(jComboBox7.getSelectedIndex() - 1).getTask_id()));
        int row = jTable1.rowAtPoint(evt.getPoint());
        LocalDate date = dataGlobal.getArrayShiftWorks().get(row).getShift_date();
        jComboBox4.setSelectedIndex(date.getDayOfMonth());
        jComboBox5.setSelectedIndex(date.getMonthValue());
        jComboBox6.setSelectedIndex(date.getYear() - 2000);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jComboBox8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox8MouseClicked
        dataGlobal.updateArrShiftTypes();
        List<ShiftTypes> arrShiftTypes = dataGlobal.getArrayShiftTypes();
        String st[] = new String[arrShiftTypes.size() +1];
        st[0] = "";
        for(int i = 1; i< arrShiftTypes.size(); i++){
            st[i] = arrShiftTypes.get(i -1).getShift_type_name();
        }
        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(st));
    }//GEN-LAST:event_jComboBox8MouseClicked

    private void jComboBox9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox9MouseClicked
        dataGlobal.updateArrBuildings();
        List<Buildings> listBuildings = dataGlobal.getArrayBuildings();
        String st[] = new String[listBuildings.size()+1];
        st[0] = "";
        for(int i =1; i<listBuildings.size(); i++ ){
            st[i] = listBuildings.get(i-1).getBuilding_name();
        }
        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(st));
    }//GEN-LAST:event_jComboBox9MouseClicked

    private void jComboBox7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox7MouseClicked
        dataGlobal.updateArrtasks();
        List<Tasks> arrTask = dataGlobal.getArrayTasks();
        String st[] = new String[arrTask.size() + 1];
        st[0] = "";
        for(int i = 1; i< arrTask.size(); i++){
            st[i] = arrTask.get(i).getTask_name();
        }
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(st));
    }//GEN-LAST:event_jComboBox7MouseClicked

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jComboBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox7ActionPerformed
        if(jComboBox7.getSelectedIndex()!=0)
            jTextField5.setText(String.valueOf(dataGlobal.getArrayTasks().get(jComboBox7.getSelectedIndex()-1).getTask_id()));
    }//GEN-LAST:event_jComboBox7ActionPerformed

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
        int id = Integer.parseInt(jTextField4.getText());
        Staff a = StaffDAO.getInstance().findbyID(id);
        jTextField6.setText(a.getFullName());
    }//GEN-LAST:event_jTextField4KeyReleased

    private void jComboBox8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox8ActionPerformed
        if(jComboBox8.getSelectedIndex()!=0)
            jTextField2.setText(String.valueOf(dataGlobal.getArrayShiftTypes().get(jComboBox8.getSelectedIndex()-1).getShift_type_id()));
    }//GEN-LAST:event_jComboBox8ActionPerformed

    private void jComboBox9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox9ActionPerformed
        if(jComboBox9.getSelectedIndex() != 0)
        jTextField3.setText(String.valueOf(dataGlobal.getArrayBuildings().get(jComboBox9.getSelectedIndex()-1).getBuilding_id()));
    }//GEN-LAST:event_jComboBox9ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        LocalDate shiftDate ;
        if(jComboBox1.getSelectedIndex() == 0 || jComboBox2.getSelectedIndex() == 0 || jComboBox3.getSelectedIndex() == 0){
            shiftDate = null;
        }else{
            shiftDate = LocalDate.of(jComboBox3.getSelectedIndex()+2000,jComboBox2.getSelectedIndex() ,jComboBox1.getSelectedIndex());
        }
        Integer shiftTypeId = null;
        Integer staffId = null;
        String shiftTypeText = jTextField7.getText().trim();
        if (!shiftTypeText.isEmpty()) {
            shiftTypeId = Integer.valueOf(shiftTypeText);
        }

        String staffText = jTextField8.getText().trim();
        if (!staffText.isEmpty()) {
            staffId = Integer.valueOf(staffText);
        }
        tableModel.setRowCount(0);
        String sql = "{CALL FillShiftWorks(?,?,?)}";
        
        try (
            Connection conn = OpenConnection.getConnection();
            CallableStatement stmt = conn.prepareCall(sql);
        ) {
            if (shiftTypeId != null) {
                stmt.setInt(1, shiftTypeId);
            } else {
                stmt.setNull(1, INTEGER);
            }

            if (staffId != null) {
                stmt.setInt(2, staffId);
            } else {
                stmt.setNull(2, INTEGER);
            }

            if (shiftDate != null) {
                stmt.setDate(3, Date.valueOf(shiftDate));
            } else {
                stmt.setNull(3, Types.DATE);
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                tableModel.addRow(new String[] {String.valueOf(rs.getInt("shift_work_id")),
                                                rs.getString("shift_type_name"),
                                                rs.getString("building_name"),
                                                String.valueOf(rs.getInt("staff_id")),
                                                rs.getString("full_name"),
                                                rs.getString("task_name"),
                                                rs.getString("shift_date")});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        viewMain.setEnabled(false);
        this.logSelection = new LogSelection(){
            @Override
            public void initContent() {
                this.label_logname.setText("Danh sách nhân viên");
                this.tableModel = new DefaultTableModel() {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    };
                };
                // khoi tao cac thanh phan bang o day
                String[] header = new String[] {"ID nhân viên", "Tên nhân viên", "Số CCCD", "Số điện thoại"};
                this.tableModel.setColumnIdentifiers(header);
                this.table.setModel(tableModel);
                this.table.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int row = table.rowAtPoint(e.getPoint());
                        jTextField4.setText((String) table.getValueAt(row, 0));
                        jTextField6.setText((String) table.getValueAt(row, 1));
                        logSelection.setVisible(false);
                        viewMain.setEnabled(true);
                        viewMain.requestFocus();
                    }
                });
                
                ArrayList<Staff> arrStaff = StaffDAO.getInstance().getList();
                for (Staff s : arrStaff) {
                    this.tableModel.addRow(new String[] {String.valueOf(s.getStaffId()), s.getFullName(), s.getSsn(), s.getPhoneNumber()});
                }
                this.tableModel.fireTableDataChanged();
                this.btn_loc.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("CLICK");
                    }
                });
                this.btn_boloc.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("CLICK");
                    }
                });
            }
            
            @Override
            public void back() {
                this.setVisible(false);
                viewMain.setEnabled(true);
                viewMain.requestFocus();
            }
        };
        this.logSelection.initContent();
        this.logSelection.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Annotation.LogConfirm;
import Annotation.LogMessage;
import Annotation.LogSelection;
import DAO.BuildingsDAO;
import DAO.CustomerDAO;
import DAO.ParkingSessionDAO;
import DAO.ResidentCardDAO;
import DAO.SessionFeeDAO;
import DAO.TimeFrameDAO;
import DAO.VehicleDAO;
import DAO.VehicleTypeDAO;
import DAO.VisitorParkingCardsDAO;
import GUI.CATRUC.gui_building;
import GUI.CATRUC.gui_shift_type;
import GUI.CATRUC.gui_shift_work;
import GUI.CATRUC.gui_task;
import GUI.DICHVU.gui_payment;
import GUI.DICHVU.gui_registration;
import GUI.DICHVU.gui_serviceType;
import GUI.DICHVU.gui_service_free;
import GUI.DICHVU.gui_session_free;
import GUI.DICHVU.gui_timeframe;
import GUI.DICHVU.gui_vehicle_type;
import GUI.GUIXE.GUI_Customer;
import GUI.GUIXE.GUI_LostResidentCard;
import GUI.GUIXE.GUI_ParkingSession;
import GUI.GUIXE.GUI_ResidentCard;
import GUI.GUIXE.GUI_Vehicle;
import Global.DataGlobal;
import Model.Buildings;
import Model.Customer;
import Model.ParkingSession;
import Model.ResidentCard;
import Model.SessionFee;
import Model.TimeFrame;
import Model.Vehicle;
import Model.VehicleType;
import Model.VisitorParkingCards;
import GUI.NHANSU.gui_account;
import GUI.NHANSU.gui_manager;
import GUI.NHANSU.gui_permission;
import GUI.NHANSU.gui_position;
import GUI.NHANSU.gui_role;
import GUI.NHANSU.gui_staff;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
/**
 *
 * @author manhh
 */
import DAO.BuildingsDAO;
import DAO.RegisatrationDAO;
import DAO.ResidentCardDAO;
import DAO.SessionFeeDAO;
import DAO.TimeFrameDAO;
import DAO.VehicleDAO;
import DAO.VehicleTypeDAO;
import DAO.VisitorParkingCardsDAO;
import DatabaseHelper.OpenConnection;
import GUI.DICHVU.gui_statictical;
import GUI.GUIXE.EntryAndExit;
import GUI.GUIXE.GUI_LostVisitorParkingCard;
import GUI.GUIXE.GUI_VisitorParkingCard;
import GUI.NHANSU.gui_listmanager;
import GUI.NHANSU.gui_profile;
import GUI.NHANSU.gui_registaff;
import Global.Global_variable;
import Model.Account;

import Model.Regisatration;
import Model.SessionFee;
import Model.ShiftTypes;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
public class ViewMain extends javax.swing.JFrame {
    LogConfirm logConfirm = new LogConfirm("nothing");
    LogMessage logMessage = new LogMessage("Nothing");
    LogSelection logSelection = new LogSelection();
    DataGlobal dataglocal = new DataGlobal();
    /**
     * Creates new form ViewMain
     */
    public ViewMain(String position) {
        dataglocal.updateAllData();
        Global.Global_variable.position = position;
        initComponents();
        GUI_DICHVU();
        GUI_GUIXE();
        GUI_CATRUC();
        GUI_NHANSU();
        System.out.println(position);
        runThread();
        checkAvailable();
        txt_checkavailable.setFont(new Font("Segoe UI", Font.BOLD, 12));
    }
    public void runThread() {
        Thread threadClock = new Thread() {
            @Override
            public void run() {
                System.out.println("Thread Start");
                while (true) {
                    txt_timer.setText(String.valueOf(LocalTime.now()).substring(0, 8));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ViewMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        threadClock.start();
    }
    public void checkAvailable() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    String sql = "EXEC checkAvailable";
                    try (
                            Connection conn = OpenConnection.getConnection();
                            Statement stmt = conn.createStatement();
                            ResultSet result = stmt.executeQuery(sql);
                    ) {
                        if (result.next()) {
                            txt_checkavailable.setText(result.getString("statement_current"));
                        }
                    } catch (Exception e) {
                        txt_checkavailable.setText(e.getMessage());
                    }
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ViewMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        thread.start();
    }
    public void addComponent(JPanel father, JPanel child) {
        father.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        father.add(child, gbc);
    }
    public void GUI_DICHVU() {
        if (Global_variable.position.equals("staff")) {
            panelDichVu.setVisible(false);
            return;
        }
        // init component
        gui_registration registration_gui = new gui_registration(this, logConfirm, logMessage, logSelection, dataglocal);
        gui_payment payment_gui = new gui_payment(this, logConfirm, logMessage, logSelection, dataglocal);
        gui_serviceType service_type_gui = new gui_serviceType(this, logConfirm, logMessage, logSelection, dataglocal);
        gui_service_free service_free_gui = new gui_service_free(this, logConfirm, logMessage, logSelection, dataglocal);
        gui_vehicle_type vehicle_type_gui = new gui_vehicle_type(this, logConfirm, logMessage, logSelection, dataglocal);
        gui_timeframe time_frame_gui = new gui_timeframe(this, logConfirm, logMessage, dataglocal);
        gui_session_free session_fee_gui = new gui_session_free(this, logConfirm, logMessage, logSelection, dataglocal);
        // add component
        addComponent(panel_dangki, registration_gui);
        addComponent(panel_thanhtoan, payment_gui);
        addComponent(panel_loaidichvu, service_type_gui);
        addComponent(panel_giadichvuThang, service_free_gui);
        addComponent(panel_khungthoigian, time_frame_gui);
        addComponent(panel_loaiphuongtien, vehicle_type_gui);
        addComponent(panel_gialuot, session_fee_gui);
        panelDichVu.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int selectedIndex = panelDichVu.getSelectedIndex();
                String tabTitle = panelDichVu.getTitleAt(selectedIndex);
                // Hoặc xử lý riêng từng tab
                switch (selectedIndex) {
                    case 0:
                        System.out.println("Cập nhật giao diện Registration");
                        dataglocal.updateArrRegistrationRender();
                        dataglocal.updateArrRegistration();
                        registration_gui.fillTable();
                        break;
                    case 1:
                        System.out.println("Cập nhật giao diện Thanh toán");
                        dataglocal.updateArrPaymentRender();
                        payment_gui.fillTable();
                        break;
                    case 2:
                        System.out.println("Cập nhật giao diện loại dịch vụ");
                        dataglocal.updateArrServiceType_render();
                        service_type_gui.fillTable();
                        break;
                    case 3:
                        System.out.println("Cập nhật giao diện giá dịch vụ tháng");
                        dataglocal.updateArrServiceFee_render();
                        service_free_gui.fillTable();
                        break;
                    case 4: 
                        System.out.println("Cập nhật giao diện giá lượt");
                        dataglocal.updateArrSessionFeeRender();
                        session_fee_gui.fillTable();
                        break;
                    case 5:
                        System.out.println("Cập nhật giao diện loại phương tiện");
                        dataglocal.updateArrVehicleType();
                        vehicle_type_gui.fillTable();
                        break;
                    case 6: 
                        System.out.println("Cập nhật giao diện các các khung thời gian");
                        dataglocal.updateArrTimeFrameToRender();
                        time_frame_gui.fillTable();
                        break;
                    case 7:
                        System.out.println("Cập nhật giao diện thống kê doanh thu dịch vụ");
                        dataglocal.updateArrServiceType_render();
                        dataglocal.updateArrPaymentRender();
                        break;
                }
            }
        });
    }
    
    public void GUI_GUIXE() 
    {   
        GUI_LostVisitorParkingCard gui_LostVisitorParkingCard = new GUI_LostVisitorParkingCard(this);
        GUI_VisitorParkingCard gui_visitorParkingCard = new GUI_VisitorParkingCard(this);
        GUI_Vehicle gui_vehicle = new GUI_Vehicle(dataglocal, this, logSelection, logMessage, logConfirm);
        GUI_ResidentCard gui_resident_card = new GUI_ResidentCard(dataglocal, this, logSelection, logMessage, logConfirm);
        GUI_Customer gui_customer = new GUI_Customer(dataglocal, this, logSelection, gui_resident_card, logMessage, logConfirm);
        GUI_ParkingSession gui_parking_session = new GUI_ParkingSession(dataglocal, this, logSelection, logMessage, gui_vehicle, logConfirm);
        GUI_LostResidentCard gui_lost_resident_card = new GUI_LostResidentCard(dataglocal, this, logSelection, logMessage, gui_resident_card, gui_parking_session, logConfirm);
        EntryAndExit gui_entry_exit = new EntryAndExit(this);
        
        addComponent(panel_mat_the_cd, gui_lost_resident_card);
        addComponent(panel_guixe, gui_parking_session);
        addComponent(panel_the_cu_dan, gui_resident_card);
        addComponent(panel_ptien, gui_vehicle);
        addComponent(panel_mat_the, gui_LostVisitorParkingCard);
        addComponent(panel_the_xe, gui_visitorParkingCard);
        
        if (Global_variable.position.equals("staff")) {
            panel_khachhang.setVisible(false);
            jPanel14.setVisible(false);
            return;
        }
        addComponent(panel_khachhang, gui_customer);
        addComponent(jPanel14, gui_entry_exit);
    }
    
    public void GUI_CATRUC(){
        gui_shift_type st = new gui_shift_type(this);
        gui_building b = new gui_building(this);
        gui_shift_work sw = new gui_shift_work(this);
        gui_task t = new gui_task(this);
        addComponent(panel_loaicatruc, st);
        addComponent(panel_catruc, sw);
        addComponent(panel_toanha, b);
        addComponent(panel_nhiemvu, t);
        
        panelCaTruc.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int selectedIndex = panelCaTruc.getSelectedIndex();
                String tabTitle = panelCaTruc.getTitleAt(selectedIndex);
                // Hoặc xử lý riêng từng tab
                switch (selectedIndex) {
                    case 0:
                        System.out.println("Cập nhật giao diện Loại ca trực");
                        dataglocal.updateArrShiftTypes();
                        st.fillTable(dataglocal.getArrayShiftTypes());
                        break;
                    case 1:
                        System.out.println("Cập nhật giao diện Ca trực");
                        sw.resetPanel();
                        dataglocal.updateArrShiftWorks();
                        sw.fillTable();
                        break;
                    case 2:
                        System.out.println("Cập nhật giao diện nhiệm vụ");
                        dataglocal.updateArrtasks();
                        t.fillTable(dataglocal.getArrayTasks());
                        break;
                    case 3:
                        System.out.println("Cập nhật giao diện Tòa nhà");
                        dataglocal.updateArrBuildings();
                        b.fillTable(dataglocal.getArrayBuildings());
                        break;
                    // thêm các case khác tương ứng tab
                }
            }
        });
    }
    
    public void GUI_NHANSU() {
        
        jTabbedPane3.removeAll(); 

        if (Global_variable.position.equals("staff")) {           
            jTabbedPane3.addTab("Thông tin cá nhân", panel_profile);
        } else {           
            jTabbedPane3.addTab("Nhân viên", panel_nhanvien);
            jTabbedPane3.addTab("Tài khoản", panel_taikhoan);
            jTabbedPane3.addTab("Giám sát", panel_quanli);
            jTabbedPane3.addTab("Quản lý", panel_vitri);
            jTabbedPane3.addTab("Thông tin cá nhân", panel_profile);
            jTabbedPane3.addTab("Đăng ký nhân viên", panel_dangkinv);
        }


        // init component
        gui_staff staff_gui = new gui_staff(this);
        gui_account account_gui = new gui_account(this);
        gui_role role_gui = new gui_role(this);
        gui_permission permission_gui = new gui_permission(this);
        gui_manager manager_gui = new gui_manager(dataglocal, logSelection, this);
//        gui_listmanager listmanager_gui = new gui_listmanager(this);
        gui_profile profile = new gui_profile();
        gui_listmanager listmanager_gui = new gui_listmanager(this);
        gui_registaff registaff_gui = new gui_registaff(this);
//        gui_profile profile = new gui_profile();
        // add component
        addComponent(panel_nhanvien, staff_gui);
        addComponent(panel_taikhoan, account_gui);
//        addComponent(panel_vaitro, role_gui);
//        addComponent(panel_quyen, permission_gui);
        addComponent(panel_quanli, manager_gui);
//        addComponent(panel_vitri, listmanager_gui);
//        addComponent(panel_profile, profile);
        addComponent(panel_vitri, listmanager_gui);
        addComponent(panel_profile, profile);
        addComponent(panel_dangkinv,registaff_gui);
        
        jTabbedPane3.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            int selectedIndex = jTabbedPane3.getSelectedIndex();
            switch (selectedIndex) {
                case 0: // Tab "Nhân viên"
                    System.out.println("Cập nhật giao diện Nhân viên");
                    staff_gui.fillTable();
                    break;
                case 1: // Tab "Tài khoản"
                    System.out.println("Cập nhật giao diện Tài khoản");
                    account_gui.fillTable();
                    break;
                // Có thể thêm các case khác nếu cần
            }
        }
    });
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        NhanSu = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        panel_nhanvien = new javax.swing.JPanel();
        panel_taikhoan = new javax.swing.JPanel();
        panel_quanli = new javax.swing.JPanel();
        panel_vitri = new javax.swing.JPanel();
        panel_profile = new javax.swing.JPanel();
        panel_dangkinv = new javax.swing.JPanel();
        CaTruc = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        panelCaTruc = new javax.swing.JTabbedPane();
        panel_loaicatruc = new javax.swing.JPanel();
        panel_catruc = new javax.swing.JPanel();
        panel_nhiemvu = new javax.swing.JPanel();
        panel_toanha = new javax.swing.JPanel();
        GuiXe = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        panel_guixe = new javax.swing.JPanel();
        panel_the_cu_dan = new javax.swing.JPanel();
        panel_mat_the_cd = new javax.swing.JPanel();
        panel_ptien = new javax.swing.JPanel();
        panel_khachhang = new javax.swing.JPanel();
        panel_mat_the = new javax.swing.JPanel();
        panel_the_xe = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        DichVu = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        panelDichVu = new javax.swing.JTabbedPane();
        panel_dangki = new javax.swing.JPanel();
        panel_thanhtoan = new javax.swing.JPanel();
        panel_loaidichvu = new javax.swing.JPanel();
        panel_giadichvuThang = new javax.swing.JPanel();
        panel_gialuot = new javax.swing.JPanel();
        panel_loaiphuongtien = new javax.swing.JPanel();
        panel_khungthoigian = new javax.swing.JPanel();
        txt_timer = new javax.swing.JLabel();
        txt_checkavailable = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 204));
        jLabel1.setText("PHẦN MỀM QUẢN LÝ GỬI XE Ở CHUNG CƯ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 204, 204));
        jLabel2.setText("PTITHCM - NHẬP MÔN CÔNG NGHỆ PHẦN MỀM");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 204, 204));
        jLabel3.setText("D22CQCN02-N. ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(127, 127, 127))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(231, 231, 231))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel_nhanvienLayout = new javax.swing.GroupLayout(panel_nhanvien);
        panel_nhanvien.setLayout(panel_nhanvienLayout);
        panel_nhanvienLayout.setHorizontalGroup(
            panel_nhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel_nhanvienLayout.setVerticalGroup(
            panel_nhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("Nhân viên", panel_nhanvien);

        javax.swing.GroupLayout panel_taikhoanLayout = new javax.swing.GroupLayout(panel_taikhoan);
        panel_taikhoan.setLayout(panel_taikhoanLayout);
        panel_taikhoanLayout.setHorizontalGroup(
            panel_taikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel_taikhoanLayout.setVerticalGroup(
            panel_taikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("Tài khoản", panel_taikhoan);

        javax.swing.GroupLayout panel_quanliLayout = new javax.swing.GroupLayout(panel_quanli);
        panel_quanli.setLayout(panel_quanliLayout);
        panel_quanliLayout.setHorizontalGroup(
            panel_quanliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel_quanliLayout.setVerticalGroup(
            panel_quanliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("Giám sát", panel_quanli);

        javax.swing.GroupLayout panel_vitriLayout = new javax.swing.GroupLayout(panel_vitri);
        panel_vitri.setLayout(panel_vitriLayout);
        panel_vitriLayout.setHorizontalGroup(
            panel_vitriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel_vitriLayout.setVerticalGroup(
            panel_vitriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("Quản lý", panel_vitri);

        javax.swing.GroupLayout panel_profileLayout = new javax.swing.GroupLayout(panel_profile);
        panel_profile.setLayout(panel_profileLayout);
        panel_profileLayout.setHorizontalGroup(
            panel_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel_profileLayout.setVerticalGroup(
            panel_profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("Thông tin cá nhân", panel_profile);

        javax.swing.GroupLayout panel_dangkinvLayout = new javax.swing.GroupLayout(panel_dangkinv);
        panel_dangkinv.setLayout(panel_dangkinvLayout);
        panel_dangkinvLayout.setHorizontalGroup(
            panel_dangkinvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1203, Short.MAX_VALUE)
        );
        panel_dangkinvLayout.setVerticalGroup(
            panel_dangkinvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("Đăng ký nhân viên", panel_dangkinv);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );

        javax.swing.GroupLayout NhanSuLayout = new javax.swing.GroupLayout(NhanSu);
        NhanSu.setLayout(NhanSuLayout);
        NhanSuLayout.setHorizontalGroup(
            NhanSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        NhanSuLayout.setVerticalGroup(
            NhanSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Nhân Sự", NhanSu);

        javax.swing.GroupLayout panel_loaicatrucLayout = new javax.swing.GroupLayout(panel_loaicatruc);
        panel_loaicatruc.setLayout(panel_loaicatrucLayout);
        panel_loaicatrucLayout.setHorizontalGroup(
            panel_loaicatrucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel_loaicatrucLayout.setVerticalGroup(
            panel_loaicatrucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelCaTruc.addTab("Loại ca trực", panel_loaicatruc);

        javax.swing.GroupLayout panel_catrucLayout = new javax.swing.GroupLayout(panel_catruc);
        panel_catruc.setLayout(panel_catrucLayout);
        panel_catrucLayout.setHorizontalGroup(
            panel_catrucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel_catrucLayout.setVerticalGroup(
            panel_catrucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelCaTruc.addTab("Ca trực", panel_catruc);

        javax.swing.GroupLayout panel_nhiemvuLayout = new javax.swing.GroupLayout(panel_nhiemvu);
        panel_nhiemvu.setLayout(panel_nhiemvuLayout);
        panel_nhiemvuLayout.setHorizontalGroup(
            panel_nhiemvuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel_nhiemvuLayout.setVerticalGroup(
            panel_nhiemvuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelCaTruc.addTab("Nhiệm vụ", panel_nhiemvu);

        javax.swing.GroupLayout panel_toanhaLayout = new javax.swing.GroupLayout(panel_toanha);
        panel_toanha.setLayout(panel_toanhaLayout);
        panel_toanhaLayout.setHorizontalGroup(
            panel_toanhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel_toanhaLayout.setVerticalGroup(
            panel_toanhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelCaTruc.addTab("Tòa nhà", panel_toanha);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCaTruc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1203, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCaTruc, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout CaTrucLayout = new javax.swing.GroupLayout(CaTruc);
        CaTruc.setLayout(CaTrucLayout);
        CaTrucLayout.setHorizontalGroup(
            CaTrucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        CaTrucLayout.setVerticalGroup(
            CaTrucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Ca Trực", CaTruc);

        javax.swing.GroupLayout panel_guixeLayout = new javax.swing.GroupLayout(panel_guixe);
        panel_guixe.setLayout(panel_guixeLayout);
        panel_guixeLayout.setHorizontalGroup(
            panel_guixeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1203, Short.MAX_VALUE)
        );
        panel_guixeLayout.setVerticalGroup(
            panel_guixeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Gửi xe", panel_guixe);

        javax.swing.GroupLayout panel_the_cu_danLayout = new javax.swing.GroupLayout(panel_the_cu_dan);
        panel_the_cu_dan.setLayout(panel_the_cu_danLayout);
        panel_the_cu_danLayout.setHorizontalGroup(
            panel_the_cu_danLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1203, Short.MAX_VALUE)
        );
        panel_the_cu_danLayout.setVerticalGroup(
            panel_the_cu_danLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Thẻ cư dân", panel_the_cu_dan);

        javax.swing.GroupLayout panel_mat_the_cdLayout = new javax.swing.GroupLayout(panel_mat_the_cd);
        panel_mat_the_cd.setLayout(panel_mat_the_cdLayout);
        panel_mat_the_cdLayout.setHorizontalGroup(
            panel_mat_the_cdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1203, Short.MAX_VALUE)
        );
        panel_mat_the_cdLayout.setVerticalGroup(
            panel_mat_the_cdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Mất thẻ cư dân", panel_mat_the_cd);

        javax.swing.GroupLayout panel_ptienLayout = new javax.swing.GroupLayout(panel_ptien);
        panel_ptien.setLayout(panel_ptienLayout);
        panel_ptienLayout.setHorizontalGroup(
            panel_ptienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1203, Short.MAX_VALUE)
        );
        panel_ptienLayout.setVerticalGroup(
            panel_ptienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Phương tiện", panel_ptien);

        javax.swing.GroupLayout panel_khachhangLayout = new javax.swing.GroupLayout(panel_khachhang);
        panel_khachhang.setLayout(panel_khachhangLayout);
        panel_khachhangLayout.setHorizontalGroup(
            panel_khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1203, Short.MAX_VALUE)
        );
        panel_khachhangLayout.setVerticalGroup(
            panel_khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Khách hàng", panel_khachhang);

        javax.swing.GroupLayout panel_mat_theLayout = new javax.swing.GroupLayout(panel_mat_the);
        panel_mat_the.setLayout(panel_mat_theLayout);
        panel_mat_theLayout.setHorizontalGroup(
            panel_mat_theLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1203, Short.MAX_VALUE)
        );
        panel_mat_theLayout.setVerticalGroup(
            panel_mat_theLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Mất thẻ xe", panel_mat_the);

        javax.swing.GroupLayout panel_the_xeLayout = new javax.swing.GroupLayout(panel_the_xe);
        panel_the_xe.setLayout(panel_the_xeLayout);
        panel_the_xeLayout.setHorizontalGroup(
            panel_the_xeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1203, Short.MAX_VALUE)
        );
        panel_the_xeLayout.setVerticalGroup(
            panel_the_xeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Thẻ xe", panel_the_xe);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1203, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Thống kê", jPanel14);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        javax.swing.GroupLayout GuiXeLayout = new javax.swing.GroupLayout(GuiXe);
        GuiXe.setLayout(GuiXeLayout);
        GuiXeLayout.setHorizontalGroup(
            GuiXeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        GuiXeLayout.setVerticalGroup(
            GuiXeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Gửi Xe", GuiXe);

        javax.swing.GroupLayout panel_dangkiLayout = new javax.swing.GroupLayout(panel_dangki);
        panel_dangki.setLayout(panel_dangkiLayout);
        panel_dangkiLayout.setHorizontalGroup(
            panel_dangkiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1203, Short.MAX_VALUE)
        );
        panel_dangkiLayout.setVerticalGroup(
            panel_dangkiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
        );

        panelDichVu.addTab("Đăng kí", panel_dangki);

        javax.swing.GroupLayout panel_thanhtoanLayout = new javax.swing.GroupLayout(panel_thanhtoan);
        panel_thanhtoan.setLayout(panel_thanhtoanLayout);
        panel_thanhtoanLayout.setHorizontalGroup(
            panel_thanhtoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1203, Short.MAX_VALUE)
        );
        panel_thanhtoanLayout.setVerticalGroup(
            panel_thanhtoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
        );

        panelDichVu.addTab("Thanh toán", panel_thanhtoan);

        javax.swing.GroupLayout panel_loaidichvuLayout = new javax.swing.GroupLayout(panel_loaidichvu);
        panel_loaidichvu.setLayout(panel_loaidichvuLayout);
        panel_loaidichvuLayout.setHorizontalGroup(
            panel_loaidichvuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1203, Short.MAX_VALUE)
        );
        panel_loaidichvuLayout.setVerticalGroup(
            panel_loaidichvuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
        );

        panelDichVu.addTab("Loại dịch vụ", panel_loaidichvu);

        javax.swing.GroupLayout panel_giadichvuThangLayout = new javax.swing.GroupLayout(panel_giadichvuThang);
        panel_giadichvuThang.setLayout(panel_giadichvuThangLayout);
        panel_giadichvuThangLayout.setHorizontalGroup(
            panel_giadichvuThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1203, Short.MAX_VALUE)
        );
        panel_giadichvuThangLayout.setVerticalGroup(
            panel_giadichvuThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
        );

        panelDichVu.addTab("Giá dịch vụ theo tháng", panel_giadichvuThang);

        javax.swing.GroupLayout panel_gialuotLayout = new javax.swing.GroupLayout(panel_gialuot);
        panel_gialuot.setLayout(panel_gialuotLayout);
        panel_gialuotLayout.setHorizontalGroup(
            panel_gialuotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1203, Short.MAX_VALUE)
        );
        panel_gialuotLayout.setVerticalGroup(
            panel_gialuotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
        );

        panelDichVu.addTab("Giá lượt", panel_gialuot);

        javax.swing.GroupLayout panel_loaiphuongtienLayout = new javax.swing.GroupLayout(panel_loaiphuongtien);
        panel_loaiphuongtien.setLayout(panel_loaiphuongtienLayout);
        panel_loaiphuongtienLayout.setHorizontalGroup(
            panel_loaiphuongtienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1203, Short.MAX_VALUE)
        );
        panel_loaiphuongtienLayout.setVerticalGroup(
            panel_loaiphuongtienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
        );

        panelDichVu.addTab("Các loại phương tiện", panel_loaiphuongtien);

        javax.swing.GroupLayout panel_khungthoigianLayout = new javax.swing.GroupLayout(panel_khungthoigian);
        panel_khungthoigian.setLayout(panel_khungthoigianLayout);
        panel_khungthoigianLayout.setHorizontalGroup(
            panel_khungthoigianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1203, Short.MAX_VALUE)
        );
        panel_khungthoigianLayout.setVerticalGroup(
            panel_khungthoigianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
        );

        panelDichVu.addTab("Các khung thời gian", panel_khungthoigian);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelDichVu, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelDichVu)
        );

        javax.swing.GroupLayout DichVuLayout = new javax.swing.GroupLayout(DichVu);
        DichVu.setLayout(DichVuLayout);
        DichVuLayout.setHorizontalGroup(
            DichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DichVuLayout.setVerticalGroup(
            DichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Dịch Vụ", DichVu);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        txt_timer.setForeground(new java.awt.Color(255, 51, 0));
        txt_timer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_timer.setText("time");

        txt_checkavailable.setForeground(new java.awt.Color(255, 0, 0));
        txt_checkavailable.setText("IS AVAILABLE");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_timer, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(txt_checkavailable, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_timer)
                    .addComponent(txt_checkavailable))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CaTruc;
    private javax.swing.JPanel DichVu;
    private javax.swing.JPanel GuiXe;
    private javax.swing.JPanel NhanSu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane panelCaTruc;
    private javax.swing.JTabbedPane panelDichVu;
    private javax.swing.JPanel panel_catruc;
    private javax.swing.JPanel panel_dangki;
    private javax.swing.JPanel panel_dangkinv;
    private javax.swing.JPanel panel_giadichvuThang;
    private javax.swing.JPanel panel_gialuot;
    private javax.swing.JPanel panel_guixe;
    private javax.swing.JPanel panel_khachhang;
    private javax.swing.JPanel panel_khungthoigian;
    private javax.swing.JPanel panel_loaicatruc;
    private javax.swing.JPanel panel_loaidichvu;
    private javax.swing.JPanel panel_loaiphuongtien;
    private javax.swing.JPanel panel_mat_the;
    private javax.swing.JPanel panel_mat_the_cd;
    private javax.swing.JPanel panel_nhanvien;
    private javax.swing.JPanel panel_nhiemvu;
    private javax.swing.JPanel panel_profile;
    private javax.swing.JPanel panel_ptien;
    private javax.swing.JPanel panel_quanli;
    private javax.swing.JPanel panel_taikhoan;
    private javax.swing.JPanel panel_thanhtoan;
    private javax.swing.JPanel panel_the_cu_dan;
    private javax.swing.JPanel panel_the_xe;
    private javax.swing.JPanel panel_toanha;
    private javax.swing.JPanel panel_vitri;
    private javax.swing.JLabel txt_checkavailable;
    private javax.swing.JLabel txt_timer;
    // End of variables declaration//GEN-END:variables
}

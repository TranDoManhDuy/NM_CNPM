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
import GUI.DICHVU.gui_statictical;
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
import DAO.ResidentCardDAO;
import DAO.SessionFeeDAO;
import DAO.TimeFrameDAO;
import DAO.VehicleDAO;
import DAO.VehicleTypeDAO;
import DAO.VisitorParkingCardsDAO;

import Model.SessionFee;
public class ViewMain extends javax.swing.JFrame {
    LogConfirm logComfirm = new LogConfirm("nothing");
    LogMessage logMessage = new LogMessage("Nothing");
    LogSelection logSelection = new LogSelection();
    DataGlobal dataglocal = new DataGlobal();public List<Buildings> buildings = new ArrayList<>();
    public ArrayList<VehicleType> vehicle_types = new ArrayList<>();
    public List<VisitorParkingCards> visitor_parking_cards = new ArrayList<>();
    public ArrayList<ResidentCard> resident_cards = new ArrayList<>();
    public ArrayList<Vehicle> vehicles = new ArrayList<>();
    public ArrayList<TimeFrame> listTimeFrames = new ArrayList<>();
    public ArrayList<SessionFee> listSessionFees = new ArrayList<>();
    public ArrayList<Customer> lstCustomer = new ArrayList<>();
    public ArrayList<ParkingSession> parking_sessions;
    
    /**
     * Creates new form ViewMain
     */
    public ViewMain() {
        dataglocal.updateAllData();
        this.buildings = BuildingsDAO.getInstance().getAllBuildings();
        this.vehicles = VehicleDAO.getInstance().getList();
        this.vehicle_types = VehicleTypeDAO.getInstance().getList();
        this.visitor_parking_cards = VisitorParkingCardsDAO.getInstance().getAll();
        this.resident_cards = ResidentCardDAO.getInstance().getList();
        this.listTimeFrames = TimeFrameDAO.getInstance().getList();
        this.listSessionFees = SessionFeeDAO.getInstance().getList();
        this.lstCustomer = CustomerDAO.getInstance().getList();
        this.parking_sessions = ParkingSessionDAO.getInstance().getList();
        initComponents();
        GUI_DICHVU();
        GUI_GUIXE();
        GUI_CATRUC();
//        GUI_DICHVU();
//        GUI_GUIXE();
//        GUI_CATRUC();
        GUI_NHANSU();
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
        // init component
        gui_registration registration_gui = new gui_registration(this, logComfirm, logMessage, logSelection);
        gui_payment payment_gui = new gui_payment(this, logComfirm, logMessage, logSelection);
        gui_serviceType service_type_gui = new gui_serviceType(this, logComfirm, logMessage, logSelection, dataglocal);
        gui_service_free service_free_gui = new gui_service_free(this, logComfirm, logMessage, logSelection, dataglocal);
        gui_vehicle_type vehicle_type_gui = new gui_vehicle_type(this, logComfirm, logMessage, logSelection);
        gui_timeframe time_frame_gui = new gui_timeframe(this, logComfirm, logMessage);
        gui_session_free session_fee_gui = new gui_session_free(this, logComfirm, logMessage, logSelection);
        gui_statictical statictical_gui = new gui_statictical();
        // add component
        if (Global.Global_variable.role_name.equals("staff")) {
            addComponent(panel_dangki, registration_gui);
            addComponent(panel_thanhtoan, payment_gui);
            addComponent(panel_loaidichvu, service_type_gui);
            addComponent(panel_giadichvuThang, service_free_gui);
            addComponent(panel_khungthoigian, time_frame_gui);
            addComponent(panel_loaiphuongtien, vehicle_type_gui);
            addComponent(panel_gialuot, session_fee_gui);
            addComponent(panel_thongkedoanhthu, statictical_gui);
        }
    }
    
    public void GUI_GUIXE() 
    {
        GUI_Customer gui_customer = new GUI_Customer(this, logSelection);
        GUI_ResidentCard gui_resident_card = new GUI_ResidentCard(this, logSelection);
        GUI_LostResidentCard gui_lost_resident_card = new GUI_LostResidentCard(this, logSelection, gui_resident_card);
        GUI_ParkingSession gui_parking_session = new GUI_ParkingSession(this, logSelection);
        GUI_Vehicle gui_vehicle = new GUI_Vehicle(this, logSelection);
        addComponent(panel_khachhang, gui_customer);
        addComponent(panel_mat_the_cd, gui_lost_resident_card);
        addComponent(panel_guixe, gui_parking_session);
        addComponent(panel_the_cu_dan, gui_resident_card);
        addComponent(panel_ptien, gui_vehicle);
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
    }
    
    public void GUI_NHANSU() {
        // init component
        gui_staff staff_gui = new gui_staff(this);
        gui_account account_gui = new gui_account(this);
        gui_role role_gui = new gui_role(this);
        gui_permission permission_gui = new gui_permission(this);
        gui_manager manager_gui = new gui_manager(this);
        gui_position position_gui = new gui_position(this);
        
        // add component
        addComponent(panel_nhanvien, staff_gui);
        addComponent(panel_taikhoan, account_gui);
        addComponent(panel_vaitro, role_gui);
        addComponent(panel_quyen, permission_gui);
        addComponent(panel_quanli, manager_gui);
        addComponent(panel_vitri, position_gui);
       
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
        panel_vaitro = new javax.swing.JPanel();
        panel_quyen = new javax.swing.JPanel();
        panel_quanli = new javax.swing.JPanel();
        panel_vitri = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        CaTruc = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        panel_loaicatruc = new javax.swing.JPanel();
        panel_catruc = new javax.swing.JPanel();
        panel_nhiemvu = new javax.swing.JPanel();
        panel_toanha = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        GuiXe = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        panel_guixe = new javax.swing.JPanel();
        panel_the_cu_dan = new javax.swing.JPanel();
        panel_mat_the_cd = new javax.swing.JPanel();
        panel_ptien = new javax.swing.JPanel();
        panel_khachhang = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
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
        panel_thongkedoanhthu = new javax.swing.JPanel();
        ThongKe = new javax.swing.JPanel();

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

        javax.swing.GroupLayout panel_vaitroLayout = new javax.swing.GroupLayout(panel_vaitro);
        panel_vaitro.setLayout(panel_vaitroLayout);
        panel_vaitroLayout.setHorizontalGroup(
            panel_vaitroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel_vaitroLayout.setVerticalGroup(
            panel_vaitroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("Vai trò", panel_vaitro);

        javax.swing.GroupLayout panel_quyenLayout = new javax.swing.GroupLayout(panel_quyen);
        panel_quyen.setLayout(panel_quyenLayout);
        panel_quyenLayout.setHorizontalGroup(
            panel_quyenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel_quyenLayout.setVerticalGroup(
            panel_quyenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("Quyền", panel_quyen);

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

        jTabbedPane3.addTab("Quản lí", panel_quanli);

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

        jTabbedPane3.addTab("tab6", panel_vitri);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("tab7", jPanel18);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("tab8", jPanel19);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("tab9", jPanel20);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1203, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
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

        jTabbedPane4.addTab("Loại ca trực", panel_loaicatruc);

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

        jTabbedPane4.addTab("Ca trực", panel_catruc);

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

        jTabbedPane4.addTab("Nhiệm vụ", panel_nhiemvu);

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

        jTabbedPane4.addTab("Tòa nhà", panel_toanha);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane4.addTab("tab5", jPanel17);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane4.addTab("tab6", jPanel21);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1203, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
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

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1203, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("tab6", jPanel11);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1203, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("tab7", jPanel12);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1203, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("tab8", jPanel13);

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

        jTabbedPane2.addTab("tab9", jPanel14);

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

        javax.swing.GroupLayout panel_thongkedoanhthuLayout = new javax.swing.GroupLayout(panel_thongkedoanhthu);
        panel_thongkedoanhthu.setLayout(panel_thongkedoanhthuLayout);
        panel_thongkedoanhthuLayout.setHorizontalGroup(
            panel_thongkedoanhthuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1203, Short.MAX_VALUE)
        );
        panel_thongkedoanhthuLayout.setVerticalGroup(
            panel_thongkedoanhthuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
        );

        panelDichVu.addTab("Thống kê doanh thu", panel_thongkedoanhthu);

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

        javax.swing.GroupLayout ThongKeLayout = new javax.swing.GroupLayout(ThongKe);
        ThongKe.setLayout(ThongKeLayout);
        ThongKeLayout.setHorizontalGroup(
            ThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1203, Short.MAX_VALUE)
        );
        ThongKeLayout.setVerticalGroup(
            ThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 488, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Thống Kê", ThongKe);

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(47, 47, 47)
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
    private javax.swing.JPanel ThongKe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane panelDichVu;
    private javax.swing.JPanel panel_catruc;
    private javax.swing.JPanel panel_dangki;
    private javax.swing.JPanel panel_giadichvuThang;
    private javax.swing.JPanel panel_gialuot;
    private javax.swing.JPanel panel_guixe;
    private javax.swing.JPanel panel_khachhang;
    private javax.swing.JPanel panel_khungthoigian;
    private javax.swing.JPanel panel_loaicatruc;
    private javax.swing.JPanel panel_loaidichvu;
    private javax.swing.JPanel panel_loaiphuongtien;
    private javax.swing.JPanel panel_mat_the_cd;
    private javax.swing.JPanel panel_nhanvien;
    private javax.swing.JPanel panel_nhiemvu;
    private javax.swing.JPanel panel_ptien;
    private javax.swing.JPanel panel_quanli;
    private javax.swing.JPanel panel_quyen;
    private javax.swing.JPanel panel_taikhoan;
    private javax.swing.JPanel panel_thanhtoan;
    private javax.swing.JPanel panel_the_cu_dan;
    private javax.swing.JPanel panel_thongkedoanhthu;
    private javax.swing.JPanel panel_toanha;
    private javax.swing.JPanel panel_vaitro;
    private javax.swing.JPanel panel_vitri;
    // End of variables declaration//GEN-END:variables
}

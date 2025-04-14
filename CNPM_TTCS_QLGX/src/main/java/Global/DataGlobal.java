/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Global;
import DAO.BuildingsDAO;
import DAO.CustomerDAO;
import DAO.PaymentDAO;
import DAO.LostVisitorParkingCardsDAO;
import DAO.ParkingSessionDAO;
import DAO.RegisatrationDAO;
import DAO.ResidentCardDAO;
import DAO.ServiceFeeDAO;
import DAO.SessionFeeDAO;
import DAO.TypeServiceDAO;
import DAO.ShiftTypesDAO;
import DAO.ShiftWorksDAO;
import DAO.TasksDAO;
import DAO.TimeFrameDAO;
import DAO.VehicleDAO;
import DAO.VehicleTypeDAO;
import DAO.VisitorParkingCardsDAO;
import DatabaseHelper.OpenConnection;
import Model.Buildings;
import Model.Customer;
import Model.LostVisitorParkingCards;
import Model.ParkingSession;
import Model.Regisatration;
import Model.ResidentCard;
import Model.SessionFee;
import Model.ShiftTypes;
import Model.ShiftWorks;
import Model.Staff;
import Model.Tasks;
import Model.TimeFrame;
import Model.TimeFrameToRender;
import Model.Vehicle;
import Model.VehicleType;
import Model.VisitorParkingCards;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
/**
 *
 * @author manhh
 */
public class DataGlobal {
    public DataGlobal() {}
    
    private JFrame viewmain;
    private ArrayList<Regisatration> arrRegistration = new ArrayList<>();
    private ArrayList<Customer> arrCustomer = new ArrayList<>();
    private ArrayList<Vehicle> arrVehicle = new ArrayList<>();
    private ArrayList<ArrayList<String>> arrServiceFee_render = new ArrayList<>();
    private ArrayList<ArrayList<String>> arrServiceType_render = new ArrayList<>();
    private ArrayList<ArrayList<String>> arrPayment_render = new ArrayList<>();
    private ArrayList<ArrayList<String>> arrRegistration_render = new ArrayList<>();
    private ArrayList<ArrayList<String>> arrSessionFee_render = new ArrayList<>();
    private ArrayList<VehicleType> arrVehicleType = new ArrayList<>();
    private ArrayList<TimeFrameToRender> arrTimeFrameToRender = new ArrayList<>();
    
    public void updateArrTimeFrameToRender() {
        this.arrTimeFrameToRender = TimeFrameDAO.getInstance().getTimeFrameToRender();
    }
    public ArrayList<TimeFrameToRender> getArrTimeFrameToRender() {
        return this.arrTimeFrameToRender;
    }
    
    public void updateArrVehicleType() {
        this.arrVehicleType = VehicleTypeDAO.getInstance().getList();
    }
    public ArrayList<VehicleType> getArrVehicleType() {
        return this.arrVehicleType;
    }
    
    public void updateArrSessionFeeRender() {
        this.arrSessionFee_render = SessionFeeDAO.getInstance().getSessionFeeRender();
    }
    public ArrayList<ArrayList<String>> getArrSessionFee_render() {
        return this.arrSessionFee_render;
    }
    
    public void updateArrRegistrationRender() {
        this.arrRegistration_render = RegisatrationDAO.getInstance().getArrRegistrationRender();
    }
    public ArrayList<ArrayList<String>> getArrRegistration_render() {
        return this.arrRegistration_render;
    }
    
    public void updateArrPaymentRender() {
        this.arrPayment_render = PaymentDAO.getInstance().getPaymentRender();
    }
    public ArrayList<ArrayList<String>> getArrPayment_render() {
        return this.arrPayment_render;
    }
    
    public void updateArrServiceType_render() {
        this.arrServiceType_render = TypeServiceDAO.getInstance().getArrServiceTypeRender();
    }
    public ArrayList<ArrayList<String>> getArrServiceType_render() {
        return this.arrServiceType_render;
    }
    
    public void updateArrServiceFee_render() {
        this.arrServiceFee_render = ServiceFeeDAO.getInstance().getArrServiceFee_render();
    }
    public ArrayList<ArrayList<String>> getArrServiceFee_render() {
        return this.arrServiceFee_render;
    }
    
    private List<ShiftTypes> arrShiftTypes = new ArrayList<>();
    private List<ShiftWorks> arrShiftWorks = new ArrayList<>();
    private List<Tasks> arrTask = new ArrayList<>();
    private List<Buildings> arrBuildings = new ArrayList<>();
    private List<VisitorParkingCards> arrVisitorParkingCards = new ArrayList<>();
    private List<LostVisitorParkingCards> arrLostVisitorParkingCards = new ArrayList<>();
    
    public ArrayList<Regisatration> getArrayRegistration() {
        return arrRegistration;
    }
    public void insertArrRegistration_local(Regisatration registration) {
        this.arrRegistration.add(registration);
    }
    public void deleteArrRegistration_local(int registrationId) {
        for (int i = 0; i <= this.arrRegistration.size() - 1; ++i) {
            if (this.arrRegistration.get(i).getRegistration_id() == registrationId) {
                this.arrRegistration.remove(i);
                break;
            }
        }
    }
    public void updateArrRegistration_local(Regisatration registration) {
        for (int i = 0; i <= this.arrRegistration.size() - 1; ++i) {
            if (this.arrRegistration.get(i).getRegistration_id() == registration.getRegistration_id()) {
                this.arrRegistration.remove(i);
                break;
            }
        }
        this.arrRegistration.add(registration);
    }
    public void updateArrRegistration() {
        try {
            arrRegistration = RegisatrationDAO.getInstance().getList();
        } catch (Exception e) {
            System.out.println("LOI KET NOI");
        }
    }
    public ArrayList<Customer> getArrayCustomer() {
        return arrCustomer;
    }
    public void updateArrCustomer() {
        try {
            arrCustomer = CustomerDAO.getInstance().getList();
        } catch (Exception e) {
            System.out.println("LOI KET NOI");
        }
    }
    public ArrayList<Vehicle> getArrayVehicle() {
        return arrVehicle;
    }
    public void updateArrVehicle() {
        try {
            arrVehicle = VehicleDAO.getInstance().getList();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("LOI KET NOI");
        }
    }
    public void updateAllData() {
        updateArrRegistration();
        updateArrCustomer();
        updateArrVehicle();
        updateArrServiceFee_render();
        updateArrServiceType_render();
    }
    
    public List<ShiftTypes> getArrayShiftTypes(){
        return arrShiftTypes;
    }
    public void updateArrShiftTypes(){
        try {
            arrShiftTypes = ShiftTypesDAO.getInstance().getAllShiftTypes();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("LOI KET NOI");
        }
    }
    
    public List<ShiftWorks> getArrayShiftWorks(){
        return arrShiftWorks;
    }
    public void updateArrShiftWorks(){
        try {
            arrShiftWorks = ShiftWorksDAO.getInstance().getAllShiftWorks();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("LOI KET NOI");
        }
    }
    
    public List<Buildings> getArrayBuildings(){
        return arrBuildings;
    }
    public void updateArrBuildings(){
        try {
            arrBuildings = BuildingsDAO.getInstance().getAllBuildings();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("LOI KET NOI");
        }
    }
    public List<Tasks> getArrayTasks(){
        return arrTask;
    }
    public void updateArrtasks(){
        try {
            arrTask = TasksDAO.getInstance().getAllTasks();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("LOI KET NOI");
        }
    }
    public List<VisitorParkingCards> getArrayVisitorParkingCards(){
        return arrVisitorParkingCards;
    }
    public void updateArrayVisitorParkingCardses(){
        try {
            arrVisitorParkingCards = VisitorParkingCardsDAO.getInstance().getAll();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("LOI KET NOI");
        }
    }
    public List<LostVisitorParkingCards> getArrayLostVisitorParkingCardses(){
        return arrLostVisitorParkingCards;
    }
    public void updateArrayLostVisitorParkingCardses(){
        try {
            arrLostVisitorParkingCards = LostVisitorParkingCardsDAO.getInstance().getAll();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("LOI KET NOI");
        }
    }
    
    private ArrayList<ResidentCard> arrResidentCards = new ArrayList<>();
    private ArrayList<ParkingSession> arrParkingSessions;
    public ArrayList<SessionFee> arrSessionFees = new ArrayList<>();
    public ArrayList<TimeFrame> arrTimeFrames = new ArrayList<>();
    public ArrayList<Staff> arrStaffs = new ArrayList<>();

    public void updateArrayStaffs() {
        arrStaffs = new ArrayList<>();
        String sql = "EXEC [dbo].[getlist_staff]";
        try (
                Connection conn = OpenConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet result = stmt.executeQuery(sql);
            ) 
        {
            while (result.next()) {
                int staff_id = result.getInt("staff_id");
                String staff_name = result.getString("full_name");
                int role_id = result.getInt("role_id");
                arrStaffs.add(new Staff(staff_id, staff_name, role_id));
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<Staff> getArrStaffs() { 
        return arrStaffs;
    }
    
    public void updateArrayResidentCard() {
        try {
            arrResidentCards = ResidentCardDAO.getInstance().getList();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("LOI KET NOI");
        }
    }
    
    public ArrayList<ResidentCard> getArrResidentCards() { 
        return arrResidentCards;
    }
    
    public void updateArrayParkingSession() {
        try {
            arrParkingSessions = ParkingSessionDAO.getInstance().getList();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("LOI KET NOI");
        }
    }
    
    public ArrayList<ParkingSession> getArrParkingSession() { 
        return arrParkingSessions;
    }
    
    public void updateArrSessionFees() {
        try {
            arrSessionFees = SessionFeeDAO.getInstance().getList();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("LOI KET NOI");
        }
    }
    
    public ArrayList<SessionFee> getArrSessionFees() { 
        return arrSessionFees;
    }
    
    public void updateArrTimeFrames() {
        try {
            arrTimeFrames = TimeFrameDAO.getInstance().getList();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("LOI KET NOI");
        }
    }
    
    public ArrayList<TimeFrame> getArrTimeFrames() { 
        return arrTimeFrames;
    }
}

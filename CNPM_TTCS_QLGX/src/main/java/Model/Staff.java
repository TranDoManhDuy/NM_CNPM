package Model;

import java.time.LocalDate;

public class Staff {
    private int staff_id;
    private int role_id;
    private String full_name;
    private String ssn;
    private LocalDate date_of_birth;
    private String gender;
    private String phone_number;
    private String address;
    private String email;
    private boolean is_active;
    private int position_id;
    private int account_number;
    
    public Staff() {
        
    }
    
    public Staff(int role_id, String full_name, String ssn, LocalDate date_of_birth, String gender, String phone_number, String address, String email, boolean is_active, int position_id, int account_number) {
        this.role_id = role_id;
        this.full_name = full_name;
        this.ssn = ssn;
        this.date_of_birth = date_of_birth;
        this.gender = gender;
        this.phone_number = phone_number;
        this.address = address;
        this.email = email;
        this.is_active = is_active;
        this.position_id = position_id;
        this.account_number = account_number;
    }
    
    public Staff(int staff_id, String full_name, int role_id, int position_id) {
        this.staff_id = staff_id;
        this.full_name = full_name;
        this.role_id = role_id;
        this.position_id = position_id;
        
    }

    public Staff(int staff_id, int role_id, String full_name, String ssn, LocalDate date_of_birth, String gender, String phone_number, String address, String email, boolean is_active, int position_id, int account_number) {
        this.staff_id = staff_id;
        this.role_id = role_id;
        this.full_name = full_name;
        this.ssn = ssn;
        this.date_of_birth = date_of_birth;
        this.gender = gender;
        this.phone_number = phone_number;
        this.address = address;
        this.email = email;
        this.is_active = is_active;
        this.position_id = position_id;
        this.account_number = account_number;
    }

    public int getStaffId() {
        return staff_id;
    }

    public void setStaffId(int staff_id) {
        this.staff_id = staff_id;
    }

    public int getRoleId() {
        return role_id;
    }

    public void setRoleId(int role_id) {
        this.role_id = role_id;
    }

    public String getFullName() {
        return full_name;
    }

    public void setFullName(String full_name) {
        this.full_name = full_name;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public LocalDate getDateOfBirth() {
        return date_of_birth;
    }

    public void setDateOfBirth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return is_active;
    }

    public void setActive(boolean is_active) {
        this.is_active = is_active;
    }

    public int getPositionId() {
        return position_id;
    }

    public void setPositionId(int position_id) {
        this.position_id = position_id;
    }

    public int getAccountNumber() {
        return account_number;
    }

    public void setAccountNumber(int account_number) {
        this.account_number = account_number;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staff_id=" + staff_id +
                ", role_id=" + role_id +
                ", full_name='" + full_name + '\'' +
                ", ssn='" + ssn + '\'' +
                ", date_of_birth=" + date_of_birth +
                ", gender='" + gender + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", is_active=" + is_active +
                ", position_id=" + position_id +
                ", account_number=" + account_number +
                '}';
    }
}
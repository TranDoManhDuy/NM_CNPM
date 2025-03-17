package Model;

public class Accounts {

    private int account_number;
    private String password;
    private boolean is_active;
    private String staff_id;

    public Accounts() {
    }

    public Accounts(int account_number, String password, boolean is_active, String staff_id) {
        this.account_number = account_number;
        this.password = password;
        this.is_active = is_active;
        this.staff_id = staff_id;
    }

    public int getAccountNumber() {
        return account_number;
    }

    public void setAccountNumber(int account_number) {
        this.account_number = account_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return is_active;
    }

    public void setActive(boolean is_active) {
        this.is_active = is_active;
    }

    public String getStaffId() {
        return staff_id;
    }

    public void setStaffId(String staff_id) {
        this.staff_id = staff_id;
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "account_number=" + account_number +
                ", password='" + password + '\'' +
                ", is_active=" + is_active +
                ", staff_id='" + staff_id + '\'' +
                '}';
    }
}

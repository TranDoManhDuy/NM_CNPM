package Model;

public class Account {
    private int accountNumber;
    private String password;
    private boolean isActive;
    private int roleId;

    public Account() {}

    public Account(int accountNumber, String password, boolean isActive, int roleId) {
        this.accountNumber = accountNumber;
        this.password = password;
        this.isActive = isActive;
        this.roleId = roleId;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", isActive=" + isActive +
                ", roleId=" + roleId +
                '}';
    }
}

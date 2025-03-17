package Model;

public class Roles {
    private int role_id;
    private String role_name;

    public Roles() {}

    public Roles(int role_id, String role_name) {
        this.role_id = role_id;
        this.role_name = role_name;
    }
    
    public Roles(String role_name) {
        this.role_name = role_name;
    }

    public int getRoleId() {
        return role_id;
    }

    public void setRoleId(int role_id) {
        this.role_id = role_id;
    }

    public String getRoleName() {
        return role_name;
    }

    public void setRoleName(String role_name) {
        this.role_name = role_name;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "role_id=" + role_id +
                ", role_name='" + role_name + '\'' +
                '}';
    }
}

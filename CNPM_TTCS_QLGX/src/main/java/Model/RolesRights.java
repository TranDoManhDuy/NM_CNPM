package Model;

public class RolesRights {
    private int role_id;
    private int right_id;
    private int roles_rights_id;

    public RolesRights() {}

    public RolesRights(int role_id, int right_id, int roles_rights_id) {
        this.role_id = role_id;
        this.right_id = right_id;
        this.roles_rights_id = roles_rights_id;
    }
    
    public RolesRights(int role_id, int right_id) {
        this.role_id = role_id;
        this.right_id = right_id;
    }

    public int getRoleId() {
        return role_id;
    }

    public void setRoleId(int role_id) {
        this.role_id = role_id;
    }

    public int getRightId() {
        return right_id;
    }

    public void setRightId(int right_id) {
        this.right_id = right_id;
    }

    public int getRolesRightsId() {
        return roles_rights_id;
    }

    public void setRolesRightsId(int roles_rights_id) {
        this.roles_rights_id = roles_rights_id;
    }

    @Override
    public String toString() {
        return "RolesRights{" +
                "role_id=" + role_id +
                ", right_id=" + right_id +
                ", roles_rights_id=" + roles_rights_id +
                '}';
    }
}

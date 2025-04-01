package Model;

public class RolePermission {
    private int role_id;
    private int permission_id;
    private int role_permission_id;

    public RolePermission() {}

    public RolePermission(int role_id, int permission_id, int role_permission_id) {
        this.role_id = role_id;
        this.permission_id = permission_id;
        this.role_permission_id = role_permission_id;
    }

    public int getRoleId() {
        return role_id;
    }

    public void setRoleId(int role_id) {
        this.role_id = role_id;
    }

    public int getPermissionId() {
        return permission_id;
    }

    public void setPermissionId(int permission_id) {
        this.permission_id = permission_id;
    }

    public int getRolesPermissionId() {
        return role_permission_id;
    }

    public void setRolesPermissionId(int role_permission_id) {
        this.role_permission_id = role_permission_id;
    }

    @Override
    public String toString() {
        return "RolePermission{" +
                "role_id=" + role_id +
                ", permission_id=" + permission_id +
                ", roles_permission_id=" + role_permission_id +
                '}';
    }
}

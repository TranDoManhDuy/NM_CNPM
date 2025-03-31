package Model;

public class Permission {
    private int permission_id;
    private String permission_name;
    private String permission_desc;

    public Permission() {}

    public Permission(int permission_id, String permission_name, String permission_desc) {
        this.permission_id = permission_id;
        this.permission_name = permission_name;
        this.permission_desc = permission_desc;
    }

    public int getPermissionId() {
        return permission_id;
    }

    public void setPermissionId(int permission_id) {
        this.permission_id = permission_id;
    }

    public String getPermissionName() {
        return permission_name;
    }

    public void setPermissionName(String permission_name) {
        this.permission_name = permission_name;
    }

    public String getPermissionDesc() {
        return permission_desc;
    }

    public void setPermissionDesc(String permission_desc) {
        this.permission_desc = permission_desc;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "permission_id=" + permission_id +
                ", permission_name='" + permission_name + '\'' +
                ", permission_desc='" + permission_desc + '\'' +
                '}';
    }
}

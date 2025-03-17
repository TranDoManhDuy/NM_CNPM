package Model;

public class Rights {
    private int right_id;
    private String right_name;
    private String right_desc;

    public Rights() {}

    public Rights(int right_id, String right_name, String right_desc) {
        this.right_id = right_id;
        this.right_name = right_name;
        this.right_desc = right_desc;
    }
    
    public Rights(String right_name, String right_desc) {
        this.right_name = right_name;
        this.right_desc = right_desc;
    }

    public int getRightId() {
        return right_id;
    }

    public void setRightId(int right_id) {
        this.right_id = right_id;
    }

    public String getRightName() {
        return right_name;
    }

    public void setRightName(String right_name) {
        this.right_name = right_name;
    }

    public String getRightDesc() {
        return right_desc;
    }

    public void setRightDesc(String right_desc) {
        this.right_desc = right_desc;
    }

    @Override
    public String toString() {
        return "Rights{" +
                "right_id=" + right_id +
                ", right_name='" + right_name + '\'' +
                ", right_desc='" + right_desc + '\'' +
                '}';
    }
}

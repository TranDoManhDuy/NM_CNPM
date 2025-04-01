package Model;

public class Manager {

    private int staff_id;

    public Manager() {
    }

    public Manager(int staff_id) {
        this.staff_id = staff_id;
    }

    public int getStaffId() {
        return staff_id;
    }

    public void setStaffId(int staff_id) {
        this.staff_id = staff_id;
    }

    @Override
    public String toString() {
        return "Managers{" +
                "staff_id=" + staff_id +
                '}';
    }
}

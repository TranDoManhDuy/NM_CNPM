package Model;

public class Managers {

    private int staff_id;

    public Managers() {
    }

    public Managers(int staff_id) {
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

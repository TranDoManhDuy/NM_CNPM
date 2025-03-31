package Model;

public class Supervisor {
    private int manager_id;
    private int staff_id;
    private int supervisor_id;

    public Supervisor() {}

    public Supervisor(int manager_id, int staff_id, int supervisor_id) {
        this.manager_id = manager_id;
        this.staff_id = staff_id;
        this.supervisor_id = supervisor_id;
    }
    
    public Supervisor(int manager_id, int staff_id) {
        this.manager_id = manager_id;
        this.staff_id = staff_id;
    }

    public int getManagerId() {
        return manager_id;
    }

    public void setManagerId(int manager_id) {
        this.manager_id = manager_id;
    }

    public int getStaffId() {
        return staff_id;
    }

    public void setStaffId(int staff_id) {
        this.staff_id = staff_id;
    }

    public int getSupervisorId() {
        return supervisor_id;
    }

    public void setSupervisorId(int supervisor_id) {
        this.supervisor_id = supervisor_id;
    }

    @Override
    public String toString() {
        return "Supervisors{" +
                "manager_id=" + manager_id +
                ", staff_id=" + staff_id +
                ", supervisor_id=" + supervisor_id +
                '}';
    }
}

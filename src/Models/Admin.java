package Models;

public class Admin extends User {
    private int workerId;

    public Admin(int id, String username, String password, String role, int workerId) {
        super(id, username, password, role);
        this.workerId = workerId;
    }

    public int getWorkerId() {
        return this.workerId;
    }
}

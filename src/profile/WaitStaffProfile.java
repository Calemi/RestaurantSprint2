package profile;

public class WaitStaffProfile extends Profile {

    private int[] assignedTables;

    public WaitStaffProfile(String username, String password, int... assignedTables) {
        super(username, password);
        this.assignedTables = assignedTables;
    }

    public int[] getAssignedTables() {
        return assignedTables;
    }

    public boolean isAssignedTable(int tableID) {

        for (int i = 0; i < assignedTables.length; i++) {

            if (assignedTables[i] == tableID) {
                return true;
            }
        }

        return false;
    }
}

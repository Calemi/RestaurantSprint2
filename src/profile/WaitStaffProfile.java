package profile;

/**
 * An extension of profile to handle waitstaff specific information and functions.
 */
public class WaitStaffProfile extends Profile {

    private final int[] assignedTables;

    public WaitStaffProfile(String username, String password, int... assignedTables) {
        super(username, password);
        this.assignedTables = assignedTables;
    }

    /**
     * Gets the waitstaff's assigned tables.
     */
    public int[] getAssignedTables() {
        return assignedTables;
    }
}

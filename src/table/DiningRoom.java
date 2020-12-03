package table;

import profile.WaitStaffProfile;

public class DiningRoom {

    private static DiningRoom instance = new DiningRoom();

    public static DiningRoom getInstance() {
        return instance;
    }

    private Table[] tables;

    private DiningRoom() {

        tables = new Table[30];

        for (int i = 0; i < tables.length; i++) {
            tables[i] = new Table(i, Table.TableState.CLOSED);
        }
    }

    public void assignTables(WaitStaffProfile profile) {

        for (int i = 0; i < profile.getAssignedTables().length; i++) {

            tables[profile.getAssignedTables()[i]].setState(Table.TableState.OPEN);
        }
    }
}

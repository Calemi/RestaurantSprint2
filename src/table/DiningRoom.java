package table;

import profile.WaitStaffProfile;

import java.util.Random;

public class DiningRoom {

    private static DiningRoom instance = new DiningRoom();

    public static DiningRoom getInstance() {
        return instance;
    }

    private Table[] tables;

    private DiningRoom() {

        tables = new Table[30];

        for (int i = 0; i < tables.length; i++) {

            tables[i] = new Table(i, Table.TableState.getRandomState(), new Tab());
        }
    }

    public Table[] getTables() {
        return tables;
    }

    public void assignTables(WaitStaffProfile profile) {

        for (int i = 0; i < tables.length; i++) {

            boolean found = false;

            for (int j = 0; j < profile.getAssignedTables().length; j++) {

                if (i == profile.getAssignedTables()[j]) found = true;
            }

            if (!found) {
                tables[i].setState(Table.TableState.CLOSED);
            }
        }
    }
}

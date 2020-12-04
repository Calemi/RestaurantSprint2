package table;

import order.Tab;
import profile.WaitStaffProfile;

/**
 * Holds an amount of tables.
 */
public class DiningRoom {

    private static final DiningRoom instance = new DiningRoom();

    /**
     * Used to get the single instance of the dining room.
     */
    public static DiningRoom getInstance() {
        return instance;
    }

    private Table[] tables;

    private DiningRoom() {
        setupTables();
    }

    /**
     * Gets all tables in the dining room.
     */
    public Table[] getTables() {
        return tables;
    }

    /**
     * Creates and sets all tables to a random state.
     */
    public void setupTables() {

        tables = new Table[30];

        for (int i = 0; i < tables.length; i++) {
            tables[i] = new Table(i, Table.TableState.getRandomState(), new Tab());
        }
    }

    /**
     * Sets all tables to a random state.
     */
    public void randomizeTableStates() {

        for (Table table : tables) {
            table.setState(Table.TableState.getRandomState());
        }
    }

    /**
     * Sets all tables not assigned to a profile to the "closed" state.
     */
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

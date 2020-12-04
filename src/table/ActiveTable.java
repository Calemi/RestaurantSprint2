package table;

public class ActiveTable {

    private static Table activeTable;

    /**
     * Gets the active table selected in the GUI.
     */
    public static Table getActiveTable() {
        return activeTable;
    }

    /**
     * Sets the active table selected in the GUI.
     */
    public static void setActiveTable(Table table) {
        activeTable = table;
    }
}

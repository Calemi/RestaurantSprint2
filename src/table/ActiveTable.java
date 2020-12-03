package table;

public class ActiveTable {

    private static Table activeTable;

    public static Table getActiveTable() {
        return activeTable;
    }

    public static void setActiveTable(Table table) {
        activeTable = table;
    }
}

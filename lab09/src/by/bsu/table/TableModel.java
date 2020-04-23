package by.bsu.table;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {
    private Cell[][] table;

    public TableModel(Cell[][] table) {
        this.table = table;
    }

    @Override
    public int getRowCount() {
        return table.length;
    }

    @Override
    public int getColumnCount() {
        return table[0].length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return table[rowIndex][columnIndex];
    }
}

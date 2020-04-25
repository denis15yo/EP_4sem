package by.bsu.table;

import by.bsu.essenses.expressions.operands.CellReference;
import by.bsu.myUtil.Parser;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.text.ParseException;

public class ExcelTableModel extends AbstractTableModel {
    private final Cell[][] table;

    public ExcelTableModel(int n) {
        table = new Cell[n][n];
    }

    @Override
    public int getRowCount() {
        return table.length;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try {
            table[rowIndex][columnIndex] = new Cell(Parser.parseToExpression((String) aValue));
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public int getColumnCount() {
        return table[0].length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return table[rowIndex][columnIndex];
    }

    public Cell getByCellReference(CellReference cellReference){
        String column = cellReference.getColumn();
        int j = 0;
        int temp = 1;
        for(int i = column.length() - 1; i >= 0; --i){
            j += (column.charAt(i) - 'A' + 1) * temp;
            temp *= 26;
        }
        return table[cellReference.getRow() - 1][j - 1];
    }
}

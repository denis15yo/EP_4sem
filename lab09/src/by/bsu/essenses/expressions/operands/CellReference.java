package by.bsu.essenses.expressions.operands;

import by.bsu.essenses.MyDate;
import by.bsu.table.Cell;
import by.bsu.table.ExcelTableModel;

public class CellReference implements Operand {
    private final int row;
    private final String column;

    public CellReference(int row, String column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public MyDate getDateValue(ExcelTableModel excelTableModel) {
        int j = 0;
        int temp = 1;
        for(int i = column.length() - 1; i >= 0; --i){
            j += (column.charAt(i) - 'A' + 1) * temp;
            temp *= 26;
        }
        Cell cell = (Cell) excelTableModel.getValueAt(row - 1, j - 1);
        return cell.getExpression().calculate(excelTableModel);
    }

    @Override
    public String toString() {
        return column + row;
    }
}

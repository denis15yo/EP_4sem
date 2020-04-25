package by.bsu.essenses.expressions.operands;

import by.bsu.essenses.MyDate;
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
        return excelTableModel.getByCellReference(this).getExpression().calculate(excelTableModel);
    }

    @Override
    public String toString() {
        return column + row;
    }

    public int getRow() {
        return row;
    }

    public String getColumn() {
        return column;
    }
}

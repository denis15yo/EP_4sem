package by.bsu.essenses.expressions.operands;

import by.bsu.essenses.MyDate;
import by.bsu.table.ExcelTableModel;

public interface Operand {
    MyDate getDateValue(ExcelTableModel excelTableModel);
}

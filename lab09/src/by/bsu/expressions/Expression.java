package by.bsu.expressions;

import by.bsu.expressions.operands.MyDate;
import by.bsu.excelTable.ExcelTableModel;

public interface Expression {
    MyDate calculate(ExcelTableModel excelTableModel);
}

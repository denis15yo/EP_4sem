package by.bsu.essenses.expressions;

import by.bsu.essenses.MyDate;
import by.bsu.table.ExcelTableModel;

public interface Expression {
    MyDate calculate(ExcelTableModel excelTableModel);
}

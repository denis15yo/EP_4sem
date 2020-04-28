package by.bsu.essenses;

import by.bsu.essenses.expressions.Expression;
import by.bsu.essenses.expressions.operands.Operand;
import by.bsu.table.ExcelTableModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDate extends Date implements Expression, Operand {
    public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public MyDate(Date date) {
        super(date.getTime());
    }

    @Override
    public String toString() {
        return simpleDateFormat.format(this);
    }

    @Override
    public MyDate calculate(ExcelTableModel excelTableModel) {
        return this;
    }

    @Override
    public MyDate getDateValue(ExcelTableModel excelTableModel) {
        return this;
    }
}

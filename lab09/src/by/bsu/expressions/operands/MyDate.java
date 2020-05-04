package by.bsu.expressions.operands;

import by.bsu.expressions.Expression;
import by.bsu.models.ExcelTableModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDate extends Date implements Expression, Operand {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yy");

    public MyDate(Date date) {
        super(date.getTime());
    }

    @Override
    public String toString() {
        return DATE_FORMAT.format(this);
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

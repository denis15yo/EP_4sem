package by.bsu.expressions.formulas;

import by.bsu.expressions.operands.MyDate;
import by.bsu.expressions.operands.Operand;
import by.bsu.models.ExcelTableModel;

import java.util.Calendar;

public class MinFormula extends ExtremumFormula {
    @Override
    public MyDate calculate(ExcelTableModel excelTableModel) {
        Calendar minCalendar = Calendar.getInstance();
        minCalendar.setTime(operands.get(0).getDateValue(excelTableModel));
        for(Operand o : operands){
            Calendar currentCalendar = Calendar.getInstance();
            currentCalendar.setTime(o.getDateValue(excelTableModel));
            if(minCalendar.after(currentCalendar)){
                minCalendar = currentCalendar;
            }
        }
        return new MyDate(minCalendar.getTime());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("=МИН(");
        for(int i = 0; i < operands.size() - 1; ++i){
            stringBuilder.append(operands.get(i)).append(", ");
        }
        stringBuilder.append(operands.get(operands.size() - 1)).append(")");
        return stringBuilder.toString();
    }
}

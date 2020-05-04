package by.bsu.expressions.formulas;

import by.bsu.expressions.operands.MyDate;
import by.bsu.expressions.operands.Operand;
import by.bsu.models.ExcelTableModel;

import java.util.Calendar;

public class OffsetFormula extends Formula {
    private final Operand operand;
    private final char operation;
    private final int numericalConstant;

    public OffsetFormula(Operand operand, char operation, int numericalConstant) {
        this.operand = operand;
        this.operation = operation;
        this.numericalConstant = numericalConstant;
    }

    public Operand getOperand() {
        return operand;
    }

    @Override
    public MyDate calculate(ExcelTableModel excelTableModel) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(operand.getDateValue(excelTableModel));
        if(operation == '+'){
            calendar.add(Calendar.DAY_OF_MONTH, numericalConstant);
        }
        else{
            calendar.add(Calendar.DAY_OF_MONTH, -numericalConstant);
        }
        return new MyDate(calendar.getTime());
    }

    @Override
    public String toString() {
        return "=" + operand.toString() + operation + numericalConstant;
    }
}

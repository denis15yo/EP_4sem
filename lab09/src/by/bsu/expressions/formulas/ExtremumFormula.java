package by.bsu.essenses.expressions;

import by.bsu.essenses.expressions.operands.Operand;

import java.util.ArrayList;
import java.util.List;

public abstract class ExtremumFormula extends Formula {
    protected List<Operand> operands;

    public ExtremumFormula() {
        operands = new ArrayList<>();
    }

    public void addOperand(Operand operand){
        operands.add(operand);
    }
}

package by.bsu.expressions.formulas;

import by.bsu.expressions.operands.Operand;

import java.util.ArrayList;
import java.util.List;

public abstract class ExtremumFormula extends Formula {
    protected List<Operand> operands;

    public List<Operand> getOperands() {
        return operands;
    }

    public ExtremumFormula() {
        operands = new ArrayList<>();
    }

    public void addOperand(Operand operand){
        operands.add(operand);
    }
}

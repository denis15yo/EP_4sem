package by.bsu.models;

import by.bsu.exceptions.EmptyCellReferenceException;
import by.bsu.exceptions.OutOfTableBoundsException;
import by.bsu.expressions.Expression;
import by.bsu.expressions.formulas.ExtremumFormula;
import by.bsu.expressions.formulas.OffsetFormula;
import by.bsu.expressions.operands.CellReference;
import by.bsu.expressions.operands.Operand;

import java.util.List;

public class Cell {
    private final Expression expression;

    private boolean used = false;

    public Cell(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return expression.toString();
    }

    public boolean hasCycle(ExcelTableModel excelTableModel) throws EmptyCellReferenceException, OutOfTableBoundsException {
        if(used){
            return true;
        }
        if(expression instanceof OffsetFormula){
            OffsetFormula offsetFormula = (OffsetFormula) expression;
            Operand operand = offsetFormula.getOperand();
            if (hasCycle(excelTableModel, operand)) return true;
            used = false;
            return false;
        }
        if(expression instanceof ExtremumFormula){
            ExtremumFormula extremumFormula = (ExtremumFormula) expression;
            List<Operand> operandList = extremumFormula.getOperands();
            for(Operand operand : operandList){
                if (hasCycle(excelTableModel, operand)) return true;
            }
            used = false;
            return false;
        }
        return false;
    }

    private boolean hasCycle(ExcelTableModel excelTableModel, Operand operand) throws EmptyCellReferenceException, OutOfTableBoundsException {
        if(operand instanceof CellReference){
            CellReference cellReference = (CellReference) operand;
            Cell cell;
            try{
                cell = excelTableModel.getByCellReference(cellReference);
            } catch (ArrayIndexOutOfBoundsException e){
                throw new OutOfTableBoundsException();
            }
            if(cell == null){
                throw new EmptyCellReferenceException();
            }
            used = true;
            if(cell.hasCycle(excelTableModel)){
                used = false;
                return true;
            }
        }
        return false;
    }
}

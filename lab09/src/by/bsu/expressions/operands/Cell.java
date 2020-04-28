package by.bsu.expressions.operands;

import by.bsu.excelTable.ExcelTableModel;
import by.bsu.expressions.Expression;
import by.bsu.expressions.formulas.ExtremumFormula;
import by.bsu.expressions.formulas.OffsetFormula;

import java.text.ParseException;
import java.util.List;

public class Cell {
    private Expression expression;

    private boolean used = false;

    public Cell() {
    }

    public Cell(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return (expression == null) ? "" : expression.toString();
    }

    public boolean hasCycle(ExcelTableModel excelTableModel) throws ParseException {
        if(used){
            return true;
        }
        if(expression instanceof OffsetFormula){
            OffsetFormula offsetFormula = (OffsetFormula) expression;
            Operand operand = offsetFormula.getOperand();
            if(operand instanceof CellReference){
                CellReference cellReference = (CellReference) operand;
                Cell cell = excelTableModel.getByCellReference(cellReference);
                if(cell == null){
                    throw new ParseException("Ссылка на пустую ячейку", 0);
                }
                used = true;
                if(cell.hasCycle(excelTableModel)){
                    used = false;
                    return true;
                }
            }
            used = false;
            return false;
        }
        if(expression instanceof ExtremumFormula){
            ExtremumFormula extremumFormula = (ExtremumFormula) expression;
            List<Operand> operandList = extremumFormula.getOperands();
            for(Operand operand : operandList){
                if(operand instanceof CellReference){
                    CellReference cellReference = (CellReference) operand;
                    Cell cell = excelTableModel.getByCellReference(cellReference);
                    if(cell == null){
                        throw new ParseException("Ссылка на пустую ячейку", 0);
                    }
                    used = true;
                    if(cell.hasCycle(excelTableModel)){
                        used = false;
                        return true;
                    }
                }
            }
            used = false;
            return false;
        }
        return false;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}

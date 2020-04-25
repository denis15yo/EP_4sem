package by.bsu.table;

import by.bsu.essenses.expressions.Expression;

public class Cell {
    private Expression expression;

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
}

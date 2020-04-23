package by.bsu.table;

public class Cell {
    private String expr;
    private String value;

    public Cell(String expr, String value) {
        this.expr = expr;
        this.value = value;
    }

    public String getExpr() {
        return expr;
    }

    public void setExpr(String expr) {
        this.expr = expr;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

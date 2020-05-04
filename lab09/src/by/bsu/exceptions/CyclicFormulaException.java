package by.bsu.exceptions;

public class CyclicFormulaException extends Exception {
    public CyclicFormulaException() {
        super("Циклическая формула.");
    }
}

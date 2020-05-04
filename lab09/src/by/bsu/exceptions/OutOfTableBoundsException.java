package by.bsu.exceptions;

public class OutOfTableBoundsException extends Exception {
    public OutOfTableBoundsException() {
        super("Слишком большой индекс в ссылке на ячейку.");
    }
}

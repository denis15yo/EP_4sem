package by.bsu.exceptions;

public class EmptyCellReferenceException extends Exception {
    public EmptyCellReferenceException() {
        super("Ссылка на пустую ячейку.");
    }
}

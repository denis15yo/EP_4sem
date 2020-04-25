package by.bsu.graphics.frames;

import by.bsu.graphics.panels.ExcelPanel;

import javax.swing.*;

@SuppressWarnings("FieldCanBeLocal")
public class ExcelFrame extends JFrame {
    private final ExcelPanel excelPanel;
    public ExcelFrame() {
        excelPanel = new ExcelPanel();

        add(excelPanel);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}

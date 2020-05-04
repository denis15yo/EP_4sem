package by.bsu.view.frames;

import by.bsu.view.panels.ExcelPanel;

import javax.swing.*;

@SuppressWarnings("FieldCanBeLocal")
public class ExcelFrame extends JFrame {
    private final ExcelPanel excelPanel;
    public ExcelFrame() {
        super("Excel Lite");
        excelPanel = new ExcelPanel();

        add(excelPanel);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}

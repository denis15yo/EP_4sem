package by.bsu.graphics.frames;

import by.bsu.graphics.panels.ExcelPanel;
import by.bsu.table.Cell;
import by.bsu.table.CellRenderer;
import by.bsu.table.TableModel;

import javax.swing.*;

@SuppressWarnings("FieldCanBeLocal")
public class ExcelFrame extends JFrame {
    private final ExcelPanel excelPanel;
    public ExcelFrame() {
        excelPanel = new ExcelPanel(5);

        add(excelPanel);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}

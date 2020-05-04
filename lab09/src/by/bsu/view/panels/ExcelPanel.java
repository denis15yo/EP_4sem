package by.bsu.view.panels;

import by.bsu.view.excelTable.*;
import by.bsu.view.excelTable.rowHeader.RowHeaderListModel;
import by.bsu.view.excelTable.rowHeader.RowHeaderRenderer;
import by.bsu.models.ExcelTableModel;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("FieldCanBeLocal")
public class ExcelPanel extends JPanel {
    private static final int n = 30;

    private final ExcelTable excelTable;

    public ExcelPanel() {
        super(new BorderLayout());

        excelTable = new ExcelTable(new ExcelTableModel(n));

        JList<Integer> rowHeader = new JList<>(new RowHeaderListModel(n));
        rowHeader.setCellRenderer(new RowHeaderRenderer(excelTable));

        JScrollPane jScrollPane = new JScrollPane(excelTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setRowHeaderView(rowHeader);

        add(jScrollPane, BorderLayout.CENTER);
    }
}

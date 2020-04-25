package by.bsu.graphics.panels;

import by.bsu.table.*;
import by.bsu.table.rowHeader.RowHeaderListModel;
import by.bsu.table.rowHeader.RowHeaderRenderer;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("FieldCanBeLocal")
public class ExcelPanel extends JPanel {
    private static final int n = 15;

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

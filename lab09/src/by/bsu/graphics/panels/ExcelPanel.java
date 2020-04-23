package by.bsu.graphics.panels;

import by.bsu.table.Cell;
import by.bsu.table.CellRenderer;
import by.bsu.table.HeaderRenderer;
import by.bsu.table.TableModel;
import by.bsu.table.rowHeader.RowHeaderListModel;
import by.bsu.table.rowHeader.RowHeaderRenderer;

import javax.swing.*;
import java.awt.*;

public class ExcelPanel extends JPanel {
    private JTable excelTable;

    public ExcelPanel(int n) {
        super(new BorderLayout());

        Cell[][] table = new Cell[n][n];
        for(int i = 0; i  < n; ++i){
            for(int j = 0; j < n; ++j){
                table[i][j] = new Cell("expr", "value");
            }
        }
        JTable jTable = new JTable(new TableModel(table));
        jTable.setShowVerticalLines(true);
        jTable.setGridColor(Color.BLACK);
        jTable.setShowGrid(true);

        for(int i = 0; i < jTable.getColumnCount(); ++i){
            jTable.getColumnModel().getColumn(i).setCellRenderer(new CellRenderer());
        }

        jTable.getTableHeader().setDefaultRenderer(new HeaderRenderer(jTable));

        JList<Integer> rowHeader = new JList<>(new RowHeaderListModel(jTable.getRowCount()));
        rowHeader.setFixedCellWidth(50);
        rowHeader.setCellRenderer(new RowHeaderRenderer(jTable));

        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setRowHeaderView(rowHeader);

        add(jScrollPane, BorderLayout.CENTER);
    }
}

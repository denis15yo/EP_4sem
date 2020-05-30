package by.bsu.view.tables;

import by.bsu.model.TicketsTableModel;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;

public class TicketsTable extends JTable {
    private final TicketsTableModel model;

    public TicketsTable(TicketsTableModel model) {
        super(model);

        this.model = model;

        setRowHeight(50);
        setShowGrid(true);
        setGridColor(Color.GRAY);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setAutoResizeMode(AUTO_RESIZE_LAST_COLUMN);

        getTableHeader().setDefaultRenderer(new HeaderRenderer(this));

        setDefaultRenderer(Object.class, new CellRenderer());
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component component = super.prepareRenderer(renderer, row, column);
        int rendererWidth = component.getPreferredSize().width;
        TableColumn tableColumn = getColumnModel().getColumn(column);
        tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
        return component;
    }
}

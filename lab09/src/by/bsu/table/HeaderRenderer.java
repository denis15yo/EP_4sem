package by.bsu.table;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class HeaderRenderer implements TableCellRenderer {
    private DefaultTableCellRenderer renderer;

    public HeaderRenderer(JTable jTable) {
        renderer = (DefaultTableCellRenderer) jTable.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        renderer.setPreferredSize(new Dimension(100, jTable.getRowHeight()));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}

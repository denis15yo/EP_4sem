package by.bsu.view.tables;

import by.bsu.model.Flight;
import by.bsu.model.Person;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class CellRenderer extends JLabel implements TableCellRenderer {
    public CellRenderer() {
        setOpaque(true);
        setHorizontalAlignment(JLabel.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setBackground(isSelected ? Color.LIGHT_GRAY : Color.WHITE);
        setText(value.toString());
        return this;
    }
}

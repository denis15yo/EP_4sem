package graphics.panels;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class MyCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if(column == 0){
            setIcon((Icon) value);
            setText("");
        }

        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        return this;
    }
}
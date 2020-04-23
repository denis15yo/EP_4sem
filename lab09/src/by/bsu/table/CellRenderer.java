package by.bsu.table;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class CellRenderer extends JLabel implements TableCellRenderer {
    public CellRenderer() {
        setHorizontalAlignment(CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Cell cell = (Cell)value;
        if(hasFocus){
            setText(cell.getExpr());
        }
        else{
            setText(cell.getValue());
        }

        return this;
    }
}

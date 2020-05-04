package by.bsu.view.excelTable;

import by.bsu.models.Cell;
import by.bsu.models.ExcelTableModel;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class CellRenderer extends JLabel implements TableCellRenderer {
    public CellRenderer() {
        setHorizontalAlignment(CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if(value == null){
            setText("");
            return this;
        }
        Cell cell = (Cell)value;
        if(hasFocus){
            setText(cell.getExpression().toString());
        }
        else{
            setText(cell.getExpression().calculate((ExcelTableModel) table.getModel()).toString());
        }

        return this;
    }
}

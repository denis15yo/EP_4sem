package by.bsu.table;

import javax.swing.*;
import java.awt.*;

public class ExcelTable extends JTable {
    public ExcelTable(ExcelTableModel excelTableModel) {
        super(excelTableModel);

        setGridColor(Color.LIGHT_GRAY);
        setShowGrid(true);

        for(int i = 0; i < getColumnCount(); ++i){
            getColumnModel().getColumn(i).setCellRenderer(new CellRenderer());
        }
        setRowHeight(30);

//        setFillsViewportHeight(true);
        setAutoResizeMode(AUTO_RESIZE_OFF);

        getTableHeader().setDefaultRenderer(new HeaderRenderer(this));
    }
}

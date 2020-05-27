package by.bsu.view.panels.chooseTypeRoad;

import by.bsu.model.road.TypeRoad;

import javax.swing.*;
import java.awt.*;

public class ChooseTypeRoadPanel extends JPanel {
    private final JTable table;

    public ChooseTypeRoadPanel() {
        super(new BorderLayout());
        
        TypesRoadTableModel tableModel = new TypesRoadTableModel();
        table = new JTable(tableModel);

        for(int i = 0; i < table.getColumnCount(); ++i){
            table.getColumnModel().getColumn(i).setCellRenderer(new TypesRoadCellRenderer());
        }
        table.setRowHeight(tableModel.getRoadList().stream().mapToInt(type -> type.getBg().getHeight(null)).max().getAsInt());

        setPreferredSize(table.getPreferredSize());

        setFocusable(true);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setCellSelectionEnabled(true);

        add(table, BorderLayout.CENTER);
    }

    public TypeRoad getChosenTypeRoad() throws IndexOutOfBoundsException{
        return (TypeRoad) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn());
    }
}

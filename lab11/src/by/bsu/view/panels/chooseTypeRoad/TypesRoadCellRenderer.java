package by.bsu.view.panels.chooseTypeRoad;

import by.bsu.model.road.TypeRoad;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TypesRoadCellRenderer extends DefaultTableCellRenderer {
    public TypesRoadCellRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        TypeRoad typeRoad = (TypeRoad) value;
        setIcon(new ImageIcon(typeRoad.getBg().getScaledInstance(250, 300, Image.SCALE_SMOOTH)));
        if(isSelected){
            setBackground(Color.LIGHT_GRAY);
        }
        else{
            setBackground(Color.WHITE);
        }

        return this;
    }
}

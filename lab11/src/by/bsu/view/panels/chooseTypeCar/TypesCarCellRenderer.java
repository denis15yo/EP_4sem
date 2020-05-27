package by.bsu.view.panels.chooseTypeCar;

import by.bsu.model.car.TypeCar;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TypesCarCellRenderer extends DefaultTableCellRenderer {
    public TypesCarCellRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        TypeCar typeRoad = (TypeCar) value;
        setIcon(new ImageIcon(typeRoad.getImage()));
        if(isSelected){
            setBackground(Color.LIGHT_GRAY);
        }
        else{
            setBackground(Color.WHITE);
        }

        return this;
    }
}

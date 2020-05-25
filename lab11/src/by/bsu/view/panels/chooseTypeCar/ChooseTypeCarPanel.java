package by.bsu.view.panels.chooseTypeCar;

import by.bsu.models.car.TypeCar;
import by.bsu.view.panels.chooseTypeRoad.TypesRoadCellRenderer;

import javax.swing.*;
import java.awt.*;

public class ChooseTypeCarPanel extends JPanel {
    private JTable table;

    public ChooseTypeCarPanel() {
        super(new BorderLayout());

        TypesCarTableModel tableModel = new TypesCarTableModel();
        table = new JTable(tableModel);

        for(int i = 0; i < table.getColumnCount(); ++i){
            table.getColumnModel().getColumn(i).setCellRenderer(new TypesCarCellRenderer());
        }
        table.setRowHeight(tableModel.getCarList().stream().mapToInt(type -> type.getImage().getHeight(null)).max().getAsInt());

        setPreferredSize(table.getPreferredSize());

        setFocusable(true);


        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setCellSelectionEnabled(true);

        add(table, BorderLayout.CENTER);
    }

    public TypeCar getChosenTypeCar() throws IndexOutOfBoundsException{
        return (TypeCar) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn());
    }
}

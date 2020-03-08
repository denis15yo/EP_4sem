package models;

import country.Country;
import tour.Tour;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ToursTableModel extends AbstractTableModel {
    private List<Tour> model;

    private Map<Country, ImageIcon> icons;
    private List<Boolean> checkBoxes;

    public ToursTableModel(List<Tour> model, Map<Country, ImageIcon> icons) {
        this.model = model;
        this.icons = icons;
        checkBoxes = new ArrayList<>();
        for(int i = 0; i < this.model.size(); ++i){
            checkBoxes.add(false);
        }
    }

    @Override
    public int getRowCount() {
        return model.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex){
            case 0 :
                return "Flag";
            case 1:
                return "Description";
            case 2:
                return "Price";
            case 3:
                return "Select";
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0 : return ImageIcon.class;
            case 1 : return String.class;
            case 2 : return int.class;
            case 3 : return Boolean.class;
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0 : return icons.get(model.get(rowIndex).getCountry());
            case 1 : return model.get(rowIndex).getDescription();
            case 2 : return model.get(rowIndex).getPrice();
            case 3 : return checkBoxes.get(rowIndex);
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(columnIndex == 3){
            checkBoxes.set(rowIndex, !checkBoxes.get(rowIndex));
        }
    }
}


package models;

import country.Country;
import tour.Tour;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToursTableModel extends AbstractTableModel {
    private List<Tour> tours;

    private Map<Country, ImageIcon> icons;
    private List<Boolean> checkBoxes;

    public ToursTableModel() {
        tours = new ArrayList<>();
        icons = new HashMap<>();
        checkBoxes = new ArrayList<>();
    }

    public ToursTableModel(List<Tour> tours, Map<Country, ImageIcon> icons) {
        this.tours = tours;
        this.icons = icons;
        checkBoxes = new ArrayList<>();
        for(int i = 0; i < this.tours.size(); ++i){
            checkBoxes.add(false);
        }
    }

    @Override
    public int getRowCount() {
        return tours.size() + 1;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex){
            case 0 :
                return "Страна";
            case 1:
                return "Описание";
            case 2:
                return "Цена";
            case 3:
                return "Выбрать";
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
        return columnIndex == 3 && rowIndex != getRowCount() - 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(rowIndex == getRowCount() - 1){
            if(columnIndex == 2){
                int sum = 0;
                for(int i = 0; i < tours.size(); ++i){
                    if(checkBoxes.get(i)){
                        sum += tours.get(i).getPrice();
                    }
                }
                return sum;
            }
            else{
                return null;
            }
        }
        switch (columnIndex){
            case 0 : return icons.get(tours.get(rowIndex).getCountry());
            case 1 : return tours.get(rowIndex).getDescription();
            case 2 : return tours.get(rowIndex).getPrice();
            case 3 : return checkBoxes.get(rowIndex);
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(columnIndex == 3){
            checkBoxes.set(rowIndex, !checkBoxes.get(rowIndex));
            fireTableDataChanged();
        }
    }

    public void addTour(Tour tour){
        tours.add(tour);
        checkBoxes.add(tours.size() - 1, false);
    }
}


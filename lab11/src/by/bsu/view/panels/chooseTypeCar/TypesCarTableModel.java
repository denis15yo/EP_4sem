package by.bsu.view.panels.chooseTypeCar;

import by.bsu.models.car.TypeCar;
import by.bsu.models.road.TypeRoad;

import javax.swing.table.AbstractTableModel;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TypesCarTableModel extends AbstractTableModel {
    private List<TypeCar> carList;
    private int rowCount;
    private int columnCount;

    public TypesCarTableModel() {
        this.carList = Arrays.stream(TypeCar.values()).collect(Collectors.toList());
        this.rowCount = (int) Math.sqrt(carList.size());
        if(rowCount * rowCount != carList.size()){
            ++rowCount;
        }
        this.columnCount = (carList.size() % rowCount == 0) ? carList.size() / rowCount : carList.size() / rowCount + 1;
    }

    public List<TypeCar> getCarList() {
        return carList;
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return carList.get(rowIndex * columnCount + columnIndex);
    }
}

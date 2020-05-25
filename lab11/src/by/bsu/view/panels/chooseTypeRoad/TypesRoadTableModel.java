package by.bsu.view.panels.chooseTypeRoad;

import by.bsu.models.road.TypeRoad;

import javax.swing.table.AbstractTableModel;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TypesRoadTableModel extends AbstractTableModel {
    private List<TypeRoad> roadList;
    private int rowCount;
    private int columnCount;

    public TypesRoadTableModel() {
        this.roadList = Arrays.stream(TypeRoad.values()).collect(Collectors.toList());
        this.rowCount = (int) Math.sqrt(roadList.size());
        if(rowCount * rowCount != roadList.size()){
            ++rowCount;
        }
        this.columnCount = (roadList.size() % rowCount == 0) ? roadList.size() / rowCount : roadList.size() / rowCount + 1;
    }

    public List<TypeRoad> getRoadList() {
        return roadList;
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
        return roadList.get(rowIndex * columnCount + columnIndex);
    }
}

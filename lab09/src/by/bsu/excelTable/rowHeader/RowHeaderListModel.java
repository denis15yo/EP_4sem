package by.bsu.excelTable.rowHeader;

import javax.swing.*;

public class RowHeaderListModel extends AbstractListModel<Integer> {
    private final int[] headers;

    public RowHeaderListModel(int rowCount) {
        headers = new int[rowCount];
        for(int i = 0; i < rowCount; ++i){
            headers[i] = i + 1;
        }
    }

    @Override
    public int getSize() {
        return headers.length;
    }

    @Override
    public Integer getElementAt(int index) {
        return headers[index];
    }
}

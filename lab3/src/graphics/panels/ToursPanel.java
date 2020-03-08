package graphics.panels;

import country.Country;
import models.ToursTableModel;
import tour.Tour;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.List;
import java.util.Map;

@SuppressWarnings("FieldCanBeLocal")
public class ToursPanel extends JPanel {
    private JTable table;
    private AbstractTableModel tableModel;

    private List<Tour> tours;
    private Map<Country, ImageIcon> icons;

    public ToursPanel(List<Tour> tours, Map<Country, ImageIcon> icons) {
        super(new BorderLayout());

        this.tours = tours;
        this.icons = icons;

        tableModel = new ToursTableModel(this.tours, this.icons);
        table = new JTable(tableModel);
        table.setRowHeight(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(500);
        table.getColumnModel().getColumn(2).setPreferredWidth(45);
        table.setGridColor(Color.BLACK);
        table.setRowSelectionAllowed(false);

        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);
    }
}

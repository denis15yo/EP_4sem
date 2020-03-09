package graphics.panels;

import country.Country;
import graphics.dialogs.TourAddDialog;
import models.ToursTableModel;
import tour.Tour;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

@SuppressWarnings("FieldCanBeLocal")
public class ToursPanel extends JPanel {
    JFrame owner;

    private JTable table;
    private ToursTableModel tableModel;

    private JButton addButton;

    private List<Tour> tours;
    private Map<Country, ImageIcon> icons;

    public ToursPanel(JFrame owner, List<Tour> tours, Map<Country, ImageIcon> icons) {
        super(new BorderLayout());

        this.owner = owner;
        this.tours = tours;
        this.icons = icons;

        addButton = new JButton("Добавить");

        tableModel = new ToursTableModel(this.tours, this.icons);
        table = new JTable(tableModel);
        table.setRowHeight(80);
        table.getColumnModel().getColumn(0).setPreferredWidth(45);
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        table.getColumnModel().getColumn(2).setPreferredWidth(35);
        table.setGridColor(Color.BLACK);
        table.setRowSelectionAllowed(false);
        for(int i = 0; i < table.getColumnCount() - 1; ++i){
            table.getColumnModel().getColumn(i).setCellRenderer(new CellRenderer());
        }

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON3) {
                    Point point = e.getPoint();
                    int row = table.rowAtPoint(point);
                    int column = table.columnAtPoint(point);

                    if (column == 3) {
                        return;
                    }

                    String message = "";
                    switch (column) {
                        case 0:
                            message = "Enter country:";
                            break;
                        case 1:
                            message = "Enter description:";
                            break;
                        case 2:
                            message = "Enter price:";
                            break;
                    }
                    String ans = JOptionPane.showInputDialog(null, message, "Edit", JOptionPane.PLAIN_MESSAGE);
                    if(ans == null){
                        return;
                    }
                    switch (column) {
                        case 0:
                            Country newCountry = new Country(ans);
                            if (icons.containsKey(newCountry)) {
                                tours.get(row).setCountry(new Country(ans));
                            }
                            else{
                                JOptionPane.showMessageDialog(ToursPanel.this,
                                        "Такой страны нет в базе данных.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                            }
                            break;
                        case 1:
                            tours.get(row).setDescription(ans);
                            break;
                        case 2:
                            try {
                                int newPrice = Integer.parseInt(ans);
                                if(newPrice < 0){
                                    throw new NumberFormatException();
                                }
                                tours.get(row).setPrice(newPrice);
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(ToursPanel.this,
                                        "Некорректная цена.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                            }
                            break;
                    }
                }
            }
        });

        addButton.addActionListener(e -> {
            TourAddDialog dlg = new TourAddDialog(owner);
            dlg.setVisible(true);
            Tour addedTour = dlg.getAddedTour();
            if(addedTour != null){
                if(icons.containsKey(addedTour.getCountry())){
                    tableModel.addTour(addedTour);
                    tableModel.fireTableDataChanged();
                }
                else{
                    JOptionPane.showMessageDialog(this,
                            "Такой страны нет в базе данных.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(addButton, BorderLayout.SOUTH);
    }

    static class CellRenderer extends DefaultTableCellRenderer{
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if(column == 0){
                setIcon((Icon) value);
                setText("");
            }

            setBackground(Color.WHITE);
            setForeground(Color.BLACK);
            return this;
        }
    }
}

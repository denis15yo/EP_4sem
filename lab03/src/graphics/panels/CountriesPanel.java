package graphics.panels;

import country.Country;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

@SuppressWarnings("FieldCanBeLocal")
public class CountriesPanel extends JPanel {
    private JList<Country> list;
    private JLabel label;

    private DefaultListModel<Country> listModel;

    private Map<Country, ImageIcon> icons;
    private Map<Country, String> capitals;

    public CountriesPanel(DefaultListModel<Country> listModel, Map<Country, ImageIcon> icons, Map<Country, String> capitals) {
        super(new BorderLayout());

        this.listModel = listModel;
        this.icons = icons;
        this.capitals = capitals;

        label = new JLabel("");

        list = new JList<>(this.listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Country selectedCountry = listModel.get(list.getSelectedIndex());
                label.setIcon(CountriesPanel.this.icons.get(selectedCountry));
                label.setText(selectedCountry.getName() + ", " + CountriesPanel.this.capitals.get(selectedCountry));
            }
        });
        list.setCellRenderer(new CountryListCellRenderer());

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(250, 300));

        add(scrollPane, BorderLayout.CENTER);
        add(label, BorderLayout.SOUTH);
    }

    class CountryListCellRenderer extends JLabel implements ListCellRenderer<Country>{
        public CountryListCellRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Country> list, Country value, int index, boolean isSelected, boolean cellHasFocus) {
            ImageIcon icon = icons.get(value);

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            setIcon(icon);
            setText(value.getName());
            setFont(list.getFont());

            return this;
        }
    }
}

package graphics.dialogs;

import country.Country;
import tour.Tour;

import javax.swing.*;

@SuppressWarnings("FieldCanBeLocal")
public class TourAddDialog extends JDialog {
    private JLabel countryLabel;
    private JLabel descriptionLabel;
    private JLabel priceLabel;

    private JTextField countryField;
    private JTextField descriptionField;
    private JTextField priceField;

    private JButton addButton;

    private Tour addedTour;
    public Tour getAddedTour() {
        return addedTour;
    }

    public TourAddDialog(JFrame owner) {
        super(owner, "Добавление", true);
        setResizable(false);

        countryLabel = new JLabel("Страна:");
        descriptionLabel = new JLabel("Описание:");
        priceLabel = new JLabel("Стоимость:");
        countryField = new JTextField(10);
        descriptionField = new JTextField(20);
        priceField = new JTextField(5);
        addButton = new JButton("Добавить");

        Box mainBox = Box.createVerticalBox();

        JPanel namePanel = new JPanel();
        namePanel.add(countryLabel);
        namePanel.add(countryField);

        JPanel descriptionPanel = new JPanel();
        descriptionPanel.add(descriptionLabel);
        descriptionPanel.add(descriptionField);

        JPanel costPanel = new JPanel();
        costPanel.add(priceLabel);
        costPanel.add(priceField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);

        mainBox.add(namePanel);
        mainBox.add(descriptionPanel);
        mainBox.add(costPanel);
        mainBox.add(buttonPanel);

        addButton.addActionListener(e -> {
            try{
                addedTour = new Tour(new Country(countryField.getText()), descriptionField.getText(), Integer.parseInt(priceField.getText()));
                if(addedTour.getPrice() < 0){
                    throw new NumberFormatException();
                }
                this.dispose();
            }
            catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(this, "Некорректные данные!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(mainBox);
        pack();
        setLocationRelativeTo(owner);
    }
}

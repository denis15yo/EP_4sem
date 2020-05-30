package by.bsu.view.panels;

import by.bsu.model.TicketsList;
import by.bsu.model.TicketsTableModel;
import by.bsu.view.tables.TicketsTable;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("FieldCanBeLocal")
public class TicketsPanel extends JPanel {
    private TicketsTableModel model;

    private final TicketsTable ticketsTable;
    private final JButton addButton, removeButton;

    public TicketsPanel(TicketsTableModel model) {
        super(new BorderLayout());

        this.model = model;
        ticketsTable = new TicketsTable(model);

        addButton = new JButton("Добавить");
        removeButton = new JButton("Удалить");

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(addButton);
        buttonsPanel.add(removeButton);

        removeButton.addActionListener(e -> {
            int row = ticketsTable.getSelectedRow();
            if(row == -1){
                return;
            }
            model.removeRow(row);
        });


        add(new JScrollPane(ticketsTable), BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    public TicketsList getTicketsList(){
        return model.getTicketsList();

    }
    public void setTicketsList(TicketsList ticketsList){
        model.setTicketsList(ticketsList);
    }
}

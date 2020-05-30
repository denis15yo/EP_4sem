package by.bsu.model;

import javax.swing.table.AbstractTableModel;

public class TicketsTableModel extends AbstractTableModel {
    private TicketsList ticketsList;

    public TicketsTableModel(TicketsList ticketsList) {
        this.ticketsList = ticketsList;
    }

    @Override
    public int getRowCount() {
        return ticketsList.size();
    }

    @Override
    public int getColumnCount() {
        return 11;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch(columnIndex){
            case 0: return "ID";
            case 1: return "Откуда";
            case 2: return "Куда";
            case 3: return "Отправка";
            case 4: return "Прибытие";
            case 5: return "Фамилия";
            case 6: return "Имя";
            case 7: return "№ Паспорта";
            case 8: return "Возраст";
            case 9: return "Место";
            case 10: return "Стоимость";
        }

        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Ticket ticket = ticketsList.get(rowIndex);
        switch(columnIndex){
            case 0: return ticket.getId();
            case 1: return ticket.getFlight().getFrom();
            case 2: return ticket.getFlight().getTo();
            case 3: return ticket.getFlight().getDepartureDate();
            case 4: return ticket.getFlight().getArrivalDate();
            case 5: return ticket.getPerson().getSurname();
            case 6: return ticket.getPerson().getName();
            case 7: return ticket.getPerson().getPassportID();
            case 8: return ticket.getPerson().getAge();
            case 9: return ticket.getPlaceNumber();
            case 10: return ticket.getCost();
        }

        return null;
    }

    public TicketsList getTicketsList() {
        return ticketsList;
    }

    public void setTicketsList(TicketsList ticketsList) {
        this.ticketsList = ticketsList;
        fireTableDataChanged();
    }

    public void removeRow(int row){
        ticketsList.remove(row);
        fireTableDataChanged();
    }
}

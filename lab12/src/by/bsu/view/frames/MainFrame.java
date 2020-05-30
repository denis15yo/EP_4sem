package by.bsu.view.frames;

import by.bsu.model.TicketsTableModel;
import by.bsu.util.Analyzer;
import by.bsu.util.FilesWork;
import by.bsu.util.Reader;
import by.bsu.util.Saver;
import by.bsu.view.panels.TicketsPanel;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class MainFrame extends JFrame {
    private final TicketsPanel ticketsPanel;

    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu calcMenu;
    private JMenuItem openMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem calcCountMenuItem;

    public MainFrame() throws ParserConfigurationException, SAXException, ParseException, IOException {
        super("Билеты");

        ticketsPanel = new TicketsPanel(new TicketsTableModel(Reader.readTicketsListFromXML(new File("tickets.xml"))));

        menuBar = new JMenuBar();
        fileMenu = new JMenu("Файл");
        calcMenu = new JMenu("Расчет");
        openMenuItem = new JMenuItem("Открыть");
        saveMenuItem = new JMenuItem("Сохранить");
        calcCountMenuItem = new JMenuItem("Количество билетов");

        menuBar.add(fileMenu);
        menuBar.add(calcMenu);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        calcMenu.add(calcCountMenuItem);

        openMenuItem.addActionListener(e -> {
            File file = FilesWork.showFileDialog(this, (dir, name) -> {
                return name.endsWith("xml") || name.endsWith("txt"); // todo binary format
            }, FileDialog.LOAD);
            if(file != null){
                try {
                    if(file.getName().endsWith("xml")){
                        ticketsPanel.setTicketsList(Reader.readTicketsListFromXML(file));
                    } else if(file.getName().endsWith("txt")){
                        ticketsPanel.setTicketsList(Reader.readTicketsListFromBinaryFile(file));
                    }
                } catch (IOException | ParserConfigurationException | SAXException | ParseException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Ошибка");
                }
            }
        });

        saveMenuItem.addActionListener(e -> {
            File file = FilesWork.showFileDialog(this, (dir, name)-> {
                return name.endsWith("xml") || name.endsWith("txt"); // todo binary format
            }, FileDialog.SAVE);
            if(file != null){
                try {
                    if(file.getName().endsWith("xml")){
                        Saver.saveToXML(ticketsPanel.getTicketsList(), file);
                    } else if(file.getName().endsWith("txt")){
                        Saver.saveToBinaryFormat(ticketsPanel.getTicketsList(), file);
                    }

                } catch (ParserConfigurationException | TransformerException | IOException parserConfigurationException) {
                    JOptionPane.showMessageDialog(this, "Ошибка сохранения", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        calcCountMenuItem.addActionListener(e -> {
            File file = FilesWork.showFileDialog(this, (dir, name) -> name.endsWith("xml"), FileDialog.LOAD);
            if(file != null){
                int ticketsCount = 0;
                try {
                    ticketsCount = Analyzer.getTicketsCount(file);
                    JOptionPane.showMessageDialog(this, "Количество билетов: " + ticketsCount);
                } catch (ParserConfigurationException | SAXException | IOException parserConfigurationException) {
                    JOptionPane.showMessageDialog(this, "Ошибка");
                }
            }
        });

        setJMenuBar(menuBar);
        add(ticketsPanel);

        setSize(1440, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}

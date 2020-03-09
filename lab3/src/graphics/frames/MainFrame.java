package graphics.frames;

import country.Country;
import graphics.panels.CountriesPanel;
import graphics.panels.ToursPanel;
import myUtil.Functions;
import tour.Tour;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
public class MainFrame extends JFrame {
    private JTabbedPane tabbedPane;
    private JPanel countriesPanel;
    private JPanel toursPanel;

    private DefaultListModel<Country> listModel;
    private Map<Country, ImageIcon> icons;
    private Map<Country, String> capitals;

    private List<Tour> tours;

    public MainFrame() throws HeadlessException {
        super("Travel Agency");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        try {
            initInfoAboutCountries();
        } catch (FileNotFoundException ignored) {}

        initTours();

        countriesPanel = new CountriesPanel(listModel, icons, capitals);
        toursPanel = new ToursPanel(this, tours, icons);

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Countries", countriesPanel);
        tabbedPane.addTab("Tours", toursPanel);
        add(tabbedPane);

        pack();
        setLocationRelativeTo(null);
    }

    private void initInfoAboutCountries() throws FileNotFoundException {
        listModel = new DefaultListModel<>();
        icons = new HashMap<>();
        capitals = new HashMap<>();

        Scanner sc = new Scanner(new File("countries.txt"));
        while(sc.hasNextLine()){
            String name = sc.next();
            String capital = sc.next();

            ImageIcon icon = new ImageIcon("flags/flag_" + name + ".png");
            Image image = icon.getImage();
            image = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            icon.setImage(image);

            Country c = new Country(Functions.format(name));
            listModel.addElement(c);

            icons.put(c, icon);
            capitals.put(c, Functions.format(capital));
        }
    }

    private void initTours(){
        tours = new ArrayList<>();
        tours.add(new Tour(new Country("Italy"), "<html>Всегда гарантированный выезд.<br>" +
                "Экскурсионный тур по Италии. Маршрут:<br> Вена-Флоренция-Сан-Джиминьяно-Рим-Падуя-Ватикан-Венеция-Брно.",195));
        tours.add(new Tour(new Country("Switzerland"), "<html>Швейцария без ночных переездов!<br>Люцерн, Цюрих, " +
                "подъем в Альпы,<br>Берн, Женева, Швейцарская Ривьера, Страсбург, Нюрнберг - все в 1 туре.</html>", 245));
        tours.add(new Tour(new Country("Spain"), "<html>Раннее бронирование до 1-го апреля.<br>Хит последних 5 лет. " +
                "Неделя в Испании+Париж+<br>Берлин+Барселона+Монако+Венеция+Вена.</html>", 395));
        tours.add(new Tour(new Country("Portugal"), "<html>Экскурсии по всей Европе+<br>неделя отдыха на юге Испании.</html>", 640));
        tours.add(new Tour(new Country("Hungary"), "<html>Новый год в Венгрии.<br></html>" +
                "<html>Автобусный тур Новый год в Будапеште за 135 евро.<br>Отель в Будапеште.</html>", 135));
    }
}

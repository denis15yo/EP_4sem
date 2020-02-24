import by.bsu.devices.Disk;
import by.bsu.music.Collection;
import by.bsu.music.Composition;
import by.bsu.myUtil.Reader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

@SuppressWarnings("FieldCanBeLocal")
public class Main {
    private static String menu = "1 - показать коллекцию\n" +
            "2 - записать на диск и воспроизвести\n" +
            "3 - подсчитать продолжительность\n" +
            "4 -  сортировка по стилю\n" +
            "5 - найти композицию по диапазону\n" +
            "0 - выход";

    public static void main(String[] args) {
        Collection<Composition> collection = new Collection<>();
        try {
            collection = Reader.readMusicalCollectionFromXML(new File("collection.xml"));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        int choice;
        Scanner sc = new Scanner(System.in);

        do{
            System.out.println(menu);
            choice = sc.nextInt();
            switch (choice){
                case 1:
                    collection.print();
                    break;
                case 2:
                    Disk disk = new Disk();
                    disk.write(collection);
                    disk.play();
                    break;
                case 3:
                    System.out.println("Продолжительность = " + collection.duration());
                    break;
                case 4:
                    collection.sortByStyle();
                    break;
                case 5:
                    int min, max;
                    System.out.print("min = ");
                    min = sc.nextInt();
                    System.out.print("max = ");
                    max = sc.nextInt();
                    Optional<Composition> res = collection.search(min, max);
                    res.ifPresent(System.out::println);
                    // otherwise not found
                    break;
            }
        } while(choice != 0);
    }
}

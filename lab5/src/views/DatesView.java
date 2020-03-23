package views;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import myUtil.RegularExpressions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@SuppressWarnings("FieldCanBeLocal")
public class DatesView extends HBox {
    private Label textLabel;
    private TextArea textArea;

    private Label datesLabel;
    private ListView<String> listView;
    private Button openButton;

    public DatesView() {
        super(20);

        textLabel = new Label("Текст");
        textArea = new TextArea("");
        datesLabel = new Label("Даты");
        listView = new ListView<>();
        openButton = new Button("Открыть");

        textArea.setPrefWidth(230);
        textArea.setWrapText(true);
        listView.setPrefWidth(90);

        textArea.textProperty().addListener(e -> {
            listView.getItems().clear();
            Pattern pattern = Pattern.compile(RegularExpressions.mapOfRegularExpressions.get("Дата"));
            Matcher matcher = pattern.matcher(textArea.getText());
            int start = 0;
            while (matcher.find(start)) {
                listView.getItems().add(matcher.group());
                start = matcher.end();
            }
        });

        openButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT", ".*\\.txt"));
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                try {
                    Stream<String> stringStream = Files.lines(Paths.get(file.getAbsolutePath()));
                    StringBuilder text = new StringBuilder("");
                    stringStream.forEachOrdered(s -> {
                        text.append(s).append("\n");
                    });
                    textArea.setText(text.toString());
                } catch (IOException ignored) {
                }
            }
        });

        VBox textBox = new VBox(5, textLabel, textArea);
        VBox datesBox = new VBox(5, datesLabel, listView);
        VBox vBox = new VBox(20, textBox, openButton);

        getChildren().addAll(vBox, datesBox);
    }
}

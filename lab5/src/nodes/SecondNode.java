package nodes;

import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import myUtil.RegExpr;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("FieldCanBeLocal")
public class SecondNode extends HBox {
    private TextArea textArea;
    private ListView<String> listView;

    public SecondNode() {
        super(20);

        textArea = new TextArea("");
        listView = new ListView<>();

//        textArea.setMaxHeight(150);
//        listView.setMaxHeight(150);
        textArea.setPrefWidth(230);
        textArea.setWrapText(true);
        listView.setPrefWidth(90);

        textArea.textProperty().addListener(e -> {
            listView.getItems().clear();
            Pattern pattern = Pattern.compile(RegExpr.regExpressions.get("Дата"));
            Matcher matcher = pattern.matcher(textArea.getText());
            int start = 0;
            while(matcher.find(start)){
                listView.getItems().add(matcher.group());
                start = matcher.end();
            }
        });

        getChildren().addAll(textArea, listView);
    }
}

package nodes;

import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;


@SuppressWarnings("FieldCanBeLocal")
public class SecondNode extends HBox {
    private TextArea textArea;
    private ListView<String> listView;

    public SecondNode() {
        super(20);

        textArea = new TextArea("TextArea");
        listView = new ListView<>();

        textArea.setMaxHeight(150);
        listView.setMaxHeight(150);
        textArea.setPrefWidth(150);
        listView.setPrefWidth(100);

        getChildren().addAll(textArea, listView);
    }
}

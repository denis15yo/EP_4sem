package by.bsu.views;

import by.bsu.KeywordManager;
import by.bsu.interfaces.Observer;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

@SuppressWarnings("FieldCanBeLocal")
public class CharLogView extends FlowPane implements Observer {
    private final Label logLabel;

    private final KeywordManager keywordManager;

    public CharLogView(KeywordManager keywordManager) {
        logLabel = new Label("");

        logLabel.setWrapText(true);
        logLabel.setPrefWidth(100);

        this.keywordManager = keywordManager;
        this.keywordManager.addObserver(this);

        getChildren().add(logLabel);
    }


    @Override
    public void update() {
        if(keywordManager.getLastKeyCode().isLetterKey() || keywordManager.getLastKeyCode().isDigitKey()){
            logLabel.setText(logLabel.getText() + keywordManager.getLastKeyCode().getChar());
        }
    }
}

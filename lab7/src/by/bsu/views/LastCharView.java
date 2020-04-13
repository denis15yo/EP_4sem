package by.bsu.views;

import by.bsu.KeywordManager;
import by.bsu.interfaces.Observer;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;

@SuppressWarnings("FieldCanBeLocal")
public class LastCharView extends FlowPane implements Observer {
    private final Label charLabel;

    private final KeywordManager keywordManager;

    public LastCharView(KeywordManager keywordManager) {
        charLabel = new Label("");
        charLabel.setFont(new Font(100));

        this.keywordManager = keywordManager;
        this.keywordManager.addObserver(this);

        getChildren().add(charLabel);
    }

    @Override
    public void update() {
        if(keywordManager.getLastKeyCode().isLetterKey() || keywordManager.getLastKeyCode().isDigitKey()){
            charLabel.setText(keywordManager.getLastKeyCode().getChar());
        }
    }
}

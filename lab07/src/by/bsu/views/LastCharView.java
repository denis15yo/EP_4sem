package by.bsu.views;

import by.bsu.KeywordManager;
import by.bsu.interfaces.Observable;
import by.bsu.interfaces.Observer;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;

@SuppressWarnings("FieldCanBeLocal")
public class LastCharView extends FlowPane implements Observer {
    private final Label charLabel;

    public LastCharView() {
        charLabel = new Label("");
        charLabel.setFont(new Font(100));

        getChildren().add(charLabel);
    }

    @Override
    public void update(Observable observable) {
        if(observable instanceof KeywordManager){
            KeywordManager keywordManager = (KeywordManager) observable;
            if(keywordManager.getLastKeyCode().isLetterKey() || keywordManager.getLastKeyCode().isDigitKey()){
                charLabel.setText(keywordManager.getLastKeyCode().getChar());
            }
        }
    }
}

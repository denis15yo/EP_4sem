package by.bsu.views;

import by.bsu.KeywordManager;
import by.bsu.interfaces.Observable;
import by.bsu.interfaces.Observer;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

@SuppressWarnings("FieldCanBeLocal")
public class CharLogView extends FlowPane implements Observer {
    private final Label logLabel;

    public CharLogView() {
        logLabel = new Label("");
        logLabel.setWrapText(true);
        logLabel.setPrefWidth(100);

        getChildren().add(logLabel);
    }


    @Override
    public void update(Observable observable) {
        if(observable instanceof KeywordManager){
            KeywordManager keywordManager = (KeywordManager)observable;
            if(keywordManager.getLastKeyCode().isLetterKey() || keywordManager.getLastKeyCode().isDigitKey()){
                logLabel.setText(logLabel.getText() + keywordManager.getLastKeyCode().getChar());
            }
        }
    }
}

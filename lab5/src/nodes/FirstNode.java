package nodes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import myUtil.RegExpr;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SuppressWarnings("FieldCanBeLocal")
public class FirstNode extends HBox {
    private ObservableList<String> observableList;

    private Image redCircle, greenCircle;
    private ImageView imageView;
    private TextField textField;
    private ComboBox<String> comboBox;

    private MyHandler handler;

    public FirstNode() {
        super(10);

        observableList = FXCollections.observableArrayList(
                "Натуральное число", "Целое число", "Число с плавающей запятой", "Дата", "Время", "e-mail");
        textField = new TextField();
        comboBox = new ComboBox<>(observableList);

        try{
            redCircle = new Image(new FileInputStream("images/red.png"), 20, 20, true, true);
            greenCircle = new Image(new FileInputStream("images/green.png"), 20, 20, true, true);
        } catch (FileNotFoundException ignored){};

        textField.setMaxWidth(100);
        comboBox.setMaxWidth(250);
        comboBox.setValue(observableList.get(0));
        imageView = new ImageView(redCircle);

        handler = new MyHandler();
        textField.textProperty().addListener(e -> {
            handler.handle(new ActionEvent());
        });
        textField.setOnAction(handler);
        comboBox.setOnAction(handler);

        getChildren().addAll(imageView, textField, comboBox);
    }

    class MyHandler implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            if(textField.getText().matches(RegExpr.regExpressions.get(comboBox.getValue()))){
                imageView.setImage(greenCircle);
            }
            else{
                imageView.setImage(redCircle);
            }
        }
    }
}

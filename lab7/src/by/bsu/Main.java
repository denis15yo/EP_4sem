package by.bsu;

import by.bsu.views.CharLogView;
import by.bsu.views.LastCharView;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;


@SuppressWarnings("FieldCanBeLocal")
public class Main extends Application {
    private KeywordManager keywordManager;

    private CharLogView charLogView;
    private LastCharView lastCharView;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.centerOnScreen();
        stage.setTitle("Pattern \"Observer\"");

        keywordManager = new KeywordManager();

        charLogView = new CharLogView(keywordManager);
        charLogView.setAlignment(Pos.CENTER);

        lastCharView = new LastCharView(keywordManager);
        lastCharView.setAlignment(Pos.CENTER);

        GridPane gridPane = new GridPane();
        gridPane.add(lastCharView, 0, 0);
        gridPane.add(charLogView, 1, 0);

        gridPane.setGridLinesVisible(true);

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setVgrow(Priority.ALWAYS);
        gridPane.getRowConstraints().add(rowConstraints);

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(Priority.ALWAYS);
        gridPane.getColumnConstraints().addAll(columnConstraints, columnConstraints);

        Scene scene = new Scene(new BorderPane(gridPane));
        scene.setOnKeyPressed(e -> {
            keywordManager.setLastKeyCode(e.getCode());
        });

        stage.setScene(scene);

        stage.show();
    }
}

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import views.ValidateView;
import views.DatesView;


@SuppressWarnings("FieldCanBeLocal")
public class Main extends Application {
    private TabPane tabPane;

    private ValidateView validateView;
    private DatesView datesView;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.centerOnScreen();
        stage.setTitle("Регулярные выражения");

        validateView = new ValidateView();
        validateView.setAlignment(Pos.CENTER);

        datesView = new DatesView();
        datesView.setAlignment(Pos.CENTER);

        tabPane = new TabPane(new Tab("Валидация", validateView), new Tab("Даты в тексте", datesView));

        stage.setScene(new Scene(tabPane, 400, 400));

        stage.show();
    }
}

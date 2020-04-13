
import com.sothawo.mapjfx.MapView;
import com.sothawo.mapjfx.Projection;
import controllers.MainController;
import controllers.MapController;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This is the main class for the application.
 */
public class Main extends Application {

    @FXML
    private GridPane gpMainWindow;

    @FXML
    private HBox loginBox;

    private MainController mainController;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        mainController = new MainController(); // not used at the moment.

        FXMLLoader loader = new FXMLLoader();
        String fxmlDocPath = "/fxml/FXMLMain.fxml";
        gpMainWindow = loader.load(getClass().getResourceAsStream(fxmlDocPath));
        gpMainWindow.add(map(), 0, gpMainWindow.getChildren().size(), 2, 2);
        
        Scene scene = new Scene(gpMainWindow);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Travel agency");
        primaryStage.show();
    }

    private MapView map() {
        Projection projection = getParameters()
                .getUnnamed().contains("wgs84")
                ? Projection.WGS_84 : Projection.WEB_MERCATOR;

        return new MapController("/files/features.txt").mapBox(projection);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

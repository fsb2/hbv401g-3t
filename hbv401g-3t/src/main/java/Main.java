import com.sothawo.mapjfx.MapView;

import controllers.MapController;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * This is the main class for the application.
 */
public class Main extends Application {

    @FXML
    private GridPane gpMainWindow;

    private MapController mapController;
    private MapView mapView;
    
    @Override
    public void start(final Stage primaryStage) throws Exception {
        mapController = new MapController("/files/features.txt");
        mapView = mapController.mapBox();
        
        FXMLLoader loader = new FXMLLoader();
        String fxmlDocPath = "/fxml/FXMLMain.fxml";
        gpMainWindow = loader.load(getClass().getResourceAsStream(fxmlDocPath));
        gpMainWindow.add(mapView, 0, gpMainWindow.getChildren().size(), 2, 2);
        
        Scene scene = new Scene(gpMainWindow);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Travel agency");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package hbv401.t3;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sothawo.mapjfx.Projection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * JavaFX App
 */
public class App extends Application {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    @Override
    public void start(final Stage primaryStage) throws Exception{
        final String fxmlFile = "/fxml/DemoApp.fxml";

        //initialize mapfeatures

        final File text = new File("src/main/resources/features.txt");
        final Scanner input = new Scanner(text);
        input.useDelimiter(";|\n");
        List<MapFeature> mapFeatures = new ArrayList<>();
        while (input.hasNext()) {
            final String name = input.next();
            final String category = input.next();
            final double x = input.nextDouble();
            final double y = input.nextDouble();
            mapFeatures.add(new MapFeature(name, category, x, y));
        }
        input.close();

        //initialize fxml
        final FXMLLoader fxmlLoader = new FXMLLoader();
        final Parent rootNode = fxmlLoader.load(getClass().getResourceAsStream(fxmlFile));

        ///
        final MapController controller = fxmlLoader.getController();
        final Projection projection = getParameters().getUnnamed().contains("wgs84")
                ? Projection.WGS_84 : Projection.WEB_MERCATOR;
        controller.initMapAndControls(projection,mapFeatures);

        final Scene scene = new Scene(rootNode);

        primaryStage.setTitle("HBV401 T3");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}

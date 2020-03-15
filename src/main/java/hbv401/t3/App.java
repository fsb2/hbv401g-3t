package hbv401.t3;

import com.sothawo.mapjfx.Projection;
import java.io.File;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

/**
 * JavaFX App
 */
public class App extends Application {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    @Override
    public void start(final Stage primaryStage) throws Exception{
        // logger.info("starting DemoApp");
        final String fxmlFile = "/fxml/DemoApp.fxml";
        // logger.debug("loading fxml file {}", fxmlFile);
        // logger.trace("stage loaded");

        //initialize mapfeatures

        final File text = new File("src/main/resources/features.txt");
        final Scanner input = new Scanner(text);
        input.useDelimiter(";|\n");
        //Local locale = Locale.IsoCountryCode(ISL);
        //input.useLocale(locale);
        List<MapFeature> mapFeatures = new ArrayList<>();

        while (input.hasNext()) {

            final String name = input.next();
            final String category = input.next();
            final double x = input.nextDouble();
            final double y = input.nextDouble();
            System.out.println("********** " + name); 
            mapFeatures.add(new MapFeature(name, category, x, y));
        }
        input.close();
        // System.out.println(mapFeatures[0]); 



        final FXMLLoader fxmlLoader = new FXMLLoader();
        final Parent rootNode = fxmlLoader.load(getClass().getResourceAsStream(fxmlFile));

        ///
        final MapController controller = fxmlLoader.getController();
        final Projection projection = getParameters().getUnnamed().contains("wgs84")
                ? Projection.WGS_84 : Projection.WEB_MERCATOR;
        // controller.
        controller.initMapAndControls(projection,mapFeatures);

        final Scene scene = new Scene(rootNode);
        // logger.trace("scene created");

        primaryStage.setTitle("HBV401 T3");
        primaryStage.setScene(scene);
        // logger.trace("showing scene");
        primaryStage.show();

        // logger.debug("application start method finished.");

    }

    public static void main(final String[] args) throws FileNotFoundException{

       // Scanner input = new Scanner(new File("features.txt"));
        //input.useDelimiter(";|\n");
        // System.out.println(input);
        launch();
    }

}

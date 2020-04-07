import com.sothawo.mapjfx.MapView;
import com.sothawo.mapjfx.Projection;
import controllers.MainController;
import controllers.MapController;

/* Model imports */
import controllers.BookingMananger;
import controllers.PersonMananger;
import datastructures.Person;
/* ------------------------------------ */

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * This is the main class for the application.
 */

public class Main extends Application {

    @FXML
    private VBox mainWindow;
    
    @FXML
    private HBox loginBox;
    
    private MainController mainController;
    
    @Override
    public void start(final Stage primaryStage) throws Exception{
        mainController = new MainController(); // not used at the moment.
        
        FXMLLoader loader = new FXMLLoader();
        String fxmlDocPath = "/fxml/FXMLMain.fxml";
        mainWindow = loader.load(getClass().getResourceAsStream(fxmlDocPath));
        
        Scene scene = new Scene(mainWindow);
       
        // Adding children to the main window.
        mainWindow.getChildren().add(map());
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Travel agency");
        primaryStage.show();
        
        PersonDemo();
    }
    
    private MapView map(){
        Projection projection = getParameters()
                .getUnnamed().contains("wgs84")
        ? Projection.WGS_84 : Projection.WEB_MERCATOR;

        return new MapController("/files/features.txt").mapBox(projection);
    }
    
    // Testing how to access and use a class
    // from the flight module.
    private void PersonDemo(){
        PersonMananger pm = new PersonMananger();
        Person p = pm.create("Hjortur", "147225825", "hss44@hi.is", "4575632");
        
        BookingMananger bm = new BookingMananger();
        bm.create('H', 1, p, null);
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}

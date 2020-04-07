package controllers;

import com.sothawo.mapjfx.Configuration;
import com.sothawo.mapjfx.MapView;
import com.sothawo.mapjfx.Projection;
import entities.MapEntity;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.util.List;
import java.util.Scanner;
import javafx.fxml.FXMLLoader;

/**
 * This is the controller for the map.
 */

public class MapController {

    @FXML
    private MapView locationMap;

    @FXML
    public TextField tripFrom;

    @FXML
    public TextField tripTo;

    final private List<MapEntity> mapFeatures;

    public MapController() {
        mapFeatures = new ArrayList<>();
    }

    public MapController(String mapFeature) {
        mapFeatures = new ArrayList<>();

        initFeature(mapFeature);
        initialize();
    }
    
    public void setFeatureAndInitMap(String mapFeature){
        initFeature(mapFeature);
        initialize();
    }

    private void initFeature(String mapFeature) {
        try (InputStreamReader inputReader = new InputStreamReader(getClass()
                    .getResourceAsStream(mapFeature));
                Scanner input = new Scanner(inputReader)) {
                
                input.useDelimiter(";|\n");
                
                while (input.hasNext()) {
                    String name = input.next();
                    String category = input.next();
                    double x = input.nextDouble();
                    double y = input.nextDouble();
                    mapFeatures.add(new MapEntity(name, category, x, y));
                }
        
                input.close();
                inputReader.close();
                
        } catch (IOException ex) {
            System.out.println(ex.getCause());
        }
    }

    private void initialize() {    
        try {
            String fxmlMap = "/fxml/FXMLMap.fxml";
            locationMap = FXMLLoader.load(getClass().getResource(fxmlMap));
            locationMap.initialize();
        } catch (IOException ex) {
            System.out.println(ex.getCause());
        }
     
    }

    // This returns the actual map.
    public MapView mapBox(Projection projection) {
        locationMap.initialize(Configuration.builder()
            .projection(projection)
            .build());
        
        return locationMap;
    }

}

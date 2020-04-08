package controllers;

import com.sothawo.mapjfx.*;
import com.sothawo.mapjfx.event.MapLabelEvent;
import com.sothawo.mapjfx.Projection;

import entities.MapEntity;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.fxml.FXMLLoader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private Map<String, Marker> features = new HashMap<>();
    
    public MapController() {
        mapFeatures = new ArrayList<>();
    }

    public MapController(String mapFeature) {
        mapFeatures = new ArrayList<>();

        initFeature(mapFeature);
        //initMapAndControls();
        initialize();
    }
    
    public void setFeatureAndInitMap(String mapFeature){
     //   initFeature(mapFeature);
        initialize();
    }

    private void initFeature(String mapFeature) {
        try (InputStreamReader inputReader = new InputStreamReader(getClass()
                    .getResourceAsStream(mapFeature));
                Scanner input = new Scanner(inputReader)) {
                
                input.useDelimiter(";|\n");
                
                while (input.hasNext()) {
                    String name = input.next();
                    System.out.print(name);
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
        locationMap.setCustomMapviewCssURL(getClass().getResource("/custom_mapview.css"));
                locationMap.initializedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    afterMapIsInitialized();
                }   
        });
        
        
        setupEventHandlers();

        locationMap.initialize(Configuration.builder()
            .projection(projection)
            .build());
        
        return locationMap;
    }
  private void setupEventHandlers() {
        locationMap.addEventHandler(MapLabelEvent.MAPLABEL_CLICKED, event -> {
                event.consume();
                String orig = event.getMapLabel().getText();
                tripFrom.setText(orig);
            });
            locationMap.addEventHandler(MapLabelEvent.MAPLABEL_RIGHTCLICKED, event -> {
                event.consume();
                String dest = event.getMapLabel().getText();
                tripTo.setText(dest);
            });
    }

    private void afterMapIsInitialized(){
        
        for (MapEntity mf : mapFeatures) {
//            if (Arrays.asList(profileFeatures).contains(mf.getCategory())){
                    final String str = mf.getCategory();
                    final String label = "/".concat(str).concat(".png");
                    final Coordinate cord = new Coordinate(mf.getCoordX(), mf.getCoordY());
                    final Marker marker = new Marker(getClass().getResource(label), -20, -20).setPosition(cord).setVisible(true);
                    marker.attachLabel(new MapLabel(mf.getName(),20,20));
                    locationMap.addMarker(marker);
                    features.put(marker.getId(), marker);
//            }
        }
        locationMap.setZoom(6);
        //Island
        locationMap.setCenter(new Coordinate(65.178, -17.919));
    }
}

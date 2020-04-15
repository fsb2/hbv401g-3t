package controllers;

import com.sothawo.mapjfx.*;
import com.sothawo.mapjfx.Projection;

import entities.MapEntity;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javafx.fxml.FXML;
import java.util.Scanner;
import javafx.fxml.FXMLLoader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is the controller for the map.
 */
public class MapController {

    @FXML
    private MapView locationMap;

    final private List<MapEntity> mapFeatures;

    final private Map<String, Marker> features;
    
    public MapController() {
        mapFeatures = new ArrayList<>();
        features = new HashMap<>();
    }

    public MapController(String mapFeature) {
        mapFeatures = new ArrayList<>();
        features = new HashMap<>();

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
                int area = input.nextInt();
                mapFeatures.add(new MapEntity(name, category, x, y, area));
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
        } catch (IOException ex) {
            System.out.println(ex.getCause());
        }

        locationMap.initializedProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue) {
                        afterMapIsInitialized();
                    }
                });

        locationMap.initialize(Configuration.builder()
                .projection(Projection.WEB_MERCATOR)
                .build());
    }

    // This returns the actual map.
    public MapView mapBox() {
        return locationMap;
    }

    // Post processing for the map.
    // Here, the icons and labels are added.
    private void afterMapIsInitialized() {
        mapFeatures.stream().map((mf) -> {
            String str = mf.getCategory();
            String label = "/pictures/" + str + ".png";
            Coordinate cord = new Coordinate(mf.getCoordX(),
                    mf.getCoordY());

            Marker marker = new Marker(getClass().getResource(label),
                    -20, -20).setPosition(cord).setVisible(true);           
            
            marker.attachLabel(new MapLabel(mf.getName(), 20, 20));
            return marker;
        }).map((marker) -> {
            locationMap.addMarker(marker);
            return marker;
        }).forEachOrdered((marker) -> {
            features.put(marker.getId(), marker);
        });

        locationMap.setZoom(6);

        //Island
        locationMap.setCenter(new Coordinate(65.178, -17.919));
    }
}

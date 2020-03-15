/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbv401.t3;
import com.sothawo.mapjfx.*;
import com.sothawo.mapjfx.event.MapLabelEvent;
import com.sothawo.mapjfx.event.MapViewEvent;
import com.sothawo.mapjfx.event.MarkerEvent;
import com.sothawo.mapjfx.offline.OfflineCache;
import javafx.animation.Transition;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller for the FXML defined code.
 *
 * @author P.J. Meisch (pj.meisch@sothawo.com).
 */
public class MapController {

    private Map<String, Marker> markersCreatedFromFeatures = new HashMap<>();

    // private static final Logger logger = LoggerFactory.getLogger(MapController.class);
    private static final Coordinate coIsland = new Coordinate(65.178, -17.919);

    @FXML
    private MapView mapView;

    @FXML
    private TextField origin;

    @FXML
    private TextField destination;

    public MapController() {

    }

    public void initMapAndControls(Projection projection, List<MapFeature> mapFeatures) {


        mapView.setCustomMapviewCssURL(getClass().getResource("/custom_mapview.css"));
        mapView.initializedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                afterMapIsInitialized(mapFeatures);
            }
        });
        setupEventHandlers();
        mapView.initialize(Configuration.builder()
            .projection(projection)
                         //   .showZoomControls(false)
            .build());

    }
    private void setupEventHandlers() {
        mapView.addEventHandler(MapLabelEvent.MAPLABEL_CLICKED, event -> {
                event.consume();
                String orig = event.getMapLabel().getText();
                origin.setText(orig);
            });
            mapView.addEventHandler(MapLabelEvent.MAPLABEL_RIGHTCLICKED, event -> {
                event.consume();
                String dest = event.getMapLabel().getText();
                destination.setText(dest);
            });
    }
 
    public void afterMapIsInitialized(List<MapFeature> mapFeatures){
        for (MapFeature mf : mapFeatures) {
            final String str = mf.getCategory(); 
            final String label = "/".concat(str).concat(".png");
            final Coordinate cord = new Coordinate(mf.getCoordx(), mf.getCoordy());
            final Marker marker = new Marker(getClass().getResource(label), -20, -20).setPosition(cord).setVisible(true);
            marker.attachLabel(new MapLabel(mf.getName(),20,20));
            mapView.addMarker(marker);
            markersCreatedFromFeatures.put(marker.getId(), marker);
        }
        mapView.setZoom(6);
        mapView.setCenter(coIsland);
    }

}

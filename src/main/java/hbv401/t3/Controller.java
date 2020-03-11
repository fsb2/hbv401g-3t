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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Controller for the FXML defined code.
 *
 * @author P.J. Meisch (pj.meisch@sothawo.com).
 */
public class Controller {

        private static final Logger logger = LoggerFactory.getLogger(Controller.class);
    private static final Coordinate coIsland = new Coordinate(65.178, -17.919);
    private static final Coordinate coordGeysir = new Coordinate(64.3271, -20.1213);
    //66.0504/-17.3529 hvalur
    private static final Coordinate coordHvalur = new Coordinate(66.0504, -17.3529);
    //65.6243/-24.0244 fuglar
    private static final Coordinate coordFuglar = new Coordinate(65.6243, -24.0244);
    //63.641/-19.611 gos
    private static final Coordinate coordGos = new Coordinate(63.641, -19.611);
    //64.0674/-16.2124 jokull
    private static final Coordinate coordJokull = new Coordinate(64.0674, -16.2124);
    //64.2634/-21.1391 stadir
    private static final Coordinate coordStadir = new Coordinate(64.2534, -21.1391);
    //64.6156/-21.6788 viking
    private static final Coordinate coordViking = new Coordinate(64.6156,-21.6788);
    //64.3288/-20.1184 foss
    private static final Coordinate coordFoss = new Coordinate(64.3288,-20.1184);
    //63.9856/-22.6236
    private static final Coordinate coordFlugvollur = new Coordinate(63.9856,-22.6236);

    @FXML
    private MapView mapView;

    @FXML
    private TextField origin;

    @FXML
    private TextField destination;

    @FXML
    private Label labelCenter;

    @FXML
    private Label labelExtent;

    @FXML
    private Label labelZoom;

    @FXML
    private Label labelEvent;

    private final Marker markerGeysir;
    private final Marker markerHvalur;
    private final Marker markerFuglar;
    private final Marker markerGos;
    private final Marker markerJokull;
    private final Marker markerViking;
    private final Marker markerStadir;
    private final Marker markerFoss;
    private final Marker markerFlugvollur;

    private final MapLabel labelGeysir;
    private final MapLabel labelHvalur;
    private final MapLabel labelFuglar;
    private final MapLabel labelGos;
    private final MapLabel labelJokull;
    private final MapLabel labelViking;
    private final MapLabel labelStadir;
    private final MapLabel labelFoss;
    private final MapLabel labelFlugvollur;

    public Controller() {

        int lblX = 5;
        int lblY = 07;
        markerGeysir = new Marker(getClass().getResource("/geysir.png"), -20, -20). setPosition(coordGeysir).setVisible(true);
        markerHvalur = new Marker(getClass().getResource("/hvalur.png"), -20, -20). setPosition(coordHvalur).setVisible(true);
        markerFuglar = new Marker(getClass().getResource("/fuglar.png"), -20, -20). setPosition(coordFuglar).setVisible(true);
        markerGos = new Marker(getClass().getResource("/gos.png"), -20, -20). setPosition(coordGos).setVisible(true);
        markerJokull = new Marker(getClass().getResource("/jokull.png"), -20, -20). setPosition(coordJokull).setVisible(true);
        markerViking = new Marker(getClass().getResource("/viking.png"), -20, -20). setPosition(coordViking).setVisible(true);
        markerStadir = new Marker(getClass().getResource("/stadir.png"), -20, -20). setPosition(coordStadir).setVisible(true);
        markerFoss = new Marker(getClass().getResource("/foss.png"), -20, -20). setPosition(coordFoss).setVisible(true);
        markerFlugvollur = new Marker(getClass().getResource("/flugvollur.png"), -20, -20). setPosition(coordFlugvollur).setVisible(true);
        labelGeysir = new MapLabel("Geysir", lblX, lblY);
        labelHvalur = new MapLabel("Whalewatching", lblX, lblY);
        labelFuglar = new MapLabel("Puffins", lblX, lblY);
        labelGos = new MapLabel("Eyjafjallajökull", lblX, lblY);
        labelJokull = new MapLabel("Jökulsáarlón", lblX, lblY);
        labelViking = new MapLabel("Snorri Sturluson", lblX, lblY);
        labelStadir = new MapLabel("Þingvellir", lblX, lblY);
        labelFoss = new MapLabel("Gullfoss", lblX, lblY);
        labelFlugvollur = new MapLabel("Airport", lblX, lblY);
        markerGeysir.attachLabel(labelGeysir);
        markerHvalur.attachLabel(labelHvalur);
        markerFuglar.attachLabel(labelFuglar);
        markerGos.attachLabel(labelGos);
        markerJokull.attachLabel(labelJokull);
        markerViking.attachLabel(labelViking);
        markerStadir.attachLabel(labelStadir);
        markerFoss.attachLabel(labelFoss);
        markerFlugvollur.attachLabel(labelFlugvollur);
    }

    public void initMapAndControls(Projection projection) {

        final OfflineCache offlineCache = mapView.getOfflineCache();
        final String cacheDir = System.getProperty("java.io.tmpdir") + "/mapjfx-cache";

        mapView.setCustomMapviewCssURL(getClass().getResource("/custom_mapview.css"));
        logger.trace("begin initialize");
        // watch the MapView's initialized property to finish initialization
        mapView.initializedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                afterMapIsInitialized();
            }
        });
        setupEventHandlers();
        // labelZoom.textProperty().bind(Bindings.format("zoom: %.0f", mapView.zoomProperty()));
        // labelCenter.textProperty().bind(Bindings.format("center: %s", mapView.centerProperty()));
        logger.trace("start map initialization");
        mapView.initialize(Configuration.builder()
            .projection(projection)
                           //   .showZoomControls(false)
            .build());

        logger.debug("initialization finished");
    }
    private void setupEventHandlers() {
   
        // add an event handler for singleclicks, set the click marker to the new position when it's visible
        mapView.addEventHandler(MapViewEvent.MAP_CLICKED, event -> {
                event.consume();
                final Coordinate newPosition = event.getCoordinate().normalize();
                labelEvent.setText("Event: map clicked at: " + newPosition);
            });
            // mapView.addEventHandler(MapViewEvent.MAP_BOUNDING_EXTENT, event -> {
            //     event.consume();
            //     labelExtent.setText(event.getExtent().toString());
            // });

            // mapView.addEventHandler(MapViewEvent.MAP_EXTENT, event -> {
            //     event.consume();
            //     mapView.setExtent(event.getExtent());
            // });
        mapView.addEventHandler(MapViewEvent.MAP_RIGHTCLICKED, event -> {
                event.consume();
                labelEvent.setText("Event: map right clicked at: " + event.getCoordinate());
            });
        mapView.addEventHandler(MapLabelEvent.MAPLABEL_CLICKED, event -> {
                event.consume();
                String orig = event.getMapLabel().getText();
                origin.setText(orig);
                // labelEvent.setText("Event: marker clicked: " + event.getMarker().getId());
            });
            mapView.addEventHandler(MapLabelEvent.MAPLABEL_RIGHTCLICKED, event -> {
                event.consume();
                // labelEvent.setText("Event: marker right clicked: " + event.getMarker().getId());
                String dest = event.getMapLabel().getText();
                destination.setText(dest);
            });

            // mapView.addEventHandler(MapViewEvent.MAP_POINTER_MOVED, event -> {
            //           logger.debug("pointer moved to " + event.getCoordinate());
            // });
    }

    private void afterMapIsInitialized() {
        mapView.setZoom(6);
        mapView.setCenter(coIsland);
        mapView.addMarker(markerGeysir);
        mapView.addMarker(markerHvalur);
        mapView.addMarker(markerFuglar);
        mapView.addMarker(markerGos);
        mapView.addMarker(markerJokull);
        mapView.addMarker(markerStadir);
        mapView.addMarker(markerViking);
        mapView.addMarker(markerFoss);
        mapView.addMarker(markerFlugvollur);


    }

}

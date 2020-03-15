package hbv401.t3;
import com.sothawo.mapjfx.*;
import com.sothawo.mapjfx.event.MapLabelEvent;
// import com.sothawo.mapjfx.event.MapViewEvent;
// import com.sothawo.mapjfx.event.MarkerEvent;
// import com.sothawo.mapjfx.offline.OfflineCache;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapController {

    private Map<String, Marker> features = new HashMap<>();

    // private static final Logger logger = LoggerFactory.getLogger(MapController.class);
    // private static final Coordinate coIsland = new Coordinate(65.178, -17.919);

    @FXML
    private MapView mapView;

    @FXML
    public TextField tripFrom;

    @FXML
    public TextField tripTo;

    //public String[] profileFeatures = { "foss", "gos" };
    public String[] profileFeatures;

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
                tripFrom.setText(orig);
            });
            mapView.addEventHandler(MapLabelEvent.MAPLABEL_RIGHTCLICKED, event -> {
                event.consume();
                String dest = event.getMapLabel().getText();
                tripTo.setText(dest);
            });
    }

    private void afterMapIsInitialized(List<MapFeature> mapFeatures){
        
        for (MapFeature mf : mapFeatures) {
            if (Arrays.asList(profileFeatures).contains(mf.getCategory())){
                    final String str = mf.getCategory();
                    final String label = "/".concat(str).concat(".png");
                    final Coordinate cord = new Coordinate(mf.getCoordx(), mf.getCoordy());
                    final Marker marker = new Marker(getClass().getResource(label), -20, -20).setPosition(cord).setVisible(true);
                    marker.attachLabel(new MapLabel(mf.getName(),20,20));
                    mapView.addMarker(marker);
                    features.put(marker.getId(), marker);
            }
        }
        mapView.setZoom(6);
        //Island
        mapView.setCenter(new Coordinate(65.178, -17.919));
    }

}

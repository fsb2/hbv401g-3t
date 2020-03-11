module hbv401.t3 {
    requires com.sothawo.mapjfx;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.slf4j;
    opens hbv401.t3 to javafx.fxml;
    exports hbv401.t3;
}
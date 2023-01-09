module com.sergiu.heapupboxgame {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens com.sergiu.heapupboxgame to javafx.fxml;
    exports com.sergiu.heapupboxgame;
}
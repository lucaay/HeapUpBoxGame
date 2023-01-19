package com.sergiu.heapupboxgame.Builder;

import com.sergiu.heapupboxgame.AudioController;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.Test;

import java.net.URISyntaxException;

import static org.testng.AssertJUnit.assertEquals;

public class BuildLevelTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Test
    public void start(Stage primaryStage) throws URISyntaxException {
        String platformImagePath = "/com/sergiu/heapupboxgame/level_items/platforms/platform_1.png";
        int numberOfBoxes = 3;
        String boxImagePath = "/com/sergiu/heapupboxgame/level_items/boxes/box_4.png";
        Label timerLabel = new Label();
        Pane gameWonPane = new Pane();
        Timeline timeline = new Timeline();
        AudioController gameWonSound = new AudioController("src/main/resources/com/sergiu/heapupboxgame/sounds/confirmation_001.wav");
        BuildLevel level = new BuildLevel(platformImagePath, numberOfBoxes, boxImagePath, timerLabel, gameWonPane, timeline, gameWonSound);

        AnchorPane mainLevelPane = new AnchorPane();
        level.createLevel(mainLevelPane);


        assertEquals(5, mainLevelPane.getChildren().size()); // if 5 is replaced with 6, the test fails
        assertEquals("platform", level.platformImageView.getStyleClass().get(1)); // if "platform" is replaced with "platform1", the test fails
        assertEquals("dottedLine", level.dottedLine.getStyleClass().get(1)); // if "dottedLine" is replaced with "dottedLine1", the test fails
        assertEquals(3, mainLevelPane.getChildren().stream().filter(n -> n instanceof ImageView && ((ImageView)n).getStyleClass().contains("box")).count()); // if 3 is replaced with 4, the test fails

        primaryStage.setScene(new Scene(mainLevelPane));
        primaryStage.show();
    }
}

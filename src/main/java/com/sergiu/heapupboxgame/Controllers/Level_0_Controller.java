package com.sergiu.heapupboxgame.Controllers;

import com.sergiu.heapupboxgame.Builder.BuildLevel;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.net.URISyntaxException;

public class Level_0_Controller extends LevelController {
    //build level here and specific methods for this level

    @FXML
    private AnchorPane mainLevelPane;

    private final String platformImagePath = "/com/sergiu/heapupboxgame/level_items/platforms/platform_1.png";
    private final String boxPath = "/com/sergiu/heapupboxgame/level_items/boxes/box_1.png";

    private int numberOfBoxes = 3; //number of boxes in this level, minimum 2 boxes, maximum 5 boxes
    private final int timerMaxSeconds = numberOfBoxes * 5; //5 seconds per box
    BuildLevel level = new BuildLevel(platformImagePath, numberOfBoxes, boxPath);

    public Level_0_Controller() throws URISyntaxException {
    }

    public void initialize() throws URISyntaxException {
        super.initialize(timerMaxSeconds);
        try {
            level.createLevel(mainLevelPane);
            levelInitialized(true);
        } catch (URISyntaxException e) {
            levelInitialized(false);
        }
    }
}

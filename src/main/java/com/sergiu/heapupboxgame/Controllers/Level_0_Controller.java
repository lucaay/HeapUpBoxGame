package com.sergiu.heapupboxgame.Controllers;

import com.sergiu.heapupboxgame.Builder.BuildLevel;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.net.URISyntaxException;

public class Level_0_Controller extends LevelController {
    //build level here and specific methods for this level

    @FXML
    private AnchorPane mainLevelPane;

    BuildLevel level = new BuildLevel();
    private int numberOfBoxes = 5; //number of boxes in this level, minimum 2 boxes, maximum 5 boxes
    private int timerMaxSeconds = numberOfBoxes * 10; //default value, adjust the value for each level depending on the number of boxes

    public void initialize() throws URISyntaxException {
        super.initialize(timerMaxSeconds);
        try {
            level.createLevel("/com/sergiu/heapupboxgame/level_items/platforms/platform_1.png", "/com/sergiu/heapupboxgame/level_items/boxes/box_1.png", numberOfBoxes, mainLevelPane);
            levelInitialized(true);
        } catch (URISyntaxException e) {
            levelInitialized(false);
        }
    }
}

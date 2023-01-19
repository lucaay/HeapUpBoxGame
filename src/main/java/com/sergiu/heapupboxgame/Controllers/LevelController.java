package com.sergiu.heapupboxgame.Controllers;

import com.sergiu.heapupboxgame.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public abstract class LevelController extends SceneController {
    @FXML
    private Label noLevelDataLabel;


    public void levelInitialized(boolean initialized) {
        //set of instructions to be executed when the level is initialized (when the level is loaded)
        noLevelDataLabel.setVisible(!initialized);
    }

    @FXML
    void switchToLevelSelector(ActionEvent event) throws IOException {
        super.switchToLevelSelector(event, "/com/sergiu/heapupboxgame/level-selector-screen.fxml");
    }

    @FXML
    void reloadCurrentScene(ActionEvent event, String pathToCurrentScene) throws IOException {
        super.reloadScene(event, pathToCurrentScene);
    }

    @FXML
    private void gameOverExitAction(ActionEvent event) throws IOException {
        switchToLevelSelector(event);
    }

}

package com.sergiu.heapupboxgame.Controllers;

import com.sergiu.heapupboxgame.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class Level_0_Controller extends SceneController {

        @FXML
        public void switchToLevelSelector(ActionEvent event) throws IOException {
            super.switchToLevelSelector(event, "/com/sergiu/heapupboxgame/level-selector-screen.fxml");
        }

}

package com.sergiu.heapupboxgame.Controllers;

import com.sergiu.heapupboxgame.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class Level_0_Controller extends SceneController {
    @FXML
    private URL location;
    @FXML
    private Label levelIndicator;
    @FXML
    private Label timerLabel;
    private int seconds = 0;
    Timeline timeline = new Timeline();

    @FXML
    public void switchToLevelSelector(ActionEvent event) throws IOException {
        super.switchToLevelSelector(event, "/com/sergiu/heapupboxgame/level-selector-screen.fxml");
    }

    private int getNumberFromLastPartOfPath(String path) {
        int lastSlash = path.lastIndexOf("/");
        String lastPart = path.substring(lastSlash + 1);
        String number = lastPart.replaceAll("[^0-9]", "");
        return Integer.parseInt(number);
    }

    private void Timer(Label label) {
        timerLabel = label;
        timerLabel.setTextFill(Color.GREEN);
        timerLabel.setText("00:00");
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                seconds++;
                String sec = String.format("%02d", seconds);
                timerLabel.setText("00:" + sec);
                if (seconds > 10) {
                    timerLabel.setTextFill(Color.YELLOW);
                }
                if (seconds > 20) {
                    timerLabel.setTextFill(Color.ORANGE);
                }
                if (seconds == 30) {
                    GameOver();
                    timerLabel.setTextFill(Color.RED);
                }
            }
        }));
        timeline.play();
    }

    private void GameOver() {
        timeline.stop();
        timerLabel.setTextFill(Color.RED);
        System.out.println("Game Over");
    }

    @FXML
    public void initialize() {
        Timer(timerLabel);
        levelIndicator.setText("Level " + getNumberFromLastPartOfPath(location.toString()));

    }
}

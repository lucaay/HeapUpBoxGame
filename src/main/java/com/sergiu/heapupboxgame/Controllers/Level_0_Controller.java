package com.sergiu.heapupboxgame.Controllers;

import com.sergiu.heapupboxgame.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
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
    @FXML
    private Pane giveUpConfirmation;
    @FXML
    private Pane gameOverPane;
    private int seconds = 0;
    Timeline timeline = new Timeline();

    @FXML
    public void switchToLevelSelector(ActionEvent event) throws IOException {
        super.switchToLevelSelector(event, "/com/sergiu/heapupboxgame/level-selector-screen.fxml");
    }

    @Override
    public void reloadScene(String fxmlFile) {
        super.reloadScene(fxmlFile);
    }

    @FXML
    private void giveUpConfirmation(ActionEvent event) throws IOException {
        timeline.stop();
        giveUpConfirmation.setVisible(true);
    }
    @FXML
    private void giveUpYesAction(ActionEvent event) throws IOException {
        switchToLevelSelector(event);
    }
    @FXML
    private void giveUpNoAction(ActionEvent event) throws IOException {
        giveUpConfirmation.setVisible(false);
        timeline.play();
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
        timerLabel.setText("00");
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                seconds++;
                String sec = String.format("%02d", seconds);
                timerLabel.setText(sec);
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

    @FXML
    private void gameOverRetryAction(ActionEvent event) throws IOException {
        reloadScene("/com/sergiu/heapupboxgame/level_0.fxml");
    }
    @FXML
    private void gameOverExitAction(ActionEvent event) throws IOException {
        switchToLevelSelector(event);
    }

    private void GameOver() {
        timeline.stop();
        timerLabel.setTextFill(Color.RED);
        gameOverPane.setVisible(true);
        System.out.println("Game Over");
    }

    @FXML
    public void initialize() {
        Timer(timerLabel);
        levelIndicator.setText("Level " + getNumberFromLastPartOfPath(location.toString()));

    }
}

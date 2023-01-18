package com.sergiu.heapupboxgame.Controllers;

import com.sergiu.heapupboxgame.Adapter.MouseInput;
import com.sergiu.heapupboxgame.AudioController;
import com.sergiu.heapupboxgame.SceneController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public abstract class LevelController extends SceneController {
    Timeline timeline = new Timeline();
    AudioController timerSound = new AudioController("src/main/resources/com/sergiu/heapupboxgame/sounds/clock-tick.wav");
    AudioController giveUpSound = new AudioController("src/main/resources/com/sergiu/heapupboxgame/sounds/giveUp_007.wav");
    AudioController errorSound = new AudioController("src/main/resources/com/sergiu/heapupboxgame/sounds/error_001.wav");
    AudioController confirmationSound = new AudioController("src/main/resources/com/sergiu/heapupboxgame/sounds/confirmation_001.wav");
    AudioController gameOverSound = new AudioController("src/main/resources/com/sergiu/heapupboxgame/sounds/game_over.wav");
    AudioController gameWonSound = new AudioController("src/main/resources/com/sergiu/heapupboxgame/sounds/confirmation_001.wav");
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
    @FXML
    private Pane gameWonPane;
    @FXML
    private Label noLevelDataLabel;
    @FXML
    private AnchorPane mainLevelPane;
    private int seconds = 0;

    public void levelInitialized(boolean initialized) {
        //set of instructions to be executed when the level is initialized (when the level is loaded)
        noLevelDataLabel.setVisible(!initialized);
    }

    @FXML
    private void switchToLevelSelector(ActionEvent event) throws IOException {
        super.switchToLevelSelector(event, "/com/sergiu/heapupboxgame/level-selector-screen.fxml");
    }

    @FXML
    private void reloadCurrentScene(ActionEvent event, String pathToCurrentScene) throws IOException {
        super.reloadScene(event, pathToCurrentScene);
    }


    @FXML
    private void giveUpConfirmation(ActionEvent event) throws IOException {
        timeline.stop();
        giveUpConfirmation.setVisible(true);
        giveUpSound.play();
    }

    @FXML
    private void giveUpYesAction(ActionEvent event) throws IOException {
        switchToLevelSelector(event);
        confirmationSound.play();
    }

    @FXML
    private void giveUpNoAction(ActionEvent event) throws IOException {
        giveUpConfirmation.setVisible(false);
        timeline.play();
        errorSound.play();
    }

    private int getNumberFromLastPartOfPath(String path) {
        int lastSlash = path.lastIndexOf("/");
        String lastPart = path.substring(lastSlash + 1);
        String number = lastPart.replaceAll("[^0-9]", "");
        return Integer.parseInt(number);
    }

    private void Timer(Label label, int maxSeconds) {
        timerLabel = label;
        timerLabel.setText(String.valueOf(maxSeconds));
        timeline.setCycleCount(Timeline.INDEFINITE);
        seconds = maxSeconds;
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                seconds--;
                String sec = String.format("%02d", seconds);
                timerLabel.setText(sec);
                if (seconds >= ((75 * maxSeconds) / 100)) {
                    timerLabel.setTextFill(Color.rgb(102, 255, 102));
                } else if (seconds >= ((50 * maxSeconds) / 100)) {
                    timerLabel.setTextFill(Color.YELLOW);
                } else if (seconds >= ((25 * maxSeconds) / 100)) {
                    timerLabel.setTextFill(Color.ORANGE);
                } else if (seconds == 0) {
                    GameOver();
                    timerLabel.setTextFill(Color.RED);
                }
                timerSound.play();
            }
        }));
        timeline.play();
    }

    @FXML
    private void gameOverRetryAction(ActionEvent event) throws IOException {
        reloadCurrentScene(event, "/com/sergiu/heapupboxgame/levels/level-0/level-0.fxml");
    }

    @FXML
    private void gameOverExitAction(ActionEvent event) throws IOException {
        switchToLevelSelector(event);
    }

    private void GameOver() {
        timeline.stop();
        timerLabel.setTextFill(Color.RED);
        gameOverSound.play();
        gameOverPane.setVisible(true);
        System.out.println("Game Over");
    }

    public void GameWon() {
        timeline.stop();
        timerLabel.setTextFill(Color.GREEN);
        gameWonSound.play();
        gameWonPane.setVisible(true);
    }

    public void initialize(int timerMaxSeconds) {
        Timer(timerLabel, timerMaxSeconds);
        levelIndicator.setText("Level " + getNumberFromLastPartOfPath(location.toString()));
    }

    public abstract void GameWon(ImageView box, ImageView wonLine);
}
